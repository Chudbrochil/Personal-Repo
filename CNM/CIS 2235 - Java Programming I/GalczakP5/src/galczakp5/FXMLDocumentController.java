/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galczakp5;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author agalczak
 */
public class FXMLDocumentController implements Initializable {
    
    // Variables
    @FXML TextField tfRecordID;
    @FXML TextField tfName;
    @FXML TextField tfAttribute;
    @FXML TextField tfGender;
    @FXML TextField tfExpansion;
    @FXML TextField tfDeleteRecord;
    private int recordID = 1;
    private String jName;
    private String jAttribute;
    private String jGender;
    private String jExpansion;
    private dbManager manager = new dbManager();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    @FXML
    private void btnInsertPressed(ActionEvent event){
            
        // Getting the data from the form 
        recordID = Integer.parseInt(tfRecordID.getText());
        jName = tfName.getText();
        jAttribute = tfAttribute.getText();
        jGender = tfGender.getText();
        jExpansion = tfExpansion.getText();
        
        // Inserting the data into the database
        manager.insert(recordID, jName, jAttribute, jGender, jExpansion);
        
        
    }
    
    @FXML
    private void btnEnterIDPressed(ActionEvent event){
        
        // Getting the record from the form
        recordID = Integer.parseInt(tfRecordID.getText());
        
        // Populating my controller data from the database
        String[] data = manager.getRecordById(recordID);
        
        // Assigning it to variables
        jName = data[0];
        jAttribute = data[1];
        jGender = data[2];
        jExpansion = data[3];
        
        // Assigning the data to the form
        tfName.setText(jName);
        tfAttribute.setText(jAttribute);
        tfGender.setText(jGender);
        tfExpansion.setText(jExpansion);
    }
    
    @FXML
    private void btnEditPressed(ActionEvent event){
        
        // Getting the data from the form
        jName = tfName.getText();
        jAttribute = tfAttribute.getText();
        jGender = tfGender.getText();
        jExpansion = tfExpansion.getText();
        
        // Putting the edited data back into the database
        manager.editRecord(recordID, jName, jAttribute, jGender, jExpansion);
    }
    
    @FXML
    private void btnDeletePressed(ActionEvent event){
        // Getting the record ID from the correct text field
        recordID = Integer.parseInt(tfDeleteRecord.getText());
        
        // Deleting the database record
        manager.deleteRecord(recordID);
    }
    
    
    
}
