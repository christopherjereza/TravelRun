package com.nowrunning.travelrun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nowrunning.travelrun.adapter.ChatMessagesSingleAdapter;
import com.nowrunning.travelrun.adapter.CircleAdapter;
import com.nowrunning.travelrun.util.Chats;

import java.util.ArrayList;

public class ChatScreenSingle extends AbsActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen_single);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = (RecyclerView) findViewById(R.id.CircRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        rv.setLayoutManager(layoutManager);
        Chats pulledChat = (OwnerProfile.getLocalChats().get(OwnerProfile.getChatRequest()));
        ChatMessagesSingleAdapter adapter = new ChatMessagesSingleAdapter(pulledChat, this);
        rv.setAdapter(adapter);


    }

}
