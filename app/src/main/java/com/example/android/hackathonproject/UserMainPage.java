package com.example.android.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.android.hackathonproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMainPage extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_user_main_page);


       // BottomNavigationView b=findViewById(R.id.bottomNavigation);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        BottomNavigationView b=(BottomNavigationView) findViewById(R.id.bottomNavigation);



//        Log.d("value of b",String.valueOf(b));
        b.setOnItemSelectedListener(item->
        {

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(new ProfileFragment());
                    return true;

                case R.id.navigation_notifications:
                    replaceFragment(new AddFragment());
                    return true;
            }
            return false;

        });











    }



    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}