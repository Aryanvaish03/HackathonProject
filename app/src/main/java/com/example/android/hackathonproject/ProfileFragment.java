package com.example.android.hackathonproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.hackathonproject.Models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class ProfileFragment extends Fragment {

    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    ImageView profileImage;

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_profile, container, false);



        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();


        EditText etUserName = view.findViewById(R.id.etUserName);
        EditText etAbout = view.findViewById(R.id.etAbout);
        EditText etAddress = view.findViewById(R.id.etAddress);
        EditText etContact = view.findViewById(R.id.etContact);
        Button btnEdit = view.findViewById(R.id.btnEdit);
        Button btnSave = view.findViewById(R.id.btnSave);

        database.getReference().child("Users").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String username , about, address, contact;

                username = user.getUserName();
                address= user.getAddress();
                contact = user.getPhoneNumber();
                about = user.getAbout();


                etUserName.setText(username);
                etAddress.setText(address);
                etContact.setText(contact);
                if(user.getAbout()!=null)
                {
                    etAbout.setText(about);
                }
                else
                {
                    etAbout.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        etUserName.setEnabled(false);
        etAbout.setEnabled(false);
        etAddress.setEnabled(false);
        etContact.setEnabled(false);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etAbout.setEnabled(true);
                etUserName.setEnabled(true);
                etAddress.setEnabled(true);
                etContact.setEnabled(true);
                etAbout.setHint("Enter about yourself");
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String new_userName, new_About,new_Address,new_Contact;
                        new_userName= etUserName.getText().toString();
                        new_About= etAbout.getText().toString();
                        new_Address=etAddress.getText().toString();
                        new_Contact=etContact.getText().toString();



                        if(TextUtils.isEmpty(new_userName) )
                        {
                            Toast.makeText(getContext(),"User Name is required",Toast.LENGTH_SHORT).show();
                            return;
                        }


                        if(TextUtils.isEmpty(new_Address) )
                        {
                            Toast.makeText(getContext(),"Address is required",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(TextUtils.isEmpty(new_Contact) )
                        {
                            Toast.makeText(getContext(),"Contact no. is required",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        etUserName.setEnabled(false);
                        etAbout.setEnabled(false);
                        etAddress.setEnabled(false);
                        etContact.setEnabled(false);

                        etUserName.setText(new_userName);
                        etAbout.setText(new_About);
                        etAddress.setText(new_Address);
                        etContact.setText(new_Contact);

                        HashMap<String, Object> obj = new HashMap<>();
                        obj.put("userName",new_userName);
                        obj.put("about",new_About);
                        obj.put("Address",new_Address);
                        obj.put("phoneNumber",new_Contact);
                        database.getReference().child("Users").child(auth.getUid()).updateChildren(obj);
                        etAbout.setHint("");

                    }
                });
            }
        });

        profileImage = view.findViewById(R.id.profile_image);
        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        Picasso.get().load(user.getProfilepic()).placeholder(R.drawable.user_avatar).into(profileImage);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        ImageView plus = view.findViewById(R.id.plus_sign);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                //Launch activity to get result
                mGetImage.launch(intent);

            }
        });


        return view;
    }

    ActivityResultLauncher<Intent> mGetImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        profileImage.setImageURI(uri);
                        StorageReference reference = storage.getReference().child("Profile Images").child(auth.getUid());
                        reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        database.getReference().child("Users").child(auth.getUid()).child("profilepic").setValue(uri.toString());
                                    }
                                });
                                Toast.makeText(getContext(), "uploaded successfully", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }

            });

}