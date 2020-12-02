# Name:
# Stanford email:

########### CS109 Problem Set 3, Question 1 ##############
"""
************************IMPORTANT************************
For all parts, do NOT modify the names of the functions.
Do not add or remove parameters to them either.
Moreover, make sure your return value is exactly as
described in the PDF handout and in the provided function
comments. Remember that your code is being autograded. You
are free to write helper functions if you so desire.
Do NOT rename this file.
************************IMPORTANT************************
"""

# Do not add import statements.
# Do not remove this import statement either.
from numpy.random import rand

# part (a) - completed for you
def simulate_bernoulli(p=0.4):
    if rand() < p:
        return 1
    return 0


# part (b)
def simulate_binomial(n=20, p=0.4):
    count = 0
    for _ in range(n):
        if rand() < p:
            count += 1

    return count

# part (c)
def simulate_geometric(p=0.03):
    num_trials = 1
    r = rand()
    while r >= p:
        num_trials += 1
        r = rand()

    return num_trials

# part (d)
def simulate_neg_binomial(r=5, p=0.03):
    count = 0
    successes = 0
    while successes <= r:
        count += 1
        if rand() < p:
            successes += 1

    return count


# Note for parts (e) and (f):
# Since `lambda` is a reserved word in Python, we've used
# the variable name `lamb` instead. Do NOT use the word
# `lambda` in your code. It won't do what you want!


# part (e)
def simulate_poisson(lamb=3.1):
    time_increments = 60000
    prob = lamb / time_increments
    count = 0
    for _ in range(time_increments):
        if rand() < prob:
            count += 1

    return count

# part (f)
def simulate_exponential(lamb=3.1):
    time_increments = 60000
    prob = lamb / time_increments
    r = rand()
    time_steps = 1

    while r >= prob:
        time_steps += 1
        r = rand()

    return time_steps / time_increments

def main():
    """
    We've provided this for convenience.
    Feel free to modify this function however you like.
    We won't grade anything in this function.
    """
    print("Bernoulli:", simulate_bernoulli())

########### CS109 Problem Set 3, Question 13 ##############
"""
*********** Article submission **********
If you choose to submit an article for extra credit, it
  should be in a function named article_ec:
  - this function should take 0 arguments
  - edit the string variable sunetid to be your SUNetID,
    e.g., "yanlisa"
  - edit the string variable title to be your article title,
    e.g., "10 Reasons Why Probability Is Great"
  - edit the string variable url to be a URL to your article,
    e.g., "http://cs109.stanford.edu/"
  - you should not modify the return value
"""
def article_ec():
    sunetid = "agalczak" # your sunet id here.
    title = "Carnival Probability of Bankruptcy" # your article title here
    url = "https://www.macroaxis.com/invest/ratio/CCL--Probability-Of-Bankruptcy" # a link to your article here
    return sunetid, title, url


############################################################
# This if-condition is True if this file was executed directly.
# It's False if this file was executed indirectly, e.g. as part
# of an import statement.
if __name__ == "__main__":
    main()