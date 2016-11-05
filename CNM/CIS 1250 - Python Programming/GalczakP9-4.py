#GalczakP9
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: Demonstrate how to define a class with properties and constructors

#Program9

from math import sqrt
#Declaring GeoPoint class
class GeoPoint(object):
    def __init__(self, x=0, y=0, z=0, description = 'TBD'):
        self.x = x
        self.y = y
        self.z = z
        self.description = description
        
    def SetPoint(self, coords):
        self.x = coords[0]
        self.y = coords[1]
        self.z = coords[2]

    def GetPoint(self):
        return self.x, self.y, self.z

    def Distance(self, toPoint):
        return sqrt(
            (self.x - toPoint.x)**2 +
            (self.y - toPoint.y)**2 +
            (self.z - toPoint.z)**2)
        
    def SetDescription(self, description):
        self.description = description

    def GetDescription(self):
        return self.description
    PointCoords = property(GetPoint, SetPoint)
    PointDescription = property(GetDescription, SetDescription)

#----MAIN----
location1 = GeoPoint(4, 9, 21, 'Building B12')
location2 = GeoPoint()
#Loopback
loopback = 'y'
while loopback == 'y':
#Exception Handling
    try:
#Define Point 2
        location2.PointCoords = 29,-13,6
        location2.PointDescription = 'Building A39'
#Ask user for their x, y, z
        userx = input('Please enter your current x-coordinate: ')
        usery = input('Please enter your current y-coordinate: ')
        userz = input('Please enter your current z-coordinate: ')
        userlocation = GeoPoint(userx, usery, userz, 'Users location')
#Calc distance from Point 1 to their point
        p1distance = location1.Distance(userlocation)
#Calc distance from Point 2 to their point
        p2distance = location2.Distance(userlocation)
#Display shortest distance
        if p2distance > p1distance:
            print 'You are closest to %s which is located at %s' % (location1.GetDescription(), location1.GetPoint())
        else:
            print 'You are closest to %s which is located at %s' % (location2.GetDescription(), location2.GetPoint())
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
