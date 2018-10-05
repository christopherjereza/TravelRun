package com.nowrunning.travelrun;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.nowrunning.travelrun.util.MyProfile;
import com.nowrunning.travelrun.util.Profile;

import java.util.ArrayList;

public abstract class AbsActivity extends AppCompatActivity {

    public static MyProfile OwnerProfile;

    public static ArrayList<Profile> nearbyProf;

    public static FirebaseDatabase database= FirebaseDatabase.getInstance();

    public static Profile descProfile;

    public static DatabaseReference databaseRef = database.getReference();

    public static FirebaseStorage storage = FirebaseStorage.getInstance();

    //public static FirebaseDatabase database;

    public static FirebaseAuth auth = FirebaseAuth.getInstance();

}
