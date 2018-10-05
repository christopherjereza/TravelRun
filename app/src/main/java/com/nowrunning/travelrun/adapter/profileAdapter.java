package com.nowrunning.travelrun.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nowrunning.travelrun.R;

import java.util.ArrayList;

/**
 * Created by Austin on 1/12/2018.
 */
public class profileAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Integer> GalImages;
    /*private int[] GalImages = new int[] {
            R.drawable.timelesstoday, R.drawable.download
            /*R.drawable.cap8, R.drawable.cap2, R.drawable.cap3, R.drawable.cap1,R.drawable.cap5,
            R.drawable.cap6, R.drawable.cap7, R.drawable.cap9,R.drawable.cap4,
            R.drawable.cap10

    };*/
    public profileAdapter(Context context)
    {
        this.context=context;
    }

    public profileAdapter(Context context, ArrayList<Integer> inArray)
    {
        this.context=context;
        this.GalImages = inArray;
    }

    @Override
    public int getCount() {
        //return GalImages.length;
        return GalImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //imageView.setImageResource(GalImages[position]);
        imageView.setImageResource(GalImages.get(position).intValue());
        container.addView(imageView, 0);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}

