sum = 0

numbers = []

x = 0
y = 1
insertable = 0
i = 0

numbers.append(x)
numbers.append(y)

while insertable < 4000000:
    insertable = numbers[0 + i] + numbers[1 + i]
    numbers.append(insertable)
    i = i + 1

sum = 0

for item in numbers:
    if item % 2 == 0:
       sum += item


print(sum)
