package com.nowrunning.travelrun.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nowrunning.travelrun.R;

import java.util.ArrayList;

/**
 * Created by Austin on 2/27/2018.
 */
public class textProfileAdapter extends PagerAdapter {
    private Context context;
    private String titles[] = new String[]{"Bio", "What's up"};

    //private String text;
    private ArrayList<String> text;


    public textProfileAdapter(Context context, String inText)
    {
        this.context=context;
        this.text.add(inText);
    }

    public textProfileAdapter(Context context, ArrayList textArray) {
        this.context =context;
        this.text = textArray;
    }

    @Override
    public int getCount() {
        //return GalImages.length;
        return text.size();
        //return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ScrollView scrollView = new ScrollView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        scrollView.setPadding(padding, padding, padding, padding);

        TextView textView = new TextView(context);
        textView.setText(text.get(position));



        scrollView.addView(textView);
        container.addView(scrollView, 0);

        //page

        return scrollView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

}
