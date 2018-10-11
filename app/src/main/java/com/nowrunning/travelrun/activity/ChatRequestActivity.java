package com.nowrunning.travelrun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.*;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nowrunning.travelrun.R;

public class ChatRequestActivity extends AbsActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);
        final Bundle bundle = new Bundle();
        RelativeLayout included = (RelativeLayout) findViewById(R.id.rl_2);
        ImageButton chatRequestButton = (ImageButton) included.findViewById(R.id.chatRequest);
        chatRequestButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("hi", "hi");
            }
        });
    }
}
