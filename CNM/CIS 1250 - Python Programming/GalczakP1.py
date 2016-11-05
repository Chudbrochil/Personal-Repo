from __future__ import division
from time import sleep
# GalczakP1
# Programmer: Anthony Galczak
# EMail: Agalczak@cnm.edu
# Purpose: Provide a user the capability to calculate 
# the surface area of a triangle.
#display a welcome message
print "Welcome to Anthony's super advanced triangle area calculating software!"
sleep(1.0)
#ask the user for the base of the triangle
base = input("What is the base of your triangle in inches? ")
sleep(0.2)
#ask the user for the height of the triangle
height = input("What is the height of your triangle in inches? ")
#calculate the area
print "-/\-  One moment while I calculate the area!  -/\-"
sleep(0.2)
print "-\/-  One moment while I calculate the area!  -\/-"
sleep(0.2)
print "-/\-  One moment while I calculate the area!  -/\-"
sleep(0.2)
print "-\/-  One moment while I calculate the area!  -\/-"
sleep(0.2)
print "-/\-  One moment while I calculate the area!  -/\-"
sleep(0.2)
print "-\/-  One moment while I calculate the area!  -\/-"
sleep(0.2)
#display the results to the user
area = float(float(base)*float(height)) / 2.0
feet = area / 12
print "The surface area of your triangle is " + str(area) + " inches or " + str(feet) + " feet!"
input("Press any key to continue!")
