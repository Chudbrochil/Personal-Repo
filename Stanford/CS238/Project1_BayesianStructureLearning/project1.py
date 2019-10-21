import sys
import pandas as pd
from collections import defaultdict
import scipy as sp
import networkx as nx
import numpy as np
import signal
import copy
import random
from datetime import datetime

# TODO: Globals
best_graph = ""
best_score = float('-inf')
output_graph_file = ""
output_score_file = ""
start_time = datetime.now()

def compute(input_file):
    global best_graph
    global best_score
    # Initialization, only have to do these once.
    df = pd.read_csv(input_file)
    vars =  list(df.columns.values)

    r_distinct_values = [[] for i in range(len(vars))]
    i = 0
    for column in df.columns.values:
        vals = sorted(list(df[column].unique()))
        r_distinct_values[i] = vals
        i  += 1

    r_distinct = df.nunique(axis=0)

    # Make a graph with no edges
    graph = nx.DiGraph()
    graph.add_nodes_from(vars)

    # Initialize our m_ijk counts from an empty graph, also initialize parental instantiations.
    m_counts = initialize_m_counts(df)
    parent_list = [[] for i in range(len(vars))]

    # Naive K2 Implementation (No topo sort, doing N^2 node comparisons)
    k2_iter = 0
    while True:
        num_vars = len(vars)

        random_children = random.sample(range(0, num_vars), num_vars)
        for child_index in random_children:
            child_name = vars[child_index]

            # Loop over only the parents not matching the children. (No self-loops)
            random_parents = random.sample(range(0, num_vars), num_vars)
            for parent_index in random_parents:
                parent_name = vars[parent_index]

                if child_index != parent_index and graph.has_edge(parent_name, child_name) == False:
                    graph.add_edge(parent_name, child_name)
                    # If we made a cycle, undo our filth.
                    if nx.is_directed_acyclic_graph(graph) == False:
                        graph.remove_edge(parent_name, child_name)
                    # Otherwise, check the score.
                    else:
                        parent_list[child_index].append(parent_index)
                        old_m_counts = copy.deepcopy(m_counts)
                        m_counts = update_m_counts(df, m_counts, r_distinct, parent_list, child_index, r_distinct_values)
                        score = bayesian_score(m_counts, r_distinct, parent_list)

                        if score > best_score:
                            best_score = score
                            best_graph = copy.deepcopy(graph)
                            print("Set new best_graph: score is: %d, k2_iteration: %d" % (best_score, k2_iter))
                        else:
                            parent_list[child_index].remove(parent_index)
                            m_counts = old_m_counts
                            graph.remove_edge(parent_name, child_name)
                    k2_iter += 1

# Initializes our m_ijk structure. This is specifically when we have an empty graph with no edges, i.e. no parents.
def initialize_m_counts(df):
    num_vars = len(df.columns.values)
    m_counts = defaultdict(int)

    for row_index, row in df.iterrows():
        for i in range(num_vars):
            q = 1 # Starting with no parents.
            r = row[i]
            index = "x" + str(i) + "q" + str(q) + "r" + str(r)
            m_counts[index] += 1

    return m_counts

# Updates our m_ijk structure. This is optimized to only update the child that had a parent added.
def update_m_counts(df, m_counts, r_distinct, parent_list, child_index, r_distinct_values):
    # Delete any counts associated with this node that has had a parent added.
    index = "x" + str(child_index) + "q" # q is necessary to avoid x1 deleting x12!
    keys = list(m_counts.keys())
    for key in keys:
        if key.startswith(index):
            m_counts.pop(key)

    parent_indices = parent_list[child_index]
    for row_index, row in df.iterrows():
        q_i = calc_q_i(parent_indices, r_distinct, row, r_distinct_values)
        r = row[child_index]
        index = "x" + str(child_index) + "q" + str(q_i) + "r" + str(r)

        m_counts[index] += 1

    return m_counts

# Given the parents of a node and a row of data, this produces the
# parent instantiation (index) that we are on (q_i).
def calc_q_i(parent_indices, r_distinct, row, r_distinct_values):
    product = 1
    q_i = 1
    num_parents = len(parent_indices)

    # We can have a column with distinct values [1,2,3,5]
    # Because of this, we can't trivially just grab the value - 1 and use it.
    for i in range(num_parents):
        index = parent_indices[i]
        # Getting the exact index of our instantiation among the distinct values in a column.
        val = r_distinct_values[index].index(row[index])

        r_i = r_distinct[index]
        q_i += (product * val)
        product *= r_i

    return q_i

