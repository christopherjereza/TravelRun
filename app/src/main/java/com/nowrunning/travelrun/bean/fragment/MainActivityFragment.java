package com.nowrunning.travelrun.bean.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.adapter.NearbyAdapter;
import com.nowrunning.travelrun.activity.adapter.NearbyAdapter2;
import com.nowrunning.travelrun.activity.util.Constant;
import com.nowrunning.travelrun.activity.util.ProfileList;
import com.nowrunning.travelrun.bean.Profile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private View view;
    private DatabaseReference myRef;
    RecyclerView recyclerView;
    private List<Profile> taskDesList;
    public MainActivityFragment() {
    }
    private ProfileList nearbyList;
    Context context;
    private StorageReference storageRef;
    private StorageReference imagesRef;
    private StorageReference usersPic;
    File localFile;
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    private ProgressBar pg_icon;
    private RelativeLayout rl_request_sent;
    private int heightImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        pg_icon = (ProgressBar) view.findViewById(R.id.pg_icon);
        pg_icon.setVisibility(View.VISIBLE);

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


        rl_request_sent = (RelativeLayout)view.findViewById(R.id.rl_request_sent);

        TextView tv_title_1 = (TextView)view.findViewById(R.id.tv_title_1);
        TextView tv_people = (TextView)view.findViewById(R.id.tv_people);
        TextView tv_sent_request= (TextView)view.findViewById(R.id.tv_sent_request);
        tv_sent_request.setTypeface(font);
        tv_sent_request.setTextSize(17);

        tv_title_1.setTypeface(font);
        tv_people.setTypeface(font);
        tv_people.setTextSize(21);
        tv_title_1.setTextSize(21);

        getData();
        context = getActivity().getApplicationContext();

        return view;
    }


    private void getData2()
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //myRef = database.child("dictionary-605f1/");
        //.orderByChild("position")
        myRef = database.child("users/");
       // getDataAll();
    }

    private void getData()
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //myRef = database.child("dictionary-605f1/");
        //.orderByChild("position")


        //myRef = database.child("users/").orderByChild("position");
        //getDataAll();

        Query myMostViewedPostsQuery = database.child("users/").orderByChild("position");

        myMostViewedPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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
                        inProf.setPosition(Integer.valueOf((String)userSnapshot.child("position").getValue().toString()));
                        pullUserUnique = usersPic.child(inProf.getUniqueID());

                        /*for(int ii = 0; ii < userSnapshot.child("picturesID").getChildrenCount(); ii++) {

                            String name=(String) userSnapshot.child("picturesID").child((Integer.toString(ii))).getValue();
                            inProf.addNewPictName(name);

                            if (name!=null && name.length()>0)
                            {
                                singleImgRef = pullUserUnique.child(name);
                                String file=singleImgRef.toString();
                                inProf.setImageProfile(file);
                            }
                            else
                            {
                                inProf.setImageProfile("");
                            }
                        }*/
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
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("Tag", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

    }

    private StorageReference usersPic2;
    private StorageReference pullUserUnique;
    private StorageReference singleImgRef;
    final long ONE_MEGABYTE = 1024 * 1024;
    File localFile2;

    private void getDataAll2()
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

                            /*for(int ii = 0; ii < userSnapshot.child("picturesID").getChildrenCount(); ii++) {

                                String name=(String) userSnapshot.child("picturesID").child((Integer.toString(ii))).getValue();
                                inProf.addNewPictName(name);

                                if (name!=null && name.length()>0)
                                {
                                    singleImgRef = pullUserUnique.child(name);
                                    String file=singleImgRef.toString();
                                    inProf.setImageProfile(file);
                                }
                                else
                                {
                                    inProf.setImageProfile("");
                                }
                            }*/
                            inProf.setImageProfile((String) userSnapshot.child("profilePicID").getValue());
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


    private void getDataUi()
    {

        NearbyAdapter nearAdap = new NearbyAdapter(nearbyList.getProfileArrayList(), context, myRef, usersPic,localFile,heightImage,this);
        NearbyAdapter2 nearAdap2 = new NearbyAdapter2(nearbyList.getProfileArrayList(), context, myRef, usersPic,localFile,heightImage,this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(nearAdap2);

       /* recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //Your action here
                rl_request_sent.setVisibility(View.GONE);
            }

        });*/

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Scrolling up
                   // rl_request_sent.setVisibility(View.GONE);
                } else {
                    // Scrolling down
                   // rl_request_sent.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                    Log.i("TAg","");
                    rl_request_sent.setVisibility(View.GONE);
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                    Log.i("TAg","");
                    rl_request_sent.setVisibility(View.GONE);
                } else {
                    // Do something
                    Log.i("TAg","");
                    rl_request_sent.setVisibility(View.GONE);
                }
            }
        });
    }

    private void changePositionRequestChat(int position, String key)
    {
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference("users/");

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        myRef = database.child("users/"+key);
        Map newUserData = new HashMap();
        int iposition=position-1;
        newUserData.put("position", iposition);

        //newUserData.put("jugados", 0);
        myRef.updateChildren(newUserData);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Tag","");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("DTS", "loadPost:onCancelled", databaseError.toException());
                pg_icon.setVisibility(View.GONE);
            }


        };
        mFirebaseDatabase.addValueEventListener(postListener);

    }

    public void getChatRequest(String name, int positionValue, String key)
    {
        Log.i("tag","");
        changePositionRequestChat(positionValue,key);
        nearbyList = new ProfileList();
        //getData();
        rl_request_sent.setVisibility(View.VISIBLE);
    }
}
