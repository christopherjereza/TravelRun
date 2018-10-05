package com.nowrunning.travelrun;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.TextView;

import com.nowrunning.travelrun.adapter.RecyclerViewRequests;
import com.nowrunning.travelrun.util.Chats;

import java.util.ArrayList;

public class StartedChatsActivity extends AbsActivity {

    RecyclerView rv;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_chats);
        rv = (RecyclerView) findViewById(R.id.recyclerRequests);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);
        ArrayList pulledChats = pullUnansweredChats(OwnerProfile.getLocalChats());
        final RecyclerViewRequests adapter = new RecyclerViewRequests(pulledChats);
        rv.setAdapter(adapter);

        TextView requestNum = (TextView)findViewById(R.id.requestNum);
        SearchManager searchManager = (SearchManager) getSystemService(getBaseContext().SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.searchText);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        //OwnerProfile

        requestNum.setText(Integer.toString(pulledChats.size()));

    }

    private ArrayList<Chats> pullUnansweredChats(ArrayList<Chats> localChats) {
        ArrayList<Chats> unansChats = new ArrayList<Chats>();
        for(int ii = 0; ii < localChats.size(); ii++){
            if(!localChats.get(ii).isAccepted()){
                unansChats.add(localChats.get(ii));
            }
        }
        return unansChats;
    }

}
