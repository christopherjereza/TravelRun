package com.nowrunning.travelrun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.util.Circle;
import com.nowrunning.travelrun.util.Profile;

import java.util.ArrayList;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.CircleHolder> {
    private ArrayList<Circle> circles;
    private Context context;

    public CircleAdapter(Context context) {
        //super(context);
    }

    public class CircleHolder extends RecyclerView.ViewHolder{
        protected LinearLayout containedLayout;

        public CircleHolder(View itemView) {
            super(itemView);
            this.containedLayout = (LinearLayout) itemView.findViewById(R.id.circleProfileImages);
        }

    }

    public CircleAdapter(ArrayList<Circle> circleIn, Context inContext){
        this.circles = circleIn;
        this.context = inContext;
    }

    @Override
    public CircleAdapter.CircleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_layout_cicles, null);
        CircleHolder mh = new CircleHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(CircleHolder holder, int position) {

        /*LinearLayout mainLayout = (LinearLayout)findViewById(R.id.circleProfileImages);

        //mainLayout = ;

        mainLayout.addView();*/
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(150,150);

        ArrayList<Profile> circleMembers = circles.get(position).getCircleMemb();

        ImageView imgIn;

        for(int ii =0; ii < circleMembers.size(); ii++) {
            imgIn = new ImageView(context);
            /*imgIn.setMaxHeight(50);
            imgIn.setMaxHeight(50);*/

            imgIn.setImageResource(circleMembers.get(ii).getMainImage());

            //imgIn.set

            holder.containedLayout.addView(imgIn, imgParams);
        }

        /*imgIn = new ImageView(context);

        imgIn.setImageResource(R.drawable.pexels_photo_97900);
        holder.containedLayout.addView(imgIn,imgParams);*/

    }

    @Override
    public int getItemCount() {
        return circles.size();
    }
}
