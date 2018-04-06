import pulp

# Future enhancements:
# Plug into a RESTful API and pull down all of the data live and optimize it


# Random note: Granularity on food units is a weird topic to visit.
# I will have to decide what units to use (cup, ounce, pound...)

# Another random note:
# Vitamin A and C is typically measured in IU
# Conversion for vitamin A is 1 UI is 0.3mcg retinol or 0.6 mcg beta-carotene
# Conversion for vitamin C is 1 UI is 50 mcg L-ascorbic acid


foodLP = pulp.LpProblem("Food LP", pulp.minimize)

# Let's start with broccoli, eggs, chicken, rice, bananas


# Costs
# https://www.bls.gov/regions/mid-atlantic/data/AverageRetailFoodAndEnergyPrices_USandMidwest_Table.htm
# I took prices for Feb 2018

# Each food (except eggs) will be measured in 100 grams for nutrition, this is the conversion
# for getting a pound of food in grams.
lbsTo100Grams = 100.0 / 453.6

# Broccoli, per lb. is $1.831, gives $/100g
f1Price = 1.831 * lbsTo100Grams

# Eggs, per dozen (awkward) is $1.755, gives $/1 large egg
f2Price = 1.755 / 12

# Chicken (legs), per lb. is $1.402, gives $/100g
f3Price = 1.402 * lbsTo100Grams

# Rice, per lb. is $0.691, gives $/100g
f4Price = 0.691 * lbsTo100Grams

# Bananas, per lb. is $0.574, gives $/100g
f5Price = 0.574 * lbsTo100Grams

# Nutrition
# (label, cost, cals, sat fat(in grams), sodium(in mg), vit c(in mg), vit a(in mcg), protein(in grams))

# Conversions for vitamin A is: 1 IU = 0.3mcg retinol or 0.6mcg beta-carotene. We will use 0.5 mcg per 1 IU
iuToMcg = 0.5

# Broccoli nutrition - http://nutritiondata.self.com/facts/vegetables-and-vegetable-products/2356/2
broccoli = ("Broccoli, raw", f1Price, 34.0, 0.0, 33.0, 89.2, 623 * iuToMcg, 2.8)

# Eggs nutrition - http://nutritiondata.self.com/facts/dairy-and-egg-products/111/2
egg = ("Egg, whole, raw, fresh", f2Price, 71.5, 1.5, 70.0, 0.0, 244 * iuToMcg, 6.3)

# Chicken nutrition - http://nutritiondata.self.com/facts/poultry-products/714/2
chicken = ("Chicken leg, raw", f3Price, 187, 3.4, 79.0, 2.5, 123 * iuToMcg, 18.2)

# Rice nutrition - http://nutritiondata.self.com/facts/cereal-grains-and-pasta/5712/2
rice = ("Rice, white, long-grain", f4Price, 365, 0.2, 5.0, 0.0, 0.0, 7.1)

# Bananas nutrition - http://nutritiondata.self.com/facts/fruits-and-fruit-juices/1846/2
bananas = ("Bananas, raw", f5Price, 89.0, 0.1, 1.0, 8.7, 64 * iuToMcg, 1.1)

foods = [broccoli, egg, chicken, rice, bananas]

# TODO: Make a food object







