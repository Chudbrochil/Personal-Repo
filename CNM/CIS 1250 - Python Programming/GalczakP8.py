#GalczakP8
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: demonstrate how to define a class and use exception handling

from math import sqrt
#Declaring GeoPoint class
class GeoPoint(object):
    def __init__(self):
        self.__x = 0
        self.__y = 0
        self.__z = 0
        __description = ""
        
    def SetPoint(self, x, y, z):
        self.__x = x
        self.__y = y
        self.__z = z

    def GetPoint(self):
        return self.__x, self.__y, self.__z

    def Distance(self, x, y, z):
        P1 = (self.__x - x)**2
        P2 = (self.__y - y)**2
        P3 = (self.__z - z)**2
        return sqrt(P1+P2+P3)
        
    def SetDescription(self, description):
        self.__description = description

    def GetDescription(self):
        return self.__description

#----MAIN----
point1 = GeoPoint()
point2 = GeoPoint()
#Loopback
loopback = 'y'
while loopback == 'y':
#Exception Handling
    try:
#Define Point 1 pick some coord
        point1.SetPoint(4, 9, 21)
        point1.SetDescription('Building B12')
#Define Point 2 pick some coord
        point2.SetPoint(29, -13, 6)
        point2.SetDescription('Building A39')
#Ask user for their x, y, z
        userx = input('Please enter your current x-coordinate: ')
        usery = input('Please enter your current y-coordinate: ')
        userz = input('Please enter your current z-coordinate: ')
#Calc distance from Point 1 to their point
        p1distance = point1.Distance(userx, usery, userz)
#Calc distance from Point 2 to their point
        p2distance = point2.Distance(userx, usery, userz)
#Display shortest distance
        if p2distance > p1distance:
            print 'You are closest to %s which is located at %s' % (point1.GetDescription(), point1.GetPoint())
        else:
            print 'You are closest to %s which is located at %s' % (point2.GetDescription(), point2.GetPoint())
#Exception types
    except TypeError:
        print 'Wrong type of input!'
    except NameError:
        print 'That is not a number!'
    except Exception, e:
        print "Something went wrong: ", e
#Loopback end
    loopback = raw_input('Do another (y/n)? ')
print 'Thank you for using my program!'
