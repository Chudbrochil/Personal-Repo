# Do NOT add any other import statements.
# Don't remove this import statement.
import numpy as np
import os

# Name:
# Stanford email:

########### CS109 Problem Set 5, Question 6 ##############
def get_filepath(filename):
    """
    filename is the name of a data file, e.g.,
    "personKeyTimingA.txt". You can call this helper
    function in all parts of your code.

    Return a full path to the data file, located in the
    directory datasets, e.g., "datasets/personKeyTimingA.txt"
    """
    return os.path.join("datasets", filename)

"""
Assembled by Lisa Yan and Past CS109 TA Anand Shankar
*************************IMPORTANT*************************
For part_a and part_b, do NOT modify the name of the 
functions. Do not add or remove parameters to them either.
Moreover, make sure your return value is exactly as 
described in the PDF handout and in the provided function 
comments. Remember that your code is being autograded. 
You are free to write helper functions if you so desire.
Do NOT rename this file.
*************************IMPORTANT*************************
"""


def part_a(filename):
    """
    filename is the name of a data file, e.g. 
    "personKeyTimingA.txt". You must use the filename 
    variable. Do NOT alter the filename variable, 
    and do NOT hard-code a filepath; if you do, you'll 
    likely fail the autograder.
    You can use the helper function defined above, get_filepath().

    Let X be a random variable as defined in the 
    assignment handout. You should compute and
    return E[X] (which is of type float).
    """

    data = np.genfromtxt(get_filepath(filename), names = ['abs_time', 'key'], delimiter=",")

    # Fancy list comprehension to convert our absolute times to "time from last keypress until next keypress"
    # Notice that we need to start our left hand zip at 0ms. We don't have an explicit keypress at 0ms, but we
    # need to start doing the diff on the first keypress against 0.
    rel_time = [curr - last for last, curr in zip(np.concatenate(([0], data['abs_time'])), data['abs_time'])]

    return np.mean(rel_time)

def part_b(filename):
    """
    filename is the name of a data file, e.g. 
    "personKeyTimingA.txt". You must use the filename 
    variable. Do NOT alter the filename variable, 
    and do NOT hard-code a filepath; if you do, you'll 
    likely fail the autograder.

    Let X be a random variable as defined in the 
    assignment handout. You should compute and
    return E[X^2] (which is of type float).
    """

    data = np.genfromtxt(get_filepath(filename), names=['abs_time', 'key'], delimiter=",")

    rel_time = [curr - last for last, curr in zip(np.concatenate(([0], data['abs_time'])), data['abs_time'])]
    rel_time_squared = [x * x for x in rel_time]

    return np.mean(rel_time_squared)

def optional_function():
    """
    We won't autograde anything you write in this function.
    But we've included this function here for convenience. 
    It will get called by our provided main method. Feel free
    to do whatever you want here, including leaving this function 
    blank. We won't read or grade it.
    """

    #print(part_a("email.txt"), part_b("email.txt"))

    data = np.genfromtxt(get_filepath("email.txt"), names = ['abs_time', 'key'], delimiter=",")
    rel_time = [curr - last for last, curr in zip(np.concatenate(([0], data['abs_time'])), data['abs_time'])]

    print(len(data))
    print(len(rel_time))

    import scipy.stats
    A = scipy.stats.norm(7.41, np.sqrt(0.00268))
    B = scipy.stats.norm(8.03, np.sqrt(0.00249))

    the_sum = 0
    # Must do log likelihood to avoid numerical stability issues.
    for t in rel_time:
        the_sum += A.logpdf(t)
        the_sum -= B.logpdf(t)

    print(np.exp(the_sum))




def main():
    """
    We've provided this for convenience, simply to call 
    the functions above. Feel free to modify this 
    function however you like. We won't grade anything in 
    this function.
    """
    print("***********************************************************")
    print("    Calling part_a with filename 'personKeyTimingA.txt'    ")
    print("\tReturn value was:", part_a('personKeyTimingA.txt'))
    print("***********************************************************\n")

    print("***********************************************************")
    print("    Calling part_a with filename 'personKeyTimingB.txt'    ")
    print("\tReturn value was:", part_a('personKeyTimingB.txt'))
    print("***********************************************************\n")

    print("***********************************************************")
    print("    Calling part_b with filename 'personKeyTimingA.txt'    ")
    print("\tReturn value was:", part_b('personKeyTimingA.txt'))
    print("***********************************************************\n")

    print("***********************************************************")
    print("    Calling part_b with filename 'personKeyTimingB.txt'    ")
    print("\tReturn value was:", part_b('personKeyTimingB.txt'))
    print("***********************************************************\n")

    print("***********************************************************")
    print("                 Calling optional_function                 ")
    print("\tReturn value was:", optional_function())
    print("***********************************************************\n")

    print("Done!")


# This if-condition is True if this file was executed directly.
# It's False if this file was executed indirectly, e.g. as part
# of an import statement.
if __name__ == "__main__":
    main()
