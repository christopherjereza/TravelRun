package com.nowrunning.travelrun.bean.fragment;

import android.content.Context;

import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;

import android.support.v7.widget.SwitchCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;

import com.jaygoo.widget.RangeSeekBar;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.TravelPref;


/**
 * A placeholder fragment containing a simple view.
 */
public class SettingFragment extends Fragment implements View.OnClickListener{
    private View view;
    private TextView tv_age_range_value,tv_public_profile ;
    private Typeface fontSemi,fontRegular;
    public SettingFragment() {
    }
    Context context;
    private RangeSeekBar seekbar3,seekbar4;
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    private ProgressBar pg_icon;
    private RadioButton radio_miles,radio_kilometers;
    private TravelPref travelPref;
    private SwitchCompat sc_traveling,sc_profile_vis;
    private TextView tv_currently_traveling;
    private RelativeLayout rl_languaje_1,rl_languaje_2;
    private RadioButton radio1,radio2,radio3,radio4,radio5,radio6,radio7,radio8,radio9,radio10;
    private RadioButton radio1a,radio2a,radio3a,radio4a,radio5a,radio6a,radio7a,radio8a,radio9a,radio10a;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting_fragment,container,false);
        pg_icon = (ProgressBar) view.findViewById(R.id.pg_icon);
        //pg_icon.setVisibility(View.VISIBLE);
        travelPref= new TravelPref(getActivity());

        rl_languaje_1 = (RelativeLayout)view.findViewById(R.id.rl_languaje_1);
        rl_languaje_2 = (RelativeLayout)view.findViewById(R.id.rl_languaje_2 );
        rl_languaje_1.setVisibility(View.GONE);
        rl_languaje_2.setVisibility(View.GONE);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(),  "proximasoft_medium.otf");
        fontRegular = Typeface.createFromAsset(getActivity().getAssets(),  "proximasoft_regular.otf");
        fontSemi = Typeface.createFromAsset(getActivity().getAssets(), "proximasoft_semibold.otf");

        TextView tv_title_1 = (TextView)view.findViewById(R.id.tv_title_1);
        TextView tv_age_range = (TextView)view.findViewById(R.id.tv_age_range);

        setRadios();


        CardView cv_language = (CardView)view.findViewById(R.id.cv_language);
        cv_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rl_languaje_1.getVisibility()==View.GONE)
                    rl_languaje_1.setVisibility(View.VISIBLE);
                else
                    rl_languaje_1.setVisibility(View.GONE);
            }
        });

        CardView cv_language_filter = (CardView)view.findViewById(R.id.cv_language_filter);
        cv_language_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rl_languaje_2.getVisibility()==View.GONE)
                    rl_languaje_2.setVisibility(View.VISIBLE);
                else
                    rl_languaje_2.setVisibility(View.GONE);
            }
        });

        radio_miles= (RadioButton)view.findViewById(R.id.radio_miles);
        radio_kilometers= (RadioButton)view.findViewById(R.id.radio_kilometers);
        radio_kilometers.setChecked(true);
        statusChecked();


        tv_age_range_value = (TextView)view.findViewById(R.id.tv_age_range_value);
        tv_age_range.setTypeface(fontRegular);
        tv_age_range.setTextSize(18);
        tv_age_range_value.setTypeface(fontSemi);
        tv_age_range_value.setTextSize(21);

        TextView tv_speak_l = (TextView)view.findViewById(R.id.tv_speak_l);
        TextView tv_filter_1 = (TextView)view.findViewById(R.id.tv_filter_1);

        TextView tv_language_filter = (TextView)view.findViewById(R.id.tv_language_filter);
        TextView tv_language = (TextView)view.findViewById(R.id.tv_language);

        tv_currently_traveling = (TextView)view.findViewById(R.id.tv_currently_traveling);
        tv_public_profile = (TextView)view.findViewById(R.id.tv_public_profile);
        TextView tv_mute_not = (TextView)view.findViewById(R.id.tv_mute_not);

        TextView tv_travel_run= (TextView)view.findViewById(R.id.tv_travel_run);
        TextView tv_report_problem= (TextView)view.findViewById(R.id.tv_report_problem);
        TextView tv_report_user= (TextView)view.findViewById(R.id.tv_report_user);
        TextView tv_where_from= (TextView)view.findViewById(R.id.tv_where_from);
        TextView tv_world2= (TextView)view.findViewById(R.id.tv_world2);

        EditText tv_world3= (EditText)view.findViewById(R.id.tv_world3);
        tv_world3.setTextSize(15);
        tv_world3.setTypeface(fontRegular);


        sc_traveling= (SwitchCompat)view.findViewById(R.id.sc_traveling);
        sc_traveling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {

                if (isChecked) {
                    //if 'isChecked' is true do whatever you need...
                    Log.i("Tag","");
                    travelPref.setStatusTravel(true);

                    tv_currently_traveling.setText(R.string.label_currently_traveling);
                }
                else {
                    travelPref.setStatusTravel(false);
                    tv_currently_traveling.setText(R.string.label_currently_hosting);
                    Log.i("Tag","");
                }
            }

        });

        sc_profile_vis= (SwitchCompat)view.findViewById(R.id.sc_profile_vis);
        sc_profile_vis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked) {
                    travelPref.setProfileVisible(true);
                    tv_public_profile.setText(R.string.label_private_profile);
                }
                else {
                    travelPref.setProfileVisible(false);
                    tv_public_profile.setText(R.string.label_public_profile);
                }
            }

        });


        if (travelPref.getStatusTravel())
        {
            sc_traveling.setChecked(true);
            tv_currently_traveling.setText(R.string.label_currently_traveling);
        }
        else
        {
            tv_currently_traveling.setText(R.string.label_currently_hosting);
            sc_traveling.setChecked(false);
        }



        tv_world2.setTextSize(15);
        tv_world2.setTypeface(fontRegular);

        tv_where_from.setTextSize(15);
        tv_where_from.setTypeface(fontRegular);

        tv_travel_run.setTypeface(fontRegular);
        tv_travel_run.setTextSize(18);

        tv_report_problem.setTypeface(fontRegular);
        tv_report_problem.setTextSize(18);

        tv_report_user.setTypeface(fontRegular);
        tv_report_user.setTextSize(18);

        TextView tv_km = (TextView)view.findViewById(R.id.tv_km);
        RadioButton radio_kilometers = (RadioButton)view.findViewById(R.id.radio_kilometers);
        RadioButton radio_miles = (RadioButton)view.findViewById(R.id.radio_miles);
        tv_km.setTypeface(fontRegular);
        tv_km.setTextSize(18);

        radio_kilometers.setTypeface(fontRegular);
        radio_kilometers.setTextSize(18);

        radio_miles.setTypeface(fontRegular);
        radio_miles.setTextSize(18);

        tv_mute_not.setTypeface(fontRegular);
        tv_mute_not.setTextSize(15);

        tv_currently_traveling.setTypeface(fontRegular);
        tv_currently_traveling.setTextSize(15);

        tv_public_profile.setTypeface(fontRegular);
        tv_public_profile.setTextSize(15);


        tv_language_filter.setTypeface(fontRegular);
        tv_language_filter.setTextSize(18);

        tv_language.setTypeface(fontRegular);
        tv_language.setTextSize(18);


        tv_speak_l.setTypeface(fontSemi);
        tv_speak_l.setTextSize(12);

        tv_filter_1.setTypeface(fontSemi);
        tv_filter_1.setTextSize(12);



        context = getActivity().getApplicationContext();
        getBubble();
        return view;
    }

    private void setRadios()
    {
        radio1 = (RadioButton)view.findViewById(R.id.radio1);
        radio2 = (RadioButton)view.findViewById(R.id.radio2);
        radio3 = (RadioButton)view.findViewById(R.id.radio3);
        radio4 = (RadioButton)view.findViewById(R.id.radio4);
        radio5 = (RadioButton)view.findViewById(R.id.radio5);
        radio6 = (RadioButton)view.findViewById(R.id.radio6);
        radio7 = (RadioButton)view.findViewById(R.id.radio7);
        radio8 = (RadioButton)view.findViewById(R.id.radio8);
        radio9 = (RadioButton)view.findViewById(R.id.radio9);
        radio10 = (RadioButton)view.findViewById(R.id.radio10);

        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        radio3.setOnClickListener(this);
        radio4.setOnClickListener(this);
        radio5.setOnClickListener(this);
        radio6.setOnClickListener(this);
        radio7.setOnClickListener(this);
        radio8.setOnClickListener(this);
        radio9.setOnClickListener(this);
        radio10.setOnClickListener(this);

        refreshRadios();

        radio1a = (RadioButton)view.findViewById(R.id.radio1a);
        radio2a = (RadioButton)view.findViewById(R.id.radio2a);
        radio3a = (RadioButton)view.findViewById(R.id.radio3a);
        radio4a = (RadioButton)view.findViewById(R.id.radio4a);
        radio5a = (RadioButton)view.findViewById(R.id.radio5a);
        radio6a = (RadioButton)view.findViewById(R.id.radio6a);
        radio7a = (RadioButton)view.findViewById(R.id.radio7a);
        radio8a = (RadioButton)view.findViewById(R.id.radio8a);
        radio9a = (RadioButton)view.findViewById(R.id.radio9a);
        radio10a = (RadioButton)view.findViewById(R.id.radio10a);

        radio1a.setOnClickListener(this);
        radio2a.setOnClickListener(this);
        radio3a.setOnClickListener(this);
        radio4a.setOnClickListener(this);
        radio5a.setOnClickListener(this);
        radio6a.setOnClickListener(this);
        radio7a.setOnClickListener(this);
        radio8a.setOnClickListener(this);
        radio9a.setOnClickListener(this);
        radio10a.setOnClickListener(this);

        refreshRadios2();
    }



    private void getBubble()
    {
        seekbar3= (RangeSeekBar) view.findViewById(R.id.seekbar3);
        seekbar3.setValue(18,30);

        seekbar3.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
//                if(isFromUser){

                String smax=""+(int)max;
                String smin=""+(int)min;
                tv_age_range_value.setText(""+smin+"-"+smax);


                if(max>=56){
                    seekbar3.setRightProgressDescription("55+");
                    tv_age_range_value.setText(""+smin+"-"+"55+");
                }else {
                    seekbar3.setRightProgressDescription((int)max+"");

                }

//                }
            }
        });

        seekbar3.setRight(0);

        seekbar4= (RangeSeekBar) view.findViewById(R.id.seekbar4);
        seekbar4.setValue(40);
        seekbar4.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
