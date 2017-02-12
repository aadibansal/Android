package com.example.aditya.webview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final String google_URL = "http://www.google.com";
    private static final String yahoo_URL = "http://www.yahoo.com";

    WebView webView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        linearLayout = (LinearLayout)findViewById(R.id.activity_main);
    }

    public void action(View v){
        switch (v.getId()){
            case R.id.red:
                linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                break;
            case R.id.blue:
                linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
                break;
            case R.id.green:
                linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
                break;
            case R.id.google:
                webView.loadUrl(google_URL);
                break;
            case R.id.yahoo:
                webView.loadUrl(yahoo_URL);
                break;
        }
    }
}
