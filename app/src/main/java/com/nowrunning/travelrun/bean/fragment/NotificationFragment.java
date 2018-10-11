package com.nowrunning.travelrun.bean.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.SwitchCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.adapter.ChatAdapter;
import com.nowrunning.travelrun.activity.adapter.NotificationAdapter;
import com.nowrunning.travelrun.activity.adapter.RequestChatAdapter;
import com.nowrunning.travelrun.activity.util.ProfileList;
import com.nowrunning.travelrun.bean.Profile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NotificationFragment extends Fragment {
    private View view;
    private DatabaseReference myRef;
    RecyclerView recyclerViewRequest;
    private List<Profile> taskDesList;
    public NotificationFragment() {
    }
    private ProfileList nearbyList;
    Context context;
    private StorageReference storageRef;
    private StorageReference imagesRef;
    private StorageReference usersPic;
    File localFile;
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    private ProgressBar pg_icon;
    private int heightImage;
    private TextView tv_notification_status;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification,container,false);
        recyclerViewRequest= (RecyclerView) view.findViewById(R.id.recyclerView2);
        pg_icon = (ProgressBar) view.findViewById(R.id.pg_icon);
        pg_icon.setVisibility(View.VISIBLE);

        ImageView iv_back = (ImageView)view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        nearbyList = new ProfileList();
        storageRef = storage.getReference();
        imagesRef = storageRef.child("images");

        //this one is going to change,
        usersPic = imagesRef.child("users");

        DisplayMetrics dm= new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        heightImage=dm.heightPixels;

        try {
            localFile = File.createTempFile("images", "bmp");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Typeface font = Typeface.createFromAsset(getActivity().getAssets(),  "proximasoft_medium.otf");
        Typeface fontRegular = Typeface.createFromAsset(getActivity().getAssets(),  "proximasoft_regular.otf");
        Typeface fontSemi = Typeface.createFromAsset(getActivity().getAssets(), "proximasoft_semibold.otf");
        TextView tv_notification = (TextView)view.findViewById(R.id.tv_notification);
        TextView tv_leave_group= (TextView)view.findViewById(R.id.tv_leave_group);
        tv_notification_status= (TextView)view.findViewById(R.id.tv_notification_status);

        SwitchCompat sc_notification= (SwitchCompat)view.findViewById(R.id.sc_notification);
        sc_notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked) {
                    //if 'isChecked' is true do whatever you need...
                    tv_notification_status.setText(R.string.label_on);
                }
                else {
                    tv_notification_status.setText(R.string.label_off);

                }
            }

        });


        tv_notification.setTypeface(font);
        tv_leave_group.setTypeface(font);
        tv_notification_status.setTypeface(font);
        getData();
        context = getActivity().getApplicationContext();

        return view;
    }


    private void getData()
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //myRef = database.child("dictionary-605f1/");
        myRef = database.child("users/");
        getDataAll();
    }

    private StorageReference usersPic2;
    private StorageReference pullUserUnique;
    private StorageReference singleImgRef;
    final long ONE_MEGABYTE = 1024 * 1024;
    File localFile2;

    private void getDataAll()
    {

        try{

            ValueEventListener personListener = new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try
                    {
                        for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                            Profile inProf = new Profile();
                            int intCast =0;
                            String stringCast = "";

                            inProf.setName((String) userSnapshot.child("name").getValue());
                            inProf.setUniqueID(((String) userSnapshot.getKey()));
                            inProf.setWhatsUp((String) userSnapshot.child("whats").getValue());
                            inProf.setBio((String) userSnapshot.child("bio").getValue());
                            inProf.setLocationName((String) userSnapshot.child("location").getValue());
                            inProf.setDistance((String) userSnapshot.child("distance").getValue());
                            inProf.setStatus((String) userSnapshot.child("status").getValue());
                            inProf.setAge(Integer.valueOf((String)userSnapshot.child("age").getValue().toString()));
                            pullUserUnique = usersPic.child(inProf.getUniqueID());


                            String name=(String) userSnapshot.child("profilePicID").getValue();
                            singleImgRef = pullUserUnique.child(name);
                            String file=singleImgRef.toString();
                            inProf.setImageProfile(file);


                            nearbyList.addNewProfile(inProf);
                        }

                    }catch(Exception e)
                    {
                        Log.i("Error: ",e.getMessage());
                    }
                    finally {

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                try{

                                    pg_icon.setVisibility(View.GONE);
                                    getDataUi();

                                }catch(Exception e)
                                {
                                    Log.i("Error: ",e.getMessage());
                                }


                            }
                        }, 300);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            myRef.addValueEventListener(personListener);


        }catch (Exception ex)
        {
            Log.e("Tag",ex.getMessage());
        }

    }

    private LinearLayoutManager linearLayoutManager,horizontalLayoutManager;

    private void getDataUi()
    {

        NotificationAdapter requestChatAdapter = new NotificationAdapter(nearbyList.getProfileArrayList(), context, myRef, usersPic,localFile,heightImage);
        recyclerViewRequest.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        horizontalLayoutManager = new LinearLayoutManager(recyclerViewRequest.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRequest.setLayoutManager(horizontalLayoutManager);

        recyclerViewRequest.setAdapter(requestChatAdapter );



    }



}
