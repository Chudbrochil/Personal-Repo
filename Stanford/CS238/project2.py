import sys
import pandas as pd
import numpy as np
from collections import defaultdict

def solve_small(df, output_filename):
    (T, R) = get_T_R(df)
    gamma = 0.95
    U = value_iteration(df, gamma, T, R, 0.0)
    output_valueiter_policy(U, output_filename, 100)


def solve_medium(df, output_filename):

    gamma = 1
    available_actions = range(1,8)
    Q = q_learning(df, available_actions, gamma)

    num_states = 50000
    num_actions = len(available_actions)
    output_qlearning_policy(Q, output_filename, num_states, num_actions)


def solve_large(df, output_filename):

    gamma = 0.95
    available_actions = range(1, 10)
    Q = q_learning(df, available_actions, gamma)

    num_states = 312020
    num_actions = len(available_actions)
    output_qlearning_policy(Q, output_filename, num_states, num_actions)


def get_T_R(df):

    # Keep counts of going from state s to state s' given action a.
    N_sas = defaultdict(int)
    # Keep counts of being in state s and taking action a
    N_sa = defaultdict(int)

    R = {}

    # Getting the counts for N_sas and N_sa
    for index, row in df.iterrows():
        s = row["s"]
        a = row["a"]
        s_prime = row["sp"]

        N_sas[(s, a, s_prime)] += 1
        N_sa[(s, a)] += 1
        R[(s, a)] = row["r"] # TODO: Is this right? Is s, a, r unique?

    # Calculate T(s_prime | s, a), i.e. Probability of going to state s' given state s and taking action a.
    T = {}
    for key, value in N_sas.items():
        sa_index = (key[0], key[1])

        # s', a, s key for T.
        T[key] = value / N_sa[sa_index]

    return (T, R)

def value_iteration(df, gamma, T, R, delta):

    possible_states = df.s.unique()

    # Initializing U to 0.
    U = {}
    for state in possible_states:
        U[state] = (0, 1) # Value, action

    k = 0

    # TODO: How should we define stopping criteria? Just doing many iterations for now.
    while k < 500:
        # Loop over all the possible states
        for state in possible_states:

            rows = df.loc[df['s'] == state]
            actions = rows.a.unique()

            best_val = 0
            best_action = 0
            # Loop over possible actions for this state
            for action in actions:

                action_val = 0

                reward = R[(state, action)]
                action_val += reward

                # Loop over available states from each available action.
                for key, value in T.items():
                    if key[0] == state and key[1] == action:
                        s_prime = key[2]
                        action_val += (gamma * value) * U[s_prime][0]

                if action_val > best_val:
                    best_val = action_val
                    best_action = action

                # TODO: May want to save off the original U value to compare U[k+1]
                U[state] = (best_val, best_action)

        k += 1
        print(k)

    return U

def q_learning(df, available_actions, gamma):

    Q = defaultdict(int)
    N = defaultdict(int) # Counts of (s,a) pairs. Used for learning rate calculation.

    num_iterations = 10

    for t in range(num_iterations):

        # Sample randomizes the dataframe row ordering.
        for index, row in df.iterrows():
            state = row['s']
            action = row['a']
            next_state = row['sp']

            N[(state, action)] += 1
            # Find the Q value of the best action from the next state.
            max_new_Q = float('-inf')
            for new_action in available_actions:
                max_new_Q = max(max_new_Q, Q[(next_state,new_action)])

            # Learning rate is set to 1/N(s,a)
            current_Q = Q[(state, action)] + (1 / N[(state, action)]) * (row['r'] + gamma * (max_new_Q - Q[(state, action)]))

            Q[(state, action)] = current_Q

        print(t)

    print(Q)
    return Q


def output_valueiter_policy(U, output_filename, num_states):

    with open(output_filename, 'w') as file:
        for i in range(1, num_states + 1):
            file.write("{0}\n".format(U[i][1]))

def output_qlearning_policy(Q, output_filename, num_states, num_actions):

    with open(output_filename, 'w') as file:
        for state in range(1, num_states + 1):

            best_action = 1
            max_val = float('-inf')
            for action in range(1, num_actions + 1):
                if Q[(state,action)] > max_val and Q[(state,action)] != 0:
                    max_val = Q[(state,action)]
                    best_action = action

            file.write("{0}\n".format(best_action))

def main():

    input_file = sys.argv[1]

    df = pd.read_csv(input_file)

    if input_file.startswith("small"):
        solve_small(df, "small.policy")
    elif input_file.startswith("medium"):
        solve_medium(df, "medium.policy")
    else:
        solve_large(df, "large.policy")

if __name__ == "__main__":
    main()



    # Hints
    # Sort the data, look at the unique states. Try to find a pattern.
    # Page 124 in book may help.
