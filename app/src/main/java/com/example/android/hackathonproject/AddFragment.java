package com.example.android.hackathonproject;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.hackathonproject.Models.FoodPost;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class AddFragment extends Fragment {


    public AddFragment() {
        // Required empty public constructor
    }

    FirebaseAuth auth;
    FirebaseDatabase database;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Processing Your Request");

        Button submit;
        EditText foodItems, feedCount;

        submit = view.findViewById(R.id.submitButton);
        foodItems = view.findViewById(R.id.uploadFood);
        feedCount = view.findViewById(R.id.feedEnter);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                String foodDetails = foodItems.getText().toString();
                String fedCount =feedCount.getText().toString();
                FoodPost post = new FoodPost();


                if (TextUtils.isEmpty(foodDetails)) {
                    foodItems.setError("Field Required");
                    progressDialog.hide();
                    return;
                }

                if (TextUtils.isEmpty(fedCount)) {
                    feedCount.setError("Email is Required");
                    progressDialog.hide();
                    return;


                }



                post.setDate(new Date());
                post.setFeedCount(fedCount);
                post.setFoodItems(foodDetails);
                post.setPostedBy(auth.getUid());
                post.setStatus("UNACCEPTED");

              database.getReference().child("FoodPosts").push().setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if (task.isSuccessful()) {
                          Toast.makeText(getContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                          progressDialog.hide();
                      }
                  }
                                                                                                     }
              ).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                      progressDialog.hide();


                  }

              });


            }
        });








        return view;

    }
}