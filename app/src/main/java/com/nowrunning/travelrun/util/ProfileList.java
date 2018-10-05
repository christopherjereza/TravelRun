package com.nowrunning.travelrun.util;

import java.util.ArrayList;

/**
 * Created by Austin on 2/7/2018.
 */
public class ProfileList {

    private ArrayList<Profile> profileArrayList;

    public ProfileList(){
        profileArrayList = new ArrayList<>();
    }

    public Profile getMyProfile(){
        return profileArrayList.get(0);
    }

    public void setProfileArrayList(ArrayList<Profile> profileArrayList) {
        this.profileArrayList = profileArrayList;
    }

    public void addNewProfile(Profile inProf){
        this.profileArrayList.add(inProf);
    }

    public ArrayList<Profile> getProfileArrayList() {
        return profileArrayList;
    }

    /*@Override
    public int compareTo(Object another) {
        return 0;
    }*/
}
