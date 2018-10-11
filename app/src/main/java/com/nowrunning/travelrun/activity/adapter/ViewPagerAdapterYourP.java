package com.nowrunning.travelrun.activity.adapter;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.util.Constant;
import com.nowrunning.travelrun.bean.fragment.ViewProfileFragment;
import com.nowrunning.travelrun.bean.fragment.YourProfileFragment;

import java.util.ArrayList;


public class ViewPagerAdapterYourP extends PagerAdapter {

    private Context mContext;
    private int[] mResources;
    ArrayList<String> prof;
    int height;
    private YourProfileFragment fragment;

    public ViewPagerAdapterYourP(Context mContext, int[] mResources, ArrayList<String> prof,int height,YourProfileFragment fragment) {
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
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item_your_p, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        //imageView.setImageResource(mResources[position]);
        ImageView iv_close = (ImageView) itemView.findViewById(R.id.iv_close);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.exit();
            }
        });


        Uri uri = Uri.parse(prof.get(position));
        StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());

        double dheight=(height* Constant.height_profile);
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