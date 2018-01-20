package com.cis2237.galczak_p3.rsi_balloon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    private ListView ballooningFAQ;
    private String[] listQuestions = new String[6];
    private String[] listAnswers = new String[6];

    private FAQ faq = new FAQ();

    private ArrayList<FAQ> faqs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        ballooningFAQ = (ListView)findViewById(R.id.ballooningFAQ);

        faqs = faq.getList();

        for(int i = 0; i < faq.getList().size(); ++i){
            listQuestions[i] = faqs.get(i).getQuestion();
        }

        for(int i = 0; i < faq.getList().size(); ++i){
            listAnswers[i] = faqs.get(i).getAnswer();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listQuestions);

        ballooningFAQ.setAdapter(adapter);

        ballooningFAQ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for(int i = 0; i < 2; ++i){
                    Toast.makeText(FAQActivity.this,listAnswers[(int)id],Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}

