package com.example.microscopewifi.medplus;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.microscopewifi.R;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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
import java.util.List;
import java.util.Locale;

@SuppressLint({"WrongConstant", "ResourceType"})
public class MjpegActivity extends Activity {

    /* renamed from: A */
    private boolean f1952A = false;

    /* renamed from: B */
    private boolean f1953B = false;

    /* renamed from: C */
    private int f1954C = 0;

    /* renamed from: D */
    private int f1955D = 0;

    /* renamed from: E */
    private int f1956E = 0;

    /* renamed from: F */
    private int f1957F = 0;

    /* renamed from: G */
    private MjpegView mJpegViewInstance = null;

    /* renamed from: H */
    String ipAdress = "http://10.10.1.1:8899/";

    /* renamed from: I */
    int f1960I = 0;

    /* renamed from: J */
    int f1961J = 0;

    /* renamed from: K */
    private List<List<String>> f1962K;

    /* renamed from: L */
//    private C0816g f1963L;

    /* renamed from: M */
    private boolean f1964M = false;

    /* renamed from: N */
    private boolean f1965N = false;

    /* renamed from: O */
    int f1966O = 0;

    /* renamed from: P */
    private int f1967P = 0;

    /* renamed from: Q */
    Point f1968Q = new Point(0, 0);

    /* renamed from: R */
    Point f1969R = new Point(0, 0);

    /* renamed from: S */
    Point f1970S = new Point(0, 0);

    /* renamed from: T */
    Point f1971T = new Point(0, 0);

    /* renamed from: U */
    private int f1972U;

    /* renamed from: V */
    private SoundPool f1973V;

    /* renamed from: W */
    private int f1974W = 0;

    /* renamed from: X */
    private float f1975X = 0.0f;

    /* renamed from: Y */
    private float f1976Y = 0.0f;

    /* renamed from: Z */
    private ImageButton f1977Z = null;

    /* renamed from: b */
    int f1978b = 0;

    /* renamed from: c */
    String f1979c = null;

    /* renamed from: d */
    String f1980d = null;

    /* renamed from: e */
    String f1981e = null;

    /* renamed from: f */
    String f1982f = null;

    /* renamed from: g */
    String f1983g = null;

    /* renamed from: h */
    String f1984h = null;

    /* renamed from: i */
    String f1985i = null;

    /* renamed from: j */
    String f1986j = null;

    /* renamed from: k */
    int f1987k = 0;

    /* renamed from: l */
    private ImageButton imageButton = null;

    /* renamed from: m */
    private boolean f1989m = false;

    /* renamed from: n */
    SharedPreferences sharedPreferences;

    /* renamed from: o */
    private String f1991o = "";

    /* renamed from: p */
    private int f1992p = 10;

    /* renamed from: q */
    private int f1993q = 1;

    /* renamed from: r */
    private int f1994r = 1;

    /* renamed from: s */
    private int f1995s = 0;

    /* renamed from: t */
    private int f1996t = 0;

    /* renamed from: u */
    private int f1997u = 0;

    /* renamed from: v */
    public String f1998v = "mm";

    /* renamed from: w */
    public int f1999w = 2;

    /* renamed from: x */
    public int f2000x = -65536;

    /* renamed from: y */
    public int f2001y = 25;

    /* renamed from: z */
    public int f2002z = 2;

    public void onClickTakePicture(View view) {
        //todo implement picture save
        createFilePath();
        String fileName = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault()).format(new Date());
        System.out.println("filePath: " + mJpegViewInstance.filePath);
        System.out.println("fileName: " + fileName);
        FileCreation.saveImage(mJpegViewInstance.bitmapImage, mJpegViewInstance.filePath, fileName);
        Toast.makeText(this, "Picture taken", Toast.LENGTH_SHORT).show();
    }

