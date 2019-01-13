from matplotlib import pyplot as plt
import math
import numpy as np


def main():

    # Constants from the prompt
    tau_s = 0.1
    delta_t = .001
    y_vector = [1.0, 0.95, 0, 0]

    # Tunable parameters
    a = .1
    w34 = -1.5
    w43 = -1.5

    # initialize Wkj = 0 except w31 = w42 = w33 = w44 = 1. w43 = w34 = -1.5
    weight_matrix = []
    weight_matrix.append([0, 0, 1, 0]) # Weights from y1 to others, y11, y21, y31, y41
    weight_matrix.append([0, 0, 0, 1])
    weight_matrix.append([0, 0, 1, w43])
    weight_matrix.append([0, 0, w34, 1])

    # How long do we want to go for, in seconds.
    total_time = 0.5

    list_of_y3_y4_time = iterate(tau_s, delta_t, weight_matrix, y_vector, a, total_time)

    plot_neurons(list_of_y3_y4_time, y_vector[0], y_vector[1], a, w34, w43)


# iterate()
# Iterate over the equation via euler's method. This returns a list of what
# the values of the outputs (y3, y4) were at the specified time.
def iterate(tau_s, delta_t, weight_matrix, y_vector, a, total_time):
    v_vector = [0, 0, 0, 0]
    list_of_y3_y4_time = []
    time_steps = int(total_time / delta_t)

    for i in range(time_steps):

        # We only want to do updates for y2 and y3. y0, y1 are fixed inputs
        for k in [0,1,2,3]:
            if v_vector[k] >= 0:
                Vnext = v_vector[k] + (delta_t / tau_s) * (-v_vector[k] + sum_w_by_y(weight_matrix, y_vector, k))
            else:
                Vnext = 0

            # Yk = phi(Vk). Phi is v^2 / (a + v^2). We don't update y0, y1.
            if k not in [0,1]:
                y_vector[k] = v_vector[k] ** 2 / (a + v_vector[k] ** 2)

            v_vector[k] = Vnext

        triplet = (y_vector[2], y_vector[3], i * delta_t)
        list_of_y3_y4_time.append(triplet)

    return list_of_y3_y4_time


# sum_w_by_y()
# Dot product of weight_vector and y_vector
def sum_w_by_y(weight_matrix, y_vector, k):

    sum = 0
    iterations = len(y_vector)

    for j in range(iterations):
        sum += weight_matrix[j][k] * y_vector[j]

    return sum


# plot_neurons()
# Plotting y3 and y4 lines
def plot_neurons(list_of_y3_y4_time, y1, y2, a, w43, w34):
    y3 = [i[0] for i in list_of_y3_y4_time]
    y4 = [i[1] for i in list_of_y3_y4_time]
    time = [i[2] for i in list_of_y3_y4_time]

    y3_plot = plt.plot(time, y3, label="y3", color='blue')
    y4_plot = plt.plot(time, y4, label="y4", color='red')

    plt.grid(axis='y')
    plot_title = r"Winner Takes All, $y_{1}$=" + str(y1) + r", $y_{2}$=" + str(y2) + ", a=" + str(a)
    plot_title += "\n" + r"$w_{43}$=" + str(w43) + r", $w_{34}$=" + str(w34)
    plt.title(plot_title, fontsize=20)
    plt.xlabel("Time (sec)", fontsize=18)
    plt.ylabel(r"Outputs $y_{3}$(blue) & $y_{4}$(red)", fontsize=18)
    plt.legend()
    plt.show()



if __name__ == "__main__":
    main()
