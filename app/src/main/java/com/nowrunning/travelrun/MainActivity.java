package com.nowrunning.travelrun;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.nowrunning.travelrun.adapter.NearbyAdapter;
import com.nowrunning.travelrun.util.BoundaryItemDecoration;
import com.nowrunning.travelrun.util.Chats;
import com.nowrunning.travelrun.util.Circle;
import com.nowrunning.travelrun.util.DividerItemDecoration;
import com.nowrunning.travelrun.util.MyProfile;
import com.nowrunning.travelrun.util.Profile;
import com.nowrunning.travelrun.util.ProfileList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AbsActivity {

    final long ONE_MEGABYTE = 1024 * 1024;
    DatabaseReference myRef;

    private RecyclerView list;

    private ProfileList nearbyProfList;
    private Profile inProf;// = new Profile();
    private ProfileList nearbyList;// = new ProfileList();
    private StorageReference storageRef;
    private StorageReference imagesRef;
    private StorageReference usersPic;

    File localFile;

    private int testint;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            //no user signed in
            //pull up sign-in
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);

        }else{

        }

        try {
            localFile = File.createTempFile("images", "bmp");
        } catch (IOException e) {
            e.printStackTrace();
        }

        context = this;

        list = (RecyclerView)findViewById(R.id.nearbyList);

        inProf = new Profile();
        nearbyList = new ProfileList();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //layoutManager.setReverseLayout(true);
        list.setLayoutManager(layoutManager);
        //ArrayList pulledCircle = (OwnerProfile.getCircList());

        storageRef = storage.getReference();
        imagesRef = storageRef.child("images");

        //this one is going to change,
        usersPic = imagesRef.child("users");

        //pullUserUnique = usersPic.child("")


        if(OwnerProfile == null) {
            OwnerProfile = new MyProfile();
        }

        if(nearbyProf == null){
            nearbyProf = new ArrayList<Profile>();
        } else {
            nearbyProf.clear();
        }

        OwnerProfile.setName("me");

        Chats newChat = new Chats();

        Profile anotherProfile = new Profile("person", 10);

        ArrayList newlist = new ArrayList<Profile>();

        //ArrayList CircleList = new ArrayList<Circle>();

        Circle newCircle = new Circle();

        //DatabaseReference myRef = database.getReference("event");

        //myRef.setValue("HELLO!, this is an event");

        myRef = database.getReference("users");

        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //retProf.setAge(0);
            }
        });*/

        ValueEventListener personListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    inProf = new Profile();
                    int intCast =0;
                    String stringCast = "";


                    inProf.setName((String) userSnapshot.child("name").getValue());
                    //stringCast = (String) userSnapshot.child("age").getValue();
                    //inProf.setAge(intCast);
                    //add in the last few bits of code to make it so that it will pull the pictures based off of the person's personal profile number
                    //the unique id is the number used, pulling from the user -> user number -> exact numbers pulled from the profile which will store the
                    //names for the pictures.
                    inProf.setUniqueID(((String) userSnapshot.getKey()));
                    inProf.setWhatsUp((String) userSnapshot.child("whats").getValue());
                    inProf.setBio((String) userSnapshot.child("bio").getValue());
                    for(int ii = 0; ii < userSnapshot.child("picturesID").getChildrenCount(); ii++) {
                        inProf.addNewPictName((String) userSnapshot.child("picturesID").child((Integer.toString(ii))).getValue());
                    }
                    nearbyList.addNewProfile(inProf);
                }
                inProf = new Profile();
                inProf.setName("Chris");
                inProf.setUniqueID("12313231231");
                inProf.setBio("Gucci fuccing gang");
                System.out.print("HELLOI");
                nearbyList.addNewProfile(inProf);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //retProf.setAge(0);
            }
        };

        myRef.addValueEventListener(personListener);


        //newlist = pullZipProfiles(myRef);
        //newlist = nearbyList.getProfileArrayList();


        /*anotherProfile.addPic(R.drawable.pexels_photo_91224);
        anotherProfile.setMainImage(R.drawable.pexels_photo_91224);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);
        newCircle.addToCircle(anotherProfile);*/



        /*anotherProfile = extractUser(myRef,1);
        if(anotherProfile != null) {
            newlist.add(anotherProfile);
        }*/
        newCircle = new Circle();
        newChat = new Chats();

        /*anotherProfile = new Profile("person2", 10);

        anotherProfile.addPic(R.drawable.pexels_photo_220453);
        anotherProfile.setMainImage(R.drawable.pexels_photo_220453);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);
        newCircle.addToCircle(anotherProfile);
        OwnerProfile.addMyCircles(newCircle);

        anotherProfile = new Profile("person3", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_212286);
        anotherProfile.setMainImage(R.drawable.pexels_photo_212286);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);
        newCircle.addToCircle(anotherProfile);
        OwnerProfile.addMyCircles(newCircle);


        anotherProfile = new Profile("person4", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_91227);
        anotherProfile.setMainImage(R.drawable.pexels_photo_91227);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);
        newCircle.addToCircle(anotherProfile);

        anotherProfile = new Profile("person5", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_97900);
        anotherProfile.setMainImage(R.drawable.pexels_photo_97900);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);
        newCircle.addToCircle(anotherProfile);
        OwnerProfile.addMyCircles(newCircle);

        anotherProfile = new Profile("person6", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_212286);
        anotherProfile.setMainImage(R.drawable.pexels_photo_212286);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);

        anotherProfile = new Profile("person7", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_395196);
        anotherProfile.setMainImage(R.drawable.pexels_photo_395196);
        newlist.add(anotherProfile);

        anotherProfile = new Profile("person8", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_556666);
        anotherProfile.setMainImage(R.drawable.pexels_photo_556666);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);

        anotherProfile = new Profile("person9", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_842567);
        anotherProfile.setMainImage(R.drawable.pexels_photo_842567);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);

        anotherProfile = new Profile("person10", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_445109);
        anotherProfile.setMainImage(R.drawable.pexels_photo_445109);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);

        anotherProfile = new Profile("person11", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_450271);
        anotherProfile.setMainImage(R.drawable.pexels_photo_450271);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);

        anotherProfile = new Profile("person12", 10);
        newChat = new Chats();
        anotherProfile.addPic(R.drawable.pexels_photo_395196);
        anotherProfile.setMainImage(R.drawable.pexels_photo_395196);
        newChat.setPerson(anotherProfile);
        newlist.add(anotherProfile);
        OwnerProfile.addNewChat(newChat);*/




        //finish setting this adapter, then finish up the adapter
        //NearbyAdapter nearAdap = new NearbyAdapter(newlist, this, myRef);
        //list.setAdapter(nearAdap);


        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, otherPersonProfile.class);
                startActivity(i);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OtherProfileActivity.class);
                startActivity(i);
            }
        });*/
        NearbyAdapter nearAdap = new NearbyAdapter(nearbyList.getProfileArrayList(), context, myRef, usersPic,localFile);
        //String value = dataSnapshot.getValue(String.class);
        //LinearLayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        //list.setLayoutManager(layout);
        list.addItemDecoration(new BoundaryItemDecoration(context, R.color.colorPrimaryDark, 10));

        list.addItemDecoration(new DividerItemDecoration(context));
        //list.addItemDecoration(new VerticalSpaceItemDecoration(48));

        list.setAdapter(nearAdap);

        ImageView goToStarted = (ImageView) findViewById(R.id.goToStarted);

        goToStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(MainActivity.this, StartedChatsActivity.class);
                startActivity(i);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CircleActivity.class);
                startActivity(i);
                //finish();
            }
        });
    }

    /*@Override
    protected void onStart() {

        super.onStart();
        testint = 3;
    }*/

    /*private Profile extractUser(DatabaseReference myRef, int userID) {
        final ProfileList newList = new ProfileList();
        final Profile retProf = new Profile();
        boolean check;
        String findusr;
        Integer usrNumb = new Integer(userID);
        findusr = usrNumb.toString();
        myRef = myRef.child(findusr);
        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                retProf.setName(value);
                retProf.setAge(10);
                retProf.addPic(R.drawable.pexels_photo_842567);
                retProf.setMainImage(R.drawable.pexels_photo_842567);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                retProf.setAge(0);
            }
        });*
        if(retProf.getAge() > 0) {
            return retProf;
        }
        return null;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openNew(){
        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    /*private ArrayList<Profile> pullZipProfiles(DatabaseReference myRef){
        //final ArrayList<Profile> nearbyProf = new ArrayList<>();
        //final Profile inProf;
        final ProfileList nearbyProf = new ProfileList();
        final Profile inProf = new Profile();

        //inProf = new Profile();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    //inProf = new Profile();
                    inProf.setName((String) userSnapshot.child("name").getValue());
                    inProf.setAge((int) userSnapshot.child("name").getValue());
                    inProf.addPic(R.drawable.pexels_photo_91224);
                    inProf.setMainImage(R.drawable.pexels_photo_91224);
                    nearbyProf.addNewProfile(inProf);
                }
                //String value = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //retProf.setAge(0);
            }
        });


        return nearbyProf.getProfileArrayList();
    }*/
}