//    /* renamed from: MjpegActivity$a */
//    class DialogInterface$OnClickListenerC0762a implements DialogInterface.OnClickListener {
//        DialogInterface$OnClickListenerC0762a(MjpegActivity mjpegActivity) {
//        }
//
//        public void onClick(DialogInterface dialogInterface, int i) {
//        }
//    }
//
//    /* renamed from: MjpegActivity$b */
//    class DialogInterface$OnClickListenerC0763b implements DialogInterface.OnClickListener {
//
//        /* renamed from: b */
//        final /* synthetic */ EditText f2003b;
//
//        DialogInterface$OnClickListenerC0763b(EditText editText) {
//            this.f2003b = editText;
//        }
//
//        public void onClick(DialogInterface dialogInterface, int i) {
//            MjpegActivity.this.f1991o = this.f2003b.getEditableText().toString();
//        }
//    }
//
//    /* renamed from: MjpegActivity$c */
//    class DialogInterface$OnClickListenerC0764c implements DialogInterface.OnClickListener {
//        DialogInterface$OnClickListenerC0764c() {
//        }
//
//        public void onClick(DialogInterface dialogInterface, int i) {
//            MjpegActivity.this.f1991o = "";
//            dialogInterface.cancel();
//        }
//    }
//
//    /* renamed from: MjpegActivity$d */
//    class View$OnClickListenerC0765d implements View.OnClickListener {
//        View$OnClickListenerC0765d() {
//        }
//
//        public void onClick(View view) {
//            ImageButton imageButton;
//            int i;
//            Resources resources;
//            if (!MjpegActivity.this.f1964M) {
//                MjpegActivity.this.f1964M = true;
//                imageButton = MjpegActivity.this.f1988l;
//                resources = MjpegActivity.this.getResources();
//                i = 2131165331;
//            } else {
//                MjpegActivity.this.f1964M = false;
//                imageButton = MjpegActivity.this.f1988l;
//                resources = MjpegActivity.this.getResources();
//                i = 2131165325;
//            }
//            imageButton.setImageDrawable(resources.getDrawable(i, MjpegActivity.this.getApplicationContext().getTheme()));
//            if (MjpegActivity.this.f1958G.mo6053b()) {
//                if (MjpegActivity.this.f1958G != null && MjpegActivity.this.f1958G.mo6053b()) {
//                    MjpegActivity.this.f1958G.mo6057f();
//                    MjpegActivity.this.f1965N = true;
//                }
//                MjpegActivity.this.mo6009a();
//            }
//        }
//    }
//
//    /* renamed from: MjpegActivity$e */
//    class View$OnClickListenerC0766e implements View.OnClickListener {
//        View$OnClickListenerC0766e() {
//        }
//
//        public void onClick(View view) {
//            MjpegActivity.this.finish();
//        }
//    }
//
//    /* renamed from: MjpegActivity$f */
//    class View$OnClickListenerC0767f implements View.OnClickListener {
//        View$OnClickListenerC0767f() {
//        }
//
//        @SuppressLint("WrongConstant")
//        public void onClick(View view) {
//            MjpegActivity mjpegActivity = MjpegActivity.this;
//            if (mjpegActivity.f1987k == 1) {
//                mjpegActivity.createFilePath();
//            } else {
//                Toast.makeText(mjpegActivity.getApplicationContext(), MjpegActivity.this.getString(2131689587), 0).show();
//            }
//        }
//    }
//
//    /* renamed from: MjpegActivity$g */
//    class View$OnClickListenerC0768g implements View.OnClickListener {
//        View$OnClickListenerC0768g() {
//        }
//
//        @SuppressLint("WrongConstant")
//        public void onClick(View view) {
//            MjpegActivity mjpegActivity = MjpegActivity.this;
//            if (mjpegActivity.f1987k != 1) {
//                Toast.makeText(mjpegActivity.getApplicationContext(), MjpegActivity.this.getString(2131689587), 0).show();
//            } else if (mjpegActivity.f1994r == 1) {
//                MjpegActivity.this.f1994r = 0;
//                MjpegActivity.this.m2619a(true);
//                MjpegActivity.this.f1989m = true;
//                new Thread(new RunnableC0778p()).start();
//            } else {
//                MjpegActivity.this.f1994r = 1;
//                MjpegActivity.this.m2619a(false);
//                MjpegActivity.this.f1989m = false;
//            }
//        }
//    }

    /* renamed from: MjpegActivity$h */
//    class View$OnClickListenerC0769h implements View.OnClickListener {
//        View$OnClickListenerC0769h() {
//        }
//
//        @SuppressLint("WrongConstant")
//        public void onClick(View view) {
//            MjpegActivity mjpegActivity = MjpegActivity.this;
//            if (mjpegActivity.f1987k == 1) {
//                mjpegActivity.f1975X = 0.0f;
//                MjpegActivity.this.f1976Y = 0.0f;
//                MjpegActivity mjpegActivity2 = MjpegActivity.this;
//                mjpegActivity2.f1966O = 0;
//                mjpegActivity2.f1967P = 0;
//                SharedPreferences.Editor edit = MjpegActivity.this.f1990n.edit();
//                edit.putString("nMagSet", String.valueOf(MjpegActivity.this.f1975X) + "-" + String.valueOf(MjpegActivity.this.f1976Y));
//                edit.apply();
//                MjpegActivity.this.f1958G.mo6046a();
//                Intent intent = new Intent(MjpegActivity.this, PointInfoActivity.class);
//                intent.putExtra("userid", MjpegActivity.this.f1978b);
//                intent.putExtra("name", MjpegActivity.this.f1979c);
//                intent.putExtra("age", MjpegActivity.this.f1980d);
//                intent.putExtra("mobile", MjpegActivity.this.f1981e);
//                intent.putExtra("email", MjpegActivity.this.f1982f);
//                intent.putExtra("userimg", MjpegActivity.this.f1983g);
//                intent.putExtra("usertime", MjpegActivity.this.f1984h);
//                intent.putExtra("usersex", MjpegActivity.this.f1985i);
//                intent.putExtra("usernote", MjpegActivity.this.f1986j);
//                MjpegActivity.this.startActivity(intent);
//                return;
//            }
//            Toast.makeText(mjpegActivity.getApplicationContext(), MjpegActivity.this.getString(2131689587), 0).show();
//        }
//    }

//    /* renamed from: MjpegActivity$i */
//    class View$OnClickListenerC0770i implements View.OnClickListener {
//        View$OnClickListenerC0770i() {
//        }
//
//        @SuppressLint("WrongConstant")
//        public void onClick(View view) {
//            MjpegActivity mjpegActivity = MjpegActivity.this;
//            if (mjpegActivity.f1987k == 1) {
//                mjpegActivity.f1966O = 0;
//                mjpegActivity.f1967P = 0;
//                new Thread(new RunnableC0777o()).start();
//                return;
//            }
//            Toast.makeText(mjpegActivity.getApplicationContext(), MjpegActivity.this.getString(2131689587), 0).show();
//        }
//    }
//
//    /* renamed from: MjpegActivity$j */
//    class C0771j extends Authenticator {
//        C0771j(MjpegActivity mjpegActivity) {
//        }
//
//        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication("admin", "admin".toCharArray());
//        }
//    }
//
//    /* renamed from: MjpegActivity$k */
//    class C0772k extends Authenticator {
//        C0772k(MjpegActivity mjpegActivity) {
//        }
//
//        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication("admin", "admin".toCharArray());
//        }
//    }

    /* renamed from: MjpegActivity$l */
