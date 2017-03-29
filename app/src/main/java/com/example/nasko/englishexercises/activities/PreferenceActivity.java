package com.example.nasko.englishexercises.activities;

import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.Nullable;

import com.example.nasko.englishexercises.R;

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
