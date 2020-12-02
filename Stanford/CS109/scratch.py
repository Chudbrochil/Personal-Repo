from scipy import stats

def num_infected():
    immune = stats.bernoulli(0.99).rvs()
    if immune: return 0

    spread = 0
    k = stats.binom(100, 0.25).rvs()
    for i in range(k):
        spread += num_infected()

    return spread + 1


total = 0
trials = 10000

for _ in range(trials):
    total += num_infected()

print(total/trials)

