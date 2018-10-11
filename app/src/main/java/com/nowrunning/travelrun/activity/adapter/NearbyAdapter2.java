package com.nowrunning.travelrun.activity.adapter;
import android.content.Context;

import android.graphics.Typeface;
import android.net.Uri;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.util.Constant;
import com.nowrunning.travelrun.bean.Profile;
import com.nowrunning.travelrun.bean.fragment.MainActivityFragment;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class NearbyAdapter2 extends RecyclerView.Adapter<NearbyAdapter2.ViewHolder> {
        private ArrayList<Profile> list;
        private Context context;
        private StorageReference usersPic;
        NearbyAdapter.NearbyHolder holder;
        File localFile;
        int height;
        private MainActivityFragment fragment;

        public NearbyAdapter2(ArrayList<Profile> list, Context context, DatabaseReference myRef, StorageReference usersPicIn, File localFileIn, int height,MainActivityFragment fragment)
         {
            this.list = list;
            this.context = context;
            this.usersPic = usersPicIn;
            this.localFile = localFileIn;
            this.height=height;
            this.fragment=fragment;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, null, false);
            //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_main, null);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Profile bean = list.get(position );

            double dheight2=(height* Constant.height_profileLayout);
            int iheight2=(int)dheight2;
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iheight2);
            //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iheight);
            //holder.containedLayout.setLayoutParams(params2);

            holder.chatRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //fragment.getChatRequest(bean.getName(),bean.getPosition(),bean.getUniqueID());
                    fragment.getChatRequest(list.get(position).getName(),list.get(0).getPosition(),list.get(position).getUniqueID());
                }
            });


            //Typeface font = Typeface.createFromAsset(context.getAssets(), "proximasoft_semibold.otf");
            Typeface fontProximaRegular = Typeface.createFromAsset(context.getAssets(), "proximasoft_regular.otf");
            //Typeface font3 = Typeface.createFromAsset(context.getAssets(), "proximasoft_medium.otf");

            holder.tv_name.setTypeface(fontProximaRegular);
            holder.tv_name.setTextSize(29);


            holder.tv_age.setTypeface(fontProximaRegular);
            holder.tv_age.setTextSize(29);

            holder.tv_name.setText(""+bean.getName()+",");
            holder.tv_age.setText(""+bean.getAge());

            holder.tv_from.setText(""+bean.getLocationName());
            holder.tv_location.setText(""+bean.getDistance());

            holder.tv_from.setTypeface(fontProximaRegular);
            holder.tv_location.setTypeface(fontProximaRegular);
            holder.tv_location.setTextSize(16);
            holder.tv_from.setTextSize(16);

            if (bean.getStatus()!=null && bean.getStatus().equals("home"))
            {
                holder.img_status.setImageResource(R.drawable.travelling);
            }
            else
            {
                holder.img_status.setImageResource(R.drawable.hosting_icon);
            }


            ImageView imageView = new ImageView(holder.containedLayout.getContext());

            double dheight=(height* Constant.height_profileFirstScreen);
            int iheight=(int)dheight;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iheight);
            //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iheight);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            String surl=bean.getImageProfile();

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
        public int getItemCount() {
            return list.size() ;
        }

        @Override
        public int getItemViewType(int position) {
            return R.layout.custom_layout_main;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_name,tv_from,tv_age,tv_location;
            ImageView img_status,chatRequest;
            CardView cv_1;
            com.nowrunning.travelrun.activity.ProportionalImageView profileImage;
            RelativeLayout containedLayout,rl_main;
            FrameLayout frame;

            public ViewHolder(View itemView) {

                super(itemView);
                tv_name = (TextView)itemView.findViewById(R.id.tv_name);
                tv_from  = (TextView)itemView.findViewById(R.id.tv_from);
                tv_age  = (TextView)itemView.findViewById(R.id.tv_age);
                tv_location  = (TextView)itemView.findViewById(R.id.tv_location);
                img_status = (ImageView)itemView.findViewById(R.id.iv_status);
                profileImage = (com.nowrunning.travelrun.activity.ProportionalImageView)itemView.findViewById(R.id.profileImage);
                chatRequest = (ImageView)itemView.findViewById(R.id.chatRequest);
                cv_1 = (CardView)itemView.findViewById(R.id.cv_1);
                containedLayout = (RelativeLayout) itemView.findViewById(R.id.nearbyPerson);
                rl_main= (RelativeLayout) itemView.findViewById(R.id.nearbyPerson);
                frame= (FrameLayout) itemView.findViewById(R.id.frame);
            }


        }

    }
