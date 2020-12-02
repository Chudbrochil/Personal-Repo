# Do NOT add any other import statements
import numpy as np

# Name:
# Stanford email:

########### CS109 Problem Set 2, Question 10 ##############
"""
************************IMPORTANT************************
For part_a and part_b, do NOT modify the name of the 
functions. Do not add or remove parameters to them either.
Moreover, make sure your return value is exactly as 
described in the PDF handout and in the provided function 
comments. Remember that your code is being autograded. You
are free to write helper functions if you so desire.
Do NOT rename this file.
************************IMPORTANT************************
"""

def part_a(filename="bats.csv"):
    """
    filename is a path to a csv file, e.g. "bats.csv".
    You must use the filename variable. Do NOT alter the 
    filename variable, and do NOT hard-code a filepath;
    if you do, you'll likely fail the autograder.

    You should return a numpy array of length 6, 
    call it probs. probs[i] should be P(G_i) for 
    0 <= i <= 4. probs[5] should be P(T).

    See the assignment handout for some advice on how 
    to use numpy to make your life easier in this 
    function.
    """

    data = np.genfromtxt(filename, delimiter=',')
    # Take average among the columns to get P(var)
    cols_avg = np.mean(data, axis=0)
    return cols_avg


def part_b(filename="bats.csv"):
    """
    filename is a path to a csv file, e.g. "bats.csv".
    As in part_a, you must use this variable or you'll
    likely fail the autograder.

    You should return a numpy array of length 5, call it 
    probs, where probs[i] is equal to P(T | G_i). See 
    the assignment handout for some information on 
    numpy functionality that'll help you here.
    """

    # We want P(T|G_i). P(T|G_i) = P(G_i,T)/P(G_i)
    # We can get P(G_i) from same process from part a, we need to calculate P(G_i,T) fresh
    data = np.genfromtxt(filename, delimiter=',')
    cols_avg = np.mean(data, axis=0)

    # Get rows where T is 1, trait present, remove that column and leave G_i's.
    data_T = data[np.where(data[:, 5] == 1)][:, :5]
    # Since "1" is a positive, we can just sum across columns to get the count.
    data_G_i_T = np.sum(data_T, axis=0)
    # Divide by number of "training examples" to get probability P(G_i,T)
    data_G_i_T = data_G_i_T / data.shape[0]
    # Divide by P(G_i)
    return data_G_i_T / cols_avg[:5]

def part_c():
    """
    We won't autograde anything you write in this function.
    You should submit your answer to part (c) in the PDF
    you upload to Gradescope. But we've included this function
    here for convenience. It will get called by our provided
    main method. Feel free to do whatever you want here, 
    including leaving this function blank. We won't read it.
    """
    print(part_a())
    print(part_b())


def main():
    """
    We've provided this for convenience, simply to call the 
    functions above for parts a, b, and c. Feel free to modify
    this function however you like. We won't grade anything
    in this function.
    """
    print("*** Beginning part (a) on 'bats.csv' ***")
    probs = part_a()
    print(f"part (a) returned {probs}")
    print("*** Ending part (a) ***\n")

    print("*** Beginning part (b) on 'bats.csv' ***")
    cond_probs = part_b()
    print(f"part (b) returned {cond_probs}")
    print("*** Ending part (b) ***\n")

    print("*** Beginning part (c) - convenience function ***")
    part_c()
    print("*** Ending part (c) ***")


########### CS109 Problem Set 2, Question 12 ##############

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
    sunetid = "agalczak" # your sunet id here."
    title = "Analyst Research on CCL (Carnival Cruise Line stock)" # your article title here
    url = "https://www.nasdaq.com/market-activity/stocks/ccl/analyst-research" # a link to your article here
    return sunetid, title, url


############################################################
# This if-condition is True if this file was executed directly.
# It's False if this file was executed indirectly, e.g. as part
# of an import statement.
if __name__ == "__main__":
    main()
