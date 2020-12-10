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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@SuppressWarnings({"Convert2Lambda", "deprecation"})
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
        System.out.println("changeCameraParams");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread.run()");
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
        System.out.println("changeResolution");
        StringBuilder sb = new StringBuilder();
        HttpGet httpGet = new HttpGet();
        int resolutionId = getHighestResolutionId(); //default 4 => 1280x1024
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

    private int getHighestResolutionId() {
        System.out.println("getHighestResolutionId");
        String settingContentHtml = getSettingContentHtml("http://10.10.1.1/wizardvideo.asp");
        int startIndex = settingContentHtml.indexOf("\"1,");
        int endIndex = settingContentHtml.indexOf("#\";");
        int highestResId = 4; //default 1280x1024
        int highestWidth = 0;
        int highestHeight = 0;
        String resolutionsLine = settingContentHtml.substring(startIndex + 1, endIndex);
        String[] resolutions = resolutionsLine.split("#");
        for (String resolution : resolutions) {
            if (resolution.contains(",1,")) {
                String[] resolutionParams = resolution.split(",");
                int width = Integer.parseInt(resolutionParams[2]);
                if (width > highestWidth) {
                    highestWidth = width;
                    highestHeight = Integer.parseInt(resolutionParams[3]);
                    highestResId = Integer.parseInt(resolutionParams[0]) - 1;
                }
            }
        }
        System.out.println("highestResId: " + highestResId);
        System.out.println("highestWidth: " + highestWidth);
        System.out.println("highestHeight: " + highestHeight);
        return highestResId;
    }

    public String getSettingContentHtml(String urlAddress) {
        System.out.println("getSettingContentHtml");
        try {
            URL url = new URL(urlAddress);
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("admin", "admin".toCharArray());
                }
            });
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            if (httpURLConnection.getResponseCode() == 200) {
                return responseStreamToString(httpURLConnection.getInputStream());
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String responseStreamToString(InputStream inputStream) {
        System.out.println("responseStreamToString");
        Throwable th;
        IOException e;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        break;
                    }
                } catch (IOException e3) {
                    e = e3;
                    bufferedReader = bufferedReader2;
                    try {
                        e.printStackTrace();
                        return sb.toString();
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        try {
                            throw th;
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = bufferedReader2;
                    try {
                        throw th;
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
            bufferedReader2.close();
        } catch (IOException e5) {
            e = e5;
            e.printStackTrace();
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            return sb.toString();
        }
        return sb.toString();
    }

    private void changeFps() {
        System.out.println("changeFps");
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