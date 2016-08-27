# 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

# What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

complete = False
i = 0
howMany = 20

while complete == False:
  i += howMany
  modCount = 0
  for x in range(howMany, 1, -1):
    if (i % x) == 0:
      modCount += 1
    elif (i % x) != 0:
      break
    if modCount == (howMany - 1):
      print(i)
      complete = True
    
    
