def getNameScore(name):
    sum = 0
    for char in name:
        sum += (ord(char) - 64)
    return sum

# MAIN
f = open('p022_names.txt', 'r')

rawNames = []
realNames = []

for file in f:
    rawNames = file.split(",")

for name in sorted(rawNames):
    realNames.append(name.strip("\""))

i = 0
bigSum = 0
for name in realNames:
    i = i + 1
    nameSum = getNameScore(name)
    bigSum += i * nameSum
    
print(bigSum)
