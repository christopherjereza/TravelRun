package com.nowrunning.travelrun.util;

import java.util.ArrayList;

public class MyProfile extends Profile {


    private String myPassword;

    private boolean milesKM;

    private ArrayList<Chats> localChats;

    private ArrayList<Circle> myCircles;

    private int chatRequest;

    public MyProfile(){
        this.localChats = new ArrayList<>();
        this.myCircles = new ArrayList<>();
        this.chatRequest = 0;
    }

    public ArrayList<Chats> getLocalChats() {
        return localChats;
    }

    public void setLocalChats(ArrayList<Chats> localChats) {
        this.localChats = localChats;
    }

    public void addNewChat(Chats inChat){
        this.localChats.add(inChat);
    }

    public void leaveCircle(Circle leaveCirc){
        if(myCircles.remove(leaveCirc))
            return;
    }

    public void addMyCircles(Circle circle){
        myCircles.add(circle);
    }

    @Override
    public ArrayList<Circle> getCircList() {
        return myCircles;
    }

    public void setChatRequest(int chatRequest) {
        this.chatRequest = chatRequest;
    }

    public int getChatRequest(){
        return this.chatRequest;
    }

    public void setMyPassword(String inPass){
        this.myPassword = inPass;
    }

    public String getMyPassword(){
        return this.myPassword;
    }
}
