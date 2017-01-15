package com.example.aditya.basic_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String userName = "edureka";
    private String password = "edureka123";

    EditText user, paswrd;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText) findViewById(R.id.userName);
        paswrd = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.login);
    }


    public void login(View view) {
        String user_name = getText(user);
        String pass = getText(paswrd);
        boolean user_check = false, pass_check = false, error_check = false;
        if(user_name == null){
            user.setError(getString(R.string.empty_username));
            user_check = true;
            error_check = true;
        }
        if(pass == null){
            paswrd.setError(getString(R.string.empty_password));
            pass_check = true;
            error_check = true;
        }
        if(!user_check && !user_name.equals(userName)){
            user.setError(getString(R.string.wrong_username));
            error_check = true;
        }
        if(!pass_check && !pass.equals(password)){
            paswrd.setError(getString(R.string.wrong_password));
            error_check = true;
        }

        if(error_check){
            return;
        }
        Toast.makeText(this, R.string.success_login, Toast.LENGTH_LONG).show();
        loginButton.setEnabled(false);
    }

    private String getText(TextView textView){
        if(textView == null || TextUtils.isEmpty(textView.getText().toString().trim())){
            return null;
        }else{
            return textView.getText().toString().trim();
        }
    }
}
