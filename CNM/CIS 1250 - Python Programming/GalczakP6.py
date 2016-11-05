#GalczakP6
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: Library usage

#Library Imports
import P6library
#Loopback & Header
P6library.header()
again = 'y'
while again == 'y':
#User input for point1 and point2
    input1 = raw_input('Please enter your first point (Either as individual points or 3 points together): ')
    p1 = P6library.pointcalc(input1)
    input2 = raw_input('Please enter your second point (Either as individual points or 3 points together): ')
    p2 = P6library.pointcalc(input2)
    P6library.formulaCalc(p1, p2)
#Outputting distance to the user
    print 'The distance between %s and %s is %s' % (p1, p2, P6library.formulaCalc(p1, p2))
    again = raw_input('Would you like to calculate another distance (y/n) ?')
#Loopback end
print 'Thank you for using the 3-Dimensional calculator!'
