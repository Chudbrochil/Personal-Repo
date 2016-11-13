package com.cis2237.galczak_p3.starbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {

    public static final String key = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        ImageView drinkImg = (ImageView)findViewById(R.id.imgDrink);
        TextView drinkName = (TextView)findViewById(R.id.txtName);
        TextView drinkPrice = (TextView)findViewById(R.id.txtPrice);
        TextView drinkDesc = (TextView)findViewById(R.id.txtDesc);


        Intent intent = getIntent();
        int key = intent.getIntExtra("key", 0);

        //drinkImg.setImageResource(key);

        for(int i = 0; i < Drink.drinks.length; ++i){
            // If the key is equal to the iterator then use the iterator as the index
            if (i == key){
                drinkName.setText("Drink Name: " + Drink.drinks[i].getName());
                drinkPrice.setText("Price: " + Drink.drinks[i].getPrice());
                drinkDesc.setText("Description: " + Drink.drinks[i].getDescription());
                drinkImg.setImageResource(Drink.drinks[i].getImgResource());
            }

        }

    }
}
