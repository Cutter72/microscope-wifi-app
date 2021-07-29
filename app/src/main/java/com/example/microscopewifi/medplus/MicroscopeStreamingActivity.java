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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MicroscopeStreamingActivity extends Activity {

    private MjpegView mJpegViewInstance = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_microscope_streaming);
        openMjpedViewStreaming();
    }

    public void onPause() {
        super.onPause();
        MjpegView mjpegView = this.mJpegViewInstance;
        if (mjpegView != null && mjpegView.isStreamRunning()) {
            this.mJpegViewInstance.closeImageStreamingThread();
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

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void createFilePath() {
        String filePath = getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        this.mJpegViewInstance.setFilePath(filePath);
    }

    public void openMjpedViewStreaming() {
        MjpegView mjpegView = findViewById(R.id.MjpegV);
        this.mJpegViewInstance = mjpegView;
        if (mjpegView != null) {
            mjpegView.setResolution(1280, 1024);
        }
        new AsyncStreamConnect().execute("http://10.10.1.1:8899/");
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
