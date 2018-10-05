package com.nowrunning.travelrun;

import android.app.ActionBar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nowrunning.travelrun.adapter.profileAdapter;
import com.nowrunning.travelrun.adapter.textProfileAdapter;

import java.util.ArrayList;

public class otherPersonProfile extends AbsActivity {

    private ViewPager images;
    private ViewPager profText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person_profile);


        final ActionBar actionBar = getActionBar();

        ArrayList intsArray = new ArrayList<Integer>();

        intsArray.add(new Integer(R.drawable.timelesstoday));
        intsArray.add(new Integer(R.drawable.download));

        images = (ViewPager)findViewById(R.id.mviewwOtherPerson);

        images.setAdapter(new profileAdapter(this, intsArray));

        profText = (ViewPager)findViewById(R.id.textSwipeOtherPerson);

        ArrayList<String> textArray = new ArrayList<>();

        textArray.add("aodskjf up");

        textArray.add("happen happen happenns stoasidfjaposidjf a asdkfj asdlfk jasd;lfk jas;ldkf jas;ldk fja;lsd kjf;alskdjf  as;ldkfj" +
                "alskdjf ;alskdj f\nalksd jfa;lksdj faskldfj" +
                "asldkfj a\n;sldkf ja;lskd jfaskdjf" +
                "a;lskdjf;alksdjfaasdf asdfasdf" +
                "asdfasdfasdf" +
                "asdfasdfasd\nfa" +
                "fasdfasdfasdfasdgfqrg4ethb rthbwregtbwetgwqetgqrg/n" +
                "werfgwefgwrhtujjujjj`\n u" +
                "dgfhdrjdtukjtehje" +
                "yjer\nthe\nryu jryjdt7ir6" +
                "wrtheyjetynefghje" +
                "wrethewrjyetyjetyj" +
                "hhh.");



        profText.setAdapter(new textProfileAdapter(this, textArray));

    }
}
