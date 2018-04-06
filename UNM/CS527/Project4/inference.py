# inference.py
# ------------
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


import itertools
import random
import busters
import game

from util import manhattanDistance


class DiscreteDistribution(dict):
    """
    A DiscreteDistribution models belief distributions and weight distributions
    over a finite set of discrete keys.
    """
    def __getitem__(self, key):
        self.setdefault(key, 0)
        return dict.__getitem__(self, key)

    def copy(self):
        """
        Return a copy of the distribution.
        """
        return DiscreteDistribution(dict.copy(self))

    def argMax(self):
        """
        Return the key with the highest value.
        """
        if len(self.keys()) == 0:
            return None
        all = self.items()
        values = [x[1] for x in all]
        maxIndex = values.index(max(values))
        return all[maxIndex][0]

    def total(self):
        """
        Return the sum of values for all keys.
        """
        return float(sum(self.values()))

    def normalize(self):
        """
        Normalize the distribution such that the total value of all keys sums
        to 1. The ratio of values for all keys will remain the same. In the case
        where the total value of the distribution is 0, do nothing.

        >>> dist = DiscreteDistribution()
        >>> dist['a'] = 1
        >>> dist['b'] = 2
        >>> dist['c'] = 2
        >>> dist['d'] = 0
        >>> dist.normalize()
        >>> list(sorted(dist.items()))
        [('a', 0.2), ('b', 0.4), ('c', 0.4), ('d', 0.0)]
        >>> dist['e'] = 4
        >>> list(sorted(dist.items()))
        [('a', 0.2), ('b', 0.4), ('c', 0.4), ('d', 0.0), ('e', 4)]
        >>> empty = DiscreteDistribution()
        >>> empty.normalize()
        >>> empty
        {}
        """

        # Re-assigning every value to value / total, so
        # [('a',5),('b',7),('c',1)] becomes
        # [('a',5/13),('b',7/13),('c',1/13)]
        originalTotal = self.total()
        for key, value in self.iteritems():

            #Catching div by 0
            if originalTotal == 0:
                self[key] = 0
            else:
                self[key] = value / originalTotal


    def sample(self):
        """
        Draw a random sample from the distribution and return the key, weighted
        by the values associated with each key.

        >>> dist = DiscreteDistribution()
        >>> dist['a'] = 1
        >>> dist['b'] = 2
        >>> dist['c'] = 2
        >>> dist['d'] = 0
        >>> N = 100000.0
        >>> samples = [dist.sample() for _ in range(int(N))]
        >>> round(samples.count('a') * 1.0/N, 1)  # proportion of 'a'
        0.2
        >>> round(samples.count('b') * 1.0/N, 1)
        0.4
        >>> round(samples.count('c') * 1.0/N, 1)
        0.4
        >>> round(samples.count('d') * 1.0/N, 1)
        0.0
        """


        # We need the values to be normalized so that we can randomly pick between 0 and 1
        self.normalize()
        randNum = random.random()
        tally = 0

        # Think of all of these values as a range, if our random number was between the end of
        # the range of the last value and the end of the range of our new value, return the key
        # of our new value
        for key, value in self.iteritems():
            tally += value
            if randNum < tally:
                return key


