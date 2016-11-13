package com.cis2237.galczak_p3.rsi_balloon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cis2237.galczak_p3.rsi_balloon.ImageAdapter;
import com.cis2237.galczak_p3.rsi_balloon.R;

import java.util.ArrayList;


public class BalloonActivity extends AppCompatActivity {

    public static final String key = "key";

    Balloon balloon = new Balloon();

    ArrayList<Balloon> balloons = new ArrayList<Balloon>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balloon);

        TextView name = (TextView)findViewById(R.id.tvName);
        TextView owner = (TextView)findViewById(R.id.tvOwner);
        TextView location = (TextView)findViewById(R.id.tvLocation);
        ImageView img = (ImageView)findViewById(R.id.ivBalloonActivity);

        balloons = balloon.getBalloonList();


        //Refernce http://stackoverflow.com/questions/7074097/how-to-pass-integer-from-one-activity-to-another
        Intent intent = getIntent();
        int key = intent.getIntExtra("key", 0);


        //key = R.drawable.angry;

        img.setImageResource(key);


        for(int i = 0; i < balloons.size(); ++i){
            if(balloons.get(i).getImgResource() == key){
                //theBalloon = balloons.get(i);
                name.setText("Balloon Name: " + balloons.get(i).getBalloonName());
                owner.setText("Owner of Balloon: " + balloons.get(i).getOwnerName());
                location.setText("Location: " + balloons.get(i).getLocationName());


            }
        }



    }


}