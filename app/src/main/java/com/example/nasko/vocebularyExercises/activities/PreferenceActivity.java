package com.example.nasko.vocebularyExercises.activities;

import android.os.Bundle;

import com.example.nasko.vocebularyExercises.R;

/**
 * Created by nasko on 28.3.2017 г..
 */

public class PreferenceActivity extends android.preference.PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }


}
