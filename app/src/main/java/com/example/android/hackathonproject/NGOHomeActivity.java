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

import com.example.android.hackathonproject.Fragments.NgoProfile;
import com.example.android.hackathonproject.Fragments.ngoHistoryFragment;
import com.example.android.hackathonproject.Fragments.ngoHome;
import com.example.android.hackathonproject.Fragments.ngo_Fragment_ManageRequests;
import com.google.firebase.auth.FirebaseAuth;

public class NGOHomeActivity extends AppCompatActivity {
    ImageView home,manage,history,profile;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngohome);
        auth= FirebaseAuth.getInstance();
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
                replaceFragment(new ngoHome());
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
                replaceFragment(new ngo_Fragment_ManageRequests());
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
                replaceFragment(new ngoHistoryFragment());
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
                replaceFragment(new NgoProfile());
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
                startActivity(new Intent(NGOHomeActivity.this,LoginActivity.class));
                finish();
                break;


        }
        return true;
    }
}