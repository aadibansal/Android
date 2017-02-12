package com.example.aditya.localization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.androidButton:
                    Toast.makeText(MainActivity.this, R.string.android_detail_message, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iOSButton:
                    Toast.makeText(MainActivity.this, R.string.ios_detail_message, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.windowsButton:
                    Toast.makeText(MainActivity.this, R.string.windows_detail_message, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button androidButton = (Button) findViewById(R.id.androidButton);
        Button iOSButton = (Button) findViewById(R.id.iOSButton);
        Button windowsButton = (Button) findViewById(R.id.windowsButton);

        androidButton.setOnClickListener(onClickListener);
        iOSButton.setOnClickListener(onClickListener);
        windowsButton.setOnClickListener(onClickListener);

    }
}
