import sys
import os
import subprocess

# Declarations
executable_name = "race.out"
iterations = 1000

if len(sys.argv) <= 1:
    print("No arguments passed. Please give a compilation file.")
    sys.exit(1)

file_name = sys.argv[1]

# Compiling the given piece of code
subprocess.call(["gcc", "-pthread", os.getcwd() + "/" + file_name, "-o", executable_name])

how_much_off = 0
how_many_correct = 0

for x in range(iterations):
    # https://stackoverflow.com/questions/748028/how-to-get-output-of-exe-in-python-script
    output = str(subprocess.Popen(["./" + executable_name], stdout=subprocess.PIPE).communicate()[0])
    
    # Grabbing the number from the output of our race program.
    sum = int(output.split(" ?= ")[0].split(" ")[-1])

    if sum == 200:
        how_many_correct += 1
    else:
        how_much_off += abs(200 - sum)

print("Ran for %d iterations.\n%d correct. %f average amount off per iteration."
      % (iterations, how_many_correct, how_much_off / iterations))

subprocess.call(["rm", executable_name])