class InferenceModule:
    """
    An inference module tracks a belief distribution over a ghost's location.
    """
    ############################################
    # Useful methods for all inference modules #
    ############################################

    def __init__(self, ghostAgent):
        """
        Set the ghost agent for later access.
        """
        self.ghostAgent = ghostAgent
        self.index = ghostAgent.index
        self.obs = []  # most recent observation position

    def getJailPosition(self):
        return (2 * self.ghostAgent.index - 1, 1)

    def getPositionDistributionHelper(self, gameState, pos, index, agent):
        try:
            jail = self.getJailPosition()
            gameState = self.setGhostPosition(gameState, pos, index + 1)
        except TypeError:
            jail = self.getJailPosition(index)
            gameState = self.setGhostPositions(gameState, pos)
        pacmanPosition = gameState.getPacmanPosition()
        ghostPosition = gameState.getGhostPosition(index + 1)  # The position you set
        dist = DiscreteDistribution()
        if pacmanPosition == ghostPosition:  # The ghost has been caught!
            dist[jail] = 1.0
            return dist
        pacmanSuccessorStates = game.Actions.getLegalNeighbors(pacmanPosition, \
                gameState.getWalls())  # Positions Pacman can move to
        if ghostPosition in pacmanSuccessorStates:  # Ghost could get caught
            mult = 1.0 / float(len(pacmanSuccessorStates))
            dist[jail] = mult
        else:
            mult = 0.0
        actionDist = agent.getDistribution(gameState)
        for action, prob in actionDist.items():
            successorPosition = game.Actions.getSuccessor(ghostPosition, action)
            if successorPosition in pacmanSuccessorStates:  # Ghost could get caught
                denom = float(len(actionDist))
                dist[jail] += prob * (1.0 / denom) * (1.0 - mult)
                dist[successorPosition] = prob * ((denom - 1.0) / denom) * (1.0 - mult)
            else:
                dist[successorPosition] = prob * (1.0 - mult)
        return dist

    def getPositionDistribution(self, gameState, pos, index=None, agent=None):
        """
        Return a distribution over successor positions of the ghost from the
        given gameState. You must first place the ghost in the gameState, using
        setGhostPosition below.
        """
        if index == None:
            index = self.index - 1
        if agent == None:
            agent = self.ghostAgent
        return self.getPositionDistributionHelper(gameState, pos, index, agent)

    def getObservationProb(self, noisyDistance, pacmanPosition, ghostPosition, jailPosition):
        """
        Return the probability P(noisyDistance | pacmanPosition, ghostPosition).
        """

        # If the ghost is in jail and we don't have a reading
        if ghostPosition == jailPosition and noisyDistance == None:
            return 1.0
        # If the ghost happens to be at the jail position, but we have a reading
        elif ghostPosition == jailPosition:
            return 0.0
        # If we don't have a reading, the ghost is in jail
        elif noisyDistance == None:
            return 0.0

        # Otherwise, get P(noisyDistance | trueDistance)
        trueDistance = busters.manhattanDistance(pacmanPosition, ghostPosition)
        return busters.getObservationProbability(noisyDistance, trueDistance)


    def setGhostPosition(self, gameState, ghostPosition, index):
        """
        Set the position of the ghost for this inference module to the specified
        position in the supplied gameState.

        Note that calling setGhostPosition does not change the position of the
        ghost in the GameState object used for tracking the true progression of
        the game.  The code in inference.py only ever receives a deep copy of
        the GameState object which is responsible for maintaining game state,
        not a reference to the original object.  Note also that the ghost
        distance observations are stored at the time the GameState object is
        created, so changing the position of the ghost will not affect the
        functioning of observe.
        """
        conf = game.Configuration(ghostPosition, game.Directions.STOP)
        gameState.data.agentStates[index] = game.AgentState(conf, False)
        return gameState

    def setGhostPositions(self, gameState, ghostPositions):
        """
        Sets the position of all ghosts to the values in ghostPositions.
        """
        for index, pos in enumerate(ghostPositions):
            conf = game.Configuration(pos, game.Directions.STOP)
            gameState.data.agentStates[index + 1] = game.AgentState(conf, False)
        return gameState

    def observe(self, gameState):
        """
        Collect the relevant noisy distance observation and pass it along.
        """
        distances = gameState.getNoisyGhostDistances()
        if len(distances) >= self.index:  # Check for missing observations
            obs = distances[self.index - 1]
            self.obs = obs
            self.observeUpdate(obs, gameState)

    def initialize(self, gameState):
        """
        Initialize beliefs to a uniform distribution over all legal positions.
        """
        self.legalPositions = [p for p in gameState.getWalls().asList(False) if p[1] > 1]
        self.allPositions = self.legalPositions + [self.getJailPosition()]
        self.initializeUniformly(gameState)

    ######################################
    # Methods that need to be overridden #
    ######################################

    def initializeUniformly(self, gameState):
        """
        Set the belief state to a uniform prior belief over all positions.
        """
        raise NotImplementedError

    def observeUpdate(self, observation, gameState):
        """
        Update beliefs based on the given distance observation and gameState.
        """
        raise NotImplementedError

    def elapseTime(self, gameState):
        """
        Predict beliefs for the next time step from a gameState.
        """
        raise NotImplementedError

    def getBeliefDistribution(self):
        """
        Return the agent's current belief state, a distribution over ghost
        locations conditioned on all evidence so far.
        """
        raise NotImplementedError


