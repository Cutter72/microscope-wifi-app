package com.example.microscopewifi.medplus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.microscopewifi.R;

public class MicroscopeSettingsActivity extends AppCompatActivity {

    @SuppressLint({"SetJavaScriptEnabled", "AuthLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microscope_settings);
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("http://admin:admin@10.10.1.1/index.asp"); //main page with all microscope and wifi settings
        webView.loadUrl("http://admin:admin@10.10.1.1/wizardvideo.asp"); //direct page for microscope camera settings
    }
}