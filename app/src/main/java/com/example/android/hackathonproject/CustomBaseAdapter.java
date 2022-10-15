package com.example.android.hackathonproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter implements View.OnClickListener{
    Context context;
    ArrayList<String> foodlist=new ArrayList<>();;
    ArrayList<String> quantitylist=new ArrayList<>();;
    LayoutInflater inflater;
    public CustomBaseAdapter(Context ctx,ArrayList<String> food,ArrayList<String> quantity)
    {
        this.context=ctx;
        inflater=LayoutInflater.from(ctx);

        foodlist.addAll(food);

        quantitylist.addAll(quantity);
    }
    @Override
    public int getCount() {
        return foodlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView = inflater.inflate(R.layout.activity_row_custom, null);
        TextView foodview=(TextView)convertView.findViewById(R.id.foodview);
       /// TextView quantityview=(TextView) convertView.findViewById(R.id.quantityview);
      //  Button delete=convertView.findViewById(R.id.delete);
      //  delete.setTag(i);
      //  delete.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View view) {

//
      //      }
     //   });
        foodview.setText(foodlist.get(i));
     //   quantityview.setText(quantitylist.get(i));
        return convertView;
    }


    @Override
    public void onClick(View view) {

    }
}
