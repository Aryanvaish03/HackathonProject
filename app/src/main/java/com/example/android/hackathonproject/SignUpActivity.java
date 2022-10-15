package com.example.android.hackathonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.hackathonproject.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private TextView tv;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private EditText etEmailSignUp, etPasswordSignUp, etNameSignUp,etAddress,etLocality,etCity,etPincode;
    private Button btnSignUp;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageView bksn=(ImageView) findViewById(R.id.back_arrow);
        bksn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                finish();
            }
        });

// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        etNameSignUp = findViewById(R.id.etNameSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        etAddress=findViewById(R.id.etAddress);
        etLocality=findViewById(R.id.etLocality);
        etCity= findViewById(R.id.etCity);
        etPincode=findViewById(R.id.etPincode);


        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating Account ");
        progressDialog.setMessage("We are creating your account");


//get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"NGO","FOOD PROVIDER"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);





        btnSignUp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                progressDialog.show();
                String email = etEmailSignUp.getText().toString();
                String password = etPasswordSignUp.getText().toString();
                String name = etNameSignUp.getText().toString();
                String address = etAddress.getText().toString();
                String locality = etLocality.getText().toString();
                String city = etCity.getText().toString();
                String pincode = etPincode.getText().toString();



                String userType= dropdown.getSelectedItem().toString();




                if (TextUtils.isEmpty(email)) {
                    etEmailSignUp.setError("Email is Required");
                    progressDialog.hide();
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    etPasswordSignUp.setError("Password is Required");
                    progressDialog.hide();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    etNameSignUp.setError("UserName is Required");
                    progressDialog.hide();
                    return;
                }

                if (TextUtils.isEmpty(address)){
                    etEmailSignUp.setError("address is Required");
                    progressDialog.hide();
                    return;
                }

                if (TextUtils.isEmpty(locality)){
                    etEmailSignUp.setError("locality is Required");
                    progressDialog.hide();
                    return;
                }

                if (TextUtils.isEmpty(city)){
                    etEmailSignUp.setError("state is Required");
                    progressDialog.hide();
                    return;
                }
                if (TextUtils.isEmpty(pincode)){
                    etEmailSignUp.setError("Pin code is Required");
                    progressDialog.hide();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){

                            User user = new User(name,email, password,address, locality ,city , pincode, userType);

                            String id = task.getResult().getUser().getUid();


                            database.getReference().child("Users").child(id).setValue(user);
                            Toast.makeText(SignUpActivity.this, "Account Created Successfully "+userType,Toast.LENGTH_SHORT).show();

                            if(userType.equals("NGO")) {

                                startActivity(new Intent(SignUpActivity.this, NGOHomeActivity.class));
                            }
                            else {
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));

                            }


                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                        }
                    }
                });
            }

        });


    }

}


