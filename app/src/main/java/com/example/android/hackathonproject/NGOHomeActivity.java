package com.example.android.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NGOHomeActivity extends AppCompatActivity {
    ImageView home,manage,history,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngohome);

        home=(ImageView) findViewById(R.id.home_ngo);
        manage=(ImageView) findViewById(R.id.manage_ngo);
        history=(ImageView) findViewById(R.id.history_ngo);
        profile=(ImageView) findViewById(R.id.profile_ngo);

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                manage.setBackgroundResource(R.color.white);
                history.setBackgroundResource(R.color.white);
                profile.setBackgroundResource(R.color.white);
                home.setBackgroundResource(R.color.teal_200);
                replaceFragment(new HomeFragment());
            }
        });
        manage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                home.setBackgroundResource(R.color.white);
                history.setBackgroundResource(R.color.white);
                profile.setBackgroundResource(R.color.white);
                manage.setBackgroundResource(R.color.teal_200);
                replaceFragment(new ProfileFragment());
            }
        });
        history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                manage.setBackgroundResource(R.color.white);
                home.setBackgroundResource(R.color.white);
                profile.setBackgroundResource(R.color.white);
                history.setBackgroundResource(R.color.teal_200);
                replaceFragment(new AddFragment());
            }
        });
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                manage.setBackgroundResource(R.color.white);
                history.setBackgroundResource(R.color.white);
                home.setBackgroundResource(R.color.white);
                profile.setBackgroundResource(R.color.teal_200);
                replaceFragment(new ProfileFragment());
            }
        });

    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout,fragment);
        fragmentTransaction.commit();
    }
}