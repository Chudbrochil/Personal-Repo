#GalczakP2
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: Provides user the capability to view contact info
# Database
names = ['donald', 'otis', 'ashley', 'amber']
emails = ['donaldg@compuserv.net', 'otis@yahoo.com', 'ashleyferguson@ashleyferguson.com', 'amberm@gmail.com']
phone = ['505-222-1234', '555-234-2923', '616-111-2093', '923-231-3932']
# Display
print names
# Input
userdata = raw_input('Who are you looking for?')
if userdata not in (names): print 'Invalid name, try again!'
# Index Reference
names_index = names.index(userdata)
emails_output = emails[names_index]
phone_output = phone[names_index]
# Successful Display
print "Email Address: " + emails_output
print "Phone Number: " + phone_output
input('Press any key to continue!')
