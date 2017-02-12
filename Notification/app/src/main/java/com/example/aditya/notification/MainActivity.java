package com.example.aditya.notification;

import android.app.NotificationManager;
import android.os.PersistableBundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String USERNAME = "edureka";
    private static final String PASSWORD = "edureka123";

    private static final int NOTIFICATION_ID = new Random().nextInt(10000);
    private NotificationManager notificationManager;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private static final String ARG_USERNAME = "username";
    private static final String ARG_PASSWORD = "password";

    enum LoginResult {
        SUCCESS, FAILED
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        usernameEditText = (EditText) findViewById(R.id.userName);
        passwordEditText = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.button);
    }
    public void validateUser(View v){
        String username = getInputText(usernameEditText);
        String password = getInputText(passwordEditText);

        if (username == null) {
            usernameEditText.setError(getString(R.string.user_name));
            return;
        }
        if (password == null) {
            passwordEditText.setError(getString(R.string.password));
            return;
        }
        if (!username.equals(USERNAME)) {
            displayNotificationMessage(LoginResult.FAILED, R.string.in_userName);
            return;
        }
        if (!password.equals(PASSWORD)) {
            displayNotificationMessage(LoginResult.FAILED, R.string.in_password);
            return;
        }

        displayNotificationMessage(LoginResult.SUCCESS, R.string.login_success);
        loginButton.setEnabled(false);
    }

    private void displayNotificationMessage(@NonNull LoginResult loginResult, @StringRes int messageRes) {
        @DrawableRes int icon = loginResult == LoginResult.SUCCESS ? R.drawable.ic_login_success :
                R.drawable.ic_login_failed;
        CharSequence title = loginResult == LoginResult.SUCCESS ? getString(R.string.login_success) :
                getString(R.string.login_failed);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker(getString(messageRes))
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(getString(messageRes));
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private String getInputText(TextView textView) {
        if (textView == null || TextUtils.isEmpty(textView.getText())) {
            return null;
        }
        return textView.getText().toString();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(ARG_USERNAME, getInputText(usernameEditText));
        outState.putString(ARG_PASSWORD, getInputText(passwordEditText));
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            if(savedInstanceState.containsKey(ARG_USERNAME)){
                usernameEditText.setText(savedInstanceState.getString(ARG_USERNAME));
            }
            if(savedInstanceState.containsKey(ARG_PASSWORD)){
                passwordEditText.setText(savedInstanceState.getString(ARG_PASSWORD));
            }
        }
    }
}
