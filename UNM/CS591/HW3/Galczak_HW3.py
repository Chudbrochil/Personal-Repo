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
    g_sra_0 = 0
    Ek = -0.07
    tau_sra = 0.1
    g_sra_delta = 1.0
    t_s = float("-inf")
    tau_s = 0.01

    # Tunable parameters
    #Ie = 1 * (10**-9)
    Ie = 0
    Ws = 3.2
    #Ws = 0.9
    Es = 0.0 # Excitatory synapse
    #Es = -0.08 # Inhibitory synapse

    # Time initialization
    num_of_timesteps = 4000
    delta_t = .00025
    using_sra = False

    list_of_list_of_spike_times = generate_spikes(tau_m, Vreset, Vth, num_of_timesteps, delta_t, g_sra_0, g_sra_delta, t_s, tau_s, Ek, Rm, Ie, tau_sra, Ws, Es, using_sra)
    list_of_spike_data = generate_data(list_of_list_of_spike_times)

    if using_sra == False:
        plot_title = "Single Excitatory Synapse, no SRA\n"
    else:
        plot_title = "Single Excitatory Synapse, with SRA\n"

    plot_title += r"$W_{s}$=" + str(Ws) + r", $I_{e}$=" + str(Ie) + r", $E_{s}$=" + str(Es) + r", $\tau{_s}$=" + str(tau_s)

    plot_risis_vs_avg_risi(list_of_spike_data, plot_title)


# generate_spikes()
# Given some tunable parameters, returns the number of spikes for a range
# of current
def generate_spikes(tau_m, Vreset, Vth, num_of_timesteps, delta_t, g_sra_0, g_sra_delta, t_s, tau_s, Ek, Rm, Ie, tau_sra, Ws, Es, using_sra):

    V = Vreset
    G = g_sra_0
    list_of_list_of_spike_times = []

    # Looping over 0-50 Hz firing rate in 0.1 steps
    freq_range = np.arange(1, 50, 0.1)
    rounded_freq_range = []

    # Rounding our frequencies. Otherwise floating point gets excessive.
    for val in freq_range:
        rounded_freq_range.append(round(val,1))

    # Each frequency needs to be iterated on to find average inter-spike time.
    for r_isis in freq_range:
        t_s = float("-inf")

        # Doing this because otherwise the mod will never work
        t_isis = int((1.0 / r_isis) * num_of_timesteps)

        list_of_spike_times = []
        for i in range(num_of_timesteps):

            # i ranges from 0-40000, delta_t = 0.00025, some of the floating
            # points come out a bit weird, so I am rounding to 5 sig figs
            actual_time = round(i * delta_t, 5)

            if i % t_isis == 0:
                t_s = actual_time

            if using_sra == True:
                Gnext = G * (1 - (delta_t / tau_sra))
            Vnext = V + (delta_t / tau_m) * (-V + Vreset + Rm * Ie - G*(V - Ek) -
                    Ws*(V - Es) * math.exp(-(actual_time - t_s)/tau_s))

            if Vnext >= Vth:
                V = Vreset # Setting V back to Vreset or El
                if using_sra == True:
                    G = G + g_sra_delta

                # Putting the spike time (in seconds) into list.
                list_of_spike_times.append(actual_time)
            # Iterating
            else:
                V = Vnext
                if using_sra == True:
                    G = Gnext

        list_of_list_of_spike_times.append((r_isis, list_of_spike_times))

    return list_of_list_of_spike_times


# generate_data()
# This method takes a list of spike timings and returns a list of tuples
# consisting of (r_isi, avg_interspike_time) for each frequency r_isi we have.
def generate_data(list_of_list_of_spike_times):

    list_of_spike_data = []

    # Getting the average inter-spike times for each r_isis
    for list_of_spike_times in list_of_list_of_spike_times:
        r_isi = list_of_spike_times[0]
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

        list_of_spike_data.append((r_isi, avg_firing_rate, stddev))

    return list_of_spike_data


# plot_risi_vs_avg_risi()
# Plotting input r_isis vs. the avg output r_isi
def plot_risis_vs_avg_risi(list_of_spike_data, plot_title):
    x = [i[0] for i in list_of_spike_data]
    y = [i[1] for i in list_of_spike_data]
    stddev = [i[2] for i in list_of_spike_data]

    plt.xlabel(r'Synaptic Input $R_{isis}$ (Hz)')
    plt.ylabel(r'Output avg $R_{isi}$ (Hz)')
    plt.title(plot_title)

    avg_rate_plot = plt.plot(x,y, label="Output Avg")
    stddev_plot = plt.plot(x, stddev, label="Output Stddev")
    plt.legend()
    plt.show()


if __name__ == "__main__":
    main()
