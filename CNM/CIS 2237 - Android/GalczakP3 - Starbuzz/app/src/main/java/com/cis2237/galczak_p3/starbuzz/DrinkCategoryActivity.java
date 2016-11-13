package com.cis2237.galczak_p3.starbuzz;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends ListActivity {

    private String[] drinkNames = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ListView drinkList = getListView();

        for(int i = 0; i < Drink.drinks.length; ++i){
            drinkNames[i] = Drink.drinks[i].getName();
        }

        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drinkNames);

        drinkList.setAdapter(listAdapter);

        drinkList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);


                // Sending the key to DrinkActivity via the intent
                intent.putExtra("key", (int)listAdapter.getItemId(position));
                startActivity(intent);
            }
        });
    }
}
