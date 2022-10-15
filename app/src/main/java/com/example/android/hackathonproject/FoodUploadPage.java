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

          add=findViewById(R.id.addButton);
          food=(TextView)findViewById(R.id.uploadFood);
          feed=(TextView)findViewById(R.id.feedEnter);
          add.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                    String s1=food.getText().toString();
                    String s2=feed.getText().toString();
                    if(s1.length()==0||s2.length()==0) {
                        Toast.makeText(FoodUploadPage.this, "Enter complete details", Toast.LENGTH_SHORT).show();
                    }
              }
          });


         // list=findViewById(R.id.list);
          //food.add("ddfdfdf");
          //quantity.add("sdsdsd");
          //food.add("dffdfdf");
          //quantity.add("s");
       //   customBaseAdapter=new CustomBaseAdapter(getApplicationContext(),food,quantity);
//        food.add("Paratha - 5 pieces");
//        food.add("Paratha - 5 pieces");
//        food.add("Paratha - 5 pieces");
//        arrayAdapter=new ArrayAdapter<String>(this, R.layout.activity_row_custom,food);
      //  list.setAdapter(customBaseAdapter);

        //Button button= (Button) findViewById(R.id.addButton);
       // button.setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View v) {
                //TextView t1 = (TextView) findViewById(R.id.foodEnter);
             //   TextView t2 = (TextView) findViewById(R.id.quantityEnter);
              //  String s1 = (String) t1.getText().toString();
             ///   String s2 = (String) t2.getText().toString();
            //    if ( s2.length() == 0) {
             //       Toast.makeText(FoodUploadPage.this, "Enter the complete details", Toast.LENGTH_SHORT).show();
             //       return;
            //    }
             //   try {
            ////        int pol= (Integer.parseInt(s2));
                   // food.add(s1);
                 //   quantity.add(s2);
               //     customBaseAdapter=new CustomBaseAdapter(getApplicationContext(),food,quantity);
                 //   list.setAdapter(customBaseAdapter);
                    //customBaseAdapter.notifyDataSetChanged();
                   // t1.setText("");
                //    t2.setText("");
              //      Toast.makeText(FoodUploadPage.this, "Added to Food List", Toast.LENGTH_SHORT).show();
             //   }catch(Exception e)
           //     {
              //      Toast.makeText(FoodUploadPage.this, "Enter integer value for  feed count", Toast.LENGTH_SHORT).show();
             //       t2.setText("");
            //    }

            }
    //    });

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(FoodUploadPage.this, "LongPress to delete item", Toast.LENGTH_SHORT).show();
//            }
//
//            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int pos, long id)
//            {
//                food.remove(pos);
//                quantity.remove(pos);
//                customBaseAdapter=new CustomBaseAdapter(getApplicationContext(),food,quantity);
//                list.setAdapter(customBaseAdapter);
//                return true;
//            }
//        });
//
//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            public boolean onItemLongClick(AdapterView<?> arg0, View v,
//                                           int index, long arg3) {
//
//                food.remove(index);
//                quantity.remove(index);
//                customBaseAdapter=new CustomBaseAdapter(getApplicationContext(),food,quantity);
//                list.setAdapter(customBaseAdapter);
//                //Toast.makeText(list.this,myList.getItemAtPosition(index).toString(), Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });


    }

//    public  void add(View v)
//    {
//        if(v==add) {
//            TextView t1 = (TextView) findViewById(R.id.foodEnter);
//            TextView t2 = (TextView) findViewById(R.id.quantityEnter);
//            String s1 = (String) t1.getText();
//            String s2 = (String) t2.getText();
//            if (s1.length() == 0 || s2.length() == 0) {
//                Toast.makeText(this, "Enter the complete details", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            food.add(s1);
//            quantity.add(s2);
//            customBaseAdapter.notifyDataSetChanged();
//        }
//    }
//}