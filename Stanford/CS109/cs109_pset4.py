# Name:
# Stanford email:

########### CS109 Problem Set 4, Question 10 ##############

# Do NOT add any other import statements.
# Don't remove this import statement.
import numpy as np
from probabilities import *

"""
Starter Code for CS 109 Problem Set 4

*************************IMPORTANT*************************
The file probabilities contains helper functions 
probStress(), probExposure(), probCold(s, e), probFlu(s, e), 
and probSymptom(i, f, c). You should NOT re-implement these
functions; they are imported for you already.

We may additionally test your code with different values
from what is provided to you.

You can call the functions directly from this file:
  x = probFlu() + 5     # not a probability

*************************IMPORTANT*************************
"""


def inferProbFlu(ntrials = 1000000) -> float:
    """
    This is question 10 part (a).
    
    Computes P(Flu = 1 | Exposure = 1, X_2 = 1), the probability
    of flu conditioned on observing that the patient has had
    exposure to a sick friend and that they are experiencing
    Symptom 2 (runny nose). Uses rejection sampling.
    :param ntrials: the number of observations to sample.

    """

    samples = 0
    flu_positive = 0

    for _ in range(ntrials):
        # Take a "trial" to get a realization of our RV's.
        stress = 1 if np.random.rand() < probStress() else 0
        exposure = 1 if np.random.rand() < probExposure() else 0
        cold = 1 if np.random.rand() < probCold(stress, exposure) else 0
        flu = 1 if np.random.rand() < probFlu(stress, exposure) else 0
        x2 = 1 if np.random.rand() < probSymptom(2, flu, cold) else 0

        # If our conditions hold, this is a "non-rejected sample"
        if exposure == 1 and x2 == 1:
            samples += 1
            if flu == 1:
                flu_positive += 1

    return flu_positive / samples


def inferProbFluExact() -> float:
    """
    This is question 10 part (b). This is a REACH problem, meaning 
    that thinking about it will be useful, even if we don't expect
    you to be able to perfectly solve the problem. Show your work.
    If you get stuck (> 15 mins), explain what is hard and
    include your explanation in your writeup, not in your code.

    Computes P(Flu = 1 | Exposure = 1, X_2 = 1) exactly without
    using rejection sampling.
    """


    return -1 # TODO: delete this line and implement the function!


def main():
    """
    We've provided this for convenience, simply to call 
    the functions above. Feel free to modify this 
    function however you like. We won't grade anything in 
    this function.
    """
    print("***********************************************************")
    print("(a) Calling inferProbFlu():")
    print("\tReturn value was:", inferProbFlu())
    print("***********************************************************\n")

    print("***********************************************************")
    print("(b) Calling inferProbFluExact():")
    print("\tReturn value was:", inferProbFluExact())
    print("***********************************************************\n")

    print("Done!")


########### CS109 Problem Set 4, Question 12 ##############
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
    sunetid = "" # your sunet id here.
    title = "" # your article title here
    url = "" # a link to your article here
    return sunetid, title, url


############################################################
# This if-condition is True if this file was executed directly.
# It's False if this file was executed indirectly, e.g. as part
# of an import statement.
if __name__ == "__main__":
    main()
