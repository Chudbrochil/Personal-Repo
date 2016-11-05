// Anthony Galczak - agalczak@cnm.edu - Program 8
// FXMLDocumentController.java

package galczakp8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

/**
 *
 * @author Anthony
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML RadioButton radEnigmaKey;
    @FXML RadioButton radEnterKey;
    @FXML TextField txtEnterKey;
    @FXML Button btnEncode;
    @FXML Button btnDecode;
    @FXML Button btnClear;
    @FXML TextField txtMessage;
    @FXML TextField txtCodedMessage;
    @FXML TextField txtKeyOutput;
    
    Enigma enig = new Enigma();
    
    int key;
    String codedMessage, message;
    
    @FXML
    private void encodeButtonClick(ActionEvent event){
        
        String message = txtMessage.getText();
        int key;
        
        if(radEnterKey.isSelected()){
            key = Integer.parseInt(txtEnterKey.getText());
            enig.setMessage(message, key);
        }
        else{
            enig.setMessage(message);
        }
        
        txtCodedMessage.setText(enig.getCodedMessage());
        txtKeyOutput.setText(String.valueOf(enig.getKey()));
        
        
    }
    
    @FXML
    private void decodeButtonClick(ActionEvent event){
        String codedMessage = txtCodedMessage.getText();
        int key = Integer.parseInt(txtEnterKey.getText());
        
        enig.setCodedMessage(codedMessage, key);
        
        txtMessage.setText(enig.getMessage());
        txtKeyOutput.setText(String.valueOf(enig.getKey()));
        
        
    }
    
    @FXML
    private void clearButtonClick(ActionEvent event){
        txtEnterKey.clear();
        txtMessage.clear();
        txtCodedMessage.clear();
        txtKeyOutput.clear();
        radEnigmaKey.isSelected();
    }
    
    @FXML
    private void saveFileClick(ActionEvent event){
    //Create Filechooser object
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File("."));
    fileChooser.setTitle("Save a Coded Message in a File");
    //Set extension filter
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
    fileChooser.getExtensionFilters().add(extFilter);         
        
    //Show the Save File Dialog
    File file = fileChooser.showSaveDialog(null); 
        
    if(file != null)
    {
        PrintWriter outputFile = null;
        try {
            String filename = file.getCanonicalPath();
            File myFile = new File(filename);
            outputFile = new PrintWriter(filename);
            outputFile.println(enig.getCodedMessage());
            outputFile.println(enig.getKey());
               
            outputFile.close();
                
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    @FXML
    private void openFileClick(ActionEvent event){
        //Create Filechooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open a Coded Message and key in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);         
        
        //Show the Open File Dialog
        File file = fileChooser.showOpenDialog(null);  
        
        if(file != null)
        {
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                Scanner inputFile = new Scanner(myFile);        	   
                codedMessage = inputFile.nextLine();
                key = inputFile.nextInt();               
                inputFile.close();
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        
        // Setting fields with gathered data
        txtCodedMessage.setText(codedMessage);
        txtKeyOutput.setText(String.valueOf(key));
        txtEnterKey.setText(String.valueOf(key));
        
        // Decoding it 
        decodeButtonClick(event);
        

 
    }
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radEnigmaKey.setSelected(true);
        txtEnterKey.setText("1");
    }
    
    

    
    
    
    
}
