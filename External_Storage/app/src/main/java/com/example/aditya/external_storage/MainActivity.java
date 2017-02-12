package com.example.aditya.external_storage;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "sample.txt";

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        try {
            File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
            FileInputStream stream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            String line = bufferedReader.readLine();
            if (!TextUtils.isEmpty(line)) {
                User savedUser = new GsonBuilder().create().fromJson(line, User.class);
                usernameEditText.setText(savedUser.username);
                passwordEditText.setText(savedUser.password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateUser(View view) {
        if(!isExternalStorage()){
            Toast.makeText(this, "Please give permission to write external storage", Toast.LENGTH_LONG).show();
            return;
        }
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return;
        }

        try {
            User user = new User(username, password);
            File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
            if(!file.exists()){
                file.createNewFile();
            }

            FileOutputStream stream = new FileOutputStream(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(stream));
            String contents = new GsonBuilder().create().toJson(user);
            bufferedWriter.write(contents);
            bufferedWriter.close();
            Toast.makeText(this, "Thanks for login in!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isExternalStorage(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    class User {
        final String username;
        final String password;

        User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
