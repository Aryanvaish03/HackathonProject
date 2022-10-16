package com.example.android.hackathonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.hackathonproject.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView signUpPromt = findViewById(R.id.SignUpPromt);

        // ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account");
        EditText EmailLogin = findViewById(R.id.etEmailLogin);
        EditText PasswordLogin = findViewById(R.id.etPasswordLogin);
        Button btnLogin= findViewById(R.id.btnLogin);
        DatabaseReference reference = database.getReference().child("Users");
        if(mAuth.getCurrentUser()!=null)
        {

            reference.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    String userType = user.getUserType();
                    if(userType.equals("NGO"))
                    {
                        startActivity(new Intent(LoginActivity.this, NGOHomeActivity.class));
                        finish();

                    }
                    else{
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(EmailLogin.getText().toString()) || EmailLogin==null)
                {
                    EmailLogin.setError("Email is invalid");
                    Toast.makeText(LoginActivity.this,"Email is invalid",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(PasswordLogin.getText().toString()) || PasswordLogin==null)
                {
                    PasswordLogin.setError("Password is invalid");
                    Toast.makeText(LoginActivity.this,"Password is invalid",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(EmailLogin.getText().toString(),PasswordLogin.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            reference.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    User user = snapshot.getValue(User.class);
                                    String userType = user.getUserType();
                                    if(userType.equals("NGO"))
                                    {
                                        startActivity(new Intent(LoginActivity.this, NGOHomeActivity.class));
                                        finish();

                                    }
                                    else{
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            Toast.makeText(LoginActivity.this, "Login Successfull",Toast.LENGTH_SHORT).show();


                        }
                        else{
                            Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });
            }
        });

        signUpPromt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();
            }
        });





}
}