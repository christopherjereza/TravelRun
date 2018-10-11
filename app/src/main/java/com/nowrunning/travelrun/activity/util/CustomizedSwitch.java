package com.nowrunning.travelrun.activity.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.nowrunning.travelrun.R;

public class CustomizedSwitch extends android.support.v7.widget.SwitchCompat {

    public CustomizedSwitch(Context context) {
        super(context);
        initialize(context);
    }

    public CustomizedSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public CustomizedSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public void initialize(Context context) {
        // Setting up my colors
        int mediumGreen = ContextCompat.getColor(context, R.color.colorAccent);
        int mediumGrey = ContextCompat.getColor(context, R.color.colorGray7);
        //int alphaMediumGreen = Color.argb(127, Color.red(mediumGreen), Color.green(mediumGreen), Color.blue(mediumGreen));
        int alphaMediumGreen = Color.argb(90, Color.red(mediumGreen), Color.green(mediumGreen), Color.blue(mediumGreen));
        int alphaMediumGrey = Color.argb(127, Color.red(mediumGrey), Color.green(mediumGrey), Color.blue(mediumGrey));
        // Sets the tints for the thumb in different states
        DrawableCompat.setTintList(this.getThumbDrawable(), new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        mediumGreen,
                        ContextCompat.getColor(getContext(), R.color.colorYellow)
                }));
        // Sets the tints for the track in different states
        DrawableCompat.setTintList(this.getTrackDrawable(), new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        alphaMediumGreen,
                        alphaMediumGrey
                }));
    }
}