# Calculates the bayesian score of a structure given m_ijk counts.
def bayesian_score(m_counts, r_distinct, parent_list):
    score = 0
    num_vars = len(parent_list)

    for i in range(num_vars):
        alpha_naught = r_distinct[i]

        num_parents = len(parent_list[i])
        if num_parents == 0:
            q_max = 1
        else:
            q_max = np.prod([r_distinct[q] for q in parent_list[i]])

        # 1 more element because we have denoted q_i = 1 as having no parents.
        for j in range(1, q_max + 1):
            m_naught = 0
            index = "x" + str(i) + "q" + str(j)

            for key, value in m_counts.items():
                if key.startswith(index):
                    m_naught += value

                    # Numerator in 2nd term
                    score += sp.special.gammaln(1 + value)

            # Numerator in first term
            score += sp.special.gammaln(alpha_naught)

            # Denominator in first term
            score -= sp.special.gammaln(alpha_naught + m_naught)

    print(score)
    return score

# If for some reason we need to initialize all of the m_counts (i.e. loading in a file of edges), then
# we will need to generate the entire map of m_counts.
def slow_update_m_counts(df, r_distinct, parent_list, r_distinct_values):
    m_counts = defaultdict(int)
    for i in range(len(r_distinct)):
        parent_indices = parent_list[i]
        for row_index, row in df.iterrows():
            q_i = calc_q_i(parent_indices, r_distinct, row, r_distinct_values)
            r = row[i]
            index = "x" + str(i) + "q" + str(q_i) + "r" + str(r)
            m_counts[index] += 1

    return m_counts

def main():
    global output_graph_file
    global output_score_file

    if len(sys.argv) != 4:
        raise Exception("Wrong inputs")

    input_file = sys.argv[1]
    output_graph_file = sys.argv[2]
    output_score_file = sys.argv[3]

    compute(input_file)

# Writes out our graph and score files.
def write_file():
    # Print all the edges
    with open(output_graph_file, 'w') as file:
        for e in best_graph.edges():
            file.write(e[0] + "," + e[1] + "\n")

    # Print out the best score.
    with open(output_score_file, 'w') as file:
        file.write(str(best_score))

    end_time = datetime.now()
    print("Runtime")
    print(end_time - start_time)

# Signal handler so I can early terminate and grab my best graph.
def signal_handler(sig, frame):
    write_file()
    sys.exit(0)

if __name__ == '__main__':
    signal.signal(signal.SIGINT, signal_handler)
    main()

    # # Smart K2
    # k2_iter = 0
    # while True:
    #     # Use all_sorts only for small graphs.
    #     #all_sorts = list(nx.all_topological_sorts(graph))
    #     #topo = random.choice(all_sorts)
    #     topo = list(nx.topological_sort(graph))
    #
    #     while(topo):
    #         child_name = topo.pop(-1) # Popping from right to get bottom of topo
    #         child_index = vars.index(child_name)
    #
    #         parent_topo = copy.deepcopy(topo)
    #
    #         while(parent_topo):
    #             parent_name = parent_topo.pop(-1)
    #             if graph.has_edge(parent_name, child_name) == False:
    #
    #                 parent_index = vars.index(parent_name)
    #
    #                 # Check if adding this edge will give us a score increase.
    #                 graph.add_edge(parent_name, child_name)
    #                 parent_list[child_index].append(parent_index)
    #                 old_m_counts = copy.deepcopy(m_counts)
    #                 m_counts = update_m_counts(df, m_counts, r_distinct, parent_list, child_index, r_distinct_values)
    #                 #m_counts = slow_update_m_counts(df, r_distinct, parent_list, r_distinct_values)
    #                 score = bayesian_score(m_counts, r_distinct, parent_list)
    #
    #                 if score > best_score:
    #                     best_score = score
    #                     best_graph = copy.deepcopy(graph)
    #                     print("Set new best_graph: score is: %d, k2_iteration: %d" % (best_score, k2_iter))
    #                 else:
    #                     parent_list[child_index].remove(parent_index)
    #                     m_counts = old_m_counts
    #                     graph.remove_edge(parent_name, child_name)
    #
    #     k2_iter += 1


