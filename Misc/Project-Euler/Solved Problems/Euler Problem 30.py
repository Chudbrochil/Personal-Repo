"""
Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

1634 = 14 + 64 + 34 + 44
8208 = 84 + 24 + 04 + 84
9474 = 94 + 44 + 74 + 44
As 1 = 14 is not a sum it is not included.

The sum of these numbers is 1634 + 8208 + 9474 = 19316.

Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
"""

import math

nums = []

def pow5(n):
  return n ** 5

for x in xrange(1, 999999):#99999):
  sum = 0
  for digit in str(x):
    sum += pow5((int)(digit))

  if sum == x:
    nums.append(x)

finalSum = 0
for num in nums:
  if num != 1:
    finalSum += num

print(nums)
print(finalSum)
