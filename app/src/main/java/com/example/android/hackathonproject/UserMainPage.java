package com.example.android.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android.hackathonproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMainPage extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_user_main_page);


        BottomNavigationView b=findViewById(R.id.bottomNavigation);

    }
}