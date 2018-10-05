package com.nowrunning.travelrun.util;

import com.nowrunning.travelrun.util.Chats;
import com.nowrunning.travelrun.util.ChatsMessages;
import com.nowrunning.travelrun.util.Profile;

import java.util.ArrayList;

public class CircleChats extends Chats {

    private ArrayList<Profile> circMember;

    public Profile getPerson(ChatsMessages inMSG) {
        return inMSG.getSent();
    }

    public Profile getMember(int memberNumb){
        return circMember.get(memberNumb);
    }

}