class ExactInference(InferenceModule):
    """
    The exact dynamic inference module should use forward algorithm updates to
    compute the exact belief function at each time step.
    """
    def initializeUniformly(self, gameState):
        """
        Begin with a uniform distribution over legal ghost positions (i.e., not
        including the jail position).
        """
        self.beliefs = DiscreteDistribution()
        for p in self.legalPositions:
            self.beliefs[p] = 1.0
        self.beliefs.normalize()

    def observeUpdate(self, observation, gameState):
        """
        Update beliefs based on the distance observation and Pacman's position.

        The observation is the noisy Manhattan distance to the ghost you are
        tracking.

        self.allPositions is a list of the possible ghost positions, including
        the jail position. You should only consider positions that are in
        self.allPositions.

        The update model is not entirely stationary: it may depend on Pacman's
        current position. However, this is not a problem, as Pacman's current
        position is known.
        """

        pacmanPos = gameState.getPacmanPosition()
        jailPos = self.getJailPosition()

        # Checking every possible ghost position, getting an observation based on pacman's
        # sonar and multiplying it by the old belief of that state
        for ghostPos in self.allPositions:
            obs = self.getObservationProb(observation, pacmanPos, ghostPos, jailPos)
            oldBelief = self.beliefs[ghostPos]
            self.beliefs[ghostPos] = oldBelief * obs


        self.beliefs.normalize()

    def elapseTime(self, gameState):
        """
        Predict beliefs in response to a time step passing from the current
        state.

        The transition model is not entirely stationary: it may depend on
        Pacman's current position. However, this is not a problem, as Pacman's
        current position is known.
        """

        # For every possible ghost position, update new beliefs based upon the game moving
        # one time step. We get this new information via getPositionDistribution
        newDist = DiscreteDistribution()
        for pos in self.allPositions:
            newPosDist = self.getPositionDistribution(gameState, pos)
            oldProb = self.beliefs[pos]

            # Iterating over all the possible moves the ghost could've taken, (up, down, left, etc.)
            for newPos in newPosDist:
                newBelief = oldProb * newPosDist[newPos]
                newDist[newPos] += newBelief

        # We can't do inline replacement because when we get to the next position in all positions
        # then we'll be basing our calculations for the old probability based on what we just added
        # to that probability
        self.beliefs = newDist

    def getBeliefDistribution(self):
        return self.beliefs


