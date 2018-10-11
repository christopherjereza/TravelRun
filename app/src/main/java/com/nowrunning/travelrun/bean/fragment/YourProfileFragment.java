package com.nowrunning.travelrun.bean.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.nowrunning.travelrun.activity.TravelPref;
import com.nowrunning.travelrun.activity.adapter.ViewPagerAdapter;
import com.nowrunning.travelrun.activity.adapter.ViewPagerAdapterYourP;
import com.nowrunning.travelrun.activity.util.Constant;
import com.nowrunning.travelrun.activity.util.ProfileList;
import com.nowrunning.travelrun.bean.Profile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class YourProfileFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private View view;
    private DatabaseReference myRef;
    RecyclerView recyclerView;
    private List<Profile> taskDesList;
    public YourProfileFragment() {
    }
    private ProfileList nearbyList;

    private ViewPagerAdapterYourP mAdapter;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;

    Context context;
    private StorageReference storageRef;
    private StorageReference imagesRef;
    private StorageReference usersPic;
    File localFile;
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    private ProgressBar pg_icon;
    private RelativeLayout rl_request_sent;
    private int heightImage;

    private int[] mImageResources1 = {R.drawable.test3,R.drawable.test4,R.drawable.test5};
    private int dotsCount;
    private ImageView[] dots;
    private TravelPref travelPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.your_profile,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        pg_icon = (ProgressBar) view.findViewById(R.id.pg_icon);
        pg_icon.setVisibility(View.VISIBLE);
        travelPref= new TravelPref(getActivity());
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
        context = getActivity().getApplicationContext();
        getData();
        return view;
    }

    private StorageReference usersPic2;
    private StorageReference pullUserUnique;
    private StorageReference singleImgRef;
    final long ONE_MEGABYTE = 1024 * 1024;
    File localFile2;

    private void getData()
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
                                setFont(view);
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



    private void setFont(View holder)
    {
        TextView tv_name = (TextView)holder.findViewById(R.id.tv_name);
        TextView tv_from = (TextView)holder.findViewById(R.id.tv_from);
        TextView tv_age = (TextView)holder.findViewById(R.id.tv_age);

        ImageView img_status = (ImageView)holder.findViewById(R.id.iv_status);
        TextView tv_footer1 = (TextView)holder.findViewById(R.id.tv_footer1);
        TextView tv_footer2= (TextView)holder.findViewById(R.id.tv_footer2);
        TextView tv_posted = (TextView)holder.findViewById(R.id.tv_posted);

        if (travelPref.getStatusTravel())
            img_status.setImageResource(R.drawable.travelling);
        else
            img_status.setImageResource(R.drawable.hosting_icon);

        ImageView chatRequest = (ImageView)holder.findViewById(R.id.chatRequest);
        chatRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        String name="James";
        String age="27";
        String from="From Santa Rosa, CA";
        String posted=getString(R.string.label_posted);
        String whatsapp=getString(R.string.label_footer1);
        String bio=getString(R.string.label_footer2);

        if (nearbyList!=null && nearbyList.getProfileArrayList()!=null && nearbyList.getProfileArrayList().size()>0)
        {
            name=nearbyList.getProfileArrayList().get(0).getName();
            age=String.valueOf(nearbyList.getProfileArrayList().get(0).getAge());
            from=nearbyList.getProfileArrayList().get(0).getLocationName();
            whatsapp=nearbyList.getProfileArrayList().get(0).getWhatsUp();
            bio=nearbyList.getProfileArrayList().get(0).getBio();
        }

        Typeface font = Typeface.createFromAsset(context.getAssets(), "proximasoft_semibold.otf");
        Typeface fontProximaRegular = Typeface.createFromAsset(context.getAssets(), "proximasoft_regular.otf");
        Typeface font3 = Typeface.createFromAsset(context.getAssets(), "proximasoft_medium.otf");

        tv_name.setTypeface(fontProximaRegular);
        tv_name.setTextSize(29);
        tv_posted.setTextSize(12);
        tv_age.setTypeface(fontProximaRegular);
        tv_age.setTextSize(29);
        tv_footer1.setTextSize(17);
        tv_footer2.setTextSize(17);

        tv_footer2.setText(bio);
        tv_footer1.setText(whatsapp);
        tv_name.setText(""+name+",");
        tv_age.setText(""+age);
        tv_posted.setText(""+posted);
        tv_from.setText(""+from);


        tv_footer2.setTypeface(fontProximaRegular);
        tv_posted.setTypeface(fontProximaRegular);
        tv_footer1.setTypeface(fontProximaRegular);
        tv_from.setTypeface(fontProximaRegular);
        tv_from.setTypeface(fontProximaRegular);
        tv_from.setTextSize(14);

    }

    public void setReference() {
        // view = LayoutInflater.from(this).inflate(R.layout.viewpager_demo,container);

        intro_images = (ViewPager) view.findViewById(R.id.pager_introduction);
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

        }


        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
        mAdapter = new ViewPagerAdapterYourP(getActivity(), mImageResources1,imageArray,heightImage,this);

        double dheight=(heightImage* Constant.height_profile);
        int iheight=(int)dheight;

        ConstraintLayout body =(ConstraintLayout)view.findViewById(R.id.body);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iheight);
        body.setLayoutParams(params);


        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);
        setUiPageViewController();
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        if (position + 1 == dotsCount) {
            // btnNext.setVisibility(View.GONE);
            // btnFinish.setVisibility(View.VISIBLE);
        } else {
            // btnNext.setVisibility(View.VISIBLE);
            // btnFinish.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void exit()
    {

    }
}
