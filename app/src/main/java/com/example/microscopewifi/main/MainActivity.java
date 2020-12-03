package com.example.microscopewifi.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.microscopewifi.R;
import com.example.microscopewifi.medplus.MjpegActivity;

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
        if (b().contains("10.10.1.")) {
            intent = new Intent();
            intent.putExtra("playmode", 0);
            cls = MjpegActivity.class;
            intent.setClass(this, cls);
            startActivity(intent);
        } else {
            context = getApplicationContext();
            str = "Please connect wifi microscope";
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    private String b() {
        int ipAddress = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getIpAddress();
        if (ipAddress == 0) {
            return "No connected wifi";
        }
        return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
    }
}