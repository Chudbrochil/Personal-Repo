##
##
##The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
##
##Find the sum of all the primes below two million.

import math

def isPrime(sieve, possiblePrime):
    for num in sieve:
        if possiblePrime % num == 0:
            return False
    return True

def main():
    primes = []
    sieve = []
    magicNum = 2000000

    sieveMax = int(math.ceil(math.sqrt(magicNum)))

    for possiblePrime in range(2, sieveMax):
        if isPrime(primes, possiblePrime) == True:
            primes.append(possiblePrime)
            
    sieve = primes[:]

    for possiblePrime in range(sieveMax, magicNum):
        if isPrime(sieve, possiblePrime) == True:
            primes.append(possiblePrime)

    print(sum(primes))

if __name__ == "__main__":
    main()
