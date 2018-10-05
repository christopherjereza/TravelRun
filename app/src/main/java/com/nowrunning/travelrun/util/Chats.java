package com.nowrunning.travelrun.util;

import java.util.ArrayList;

public class Chats {

    private ArrayList<ChatsMessages> sentMessages;

    private Profile person;

    private boolean sentTo;
    private boolean accepted;
    private boolean newMSG;

    public Chats(){
       this.sentMessages = new ArrayList<ChatsMessages>();
    }

    public void setPerson(Profile person) {
        this.person = person;
    }

    public Profile getPerson() {
        return person;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isNewMSG() {
        return newMSG;
    }

    public boolean isSentTo() {
        return sentTo;
    }

    public ArrayList<ChatsMessages> getSentMessages() {
        return sentMessages;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setNewMSG(boolean newMSG) {
        this.newMSG = newMSG;
    }

    public void setSentMessages(ArrayList<ChatsMessages> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public void setSentTo(boolean sentTo) {
        this.sentTo = sentTo;
    }

    public void addNewMessage(ChatsMessages inMsg){
        this.sentMessages.add(0, inMsg);
    }


}
