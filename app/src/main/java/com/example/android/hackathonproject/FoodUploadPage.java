package com.example.android.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodUploadPage extends AppCompatActivity {
    Button add;
   // ArrayList<String> food=new ArrayList<>();
   /// ArrayList<String> quantity=new ArrayList<>();
  //  ListView list;
    TextView food,feed;
    String foodText,feedText;
  //  ArrayAdapter<String> arrayAdapter;
  //  CustomBaseAdapter customBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_upload_page);

        add = findViewById(R.id.addButton);
        food = (TextView) findViewById(R.id.uploadFood);
        feed = (TextView) findViewById(R.id.feedEnter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = food.getText().toString();
                String s2 = feed.getText().toString();
                if (s1.length() == 0 || s2.length() == 0) {
                    Toast.makeText(FoodUploadPage.this, "Enter complete details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}