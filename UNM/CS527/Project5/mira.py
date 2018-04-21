# mira.py
# -------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


# Mira implementation
import util
import random
PRINT = True

class MiraClassifier:
    """
    Mira classifier.

    Note that the variable 'datum' in this code refers to a counter of features
    (not to a raw samples.Datum).
    """
    def __init__( self, legalLabels, max_iterations):
        self.legalLabels = legalLabels
        self.type = "mira"
        self.automaticTuning = False
        self.C = 0.001
        self.legalLabels = legalLabels
        self.max_iterations = max_iterations
        self.initializeWeightsToZero()

    def initializeWeightsToZero(self):
        "Resets the weights of each label to zero vectors"
        self.weights = {}
        for label in self.legalLabels:
            self.weights[label] = util.Counter() # this is the data-structure you should use

    def train(self, trainingData, trainingLabels, validationData, validationLabels):
        "Outside shell to call your method. Do not modify this method."

        self.features = trainingData[0].keys() # this could be useful for your code later...

        if (self.automaticTuning):
            Cgrid = [0.002, 0.004, 0.008]
        else:
            Cgrid = [self.C]

        return self.trainAndTune(trainingData, trainingLabels, validationData, validationLabels, Cgrid)

    def trainAndTune(self, trainingData, trainingLabels, validationData, validationLabels, Cgrid):
        """
        This method sets self.weights using MIRA.  Train the classifier for each value of C in Cgrid,
        then store the weights that give the best accuracy on the validationData.

        Use the provided self.weights[label] data structure so that
        the classify method works correctly. Also, recall that a
        datum is a counter from features to values for those features
        representing a vector of values.
        """


        print(Cgrid)

        tau = 1

        # tau is defined as T = min (C, bigFun)
        # bigFun = ((self.weights[possibleLabel] - self.weights[realLabel]) * feature + 1) / (2 * (f * f))


        for iteration in range(self.max_iterations):

            for i in range(len(trainingData)):

                highScore = None
                possibleLabel = None
                feature = trainingData[i]

                # They told us classification would improve if we randomized the training examples,
                # This gives approximately +10 improvement (55->62 and 48->59)
                randLabels = self.legalLabels
                random.shuffle(randLabels)

                # Go through all the labels and find out which one scores the highest
                for label in randLabels:
                    score = feature * self.weights[label]
                    if score > highScore:
                        highScore = score
                        possibleLabel = label



                # If we didn't get a correct classification, then update the weights
                realLabel = trainingLabels[i]
                tau = min(Cgrid[0], ((self.weights[possibleLabel] - self.weights[realLabel]) * feature + 1) /
                          (2 * (feature * feature)))
                if possibleLabel != trainingLabels[i]:
                    self.weights[realLabel] += feature*tau # Reinforce
                    self.weights[possibleLabel] -= feature*tau # Penalize











    def classify(self, data ):
        """
        Classifies each datum as the label that most closely matches the prototype vector
        for that label.  See the project description for details.

        Recall that a datum is a util.counter...
        """
        guesses = []
        for datum in data:
            vectors = util.Counter()
            for l in self.legalLabels:
                vectors[l] = self.weights[l] * datum
            guesses.append(vectors.argMax())
        return guesses


