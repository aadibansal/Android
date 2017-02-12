package com.example.aditya.broadcast;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private static final String[] REQUIRED_PERMISSIONS = new String[]
            {Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS};
    private static final int PERMISSION_REQUEST_CODE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EasyPermissions.hasPermissions(this, REQUIRED_PERMISSIONS)){
            EasyPermissions.requestPermissions(this, "Please provide permission to read sms message!", PERMISSION_REQUEST_CODE, REQUIRED_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
