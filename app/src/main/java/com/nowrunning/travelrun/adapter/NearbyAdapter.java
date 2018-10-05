package com.nowrunning.travelrun.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.util.Profile;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Austin on 12/5/2017.
 */
public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.NearbyHolder>{
    private ArrayList<Profile> list;
    private Context context;
    //protected ImageView profPic;
    private StorageReference usersPic;
    private StorageReference pullUserUnique;
    private StorageReference singleImgRef;
    final long ONE_MEGABYTE = 1024 * 1024;

    NearbyAdapter.NearbyHolder holder;

    File localFile;

    int position;




    public class NearbyHolder extends RecyclerView.ViewHolder{
        protected FrameLayout frame;
        protected ImageView profPic;
        protected LinearLayout containedLayout;

        public NearbyHolder(View itemView) {
            super(itemView);
            this.containedLayout = (LinearLayout) itemView.findViewById(R.id.nearbyPerson);
            this.profPic = (ImageView) itemView.findViewById(R.id.profileImage);
            this.frame = (FrameLayout) itemView.findViewById(R.id.frame);

        }

    }
    public NearbyAdapter(ArrayList<Profile> list, Context context, DatabaseReference myRef, StorageReference usersPicIn, File localFileIn) {
        this.list = list;
        this.context = context;
        this.usersPic = usersPicIn;
        this.localFile = localFileIn;



        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                //Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                //
                //                // A new comment has been added, add it to the displayed list
                //                Comment comment = dataSnapshot.getValue(Comment.class);
                //
                //                // [START_EXCLUDE]
                //                // Update RecyclerView
                //                mCommentIds.add(dataSnapshot.getKey());
                //                mComments.add(comment);
                //                notifyItemInserted(mComments.size() - 1);
                //                // [END_EXCLUDE]



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                /*Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.
                Comment newComment = dataSnapshot.getValue(Comment.class);
                String commentKey = dataSnapshot.getKey();

                // [START_EXCLUDE]
                int commentIndex = mCommentIds.indexOf(commentKey);
                if (commentIndex > -1) {
                    // Replace with the new data
                    mComments.set(commentIndex, newComment);

                    // Update the RecyclerView
                    notifyItemChanged(commentIndex);
                } else {
                    Log.w(TAG, "onChildChanged:unknown_child:" + commentKey);
                }*/
                // [END_EXCLUDE]
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                /*Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();

                // [START_EXCLUDE]
                int commentIndex = mCommentIds.indexOf(commentKey);
                if (commentIndex > -1) {
                    // Remove data from the list
                    mCommentIds.remove(commentIndex);
                    mComments.remove(commentIndex);

                    // Update the RecyclerView
                    notifyItemRemoved(commentIndex);
                } else {
                    Log.w(TAG, "onChildRemoved:unknown_child:" + commentKey);
                }*/
                // [END_EXCLUDE]
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                /*Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                Comment movedComment = dataSnapshot.getValue(Comment.class);
                String commentKey = dataSnapshot.getKey();*/

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /*Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                Toast.makeText(mContext, "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();*/
            }
        };

        myRef.addChildEventListener(childEventListener);
    }

    /*@Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }*/

    @Override
    public NearbyAdapter.NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout_main, null);
        NearbyHolder mh = new NearbyHolder(v);
        return mh;

        //return null;
    }

    @Override
    public void onBindViewHolder(NearbyAdapter.NearbyHolder inHolder, final int positionIn) {
        this.position =positionIn;
        this.holder = inHolder;
        //RecyclerView lstView = (RecyclerView) holder.containedLayout.findViewById(R.id.nearbyList);

        //ImageView diplayPic = (ImageView)context.findViewById(R.id.profileImage);

        TextView displayName = (TextView)holder.containedLayout.findViewById(R.id.textView);
        displayName.setText(list.get(position).getName());


        /*lstView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(context, otherPersonProfile.class);
                context.startActivity(i);
            }
        });*/

        /*lstView.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {
                Intent i = new Intent(context, otherPersonProfile.class);
                context.startActivity(i);
            }
        });*/

        /*lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {
                Intent i = new Intent(context, otherPersonProfile.class);
                context.startActivity(i);
            }
        });*/

        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(150,150);

        //ArrayList<Profile> circleMembers = circles.get(position).getCircleMemb();


        if(!list.get(position).getPictNames().isEmpty()) {
            pullUserUnique = usersPic.child(list.get(position).getUniqueID());
            for(int jj = 0; jj < list.get(position).getPictNames().size(); jj++){
                singleImgRef = pullUserUnique.child(list.get(position).getPictNames().get(jj));

                singleImgRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Local temp file has been created
                        //taskSnapshot;.getStorage().getBytes(ONE_MEGABYTE).getResult()

                        //inProf.addPicPath(localFile.getAbsolutePath());
                        //inProf.addPic(taskSnapshot.getStorage().getBytes());
                        taskSnapshot.getStorage().getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                // Data for "images/island.jpg" is returns, use this as needed
                                list.get(position).addPic(bytes);
                                //Bitmap bmp = BitmapFactory.decodeByteArray(list.get(position).getBytePicTist().get(0), 0, list.get(position).getBytePicTist().get(0).length);
                                ImageView imageView = new ImageView(holder.containedLayout.getContext());
// Set the Bitmap data to the ImageView
                                imageView.setImageBitmap(list.get(position).getPictBMP().get(0));
                                imageView.scrollTo(80,80);
                                holder.frame.addView(imageView);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });
                        //Bitmap bmp = BitmapFactory.decodeByteArray(inProf.getBytePicTist().get(0), 0, inProf.getBytePicTist().get(0).length);

                        //inProf.addBMP(bmp);
                        //ImageView imageView = new ImageView(context);
// Set the Bitmap data to the ImageView
                        //imageView.setImageBitmap(bmp);

                        //ViewGroup layout = (ViewGroup) findViewById(R.id.nearbyList);
// Add the ImageView to the Layout
                        //layout.addView(imageView);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });


                //inProf.addPic();
                //inProf.setMainImage(R.drawable.pexels_photo_220453);


            }



            //ViewGroup layout = (ViewGroup) holder.containedLayout.findViewById(R.id.nearbyList);
