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

/**
 * Created by Austin on 12/5/2017.
 */
public class RequestChatAdapter extends RecyclerView.Adapter<RequestChatAdapter.NearbyHolder>{
    private ArrayList<Profile> list;
    private Context context;
    private StorageReference usersPic;
    RequestChatAdapter.NearbyHolder holder;
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
    public RequestChatAdapter(ArrayList<Profile> list, Context context, DatabaseReference myRef, StorageReference usersPicIn, File localFileIn, int height) {
        this.list = list;
        this.context = context;
        this.usersPic = usersPicIn;
        this.localFile = localFileIn;
        this.height=height;
    }


    @Override
    public RequestChatAdapter.NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_adapter_rows, null);
        NearbyHolder mh = new NearbyHolder(v);
        return mh;

        //return null;
    }

    @Override
    public void onBindViewHolder(RequestChatAdapter.NearbyHolder inHolder, final int positionIn) {
        this.position =positionIn;
        this.holder = inHolder;

        ImageView img_status = (ImageView)holder.containedLayout.findViewById(R.id.iv_status);
      //  ImageView imageView = new ImageView(holder.containedLayout.getContext());
        ImageView imageView = (ImageView)holder.containedLayout.findViewById(R.id.profileImage);

        if (list.get(position).getStatus()!=null && list.get(position).getStatus().equals("home"))
        {
            img_status.setImageResource(R.drawable.circle_d1_cyan);
        }
        else
        {
            img_status.setImageResource(R.drawable.circle_d1_yellow);
        }

        float pixels =  70 * context.getResources().getDisplayMetrics().density;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(70, 70);

        float factor = context.getResources().getDisplayMetrics().density;
        params.width = (int)(70 * factor);
        params.height = (int)(70 * factor);

        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        String surl=list.get(position).getImageProfile();
        if (surl!=null && surl.length()>0)
        {
            Uri uri = Uri.parse(surl);
            StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());
            Glide.with(context)
                    .load(ref)
                    .apply(bitmapTransform(new CropCircleTransformation()))
                    .apply(new RequestOptions().override(params.width , params.height))
                    .into(imageView);

        }
        else
        {
            imageView.setImageResource(R.drawable.blank);
        }
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
