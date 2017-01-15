package com.example.aditya.android_life_cycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayMessage("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayMessage("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayMessage("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        displayMessage("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        displayMessage("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        displayMessage("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        displayMessage("onDestroy");
    }

    private void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