// Add the ImageView to the Layout
            //layout.addView(imageView);

            //holder.profPic

            //holder.profPic.setImageResource(list.get(position).getMainImage());

        //ImageView chatRequest = (ImageView)view.findViewById(R.id.chatRequest);


        //holder.profPic.setImageBitmap(list.get(position).getPictBMP().get(0));

            //holder.profPic.scrollTo(80,80);
        }

        //holder.addItemDecoration(new BoundaryItemDecoration(context, R.color.black, 50));



        /*ImageView profileImage = (ImageView)view.findViewById(R.id.profileImage);

        profileImage.setImageResource(list.get(position).getMainImage());

        profileImage.scrollTo(80,80);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(context, otherPersonProfile.class);
                context.startActivity(i);
            }
        });*/

    }

    /*@Override
    public long getItemId(int position) {
        return list.get(position).getUniqueID();
    }*/

    @Override
    public int getItemCount() {
        return list.size();
    }


    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;
        View view = convertView;
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout_main, null);
        }
        RecyclerView lstView = (RecyclerView) parent.findViewById(R.id.nearbyList);

        //ImageView diplayPic = (ImageView)view.findViewById(R.id.profileImage);

        TextView displayName = (TextView)view.findViewById(R.id.textView);
        displayName.setText(list.get(position).getName());


        /*lstView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(context, otherPersonProfile.class);
                context.startActivity(i);
            }
        });*/

        /*lstView.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {
                Intent i = new Intent(context, otherPersonProfile.class);
                context.startActivity(i);
            }
        });*/

        /*lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {
                Intent i = new Intent(context, otherPersonProfile.class);
                context.startActivity(i);
            }
        });*/

        /*ImageView chatRequest = (ImageView)view.findViewById(R.id.chatRequest);




        ImageView profileImage = (ImageView)view.findViewById(R.id.profileImage);

        profileImage.setImageResource(list.get(position).getMainImage());

        profileImage.scrollTo(80,80);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(context, otherPersonProfile.class);
                context.startActivity(i);
            }
        });

        return view;
    }*/




}
