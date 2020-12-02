# Do NOT add any other import statements.
# Don't remove these import statements.
import numpy as np
import copy
import os

# Name:
# Stanford email:

########### CS109 Problem Set 5, Question 8 ##############
def get_filepath(filename):
    """
    filename is the name of a data file, e.g.,
    "learningOutcomes.csv". You can call this helper
    function in all parts of your code.

    Return a full path to the data file, located in the
    directory datasets, e.g., "datasets/learningOutcomes.csv"
    """
    return os.path.join("datasets", filename)

"""
Assembled by Lisa Yan and Past CS109 TA Anand Shankar
*************************IMPORTANT*************************
For part_a and part_b, do NOT modify the name of 
the functions. Do not add or remove parameters to them
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
    "learningOutcomes.csv". You must use the filename 
    variable. Do NOT alter the filename variable, 
    and do NOT hard-code a filepath; if you do, you'll 
    likely fail the autograder.
    You can use the helper function defined above, get_filepath().

    Return the difference in sample means (float) as 
    described in the handout.
    """

    data = np.genfromtxt(get_filepath(filename), names = ['id', 'activity', 'score'], dtype=[('id', np.int32), ('activity', np.dtype('U9')), ('score', np.int32)], delimiter=',')
    activity1, activity2 = data[data['activity'] == "activity1"], data[data['activity'] == "activity2"]
    a1_scores, a2_scores = [row[2] for row in activity1], [row[2] for row in activity2]
    return abs(np.mean(a1_scores) - np.mean(a2_scores))

def part_b(filename, seed=109):
    """
    filename is the name of a data file, e.g. 
    "learningOutcomes.csv". You must use the filename 
    variable. Do NOT alter the filename variable, 
    and do NOT hard-code a filepath; if you do, you'll 
    likely fail the autograder.

    You MUST use np.random.choice with replace=True
    to draw random samples. You may NOT use any other 
    function to draw random samples. See assignment 
    handout for details.

    Return the p-value (float) as described in the handout.
    """
    np.random.seed(seed)  # DO NOT ALTER OR DELETE THIS LINE

    ### BEGIN YOUR CODE FOR PART (B) ###

    data = np.genfromtxt(get_filepath(filename), names = ['id', 'activity', 'score'], dtype=[('id', np.int32), ('activity', np.dtype('U9')), ('score', np.int32)], delimiter=',')
    activity1, activity2 = data[data['activity'] == "activity1"], data[data['activity'] == "activity2"]
    a1_scores, a2_scores = [row[2] for row in activity1], [row[2] for row in activity2]

    true_diff = part_a(filename)

    # 1. Create universal sample with two samples.
    universal_sample = a1_scores + a2_scores

    # 2. Repeat bootstrapping procedure 10000 times.
    num_greater_diffs = 0
    iters = 10000
    for i in range(iters):
        resample1 = resample(universal_sample, len(a1_scores))
        resample2 = resample(universal_sample, len(a2_scores))

        diff = abs(np.mean(resample1) - np.mean(resample2))

        if diff > true_diff:
            num_greater_diffs += 1

    p_val = num_greater_diffs / iters

    return p_val

    ### END YOUR CODE FOR PART (B) ###

def resample(data, n):
    return np.random.choice(data, n, replace=True)


def optional_function():
    """
    We won't autograde anything you write in this function.
    But we've included this function here for convenience. 
    It will get called by our provided main method. Feel free
    to do whatever you want here, including leaving this function 
    blank. We won't read or grade it.
    """

    np.random.seed(109)

    data_scores = np.genfromtxt(get_filepath('learningOutcomes.csv'), names=['id', 'activity', 'score'], dtype=[('id', np.int32), ('activity', np.dtype('U9')), ('score', np.int32)], delimiter=',')
    data_back = np.genfromtxt(get_filepath('background.csv'), names=['id', 'background'], dtype=[('id', np.int32), ('background', np.dtype('U7'))], delimiter=',')

    activity1, activity2 = data_scores[data_scores['activity'] == "activity1"], data_scores[data_scores['activity'] == "activity2"]
    a1_scores, a2_scores = [row[2] for row in activity1], [row[2] for row in activity2]

    back_less, back_avg, back_more = data_back[data_back['background'] == 'less'], data_back[data_back['background'] == 'average'], data_back[data_back['background'] == 'more']
    backs = [back_less, back_avg, back_more]

    # Loop over all the backgrounds and get a difference in means between act1 and act2. Similar to part a.
    for back in backs:

        act1_scores = []
        act2_scores = []

        for row in back:
            id = row['id']
            score_row = data_scores[data_scores['id'] == id]
            score = score_row['score']

            if score_row['activity'] == "activity1":
                act1_scores.append(score)
            else:
                act2_scores.append(score)

        back_mean = abs(np.mean(act1_scores) - np.mean(act2_scores))

        # Calculate p-val, exact same code as in part_b
        universal_sample = (act1_scores + act2_scores)

        # This returns a list of arrays for some reason. Need to flatten it.
        universal_sample = [element for sublist in universal_sample for element in sublist]

        num_greater_diffs = 0
        iters = 10000
        for i in range(iters):
            resample1 = resample(universal_sample, len(act1_scores))
            resample2 = resample(universal_sample, len(act2_scores))

            diff = abs(np.mean(resample1) - np.mean(resample2))

            if diff > back_mean:
                num_greater_diffs += 1

        p_val = num_greater_diffs / iters

        print("Background: {} mean: {:.2f} p-value: {}".format(back[0]['background'], back_mean, p_val))


def main():
    """
    We've provided this for convenience, simply to call 
    the functions above. Feel free to modify this 
    function however you like. We won't grade anything in 
    this function.
    """
    print("****************************************************")
    print("Calling part_a with filename 'learningOutcomes.csv':")
    print("\tReturn value was:", part_a('learningOutcomes.csv'))
    print("****************************************************")

    print("****************************************************")
    print("Calling part_b with filename 'learningOutcomes.csv':")
    print("\tReturn value was:", part_b('learningOutcomes.csv'))
    print("****************************************************")

    print("****************************************************")
    print("Calling optional_function:")
    print("\tReturn value was:", optional_function())
    print("****************************************************")


    print("Done!")


# This if-condition is True if this file was executed directly.
# It's False if this file was executed indirectly, e.g. as part
# of an import statement.
if __name__ == "__main__":
    main()
