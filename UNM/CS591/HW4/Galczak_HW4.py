from matplotlib import pyplot as plt
import math
import numpy as np


def main():
    # Constants defined from prompt on HW3
    Rm = 9 * (10**7)
    Vreset = -0.065
    El = Vreset
    V0 = Vreset
    Vth = -0.05
    tau_m = 0.03
    Ek = -0.07
    tau_sra = 0.1
    t_s = float("-inf")
    tau_s = 0.01

    # Tunable parameters
    Ie = 0
    Ws = 0.6
    Es = 0.0 # Excitatory synapse

    # Time initialization
    how_many_seconds = 10
    num_of_timesteps = 4000 * how_many_seconds
    delta_t = .00025

    # Hard coding r_isis frequency
    r_isis = 20

    list_of_list_of_spike_times = generate_spikes(tau_m, Vreset, Vth, num_of_timesteps, delta_t, t_s, tau_s, Ek, Rm, Ie, tau_sra, Ws, Es, r_isis, how_many_seconds)

    list_of_spike_data = generate_data(list_of_list_of_spike_times)

    plot_title = r"Excitatory, n=2, $W_{s}$=" + str(Ws) + r", $R_{isis}$=" + str(r_isis)

    plot_fshift_vs_avg_risi(list_of_spike_data, plot_title, r_isis)


# generate_spikes()
# Given some tunable parameters, returns the number of spikes for a range
# of current
def generate_spikes(tau_m, Vreset, Vth, num_of_timesteps, delta_t, t_s, tau_s, Ek, Rm, Ie, tau_sra, Ws, Es, r_isis, how_many_seconds):

    list_of_list_of_spike_times = []

    # Doing this because otherwise the mod will never work
    t_isis = int((1.0 / r_isis) * (num_of_timesteps / how_many_seconds))

    # We need a fractional shift between synapse 1 and synapse 2.
    time_shift_list = np.linspace(0, t_isis, 500)

    for time_shift_delta in time_shift_list:
        # t_j and tau_j replace t_s and tau_s
        tau_s = 0.01
        t_j1 = float("-inf")
        t_j2 = float("-inf")
        V = Vreset

        list_of_spike_times = []
        for i in range(1, num_of_timesteps):

            # i ranges from 0-40000, delta_t = 0.00025, some of the floating
            # points come out a bit weird, so I am rounding to 5 sig figs
            actual_time = round(i * delta_t, 5)

            if i % t_isis == 0:
                t_j1 = actual_time

            if (i + int(time_shift_delta)) % t_isis == 0:
                t_j2 = actual_time

            # Ws, Es are the same between the two synapses. So I'm using the same value.
            # He uses Wj and Ej, but I'm not changing the weights or E
            Vnext = V + (delta_t / tau_m) * (-V + Vreset + Rm * Ie -
                    ((Ws * (V - Es) * math.exp(-(actual_time - t_j1) / tau_s)) +
                    (Ws * (V - Es) * math.exp(-(actual_time - t_j2) / tau_s))))

            if Vnext >= Vth:
                V = Vreset # Setting V back to Vreset or El

                # Putting the spike time (in seconds) into list.
                list_of_spike_times.append(actual_time)
            # Iterating
            else:
                V = Vnext

        # Fractional shift is just time_shift / t_isis
        fractional_shift = time_shift_delta / t_isis
        list_of_list_of_spike_times.append((fractional_shift, list_of_spike_times))

    return list_of_list_of_spike_times


# generate_data()
# This method takes a list of spike timings and returns a list of tuples
# consisting of (r_isi, avg_interspike_time) for each frequency r_isi we have.
def generate_data(list_of_list_of_spike_times):

    list_of_spike_data = []

    # Getting the average inter-spike times for each fractional_shift
    for list_of_spike_times in list_of_list_of_spike_times:
        fractional_shift = list_of_spike_times[0]
        spike_times = list_of_spike_times[1]

        how_many_spikes = len(spike_times)
        list_of_interspike_times = []

        # If we didn't have any spikes or we only had 1 spike, then our
        # inter-spike average is 0
        for i in range(how_many_spikes):

            if (i + 1) < how_many_spikes:
                list_of_interspike_times.append((spike_times[i+1] - spike_times[i]))

        avg_firing_rate = 0
        stddev = 0
        if list_of_interspike_times != []:

            avg_firing_rate = 1.0 / np.average(list_of_interspike_times)
            stddev = np.std(list_of_interspike_times)

        # Standard deviation doesn't actually show up much in graph, multiply by 50
        list_of_spike_data.append((fractional_shift, avg_firing_rate, stddev * 50))

    return list_of_spike_data


# plot_fshift_vs_avg_risi()
# Plotting fractional shift vs avg. firing rate and stddev
def plot_fshift_vs_avg_risi(list_of_spike_data, plot_title, r_isis):
    x = [i[0] for i in list_of_spike_data]
    y = [i[1] for i in list_of_spike_data]
    stddev = [i[2] for i in list_of_spike_data]

    plt.xlabel(r'Fractional Shift', fontsize=14)
    plt.ylabel(r'Ave & SD $R_{isi}$ (Hz)', fontsize=14)
    plt.title(plot_title, fontsize=18)

    avg_rate_plot = plt.plot(x,y, label="Output Avg")
    stddev_plot = plt.plot(x, stddev, label="Output Stddev")
    plt.legend()
    plt.show()


if __name__ == "__main__":
    main()
