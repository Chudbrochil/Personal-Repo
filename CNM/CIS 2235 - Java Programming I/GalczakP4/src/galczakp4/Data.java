// Anthony Galczak - agalczak@cnm.edu
// Java I - Program 4 GUI by NetBeans
// Data.java - Data Storage/File Manipulation


package galczakp4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class Data {
    
    String name, cName, color, processor, hdd, memory, frameSummary, fileSummary;
    StringBuilder peripherals = new StringBuilder();
    int memoryIndex, memoryPrice;
    
    // Setters - will be set by the GUI
    public void setName(String n){
        name = n;
    }
    public void setcName(String cn){
        cName = cn;
    }
    public void setColor(String c){
        color = c;
    }
    public void setProcessor(String p){
        processor = p;
    }
    public void setHDD(String h){
        hdd = h;
    }
    public void setPeriphs(String p){
        peripherals.append("\n");
        peripherals.append(p);
    }
    public void setMemoryIndex(int m){
        memoryIndex = m;
        parseMemoryIndex();
    }
    
    
    // Getters 
    public String getName(){
        return name;
    }
    public String getcName(){
        return cName;
    }
    public String getColor(){
        return color;
    }
    public String getProcessor(){
        return processor;
    }
    public String getHDD(){
        return hdd;
    }
    public String getPeriphs(){
        return peripherals.toString();
    }
    public String getMemory(){
        return memory;
    }
    public int getMemoryPrice(){
        return memoryPrice;
    }
    
    // Changing the index gathered from the slider into a string declaring how
    // much memory the user has selected
    public void parseMemoryIndex(){
        if(memoryIndex == 0){
            memory = "8GB";
            memoryPrice = 50;
        }
        else if (memoryIndex == 1){
            memory = "16GB";
            memoryPrice = 100;
        }
        else if (memoryIndex == 2){
            memory = "32GB";
            memoryPrice = 200;
        }
    }
    
    // Writing data to a file
    public void writeFile(File file) {
        try{
            FileWriter fstream = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(this.toString());
            out.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    // toString for the summary jta
    public String toString(){
        frameSummary = "Hello, " + name + ".\nHere are the details for: " + cName + 
                "\nColor: " + color + "\nProcessor: " + processor + 
                "\nHard Drive: " + hdd + "\nMemory: " + memory + " - " + 
                memoryPrice + "\nPeripherals: " + getPeriphs();
        return frameSummary;
    }


    
    
}
