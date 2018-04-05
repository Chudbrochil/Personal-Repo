# Following the tutorial found at http://benalexkeen.com/linear-programming-with-python-and-pulp-part-2/
import pulp

myLP = pulp.LpProblem("MyLP", pulp.LpMaximize)

# Define x, bound it from 0 and above
x = pulp.LpVariable('x', lowBound=0, cat='Continuous')

# Define y, bound it from 2 and above
y = pulp.LpVariable('y', lowBound=2, cat='Continuous')

# Objective Function Z defined by Z = 4x + 3y
myLP += 4*x + 3*y, "Z"

# Adding a constraint of 2y <= 25 - x
myLP += 2 * y <= 25 - x

# Adding a constraint of 4y >= x - 8
myLP += 4 * y >= 2 * x - 8

#Adding a constraint of y <= 2 * x - 5
myLP += y <= 2 * x - 5


print(myLP)

myLP.solve()

# Printing solved information
print(pulp.LpStatus[myLP.status])

for variable in myLP.variables():
    print "{} = {}".format(variable.name, variable.varValue)

print pulp.value(myLP.objective)

