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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.adapter.ChatAdapter;
import com.nowrunning.travelrun.activity.adapter.ContactAdapter;
import com.nowrunning.travelrun.activity.adapter.ProductFilterRecyclerViewAdapter;
import com.nowrunning.travelrun.activity.adapter.RequestChatAdapter;
import com.nowrunning.travelrun.activity.util.ProfileList;
import com.nowrunning.travelrun.bean.FilterModel;
import com.nowrunning.travelrun.bean.Profile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactSelectFragment extends Fragment {
    private View view;
    private DatabaseReference myRef;
    RecyclerView recyclerView,brandRecyclerView,recyclerViewRequest;
    private List<Profile> taskDesList;
    public ContactSelectFragment() {
    }
    private ProfileList nearbyList;
    Context context;
    private StorageReference storageRef;
    private StorageReference imagesRef;
    private StorageReference usersPic;
    private EditText searchEditText;
    File localFile;
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    private ProgressBar pg_icon;
    private int heightImage;
    private Button bt_create;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact,container,false);

        ImageView iv_back = (ImageView)view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        brandRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        bt_create= (Button) view.findViewById(R.id.bt_create);
        searchEditText= (EditText) view.findViewById(R.id.searchEditText);

        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                //if (s!=null && s.length()>0)
                //  callVolley(s.toString());

                    if  (s!=null && s.length()>0)
                    {
                        try {
                            getData2(s.toString());
                            //resultBean_ = UserDatabase.instance(MainActivity.this).groupALLfilter(s.toString());
                            //getData(resultBean_);
                        }catch (Exception ex)
                        {
                            Log.e("Error",ex.getMessage());
                        }
                    }
                    else
                    {
                        getData();
                    }


            }

            @Override
            public void afterTextChanged(Editable s) {
                //menu_cancel.setVisibility(View.VISIBLE);

                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

        });


        //RecyclerView layout manager
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());
        brandRecyclerView.setLayoutManager(recyclerLayoutManager);
        //RecyclerView item decorator
        //DividerItemDecoration dividerItemDecoration =new DividerItemDecoration(brandRecyclerView.getContext(),recyclerLayoutManager.getOrientation());
        //brandRecyclerView.addItemDecoration(dividerItemDecoration);

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
        Typeface fontRegular = Typeface.createFromAsset(getActivity().getAssets(),  "proximasoft_regular.otf");
        Typeface fontSemi = Typeface.createFromAsset(getActivity().getAssets(), "proximasoft_semibold.otf");

        bt_create.setTypeface(fontRegular);
        searchEditText.setTypeface(fontRegular);
        getData();

        context = getActivity().getApplicationContext();

        return view;
    }

    private void getData2(String text)
    {
        getData(text);
    }

    private String getCapsSentences(String tagName) {
        String[] splits = tagName.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splits.length; i++) {
            String eachWord = splits[i];
            if (i > 0 && eachWord.length() > 0) {
                sb.append(" ");
            }
            String cap = eachWord.substring(0, 1).toUpperCase()
                    + eachWord.substring(1);
            sb.append(cap);
        }
        return sb.toString();
    }

    private void getData(String text)
    {
        text=getCapsSentences(text);
        nearbyList = new ProfileList();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //myRef = database.child("dictionary-605f1/");
        myRef = database.child("users/");
        //Query myMostViewedPostsQuery = database.child("users/").orderByChild("name").startAt(text).endAt(text+"\uf8ff");
        Query myMostViewedPostsQuery = database.child("users/").orderByChild("name").startAt(text).endAt(text+"\uf8ff");


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

                        String name=(String) userSnapshot.child("profilePicID").getValue();
                        singleImgRef = pullUserUnique.child(name);
                        String file=singleImgRef.toString();
                        inProf.setImageProfile(file);

                        if (nearbyList!=null && nearbyList.getProfileArrayList().size()>0)
                        {
                            for (int i=0;i>nearbyList.getProfileArrayList().size();i++)
                            {
                                Profile prof=nearbyList.getProfileArrayList().get(i);
                                if (prof.getUniqueID().equalsIgnoreCase(inProf.getUniqueID()))
                                {
                                }
                                else
                                {
                                    nearbyList.addNewProfile(inProf);
                                }
                            }
                        }
                        else
                        {
                            nearbyList.addNewProfile(inProf);
                        }
                       // nearbyList.addNewProfile(inProf);

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
        nearbyList = new ProfileList();
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

                            //setUniqueID
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

    private LinearLayoutManager linearLayoutManager;

    private void getDataUi()
    {
        ContactAdapter chatAdap = new ContactAdapter(nearbyList.getProfileArrayList(), context, myRef, usersPic,localFile,heightImage);

        brandRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(brandRecyclerView.getContext(),linearLayoutManager.getOrientation());
        //brandRecyclerView.addItemDecoration(dividerItemDecoration);
        brandRecyclerView.setAdapter(chatAdap );

    }



}
