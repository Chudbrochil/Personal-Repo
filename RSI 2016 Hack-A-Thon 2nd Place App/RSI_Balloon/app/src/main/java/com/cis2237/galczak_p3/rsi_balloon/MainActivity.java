package com.cis2237.galczak_p3.rsi_balloon;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView menu;
    String[] menuItems = new String[]{"Balloons", "Balloon Fiesta FAQs", "Balloon Fiesta Website", "Balloon Fiesta Schedule",
        "Balloon Fiesta Merch"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = (ListView)findViewById(R.id.lvMenu);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, menuItems);
        menu.setAdapter(adapter);

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            Intent intent = null;

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                switch((int)id){
                    case 0:
                        intent = new Intent(MainActivity.this, BalloonCategoryActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, FAQActivity.class);
                        break;
                    case 2:
                        intent = null;
                        goToUrl("http://www.balloonfiesta.com");
                        break;
                    case 3:
                        intent = null;
                        goToUrl("http://www.balloonfiesta.com/event-info/event-schedule");
                        break;
                    case 4:
                        intent = null;
                        goToUrl("http://www.balloonfiestastuff.com/index.php");
                        break;
                }

                if (intent != null){
                    startActivity(intent);
                }

            }


        });



    }

    public void goToUrl (String url) {
        Uri theUrl = Uri.parse(url);
        Intent browser = new Intent(Intent.ACTION_VIEW, theUrl);
        startActivity(browser);

    }


}