//                if(isFromUser){

                if(max==31){
                    seekbar4.setRightProgressDescription("");
                }else {
                    seekbar4.setRightProgressDescription((int)max+"");

                }

//                }
            }
        });


        RadioGroup radio = (RadioGroup) view.findViewById(R.id.opcion_distance);

        int selectedId=radio.getCheckedRadioButtonId();
        int n=0;
        if(selectedId==R.id.radio_miles)
        {
            seekbar4.setRange(1,101);

        }
        else if(selectedId==R.id.radio_kilometers)
        {
            seekbar4.setRange(1,160);
        }

    }


    private void statusChecked()
    {
        final RadioGroup radio = (RadioGroup) view.findViewById(R.id.opcion_distance);

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

                // Add logic here

                switch (index) {
                    case 0: // kilometers
                        seekbar4.setValue(1);
                        seekbar4.setRange(1,161);
                        break;
                    case 1: // miles
                        seekbar4.setValue(1);
                        seekbar4.setRange(1,101);
                        //Toast.makeText(getActivity(), "Selected button number " + index, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }


   /* @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.radio1) {
                radio1.setChecked(false);
            }
            if (buttonView.getId() == R.id.radio2) {
                radio2.setChecked(true);
            }
        }
        else
        {
            Log.i("","");
        }
    }*/

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio1:
                // set inch button to unchecked
                radio1.setChecked(!checked);
                break;
            case R.id.radio2:
                // set MM button to unchecked
                radio2.setChecked(!checked);
                break;
        }
    }

    private void refreshRadios()
    {
        if (travelPref.getRadio1())
        {
            radio1.setChecked(true);
        }
        else
        {
            radio1.setChecked(false);
        }


        if (travelPref.getRadio2())
        {
            radio2.setChecked(true);
        }
        else
        {
            radio2.setChecked(false);
        }

        if (travelPref.getRadio3())
        {
            radio3.setChecked(true);
        }
        else
        {
            radio3.setChecked(false);
        }

        if (travelPref.getRadio4())
        {
            radio4.setChecked(true);
        }
        else
        {
            radio4.setChecked(false);
        }

        if (travelPref.getRadio5())
        {
            radio5.setChecked(true);
        }
        else
        {
            radio5.setChecked(false);
        }

        if (travelPref.getRadio6())
        {
            radio6.setChecked(true);
        }
        else
        {
            radio6.setChecked(false);
        }

        if (travelPref.getRadio7())
        {
            radio7.setChecked(true);
        }
        else
        {
            radio7.setChecked(false);
        }

        if (travelPref.getRadio8())
        {
            radio8.setChecked(true);
        }
        else
        {
            radio8.setChecked(false);
        }

        if (travelPref.getRadio9())
        {
            radio9.setChecked(true);
        }
        else
        {
            radio9.setChecked(false);
        }

        if (travelPref.getRadio10())
        {
            radio10.setChecked(true);
        }
        else
        {
            radio10.setChecked(false);
        }

    }

    private void refreshRadios2()
    {
        if (travelPref.getRadio1a())
        {
            radio1a.setChecked(true);
        }
        else
        {
            radio1a.setChecked(false);
        }


        if (travelPref.getRadio2a())
        {
            radio2a.setChecked(true);
        }
        else
        {
            radio2a.setChecked(false);
        }

        if (travelPref.getRadio3a())
        {
            radio3a.setChecked(true);
        }
        else
        {
            radio3a.setChecked(false);
        }

        if (travelPref.getRadio4a())
        {
            radio4a.setChecked(true);
        }
        else
        {
            radio4a.setChecked(false);
        }

        if (travelPref.getRadio5a())
        {
            radio5a.setChecked(true);
        }
        else
        {
            radio5a.setChecked(false);
        }

        if (travelPref.getRadio6a())
        {
            radio6a.setChecked(true);
        }
        else
        {
            radio6a.setChecked(false);
        }

        if (travelPref.getRadio7a())
        {
            radio7a.setChecked(true);
        }
        else
        {
            radio7a.setChecked(false);
        }

        if (travelPref.getRadio8a())
        {
            radio8a.setChecked(true);
        }
        else
        {
            radio8a.setChecked(false);
        }

        if (travelPref.getRadio9a())
        {
            radio9a.setChecked(true);
        }
        else
        {
            radio9a.setChecked(false);
        }

        if (travelPref.getRadio10a())
        {
            radio10a.setChecked(true);
        }
        else
        {
            radio10a.setChecked(false);
        }

    }


    @Override
    public void onClick(View v)
    {
        //boolean checked1 = ((RadioButton) radio1).isChecked();
        //boolean checked2 = ((RadioButton) radio2).isChecked();

        switch (v.getId())
        {
            case R.id.radio1:
                if (travelPref.getRadio1())
                    travelPref.setRadio1(false);
                else
                    travelPref.setRadio1(true);

                break;
            case R.id.radio2:
                if (travelPref.getRadio2())
                    travelPref.setRadio2(false);
                else
                    travelPref.setRadio2(true);
                break;

            case R.id.radio3:
                if (travelPref.getRadio3())
                    travelPref.setRadio3(false);
                else
                    travelPref.setRadio3(true);
                break;

            case R.id.radio4:
                if (travelPref.getRadio4())
                    travelPref.setRadio4(false);
                else
                    travelPref.setRadio4(true);
                break;


            case R.id.radio5:
                if (travelPref.getRadio5())
                    travelPref.setRadio5(false);
                else
                    travelPref.setRadio5(true);
                break;


            case R.id.radio6:
                if (travelPref.getRadio6())
                    travelPref.setRadio6(false);
                else
                    travelPref.setRadio6(true);
                break;

            case R.id.radio7:
                if (travelPref.getRadio7())
                    travelPref.setRadio7(false);
                else
                    travelPref.setRadio7(true);
                break;


            case R.id.radio8:
                if (travelPref.getRadio8())
                    travelPref.setRadio8(false);
                else
                    travelPref.setRadio8(true);
                break;


            case R.id.radio9:
                if (travelPref.getRadio9())
                    travelPref.setRadio9(false);
                else
                    travelPref.setRadio9(true);
                break;


            case R.id.radio10:
                if (travelPref.getRadio10())
                    travelPref.setRadio10(false);
                else
                    travelPref.setRadio10(true);
                break;



            case R.id.radio1a:
                if (travelPref.getRadio1a())
                    travelPref.setRadio1a(false);
                else
                    travelPref.setRadio1a(true);

                break;
            case R.id.radio2a:
                if (travelPref.getRadio2a())
                    travelPref.setRadio2a(false);
                else
                    travelPref.setRadio2a(true);
                break;

            case R.id.radio3a:
                if (travelPref.getRadio3a())
                    travelPref.setRadio3a(false);
                else
                    travelPref.setRadio3a(true);
                break;

            case R.id.radio4a:
                if (travelPref.getRadio4a())
                    travelPref.setRadio4a(false);
                else
                    travelPref.setRadio4a(true);
                break;


            case R.id.radio5a:
                if (travelPref.getRadio5a())
                    travelPref.setRadio5a(false);
                else
                    travelPref.setRadio5a(true);
                break;


            case R.id.radio6a:
                if (travelPref.getRadio6a())
                    travelPref.setRadio6a(false);
                else
                    travelPref.setRadio6a(true);
                break;

            case R.id.radio7a:
                if (travelPref.getRadio7a())
                    travelPref.setRadio7a(false);
                else
                    travelPref.setRadio7a(true);
                break;


            case R.id.radio8a:
                if (travelPref.getRadio8a())
                    travelPref.setRadio8a(false);
                else
                    travelPref.setRadio8a(true);
                break;


            case R.id.radio9a:
                if (travelPref.getRadio9a())
                    travelPref.setRadio9a(false);
                else
                    travelPref.setRadio9a(true);
                break;


            case R.id.radio10a:
                if (travelPref.getRadio10a())
                    travelPref.setRadio10a(false);
                else
                    travelPref.setRadio10a(true);
                break;

        }

        refreshRadios();
        refreshRadios2();
    }
}

