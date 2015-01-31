package com.example.danco.homework4.h254danco.fragment;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.danco.homework4.h254danco.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
