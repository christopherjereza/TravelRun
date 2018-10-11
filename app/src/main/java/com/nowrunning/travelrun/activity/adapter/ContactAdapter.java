package com.nowrunning.travelrun.activity.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.NearbyHolder>{
    private ArrayList<Profile> list;
    private Context context;
    private StorageReference usersPic;
    ContactAdapter.NearbyHolder holder;
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
    public ContactAdapter(ArrayList<Profile> list, Context context, DatabaseReference myRef, StorageReference usersPicIn, File localFileIn, int height) {
        this.list = list;
        this.context = context;
        this.usersPic = usersPicIn;
        this.localFile = localFileIn;
        this.height=height;
    }


    @Override
    public ContactAdapter.NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rows, null);
        NearbyHolder mh = new NearbyHolder(v);
        return mh;

        //return null;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.NearbyHolder inHolder, final int positionIn) {
        this.position =positionIn;
        this.holder = inHolder;

        TextView tv_name = (TextView)holder.containedLayout.findViewById(R.id.tv_name);
        ImageView imageView = (ImageView)holder.containedLayout.findViewById(R.id.profileImage);
        CheckBox brand_select = (CheckBox)holder.containedLayout.findViewById(R.id.brand_select);

        //if (position==0)
          //  brand_select.setChecked(true);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "proximasoft_semibold.otf");
        Typeface fontProximaRegular = Typeface.createFromAsset(context.getAssets(), "proximasoft_regular.otf");
        Typeface font3 = Typeface.createFromAsset(context.getAssets(), "proximasoft_medium.otf");

        tv_name.setTypeface(font3);
        tv_name.setTextSize(20);
        tv_name.setText(""+list.get(position).getName());

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
