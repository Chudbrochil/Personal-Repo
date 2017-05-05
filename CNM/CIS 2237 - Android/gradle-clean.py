# Anthony Galczak - built using Python 2.7.12 on 11-22-2016

import os

basePath = 'C:\\Users\\anthony\\Documents\\~Docs\\ws\\Android'

print("If this gets stuck, you must close the DOS window so the script can continue. This means your folder isn't a Android-gradle folder.")

for folder in os.listdir(basePath):
    print("Gradle clean'ing " + folder)
    os.system("cd " + basePath + "\\" + folder + " && gradlew clean")

    
