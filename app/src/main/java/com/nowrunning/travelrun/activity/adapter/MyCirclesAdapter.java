package com.nowrunning.travelrun.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.bean.Profile;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by Austin on 12/5/2017.
 */
public class MyCirclesAdapter extends RecyclerView.Adapter<MyCirclesAdapter.NearbyHolder>{
    private ArrayList<Profile> list;
    private Context context;
    private StorageReference usersPic;
    MyCirclesAdapter.NearbyHolder holder;
    File localFile;
    int position;
    int height;


    public class NearbyHolder extends RecyclerView.ViewHolder{
        protected FrameLayout frame;
        protected ImageView profPic;
        protected RelativeLayout containedLayout;
        //protected LinearLayout containedLayout;
        //protected FrameLayout containedLayout;


        public NearbyHolder(View itemView) {
            super(itemView);
            this.containedLayout = (RelativeLayout) itemView.findViewById(R.id.nearbyPerson);
            //this.containedLayout = (LinearLayout) itemView.findViewById(R.id.nearbyPerson);
            //this.containedLayout = (FrameLayout) itemView.findViewById(R.id.nearbyPerson);
            this.profPic = (ImageView) itemView.findViewById(R.id.profileImage);
            this.frame = (FrameLayout) itemView.findViewById(R.id.frame);
        }

    }
    public MyCirclesAdapter(ArrayList<Profile> list, Context context, DatabaseReference myRef, StorageReference usersPicIn, File localFileIn, int height) {
        this.list = list;
        this.context = context;
        this.usersPic = usersPicIn;
        this.localFile = localFileIn;
        this.height=height;
    }


    @Override
    public MyCirclesAdapter.NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycircles_adapter_rows, null);
        NearbyHolder mh = new NearbyHolder(v);
        return mh;

        //return null;
    }

    @Override
    public void onBindViewHolder(MyCirclesAdapter.NearbyHolder inHolder, final int positionIn) {
        this.position =positionIn;
        this.holder = inHolder;

        ImageView imageView = (ImageView)holder.containedLayout.findViewById(R.id.profileImage);
      //  de.hdodenhof.circleimageview.CircleImageView imageView = (de.hdodenhof.circleimageview.CircleImageView)holder.containedLayout.findViewById(R.id.profileImage);

        float pixels =  70 * context.getResources().getDisplayMetrics().density;
        float factor = context.getResources().getDisplayMetrics().density;
        int iwidth=(int)(70 * factor);
        int iheight=(int)(70 * factor);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(70, 70);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iwidth, iheight);
        //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(iwidth, iheight);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        params.width = iwidth;
        params.height = iheight;

        if (position==0)
        {
            params.rightMargin=0;
            params.leftMargin=0;
        }
        else if (position==1)
        {
            params.rightMargin=0;
            params.leftMargin=0;
        }
        else if (position==5)
        {
            params.rightMargin=0;
            params.leftMargin=0;
        }
        else
        {
           params.rightMargin=0;
            params.leftMargin=0;
           // params.leftMargin=-50;
        }

        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        String surl=list.get(position).getImageProfile();
        if (surl!=null && surl.length()>0)
        {
            Uri uri = Uri.parse(surl);
            StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());
            /*Glide.with(context)
                    .load(ref)
                    //.apply(bitmapTransform(new CropCircleTransformation()))
                    .apply(new RequestOptions().override(iwidth , iheight))
                    .into(imageView);*/
        }
        else
        {
            imageView.setImageResource(R.drawable.blank);
        }

        if (position==0)
            imageView.setImageResource(R.drawable.test3);
        else if (position==1)
            imageView.setImageResource(R.drawable.test5);
        else if (position==2)
            imageView.setImageResource(R.drawable.test4);
        else if (position==3)
            imageView.setImageResource(R.drawable.test3);
        else if (position==4)
            imageView.setImageResource(R.drawable.test4);
        else if (position==5)
            imageView.setImageResource(R.drawable.test5);
        else
            imageView.setImageResource(R.drawable.test4);


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
