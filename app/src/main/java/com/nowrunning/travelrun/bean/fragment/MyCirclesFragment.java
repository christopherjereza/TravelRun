package com.nowrunning.travelrun.bean.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaygoo.widget.RangeSeekBar;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.ItemOffsetDecoration;
import com.nowrunning.travelrun.activity.RecyclerViewMargin;
import com.nowrunning.travelrun.activity.TravelPref;
import com.nowrunning.travelrun.activity.adapter.ChatAdapter;
import com.nowrunning.travelrun.activity.adapter.MyCirclesAdapter;
import com.nowrunning.travelrun.activity.adapter.RequestChatAdapter;
import com.nowrunning.travelrun.activity.util.ProfileList;
import com.nowrunning.travelrun.bean.Profile;

import java.io.File;
import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MyCirclesFragment extends Fragment{
    private View view;
    private Typeface fontSemi,fontRegular;
    private ProfileList nearbyList;
    private DatabaseReference myRef;
    private StorageReference storageRef;
    private StorageReference imagesRef;
    private StorageReference usersPic;
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    RecyclerView recyclerView,recyclerViewRequest;
    File localFile;
    private int heightImage;

    private LinearLayoutManager linearLayoutManager,horizontalLayoutManager;
    public MyCirclesFragment() {
    }
    Context context;
    private ProgressBar pg_icon;
    private TravelPref travelPref;
    private RelativeLayout rl_languaje_1,rl_languaje_2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_circles_fragment,container,false);
        pg_icon = (ProgressBar) view.findViewById(R.id.pg_icon);
        //pg_icon.setVisibility(View.VISIBLE);
        travelPref= new TravelPref(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        Typeface font = Typeface.createFromAsset(getActivity().getAssets(),  "proximasoft_medium.otf");
        fontRegular = Typeface.createFromAsset(getActivity().getAssets(),  "proximasoft_regular.otf");
        fontSemi = Typeface.createFromAsset(getActivity().getAssets(), "proximasoft_semibold.otf");

        ImageView iv_back = (ImageView)view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        TextView tv_title_1 = (TextView)view.findViewById(R.id.tv_title_1 );
        tv_title_1.setTypeface(fontSemi);

        storageRef = storage.getReference();
        imagesRef = storageRef.child("images");
        usersPic = imagesRef.child("users");
        nearbyList = new ProfileList();
        context = getActivity().getApplicationContext();
        getData();
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

        ChatAdapter chatAdap = new ChatAdapter(nearbyList.getProfileArrayList(), context, myRef, usersPic,localFile,heightImage);
        MyCirclesAdapter requestChatAdapter = new MyCirclesAdapter(nearbyList.getProfileArrayList(), context, myRef, usersPic,localFile,heightImage);

        /*recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(chatAdap );*/

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        horizontalLayoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(requestChatAdapter );

        //nearbyList.getProfileArrayList().remove(0);
        fillData(nearbyList.getProfileArrayList());
    }


    private LinearLayout layoutItem,layoutItem2,layoutItem3,layoutItem4,layoutItem5;
    private LinearLayout relLayout2;
    private RelativeLayout relLayout;

    private void fillData(ArrayList<Profile> pr)
    {
        layoutItem = (LinearLayout)view.findViewById(R.id.record_data);
        layoutItem2 = (LinearLayout)view.findViewById(R.id.record_data2);
        layoutItem3 = (LinearLayout)view.findViewById(R.id.record_data3);
        layoutItem4 = (LinearLayout)view.findViewById(R.id.record_data4);
        layoutItem5 = (LinearLayout)view.findViewById(R.id.record_data5);
        //layoutItem.removeAllViews();

        layoutItem.addView(fillList(3,pr,1));
        layoutItem2.addView(fillList(2,pr,2));

        layoutItem3.addView(fillList(4,pr,3));

        ArrayList<Profile> pr2=pr;
        pr2.remove(0);
        layoutItem4.addView(fillList(1,pr2,4));

        pr2.remove(0);
        layoutItem5.addView(fillList(3,pr2,5));
    }

    protected RelativeLayout fillList(int size,ArrayList<Profile> pr,int pos)
    {
        de.hdodenhof.circleimageview.CircleImageView imageView1;
        //i=i+1;
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.mycircles_adapter_rows3, null);

        RelativeLayout.LayoutParams params0 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relLayout= null;
        relLayout = (RelativeLayout)view.findViewById(R.id.nearbyPerson);
        params0.leftMargin=50;
        relLayout.setLayoutParams(params0);

        for (int i=0;i<size;i++)
        {
            imageView1 = new de.hdodenhof.circleimageview.CircleImageView(getActivity());
            imageView1.setBorderColor(Color.WHITE);
            imageView1.setBorderWidth(8);

            float pixels =  70 * context.getResources().getDisplayMetrics().density;
            float factor = context.getResources().getDisplayMetrics().density;
            int iwidth=(int)(80 * factor);
            int iheight=(int)(80 * factor);
            int counter=iwidth/2;

            //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iwidth, iheight);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(iwidth, iheight);
            params.width = iwidth;
            params.height = iheight;

            if (i==0)
            {
                params.bottomMargin=0;
                params.rightMargin=0;
                params.leftMargin=0;

                imageView1.setImageResource(R.drawable.test4);
                String surl=pr.get(i).getImageProfile();
                if (surl!=null && surl.length()>0)
                {
                    Uri uri = Uri.parse(surl);
                    StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());
                    Glide.with(context)
                            .load(ref)
                            //.apply(bitmapTransform(new CropCircleTransformation()))
                            .apply(new RequestOptions().override(iwidth , iheight))
                            .into(imageView1);
                }

            }
            else
            {
                params.rightMargin=0;
                params.leftMargin=counter*i;
               // params.leftMargin=-(counter);
                //params.leftMargin=-(counter/3);

                imageView1.setImageResource(R.drawable.test5);

                String surl1=pr.get(i).getImageProfile();
                if (surl1!=null && surl1.length()>0)
                {
                    Uri uri = Uri.parse(surl1);
                    StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());
                    Glide.with(context)
                            .load(ref)
                            //.apply(bitmapTransform(new CropCircleTransformation()))
                            .apply(new RequestOptions().override(iwidth , iheight))
                            .into(imageView1);
                }

                /*RelativeLayout rl_mes= (RelativeLayout)view.findViewById(R.id.rl_mes);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(iwidth, iheight);
                rl_mes.setGravity(Gravity.RIGHT);
                rl_mes.setHorizontalGravity(Gravity.RIGHT);
                rl_mes.setVerticalGravity(Gravity.RIGHT);
                rl_mes.setLayoutParams(params2);*/

                TextView tv_message= (TextView)view.findViewById(R.id.tv_message);
                ImageView iv_chat_message= (ImageView)view.findViewById(R.id.iv_chat_message);
                ImageView iv_chat_message2= (ImageView)view.findViewById(R.id.iv_chat_message2);
                tv_message.setTypeface(fontSemi);

                if (pos>2)
                {
                    tv_message.setVisibility(View.GONE);
                    iv_chat_message.setVisibility(View.GONE);
                    iv_chat_message2.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_message.setVisibility(View.VISIBLE);
                    iv_chat_message.setVisibility(View.VISIBLE);
                    iv_chat_message2.setVisibility(View.GONE);

                }
            }

            imageView1.setLayoutParams(params);
            imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView1.setImageResource(R.drawable.test4);


            relLayout.addView(imageView1);
        }

        return relLayout;
    }


    protected RelativeLayout fillList1(int size,ArrayList<Profile> pr,int pos)
    {
        de.hdodenhof.circleimageview.CircleImageView imageView1;
        //i=i+1;
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.mycircles_adapter_rows2, null);

        RelativeLayout.LayoutParams params0 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relLayout= null;
        relLayout = (RelativeLayout)view.findViewById(R.id.nearbyPerson);

        params0.leftMargin=50;
        relLayout.setLayoutParams(params0);

        for (int i=0;i<size;i++)
        {
            imageView1 = new de.hdodenhof.circleimageview.CircleImageView(getActivity());
            imageView1.setBorderColor(Color.WHITE);
            imageView1.setBorderWidth(8);

            float pixels =  70 * context.getResources().getDisplayMetrics().density;
            float factor = context.getResources().getDisplayMetrics().density;
            int iwidth=(int)(80 * factor);
            int iheight=(int)(80 * factor);
            int counter=iwidth/2;

            //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iwidth, iheight);
            params.width = iwidth;
            params.height = iheight;

                if (i==0)
                {
                    params.bottomMargin=0;
                    params.rightMargin=0;
                    params.leftMargin=0;
                    imageView1.setImageResource(R.drawable.test4);
                    String surl=pr.get(i).getImageProfile();
                    if (surl!=null && surl.length()>0)
                    {
                        Uri uri = Uri.parse(surl);
                        StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());
                        Glide.with(context)
                                .load(ref)
                                //.apply(bitmapTransform(new CropCircleTransformation()))
                                .apply(new RequestOptions().override(iwidth , iheight))
                                .into(imageView1);
                    }

                }
                else
                {
                    params.rightMargin=0;;
                    params.leftMargin=counter*i;

                    imageView1.setImageResource(R.drawable.test5);

                    String surl1=pr.get(i).getImageProfile();
                    if (surl1!=null && surl1.length()>0)
                    {
                        Uri uri = Uri.parse(surl1);
                        StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());
                        Glide.with(context)
                                .load(ref)
                                //.apply(bitmapTransform(new CropCircleTransformation()))
                                .apply(new RequestOptions().override(iwidth , iheight))
                                .into(imageView1);
                    }

                    TextView tv_message= (TextView)view.findViewById(R.id.tv_message);
                    ImageView iv_chat_message= (ImageView)view.findViewById(R.id.iv_chat_message);
                    ImageView iv_chat_message2= (ImageView)view.findViewById(R.id.iv_chat_message2);
                    tv_message.setTypeface(fontSemi);

                    if (pos>2)
                    {
                        tv_message.setVisibility(View.GONE);
                        iv_chat_message.setVisibility(View.GONE);
                        iv_chat_message2.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        tv_message.setVisibility(View.VISIBLE);
                        iv_chat_message.setVisibility(View.VISIBLE);
                        iv_chat_message2.setVisibility(View.GONE);

                    }
                }

            imageView1.setLayoutParams(params);
            imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView1.setImageResource(R.drawable.test4);


            relLayout.addView(imageView1);
        }

        return relLayout;
    }


    protected RelativeLayout fillList0(int i)
    {

        i=i+1;
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.mycircles_adapter_rows2, null);

        relLayout= null;
        relLayout = (RelativeLayout)view.findViewById(R.id.nearbyPerson);

        ImageView imageView1 = (ImageView)view.findViewById(R.id.profileImage);
        ImageView imageView2 = (ImageView)view.findViewById(R.id.profileImage2);
        ImageView imageView3 = (ImageView)view.findViewById(R.id.profileImage3);
        //ImageView imageView4 = (ImageView)view.findViewById(R.id.profileImage4);

        //tv_delete= (TextView)view.findViewById(R.id.tv_delete);

        float pixels =  70 * context.getResources().getDisplayMetrics().density;
        float factor = context.getResources().getDisplayMetrics().density;
        int iwidth=(int)(70 * factor);
        int iheight=(int)(70 * factor);

        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(70, 70);
        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iwidth, iheight);
        //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(iwidth, iheight);
        //params.addRule(RelativeLayout.CENTER_IN_PARENT);
        params.width = iwidth;
        params.height = iheight;
        //params.addRule(RelativeLayout.ALIGN_LEFT,i-1);

       /* if (i==1)
        {
            params.rightMargin=0;
            params.leftMargin=-0;
        }
        else
        {
            params.rightMargin=-45;
            params.leftMargin=-0;
        }*/

        //imageView1.setLayoutParams(params);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //imageView2.setLayoutParams(params);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //imageView3.setLayoutParams(params);
        imageView3.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //imageView4.setLayoutParams(params);
        //imageView4.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageView1.setImageResource(R.drawable.test4);
        imageView2.setImageResource(R.drawable.test3);
        imageView3.setImageResource(R.drawable.test5);

        //else if (i==4)
          //  imageView4.setImageResource(R.drawable.test5);

        return relLayout;
    }


    protected RelativeLayout fillList2(int i)
    {

            //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


        i=i+1;
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.mycircles_adapter_rows, null);

        relLayout= null;
        relLayout = (RelativeLayout)view.findViewById(R.id.nearbyPerson);

        ImageView imageView = (ImageView)view.findViewById(R.id.profileImage);
        imageView.setImageResource(R.drawable.test3);
        //tv_delete= (TextView)view.findViewById(R.id.tv_delete);

        float pixels =  70 * context.getResources().getDisplayMetrics().density;
        float factor = context.getResources().getDisplayMetrics().density;
        int iwidth=(int)(70 * factor);
        int iheight=(int)(70 * factor);

        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(70, 70);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iwidth, iheight);
        //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(iwidth, iheight);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        params.width = iwidth;
        params.height = iheight;

       // params.addRule(RelativeLayout.ALIGN_TOP, i-1);
        params.addRule(RelativeLayout.ALIGN_LEFT,i-1);
        //params.leftMargin= -50;
        //params.rightMargin=50;
        //imageView.setPadding(0, 0, 0, 0);
        imageView.setId(i);

        imageView.bringToFront();
        imageView.requestLayout();
        imageView.invalidate();
        imageView.setTranslationX(0);
        //moveToBack(imageView);
       // imageView.setLayoutParams(params);

        if (i==0)
        {
            params.rightMargin=0;
            params.leftMargin=-50;
        }
        else if (i==5)
        {
            params.rightMargin=0;
            params.leftMargin=-50;
        }
        else
        {
            params.rightMargin=0;
            params.leftMargin=-50;
            // params.leftMargin=-50;
        }

       if (i==6)
       {
        params.rightMargin=0;
        params.leftMargin=-50;
       }

        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (i==0)
            imageView.setImageResource(R.drawable.test3);
        else if (i==1)
            imageView.setImageResource(R.drawable.test5);
        else if (i==2)
            imageView.setImageResource(R.drawable.test4);
        else if (i==3)
            imageView.setImageResource(R.drawable.test3);
        else if (i==4)
            imageView.setImageResource(R.drawable.test4);
        else if (i==5)
            imageView.setImageResource(R.drawable.test5);

        //relLayout.addView(imageView);
        return relLayout;
    }

    private void moveToBack(View myCurrentView)
    {
        ViewGroup myViewGroup = ((ViewGroup) myCurrentView.getParent());
        int index = myViewGroup.indexOfChild(myCurrentView);
        for(int i = 0; i<index; i++)
        {
            myViewGroup.bringChildToFront(myViewGroup.getChildAt(i));
        }
    }

}

