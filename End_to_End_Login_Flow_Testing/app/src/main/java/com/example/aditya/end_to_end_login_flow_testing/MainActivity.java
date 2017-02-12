package com.example.aditya.end_to_end_login_flow_testing;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        final EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        final View container = findViewById(R.id.activity_main);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resultTextView.setVisibility(View.VISIBLE);

                Editable username = usernameEditText.getText();
                Editable password = passwordEditText.getText();

                if (username != null && password != null && username.toString().equals("Edureka")
                        && password.toString().equals("Edureka123")) {
                    resultTextView.setText(R.string.success);
                    container.setBackgroundColor(Color.GREEN);
                } else {
                    resultTextView.setText(R.string.failure);
                    container.setBackgroundColor(Color.RED);
                }
            }
        });
    }
}
