# Find the largest palindrome product from two 3-digit numbers
# The largest palindrome made from the product of two 2-digit numbers is
# 9009 = 91 * 99

nums = []

for x in range(100, 999):
  for y in range(100, 999):
    calcNum = x*y
    if str(calcNum)[::-1] == str(calcNum):
      nums.append(calcNum)


nums = sorted(nums)

print(nums[len(nums) - 1])