class ParticleFilter(InferenceModule):
    """
    A particle filter for approximately tracking a single ghost.
    """
    def __init__(self, ghostAgent, numParticles=300):
        InferenceModule.__init__(self, ghostAgent);
        self.setNumParticles(numParticles)

    def setNumParticles(self, numParticles):
        self.numParticles = numParticles

    def initializeUniformly(self, gameState):
        """
        Initialize a list of particles. Use self.numParticles for the number of
        particles. Use self.legalPositions for the legal board positions where
        a particle could be located. Particles should be evenly (not randomly)
        distributed across positions in order to ensure a uniform prior. Use
        self.particles for the list of particles.
        """
        self.particles = []

        # Loop over all numParticles, adding positions until there are no more particles to add
        #
        # NOTE: This will tend to give the numParticles % #pos's surplus to the
        # positions at the beginning of the legalPositions
        particlesLeft = self.numParticles
        while particlesLeft > 0:

            for pos in self.legalPositions:
                self.particles.append(pos)
                particlesLeft -= 1


    def observeUpdate(self, observation, gameState):
        """
        Update beliefs based on the distance observation and Pacman's position.

        The observation is the noisy Manhattan distance to the ghost you are
        tracking.

        There is one special case that a correct implementation must handle.
        When all particles receive zero weight, the list of particles should
        be reinitialized by calling initializeUniformly. The total method of
        the DiscreteDistribution may be useful.
        """

        pacmanPos = gameState.getPacmanPosition()
        jailPos = self.getJailPosition()
        zeroWeights = True
        dist = DiscreteDistribution()

        # Looping over all the particles and constructing a new weight distribution
        for particle in self.particles:
            obsProb = self.getObservationProb(observation, pacmanPos, particle, jailPos)
            dist[particle] += obsProb

            # If we calculated a non-zero belief, then don't re-initialize
            if (zeroWeights == True and dist[particle] != 0):
                zeroWeights = False

        # If all the weights are zero, re-initialize
        if zeroWeights:
            self.initializeUniformly(gameState)
        # Otherwise, proceed as normal and resample
        # NOTE: I've noticed that I can pass the test cases without normalizing, but
        # my inclination is to normalize here.
        else:
            dist.normalize()
            self.beliefs = dist
            for particle in self.particles:
                indexOfParticle = self.particles.index(particle)
                self.particles[indexOfParticle] = dist.sample()


    def elapseTime(self, gameState):
        """
        Sample each particle's next state based on its current state and the
        gameState.
        """

        newParticleList = []

        # For every particle, get a new possible position distribution
        # Do a re-sample and then append it to my new list of particles
        for particle in self.particles:
            newObs = self.getPositionDistribution(gameState, particle)
            newSample = newObs.sample()
            newParticleList.append(newSample)

        self.particles = newParticleList


    def getBeliefDistribution(self):
        """
        Return the agent's current belief state, a distribution over ghost
        locations conditioned on all evidence and time passage. This method
        essentially converts a list of particles into a belief distribution.
        """

        # I knew that this method would be used a lot so I did some research
        # to figure out a pythonic way to count elements of a list quickly and put
        # them into a dictionary. This line will output something like:
        # {(5, 4): 625, (3, 4): 625, (1, 4): 625, (7, 4): 625, (9, 4): 625, (5, 2): 625, (2, 4): 625, (8, 4): 625}
        # This should operate in O(n) (+ constant for count) time and without sorting the list
        # I have a decent understanding of the list comprehension as I'm in CS357 (Haskell) right now, but I
        # did see it on stack overflow
        # https://stackoverflow.com/questions/2870466/python-histogram-one-liner
        dist = DiscreteDistribution()
        countDict = {x: self.particles.count(x) for x in set(self.particles)}

        for key, value in countDict.items():
            dist[key] = value / float(self.numParticles)

        return dist


