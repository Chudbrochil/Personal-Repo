import numpy as np
from matplotlib import pyplot as plt

def main():

	# Define constants (from pg. 165a)
	tau = .030 # Neuron has a 30ms time constant
	Rm = 90000000 # Resistance is 90 Mega-Ohm
	Vth = -.050 # Threshold where we will spike

	iterations = 5000 # Was recommended 30000, it's a bit computationally heavy.
	delta_t = .0001 # Was recommended 10^-4 (.0001)
	upper_bound_for_current = 2*(10**-9)
	step_size = upper_bound_for_current / iterations
	total_time = iterations * delta_t

	range_of_currents = np.arange(0, upper_bound_for_current, step_size) # Creating a range of Ie

	range_of_resets = [-.080, -.075, -.070, -.065, -.060]
	#range_of_resets = [-.065]
	firing_rate_dict = {}

	# For each Vreset in the list, we will generate firing rates and a corresponding plot
	for Vreset in range_of_resets:
		spikes_list = generate_spikes(range_of_currents, tau, Vreset, Rm, Vth, iterations, delta_t)
		firing_rate_list = []

		firing_rate_list = [x / total_time for x in spikes_list]
		firing_rate_dict[Vreset] = firing_rate_list

	# Plotting,
	plt.title("Leaky Integrate and Fire Model\n" + r"$\tau{_m}$=" + str(tau) + \
			  r"s, $R_{m}$=" + str(Rm) + r"$\Omega$" + r", $V_{th}$=" + str(Vth) + "V")
	plt.xlabel('Current in Amperes')
	plt.ylabel('Firing rate in Hertz')

	legend_list = []
	for key in firing_rate_dict:
		plt.plot(range_of_currents, firing_rate_dict[key], label=key)
		legend_list.append("Vreset = " + str(key) + "V")

	plt.legend(legend_list)
	plt.show()


# generate_spikes()
# Given some tunable parameters, returns the number of spikes for a range
# of current
def generate_spikes(range_of_currents, tau, Vreset, Rm, Vth, iterations, delta_t):
	spikes_list = []
	for Ie in range_of_currents:
		V = Vreset
		num_of_spikes = 0

		resistance_and_current = Rm * Ie # This will be constant for same current

		# Each iteration of the loop is a timestep, i.e. delta_t
		for i in range(iterations):
			Vnext = V + (delta_t / tau) * (-V + Vreset + resistance_and_current)

			if Vnext >= Vth:
				V = Vreset # Setting V back to Vreset or El
				num_of_spikes += 1
			# Iterating
			else:
				V = Vnext

		spikes_list.append(num_of_spikes)
	return spikes_list


if __name__ == "__main__":
	main()
