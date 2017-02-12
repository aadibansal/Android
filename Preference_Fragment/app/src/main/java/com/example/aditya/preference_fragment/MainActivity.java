package com.example.aditya.preference_fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        refreshView();

        Button openPreferenceButton = (Button) findViewById(R.id.openPreferenceButton);

        openPreferenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(android.R.id.content, new MyPreferenceFragment())
                        .commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getFragmentManager().findFragmentById(android.R.id.content);
        if (fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
            refreshView();
        } else {
            super.onBackPressed();
        }
    }

    private void refreshView() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        TextView textView = (TextView) findViewById(R.id.usernameTextView);
        View container = findViewById(R.id.container);

        String[] tinColors = getResources().getStringArray(R.array.tint_entries);
        int[] tintColorRes = new int[]{R.color.colorPrimary, R.color.colorPrimaryDark,
                R.color.colorAccent};
        String tintColor = preferences.getString(MyPreferenceFragment.KEY_TINT, tinColors[2]);
        String username = preferences.getString(MyPreferenceFragment.KEY_USERNAME, "");
        container.setBackgroundColor(ContextCompat.getColor(this,
                tintColorRes[Arrays.asList(tinColors).indexOf(tintColor)]));
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        textView.setText(username);
    }
}
