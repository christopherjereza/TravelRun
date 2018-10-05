package com.nowrunning.travelrun.util;

public class ChatsMessages {

    private String msgText;

    private Profile sent;

    ChatsMessages(Profile sender){
        this.msgText = "";
        this.sent = sender;
    }

    ChatsMessages(Profile sender, String msg){
        this.msgText = msg;
        this.sent = sender;
    }

    public void setSent(Profile sent) {
        this.sent = sent;
    }

    public Profile getSent() {
        return sent;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getMsgText() {
        return msgText;
    }

}
