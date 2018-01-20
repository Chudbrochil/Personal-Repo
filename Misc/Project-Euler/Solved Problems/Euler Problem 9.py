# A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
# a2 + b2 = c2
# For example, 32 + 42 = 9 + 16 = 25 = 52.
# There exists exactly one Pythagorean triplet for which a + b + c = 1000.
# Find the product abc.

# n^3 to find triplets of nums that add to 1000, n is "small"
# Might find some optimization on limiting range of numbers drastically
for x in range(1, 1000):
    for y in range(x+1, 1000):
        for z in range(y+1, 1000):
            if x + y + z == 1000 and (x**2 + y**2) == z**2:
                print("x:%d y:%d z:%d product:%d" % (x, y, z, x*y*z))



