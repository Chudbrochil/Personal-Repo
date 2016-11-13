package com.cis2237.galczak_p3.starbuzz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FindStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_store);

        final RadioButton radSite1 = (RadioButton)findViewById(R.id.radSite1);
        final RadioButton radSite2 = (RadioButton)findViewById(R.id.radSite2);
        final RadioButton radSite3 = (RadioButton)findViewById(R.id.radSite3);

        RadioGroup radGroup = (RadioGroup)findViewById(R.id.radGrp);

        final String website1 = "http://www.google.com";
        final String website2 = "http://www.crazygoodeats.com";
        final String website3 = "http://www.foodnetwork.com";

        radSite1.setText(website1);
        radSite2.setText(website2);
        radSite3.setText(website3);

        // When you return to the activity all the radio buttons will be blank
        radSite1.setChecked(false);
        radSite2.setChecked(false);
        radSite3.setChecked(false);

        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId){
                Log.d("DEBUG", checkedId + "");

                // Finding which radio button is the one that got checked
                RadioButton checkedRadio = (RadioButton)group.findViewById(checkedId);

                // Opening a website based on which radio button got checked
                urlNav(checkedRadio.getText().toString());
            }
        });

    }

    public void urlNav(String url){
        Uri urlToVisit = Uri.parse(url);
        Intent browser = new Intent(Intent.ACTION_VIEW, urlToVisit);
        startActivity(browser);
    }
}
