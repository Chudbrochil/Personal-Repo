package com.cis2237.galczak_p3.starbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class FoodCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);

        final GridView gv = (GridView)findViewById(R.id.gvFoods);
        gv.setAdapter(new ImageAdapter(this));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){

                // Creating intent for use in FoodActivity
                Intent intent = new Intent(FoodCategoryActivity.this, FoodActivity.class);

                // Passing key to next class
                intent.putExtra("key", ImageAdapter.imageIds[position]);
                startActivity(intent);
            }
        });

    }
}
