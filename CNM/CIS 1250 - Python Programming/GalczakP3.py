#GalczakP3
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: Provide user capability to find fruit in a string
from time import sleep
#Fruit database
fruits = ['orange', 'pear', 'grape', 'apricot', 'strawberry', 'tangerine', 'kiwi']
print fruits
#User input
userfruit = raw_input("Please enter a sentence with any of these fruit in it: ")
#Splitting users input
userfruit_split = userfruit.split()

#Users' fruit
fruit_intersect = list(set(fruits)&set(userfruit_split))
#How many fruit
fruit_count = len(fruit_intersect)
#User output
sleep(1.0)
print "I found " + str(fruit_count) + ' fruit(s) in your sentence!'
print "Your fruits are: " + str(fruit_intersect)
#Replacing the first fruit with Brussel Sprout
fruit_index = fruit_intersect[0]
userfruit_index = userfruit_split.index(fruit_index)
userfruit_split[userfruit_index] = "brussel sprouts"
#Printing the re-made sentence
space = ' '
sleep(2.0)
print "Your sentence with some healthy brussel sprouts added: "
print space.join(userfruit_split)
input("Press any key to continue")
