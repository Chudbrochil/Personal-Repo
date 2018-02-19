# multiAgents.py
# --------------
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


from util import manhattanDistance
from game import Directions
from game import Actions
import search
import random, util

from game import Agent

class ReflexAgent(Agent):
    """
      A reflex agent chooses an action at each choice point by examining
      its alternatives via a state evaluation function.

      The code below is provided as a guide.  You are welcome to change
      it in any way you see fit, so long as you don't touch our method
      headers.
    """


    def getAction(self, gameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {North, South, West, East, Stop}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices) # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

        theScore = 0

        closestGhostDistance = float("inf")
        closestFoodDistance = float("inf")

        # Getting all ghosts
        for x in range (1, len(newGhostStates) + 1):
            aGhost = successorGameState.getGhostPosition(x)
            distanceToGhost = util.manhattanDistance(newPos, aGhost)
            if distanceToGhost < closestGhostDistance:
                closestGhostDistance = distanceToGhost

        # Getting all food
        for food in newFood.asList():
            distanceToFood = util.manhattanDistance(newPos, food)
            if distanceToFood < closestFoodDistance:
                closestFoodDistance = distanceToFood

        # Calculations for food
        if currentGameState.hasFood(newPos[0], newPos[1]):
            theScore += 75
        else:
            theScore += (50 - closestFoodDistance)

        # Analysis of score for distance to ghost will be simple at first
        if closestGhostDistance == 0:
            theScore = 0
        elif closestGhostDistance == 1:
            theScore += 0 # Do nothing...
        else:
            theScore += 100

        if currentGameState.getPacmanPosition() == newPos:
            theScore = 1

        return theScore

