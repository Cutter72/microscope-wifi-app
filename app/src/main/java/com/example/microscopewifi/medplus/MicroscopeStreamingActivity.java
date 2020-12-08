package com.example.microscopewifi.medplus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import com.example.microscopewifi.R;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressLint({"WrongConstant", "ResourceType"})
public class MicroscopeStreamingActivity extends Activity {

    /* renamed from: A */
    private boolean f1952A = false;

    /* renamed from: B */
    private boolean f1953B = false;

    /* renamed from: C */
    private final int f1954C = 0;

    /* renamed from: D */
    private final int f1955D = 0;

    /* renamed from: E */
    private final int f1956E = 0;

    /* renamed from: F */
    private final int f1957F = 0;

    /* renamed from: G */
    private MjpegView mJpegViewInstance = null;

    /* renamed from: H */
    final String ipAdress = "http://10.10.1.1:8899/";

    /* renamed from: M */
    private final boolean f1964M = false;

    /* renamed from: N */
    private boolean f1965N = false;

    /* renamed from: s */
    private final int f1995s = 0;

    /* renamed from: t */
    private final int f1996t = 0;

    /* renamed from: u */
    private final int f1997u = 0;

    public void onClickTakePicture(View view) {
        //todo implement picture save
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

    @SuppressWarnings("deprecation")
    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: MicroscopeStreamingActivity$m */
    public class AsyncTaskC0774m extends AsyncTask<String, Void, C0815f> {

        /* renamed from: MicroscopeStreamingActivity$m$a */
        class C0775a extends Thread {
            C0775a() {
            }

            public void run() {
                SystemClock.sleep(5000);
                if (MicroscopeStreamingActivity.this.mJpegViewInstance != null && MicroscopeStreamingActivity.this.f1965N) {
                    new AsyncTaskC0774m().execute(MicroscopeStreamingActivity.this.ipAdress);
                    MicroscopeStreamingActivity.this.f1965N = false;
                }
            }
        }

        public AsyncTaskC0774m() {
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x00b2  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x013b A[SYNTHETIC, Splitter:B:34:0x013b] */
        /* renamed from: a */
        public C0815f doInBackground(String... strArr) {
            HttpURLConnection httpURLConnection;
            String str;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            StringBuilder sb2;
            String num;
            InputStream inputStream = null;
            if (MicroscopeStreamingActivity.this.f1952A) {
                MicroscopeStreamingActivity.this.f1952A = false;
                HttpGet httpGet = new HttpGet();
                if (MicroscopeStreamingActivity.this.f1995s == 1) {
                    sb2 = new StringBuilder();
                    sb2.append("http://10.10.1.1/apply.cgi?submit_button=wizardvideo&video_idx=");
                    sb2.append(MicroscopeStreamingActivity.this.f1996t);
                    sb2.append("&video_frame=30&action=video_idx&video_idx_sel=");
                    sb2.append(MicroscopeStreamingActivity.this.f1996t);
                    num = "&video_frame_sel=30";
                } else if (MicroscopeStreamingActivity.this.f1995s == 2) {
                    sb2 = new StringBuilder();
                    sb2.append("http://10.10.1.1/apply.cgi?submit_button=wizardvideo&video_idx=&video_frame=");
                    sb2.append(MicroscopeStreamingActivity.this.f1997u);
                    sb2.append("&action=video_frame&video_idx_sel=");
                    sb2.append(MicroscopeStreamingActivity.this.f1996t);
                    sb2.append("&video_frame_sel=");
                    num = Integer.toString(MicroscopeStreamingActivity.this.f1997u);
                } else {
                    if (MicroscopeStreamingActivity.this.f1995s == 4) {
                        MicroscopeStreamingActivity.this.f1953B = true;
                    }
                    str = null;
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    if (!MicroscopeStreamingActivity.this.f1953B) {
                        for (int i2 = 0; i2 < 2; i2++) {
                            if (i2 == 0) {
                                sb = new StringBuilder();
                                sb.append("http://10.10.1.1:8899/?action=command&dest=0&plugin=0&id=");
                                sb.append(MicroscopeStreamingActivity.this.f1954C);
                                sb.append("&group=01&value=");
                                i = MicroscopeStreamingActivity.this.f1955D;
                            } else {
                                if (i2 == 1) {
                                    sb = new StringBuilder();
                                    sb.append("http://10.10.1.1:8899/?action=command&dest=0&plugin=0&id=");
                                    sb.append(MicroscopeStreamingActivity.this.f1956E);
                                    sb.append("&group=01&value=");
                                    i = MicroscopeStreamingActivity.this.f1957F;
                                }
                                try {
                                    httpGet.setURI(new URI(str));
                                } catch (URISyntaxException e) {
                                    e.printStackTrace();
                                }
                                httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("admin", "admin"), "UTF-8", false));
                                try {
                                    defaultHttpClient.execute(httpGet);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            sb.append(i);
                            str = sb.toString();
                            try {
                                httpGet.setURI(new URI(str));
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
                        MicroscopeStreamingActivity.this.f1953B = false;
                    } else {
                        try {
                            httpGet.setURI(new URI(str));
                        } catch (URISyntaxException e3) {
                            e3.printStackTrace();
                        }
                        httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("admin", "admin"), "UTF-8", false));
                        try {
                            defaultHttpClient.execute(httpGet);
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    new C0775a().start();
                    return null;
                }
                sb2.append(num);
                new C0775a().start();
                return null;
            }
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
            return new C0815f(inputStream);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(C0815f fVar) {
            MjpegView mjpegView;
            int i;
            MicroscopeStreamingActivity.this.mJpegViewInstance.setSource(fVar);
            if (!MicroscopeStreamingActivity.this.f1964M) {
                mjpegView = MicroscopeStreamingActivity.this.mJpegViewInstance;
                i = 4;
            } else {
                mjpegView = MicroscopeStreamingActivity.this.mJpegViewInstance;
                i = 8;
            }
            mjpegView.setDisplayMode(i);
        }
    }

    /* renamed from: a */
    public void openMjpedViewStreaming() {
        MjpegView mjpegView = (MjpegView) findViewById(R.id.MjpegV);
        this.mJpegViewInstance = mjpegView;
        if (mjpegView != null) {
            mjpegView.setResolution(1280, 1024);
        }
        new AsyncTaskC0774m().execute(this.ipAdress);
    }

    /* renamed from: c */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void createFilePath() {
        String filePath = getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        this.mJpegViewInstance.setFilePath(1, filePath);
    }

    @SuppressLint({"ClickableViewAccessibility", "RtlHardcoded", "WrongConstant"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_microscope_streaming);
        openMjpedViewStreaming();
    }

    public void onPause() {
        super.onPause();
        MjpegView mjpegView = this.mJpegViewInstance;
        if (mjpegView != null && mjpegView.mo6053b()) {
            this.mJpegViewInstance.mo6057f();
            this.f1965N = true;
        }
    }

    public void onRestart() {
        openMjpedViewStreaming();
        super.onRestart();
    }
}
