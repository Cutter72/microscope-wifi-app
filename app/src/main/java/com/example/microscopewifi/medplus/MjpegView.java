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
    private int leftBorder = 0;

    /* renamed from: P */
    private int topBorder = 0;

    /* renamed from: b */
    SurfaceHolder surfaceHolder;

    /* renamed from: c */
    Context context;

    /* renamed from: d */
    private DrawImageThread drawImageThread;

    /* renamed from: e */
    private InputStreamHandler inputStreamHandler = null;

    /* renamed from: f */
    private boolean whileThis = false;

    /* renamed from: g */
    private boolean surfaceCreated = false;

    /* renamed from: h */
    private int rightBorder;

    /* renamed from: i */
    private int bottomBorder;

    /* renamed from: j */
    private int displayMode;

    /* renamed from: k */
    private boolean f2048k = false;

    /* renamed from: l */
    public Bitmap bitmapImage = null;

    /* renamed from: m */
    public int pixelWidth = 1280;

    /* renamed from: n */
    public int pixelHeight = 1024;

    /* renamed from: q */
    private int filePathInt = 0;

    /* renamed from: r */
    public String filePath = null;

    /* renamed from: MjpegView$a */
    public class DrawImageThread extends Thread {

        /* renamed from: b */
        private final SurfaceHolder surfaceHolder2;

        /* renamed from: c */
        private boolean f2065c = false;

        /* renamed from: d */
        private Rect rectangle = null;

        DrawImageThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder2 = surfaceHolder;
        }

        /* renamed from: b */
        private Rect getRectangle(int width, int height) {
            Rect rect;
            if (MjpegView.this.displayMode == 1) {
                MjpegView mjpegView2 = MjpegView.this;
                mjpegView2.leftBorder = (mjpegView2.rightBorder / 2) - (width / 2);
                MjpegView mjpegView3 = MjpegView.this;
                mjpegView3.topBorder = (mjpegView3.bottomBorder / 2) - (height / 2);
                return new Rect(MjpegView.this.leftBorder, MjpegView.this.topBorder, width + MjpegView.this.leftBorder, height + MjpegView.this.topBorder);
            }
            if (MjpegView.this.displayMode == 4) {
                float f = ((float) width) / ((float) height);
                int i3 = MjpegView.this.rightBorder;
                int i4 = (int) (((float) MjpegView.this.rightBorder) / f);
                if (i4 > MjpegView.this.bottomBorder) {
                    i4 = MjpegView.this.bottomBorder;
                    i3 = (int) (((float) MjpegView.this.bottomBorder) * f);
                }
                MjpegView mjpegView4 = MjpegView.this;
                mjpegView4.leftBorder = (mjpegView4.rightBorder / 2) - (i3 / 2);
                MjpegView mjpegView5 = MjpegView.this;
                mjpegView5.topBorder = (mjpegView5.bottomBorder / 2) - (i4 / 2);
                rect = new Rect(MjpegView.this.leftBorder, MjpegView.this.topBorder, i3 + MjpegView.this.leftBorder, i4 + MjpegView.this.topBorder);
            } else if (MjpegView.this.displayMode != 8) {
                return null;
            } else {
                MjpegView.this.leftBorder = 0;
                MjpegView.this.topBorder = 0;
                rect = new Rect(0, 0, MjpegView.this.rightBorder, MjpegView.this.bottomBorder);
            }
            return rect;
        }

        /* renamed from: a */
        void setRightBottomBorders(int rightBorder, int bottomBorder) {
            synchronized (this.surfaceHolder2) {
                MjpegView.this.rightBorder = rightBorder;
                MjpegView.this.bottomBorder = bottomBorder;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:38:0x00f4  */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x00fd  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x0005 A[SYNTHETIC] */
        public void run() {
            Throwable th;
            Paint paint = new Paint();
            while (MjpegView.this.whileThis) {
                if (MjpegView.this.surfaceCreated) {
                    Canvas canvas = null;
                    try {
                        if (MjpegView.this.bitmapImage == null) {
                            MjpegView.this.bitmapImage = Bitmap.createBitmap(MjpegView.this.pixelWidth, MjpegView.this.pixelHeight, Bitmap.Config.ARGB_8888);
                        }
                        MjpegView.this.bitmapImage = MjpegView.this.inputStreamHandler.decodeBitmapFromStream();
                        if (MjpegView.this.bitmapImage != null) {
                            if (MjpegView.this.filePathInt == 1) {
                                MjpegView.this.filePathInt = 0;
                            }
                            if (!this.f2065c) {
                                this.f2065c = true;
                                this.rectangle = getRectangle(MjpegView.this.bitmapImage.getWidth(), MjpegView.this.bitmapImage.getHeight());
                            }
                            Canvas lockCanvas = this.surfaceHolder2.lockCanvas();
                            try {
                                synchronized (this.surfaceHolder2) {
                                    lockCanvas.drawBitmap(MjpegView.this.bitmapImage, null, this.rectangle, paint);
                                }
                                if (lockCanvas != null) {
                                    this.surfaceHolder2.unlockCanvasAndPost(lockCanvas);
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                canvas = lockCanvas;
                                throw th;
                            }
                        }
                    } catch (IOException unused2) {
                        unused2.printStackTrace();
                    } catch (Throwable th3) {
                        th = th3;
                        if (canvas != null) {
                            this.surfaceHolder2.unlockCanvasAndPost(canvas);
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
        initializeFields(context);
    }

    public MjpegView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initializeFields(context);
    }

    /* renamed from: a */
    private void initializeFields(Context context) {
        SurfaceHolder holder = getHolder();
        this.surfaceHolder = holder;
        this.context = context;
        holder.addCallback(this);
        this.drawImageThread = new DrawImageThread(this.surfaceHolder);
        setFocusable(true);
        this.displayMode = 1;
        this.rightBorder = getWidth();
        this.bottomBorder = getHeight();
    }

    /* renamed from: a */
    public void setResolution(int width, int height) {
        this.pixelWidth = width;
        this.pixelHeight = height;
    }

    /* renamed from: a */
    public void setFilePath(int filePathInt, String filePath) {
        this.filePathInt = filePathInt;
        this.filePath = filePath;
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
            DrawImageThread aVar = new DrawImageThread(holder);
            this.drawImageThread = aVar;
            aVar.start();
            this.f2048k = false;
        }
    }

    /* renamed from: e */
    public void mo6056e() {
        if (this.inputStreamHandler != null) {
            this.whileThis = true;
            if (this.drawImageThread == null) {
                this.drawImageThread = new DrawImageThread(this.surfaceHolder);
            }
            this.drawImageThread.start();
        }
    }

    /* renamed from: f */
    public void mo6057f() {
        boolean z = true;
        if (this.whileThis) {
            this.f2048k = true;
        }
        this.whileThis = false;
        if (this.drawImageThread != null) {
            while (z) {
                try {
                    this.drawImageThread.join();
                    z = false;
                } catch (InterruptedException unused) {
                    unused.printStackTrace();
                }
            }
            this.drawImageThread = null;
        }
        if (this.inputStreamHandler != null) {
            try {
                this.inputStreamHandler.close();
            } catch (IOException unused2) {
                unused2.printStackTrace();
            }
            this.inputStreamHandler = null;
        }
    }

    public void setDisplayMode(int i) {
        this.displayMode = i; // 8 -> stretched to full area, 1, 4 -> normal
    }

    public void setSource(InputStreamHandler inputStreamHandler) {
        this.inputStreamHandler = inputStreamHandler;
        if (!this.f2048k) {
            mo6056e();
        } else {
            mo6055d();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int rightBorder, int bottomBorder) {
        DrawImageThread drawImageThread = this.drawImageThread;
        if (drawImageThread != null) {
            drawImageThread.setRightBottomBorders(rightBorder, bottomBorder);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.surfaceCreated = true;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.surfaceCreated = false;
        mo6057f();
    }
}
