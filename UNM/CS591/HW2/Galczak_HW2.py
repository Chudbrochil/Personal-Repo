from matplotlib import pyplot as plt

def main():

	# Never-changing constants (From HW2 prompt, i.e. pdf)
	tau = .030 # Neuron has a 30ms time constant
	g_sra_0 = 0 # Initial value of G'sra
	Vreset = -.065
	Ek = -0.07
	tau_sra = 0.1
	Rm = 90000000 # Resistance is 90 Mega-Ohm (Only used when tuning Ie)
	RmIe = 0.09

	# Possible tunable parameters.
	Vth = -.050 # Threshold where we will spike
	g_sra_delta = 1.0
	Ie =  1 * (10**-9) # Ie in RmIe(default) is 1 * (10**-9)

	# Use this if we are tuning Ie and want to set RmIe manually
	#RmIe = Rm * Ie

	# Iterations and time steps.
	iterations = 2000 # Prompt has 2000 (.2s)
	delta_t = .0001 # Recommended 10^-4
	total_time = iterations * delta_t

	list_of_spike_times = generate_spikes(RmIe, tau, Vreset, Vth, iterations, delta_t, g_sra_0, g_sra_delta, tau_sra, Ek)
	(discretized_spike_times, range_of_times, spike_averages_list) = generate_data(iterations, delta_t, list_of_spike_times)

	# Plot generation
	plot_time_vs_spike(range_of_times, discretized_spike_times, RmIe, g_sra_delta)
	plot_time_vs_risi(spike_averages_list, RmIe, g_sra_delta)


# generate_data()
# Given a list of spikes, return some data (Tisi, spike time list, etc.)
# about the spikes.
# TODO: These data structures could potentially be combined...
def generate_data(iterations, delta_t, list_of_spike_times):
	discretized_spike_times = []
	range_of_times = []
	spike_averages_list = [] # List of tuples
	for i in range(iterations):
		actual_time = i * delta_t
		range_of_times.append(actual_time)
		# If we had a spike, add a 1 to list, otherwise add a 0.
		if actual_time in list_of_spike_times:
			discretized_spike_times.append(1)

			# Getting average spike rate at this point
			element_number = list_of_spike_times.index(actual_time)
			element_number += 1 # since 0-index'ed
			avg = element_number / actual_time

			spike_averages_list.append((actual_time, avg))
		else:
			discretized_spike_times.append(0)

	return (discretized_spike_times, range_of_times, spike_averages_list)


# Plotting Time vs. Spike
def plot_time_vs_spike(range_of_times, discretized_spike_times, RmIe, g_sra_delta):
	plt.plot(range_of_times, discretized_spike_times)
	plt.xlabel("Time(s)")
	plt.ylabel("Spike")
	plt.title("Spike-Time vs. Time\n" + r"$R_{m}I_{e}$=" + str(round(RmIe, 2)) + r", $\Delta$" + r"$g_{sra}$=" + str(g_sra_delta))
	plt.grid()
	plt.show()


# Plotting Time vs. Risi
def plot_time_vs_risi(spike_averages_list, RmIe, g_sra_delta):
	x, y = zip(*spike_averages_list) # Separating tuple parts functionally
	plt.scatter(x,y)
	plt.xlabel("Time(s)")
	plt.ylabel(r"$R_{isi}$")
	plt.title(r"$R_{isi}$ vs Spike-Time" + "\n" + r"$R_{m}I_{e}$=" + str(round(RmIe, 2)) + r", $\Delta$" + r"$g_{sra}$=" + str(g_sra_delta))
	plt.grid()
	plt.show()


# generate_spikes()
# Given some tunable parameters, returns the number of spikes for a range
# of current
def generate_spikes(RmIe, tau, Vreset, Vth, iterations, delta_t, g_sra_0, g_sra_delta, tau_sra, Ek):

	V = Vreset
	G = g_sra_0
	list_of_spike_times = []

	for i in range(iterations):
		Gnext = G * (1 - (delta_t / tau_sra))
		Vnext = V + (delta_t / tau) * (-V + Vreset - G*(V - Ek) + RmIe)

		if Vnext >= Vth:
			V = Vreset # Setting V back to Vreset or El
			G = G + g_sra_delta
			# Putting the spike time (in seconds) into list.
			list_of_spike_times.append(i * delta_t)
		# Iterating
		else:
			V = Vnext
			G = Gnext

	return list_of_spike_times


if __name__ == "__main__":
	main()
