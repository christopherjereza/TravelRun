package com.nowrunning.travelrun.util;

import java.util.ArrayList;

public class Circle {

    private ArrayList<Profile> circleMemb;

    public Circle(){
        this.circleMemb = new ArrayList<>();
    }

    public void addToCircle(Profile addProf){
        this.circleMemb.add(addProf);
    }

    public ArrayList<Profile> getCircleMemb() {
        return circleMemb;
    }

}
