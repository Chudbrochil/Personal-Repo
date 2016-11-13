// Anthony Galczak - agalczak@cnm.edu
// CIS 2237 - Program 2

package mobiletipster.galczakp2.cis2237.com.galczakp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

// ic-launcher gathered from:
// https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html

public class TipsterActivity extends AppCompatActivity {
    TextView txtTipAmt, txtTotalWTip, txtDinerShare, txtBillAmt;
    SeekBar sb;
    TipsterCalc tc;
    Boolean submitted = false;
    Button btnCalc, btnReset;
    EditText editBillAmt, editSeekBarValue, editNumDiners;

    // Decimal formatter for currency
    DecimalFormat df = new DecimalFormat("$0.00");
    DecimalFormat pf = new DecimalFormat("0%");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipster);

        // Declaring model object
        tc = new TipsterCalc();

        // Textviews at bottom
        txtTipAmt = (TextView)findViewById(R.id.txtTipAmt);
        txtTotalWTip = (TextView)findViewById(R.id.txtTotalWTip);
        txtDinerShare = (TextView)findViewById(R.id.txtDinerShare);



        // Top textviews and editviews
        txtBillAmt = (TextView)findViewById(R.id.txtBillAmt);
        editBillAmt = (EditText)findViewById(R.id.editBillAmt);
        editSeekBarValue = (EditText)findViewById(R.id.editSeekBarValue);
        editNumDiners = (EditText)findViewById(R.id.editNumDiners);

        // Grabbing buttons
        btnCalc = (Button)findViewById(R.id.btnCalc);
        btnReset = (Button)findViewById(R.id.btnReset);

        // Grabbing the seekbar from the layout
        SeekBar sb = (SeekBar)findViewById(R.id.seekBar);

        // Setting initial value of the seek bar
        editSeekBarValue.setText(pf.format(sb.getProgress() / 100.0));

        // On click listener for calculate button
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValues();
            }
        });

        // On click listener for reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTipAmt.setText(df.format(0.00));
                txtTotalWTip.setText(df.format(0.00));
                txtDinerShare.setText(df.format(0.00));
                editBillAmt.setText("20.00");
                editNumDiners.setText("2");
                editBillAmt.setEnabled(true);
                editNumDiners.setEnabled(true);
                submitted = false;
            }
        });

        // Seek bar anonymous change listener
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                tc.setTipPercent(value);
                editSeekBarValue.setText(pf.format(tc.getTipPercent() / 100));
                if(submitted) setValues();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // This is where the edit text listener lives, it was a valiant effort...

        editBillAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("DEBUG", s.toString());
                if(s.length() > 0){
                    String value = s.toString().replace("$","");
                    txtBillAmt.setText("$" + value);
                }
                else{
                    txtBillAmt.setText("");
                }



                // Error handling if user deletes all the spaces and this field returns a null
                //if(value == null) { value = "0.00"; }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    // Method that is called to recalculate values
    private void setValues(){
        submitted = true;


        Double billAmt = Double.parseDouble(editBillAmt.getText().toString().replace("$", ""));
        Integer numDiners = Integer.parseInt(editNumDiners.getText().toString());
        // Grabbing the tip amount from the slider so I don't have to set this info into the class onto the initialization
        Integer tipAmt = Integer.parseInt(editSeekBarValue.getText().toString().replace("%", ""));

        // Exception Handling for if our lovely user sets diners to 0 somehow
        if (numDiners == 0){
            editNumDiners.setText("1");
            numDiners = 1;
        }

        // Setting input from text fields to the tipstercalc model
        tc.setInput(billAmt, numDiners, tipAmt);

        // Setting output to user
        txtTipAmt.setText(df.format(tc.getTipAmount()));
        txtTotalWTip.setText(df.format(tc.getTotalDue()));
        txtDinerShare.setText(df.format(tc.getAmountPerDiner()));

        // Giving the formatted value to user after hitting submit
        editBillAmt.setText(df.format(billAmt));

        // Disallowing user to edit fields until reset is hit
        editBillAmt.setEnabled(false);
        editNumDiners.setEnabled(false);

    }







}
