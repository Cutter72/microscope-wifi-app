package com.example.microscopewifi.medplus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class MjpegView extends SurfaceView implements SurfaceHolder.Callback {

    /* renamed from: O */
    private int f2035O = 0;

    /* renamed from: P */
    private int f2036P = 0;

    /* renamed from: b */
    SurfaceHolder surfaceHolder;

    /* renamed from: c */
    Context context;

    /* renamed from: d */
    private SaveImageThread saveImageThread;

    /* renamed from: e */
    private InputStreamHandler inputStreamHandler = null;

    /* renamed from: f */
    private boolean whileThis = false;

    /* renamed from: g */
    private boolean f2044g = false;

    /* renamed from: h */
    private int f2045h;

    /* renamed from: i */
    private int f2046i;

    /* renamed from: j */
    private int f2047j;

    /* renamed from: k */
    private boolean f2048k = false;

    /* renamed from: l */
    public Bitmap bitmapImage = null;

    /* renamed from: m */
    public int pixelWidth2 = 1280;

    /* renamed from: n */
    public int pixelHeight2 = 1024;

    /* renamed from: q */
    private int f2054q = 0;

    /* renamed from: r */
    public String filePath = null;

    /* renamed from: MjpegView$a */
    public class SaveImageThread extends Thread {

        /* renamed from: b */
        private final SurfaceHolder f2064b;

        /* renamed from: c */
        private boolean f2065c = false;

        /* renamed from: d */
        private Rect f2066d = null;

        SaveImageThread(SurfaceHolder surfaceHolder) {
            this.f2064b = surfaceHolder;
        }

        /* renamed from: b */
        private Rect m2711b(int i, int i2) {
            Rect rect;
            if (MjpegView.this.f2047j == 1) {
                MjpegView mjpegView2 = MjpegView.this;
                mjpegView2.f2035O = (mjpegView2.f2045h / 2) - (i / 2);
                MjpegView mjpegView3 = MjpegView.this;
                mjpegView3.f2036P = (mjpegView3.f2046i / 2) - (i2 / 2);
                return new Rect(MjpegView.this.f2035O, MjpegView.this.f2036P, i + MjpegView.this.f2035O, i2 + MjpegView.this.f2036P);
            }
            if (MjpegView.this.f2047j == 4) {
                float f = ((float) i) / ((float) i2);
                int i3 = MjpegView.this.f2045h;
                int i4 = (int) (((float) MjpegView.this.f2045h) / f);
                if (i4 > MjpegView.this.f2046i) {
                    i4 = MjpegView.this.f2046i;
                    i3 = (int) (((float) MjpegView.this.f2046i) * f);
                }
                MjpegView mjpegView4 = MjpegView.this;
                mjpegView4.f2035O = (mjpegView4.f2045h / 2) - (i3 / 2);
                MjpegView mjpegView5 = MjpegView.this;
                mjpegView5.f2036P = (mjpegView5.f2046i / 2) - (i4 / 2);
                rect = new Rect(MjpegView.this.f2035O, MjpegView.this.f2036P, i3 + MjpegView.this.f2035O, i4 + MjpegView.this.f2036P);
            } else if (MjpegView.this.f2047j != 8) {
                return null;
            } else {
                MjpegView.this.f2035O = 0;
                MjpegView.this.f2036P = 0;
                rect = new Rect(0, 0, MjpegView.this.f2045h, MjpegView.this.f2046i);
            }
            return rect;
        }

        /* renamed from: a */
        void mo6065a(int i, int i2) {
            synchronized (this.f2064b) {
                MjpegView.this.f2045h = i;
                MjpegView.this.f2046i = i2;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:38:0x00f4  */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x00fd  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x0005 A[SYNTHETIC] */
        public void run() {
            Throwable th;
            Paint paint = new Paint();
            while (MjpegView.this.whileThis) {
                if (MjpegView.this.f2044g) {
                    Canvas canvas = null;
                    try {
                        if (MjpegView.this.bitmapImage == null) {
                            MjpegView.this.bitmapImage = Bitmap.createBitmap(MjpegView.this.pixelWidth2, MjpegView.this.pixelHeight2, Bitmap.Config.ARGB_8888);
                        }
                        MjpegView.this.bitmapImage = MjpegView.this.inputStreamHandler.mo6140a();
                        if (MjpegView.this.bitmapImage != null) {
                            if (MjpegView.this.f2054q == 1) {
                                MjpegView.this.f2054q = 0;
//                                saveImage(MjpegView.this.bitmapImage);
                            }
                            if (!this.f2065c) {
                                this.f2065c = true;
                                this.f2066d = m2711b(MjpegView.this.bitmapImage.getWidth(), MjpegView.this.bitmapImage.getHeight());
                            }
                            Canvas lockCanvas = this.f2064b.lockCanvas();
                            try {
                                synchronized (this.f2064b) {
//                                    lockCanvas.drawColor(-16777216);
                                    lockCanvas.drawBitmap(MjpegView.this.bitmapImage, (Rect) null, this.f2066d, paint);
                                }
                                if (lockCanvas != null) {
                                    this.f2064b.unlockCanvasAndPost(lockCanvas);
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                canvas = lockCanvas;
                                throw th;
                            }
                        }
                    } catch (IOException unused2) {
                        if (canvas == null) {
                            this.f2064b.unlockCanvasAndPost(canvas);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (canvas != null) {
                            this.f2064b.unlockCanvasAndPost(canvas);
                        }
                        try {
                            throw th;
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public MjpegView(Context context) {
        super(context);
        m2668a(context);
    }

    public MjpegView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m2668a(context);
    }

    /* renamed from: a */
    private void m2668a(Context context) {
        SurfaceHolder holder = getHolder();
        this.surfaceHolder = holder;
        this.context = context;
        holder.addCallback(this);
        this.saveImageThread = new SaveImageThread(this.surfaceHolder);
        setFocusable(true);
        this.f2047j = 1;
        this.f2045h = getWidth();
        this.f2046i = getHeight();
    }

    /* renamed from: a */
    public void setResolution(int i, int i2) {
        this.pixelWidth2 = i;
        this.pixelHeight2 = i2;
    }

    /* renamed from: a */
    public void setFilePath(int i, String str) {
        this.f2054q = i;
        this.filePath = str;
    }

    /* renamed from: b */
    public boolean mo6053b() {
        return this.whileThis;
    }

    /* renamed from: d */
    public void mo6055d() {
        if (this.f2048k && this.inputStreamHandler != null) {
            this.whileThis = true;
            SurfaceHolder holder = getHolder();
            holder.addCallback(this);
            SaveImageThread aVar = new SaveImageThread(holder);
            this.saveImageThread = aVar;
            aVar.start();
            this.f2048k = false;
        }
    }

    /* renamed from: e */
    public void mo6056e() {
        if (this.inputStreamHandler != null) {
            this.whileThis = true;
            if (this.saveImageThread == null) {
                this.saveImageThread = new SaveImageThread(this.surfaceHolder);
            }
            this.saveImageThread.start();
        }
    }

    /* renamed from: f */
    public void mo6057f() {
        boolean z = true;
        if (this.whileThis) {
            this.f2048k = true;
        }
        this.whileThis = false;
        if (this.saveImageThread != null) {
            while (z) {
                try {
                    this.saveImageThread.join();
                    z = false;
                } catch (InterruptedException unused) {
                    unused.printStackTrace();
                }
            }
            this.saveImageThread = null;
        }
        InputStreamHandler fVar = this.inputStreamHandler;
        if (fVar != null) {
            try {
                fVar.close();
            } catch (IOException unused2) {
                unused2.printStackTrace();
            }
            this.inputStreamHandler = null;
        }
    }

    public void setDisplayMode(int i) {
        this.f2047j = i;
    }

    public void setSource(InputStreamHandler fVar) {
        this.inputStreamHandler = fVar;
        if (!this.f2048k) {
            mo6056e();
        } else {
            mo6055d();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        SaveImageThread aVar = this.saveImageThread;
        if (aVar != null) {
            aVar.mo6065a(i2, i3);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f2044g = true;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f2044g = false;
        mo6057f();
    }
}
