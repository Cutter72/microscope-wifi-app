package com.example.microscopewifi.medplus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.microscopewifi.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("Convert2Lambda")
@SuppressLint({"WrongConstant", "ResourceType"})
public class MicroscopeStreamingActivity extends Activity {

    private MjpegView mJpegViewInstance = null;

    final String ipAddress = "http://10.10.1.1:8899/";

    @SuppressLint({"ClickableViewAccessibility", "RtlHardcoded", "WrongConstant"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_microscope_streaming);
        openMjpedViewStreaming();
    }

    public void onPause() {
        super.onPause();
        MjpegView mjpegView = this.mJpegViewInstance;
        if (mjpegView != null && mjpegView.isWhileThis()) {
            this.mJpegViewInstance.mo6057f();
        }
    }

    public void onRestart() {
        openMjpedViewStreaming();
        super.onRestart();
    }

    public void onClickTakePicture(View view) {
        createFilePath();
        String fileName = new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.getDefault()).format(new Date());
        System.out.println("filePath: " + mJpegViewInstance.filePath);
        System.out.println("fileName: " + fileName);
        FileCreation.saveImage(mJpegViewInstance.bitmapImage, mJpegViewInstance.filePath, fileName);
        Toast.makeText(this, "Picture taken", Toast.LENGTH_SHORT).show();
    }

    public void onClickMicroscopeSettings(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MicroscopeSettingsActivity.class);
        startActivity(intent);
    }

    public void onClickSaveSettingContent(View view) {
        getAndSaveToFileSettingsContent();
    }

    public String getSettingContent(String urlAdress) {
        try {
            URL url = new URL(urlAdress);
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

    private void getAndSaveToFileSettingsContent() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String settingContent = getSettingContent("http://10.10.1.1/wizardvideo.asp");
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/fileSettingContent.txt");
                System.out.println("fileSettingContent: " + file.getAbsolutePath());
                try {
                    if (file.createNewFile()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
                        fileOutputStream.write(settingContent.getBytes());
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void createFilePath() {
        String filePath = getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        this.mJpegViewInstance.setFilePath(1, filePath);
    }

    public void openMjpedViewStreaming() {
        MjpegView mjpegView = findViewById(R.id.MjpegV);
        this.mJpegViewInstance = mjpegView;
        if (mjpegView != null) {
            mjpegView.setResolution(1280, 1024);
        }
        new AsyncStreamConnect().execute(this.ipAddress);
    }

    @SuppressLint({"StaticFieldLeak"})

    public class AsyncStreamConnect extends AsyncTask<String, Void, InputStreamHandler> {

        public AsyncStreamConnect() {
        }

        public InputStreamHandler doInBackground(String... strArr) {
            HttpURLConnection httpURLConnection;
            InputStream inputStream = null;
            try {
                httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
            } catch (IOException e5) {
                e5.printStackTrace();
                httpURLConnection = null;
            }
            try {
                httpURLConnection.setRequestMethod("GET");
            } catch (ProtocolException e6) {
                e6.printStackTrace();
            }
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setUseCaches(false);
            try {
                httpURLConnection.connect();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            try {
                inputStream = httpURLConnection.getInputStream();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            return new InputStreamHandler(inputStream);
        }

        public void onPostExecute(InputStreamHandler inputStreamHandler) {
            MicroscopeStreamingActivity.this.mJpegViewInstance.setSource(inputStreamHandler);
        }

    }
}
