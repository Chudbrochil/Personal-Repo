/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galczakp5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;



public class dbManager {
    
    private Connection connection;
    private Statement statement;
    private final String TABLE_NAME = "GalczakP5";
    private Result result;
    
    //Constructor established the connection to the database
    public dbManager(){
        try {            
                // Load the JDBC driver
                Class.forName("oracle.jdbc.driver.OracleDriver");    
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try{    
            // Establish a connection
            connection = DriverManager.getConnection
               ("jdbc:oracle:thin:@instora01.admin.ad.cnm.edu:1521:orcl","scott", "tiger");
            //statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"The database could not be located. "
                        + "Please select the database"+ " file you wish to connect to.",
			"Database Error", JOptionPane.ERROR_MESSAGE);
        }   
    }
    
    public void insert(int recordID, String jname, String jattribute, String jgender, String jexpacOrClass){
        try {
            //Insert a new record into the database
            String insertQuery =
                    "INSERT INTO "+TABLE_NAME +
                    " VALUES ("+recordID +", '"+jname+"', '"+jattribute+"', '"+jgender+"', '"+jexpacOrClass+"')";
            
            statement = connection.createStatement();
            statement.executeUpdate(insertQuery);
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] getRecordById(int recordID){

            String jname = "";
            String jattribute= "";
            String jgender = "";
            String jexpacOrClass = "";

            try {
            statement = connection.createStatement();

                //Display the contents of the record      
                String newQuery = "SELECT * "
                        +"FROM "+TABLE_NAME
                        +" WHERE ID = "+ recordID;
                ResultSet result = statement.executeQuery(newQuery);

                if(result.next())
                {
                    jname = result.getString(2);
                    jattribute = result.getString(3);
                    jgender = result.getString(4);
                    jexpacOrClass = result.getString(5);
                }               

        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         String[] getRow = {jname, jattribute, jgender, jexpacOrClass};
            return getRow;
    }
    
    public void editRecord(int recordID, String jname, String jattribute, String jgender, String jexpacOrClass){
        try {
            statement = connection.createStatement();
            //update the record
            String editQuery = "UPDATE " +TABLE_NAME
                    + " SET NAME = '"+jname+"', PRIMARY_ATTRIBUTE = '"+jattribute+"', GENDER = '"+
                    jgender+"', EXPANSION_OR_CLASSIC = '"+jexpacOrClass+"' "+ "WHERE ID = "+recordID;
            statement.executeUpdate(editQuery);
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteRecord(int  recordID) {

        try {
            statement = connection.createStatement();

            //Delete the record.
            String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE ID = " + recordID;
            statement.executeUpdate(deleteQuery);
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //Classes for creating the table and putting values in it.
    
    //Create a table
    public void createTable(){
        try {
            statement = connection.createStatement();
            statement.executeUpdate
                ("Create table  " +TABLE_NAME+ " " +
                        "(ID NUMBER(3) NOT NULL PRIMARY KEY, "
                        + "NAME Varchar2(30) NOT NULL, "
                        + "PRIMARY_ATTRIBUTE Varchar2(30) NOT NULL , "
                        + "GENDER Varchar2(10) NOT NULL , "
                        + "EXPANSION_OR_CLASSIC Varchar2(30) NOT NULL )");
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void populateDatabase(){
        try {
            //Add 5 rows of data to the table
            statement = connection.createStatement(); 
            String query1 = "INSERT INTO " +TABLE_NAME+ " " +
                    "(ID, NAME, PRIMARY_ATTRIBUTE, GENDER, EXPANSION_OR_CLASSIC)"  +
                    "VALUES (1, 'Sorceress', 'Magic', 'Female', 'Classic')";
            String query2 = "INSERT INTO " +TABLE_NAME+ " " +
                    "(ID, NAME, PRIMARY_ATTRIBUTE, GENDER, EXPANSION_OR_CLASSIC)"  +
                    "VALUES (2, 'Barbarian', 'Strength', 'Male', 'Classic')";
            String query3 = "INSERT INTO " +TABLE_NAME+ " " +
                    "(ID, NAME, PRIMARY_ATTRIBUTE, GENDER, EXPANSION_OR_CLASSIC)"  +
                    "VALUES (3, 'Druid', 'Vitality', 'Male', 'Expansion')";
            String query4 = "INSERT INTO " +TABLE_NAME+ " " +
                    "(ID, NAME, PRIMARY_ATTRIBUTE, GENDER, EXPANSION_OR_CLASSIC)"  +
                    "VALUES (4, 'Necromancer', 'Magic', 'Male', 'Classic')";
            String query5 = "INSERT INTO " +TABLE_NAME+ " " +
                    "(ID, NAME, PRIMARY_ATTRIBUTE, GENDER, EXPANSION_OR_CLASSIC)"  +
                    "VALUES (5, 'Assassin', 'Dexterity', 'Female', 'Expansion')";
            statement.executeQuery(query1);
            statement.executeQuery(query2);
            statement.executeQuery(query3);
            statement.executeQuery(query4);
            statement.executeQuery(query5);
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        public void dropTable(){

        try {
            statement = connection.createStatement();
            
            //Be sure to change the name of the table
            String drop = "Drop Table " +TABLE_NAME+ " " ;
            statement.execute(drop);
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }    
          
     }
    
}
