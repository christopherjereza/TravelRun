package com.nowrunning.travelrun.activity.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.bean.Profile;

import java.io.File;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.bumptech.glide.request.RequestOptions.overrideOf;

/**
 * Created by Austin on 12/5/2017.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.NearbyHolder>{
    private ArrayList<Profile> list;
    private Context context;
    private StorageReference usersPic;
    ChatAdapter.NearbyHolder holder;
    File localFile;
    int position;
    int height;


    public class NearbyHolder extends RecyclerView.ViewHolder{
        protected FrameLayout frame;
        protected ImageView profPic;
        protected RelativeLayout containedLayout;

        public NearbyHolder(View itemView) {
            super(itemView);
            this.containedLayout = (RelativeLayout) itemView.findViewById(R.id.nearbyPerson);
            this.profPic = (ImageView) itemView.findViewById(R.id.profileImage);
            this.frame = (FrameLayout) itemView.findViewById(R.id.frame);
        }

    }
    public ChatAdapter(ArrayList<Profile> list, Context context, DatabaseReference myRef, StorageReference usersPicIn, File localFileIn, int height) {
        this.list = list;
        this.context = context;
        this.usersPic = usersPicIn;
        this.localFile = localFileIn;
        this.height=height;
    }


    @Override
    public ChatAdapter.NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_rows, null);
        NearbyHolder mh = new NearbyHolder(v);
        return mh;

        //return null;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.NearbyHolder inHolder, final int positionIn) {
        this.position =positionIn;
        this.holder = inHolder;

        TextView tv_name = (TextView)holder.containedLayout.findViewById(R.id.tv_name);
        TextView tv_status_connection = (TextView)holder.containedLayout.findViewById(R.id.tv_status_connection);
        TextView tv_age = (TextView)holder.containedLayout.findViewById(R.id.tv_age);
        TextView tv_status_new_message = (TextView)holder.containedLayout.findViewById(R.id.tv_status_new_message);
        ImageView img_status = (ImageView)holder.containedLayout.findViewById(R.id.iv_status);
      //  ImageView imageView = new ImageView(holder.containedLayout.getContext());
        ImageView imageView = (ImageView)holder.containedLayout.findViewById(R.id.profileImage);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "proximasoft_semibold.otf");

        Typeface fontProximaRegular = Typeface.createFromAsset(context.getAssets(), "proximasoft_regular.otf");

        Typeface font3 = Typeface.createFromAsset(context.getAssets(), "proximasoft_medium.otf");


        tv_name.setTypeface(fontProximaRegular);
        tv_name.setTextSize(17);

        if (position==0)
            tv_status_new_message.setText(R.string.label_new_message);

        tv_status_connection.setTextSize(13);
        tv_age.setTypeface(font);

        tv_age.setTypeface(fontProximaRegular);
        tv_age.setTextSize(17);

        tv_name.setText(""+list.get(position).getName()+",");
        tv_age.setText(""+list.get(position).getAge());

        tv_status_connection.setText(""+list.get(position).getLocationName());
        tv_status_connection.setTypeface(fontProximaRegular);
        tv_status_connection.setTextSize(14);


        if (list.get(position).getStatus()!=null && list.get(position).getStatus().equals("home"))
        {
            img_status.setImageResource(R.drawable.circle_d1_cyan);
            //img_status.setBackgroundResource(R.drawable.b_status_cyan);
        }
        else
        {
            img_status.setImageResource(R.drawable.circle_d1_yellow);
        }

        float pixels =  55 * context.getResources().getDisplayMetrics().density;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(55, 55);

        float factor = context.getResources().getDisplayMetrics().density;
        params.width = (int)(55 * factor);
        params.height = (int)(55 * factor);



        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        String surl=list.get(position).getImageProfile();
        if (surl!=null && surl.length()>0)
        {
            Uri uri = Uri.parse(surl);
            StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());
            Glide.with(context)
                    .load(ref)
                    .apply(new RequestOptions().override(params.width , params.height))
                    .apply(bitmapTransform(new CropCircleTransformation()))
                    .into(imageView);


            /*Glide.with(context).load(R.drawable.test2)
                    .apply(bitmapTransform(new BlurTransformation(25, 3)))
                    .into(imageView);*/

            //Glide.with(context).load(ref).tr(new CircleTransform(this.context)).into(imageView);

            /*Glide.with(context)
                    .load(ref)
                    .apply(bitmapTransform(new GrayscaleTransformation()))
                    .into(imageView);*/

        }
        else
        {
            imageView.setImageResource(R.drawable.blank);
        }

        //holder.frame.addView(imageView);
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
