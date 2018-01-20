# Anthony Galczak 01-09-18
# Euler problem 19

##You are given the following information, but you may prefer to do some research for yourself.
##
##    1 Jan 1900 was a Monday.
##    Thirty days has September,
##    April, June and November.
##    All the rest have thirty-one,
##    Saving February alone,
##    Which has twenty-eight, rain or shine.
##    And on leap years, twenty-nine.
##    A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
##
##How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

daysInMonths = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ]
currentDay = 0
sundaysOnFirst = 0
for year in range(1900, 2001):
    # Checking for leap year
    if year % 4 == 0 and (year % 100 != 0 or year % 400 == 0):
        daysInMonths[1] = 29
    else:
        daysInMonths[1] = 28
    
    for days in daysInMonths:
        for day in range(1, days + 1):
            currentDay += 1

            # If we aren't on first year(not included), it's the first of the month
            # and it's a sunday, we have a Sunday on the 1st.
            if year != 1900 and day == 1 and currentDay % 7 == 0:
                sundaysOnFirst += 1

print(sundaysOnFirst)

