# Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
# CS362 - Data Structures & Algorithms II
# Lab 2 - Food Linear Programming

import pulp

'''
Random Notes

Vitamin A and C are typically measured in IU's.
Conversion for vitamin A is 1 UI is 0.3mcg retinol or 0.6 mcg beta-carotene.
Conversion for vitamin C is 1 UI is 50 mcg L-ascorbic acid.

Food units are a bit weird to think about. You can become very granular and it is dependent
on the source of your pricing and nutrition data.

I've decided to normalize everything I can to 100 grams. Some foods don't tend towards 
grams however (eggs).

Possible future enhancements for a program like this:
Plug into one of the many nutrition sites RESTful API and pull down the nutrition and price
data live to be able to get the most ideal foods possible.
'''

# Food inner class
class Food(object):

    # Conversions for vitamin A is: 1 IU = 0.3mcg retinol or 0.6mcg beta-carotene. We will use 0.5 mcg per 1 IU
    iuToMcg = 0.5

    # If a food is discrete (think eggs) then I will be dividing price by a dozen. Otherwise all other foods
    # will be normalized to 100 grams.
    lbsTo100Grams = 100.0 / 453.6

    def __init__(self, shortLabel, longLabel, price, cals, satFat, sodium, vitC, vitA, protein, isDiscrete):



        self.shortLabel = shortLabel # Short name for food
        self.longLabel = longLabel # Full name for food
        self.price = price # Price of food (typically in $/100grams)
        self.cals = cals # Calories
        self.satFat = satFat # Saturated fat in grams
        self.sodium = sodium # Sodium in mg
        self.vitC = vitC # Vitamin C in mg
        self.vitA = vitA # Vitamin A in mcg
        self.protein = protein # Protein in grams
        self.isDiscrete = isDiscrete # If true, then we're dealing with 1 egg, 1 avocado. Otherwise it is 100grams

        self.fixMeasurements()


        print(self.price)

    def fixMeasurements(self):
        self.vitA *= self.iuToMcg
        if self.isDiscrete:
            self.price /= 12.0
            print("Test")
        else:
            self.price *= self.lbsTo100Grams

    def getFoodString(self):
        costDivisor = ""
        if self.isDiscrete: costDivisor = "%s" % self.shortLabel
        else: costDivisor = "100grams"
        return ("%s: Price:$%f per %s Calories:%s Sat. Fat:%s g Sodium:%s mg Vit C:%s mg Vit A:%s mcg Protein:%s g" %
                (self.longLabel, self.price, costDivisor, self.cals, self.satFat, self.sodium, self.vitC, self.vitA, self.protein))

# Food object data entry
foods = []
foods.append(Food("Broccoli", "Broccoli, raw", 1.831, 34.0, 0.0, 33.0, 89.2, 623, 2.8, False))
foods.append(Food("Egg", "Egg, whole, raw, fresh", 1.755, 71.5, 1.5, 70.0, 0.0, 244, 6.3, True))
foods.append(Food("Chicken", "Chicken, broilers or fryers, leg, meat and skin, raw", 1.402, 187, 3.4, 79.0, 2.5, 123, 18.2, False))
foods.append(Food("Rice", "Rice, white, long-grain, regular, cooked", 0.691, 365, 0.2, 5.0, 0.0, 0.0, 7.1, False))
foods.append(Food("Bananas", "Bananas, raw", 0.574, 89.0, 0.1, 1.0, 8.7, 64, 1.1, False))

# Now for the details of the linear program.
# My constraints are given via the prompt:
# 2000 cals, satFat <= 20g, sodium <= 2400mg, vitC >= 90mg, vitA >= 700mcg, protein >= 56g
foodLP = pulp.LpProblem("Food LP", pulp.LpMinimize)

pulpVars = []
totalPrice = 0.0
totalCals = 0
totalSatFat = 0
totalSodium = 0
totalVitC = 0
totalVitA = 0
totalProtein = 0

# Making a constraint variable for each food and putting them into a list
for food in foods:
    pulpVars.append(pulp.LpVariable(food.shortLabel, lowBound = 0, cat='Integer'))

# Getting the dot product of
#for food in foods:
for i in range(len(foods)):
    currentFood = foods[i]
    foodLPVar = pulpVars[i]
    totalPrice += (currentFood.price * foodLPVar)
    totalCals += (currentFood.cals * foodLPVar)
    totalSatFat += (currentFood.satFat * foodLPVar)
    totalSodium += (currentFood.sodium * foodLPVar)
    totalVitC += (currentFood.vitC * foodLPVar)
    totalVitA += (currentFood.vitA * foodLPVar)
    totalProtein += (currentFood.protein * foodLPVar)


foodLP += totalPrice, "Total Cost for a Day"
foodLP += totalCals >= 2000, "Calories"
foodLP += totalSatFat <= 20, "Saturated Fat"
foodLP += totalSodium <= 2400, "Sodium"
foodLP += totalVitC >= 90, "Vitamin C"
foodLP += totalVitA >= 700, "Vitamin A"
foodLP += totalProtein >= 56, "Protein"

foodLP.solve()

print(foodLP)
print("Your optimized daily diet will consist of:")
for food in foodLP.variables():
    print("%s %s" % (food.varValue, food.name))
print "Total cost of diet: ${:.2f}".format(pulp.value(foodLP.objective))



print("\nAll Foods Breakdown:")
for food in foods:
    print(food.getFoodString())