def scoreEvaluationFunction(currentGameState):
    """
      This default evaluation function just returns the score of the state.
      The score is the same one displayed in the Pacman GUI.

      This evaluation function is meant for use with adversarial search agents
      (not reflex agents).
    """
    return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
    """
      This class provides some common elements to all of your
      multi-agent searchers.  Any methods defined here will be available
      to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

      You *do not* need to make any changes here, but you can if you want to
      add functionality to all your adversarial search agents.  Please do not
      remove anything, however.

      Note: this is an abstract class: one that should not be instantiated.  It's
      only partially specified, and designed to be extended.  Agent (game.py)
      is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
    """
      Your minimax agent (question 2)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action from the current gameState using self.depth
          and self.evaluationFunction.

          Here are some method calls that might be useful when implementing minimax.

          gameState.getLegalActions(agentIndex):
            Returns a list of legal actions for an agent
            agentIndex=0 means Pacman, ghosts are >= 1

          gameState.generateSuccessor(agentIndex, action):
            Returns the successor game state after an agent takes an action

          gameState.getNumAgents():
            Returns the total number of agents in the game
        """

        pacmanMove = Directions.STOP
        evaluation = float("-inf")
        agentIndex = 0

        # This is basically pacman's first move being looped over and starting minimax
        # This is useful for capturing the actual directional move gotten from the analysis
        # of states. Eliminates the need to keep track of actual actions inside min/max.
        for action in gameState.getLegalActions(agentIndex):
            successor = gameState.generateSuccessor(agentIndex, action)
            oldEval = evaluation
            evaluation = max(evaluation, self.minimax(successor, self.depth, agentIndex + 1))
            if evaluation > oldEval:
                pacmanMove = action

        return pacmanMove


    def minimax(self, gameState, depth, agentIndex):

        # If we have iterated the agent index to more than the amount of players, we now
        # want to switch to player0 (max) and reduce depth by 1
        if agentIndex >= gameState.getNumAgents():
            agentIndex = 0
            depth -= 1

        # Generating actions for current game state
        actions = gameState.getLegalActions(agentIndex)

        # If we reached our depth or lost or won or no legal actions are left(terminal), we are done
        if depth == 0 or gameState.isLose() or gameState.isWin() or len(actions) < 0:
            return self.evaluationFunction(gameState)

        # First index is a player(max) move, other indices are ghost(min) moves
        if agentIndex == 0:
            return self.maxValue(gameState, depth, agentIndex, actions)
        else:
            return self.minValue(gameState, depth, agentIndex, actions)

    def maxValue(self, gameState, depth, agentIndex, actions):
        v = float("-inf")
        for action in actions:

            # Getting the value of the following successor
            successor = gameState.generateSuccessor(agentIndex, action)
            stateValue = self.minimax(successor, depth, agentIndex + 1)

            # If it's higher than our current value, store the value and action
            if stateValue > v:
                v = stateValue

        return v


    def minValue(self, gameState, depth, agentIndex, actions):
        v = float("inf")
        for action in actions:
            successor = gameState.generateSuccessor(agentIndex, action)
            stateValue = self.minimax(successor, depth, agentIndex + 1)
            if stateValue < v:
                v = stateValue

        return v


class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        pacmanMove = Directions.STOP
        evaluation = float("-inf")
        agentIndex = 0
        alpha = float("-inf")
        beta = float("inf")

        # This is basically pacman's first move being looped over and starting minimax
        # This is useful for capturing the actual directional move gotten from the analysis
        # of states. Eliminates the need to keep track of actual actions inside min/max.
        for action in gameState.getLegalActions(agentIndex):
            successor = gameState.generateSuccessor(agentIndex, action)
            oldEval = evaluation
            evaluation = max(evaluation, self.minimax(successor, self.depth, agentIndex + 1, alpha, beta))
            if evaluation > oldEval:
                pacmanMove = action

        return pacmanMove


    def minimax(self, gameState, depth, agentIndex, alpha, beta):

        # If we have iterated the agent index to more than the amount of players, we now
        # want to switch to player0 (max) and reduce depth by 1
        if agentIndex >= gameState.getNumAgents():
            agentIndex = 0
            depth -= 1

        # Generating actions for current game state
        actions = gameState.getLegalActions(agentIndex)

        # If we reached our depth or lost or won or no legal actions are left(terminal), we are done
        if depth == 0 or gameState.isLose() or gameState.isWin() or len(actions) < 0:
            return self.evaluationFunction(gameState)

        # First index is a player(max) move, other indices are ghost(min) moves
        if agentIndex == 0:
            return self.maxValue(gameState, depth, agentIndex, actions, alpha, beta)
        else:
            return self.minValue(gameState, depth, agentIndex, actions, alpha, beta)

    def maxValue(self, gameState, depth, agentIndex, actions, alpha, beta):
        v = float("-inf")
        for action in actions:

            # Getting the value of the following successor
            successor = gameState.generateSuccessor(agentIndex, action)
            stateValue = self.minimax(successor, depth, agentIndex + 1, alpha, beta)

            # If it's higher than our current value, store the value and action
            if stateValue > v:
                v = stateValue

            # Early terminating this actions loop, this is how we are pruning results
            if v > beta:
                return v

            alpha = max(alpha, v)

        return v


    def minValue(self, gameState, depth, agentIndex, actions, alpha, beta):
        v = float("inf")
        for action in actions:
            successor = gameState.generateSuccessor(agentIndex, action)
            stateValue = self.minimax(successor, depth, agentIndex + 1, alpha, beta)
            if stateValue < v:
                v = stateValue
            if v < alpha:
                return v
            beta = min(beta, v)

        return v

class ExpectimaxAgent(MultiAgentSearchAgent):
    """
      Your expectimax agent (question 4)
    """

    def getAction(self, gameState):
        """
          Returns the expectimax action using self.depth and self.evaluationFunction

          All ghosts should be modeled as choosing uniformly at random from their
          legal moves.
        """

        pacmanMove = Directions.STOP
        evaluation = float("-inf")
        agentIndex = 0

        # This is basically pacman's first move being looped over and starting minimax
        # This is useful for capturing the actual directional move gotten from the analysis
        # of states. Eliminates the need to keep track of actual actions inside min/max.
        for action in gameState.getLegalActions(agentIndex):
            successor = gameState.generateSuccessor(agentIndex, action)
            oldEval = evaluation
            evaluation = max(evaluation, self.expectimax(successor, self.depth, agentIndex + 1))
            if evaluation > oldEval:
                pacmanMove = action

        return pacmanMove

    def expectimax(self, gameState, depth, agentIndex):

        # If we have iterated the agent index to more than the amount of players, we now
        # want to switch to player0 (max) and reduce depth by 1
        if agentIndex >= gameState.getNumAgents():
            agentIndex = 0
            depth -= 1

        # Generating actions for current game state
        actions = gameState.getLegalActions(agentIndex)

        # If we reached our depth or lost or won or no legal actions are left(terminal), we are done
        if depth == 0 or gameState.isLose() or gameState.isWin() or len(actions) < 0:
            return self.evaluationFunction(gameState)

        # First index is a player(max) move, other indices are ghost(min) moves
        if agentIndex == 0:
            return self.maxValue(gameState, depth, agentIndex, actions)
        else:
            return self.minValue(gameState, depth, agentIndex, actions)

    def maxValue(self, gameState, depth, agentIndex, actions):
        v = float("-inf")
        for action in actions:

            # Getting the value of the following successor
            successor = gameState.generateSuccessor(agentIndex, action)
            stateValue = self.expectimax(successor, depth, agentIndex + 1)

            # If it's higher than our current value, store the value and action
            if stateValue > v:
                v = stateValue

        return v


    def minValue(self, gameState, depth, agentIndex, actions):
        v = 0
        # This is the probability that a ghost will choose any given action
        probabilityOfEachAction = 1.0 / len(actions)

        # Adding p*(action cost) for each action to value
        for action in actions:
            successor = gameState.generateSuccessor(agentIndex, action)
            stateValue = self.expectimax(successor, depth, agentIndex + 1)
            v += probabilityOfEachAction * stateValue

        return v

def betterEvaluationFunction(currentGameState):
    """
      Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
      evaluation function (question 5).

      DESCRIPTION: <write something here so we know what you did>
    """

    # Notes

    # Could try to use mazeDistance, would that make this better?

    # This statement proves all of our test cases are with 1 ghost
    #if len(ghostStates) == 0 or len(ghostStates) > 1:
    #    print("Degenerate case")



    # pacmanPos = currentGameState.getPacmanPosition()
    # foodLocations = currentGameState.getFood()
    # ghostStates = currentGameState.getGhostStates()
    # capsuleLocations = currentGameState.getCapsules()
    # scaredTimes = [ghostState.scaredTimer for ghostState in ghostStates]
    #
    # theScore = 0
    #
    #
    # closestGhost = float("inf")
    # closestFood = float("inf")
    # closestCapsule = float("inf")
    #
    # # Getting all food
    # for food in foodLocations.asList():
    #     distanceToFood = util.manhattanDistance(pacmanPos, food)#, currentGameState)
    #     if distanceToFood < closestFood:
    #         closestFood = distanceToFood
    #
    # # Getting all ghosts
    # for x in range(1, len(ghostStates) + 1):
    #     aGhost = currentGameState.getGhostPosition(x)
    #     aGhost = (int(aGhost[0]), int(aGhost[1]))
    #     distanceToGhost = mazeDistance(pacmanPos, aGhost, currentGameState)
    #     if distanceToGhost < closestGhost:
    #         closestGhost = distanceToGhost
    #
    # # Getting all capsules
    # for capsule in capsuleLocations:
    #     distanceToCapsule = mazeDistance(pacmanPos, capsule, currentGameState)
    #     if distanceToCapsule < closestCapsule:
    #         closestCapsule = distanceToCapsule
    #
    # # Checking if our ghost is scared, > 1 to be safe
    # isScared = False
    # if scaredTimes[0] > 1:
    #     isScared = True
    #
    #
    # # Calculations for food
    # if closestFood == 1:
    #    theScore += 75 + random.randrange(5)
    # else:
    #     theScore += (50 - closestFood)
    #
    # if closestCapsule == 1:
    #     theScore += 100
    # elif closestCapsule == 2:
    #     theScore += 50
    #
    # # Analysis of score for distance to ghost will be simple at first
    # if isScared == False:
    #     #if closestGhost == 0:
    #     #    theScore = 0
    #     #elif closestGhost == 1:
    #     #    theScore += 0  # Do nothing...
    #     if closestGhost <= 1:
    #         theScore = 0
    #     elif closestGhost > 1:
    #         theScore += 100
    # else:
    #     theScore += 100
    #     if closestGhost <= 1:
    #         theScore += 1000
    #     elif closestGhost == 2:
    #         theScore += 20
    #
    #
    #
    # print("Food:%s isScared:%s scaredTimes:%s Ghost:%s Capsule:%s Score:%s" % (closestFood, isScared, scaredTimes[0], closestGhost, closestCapsule, theScore))
    # #print(currentGameState.hasFood(pacmanPos[0], pacmanPos[1]))
    #
    # return theScore + scoreEvaluationFunction(currentGameState)




    # Copied from the reflex agent evaluation function
    pacmanPos = currentGameState.getPacmanPosition()
    foodLocations = currentGameState.getFood()
    ghostStates = currentGameState.getGhostStates()
    #capsuleLocations = currentGameState.getCapsules()
    #scaredTimes = [ghostState.scaredTimer for ghostState in ghostStates]

    theScore = 0

    closestGhost = 10000
    closestFood = 10000
    manhattanFoodList = []
    #closestCapsule = float("inf")

    # Getting all ghosts
    for x in range(1, len(ghostStates) + 1):
        aGhost = currentGameState.getGhostPosition(x)
        aGhost = (int(aGhost[0]), int(aGhost[1]))
        distanceToGhost = mazeDistance(pacmanPos, aGhost, currentGameState)
        if distanceToGhost < closestGhost:
            closestGhost = distanceToGhost

    # This is an attempt to tighten up the distances on the food
    # for food in foodLocations.asList():
    #     manhattanDistanceToFood = util.manhattanDistance(pacmanPos, food)
    #     if len(manhattanFoodList) < 5:
    #         manhattanFoodList.append(food)
    #
    #     elif manhattanDistanceToFood < min(manhattanFoodList):
    #         manhattanFoodList.append(food)
    #         manhattanFoodList.remove(max(manhattanFoodList))
    #
    # for food in manhattanFoodList:
    #     distanceToFood = mazeDistance(pacmanPos, food, currentGameState)
    #     if distanceToFood < closestFood:
    #         closestFood = distanceToFood

    for food in foodLocations.asList():
        distanceToFood = util.manhattanDistance(pacmanPos, food)
        if distanceToFood < closestFood:
            closestFood = distanceToFood


    # Getting more accurate food distance via mazeDistance
    # Can't do this mazeDistance with all food because there is a ton of food

    # Getting all capsules
    # for capsule in capsuleLocations:
    #     distanceToCapsule = mazeDistance(pacmanPos, capsule, currentGameState)
    #     if distanceToCapsule < closestCapsule:
    #         closestCapsule = distanceToCapsule

    # Calculations for food
    if currentGameState.hasFood(pacmanPos[0], pacmanPos[1]):
        theScore += 75
    else:
        theScore += (60 - closestFood)

    # if closestCapsule == 1:
    #     theScore += 20
    # elif closestCapsule == 2:
    #     theScore += 10


    # Analysis of score for distance to ghost will be simple at first
    # if closestGhost == 1:
    #     theScore = 0
    if closestGhost == 0:
        theScore = 0
    elif closestGhost > 1:
        theScore += 100

    #print("theScore: %s, scoreEval: %s", (theScore, scoreEvaluationFunction(currentGameState)))

    return theScore + scoreEvaluationFunction(currentGameState)





# Bringing in mazeDistance, prompt specifically says we can use tools from last project
# I have no idea if our TA will have the other projects and imports so I'm copying all
# of what we need for mazeDistance



def mazeDistance(point1, point2, gameState):
    """
    Returns the maze distance between any two points, using the search functions
    you have already built. The gameState can be any game state -- Pacman's
    position in that state is ignored.

    Example usage: mazeDistance( (2,4), (5,6), gameState)

    This might be a useful helper function for your ApproximateSearchAgent.
    """
    x1, y1 = point1
    x2, y2 = point2
    walls = gameState.getWalls()
    assert not walls[x1][y1], 'point1 is a wall: ' + str(point1)
    assert not walls[x2][y2], 'point2 is a wall: ' + str(point2)
    prob = PositionSearchProblem(gameState, start=point1, goal=point2, warn=False, visualize=False)
    return len(search.bfs(prob))



class PositionSearchProblem(search.SearchProblem):
    """
    A search problem defines the state space, start state, goal test, successor
    function and cost function.  This search problem can be used to find paths
    to a particular point on the pacman board.

    The state space consists of (x,y) positions in a pacman game.

    Note: this search problem is fully specified; you should NOT change it.
    """

    def __init__(self, gameState, costFn = lambda x: 1, goal=(1,1), start=None, warn=True, visualize=True):
        """
        Stores the start and goal.

        gameState: A GameState object (pacman.py)
        costFn: A function from a search state (tuple) to a non-negative number
        goal: A position in the gameState
        """
        self.walls = gameState.getWalls()
        self.startState = gameState.getPacmanPosition()
        if start != None: self.startState = start
        self.goal = goal
        self.costFn = costFn
        self.visualize = visualize
        if warn and (gameState.getNumFood() != 1 or not gameState.hasFood(*goal)):
            print 'Warning: this does not look like a regular search maze'

        # For display purposes
        self._visited, self._visitedlist, self._expanded = {}, [], 0 # DO NOT CHANGE

    def getStartState(self):
        return self.startState

    def isGoalState(self, state):
        isGoal = state == self.goal

        # For display purposes only
        if isGoal and self.visualize:
            self._visitedlist.append(state)
            import __main__
            if '_display' in dir(__main__):
                if 'drawExpandedCells' in dir(__main__._display): #@UndefinedVariable
                    __main__._display.drawExpandedCells(self._visitedlist) #@UndefinedVariable

        return isGoal

    def getSuccessors(self, state):
        """
        Returns successor states, the actions they require, and a cost of 1.

         As noted in search.py:
             For a given state, this should return a list of triples,
         (successor, action, stepCost), where 'successor' is a
         successor to the current state, 'action' is the action
         required to get there, and 'stepCost' is the incremental
         cost of expanding to that successor
        """

        successors = []
        for action in [Directions.NORTH, Directions.SOUTH, Directions.EAST, Directions.WEST]:
            x,y = state
            dx, dy = Actions.directionToVector(action)
            nextx, nexty = int(x + dx), int(y + dy)
            if not self.walls[nextx][nexty]:
                nextState = (nextx, nexty)
                cost = self.costFn(nextState)
                successors.append( ( nextState, action, cost) )

        # Bookkeeping for display purposes
        self._expanded += 1 # DO NOT CHANGE
        if state not in self._visited:
            self._visited[state] = True
            self._visitedlist.append(state)

        return successors

    def getCostOfActions(self, actions):
        """
        Returns the cost of a particular sequence of actions. If those actions
        include an illegal move, return 999999.
        """
        if actions == None: return 999999
        x,y= self.getStartState()
        cost = 0
        for action in actions:
            # Check figure out the next state and see whether its' legal
            dx, dy = Actions.directionToVector(action)
            x, y = int(x + dx), int(y + dy)
            if self.walls[x][y]: return 999999
            cost += self.costFn((x,y))
        return cost



# Abbreviation
better = betterEvaluationFunction

