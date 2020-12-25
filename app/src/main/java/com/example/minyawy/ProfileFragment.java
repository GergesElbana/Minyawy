package com.example.minyawy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private  String name4;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView userName,userEmail;
    private CircleImageView profilePhoto;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private final String users="user";
    private FirebaseAuth mAuth;
    private FirebaseUser currentuser;

    public ProfileFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        userName=(TextView)v.findViewById(R.id.Username);
        userEmail=(TextView)v.findViewById(R.id.UserEmail);
        profilePhoto= (CircleImageView) v.findViewById(R.id.ProfilePhoto);
        database=FirebaseDatabase.getInstance();
        userRef=database.getReference(users);

        mAuth=FirebaseAuth.getInstance();
        currentuser=mAuth.getCurrentUser();
         name4=currentuser.getEmail();

       /* Bundle bundle=getArguments();
        name4=bundle.getString("email");*/

    //    Log.v("ger",name4);
        //if(this.getArguments() != null){
           // Bundle bundle=this.getArguments();
            //name4=bundle.getString("email0");
          /*  name4 =this.getArguments().getString("email0");
            Log.v("ger",name4);*/
       // }else name4="very@gmail.com";*/
   ////////////////////////////
     /*   Login activity = (Login) getActivity();
        name4 =activity.getMyData();*/



        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    if (ds.child("username").getValue().equals(name4)){
                        userEmail.setText(ds.child("username").getValue(String.class));
                        userName.setText(ds.child("name").getValue(String.class));


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
     /*   Bundle bundle=getArguments();
        String name=bundle.getString("name");
        String email=bundle.getString("email");
        userName.setText(name);
        userEmail.setText(email);*/



        return v;
    }
}