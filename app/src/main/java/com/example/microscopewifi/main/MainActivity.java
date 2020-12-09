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
        String str;
        Context context;
        Class<?> cls;
        Intent intent;
        if (getWifiIpAdress().contains("10.10.1.")) {
            intent = new Intent();
            intent.putExtra("playmode", 0);
            cls = MicroscopeStreamingActivity.class;
            intent.setClass(this, cls);
            startActivity(intent);
        } else {
            context = getApplicationContext();
            str = "Please connect wifi microscope";
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    private String getWifiIpAdress() {
        int ipAddress = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getIpAddress();
        if (ipAddress == 0) {
            return "No connected wifi";
        }
        return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
    }

    private void changeResolution() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                StringBuilder sb2 = new StringBuilder();
                HttpGet httpGet = new HttpGet();
                int resolutionId = 4;
                sb2.append("http://10.10.1.1/apply.cgi?submit_button=wizardvideo&video_idx=");
                sb2.append(resolutionId);
                sb2.append("&action=video_idx&video_idx_sel=");
                sb2.append(resolutionId);
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                try {
                    httpGet.setURI(new URI(sb2.toString()));
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
        };
        new Thread(runnable).start();
    }

    private void changeFps() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                StringBuilder sb2 = new StringBuilder();
                HttpGet httpGet = new HttpGet();
                int fps = 7;
                sb2.append("http://10.10.1.1/apply.cgi?submit_button=wizardvideo&video_frame=");
                sb2.append(fps);
                sb2.append("&action=video_frame&video_frame_sel=");
                sb2.append(fps);
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                try {
                    httpGet.setURI(new URI(sb2.toString()));
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
        };
        new Thread(runnable).start();
    }

    public void onClickChangeResolution(View view) {
        changeResolution();
    }

    public void onClickChangeFps(View view) {
        changeFps();
    }
}