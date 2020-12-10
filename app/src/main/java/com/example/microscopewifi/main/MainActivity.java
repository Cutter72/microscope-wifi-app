package com.example.microscopewifi.main;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.microscopewifi.R;
import com.example.microscopewifi.medplus.MicroscopeStreamingActivity;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickGoToMedPlus(View view) {
        //go to med plus
        if (getWifiIpAddress().contains("10.10.1.")) {
            Intent intent = new Intent();
            intent.setClass(this, MicroscopeStreamingActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please connect wifi microscope", Toast.LENGTH_SHORT).show();
        }
    }

    private String getWifiIpAddress() {
        int ipAddress = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getIpAddress();
        if (ipAddress == 0) {
            return "No connected wifi";
        }
        return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
    }

    public void onClickChangeResolutionAndFps(View view) {
        changeCameraParams();
    }

    private void changeCameraParams() {
        //todo disable all buttons to prevent user to change anything
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //todo get available resolutions from SettingContent
                //todo find highest resolution
                //todo set highest resolution and 7 fps
                changeResolution();
                changeFps();
                //todo enable buttons when camera is ready after resolution and fps change
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MicroscopeStreamingActivity.class);
                startActivity(intent);
            }
        };
        new Thread(runnable).start();
    }

    private void changeResolution() {
        StringBuilder sb = new StringBuilder();
        HttpGet httpGet = new HttpGet();
        int resolutionId = 4; //4 = 1280x1024
        sb.append("http://10.10.1.1/apply.cgi?submit_button=wizardvideo&video_idx=");
        sb.append(resolutionId);
        sb.append("&action=video_idx&video_idx_sel=");
        sb.append(resolutionId);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        try {
            httpGet.setURI(new URI(sb.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("admin", "admin"), "UTF-8", false));
        try {
            defaultHttpClient.execute(httpGet);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void changeFps() {
        StringBuilder sb = new StringBuilder();
        HttpGet httpGet = new HttpGet();
        int fps = 7;
        sb.append("http://10.10.1.1/apply.cgi?submit_button=wizardvideo&video_frame=");
        sb.append(fps);
        sb.append("&action=video_frame&video_frame_sel=");
        sb.append(fps);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        try {
            httpGet.setURI(new URI(sb.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("admin", "admin"), "UTF-8", false));
        try {
            defaultHttpClient.execute(httpGet);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}