// Anthony Galczak - agalczak@cnm.edu
// GalczakP1 - Tipster Tip Calculator
// Calculates a tip and your part of the bill for dining out.
// FXMLDocumentController.java - Controller

package galczakp1;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.Locale;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Anthony
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML Button btnClear;
    @FXML TextField tfBillAmount;
    @FXML TextField tfPeopleAmount;
    @FXML TextField tfTipPercent;
    @FXML Slider slideTip;            
    @FXML TextField tfTipAmount;
    @FXML TextField tfTotalDue;
    @FXML TextField tfTotalPerDiner;
    
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    NumberFormat np = NumberFormat.getPercentInstance(Locale.ENGLISH);
    TipsterCalc tc = new TipsterCalc(); 
    Boolean submitted = false;
    
    // Method for setting values into TipsterCalc and returning them into the
    // text fields in JavaFX, includes exception handling for incorrect inputs
    private void setValues(){
        try{
            String cleanedBillAmt = tfBillAmount.getText().replace("$", "").replace(",", "");
            String cleanedTipAmt = tfTipPercent.getText().replace("%", "");
            tc.setInput(Double.parseDouble(cleanedBillAmt),
                Integer.parseInt(tfPeopleAmount.getText()),
                Double.parseDouble(cleanedTipAmt));
            tfTipAmount.setText(String.valueOf(nf.format(tc.getTipAmount())));
            tfTotalDue.setText(String.valueOf(nf.format(tc.getTotalDue())));
            tfTotalPerDiner.setText(String.valueOf(nf.format(tc.getAmountPerDiner())));
            
            // Re-formatting bill amount to currency value when calculate is hit
            tfBillAmount.setText(nf.format(Double.valueOf(cleanedBillAmt)));
            submitted = true;
            
            // Setting values to un editable to save from further errors when
            // changing values on scroll. To change these values, hit clear instead
            tfBillAmount.setEditable(false);
            tfPeopleAmount.setEditable(false);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, 
                "\nPlease enter valid inputs into the text fields.\n"
                + "Example:\nBill: 78.50\nPeople: 3",
                "Try again", JOptionPane.OK_OPTION);
        }
    }
    
    @FXML
    private void handleCalculateButton(ActionEvent event) {
        setValues();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Getting initial value of the slider for window generation
        tfTipPercent.setText(np.format((slideTip.getValue()/100)));
        
        // Slider construct obtained from 
        // http://code.makery.ch/blog/javafx-2-event-handlers-and-change-listeners/
        // Anonymous ChangeListener that executes upon a "changed" event
        // Overriden changed method implements generic Number parameters
        // https://docs.oracle.com/javafx/2/api/javafx/beans/value/ChangeListener.html
        slideTip.valueProperty().addListener(new ChangeListener<Number>(){
        public void changed(ObservableValue<? extends Number> observable,
                Number oldValue, Number newValue){
        tc.setTipPercent(newValue.intValue());
        tfTipPercent.setText(np.format((tc.getTipPercent()))); 
        
        // If user has hit submit already, they can setValues via scrolling
        if(submitted) setValues();
        }
        }); 
        
        // Anonymous class for the clear button handler
        btnClear.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                tfBillAmount.clear();
                tfPeopleAmount.clear();
                tfTipAmount.clear();
                tfTotalDue.clear();
                tfTotalPerDiner.clear();
                tfBillAmount.setEditable(true);
                tfPeopleAmount.setEditable(true);
                submitted = false;
            }
        });
        
    }    
    
}
