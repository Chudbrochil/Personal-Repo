package com.cis2237.galczak_p3.starbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        TextView name = (TextView)findViewById(R.id.txtFoodName);
        TextView price = (TextView)findViewById(R.id.txtFoodPrice);
        TextView desc = (TextView)findViewById(R.id.txtFoodDesc);
        ImageView img = (ImageView)findViewById(R.id.imgFood);

        Intent intent = getIntent();
        int key = intent.getIntExtra("key", 0);

        img.setImageResource(key);

        for(int i = 0; i < Food.foods.length; ++i){
            if(Food.foods[i].getImgResourceId() == key){
                name.setText("Name: " + Food.foods[i].getName());
                price.setText("Price: " + Food.foods[i].getCost());
                desc.setText("Description: " + Food.foods[i].getDescription());
            }
        }
    }
}
