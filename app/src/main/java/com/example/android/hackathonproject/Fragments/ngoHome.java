package com.example.android.hackathonproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.hackathonproject.Models.FoodPost;
import com.example.android.hackathonproject.Models.User;
import com.example.android.hackathonproject.R;
import com.example.android.hackathonproject.adapter_package.ListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;



public class ngoHome extends Fragment  {



    FirebaseAuth auth = FirebaseAuth.getInstance();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ngoHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ngoHome.
     */
    // TODO: Rename and change types and number of parameters
    public static ngoHome newInstance(String param1, String param2) {
        ngoHome fragment = new ngoHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
  //  ActivityMainBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


//        binding=ActivityMainBinding.inflate(getLayoutInflater());


    }
    private DatabaseReference mDatabase;
    ArrayList<FoodPost> food=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ngo_home, container, false);

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        mDatabase = reference.child("FoodPosts");
//        reference.child("Users").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
//                                                                                @Override
//                                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                                                    User user = snapshot.getValue(User.class);
//                                                                                    ngoCity[0] =user.getCity();
//
//                                                                                }
//
//                                                                                @Override
//                                                                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                                                                }
//                                                                            });


        final String[] ngoCity = new String[1];
        ngoData(new MyCallback() {
            @Override
            public void onCallback(Object obj) {


                User u = (User)obj;
                ngoCity[0] = u.getCity();

            }
        });


        final String[] status = new String[1];
        final String[]city = new String[1];

        final FoodPost[] post1 = new FoodPost[1];
        FoodPostsData(new MyCallback() {
            @Override
            public void onCallback(Object obj) {
                 post1[0] = (FoodPost)obj;
                status[0] = post1[0].getStatus();

                FoodProviderData(new MyCallback() {
                    @Override
                    public void onCallback(Object obj) {

                        User u = (User)obj;
                        if(u==null)
                        {
                            System.out.println("User Object returned by Food Provider Data is null");
                        }
                        else
                        {
                            System.out.println(" Value of food provider city is "+ u.getCity());
                        }
                        city[0]=u.getCity();

                    }
                }, post1[0]);
            }
        });







        System.out.println("City FOOD PROVIDER "+ city[0]);
        System.out.println("City ngo "+ ngoCity[0]);
        if(city[0].equalsIgnoreCase(ngoCity[0])&&status[0].equalsIgnoreCase("UNACCEPTED"))
        {
            food.add(new FoodPost());
        }

        ListAdapter listAdapter=new ListAdapter(getContext(),food);
        ListView l=  view.findViewById(R.id.listview);
        l.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        l.setClickable(true);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return view;
    }



    public void FoodPostsData(MyCallback myCallback){

        FirebaseDatabase.getInstance().getReference().child("FoodPosts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                FoodPost pst = null;
                for (DataSnapshot snapshot1 : datasnapshot.getChildren()) {



                    pst = snapshot1.getValue(FoodPost.class);







                }
                myCallback.onCallback(pst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void FoodProviderData(MyCallback myCallback,FoodPost post)
    {
        FirebaseDatabase.getInstance().getReference().child("Users").child(post.getPostedBy()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1 = snapshot.getValue(User.class);
                myCallback.onCallback(user1);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
        ;
    }
    public void ngoData(MyCallback myCallback) {
        FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                myCallback.onCallback(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}

 interface MyCallback{
    void onCallback(Object obj);
}