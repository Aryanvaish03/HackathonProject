package com.example.android.hackathonproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.hackathonproject.R;


public class ngo_Fragment_ManageRequests extends Fragment {


    public ngo_Fragment_ManageRequests() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ngo___manage_requests, container, false);
    }
}