//    class DialogInterface$OnClickListenerC0773l implements DialogInterface.OnClickListener {
//
//        /* renamed from: b */
//        final /* synthetic */ EditText f2012b;
//
//        /* renamed from: c */
//        final /* synthetic */ EditText f2013c;
//
//        DialogInterface$OnClickListenerC0773l(EditText editText, EditText editText2) {
//            this.f2012b = editText;
//            this.f2013c = editText2;
//        }
//
//        public void onClick(DialogInterface dialogInterface, int i) {
//            String trim = this.f2012b.getText().toString().trim();
//            String trim2 = this.f2013c.getText().toString().trim();
//            if (trim.length() <= 0 || trim2.length() <= 0) {
//                MjpegView mjpegView = MjpegActivity.this.f1958G;
//                MjpegActivity mjpegActivity = MjpegActivity.this;
//                mjpegView.mo6050a(mjpegActivity.f1970S, 3, mjpegActivity.f1966O, "", String.valueOf(mjpegActivity.f1976Y));
//                return;
//            }
//            MjpegActivity mjpegActivity2 = MjpegActivity.this;
//            Point point = mjpegActivity2.f1970S;
//            point.x = mjpegActivity2.f1960I;
//            point.y = mjpegActivity2.f1961J;
//            MjpegView mjpegView2 = mjpegActivity2.f1958G;
//            MjpegActivity mjpegActivity3 = MjpegActivity.this;
//            mjpegView2.mo6050a(mjpegActivity3.f1970S, 3, mjpegActivity3.f1966O, trim2, trim);
//            MjpegActivity.this.f1966O = 0;
//        }
//    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: MjpegActivity$m */
    public class AsyncTaskC0774m extends AsyncTask<String, Void, C0815f> {

        /* renamed from: MjpegActivity$m$a */
        class C0775a extends Thread {
            C0775a() {
            }

            public void run() {
                SystemClock.sleep(5000);
                if (MjpegActivity.this.mJpegViewInstance != null && MjpegActivity.this.f1965N) {
                    new AsyncTaskC0774m().execute(MjpegActivity.this.ipAdress);
                    MjpegActivity.this.f1965N = false;
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
            if (MjpegActivity.this.f1952A) {
                MjpegActivity.this.f1952A = false;
                HttpGet httpGet = new HttpGet();
                if (MjpegActivity.this.f1995s == 1) {
                    sb2 = new StringBuilder();
                    sb2.append("http://10.10.1.1/apply.cgi?submit_button=wizardvideo&video_idx=");
                    sb2.append(Integer.toString(MjpegActivity.this.f1996t));
                    sb2.append("&video_frame=30&action=video_idx&video_idx_sel=");
                    sb2.append(Integer.toString(MjpegActivity.this.f1996t));
                    num = "&video_frame_sel=30";
                } else if (MjpegActivity.this.f1995s == 2) {
                    sb2 = new StringBuilder();
                    sb2.append("http://10.10.1.1/apply.cgi?submit_button=wizardvideo&video_idx=&video_frame=");
                    sb2.append(Integer.toString(MjpegActivity.this.f1997u));
                    sb2.append("&action=video_frame&video_idx_sel=");
                    sb2.append(Integer.toString(MjpegActivity.this.f1996t));
                    sb2.append("&video_frame_sel=");
                    num = Integer.toString(MjpegActivity.this.f1997u);
                } else {
                    if (MjpegActivity.this.f1995s == 4) {
                        MjpegActivity.this.f1953B = true;
                    }
                    str = null;
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    if (!MjpegActivity.this.f1953B) {
                        for (int i2 = 0; i2 < 2; i2++) {
                            if (i2 == 0) {
                                sb = new StringBuilder();
                                sb.append("http://10.10.1.1:8899/?action=command&dest=0&plugin=0&id=");
                                sb.append(Integer.toString(MjpegActivity.this.f1954C));
                                sb.append("&group=01&value=");
                                i = MjpegActivity.this.f1955D;
                            } else {
                                if (i2 == 1) {
                                    sb = new StringBuilder();
                                    sb.append("http://10.10.1.1:8899/?action=command&dest=0&plugin=0&id=");
                                    sb.append(Integer.toString(MjpegActivity.this.f1956E));
                                    sb.append("&group=01&value=");
                                    i = MjpegActivity.this.f1957F;
                                }
                                try {
                                    httpGet.setURI(new URI(str));
                                } catch (URISyntaxException e) {
                                    e.printStackTrace();
                                }
                                httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("admin", "admin"), "UTF-8", false));
                                try {
                                    EntityUtils.toString(defaultHttpClient.execute(httpGet).getEntity());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            sb.append(Integer.toString(i));
                            str = sb.toString();
                            try {
                                httpGet.setURI(new URI(str));
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                            httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("admin", "admin"), "UTF-8", false));
                            try {
                                EntityUtils.toString(defaultHttpClient.execute(httpGet).getEntity());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        MjpegActivity.this.f1953B = false;
                    } else {
                        try {
                            httpGet.setURI(new URI(str));
                        } catch (URISyntaxException e3) {
                            e3.printStackTrace();
                        }
                        httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("admin", "admin"), "UTF-8", false));
                        try {
                            EntityUtils.toString(defaultHttpClient.execute(httpGet).getEntity());
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    new C0775a().start();
                    return null;
                }
                sb2.append(num);
                str = sb2.toString();
                DefaultHttpClient defaultHttpClient2 = new DefaultHttpClient();
                if (!MjpegActivity.this.f1953B) {
                }
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
            MjpegActivity.this.mJpegViewInstance.setSource(fVar);
            if (fVar != null) {
                fVar.mo6141a(2);
            }
            if (!MjpegActivity.this.f1964M) {
                mjpegView = MjpegActivity.this.mJpegViewInstance;
                i = 4;
            } else {
                mjpegView = MjpegActivity.this.mJpegViewInstance;
                i = 8;
            }
            mjpegView.setDisplayMode(i);
        }
    }

    /* renamed from: MjpegActivity$n */
//    class C0776n implements AdapterView.OnItemClickListener {
//        C0776n() {
//        }
//
//        @SuppressLint("WrongConstant")
//        @Override // android.widget.AdapterView.OnItemClickListener
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
//            int i2;
//            Context applicationContext;
//            MjpegActivity mjpegActivity;
//            String str = (String) ((List) MjpegActivity.this.f1962K.get(MjpegActivity.this.f1963L.mo6142a())).get(i);
//            if (str.equals(MjpegActivity.this.getString(2131689598))) {
//                MjpegActivity mjpegActivity2 = MjpegActivity.this;
//                mjpegActivity2.f1966O = 1;
//                mjpegActivity2.f1967P = 1;
//                MjpegActivity.this.mo6011b();
//                applicationContext = MjpegActivity.this.getApplicationContext();
//                mjpegActivity = MjpegActivity.this;
//                i2 = 2131689472;
//            } else {
//                i2 = 2131689589;
//                if (str.equals(MjpegActivity.this.getString(2131689608))) {
//                    if (MjpegActivity.this.f1975X > 0.0f && MjpegActivity.this.f1976Y > 0.0f) {
//                        MjpegActivity mjpegActivity3 = MjpegActivity.this;
//                        mjpegActivity3.f1966O = 2;
//                        mjpegActivity3.f1967P = 1;
//                        MjpegActivity.this.mo6011b();
//                        applicationContext = MjpegActivity.this.getApplicationContext();
//                        mjpegActivity = MjpegActivity.this;
//                        i2 = 2131689552;
//                    }
//                } else if (str.equals(MjpegActivity.this.getString(2131689595))) {
//                    if (MjpegActivity.this.f1975X > 0.0f && MjpegActivity.this.f1976Y > 0.0f) {
//                        MjpegActivity mjpegActivity4 = MjpegActivity.this;
//                        mjpegActivity4.f1966O = 4;
//                        mjpegActivity4.f1967P = 1;
//                        MjpegActivity.this.mo6011b();
//                        applicationContext = MjpegActivity.this.getApplicationContext();
//                        mjpegActivity = MjpegActivity.this;
//                        i2 = 2131689533;
//                    }
//                } else if (!str.equals(MjpegActivity.this.getString(2131689599))) {
//                    if (str.equals(MjpegActivity.this.getString(2131689600))) {
//                        MjpegActivity.this.mo6011b();
//                        MjpegActivity.m2650s(MjpegActivity.this);
//                        if (MjpegActivity.this.f1992p == 13) {
//                            MjpegActivity.this.f1992p = 10;
//                        }
//                        MjpegActivity.this.f1958G.mo6050a(new Point(0, 0), 8, MjpegActivity.this.f1992p, "", String.valueOf(MjpegActivity.this.f1976Y));
//                    } else if (str.equals(MjpegActivity.this.getString(2131689616))) {
//                        MjpegActivity mjpegActivity5 = MjpegActivity.this;
//                        mjpegActivity5.f1966O = 6;
//                        mjpegActivity5.f1967P = 1;
//                        MjpegActivity.this.mo6011b();
//                        applicationContext = MjpegActivity.this.getApplicationContext();
//                        mjpegActivity = MjpegActivity.this;
//                        i2 = 2131689619;
//                    } else {
//                        MjpegActivity.this.mo6011b();
//                        MjpegActivity mjpegActivity6 = MjpegActivity.this;
//                        mjpegActivity6.f1966O = 0;
//                        mjpegActivity6.f1967P = 0;
//                    }
//                    MjpegActivity.this.f1963L.dismiss();
//                    MjpegActivity.this.f1963L.f2259g = 1;
//                } else if (MjpegActivity.this.f1975X > 0.0f && MjpegActivity.this.f1976Y > 0.0f) {
//                    MjpegActivity mjpegActivity7 = MjpegActivity.this;
//                    mjpegActivity7.f1966O = 3;
//                    mjpegActivity7.f1967P = 1;
//                    MjpegActivity.this.mo6011b();
//                    applicationContext = MjpegActivity.this.getApplicationContext();
//                    mjpegActivity = MjpegActivity.this;
//                    i2 = 2131689541;
//                }
//                applicationContext = MjpegActivity.this.getApplicationContext();
//                mjpegActivity = MjpegActivity.this;
//            }
//            Toast.makeText(applicationContext, mjpegActivity.getString(i2), 0).show();
//            MjpegActivity.this.f1963L.dismiss();
//            MjpegActivity.this.f1963L.f2259g = 1;
//        }
//    }

    /* renamed from: MjpegActivity$o */
//    class RunnableC0777o implements Runnable {
//        RunnableC0777o() {
//        }
//
//        public void run() {
//            String b = MjpegActivity.this.mo6010b("http://10.10.1.1/wizardvideo.asp");
//            Intent intent = new Intent();
//            intent.putExtra("WiFiORIMAGE", true);
//            intent.putExtra("SettingContent", b);
//            intent.putExtra("bRename", MjpegActivity.this.f1993q);
//            intent.putExtra("currentdecimal", MjpegActivity.this.f1999w);
//            intent.putExtra("currentcolor", MjpegActivity.this.f2000x);
//            intent.putExtra("currentfontsize", MjpegActivity.this.f2001y);
//            intent.putExtra("currentlinesize", MjpegActivity.this.f2002z);
//            intent.putExtra("currentunit", MjpegActivity.this.f1998v);
//            intent.setClass(MjpegActivity.this, SettingActivity.class);
//            MjpegActivity.this.startActivityForResult(intent, 1000);
//        }
//    }

    /* renamed from: MjpegActivity$p */
//    class RunnableC0778p implements Runnable {
//
//        /* renamed from: b */
//        boolean f2019b = false;
//
//        RunnableC0778p() {
//        }
//
//        public void run() {
//            while (MjpegActivity.this.f1989m) {
//                if (!this.f2019b) {
//                    this.f2019b = true;
//                    MjpegActivity mjpegActivity = MjpegActivity.this;
//                    mjpegActivity.f1974W = mjpegActivity.mo6008a("http://admin:admin@10.10.1.1/updateNPNvram.cgi?camera_gpio_trigger");
//                } else if (MjpegActivity.this.m2634e().contains("10.10.1.")) {
//                    int a = MjpegActivity.this.mo6008a("http://admin:admin@10.10.1.1/updateNPNvram.cgi?camera_gpio_trigger");
//                    if (MjpegActivity.this.f1974W != a) {
//                        MjpegActivity.this.f1974W = a;
//                        MjpegActivity.this.createFilePath();
//                    }
//                } else {
//                    MjpegActivity.this.f1989m = false;
//                }
//            }
//        }
//    }

    @SuppressLint("WrongConstant")
    @TargetApi(21)
    /* renamed from: a */
    private SoundPool m2614a(int i) {
        if (Build.VERSION.SDK_INT < 21) {
            return new SoundPool(i, 3, 0);
        }
        return new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).setMaxStreams(i).build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003d A[SYNTHETIC, Splitter:B:24:0x003d] */
    /* renamed from: a */
//    private String m2616a(InputStream inputStream) {
//        Throwable th;
//        IOException e;
//        StringBuilder sb = new StringBuilder();
//        BufferedReader bufferedReader = null;
//        try {
//            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//            while (true) {
//                try {
//                    String readLine = bufferedReader2.readLine();
//                    if (readLine != null) {
//                        sb.append(readLine);
//                    } else {
//                        break;
//                    }
//                } catch (IOException e3) {
//                    e = e3;
//                    bufferedReader = bufferedReader2;
//                    try {
//                        e.printStackTrace();
//                        if (bufferedReader != null) {
//                        }
//                        return sb.toString();
//                    } catch (Throwable th2) {
//                        th = th2;
//                        if (bufferedReader != null) {
//                            try {
//                                bufferedReader.close();
//                            } catch (IOException e4) {
//                                e4.printStackTrace();
//                            }
//                        }
//                        try {
//                            throw th;
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//                        }
//                    }
//                } catch (Throwable th3) {
//                    th = th3;
//                    bufferedReader = bufferedReader2;
//                    if (bufferedReader != null) {
//                    }
//                    try {
//                        throw th;
//                    } catch (Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                }
//            }
//            bufferedReader2.close();
//        } catch (IOException e5) {
//            e = e5;
//            e.printStackTrace();
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
//            }
//            return sb.toString();
//        }
//        return sb.toString();
//    }

    /* renamed from: a */
//    private List<Integer> m2617a(Integer[] numArr) {
//        ArrayList arrayList = new ArrayList();
//        for (Integer num : numArr) {
//            arrayList.add(num);
//        }
//        return arrayList;
//    }

    /* renamed from: a */
//    private List<String> m2618a(String[] strArr) {
//        ArrayList arrayList = new ArrayList();
//        for (String str : strArr) {
//            arrayList.add(str);
//        }
//        return arrayList;
//    }

    /* access modifiers changed from: private */
    /* renamed from: a */
//    public void m2619a(boolean z) {
//        ImageButton imageButton;
//        int i;
//        Resources resources;
//        if (z) {
//            imageButton = this.f1977Z;
//            resources = getBaseContext().getResources();
//            i = 2131165361;
//        } else {
//            imageButton = this.f1977Z;
//            resources = getBaseContext().getResources();
//            i = 2131165360;
//        }
//        imageButton.setImageDrawable(resources.getDrawable(i));
//    }

    /* renamed from: c */
//    public static boolean m2629c(String str) {
//        int length = str.length();
//        do {
//            length--;
//            if (length < 0) {
//                return true;
//            }
//        } while (Character.isDigit(str.charAt(length)));
//        return false;
//    }

    /* access modifiers changed from: private */
    /* renamed from: e */
//    public String m2634e() {
//        @SuppressLint("WrongConstant") int ipAddress = ((WifiManager) getApplicationContext().getSystemService("wifi")).getConnectionInfo().getIpAddress();
//        if (ipAddress == 0) {
//            return "no connect wifi";
//        }
//        return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
//    }

    /* renamed from: s */
//    static /* synthetic */ int m2650s(MjpegActivity mjpegActivity) {
//        int i = mjpegActivity.f1992p;
//        mjpegActivity.f1992p = i + 1;
//        return i;
//    }

    /* renamed from: a */
//    public int mo6008a(String str) {
//        String str2 = null;
//        try {
//            URL url = new URL(str);
//            Authenticator.setDefault(new C0772k(this));
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setConnectTimeout(3000);
//            if (httpURLConnection.getResponseCode() == 200) {
//                str2 = m2616a(httpURLConnection.getInputStream());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String trim = str2.replace("\n", "").trim();
//        if (m2629c(trim)) {
//            return Integer.valueOf(trim).intValue();
//        }
//        return -1;
//    }

    /* renamed from: a */
    public void openMjpedViewStreaming() {
        MjpegView mjpegView = (MjpegView) findViewById(R.id.MjpegV);
        this.mJpegViewInstance = mjpegView;
        if (mjpegView != null) {
            mjpegView.setResolution(1280, 1024);
        }
        new AsyncTaskC0774m().execute(this.ipAdress);
    }

    /* renamed from: b */
//    public String mo6010b(String str) {
//        try {
//            URL url = new URL(str);
//            Authenticator.setDefault(new C0771j(this));
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setConnectTimeout(3000);
//            if (httpURLConnection.getResponseCode() == 200) {
//                return m2616a(httpURLConnection.getInputStream());
//            }
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /* renamed from: b */
//    public void mo6011b() {
//        Point point = this.f1968Q;
//        point.y = 0;
//        point.x = 0;
//        Point point2 = this.f1969R;
//        point2.y = 0;
//        point2.x = 0;
//        Point point3 = this.f1970S;
//        point3.y = 0;
//        point3.x = 0;
//        Point point4 = this.f1971T;
//        point4.y = 0;
//        point4.x = 0;
//    }

    /* renamed from: c */
    public void createFilePath() {
//        String filePath = (Environment.getExternalStorageDirectory().getAbsolutePath()+"/"); /*+ this.f1979c + this.f1980d + "/") + new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date()) + "/";*/
        String filePath = getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
//        this.f1973V.play(this.f1972U, 1.0f, 1.0f, 0, 0, 1.0f);
        this.mJpegViewInstance.setFilePath(1, filePath, "");
    }

    /* renamed from: d */
//    public void mo6013d() {
//        MjpegView mjpegView = this.f1958G;
//        if (mjpegView != null && mjpegView.mo6053b()) {
//            this.f1958G.mo6057f();
//            this.f1965N = true;
//            new AsyncTaskC0774m().execute(this.f1959H);
//        }
//    }

//    protected void onActivityResult(int i, int i2, Intent intent) {
//        if (i == 1000 && i2 == -1) {
//            this.f1975X = 0.0f;
//            this.f1976Y = 0.0f;
//            this.f1966O = 0;
//            this.f1967P = 0;
//            SharedPreferences.Editor edit = this.f1990n.edit();
//            edit.putString("nMagSet", String.valueOf(this.f1975X) + "-" + String.valueOf(this.f1976Y));
//            edit.apply();
//            this.f1993q = this.f1990n.getInt("bRename", 0);
//            this.f1999w = this.f1990n.getInt("currentdecimal", 2);
//            this.f2000x = this.f1990n.getInt("currentcolor", -65536);
//            this.f2001y = this.f1990n.getInt("currentfontsize", 25);
//            this.f2002z = this.f1990n.getInt("currentlinesize", 2);
//            this.f1998v = this.f1990n.getString("currentunit", "mm");
//            this.f1954C = this.f1990n.getInt("defualtbrightnessid", 0);
//            this.f1955D = this.f1990n.getInt("defualtBrightnessvalue", 0);
//            this.f1956E = this.f1990n.getInt("defualtContractid", 0);
//            this.f1957F = this.f1990n.getInt("defualtContractvalue", 0);
//            Bundle extras = intent.getExtras();
//            this.f1995s = extras.getInt("operation");
//            this.f1996t = extras.getInt("currentresolution");
//            this.f1997u = extras.getInt("currentfps");
//            int i3 = this.f1995s;
//            if (i3 == 1 || i3 == 2 || (i3 != 3 && i3 == 4)) {
//                this.f1952A = true;
//                mo6013d();
//            }
//        }
//        super.onActivityResult(i, i2, intent);
//    }

    @SuppressLint({"ClickableViewAccessibility", "RtlHardcoded", "WrongConstant"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
//        getWindow().setFlags(128, 128);
//        requestWindowFeature(1);
//        getWindow().setFlags(1024, 1024);
//        setRequestedOrientation(0);
        setContentView(R.layout.activity_mjpeg);
//        SharedPreferences sharedPreferences = getSharedPreferences("HiviewPlus3Preferences", 0);
//        this.sharedPreferences = sharedPreferences;
//        this.f1993q = sharedPreferences.getInt("bRename", 0);
//        this.f1998v = this.sharedPreferences.getString("currentunit", "mm");
//        this.f1999w = this.sharedPreferences.getInt("currentdecimal", 2);
//        this.f2000x = this.sharedPreferences.getInt("currentcolor", -65536);
//        this.f2001y = this.sharedPreferences.getInt("currentfontsize", 25);
//        this.f2002z = this.sharedPreferences.getInt("currentlinesize", 2);
//        this.f1978b = getIntent().getIntExtra("userid", 0);
//        this.f1979c = getIntent().getStringExtra("name");
//        this.f1980d = getIntent().getStringExtra("age");
//        this.f1981e = getIntent().getStringExtra("mobile");
//        this.f1982f = getIntent().getStringExtra("email");
//        this.f1983g = getIntent().getStringExtra("userimg");
//        this.f1984h = getIntent().getStringExtra("usertime");
//        this.f1985i = getIntent().getStringExtra("usersex");
//        this.f1986j = getIntent().getStringExtra("usernote");
//        this.f1987k = getIntent().getIntExtra("playmode", 0);
//        SoundPool a = m2614a(6);
//        this.f1973V = a;
//        this.f1972U = a.load(this, 2131623936, 1);
//        getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
//        ImageButton imageButton = new ImageButton(this);
//        this.f1988l = imageButton;
//        imageButton.setImageDrawable(getResources().getDrawable(2131165325, getApplicationContext().getTheme()));
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
//        layoutParams.topMargin = 0;
//        layoutParams.gravity = 53;
//        addContentView(this.f1988l, layoutParams);
//        this.f1988l.setOnClickListener(new View$OnClickListenerC0765d());
//        ((ImageButton) findViewById(2131230847)).setOnClickListener(new View$OnClickListenerC0766e());
//        ImageButton imageButton2 = (ImageButton) findViewById(2131230778);
//        if (this.f1987k == 1) {
//            imageButton2.setVisibility(0);
//        } else {
//            imageButton2.setVisibility(4);
//        }
//        imageButton2.setOnClickListener(new View$OnClickListenerC0767f());
//        ImageButton imageButton3 = (ImageButton) findViewById(2131230876);
//        this.f1977Z = imageButton3;
//        if (this.f1987k == 1) {
//            imageButton3.setVisibility(0);
//        } else {
//            imageButton3.setVisibility(4);
//        }
//        this.f1977Z.setOnClickListener(new View$OnClickListenerC0768g());
//        ImageButton imageButton4 = (ImageButton) findViewById(2131230725);
//        if (this.f1987k == 1) {
//            imageButton4.setVisibility(0);
//        } else {
//            imageButton4.setVisibility(4);
//        }
//        imageButton4.setOnClickListener(new View$OnClickListenerC0769h());
//        ImageButton imageButton5 = (ImageButton) findViewById(2131230965);
//        if (this.f1987k == 1) {
//            imageButton5.setVisibility(0);
//        } else {
//            imageButton5.setVisibility(4);
//        }
//        imageButton5.setOnClickListener(new View$OnClickListenerC0770i());
        openMjpedViewStreaming();
//        SharedPreferences.Editor edit = getSharedPreferences("HiviewPlus3Preferences", 0).edit();
//        edit.putString("nMagSet", String.valueOf(0) + "-" + String.valueOf(0));
//        edit.apply();
//        List<String> a2 = m2618a(new String[]{getString(2131689482)});
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(m2617a(new Integer[]{2131165286, 2131165321, 2131165277, 2131165290, 2131165292, 2131165363}));
//        ArrayList arrayList2 = new ArrayList();
//        this.f1962K = arrayList2;
//        arrayList2.add(m2618a(new String[]{getString(2131689598), getString(2131689608), getString(2131689595), getString(2131689599), getString(2131689600), getString(2131689616)}));
//        this.f1963L = new C0816g(this, a2, this.f1962K, arrayList, new C0776n());
    }

    public void onDestroy() {
        this.f1989m = false;
//        this.f1973V.release();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        finish();
        return false;
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

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00ee, code lost:
        if (r1 == 3) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00f0, code lost:
        r13 = r18.f1970S;
        r13.x = r2;
        r13.y = r3;
        r12 = r18.f1958G;
        r14 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00f9, code lost:
        r17 = java.lang.String.valueOf(r18.f1976Y);
        r16 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0123, code lost:
        if (r1 == 4) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0125, code lost:
        r13 = r18.f1971T;
        r13.x = r2;
        r13.y = r3;
        r12 = r18.f1958G;
        r14 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x013c, code lost:
        if (r1 == 4) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0170, code lost:
        if (r1 == 3) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0184, code lost:
        if (r1 == 4) goto L_0x0125;
     */

//    @SuppressLint("WrongConstant")
//    public boolean onTouchEvent(MotionEvent motionEvent) {
//        int i = 0;
//        Point point;
//        MjpegView mjpegView;
//        int i2;
//        int action = motionEvent.getAction();
//        int x = (int) motionEvent.getX();
//        int y = (int) motionEvent.getY();
//        if (action == 0) {
//            MjpegView mjpegView2 = this.f1958G;
//            String str = this.f1998v;
//            int i3 = this.f1999w;
//            int i4 = this.f2000x;
//            mjpegView2.mo6051a(str, i3, i4, this.f2001y, 1, this.f2002z, i4);
//            int i5 = this.f1966O;
//            if (i5 == 1) {
//                int i6 = this.f1967P;
//                if (i6 == 1) {
//                    this.f1967P = i6 + 1;
//                } else if (i6 == 2) {
//                    this.f1967P = i6 + 1;
//                    point = this.f1969R;
//                    point.x = x;
//                    point.y = y;
//                    mjpegView = this.f1958G;
//                    i2 = 2;
//                    mjpegView.mo6050a(point, i2, i5, "", String.valueOf(this.f1976Y));
//                } else if (i6 == 3) {
//                    this.f1967P = i6 + 1;
//                    this.f1960I = x;
//                    this.f1961J = y;
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setTitle(getString(2131689478));
//                    builder.setMessage(getString(2131689479) + " [ " + this.f1998v + " ]");
//                    View inflate = LayoutInflater.from(this).inflate(2131427383, (ViewGroup) null);
//                    builder.setView(inflate);
//                    EditText editText = (EditText) inflate.findViewById(2131231023);
//                    editText.setText("60.0");
//                    editText.setInputType(8194);
//                    EditText editText2 = (EditText) inflate.findViewById(2131230904);
//                    editText2.setInputType(8194);
//                    builder.setPositiveButton("OK", new DialogInterface$OnClickListenerC0773l(editText, editText2));
//                    builder.setNegativeButton("Cancel", new DialogInterface$OnClickListenerC0762a(this));
//                    builder.show();
//                }
//            } else {
//                if (i5 == 2) {
//                    i = this.f1967P;
//                    if (i != 1) {
//                        if (i != 2) {
//                        }
//                        this.f1967P = i + 1;
//                        point = this.f1969R;
//                        point.x = x;
//                        point.y = y;
//                        mjpegView = this.f1958G;
//                        i2 = 2;
//                        mjpegView.mo6050a(point, i2, i5, "", String.valueOf(this.f1976Y));
//                    }
//                } else {
//                    if (i5 == 3) {
//                        i = this.f1967P;
//                        if (i != 1) {
//                            if (i != 2) {
//                                if (i != 3) {
//                                }
//                            }
//                            this.f1967P = i + 1;
//                            point = this.f1969R;
//                            point.x = x;
//                            point.y = y;
//                            mjpegView = this.f1958G;
//                            i2 = 2;
//                            mjpegView.mo6050a(point, i2, i5, "", String.valueOf(this.f1976Y));
//                        }
//                    } else if (i5 == 4) {
//                        i = this.f1967P;
//                        if (i != 1) {
//                            if (i != 2) {
//                                if (i != 3) {
//                                }
//                            }
//                            this.f1967P = i + 1;
//                            point = this.f1969R;
//                            point.x = x;
//                            point.y = y;
//                            mjpegView = this.f1958G;
//                            i2 = 2;
//                            mjpegView.mo6050a(point, i2, i5, "", String.valueOf(this.f1976Y));
//                        }
//                    } else if (i5 == 5) {
//                        i = this.f1967P;
//                        if (i == 1) {
//                            this.f1967P = i + 1;
//                            Point point2 = this.f1968Q;
//                            point2.x = x;
//                            point2.y = y;
//                            this.f1958G.mo6050a(point2, 1, i5, "", String.valueOf(this.f1976Y));
//                            Toast.makeText(getApplicationContext(), "Lower-right corner of rectangle", 1).show();
//                        } else {
//                            if (i != 2) {
//                            }
//                            this.f1967P = i + 1;
//                            point = this.f1969R;
//                            point.x = x;
//                            point.y = y;
//                            mjpegView = this.f1958G;
//                            i2 = 2;
//                            mjpegView.mo6050a(point, i2, i5, "", String.valueOf(this.f1976Y));
//                        }
//                    } else if (i5 == 8) {
//                        i = this.f1967P;
//                        if (i != 1) {
//                            if (i != 2) {
//                                if (i != 3) {
//                                }
//                            }
//                            this.f1967P = i + 1;
//                            point = this.f1969R;
//                            point.x = x;
//                            point.y = y;
//                            mjpegView = this.f1958G;
//                            i2 = 2;
//                            mjpegView.mo6050a(point, i2, i5, "", String.valueOf(this.f1976Y));
//                        }
//                    } else if (i5 == 6) {
//                        int i7 = this.f1967P;
//                        if (i7 == 1) {
//                            this.f1967P = i7 + 1;
//                            Point point3 = this.f1968Q;
//                            point3.x = x;
//                            point3.y = y;
//                            this.f1958G.mo6050a(point3, 1, i5, "", String.valueOf(this.f1976Y));
//                            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
//                            EditText editText3 = new EditText(this);
//                            builder2.setView(editText3);
//                            builder2.setPositiveButton("OK", new DialogInterface$OnClickListenerC0763b(editText3));
//                            builder2.setNegativeButton("CANCEL", new DialogInterface$OnClickListenerC0764c());
//                            builder2.create().show();
//                        } else if (i7 == 2) {
//                            Point point4 = this.f1969R;
//                            point4.x = x;
//                            point4.y = y;
//                            MjpegView mjpegView3 = this.f1958G;
//                            int i8 = 2;
//                            String str2 = this.f1991o;
//                            String valueOf = String.valueOf(this.f1976Y);
//                            String str3 = str2;
//                            mjpegView3.mo6050a(point4, i8, i5, str3, valueOf);
//                            this.f1967P = 1;
//                        }
//                    }
//                    this.f1967P = i + 1;
//                    point = this.f1970S;
//                    point.x = x;
//                    point.y = y;
//                    mjpegView = this.f1958G;
//                    i2 = 3;
//                    mjpegView.mo6050a(point, i2, i5, "", String.valueOf(this.f1976Y));
//                }
//                this.f1967P = i + 1;
//            }
//            point = this.f1968Q;
//            point.x = x;
//            point.y = y;
//            mjpegView = this.f1958G;
//            i2 = 1;
//            mjpegView.mo6050a(point, i2, i5, "", String.valueOf(this.f1976Y));
//        }
//        return super.onTouchEvent(motionEvent);
//    }
}
