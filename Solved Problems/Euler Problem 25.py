from math import sqrt
import decimal

i1 = 1
i2 = 1

digits = 3

# My implementation
#def fib(n):
#    if n == 0:
#        return 0
#    elif n == 1:
#        return 1
#    else:
#        return fib(n-1)+fib(n-2)

s5 = decimal.Decimal(5).sqrt()
# Direct implementation
def fib(n):
    return int(((1+s5)**n-(1-s5)**n)/(2**n*s5))
        

fibNumLen = 0
digsReq = 1000

i = 0
while fibNumLen < (digsReq + 1):
    i += 1
    fibNumLen = len(str(fib(i)))
    if fibNumLen == 1000:
        print(i)
        break
    
    
    
