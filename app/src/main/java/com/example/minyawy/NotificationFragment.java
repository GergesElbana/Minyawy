package com.example.minyawy;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {
   RecyclerView prefRecy;
   Context context;
    private DatabaseReference databaseReference;
    private List<FetchPlaceName> placesListData;
    private Notification_Adapter notification_adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        prefRecy=(RecyclerView)v.findViewById(R.id.notiRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        prefRecy.setLayoutManager(linearLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference("favouriteList");
        placesListData=new ArrayList<>();
        getfirebasedata();
        return v;
    }
    private void getfirebasedata(){
//        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
//        currentFirebaseUser.getUid();
//        String m= currentFirebaseUser.getUid();

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String currentUser=user.getUid();

        Query query=databaseReference.child(currentUser);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data:snapshot.getChildren()){
                    FetchPlaceName fetchPlaceName=new FetchPlaceName();
                    fetchPlaceName.setName(data.child("name").getValue().toString());
                    fetchPlaceName.setDescrip(data.child("descrip").getValue().toString());
                    fetchPlaceName.setPhoto(data.child("photo").getValue().toString());
                    placesListData.add(fetchPlaceName);

                }
                notification_adapter = new Notification_Adapter(getActivity(), placesListData);
                prefRecy.setAdapter(notification_adapter);
                notification_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show();

            }
        });
    }


}