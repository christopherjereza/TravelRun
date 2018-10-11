package com.nowrunning.travelrun.activity.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.nowrunning.travelrun.activity.CircleTransform;
import com.nowrunning.travelrun.bean.ChatBean;
import com.nowrunning.travelrun.bean.Profile;

import java.io.File;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class ChatConversationAdapter extends RecyclerView.Adapter<ChatConversationAdapter.NearbyHolder>{
    private ArrayList<ChatBean> list;
    private Context context;
    private StorageReference usersPic;
    ChatConversationAdapter.NearbyHolder holder;
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
    public ChatConversationAdapter(ArrayList<ChatBean> list, Context context, DatabaseReference myRef, StorageReference usersPicIn, File localFileIn, int height) {
        this.list = list;
        this.context = context;
        this.usersPic = usersPicIn;
        this.localFile = localFileIn;
        this.height=height;
    }


    @Override
    public ChatConversationAdapter.NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_conversation, null);
        NearbyHolder mh = new NearbyHolder(v);
        return mh;

        //return null;
    }

    @Override
    public void onBindViewHolder(ChatConversationAdapter.NearbyHolder inHolder, final int positionIn) {
        this.position =positionIn;
        this.holder = inHolder;

        TextView tv_name = (TextView)holder.containedLayout.findViewById(R.id.tv_name);
        //ImageView imageView = (ImageView)holder.containedLayout.findViewById(R.id.profileImage);
        de.hdodenhof.circleimageview.CircleImageView imageView = (de.hdodenhof.circleimageview.CircleImageView)holder.containedLayout.findViewById(R.id.profileImage);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "proximasoft_semibold.otf");
        Typeface fontProximaRegular = Typeface.createFromAsset(context.getAssets(), "proximasoft_regular.otf");
        Typeface font3 = Typeface.createFromAsset(context.getAssets(), "proximasoft_medium.otf");

        tv_name.setTypeface(fontProximaRegular);
        tv_name.setTextSize(14);

        //RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tv_name.getLayoutParams();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        tv_name.setLayoutParams(lp);

        tv_name.setText(list.get(position).getMessage());

        float pixels =  55 * context.getResources().getDisplayMetrics().density;
        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(35, 35);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        float factor = context.getResources().getDisplayMetrics().density;
        params.width = (int)(34 * factor);
        params.height = (int)(34 * factor);

        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


        if (list!=null && list.get(position).getStatus()==0)
        {
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            tv_name.setGravity(Gravity.LEFT);
            lp.leftMargin=170;
            tv_name.setBackgroundResource(R.drawable.bg_chat_cyan);
        }
        else
        {
            Glide.with(context)
                    .load(R.drawable.test4)
                    .apply(new RequestOptions().override(params.width , params.height))
                    .apply(bitmapTransform(new CropCircleTransformation()))
                    .into(imageView);

            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lp.topMargin=20;
            lp.leftMargin=60;
            lp.rightMargin=170;
            tv_name.setBackgroundResource(R.drawable.bg_chat_white);
        }


        //String surl=list.get(position).getImageProfile();
        /*if (surl!=null && surl.length()>0)
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

        /*}
        else
        {
            imageView.setImageResource(R.drawable.blank);
        }*/

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
