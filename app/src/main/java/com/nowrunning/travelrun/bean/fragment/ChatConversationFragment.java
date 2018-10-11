package com.nowrunning.travelrun.bean.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.adapter.ChatConversationAdapter;
import com.nowrunning.travelrun.activity.adapter.NotificationAdapter;
import com.nowrunning.travelrun.activity.adapter.ViewPagerAdapterYourP;
import com.nowrunning.travelrun.activity.util.Constant;
import com.nowrunning.travelrun.activity.util.ProfileList;
import com.nowrunning.travelrun.bean.ChatBean;
import com.nowrunning.travelrun.bean.Profile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChatConversationFragment extends Fragment {
    private View view;
    private DatabaseReference myRef;
    RecyclerView recyclerViewRequest;
    private List<Profile> taskDesList;
    public ChatConversationFragment() {
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
    private TextView tv_notification_status,tv_name_prof;
    private ArrayList<ChatBean> arrayCB;
    private EditText tv_edit;
    private ImageView iv_profile;
    private Typeface fontSemi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat_conv,container,false);
        recyclerViewRequest= (RecyclerView) view.findViewById(R.id.recyclerView2);
        pg_icon = (ProgressBar) view.findViewById(R.id.pg_icon);
        pg_icon.setVisibility(View.GONE);

        ImageView iv_back = (ImageView)view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        tv_edit = (EditText)view.findViewById(R.id.tv_edit);
        iv_profile= (ImageView)view.findViewById(R.id.iv_profile);
        tv_name_prof= (TextView)view.findViewById(R.id.tv_name_prof);

        ImageView iv_send = (ImageView)view.findViewById(R.id.iv_send);
        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ChatBean cb= new ChatBean();
                cb.setMessage(tv_edit.getText().toString());
                cb.setStatus(0);
                cb.setUserID("1");
                arrayCB.add(cb);
                tv_edit.setText("");
                getDataUi();

                linearLayoutManager.scrollToPosition(arrayCB.size()- 1);
            }
        });

        setMessage();


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
        fontSemi = Typeface.createFromAsset(getActivity().getAssets(), "proximasoft_semibold.otf");


       // getData();
        context = getActivity().getApplicationContext();
        getDataImage();
        getDataUi();
        return view;
    }


    private void setMessage()
    {
        arrayCB= new ArrayList<ChatBean>();
        ChatBean cb= new ChatBean();
        cb.setMessage("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore.");
        cb.setStatus(0);
        cb.setUserID("1");
        arrayCB.add(cb);

        cb= new ChatBean();
        cb.setMessage("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        cb.setStatus(1);
        cb.setUserID("1");
        arrayCB.add(cb);

        cb= new ChatBean();
        cb.setMessage("Sed ut perspiciatis unde omnis.");
        cb.setStatus(0);
        cb.setUserID("1");
        arrayCB.add(cb);

        cb= new ChatBean();
        cb.setMessage("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore2.");
        cb.setStatus(0);
        cb.setUserID("1");
        arrayCB.add(cb);

        cb= new ChatBean();
        cb.setMessage("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        cb.setStatus(1);
        cb.setUserID("1");
        arrayCB.add(cb);

        cb= new ChatBean();
        cb.setMessage("Sed ut perspiciatis unde omnis.");
        cb.setStatus(0);
        cb.setUserID("1");
        arrayCB.add(cb);
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

    private LinearLayoutManager linearLayoutManager;


    private void getDataUi()
    {

        ChatConversationAdapter requestChatAdapter = new ChatConversationAdapter(arrayCB, context, myRef, usersPic,localFile,heightImage);
        recyclerViewRequest.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewRequest.getContext(),linearLayoutManager.getOrientation());
        //recyclerViewRequest.addItemDecoration(dividerItemDecoration);
        recyclerViewRequest.setLayoutManager(linearLayoutManager);
        recyclerViewRequest.setAdapter(requestChatAdapter );

    }

    public void setReference() {
        // view = LayoutInflater.from(this).inflate(R.layout.viewpager_demo,container);

        //intro_images = (ViewPager) view.findViewById(R.id.pager_introduction);
        //btnNext = (ImageButton) view.findViewById(R.id.btn_next);
        //btnFinish = (ImageButton) view.findViewById(R.id.btn_finish);

        Log.i("Tag",""+nearbyList.getProfileArrayList());
        ArrayList<String> imageArray= new ArrayList<String> ();
        if (nearbyList!=null && nearbyList.getProfileArrayList()!=null && nearbyList.getProfileArrayList().size()>0 &&  nearbyList.getProfileArrayList().get(0).getPictNames()!=null)
        {
            for (int i=0; i<nearbyList.getProfileArrayList().get(0).getPictNames().size();i++)
            {
                String name=nearbyList.getProfileArrayList().get(0).getPictNames().get(i);
                singleImgRef = pullUserUnique.child(name);
                String file=singleImgRef.toString();
                imageArray.add(file);
            }

            tv_name_prof.setText(nearbyList.getMyProfile().getName()+", "+nearbyList.getMyProfile().getAge());
            tv_name_prof.setTypeface(fontSemi);

        }

       // imageArray.get(0);

        //pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
        //mAdapter = new ViewPagerAdapterYourP(getActivity(), mImageResources1,imageArray,heightImage,this);



        float pixels =  55 * context.getResources().getDisplayMetrics().density;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(80, 80);
        float factor = context.getResources().getDisplayMetrics().density;
        params.width = (int)(55 * factor);
        params.height = (int)(55 * factor);

        Uri uri = Uri.parse(imageArray.get(0));
        StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());


        RelativeLayout.LayoutParams params_i = new RelativeLayout.LayoutParams(75, 75);
        params_i.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        iv_profile.setLayoutParams(params_i);
        iv_profile.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(context)
                .load(ref)
                .apply(new RequestOptions().override(params.width , params.height))
                .apply(bitmapTransform(new CropCircleTransformation()))
                .into(iv_profile);

    }


    private void getDataImage()
    {
        nearbyList = new ProfileList();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        Query myMostViewedPostsQuery = database.child("users/").child("1");
        myMostViewedPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot userSnapshot) {
                try
                {
                    //for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
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

                    for(int ii = 0; ii < userSnapshot.child("picturesID").getChildrenCount(); ii++) {

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
                    }
                    nearbyList.addNewProfile(inProf);
                    //}

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
                                //  getDataUi();
                                setReference();
                                //setFont(view);
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
}
