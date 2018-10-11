package com.nowrunning.travelrun.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.bean.fragment.ChatConversationFragment;
import com.nowrunning.travelrun.bean.fragment.ChatFragment;
import com.nowrunning.travelrun.bean.fragment.ContactSelectFragment;
import com.nowrunning.travelrun.bean.fragment.MainActivityFragment;
import com.nowrunning.travelrun.bean.fragment.MyCirclesFragment;
import com.nowrunning.travelrun.bean.fragment.NotificationFragment;
import com.nowrunning.travelrun.bean.fragment.ProfilePhotoFragment;
import com.nowrunning.travelrun.bean.fragment.SettingFragment;
import com.nowrunning.travelrun.bean.fragment.ViewProfileFragment;
import com.nowrunning.travelrun.bean.fragment.YourProfileFragment;

public class MainActivityLauncher extends AbsActivity {
   private TravelPref travelPref;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_launcher);
        travelPref= new TravelPref(this);

        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if(b != null)
        {
            value = b.getInt("screen");
        }

        if (value==1)
        {
            gotoProfileFragment();
        }
        else if (value==2)
        {
            chatConversation();
        }
        else if (value==4)
        {
            notificationFragment();
        }

        else if (value==5)
        {
            viewProfileFragment();
        }
        else if (value==9)
        {
            gotoChatFragment();
        }
        else if (value==10)
        {
            gotoMyCircles();
        }
        else if (value==11)
        {
            chooseContact();
        }
        else if (value==12)
        {
            yourProfileFragment();
        }
        else if (value==14)
        {
            settingFragment();
        }

        else if (value==17)
        {
            profilePhotosFragment();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void gotoChatFragment()
    {
        Bundle args = new Bundle();
        ChatFragment fragTransaction = new ChatFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void viewProfileFragment()
    {
        Bundle args = new Bundle();
        ViewProfileFragment fragTransaction = new ViewProfileFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void chatConversation()
    {
        Bundle args = new Bundle();
        ChatConversationFragment fragTransaction = new ChatConversationFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void notificationFragment()
    {
        Bundle args = new Bundle();
        NotificationFragment fragTransaction = new NotificationFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void yourProfileFragment()
    {
        Bundle args = new Bundle();
        YourProfileFragment fragTransaction = new YourProfileFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void settingFragment()
    {
        Bundle args = new Bundle();
        SettingFragment fragTransaction = new SettingFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void profilePhotosFragment()
    {
        Bundle args = new Bundle();
        ProfilePhotoFragment fragTransaction = new ProfilePhotoFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void gotoProfileFragment()
    {
        Bundle args = new Bundle();
        MainActivityFragment fragTransaction = new MainActivityFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void gotoMyCircles()
    {
        Bundle args = new Bundle();
        MyCirclesFragment fragTransaction = new MyCirclesFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void chooseContact()
    {
        Bundle args = new Bundle();
        ContactSelectFragment fragTransaction = new ContactSelectFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment,fragTransaction);
        fragTransaction.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        /*int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }*/
        finish();
    }
}
