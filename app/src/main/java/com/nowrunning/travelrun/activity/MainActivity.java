package com.nowrunning.travelrun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.bean.fragment.ChatFragment;
import com.nowrunning.travelrun.bean.fragment.MainActivityFragment;
import com.nowrunning.travelrun.bean.fragment.ViewProfileFragment;
import com.nowrunning.travelrun.bean.fragment.YourProfileFragment;

public class MainActivity extends AbsActivity {
    int screen=1;
    private RelativeLayout rl_screen;
    ImageButton chatRequestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Bundle bundle = new Bundle();

        rl_screen = (RelativeLayout) findViewById(R.id.rl_screen);

        RelativeLayout rl_1 = (RelativeLayout) findViewById(R.id.rl_1);
        rl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 1);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
                //gotoProfileFragment();
            }
        });

        RelativeLayout rl_2 = (RelativeLayout) findViewById(R.id.rl_2);
        rl_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 2);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        RelativeLayout rl_4 = (RelativeLayout) findViewById(R.id.rl_4);
        rl_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 4);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        RelativeLayout rl_5 = (RelativeLayout) findViewById(R.id.rl_5);
        rl_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 5);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        RelativeLayout rl_9 = (RelativeLayout) findViewById(R.id.rl_9);
        rl_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 9);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        RelativeLayout rl_10 = (RelativeLayout) findViewById(R.id.rl_10);
        rl_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 10);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        RelativeLayout rl_11 = (RelativeLayout) findViewById(R.id.rl_11);
        rl_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 11);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        RelativeLayout rl_12 = (RelativeLayout) findViewById(R.id.rl_12);
        rl_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 12);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        RelativeLayout rl_14 = (RelativeLayout) findViewById(R.id.rl_14);
        rl_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 14);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        RelativeLayout rl_17 = (RelativeLayout) findViewById(R.id.rl_17);
        rl_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityLauncher.class);
                bundle.putInt("screen", 17);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });



        screen=12;

       /* if (screen==1)
            gotoProfileFragment();
        else if (screen==5)
            viewProfileFragment();
        else if (screen==12)
            yourProfileFragment();

        else if (screen==9)
            gotoChatFragment();*/
    }

    /*@Override
    public void onResume(){
        super.onResume();
        //rl_screen.setVisibility(View.VISIBLE);

    }

    @Override
    public void onStart(){
        super.onStart();
        rl_screen.setVisibility(View.VISIBLE);

    }


    @Override
    public void onRestart(){
        super.onRestart();
        rl_screen.setVisibility(View.VISIBLE);

    }

    @Override
    public void onPause(){
        super.onPause();
        rl_screen.setVisibility(View.VISIBLE);
    }*/


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
}
