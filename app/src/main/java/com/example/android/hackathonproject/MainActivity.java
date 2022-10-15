package com.example.android.hackathonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ImageView home,manage,add,profile;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        auth =FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startActivity(new Intent(MainActivity.this,UserMainPage.class));

        home=(ImageView) findViewById(R.id.home_user);
        manage=(ImageView) findViewById(R.id.manage_user);
        add=(ImageView) findViewById(R.id.add_user);
        profile=(ImageView) findViewById(R.id.profile_user);

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                manage.setBackgroundResource(R.color.white);
                add.setBackgroundResource(R.color.white);
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
                add.setBackgroundResource(R.color.white);
                profile.setBackgroundResource(R.color.white);
                manage.setBackgroundResource(R.color.teal_200);
                replaceFragment(new ManageFragment());
            }
        });
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                manage.setBackgroundResource(R.color.white);
                home.setBackgroundResource(R.color.white);
                profile.setBackgroundResource(R.color.white);
                add.setBackgroundResource(R.color.teal_200);
                replaceFragment(new AddFragment());
            }
        });
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                manage.setBackgroundResource(R.color.white);
                add.setBackgroundResource(R.color.white);
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
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.logout:

                auth.signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
                break;


        }
        return true;
    }
}

