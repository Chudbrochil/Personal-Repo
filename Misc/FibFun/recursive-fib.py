# Recursive fib, fib(0)=1, fib(1)=1, fib(2)=2

fibs = {0:1, 1:1}

def fib(num):
    if num in fibs:
        return(fibs[num])
    else:
        fibs[num] = fib(num - 1) + fib(num - 2)
        return fibs[num]


print(fib(200))
