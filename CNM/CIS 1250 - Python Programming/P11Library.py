#GalczakP10
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: Use a class with reading from a text file.

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

#Initialization
pointList = []
shortestPoint = []
loopback = 'y'
#Loopback
while loopback == 'y':
#Exception Handling for User Input
    try:
#Ask user for their x, y, z
        userx = xGUI.GetValue()
        usery = yGUI.GetValue()
        userz = zGUI.GetValue()
        userlocation = GeoPoint(userx, usery, userz, 'Users location')
#Exception types
    except TypeError:
        print 'Wrong type of input!'
    except NameError:
        print 'That is not a number!'
    except Exception, e:
        print "Something went wrong: ", e
#File Reference/Database of points
    fileRef = open('P10Points.txt','r')
#Iterating List of Points
    while True:
        filePoints = fileRef.readline()
        if not filePoints: break
        splitFile = filePoints.split(",")
#Assigning individual indices
        newX = int(splitFile[0])
        newY = int(splitFile[1])
        newZ = int(splitFile[2])
        newDescription = splitFile[3].replace("\n", "")
#Instantiating the point into GeoPoint 
        newPoint = GeoPoint(newX, newY, newZ, newDescription)
#Appending pointList with points
        pointList.append(newPoint)
#Calculating distances and finding shortest point distance
        shortestPoint = pointList[0]
        for point in pointList:
            if point.Distance(userlocation) < shortestPoint.Distance(userlocation):
                shortestPoint = point
#Printing out to user and closing the file
    print 'You are closest to %s which is located at %s' % (shortestPoint.PointDescription, shortestPoint.PointCoords)
    fileRef.close()
#Loopback end
    loopback = raw_input('Do another (y/n)? ')
print 'Thank you for using my program!'

import wx
#Submit Function
def submit(event):
    output.SetValue(shortestPoint.PointCoords, shortestPoint.PointDescription)
#Instantiating wx App
GUI = wx.App()
#Display Main Window
win = wx.Frame(None, title = 'Point Calculator', size = (500, 400))
#Display Filename Window and Label
filename = wx.TextCtrl(win, pos = (5, 30), size = (300, 25))
filenameLabel = wx.StaticText (win, pos = (5, 5), size = (300, 25), label = 'File Name')
#Display xyz Window and Label
xGUI = wx.TextCtrl(win, pos = (5, 140), size = (60, 25))
xLabel = wx.StaticText (win, pos = (5, 115), size = (60, 25), label = 'x-Coord')                        
yGUI = wx.TextCtrl(win, pos = (5, 200), size = (60, 25))
yLabel = wx.StaticText (win, pos = (5, 175), size = (60, 25), label = 'y-Coord')
zGUI = wx.TextCtrl(win, pos = (5, 260), size = (60, 25))
zLabel = wx.StaticText (win, pos = (5, 235), size = (60, 25), label = 'z-Coord')
#Display Output Window and Label
output = wx.TextCtrl(win, pos = (240, 140), size = (200, 200))
outputLabel = wx.StaticText(win, pos = (240, 115), size = (200, 25), label = 'Output')
#Submit Button and Binding
submitButton = wx.Button(win, label = 'Submit Coords', pos = (80, 200), size = (100, 25))
submitButton.Bind(wx.EVT_BUTTON, submit)
#Show "win" and GUI Loop
win.Show()
GUI.MainLoop()

