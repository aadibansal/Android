package com.example.aditya.preference_fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by Aditya on 2/7/2017.
 */

public class MyPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    public static final String KEY_TINT = "tintColorKey";
    public static final String KEY_USERNAME = "usernameKey";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            view.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.white));
        }
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Preference tintPreference = findPreference(KEY_TINT);
        Preference usernamePreference = findPreference(KEY_USERNAME);

        SharedPreferences preferences = getDefaultSharedPreferences(getActivity());
        String[] tintValues = getResources().getStringArray(R.array.tint_entries);
        if (preferences.contains(KEY_TINT)) {
            tintPreference.setSummary(preferences.getString(KEY_TINT, tintValues[2]));
        }
        if (preferences.contains(KEY_USERNAME)) {
            usernamePreference.setSummary(preferences.getString(KEY_USERNAME, ""));
        }

        tintPreference.setOnPreferenceChangeListener(this);
        usernamePreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        preference.setSummary(String.valueOf(newValue));
        return true;
    }
}