class JointParticleFilter(ParticleFilter):
    """
    JointParticleFilter tracks a joint distribution over tuples of all ghost
    positions.
    """
    def __init__(self, numParticles=600):
        self.setNumParticles(numParticles)

    def initialize(self, gameState, legalPositions):
        """
        Store information about the game, then initialize particles.
        """
        self.numGhosts = gameState.getNumAgents() - 1
        self.ghostAgents = []
        self.legalPositions = legalPositions
        self.initializeUniformly(gameState)

    def initializeUniformly(self, gameState):
        """
        Initialize particles to be consistent with a uniform prior. Particles
        should be evenly distributed across positions in order to ensure a
        uniform prior.
        """
        self.particles = []

        # Line 548 in itertools.py corresponds to "repeat" which is how many elements in the
        # tuple of combinations. This corresponds to how many ghosts are still alive.
        # We will want to keep the same elements as alive ghosts.
        possibleGhostPositions = list(itertools.product(self.legalPositions, repeat = self.numGhosts))
        random.shuffle(possibleGhostPositions)
        particlesLeft = self.numParticles

        # Instead of looping over a list of legal positions for the single ghost,
        # we will use the possibleGhostPositions, (Think of 4 ghosts in 4 coords)
        while particlesLeft > 0:

            for posTuple in possibleGhostPositions:
                self.particles.append(posTuple)
                particlesLeft -= 1

                # Make sure that while we're iterating over position if we run out of
                # particles that we stop. This caused some bugs in elapseTime
                if particlesLeft == 0:
                    break


    def addGhostAgent(self, agent):
        """
        Each ghost agent is registered separately and stored (in case they are
        different).
        """
        self.ghostAgents.append(agent)

    def getJailPosition(self, i):
        return (2 * i + 1, 1);

    def observe(self, gameState):
        """
        Resample the set of particles using the likelihood of the noisy
        observations.
        """
        observation = gameState.getNoisyGhostDistances()
        self.observeUpdate(observation, gameState)

    def observeUpdate(self, observation, gameState):
        """
        Update beliefs based on the distance observation and Pacman's position.

        The observation is the noisy Manhattan distances to all ghosts you
        are tracking.

        There is one special case that a correct implementation must handle.
        When all particles receive zero weight, the list of particles should
        be reinitialized by calling initializeUniformly. The total method of
        the DiscreteDistribution may be useful.
        """

        pacmanPos = gameState.getPacmanPosition()
        zeroWeights = True
        dist = DiscreteDistribution()

        # Looping over all the particles and constructing a new weight distribution
        for particle in self.particles:

            probProduct = 1 # Multiplication identity

            for i in range(self.numGhosts):
                obsProb = self.getObservationProb(observation[i], pacmanPos, particle[i], self.getJailPosition(i))
                probProduct = obsProb * probProduct

            dist[particle] += probProduct

            # If we calculated a non-zero belief, then don't re-initialize
            if (zeroWeights == True and dist[particle] != 0):
                zeroWeights = False

        # If all the weights are zero, re-initialize
        if zeroWeights:
            self.initializeUniformly(gameState)
        # Otherwise, proceed as normal, normalize and resample
        else:
            #dist.normalize()
            numParticles = len(self.particles)
            for i in range(numParticles):
                self.particles[i] = dist.sample()


    def elapseTime(self, gameState):
        """
        Sample each particle's next state based on its current state and the
        gameState.
        """
        newParticles = []
        for oldParticle in self.particles:
            newParticle = list(oldParticle)  # A list of ghost positions

            # now loop through and update each entry in newParticle...
            "*** YOUR CODE HERE ***"

            for i in range(self.numGhosts):
                newObs = self.getPositionDistribution(gameState, oldParticle, i, self.ghostAgents[i])
                newParticle[i] = newObs.sample()

            """*** END YOUR CODE HERE ***"""
            newParticles.append(tuple(newParticle))
        self.particles = newParticles


# One JointInference module is shared globally across instances of MarginalInference
jointInference = JointParticleFilter()


class MarginalInference(InferenceModule):
    """
    A wrapper around the JointInference module that returns marginal beliefs
    about ghosts.
    """
    def initializeUniformly(self, gameState):
        """
        Set the belief state to an initial, prior value.
        """
        if self.index == 1:
            jointInference.initialize(gameState, self.legalPositions)
        jointInference.addGhostAgent(self.ghostAgent)

    def observe(self, gameState):
        """
        Update beliefs based on the given distance observation and gameState.
        """
        if self.index == 1:
            jointInference.observe(gameState)

    def elapseTime(self, gameState):
        """
        Predict beliefs for a time step elapsing from a gameState.
        """
        if self.index == 1:
            jointInference.elapseTime(gameState)

    def getBeliefDistribution(self):
        """
        Return the marginal belief over a particular ghost by summing out the
        others.
        """
        jointDistribution = jointInference.getBeliefDistribution()
        dist = DiscreteDistribution()
        for t, prob in jointDistribution.items():
            dist[t[self.index - 1]] += prob
        return dist
