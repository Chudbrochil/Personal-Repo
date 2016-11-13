package com.cis2237.galczak_p3.starbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html for ic_launcher pictures



public class MainActivity extends AppCompatActivity {

    ListView mainMenu;
    String[] menuItems = new String[]{"Drinks", "Food", "Website"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainMenu = (ListView)findViewById(R.id.lvMenu);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, menuItems);
        mainMenu.setAdapter(adapter);

        mainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = null;

                switch((int)id){
                    case 0:
                        intent = new Intent(MainActivity.this, DrinkCategoryActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, FoodCategoryActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, FindStoreActivity.class);
                        break;

                }

                // If the user successfully selected an option, then navigate to that activity
                if(intent != null){
                    startActivity(intent);
                }
            }


        });
    }
}
