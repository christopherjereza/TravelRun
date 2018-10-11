package com.nowrunning.travelrun.activity.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.util.Constant;
import com.nowrunning.travelrun.activity.util.FirebaseImageLoader;
import com.nowrunning.travelrun.bean.Profile;
import com.nowrunning.travelrun.bean.fragment.MainActivityFragment;


import java.io.File;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by Austin on 12/5/2017.
 */
public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.NearbyHolder>{
    private ArrayList<Profile> list;
    private Context context;
    private StorageReference usersPic;
    NearbyAdapter.NearbyHolder holder;
    File localFile;
    int position;
    int height;
    private MainActivityFragment fragment;

    public class NearbyHolder extends RecyclerView.ViewHolder{
        protected FrameLayout frame;
        protected com.nowrunning.travelrun.activity.ProportionalImageView profPic;
        protected RelativeLayout containedLayout;
        protected CardView cv_1;

        public NearbyHolder(View itemView) {
            super(itemView);
            this.containedLayout = (RelativeLayout) itemView.findViewById(R.id.nearbyPerson);
            this.profPic = (com.nowrunning.travelrun.activity.ProportionalImageView) itemView.findViewById(R.id.profileImage);
            this.frame = (FrameLayout) itemView.findViewById(R.id.frame);

            /*double dheight2=(height* Constant.height_profileLayout);
            int iheight2=(int)dheight2;
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2200);
            containedLayout.setLayoutParams(params2);*/
        }

    }
    public NearbyAdapter(ArrayList<Profile> list, Context context, DatabaseReference myRef, StorageReference usersPicIn, File localFileIn, int height,MainActivityFragment fragment
            ) {
        this.list = list;
        this.context = context;
        this.usersPic = usersPicIn;
        this.localFile = localFileIn;
        this.height=height;
        this.fragment=fragment;
    }


    @Override
    public NearbyAdapter.NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_main, null);
        NearbyHolder mh = new NearbyHolder(v);
        return mh;

        //return null;
    }

    @Override
    public void onBindViewHolder(NearbyAdapter.NearbyHolder inHolder, final int positionIn) {
        this.position =positionIn;
        this.holder = inHolder;

        TextView tv_name = (TextView)holder.containedLayout.findViewById(R.id.tv_name);
        TextView tv_from = (TextView)holder.containedLayout.findViewById(R.id.tv_from);
        TextView tv_age = (TextView)holder.containedLayout.findViewById(R.id.tv_age);
        TextView tv_location = (TextView)holder.containedLayout.findViewById(R.id.tv_location);
        ImageView img_status = (ImageView)holder.containedLayout.findViewById(R.id.iv_status);
        ImageView chatRequest = (ImageView)holder.containedLayout.findViewById(R.id.chatRequest);
        CardView cv_1 = (CardView)holder.containedLayout.findViewById(R.id.cv_1);

        /*double dheight2=(height* Constant.height_profileLayout);
        int iheight2=(int)dheight2;
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iheight2);
        cv_1.setLayoutParams(params2);*/

        chatRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.getChatRequest(list.get(position).getName(),list.get(0).getPosition(),list.get(position).getUniqueID());
            }
        });


        Typeface font = Typeface.createFromAsset(context.getAssets(), "proximasoft_semibold.otf");

        Typeface fontProximaRegular = Typeface.createFromAsset(context.getAssets(), "proximasoft_regular.otf");

        Typeface font3 = Typeface.createFromAsset(context.getAssets(), "proximasoft_medium.otf");


        tv_name.setTypeface(fontProximaRegular);
        tv_name.setTextSize(29);

        tv_age.setTypeface(fontProximaRegular);
        tv_age.setTextSize(29);

        tv_name.setText(""+list.get(position).getName()+",");
        tv_age.setText(""+list.get(position).getAge());

        tv_from.setText(""+list.get(position).getLocationName());
        tv_location.setText(""+list.get(position).getDistance());

        tv_from.setTypeface(fontProximaRegular);
        tv_location.setTypeface(fontProximaRegular);
        tv_location.setTextSize(16);
        tv_from.setTextSize(16);

        if (list.get(position).getStatus()!=null && list.get(position).getStatus().equals("home"))
        {
            img_status.setImageResource(R.drawable.travelling);
        }
        else
        {
            img_status.setImageResource(R.drawable.hosting_icon);
        }


        ImageView imageView = new ImageView(holder.containedLayout.getContext());

        double dheight=(height* Constant.height_profileFirstScreen);
        int iheight=(int)dheight;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iheight);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        String surl=list.get(position).getImageProfile();

        if (surl!=null && surl.length()>0)
        {
            Uri uri = Uri.parse(surl);
            StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());
            Glide.with(context)
                    .load(ref)
                    .apply(new RequestOptions().override(iheight, iheight))
                    .into(imageView);

        }
        else
        {
            imageView.setImageResource(R.drawable.blank);
        }

        holder.frame.addView(imageView);
    }

    @Override
    public long getItemId(int position) {
        return list.size();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
