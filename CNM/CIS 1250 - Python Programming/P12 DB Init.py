#GalczakP12 DB Init
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: Initialize a database of points

import sqlite3

#---Database Initialization Function---
def makeData():
    conn = sqlite3.connect('P12Points.db')
    curs = conn.cursor()
#Formatting table
    curs.execute('''
    CREATE TABLE Points(
    Num1Field INT,
    Num2Field INT,
    Num3Field INT,
    StringField TEXT
)
''')
#Declaring Data
    startingData = (
        (4,9,21,'Building B12'),
        (29,-13,6,'Building A39'),
        (90,6,-18,'Building C22'),
        (42,19,22,'Building A21'),
        (109,55,92,'Building B19'),
        (81,144,121,'Building A51'),
        (5,20,-12,'Building B14')
        )
#Inserting data into Table
    for row in startingData:
        sqlCmd = '''
            INSERT INTO Points (Num1Field, Num2Field, Num3Field, StringField)
            VALUES (%s, %s, %s, '%s')
            ''' % row
        curs.execute(sqlCmd)
#Committing changes to database
    conn.commit()
    conn.close()

makeData()
