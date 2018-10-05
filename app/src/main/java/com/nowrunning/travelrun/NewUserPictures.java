package com.nowrunning.travelrun;

import android.content.Intent;
import android.os.Bundle;

public class NewUserPictures extends AbsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_pictures);

        Intent i = new Intent(this, MainActivity.class);
    }
}
