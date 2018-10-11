package com.nowrunning.travelrun.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/***Preference Class for save data of server***/
public class TravelPref {
	private SharedPreferences prefs;

	private static final String status_travelling = "data.first.status.travelling";
    private static final String profile_visible = "data.first.status.visible_profile";

	private static final String radio1= "data.first.status.radio1.1";
	private static final String radio2= "data.first.status.radio2.1";
    private static final String radio3= "data.first.status.radio3.1";
    private static final String radio4= "data.first.status.radio4.1";
    private static final String radio5= "data.first.status.radio5.1";
    private static final String radio6= "data.first.status.radio6.1";
    private static final String radio7= "data.first.status.radio7.1";
    private static final String radio8= "data.first.status.radio8.1";
    private static final String radio9= "data.first.status.radio9.1";
    private static final String radio10= "data.first.status.radio10.1";

    private static final String radio1a= "data.first.status.radio1a.1";
    private static final String radio2a= "data.first.status.radio2a.1";
    private static final String radio3a= "data.first.status.radio3a.1";
    private static final String radio4a= "data.first.status.radio4a.1";
    private static final String radio5a= "data.first.status.radio5a.1";
    private static final String radio6a= "data.first.status.radio6a.1";
    private static final String radio7a= "data.first.status.radio7a.1";
    private static final String radio8a= "data.first.status.radio8a.1";
    private static final String radio9a= "data.first.status.radio9a.1";
    private static final String radio10a= "data.first.status.radio10a.1";


	public TravelPref(Context context) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
	}


    public boolean getProfileVisible() {
        return prefs.getBoolean(profile_visible, true);
    }

    public void setProfileVisible(boolean value) {
        prefs.edit().putBoolean(profile_visible, value).commit();
    }

	public boolean getRadio1a() {
		return prefs.getBoolean(radio1a, true);
	}

	public void setRadio1a(boolean value) {
		prefs.edit().putBoolean(radio1a, value).commit();
	}

	public boolean getRadio2a() {
		return prefs.getBoolean(radio2a, false);
	}

	public void setRadio2a(boolean value) {
		prefs.edit().putBoolean(radio2a, value).commit();
	}

    public boolean getRadio3a() {
        return prefs.getBoolean(radio3a, false);
    }

    public void setRadio3a(boolean value) {
        prefs.edit().putBoolean(radio3a, value).commit();
    }

    public boolean getRadio4a() {
        return prefs.getBoolean(radio4a, false);
    }

    public void setRadio4a(boolean value) {
        prefs.edit().putBoolean(radio4a, value).commit();
    }

    public boolean getRadio5a() {
        return prefs.getBoolean(radio5a, false);
    }

    public void setRadio5a(boolean value) {
        prefs.edit().putBoolean(radio5a, value).commit();
    }

    public boolean getRadio6a() {
        return prefs.getBoolean(radio6a, false);
    }

    public void setRadio6a(boolean value) {
        prefs.edit().putBoolean(radio6a, value).commit();
    }

    public boolean getRadio7a() {
        return prefs.getBoolean(radio7a, false);
    }

    public void setRadio7a(boolean value) {
        prefs.edit().putBoolean(radio7a, value).commit();
    }

    public boolean getRadio8a() {
        return prefs.getBoolean(radio8a, false);
    }

    public void setRadio8a(boolean value) {
        prefs.edit().putBoolean(radio8a, value).commit();
    }

    public boolean getRadio9a() {
        return prefs.getBoolean(radio9a, false);
    }

    public void setRadio9a(boolean value) {
        prefs.edit().putBoolean(radio9a, value).commit();
    }

    public boolean getRadio10a() {
        return prefs.getBoolean(radio10a, false);
    }

    public void setRadio10a(boolean value) {
        prefs.edit().putBoolean(radio10a, value).commit();
    }

    public boolean getStatusTravel() {
		return prefs.getBoolean(status_travelling, false);
	}

	public void setStatusTravel(boolean value) {
		prefs.edit().putBoolean(status_travelling, value).commit();
	}

    public boolean getRadio1() {
        return prefs.getBoolean(radio1, true);
    }

    public void setRadio1(boolean value) {
        prefs.edit().putBoolean(radio1, value).commit();
    }

    public boolean getRadio2() {
        return prefs.getBoolean(radio2, false);
    }

    public void setRadio2(boolean value) {
        prefs.edit().putBoolean(radio2, value).commit();
    }

    public boolean getRadio3() {
        return prefs.getBoolean(radio3, false);
    }

    public void setRadio3(boolean value) {
        prefs.edit().putBoolean(radio3, value).commit();
    }

    public boolean getRadio4() {
        return prefs.getBoolean(radio4, false);
    }

    public void setRadio4(boolean value) {
        prefs.edit().putBoolean(radio4, value).commit();
    }

    public boolean getRadio5() {
        return prefs.getBoolean(radio5, false);
    }

    public void setRadio5(boolean value) {
        prefs.edit().putBoolean(radio5, value).commit();
    }

    public boolean getRadio6() {
        return prefs.getBoolean(radio6, false);
    }

    public void setRadio6(boolean value) {
        prefs.edit().putBoolean(radio6, value).commit();
    }

    public boolean getRadio7() {
        return prefs.getBoolean(radio7, false);
    }

    public void setRadio7(boolean value) {
        prefs.edit().putBoolean(radio7, value).commit();
    }

    public boolean getRadio8() {
        return prefs.getBoolean(radio8, false);
    }

    public void setRadio8(boolean value) {
        prefs.edit().putBoolean(radio8, value).commit();
    }

    public boolean getRadio9() {
        return prefs.getBoolean(radio9, false);
    }

    public void setRadio9(boolean value) {
        prefs.edit().putBoolean(radio9, value).commit();
    }

    public boolean getRadio10() {
        return prefs.getBoolean(radio10, false);
    }

    public void setRadio10(boolean value) {
        prefs.edit().putBoolean(radio10, value).commit();
    }

}
