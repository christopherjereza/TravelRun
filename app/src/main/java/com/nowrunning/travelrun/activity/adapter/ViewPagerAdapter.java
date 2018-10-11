package com.nowrunning.travelrun.activity.adapter;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.util.Constant;
import com.nowrunning.travelrun.bean.Profile;
import com.nowrunning.travelrun.bean.fragment.MainActivityFragment;
import com.nowrunning.travelrun.bean.fragment.ViewProfileFragment;

import java.util.ArrayList;


public class ViewPagerAdapter extends PagerAdapter {
    private ViewProfileFragment fragment;
    private Context mContext;
    private int[] mResources;
    ArrayList<String> prof;
    int height;
    public ViewPagerAdapter(Context mContext, int[] mResources, ArrayList<String> prof,int height,ViewProfileFragment fragment) {
        this.mContext = mContext;
        this.mResources = mResources;
        this.prof=prof;
        this.height=height;
        this.fragment=fragment;
    }

    @Override
    public int getCount() {
        return prof.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //return view == ((LinearLayout) object);
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        ImageView iv_close = (ImageView) itemView.findViewById(R.id.iv_close);
        //imageView.setImageResource(mResources[position]);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.exit();
            }
        });



        Uri uri = Uri.parse(prof.get(position));
        StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());

        double dheight=(height* Constant.height);
        int iheight=(int)dheight;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iheight);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(mContext)
                .load(ref)
                .apply(new RequestOptions().override(iheight, iheight))
                .into(imageView);


        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}