package com.cis2237.galczak_p3.rsi_balloon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class BalloonCategoryActivity extends AppCompatActivity {

    ImageAdapter image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balloon_category);

        final GridView gridView =(GridView)findViewById(R.id.gvBallon);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(BalloonCategoryActivity.this, BalloonActivity.class );



                intent.putExtra("key", ImageAdapter.photoId[position]);
                startActivity(intent);

            }
        });

    }

}
