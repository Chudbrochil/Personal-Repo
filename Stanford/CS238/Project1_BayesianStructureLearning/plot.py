import networkx as nx
import matplotlib.pyplot as plt
import pandas as pd

input_file = "large.csv"
graph_file = "saved/large.gph"

df = pd.read_csv(input_file)
vars =  list(df.columns.values)

graph = nx.DiGraph()
graph.add_nodes_from(vars)

# Add all the edges to our graph
with open(graph_file, 'r') as file:
    lines = file.readlines()

    for line in lines:
        line = line.strip("\n")
        line = line.split(",")
        child = line[1]
        parent = line[0]

        graph.add_edge(parent, child)

pos = nx.spring_layout(graph, k=0.6, iterations=20)

nx.draw_networkx(graph, pos, arrows=True)
plt.draw()
plt.savefig('large.png')


