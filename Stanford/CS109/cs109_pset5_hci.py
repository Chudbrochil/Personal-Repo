# Do NOT add any other import statements.
# Don't remove this import statement.
import numpy as np
import os

# Name:
# Stanford email:

########### CS109 Problem Set 2, Question 7 ##############
def get_filepath(filename):
    """
    filename is the name of a data file, e.g.,
    "peerGrades.csv". You can call this helper
    function in all parts of your code.

    Return a full path to the data file, located in the
    directory datasets, e.g., "datasets/peerGrades.csv"
    """
    return os.path.join("datasets", filename)

"""
Assembled by Lisa Yan and Past CS109 TA Anand Shankar
*************************IMPORTANT*************************
For part_a and part_b and part_c, do NOT modify the name of 
the  functions. Do not add or remove parameters to them
either. Moreover, make sure your return value is exactly as
described in the PDF handout and in the provided function 
comments. Remember that your code is being autograded. 
You are free to write helper functions if you so desire.
Do NOT rename this file.
*************************IMPORTANT*************************
"""

def part_a(filename):
    """
    filename is the name of a data file, e.g. 
    "peerGrades.csv". You must use the filename 
    variable. Do NOT alter the filename variable, 
    and do NOT hard-code a filepath; if you do, you'll 
    likely fail the autograder.
    You can use the helper function defined above, get_filepath().

    Return the sample mean of the 10,000 grades to 
    the control assignment (float).
    """

    return np.mean(np.genfromtxt(get_filepath(filename)))

def simulation(filename, func):
    num_iters = 10000
    num_samples = 5
    data = np.genfromtxt(get_filepath(filename))

    values = []
    for _ in range(num_iters):
        sample = np.random.choice(data, num_samples, replace=True)
        values.append(func(sample))

    return values

def part_b(filename, seed=109):
    """
    filename is the name of a data file, e.g. 
    "peerGrades.csv". You must use the filename 
    variable. Do NOT alter the filename variable, 
    and do NOT hard-code a filepath; if you do, you'll 
    likely fail the autograder. Do not alter the 
    seed variable either.

    You MUST use np.random.choice with replace=True
    to draw random samples. You may NOT use any other 
    function to draw random samples. See assignment 
    handout for details.

    Return the variance of the mean grade that the 
    control experiment described in the assignment 
    handout would have been given.
    """
    np.random.seed(seed)  # DO NOT ALTER OR DELETE THIS LINE

    ### BEGIN YOUR CODE FOR PART (B) ###
    vals =  simulation(filename, np.mean)
    return np.var(vals)
    ### END YOUR CODE FOR PART (B) ###


def part_c(filename, seed=109):
    """
    filename is the name of a data file, e.g. 
    "peerGrades.csv". You must use the filename 
    variable. Do NOT alter the filename variable, 
    and do NOT hard-code a filepath; if you do, you'll 
    likely fail the autograder. Do not alter the 
    seed variable either.

    Just like in part_b, you MUST use np.random.choice.

    Return the variance of the median grade that the 
    control experiment described in the assignment 
    handout would have been given.
    """
    np.random.seed(seed)  # DO NOT ALTER OR DELETE THIS LINE

    ### BEGIN YOUR CODE FOR PART (C) ###
    vals = simulation(filename, np.median)
    return np.var(vals)
    ### END YOUR CODE FOR PART (C) ###


def optional_function():
    """
    We won't autograde anything you write in this function.
    But we've included this function here for convenience. 
    It will get called by our provided main method. Feel free
    to do whatever you want here, including leaving this function 
    blank. We won't read or grade it.
    """

    filename = "peerGrades.csv"

    true_mean = part_a(filename)
    print(np.var(np.genfromtxt(get_filepath(filename))))
    sample_means = simulation(filename, np.mean)
    sample_medians = simulation(filename, np.median)

    print(true_mean)
    print(np.mean(sample_means))
    print(np.mean(sample_medians))

def main():
    """
    We've provided this for convenience, simply to call 
    the functions above. Feel free to modify this 
    function however you like. We won't grade anything in 
    this function.
    """
    print("******************************************************")
    print("Calling part_a with filename 'peerGrades.csv'")
    print("\tReturn value was:", part_a('peerGrades.csv'))
    print("******************************************************")

    print("******************************************************")
    print("Calling part_b with filename 'peerGrades.csv'")
    print("\tReturn value was:", part_b('peerGrades.csv'))
    print("******************************************************")

    print("******************************************************")
    print("Calling part_c with filename 'peerGrades.csv'")
    print("\tReturn value was:", part_c('peerGrades.csv'))
    print("******************************************************")

    print("******************************************************")
    print("Calling optional_function:")
    print("\tReturn value was:", optional_function())
    print("******************************************************")

    print("Done!")


# This if-condition is True if this file was executed directly.
# It's False if this file was executed indirectly, e.g. as part
# of an import statement.
if __name__ == "__main__":
    main()
