package com.example.microscopewifi.medplus;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.ArrayList;

public class MjpegView extends SurfaceView implements SurfaceHolder.Callback {

    /* renamed from: A */
    public int f2021A = -65536;

    /* renamed from: B */
    ArrayList<C0820j> f2022B = new ArrayList<>();

    /* renamed from: C */
    private String f2023C = "";

    /* renamed from: D */
    private float f2024D = 0.0f;

    /* renamed from: E */
    Point f2025E = new Point(0, 0);

    /* renamed from: F */
    Point f2026F = new Point(0, 0);

    /* renamed from: G */
    Point f2027G = new Point(0, 0);

    /* renamed from: H */
    Point f2028H = new Point(0, 0);

    /* renamed from: I */
    private int f2029I = 0;

    /* renamed from: J */
    private int f2030J = 0;

    /* renamed from: K */
    private int f2031K = 0;

    /* renamed from: L */
    private int f2032L = 0;

    /* renamed from: M */
    private int f2033M = 0;

    /* renamed from: N */
    private int f2034N = 0;

    /* renamed from: O */
    private int f2035O = 0;

    /* renamed from: P */
    private int f2036P = 0;

    /* renamed from: Q */
    private float f2037Q = 1280.0f;

    /* renamed from: R */
    private float f2038R = 1024.0f;

    /* renamed from: b */
    SurfaceHolder surfaceHolder;

    /* renamed from: c */
    Context context;

    /* renamed from: d */
    private SaveImageThread saveImageThread;

    /* renamed from: e */
    private C0815f f2042e = null;

    /* renamed from: f */
    private boolean f2043f = false;

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

    /* renamed from: o */
    private int pixelWidth = 1280;

    /* renamed from: p */
    private int pixelHeight = 1024;

    /* renamed from: q */
    private int f2054q = 0;

    /* renamed from: r */
    public String filePath = null;

    /* renamed from: s */
    double f2056s = 1.0d;

    /* renamed from: t */
    int f2057t = 0;

    /* renamed from: u */
    public String f2058u = "mm";

    /* renamed from: v */
    public int f2059v = 2;

    /* renamed from: w */
    public int f2060w = -65536;

    /* renamed from: x */
    public int f2061x = 25;

    /* renamed from: y */
    public int f2062y = 1;

    /* renamed from: z */
    public int f2063z = 2;

    /* renamed from: MjpegView$a */
    public class SaveImageThread extends Thread {

        /* renamed from: b */
        private final SurfaceHolder f2064b;

        /* renamed from: c */
        private boolean f2065c = false;

        /* renamed from: d */
        private Rect f2066d = null;

        SaveImageThread(SurfaceHolder surfaceHolder, Context context) {
            this.f2064b = surfaceHolder;
        }

        /* renamed from: b */
        private Rect m2711b(int i, int i2) {
            Rect rect;
            MjpegView mjpegView;
            int width;
            if (MjpegView.this.f2047j == 1) {
                MjpegView mjpegView2 = MjpegView.this;
                mjpegView2.f2035O = (mjpegView2.f2045h / 2) - (i / 2);
                MjpegView mjpegView3 = MjpegView.this;
                mjpegView3.f2036P = (mjpegView3.f2046i / 2) - (i2 / 2);
                MjpegView.this.f2037Q = (float) i;
                MjpegView.this.f2038R = (float) i2;
                Rect rect2 = new Rect(MjpegView.this.f2035O, MjpegView.this.f2036P, i + MjpegView.this.f2035O, i2 + MjpegView.this.f2036P);
                MjpegView.this.f2029I = rect2.width() / 2;
                MjpegView.this.f2030J = rect2.width() / 3;
                MjpegView.this.f2031K = (rect2.width() * 2) / 3;
                MjpegView.this.f2032L = rect2.height() / 2;
                MjpegView.this.f2033M = rect2.height() / 3;
                MjpegView.this.f2034N = (rect2.height() * 2) / 3;
                return rect2;
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
                MjpegView.this.f2037Q = (float) i3;
                MjpegView.this.f2038R = (float) i4;
                rect = new Rect(MjpegView.this.f2035O, MjpegView.this.f2036P, i3 + MjpegView.this.f2035O, i4 + MjpegView.this.f2036P);
                MjpegView mjpegView6 = MjpegView.this;
                mjpegView6.f2029I = mjpegView6.f2035O + (rect.width() / 2);
                MjpegView mjpegView7 = MjpegView.this;
                mjpegView7.f2030J = mjpegView7.f2035O + (rect.width() / 3);
                mjpegView = MjpegView.this;
                width = mjpegView.f2035O + ((rect.width() * 2) / 3);
            } else if (MjpegView.this.f2047j != 8) {
                return null;
            } else {
                MjpegView.this.f2035O = 0;
                MjpegView.this.f2036P = 0;
                MjpegView mjpegView8 = MjpegView.this;
                mjpegView8.f2037Q = (float) mjpegView8.f2045h;
                MjpegView mjpegView9 = MjpegView.this;
                mjpegView9.f2038R = (float) mjpegView9.f2046i;
                rect = new Rect(0, 0, MjpegView.this.f2045h, MjpegView.this.f2046i);
                MjpegView.this.f2029I = rect.width() / 2;
                MjpegView.this.f2030J = rect.width() / 3;
                mjpegView = MjpegView.this;
                width = (rect.width() * 2) / 3;
            }
            mjpegView.f2031K = width;
            MjpegView.this.f2032L = rect.height() / 2;
            MjpegView.this.f2033M = rect.height() / 3;
            MjpegView.this.f2034N = (rect.height() * 2) / 3;
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
            while (MjpegView.this.f2043f) {
                if (MjpegView.this.f2044g) {
                    Canvas canvas = null;
                    try {
                        if (MjpegView.this.bitmapImage == null) {
                            MjpegView.this.bitmapImage = Bitmap.createBitmap(MjpegView.this.pixelWidth2, MjpegView.this.pixelHeight2, Bitmap.Config.ARGB_8888);
                        }
                        MjpegView.this.bitmapImage = MjpegView.this.f2042e.mo6140a();
                        if (MjpegView.this.bitmapImage != null) {
                            MjpegView.this.pixelWidth = MjpegView.this.bitmapImage.getWidth();
                            MjpegView.this.pixelHeight = MjpegView.this.bitmapImage.getHeight();
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
                                    lockCanvas.drawColor(-16777216);
                                    lockCanvas.drawBitmap(MjpegView.this.bitmapImage, (Rect) null, this.f2066d, paint);
                                    MjpegView.this.mo6049a(paint, this.f2066d, lockCanvas, MjpegView.this.f2037Q / ((float) MjpegView.this.pixelWidth), MjpegView.this.f2038R / ((float) MjpegView.this.pixelHeight));
                                }
                                if (lockCanvas != null) {
                                    this.f2064b.unlockCanvasAndPost(lockCanvas);
                                }
//                            } catch (IOException unused) {
//                                canvas = lockCanvas;
//                                if (canvas == null) {
//                                }
                            } catch (Throwable th2) {
                                th = th2;
                                canvas = lockCanvas;
                                if (canvas != null) {
                                }
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
        this.saveImageThread = new SaveImageThread(this.surfaceHolder, context);
        setFocusable(true);
        this.f2047j = 1;
        this.f2045h = getWidth();
        this.f2046i = getHeight();
    }

    /* renamed from: a */
    public float mo6044a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        float f;
        float f2;
        float f3 = (float) i;
        float f4 = (float) i3;
        float f5 = (float) i5;
        float f6 = (float) i7;
        float f7 = (float) i2;
        float f8 = (float) i4;
        float f9 = (float) i6;
        float f10 = (float) i8;
        float f11 = f4 - f3;
        float f12 = f6 - f5;
        float f13 = f8 - f7;
        float f14 = f10 - f9;
        int i9 = (f11 > 0.0f ? 1 : (f11 == 0.0f ? 0 : -1));
        if (i9 != 0 && f12 != 0.0f) {
            float f15 = f14 / f12;
            float f16 = f13 / f11;
            if (f15 == f16) {
                return (f13 != 0.0f || f14 != 0.0f || f4 >= f3 || f3 >= f6) ? 0.0f : 180.0f;
            }
            f = ((((f15 * f6) - f10) - (f16 * f4)) + f8) / (f15 - f16);
            f2 = (f16 * (f - f4)) + f8;
        } else if (i9 == 0 && f12 != 0.0f) {
            f2 = ((f14 / f12) * (f3 - f6)) + f10;
            f = f3;
        } else if (i9 == 0 || f12 != 0.0f) {
            return (i9 != 0 || f12 != 0.0f || f8 >= f7 || f7 >= f10) ? 0.0f : 180.0f;
        } else {
            f2 = ((f13 / f11) * (f5 - f4)) + f8;
            f = f5;
        }
        float f17 = f - f3;
        float f18 = f2 - f7;
        float f19 = (f17 * f17) + (f18 * f18);
        float f20 = f - f4;
        float f21 = f2 - f8;
        if (f19 <= (f20 * f20) + (f21 * f21)) {
            f3 = f4;
            f7 = f8;
        }
        float f22 = f - f5;
        float f23 = f2 - f9;
        float f24 = (f22 * f22) + (f23 * f23);
        float f25 = f - f6;
        float f26 = f2 - f10;
        if (f24 <= (f25 * f25) + (f26 * f26)) {
            f5 = f6;
            f9 = f10;
        }
        float f27 = f3 - f;
        float f28 = f5 - f;
        float f29 = f7 - f2;
        float f30 = f9 - f2;
        return (float) ((Math.acos((double) (((f27 * f28) + (f29 * f30)) / (((float) Math.sqrt((double) ((f27 * f27) + (f29 * f29)))) * ((float) Math.sqrt((double) ((f28 * f28) + (f30 * f30))))))) * 180.0d) / 3.1415926d);
    }

    /* renamed from: a */
    public final Point mo6045a(Point point, Point point2, Point point3) {
        float f;
        float f2;
        int i = point.x;
        float f3 = (float) (i - point2.x);
        float f4 = (float) (point.y - point2.y);
        float pow = ((float) (((Math.pow((double) i, 2.0d) - Math.pow((double) point2.x, 2.0d)) + Math.pow((double) point.y, 2.0d)) - Math.pow((double) point2.y, 2.0d))) / 2.0f;
        int i2 = point3.x;
        float f5 = (float) (i2 - point2.x);
        float f6 = (float) (point3.y - point2.y);
        float pow2 = ((float) (((Math.pow((double) i2, 2.0d) - Math.pow((double) point2.x, 2.0d)) + Math.pow((double) point3.y, 2.0d)) - Math.pow((double) point2.y, 2.0d))) / 2.0f;
        float f7 = (f3 * f6) - (f5 * f4);
        if (f7 == 0.0f) {
            f = (float) point.x;
            f2 = (float) point.y;
        } else {
            f = ((f6 * pow) - (f4 * pow2)) / f7;
            f2 = ((f3 * pow2) - (f5 * pow)) / f7;
        }
        return new Point((int) (((double) f) + 0.5d), (int) (((double) f2) + 0.5d));
    }

    /* renamed from: a */
    public void setResolution(int i, int i2) {
        this.pixelWidth2 = i;
        this.pixelHeight2 = i2;
    }

    /* renamed from: a */
    public void setFilePath(int i, String str, String str2) {
        this.f2054q = i;
        this.filePath = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:212:0x07c1  */
    /* renamed from: a */
    public final void mo6049a(Paint paint, Rect rect, Canvas canvas, float f, float f2) {
        int size;
        int i = 0;
        int i2;
        int i3;
        double d;
        double d2;
        double d3;
        int i4;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8;
        int i5;
        C0820j jVar = null;
        Point point = null;
        ArrayList<Point> arrayList = null;
        int i6;
        Point point2;
        Paint paint2 = null;
        int i7 = 0;
        int i8;
        Point point3;
        int i9;
        int i10;
        Point point4;
        int i11;
        int i12;
        Point point5;
        int i13;
        int i14;
        C0820j jVar2 = null;
        ArrayList<Point> arrayList2 = null;
        Point point6 = null;
        int i15;
        int i16;
        int i17;
        int i18;
        Point point7;
        int i19;
        int i20;
        Point point8;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        Point point9;
        int i26;
        int i27;
        Point point10;
        int i28;
        int i29;
        Point point11;
        int i30;
        int i31;
        int i32;
        int i33;
        int i34;
        int i35;
        Point point12;
        int i36;
        int i37;
        Point point13;
        int i38;
        int i39;
        Point point14;
        int i40;
        int i41;
        int i42;
        int i43;
        int i44;
        int i45;
        Point point15;
        int i46;
        int i47;
        Point point16;
        int i48;
        int i49;
        int i50;
        int i51;
        int i52;
        int i53;
        Point point17;
        int i54;
        int i55;
        Point point18;
        int i56;
        int i57;
        int i58;
        int i59;
        MjpegView mjpegView = this;
        Paint paint3 = paint;
        int i60 = mjpegView.f2057t;
        if (i60 == 1) {
            paint3.setColor(mjpegView.f2021A);
            paint3.setStrokeWidth(((float) mjpegView.f2063z) * f);
            Point point19 = mjpegView.f2025E;
            int i61 = point19.x;
            if (i61 == 0 || (i59 = point19.y) == 0) {
                i52 = 5;
            } else {
                i52 = 5;
                canvas.drawLine((float) (i61 - 5), (float) i59, (float) (i61 + 5), (float) i59, paint);
                Point point20 = mjpegView.f2025E;
                int i62 = point20.x;
                int i63 = point20.y;
                canvas.drawLine((float) i62, (float) (i63 - 5), (float) i62, (float) (i63 + 5), paint);
            }
            Point point21 = mjpegView.f2026F;
            int i64 = point21.x;
            if (!(i64 == 0 || (i58 = point21.y) == 0)) {
                canvas.drawLine((float) (i64 - 5), (float) i58, (float) (i64 + i52), (float) i58, paint);
                Point point22 = mjpegView.f2026F;
                int i65 = point22.x;
                int i66 = point22.y;
                canvas.drawLine((float) i65, (float) (i66 - 5), (float) i65, (float) (i66 + i52), paint);
            }
            Point point23 = mjpegView.f2025E;
            int i67 = point23.x;
            if (!(i67 == 0 || (i53 = point23.y) == 0 || (i54 = (point17 = mjpegView.f2026F).x) == 0 || (i55 = point17.y) == 0 || (i56 = (point18 = mjpegView.f2027G).x) == 0 || (i57 = point18.y) == 0)) {
                String str = mjpegView.f2023C;
                if (!(str == null || str.length() == 0)) {
                    float f3 = mjpegView.f2024D;
                    float parseFloat = Float.parseFloat(mjpegView.f2023C);
                    double sqrt = Math.sqrt(Math.pow((double) (i55 - i53), 2.0d) + Math.pow((double) (i54 - i67), 2.0d)) * mjpegView.f2056s;
                    float f4 = mjpegView.f2024D;
                    mjpegView.f2024D = (((float) (sqrt / ((double) f4))) / parseFloat) * f4;
                    SharedPreferences.Editor edit = mjpegView.context.getSharedPreferences("HiviewPlus3Preferences", 0).edit();
                    edit.putString("nMagSet", String.valueOf(f3) + "-" + String.valueOf(mjpegView.f2024D));
                    edit.apply();
                    jVar = new C0820j(1, mjpegView.f2058u, mjpegView.f2059v, mjpegView.f2063z, mjpegView.f2021A, mjpegView.f2061x, mjpegView.f2060w, mjpegView.f2062y);
                    jVar.f2267a.add(new Point(i67, i53));
                    jVar.f2267a.add(new Point(i54, i55));
                    arrayList = jVar.f2267a;
                    point = new Point(i56, i57);
                }
                Point point24 = mjpegView.f2025E;
                point24.y = 0;
                point24.x = 0;
                Point point25 = mjpegView.f2026F;
                point25.y = 0;
                point25.x = 0;
                Point point26 = mjpegView.f2027G;
                point26.y = 0;
                point26.x = 0;
            }
            size = mjpegView.f2022B.size();
            for (i = 0; i < size; i = i3 + 1) {
                C0820j jVar3 = mjpegView.f2022B.get(i);
                int i68 = jVar3.f2268b;
                if (i68 == 1) {
                    paint3.setColor(jVar3.f2270d);
                    paint3.setStrokeWidth(((float) jVar3.f2272f) * f);
                    canvas.drawLine((float) jVar3.f2267a.get(0).x, (float) jVar3.f2267a.get(0).y, (float) jVar3.f2267a.get(1).x, (float) jVar3.f2267a.get(1).y, paint);
                    paint3.setColor(jVar3.f2270d);
                    paint3.setTextSize(((float) jVar3.f2271e) * f);
                    double sqrt2 = (Math.sqrt(Math.pow((double) (jVar3.f2267a.get(1).y - jVar3.f2267a.get(0).y), 2.0d) + Math.pow((double) (jVar3.f2267a.get(1).x - jVar3.f2267a.get(0).x), 2.0d)) * mjpegView.f2056s) / ((double) mjpegView.f2024D);
                    int i69 = jVar3.f2269c;
                    if (i69 == 2) {
                        d8 = ((double) Math.round(sqrt2 * 100.0d)) / 100.0d;
                    } else if (i69 == 3) {
                        d8 = ((double) Math.round(sqrt2 * 1000.0d)) / 1000.0d;
                    } else if (i69 == 4) {
                        d8 = ((double) Math.round(sqrt2 * 10000.0d)) / 10000.0d;
                    } else if (i69 == 5) {
                        d8 = ((double) Math.round(sqrt2 * 100000.0d)) / 100000.0d;
                    } else {
                        i5 = 2;
                        d8 = 0.0d;
                        Object[] objArr = new Object[i5];
                        objArr[0] = Double.toString(d8);
                        objArr[1] = mjpegView.f2058u;
                        canvas.drawText(String.format("L:%s%s", objArr), (float) jVar3.f2267a.get(i5).x, (float) jVar3.f2267a.get(i5).y, paint3);
                    }
                    i5 = 2;
                    Object[] objArr2 = new Object[i5];
                    objArr2[0] = Double.toString(d8);
                    objArr2[1] = mjpegView.f2058u;
                    canvas.drawText(String.format("L:%s%s", objArr2), (float) jVar3.f2267a.get(i5).x, (float) jVar3.f2267a.get(i5).y, paint3);
                } else if (i68 == 2) {
                    paint3.setColor(jVar3.f2270d);
                    paint3.setStrokeWidth(((float) jVar3.f2272f) * f);
                    canvas.drawLine((float) jVar3.f2267a.get(0).x, (float) jVar3.f2267a.get(0).y, (float) jVar3.f2267a.get(1).x, (float) jVar3.f2267a.get(1).y, paint);
                    paint3.setColor(jVar3.f2270d);
                    paint3.setTextSize(((float) jVar3.f2271e) * f);
                    double sqrt3 = (Math.sqrt(Math.pow(((double) (jVar3.f2267a.get(1).y - jVar3.f2267a.get(0).y)) * 1.0d, 2.0d) + Math.pow(((double) (jVar3.f2267a.get(1).x - jVar3.f2267a.get(0).x)) * 1.0d, 2.0d)) * mjpegView.f2056s) / ((double) mjpegView.f2024D);
                    int i70 = jVar3.f2269c;
                    canvas.drawText(String.format("L:%s%s", Double.toString(i70 == 2 ? ((double) Math.round(sqrt3 * 100.0d)) / 100.0d : i70 == 3 ? ((double) Math.round(sqrt3 * 1000.0d)) / 1000.0d : i70 == 4 ? ((double) Math.round(sqrt3 * 10000.0d)) / 10000.0d : i70 == 5 ? ((double) Math.round(sqrt3 * 100000.0d)) / 100000.0d : 0.0d), mjpegView.f2058u), (float) jVar3.f2267a.get(2).x, (float) jVar3.f2267a.get(2).y, paint3);
                } else if (i68 == 3) {
                    paint3.setColor(jVar3.f2270d);
                    paint3.setStrokeWidth(((float) jVar3.f2272f) * f);
                    paint3.setStyle(Paint.Style.STROKE);
                    Point a = mjpegView.mo6045a(jVar3.f2267a.get(0), jVar3.f2267a.get(1), jVar3.f2267a.get(2));
                    double sqrt4 = Math.sqrt(Math.pow(((double) (a.y - jVar3.f2267a.get(0).y)) * 1.0d, 2.0d) + Math.pow(((double) (a.x - jVar3.f2267a.get(0).x)) * 1.0d, 2.0d));
                    canvas.drawCircle((float) a.x, (float) a.y, (float) sqrt4, paint3);
                    paint3.setColor(jVar3.f2270d);
                    paint3.setTextSize(((float) jVar3.f2271e) * f);
                    paint3.setStyle(Paint.Style.FILL);
                    mjpegView = this;
                    double d9 = (sqrt4 * mjpegView.f2056s) / ((double) mjpegView.f2024D);
                    double d10 = 3.141592653589793d * d9 * d9;
                    double d11 = 6.283185307179586d * d9;
                    int i71 = jVar3.f2269c;
                    if (i71 == 2) {
                        d5 = ((double) Math.round(d9 * 100.0d)) / 100.0d;
                        d6 = ((double) Math.round(d10 * 100.0d)) / 100.0d;
                        d7 = ((double) Math.round(d11 * 100.0d)) / 100.0d;
                    } else if (i71 == 3) {
                        d5 = ((double) Math.round(d9 * 1000.0d)) / 1000.0d;
                        d6 = ((double) Math.round(d10 * 1000.0d)) / 1000.0d;
                        d7 = ((double) Math.round(d11 * 1000.0d)) / 1000.0d;
                    } else if (i71 == 4) {
                        d5 = ((double) Math.round(d9 * 10000.0d)) / 10000.0d;
                        d6 = ((double) Math.round(d10 * 10000.0d)) / 10000.0d;
                        d7 = ((double) Math.round(d11 * 10000.0d)) / 10000.0d;
                    } else if (i71 == 5) {
                        d5 = ((double) Math.round(d9 * 100000.0d)) / 100000.0d;
                        d6 = ((double) Math.round(d10 * 100000.0d)) / 100000.0d;
                        d7 = ((double) Math.round(d11 * 100000.0d)) / 100000.0d;
                    } else {
                        d5 = 0.0d;
                        d6 = 0.0d;
                        d7 = 0.0d;
                    }
                    String format = String.format("R:%s%s", Double.toString(d5), mjpegView.f2058u);
                    String format2 = String.format("A:%s%s²", Double.toString(d6), mjpegView.f2058u);
                    String format3 = String.format("C:%s%s", Double.toString(d7), mjpegView.f2058u);
                    canvas.drawText(format, (float) jVar3.f2267a.get(3).x, (float) jVar3.f2267a.get(3).y, paint3);
                    canvas.drawText(format2, (float) jVar3.f2267a.get(3).x, ((float) jVar3.f2267a.get(3).y) + (((float) jVar3.f2271e) * f), paint3);
                    canvas.drawText(format3, (float) jVar3.f2267a.get(3).x, ((float) jVar3.f2267a.get(3).y) + (((float) (jVar3.f2271e * 2)) * f), paint3);
                } else {
                    if (i68 == 4) {
                        paint3.setColor(jVar3.f2270d);
                        paint3.setStrokeWidth(((float) jVar3.f2272f) * f);
                        canvas.drawLine((float) jVar3.f2267a.get(0).x, (float) jVar3.f2267a.get(0).y, (float) jVar3.f2267a.get(1).x, (float) jVar3.f2267a.get(1).y, paint);
                        canvas.drawLine((float) jVar3.f2267a.get(1).x, (float) jVar3.f2267a.get(1).y, (float) jVar3.f2267a.get(2).x, (float) jVar3.f2267a.get(2).y, paint);
                        double a2 = (double) mo6044a(jVar3.f2267a.get(0).x, jVar3.f2267a.get(0).y, jVar3.f2267a.get(1).x, jVar3.f2267a.get(1).y, jVar3.f2267a.get(1).x, jVar3.f2267a.get(1).y, jVar3.f2267a.get(2).x, jVar3.f2267a.get(2).y);
                        paint3.setColor(jVar3.f2270d);
                        paint3.setTextSize(((float) jVar3.f2271e) * f);
                        int i72 = jVar3.f2269c;
                        if (i72 == 2) {
                            d4 = ((double) Math.round(a2 * 100.0d)) / 100.0d;
                            i4 = 3;
                        } else {
                            i4 = 3;
                            d4 = i72 == 3 ? ((double) Math.round(a2 * 1000.0d)) / 1000.0d : i72 == 4 ? ((double) Math.round(a2 * 10000.0d)) / 10000.0d : i72 == 5 ? ((double) Math.round(a2 * 100000.0d)) / 100000.0d : 0.0d;
                        }
                        canvas.drawText(Double.toString(d4) + "°", (float) jVar3.f2267a.get(i4).x, (float) jVar3.f2267a.get(i4).y, paint3);
                        i2 = size;
                        i3 = i;
                    } else if (i68 == 5) {
                        paint3.setColor(jVar3.f2270d);
                        paint3.setStrokeWidth(((float) jVar3.f2272f) * f);
                        paint3.setStyle(Paint.Style.STROKE);
                        canvas.drawRect((float) jVar3.f2267a.get(0).x, (float) jVar3.f2267a.get(0).y, (float) jVar3.f2267a.get(1).x, (float) jVar3.f2267a.get(1).y, paint);
                        paint3.setStyle(Paint.Style.FILL);
                        paint3.setColor(jVar3.f2270d);
                        paint3.setTextSize(((float) jVar3.f2271e) * f);
                        double d12 = mjpegView.f2056s;
                        float f5 = mjpegView.f2024D;
                        i2 = size;
                        i3 = i;
                        double abs = (((double) Math.abs(jVar3.f2267a.get(1).x - jVar3.f2267a.get(0).x)) * d12) / ((double) f5);
                        double abs2 = (((double) Math.abs(jVar3.f2267a.get(1).y - jVar3.f2267a.get(0).y)) * d12) / ((double) f5);
                        double d13 = abs * abs2;
                        int i73 = jVar3.f2269c;
                        if (i73 == 2) {
                            d3 = ((double) Math.round(abs * 100.0d)) / 100.0d;
                            d2 = ((double) Math.round(abs2 * 100.0d)) / 100.0d;
                            d = ((double) Math.round(d13 * 100.0d)) / 100.0d;
                        } else if (i73 == 3) {
                            d3 = ((double) Math.round(abs * 1000.0d)) / 1000.0d;
                            d2 = ((double) Math.round(abs2 * 1000.0d)) / 1000.0d;
                            d = ((double) Math.round(d13 * 1000.0d)) / 1000.0d;
                        } else if (i73 == 4) {
                            d3 = ((double) Math.round(abs * 10000.0d)) / 10000.0d;
                            d2 = ((double) Math.round(abs2 * 10000.0d)) / 10000.0d;
                            d = ((double) Math.round(d13 * 10000.0d)) / 10000.0d;
                            String format4 = String.format("W:%s%s", Double.toString(d3), mjpegView.f2058u);
                            String format5 = String.format("H:%s%s", Double.toString(d2), mjpegView.f2058u);
                            String format6 = String.format("A:%s%s²", Double.toString(d), mjpegView.f2058u);
                            canvas.drawText(format4, (float) jVar3.f2267a.get(2).x, (float) jVar3.f2267a.get(2).y, paint3);
                            canvas.drawText(format5, (float) jVar3.f2267a.get(2).x, ((float) jVar3.f2267a.get(2).y) + (((float) jVar3.f2271e) * f), paint3);
                            canvas.drawText(format6, (float) jVar3.f2267a.get(2).x, ((float) jVar3.f2267a.get(2).y) + (((float) (jVar3.f2271e * 2)) * f), paint3);
                        } else {
                            if (i73 == 5) {
                                d3 = ((double) Math.round(abs * 100000.0d)) / 100000.0d;
                                d2 = ((double) Math.round(abs2 * 100000.0d)) / 100000.0d;
                                d = ((double) Math.round(d13 * 100000.0d)) / 100000.0d;
                            } else {
                                d3 = 0.0d;
                                d2 = 0.0d;
                                d = 0.0d;
                            }
                            String format42 = String.format("W:%s%s", Double.toString(d3), mjpegView.f2058u);
                            String format52 = String.format("H:%s%s", Double.toString(d2), mjpegView.f2058u);
                            String format62 = String.format("A:%s%s²", Double.toString(d), mjpegView.f2058u);
                            canvas.drawText(format42, (float) jVar3.f2267a.get(2).x, (float) jVar3.f2267a.get(2).y, paint3);
                            canvas.drawText(format52, (float) jVar3.f2267a.get(2).x, ((float) jVar3.f2267a.get(2).y) + (((float) jVar3.f2271e) * f), paint3);
                            canvas.drawText(format62, (float) jVar3.f2267a.get(2).x, ((float) jVar3.f2267a.get(2).y) + (((float) (jVar3.f2271e * 2)) * f), paint3);
                        }
                        String format422 = String.format("W:%s%s", Double.toString(d3), mjpegView.f2058u);
                        String format522 = String.format("H:%s%s", Double.toString(d2), mjpegView.f2058u);
                        String format622 = String.format("A:%s%s²", Double.toString(d), mjpegView.f2058u);
                        canvas.drawText(format422, (float) jVar3.f2267a.get(2).x, (float) jVar3.f2267a.get(2).y, paint3);
                        canvas.drawText(format522, (float) jVar3.f2267a.get(2).x, ((float) jVar3.f2267a.get(2).y) + (((float) jVar3.f2271e) * f), paint3);
                        canvas.drawText(format622, (float) jVar3.f2267a.get(2).x, ((float) jVar3.f2267a.get(2).y) + (((float) (jVar3.f2271e * 2)) * f), paint3);
                    } else {
                        i2 = size;
                        i3 = i;
                        if (i68 == 6) {
                            paint3.setColor(jVar3.f2270d);
                            paint3.setTextSize(((float) jVar3.f2271e) * f);
                            canvas.drawText(jVar3.f2273g, (float) jVar3.f2267a.get(0).x, (float) jVar3.f2267a.get(0).y, paint3);
                        }
                    }
                    size = i2;
                }
                i2 = size;
                i3 = i;
                size = i2;
            }
        } else if (i60 == 2) {
            paint3.setColor(mjpegView.f2021A);
            paint3.setStrokeWidth(((float) mjpegView.f2063z) * f);
            Point point27 = mjpegView.f2025E;
            int i74 = point27.x;
            if (!(i74 == 0 || (i51 = point27.y) == 0)) {
                canvas.drawLine((float) (i74 - 5), (float) i51, (float) (i74 + 5), (float) i51, paint);
                Point point28 = mjpegView.f2025E;
                int i75 = point28.x;
                int i76 = point28.y;
                canvas.drawLine((float) i75, (float) (i76 - 5), (float) i75, (float) (i76 + 5), paint);
            }
            Point point29 = mjpegView.f2026F;
            int i77 = point29.x;
            if (!(i77 == 0 || (i50 = point29.y) == 0)) {
                canvas.drawLine((float) (i77 - 5), (float) i50, (float) (i77 + 5), (float) i50, paint);
                Point point30 = mjpegView.f2026F;
                int i78 = point30.x;
                int i79 = point30.y;
                canvas.drawLine((float) i78, (float) (i79 - 5), (float) i78, (float) (i79 + 5), paint);
            }
            Point point31 = mjpegView.f2025E;
            int i80 = point31.x;
            if (!(i80 == 0 || (i45 = point31.y) == 0 || (i46 = (point15 = mjpegView.f2026F).x) == 0 || (i47 = point15.y) == 0 || (i48 = (point16 = mjpegView.f2027G).x) == 0 || (i49 = point16.y) == 0)) {
                jVar = new C0820j(2, mjpegView.f2058u, mjpegView.f2059v, mjpegView.f2063z, mjpegView.f2021A, mjpegView.f2061x, mjpegView.f2060w, mjpegView.f2062y);
                jVar.f2267a.add(new Point(i80, i45));
                jVar.f2267a.add(new Point(i46, i47));
                arrayList = jVar.f2267a;
                point = new Point(i48, i49);
            }
            size = mjpegView.f2022B.size();
            while (i < size) {
            }
        } else {
            if (i60 == 3) {
                paint3.setColor(mjpegView.f2021A);
                paint3.setStrokeWidth(((float) mjpegView.f2063z) * f);
                Point point32 = mjpegView.f2025E;
                int i81 = point32.x;
                if (!(i81 == 0 || (i44 = point32.y) == 0)) {
                    canvas.drawLine((float) (i81 - 5), (float) i44, (float) (i81 + 5), (float) i44, paint);
                    Point point33 = mjpegView.f2025E;
                    int i82 = point33.x;
                    int i83 = point33.y;
                    canvas.drawLine((float) i82, (float) (i83 - 5), (float) i82, (float) (i83 + 5), paint);
                }
                Point point34 = mjpegView.f2026F;
                int i84 = point34.x;
                if (!(i84 == 0 || (i43 = point34.y) == 0)) {
                    canvas.drawLine((float) (i84 - 5), (float) i43, (float) (i84 + 5), (float) i43, paint);
                    Point point35 = mjpegView.f2026F;
                    int i85 = point35.x;
                    int i86 = point35.y;
                    canvas.drawLine((float) i85, (float) (i86 - 5), (float) i85, (float) (i86 + 5), paint);
                }
                Point point36 = mjpegView.f2027G;
                int i87 = point36.x;
                if (!(i87 == 0 || (i42 = point36.y) == 0)) {
                    canvas.drawLine((float) (i87 - 5), (float) i42, (float) (i87 + 5), (float) i42, paint);
                    Point point37 = mjpegView.f2027G;
                    int i88 = point37.x;
                    int i89 = point37.y;
                    canvas.drawLine((float) i88, (float) (i89 - 5), (float) i88, (float) (i89 + 5), paint);
                }
                Point point38 = mjpegView.f2025E;
                int i90 = point38.x;
                if (!(i90 == 0 || (i35 = point38.y) == 0 || (i36 = (point12 = mjpegView.f2026F).x) == 0 || (i37 = point12.y) == 0 || (i38 = (point13 = mjpegView.f2027G).x) == 0 || (i39 = point13.y) == 0 || (i40 = (point14 = mjpegView.f2028H).x) == 0 || (i41 = point14.y) == 0)) {
                    jVar2 = new C0820j(3, mjpegView.f2058u, mjpegView.f2059v, mjpegView.f2063z, mjpegView.f2021A, mjpegView.f2061x, mjpegView.f2060w, mjpegView.f2062y);
                    jVar2.f2267a.add(new Point(i90, i35));
                    jVar2.f2267a.add(new Point(i36, i37));
                    jVar2.f2267a.add(new Point(i38, i39));
                    arrayList2 = jVar2.f2267a;
                    point6 = new Point(i40, i41);
                }
                paint3 = paint;
                size = mjpegView.f2022B.size();
                while (i < size) {
                    i++;
                }
            } else if (i60 == 4) {
                paint.setColor(mjpegView.f2021A);
                paint.setStrokeWidth(((float) mjpegView.f2063z) * f);
                Point point39 = mjpegView.f2025E;
                int i91 = point39.x;
                if (!(i91 == 0 || (i34 = point39.y) == 0)) {
                    canvas.drawLine((float) (i91 - 5), (float) i34, (float) (i91 + 5), (float) i34, paint);
                    Point point40 = mjpegView.f2025E;
                    int i92 = point40.x;
                    int i93 = point40.y;
                    canvas.drawLine((float) i92, (float) (i93 - 5), (float) i92, (float) (i93 + 5), paint);
                }
                Point point41 = mjpegView.f2026F;
                int i94 = point41.x;
                if (!(i94 == 0 || (i33 = point41.y) == 0)) {
                    canvas.drawLine((float) (i94 - 5), (float) i33, (float) (i94 + 5), (float) i33, paint);
                    Point point42 = mjpegView.f2026F;
                    int i95 = point42.x;
                    int i96 = point42.y;
                    canvas.drawLine((float) i95, (float) (i96 - 5), (float) i95, (float) (i96 + 5), paint);
                }
                Point point43 = mjpegView.f2027G;
                int i97 = point43.x;
                if (!(i97 == 0 || (i32 = point43.y) == 0)) {
                    canvas.drawLine((float) (i97 - 5), (float) i32, (float) (i97 + 5), (float) i32, paint);
                    Point point44 = mjpegView.f2027G;
                    int i98 = point44.x;
                    int i99 = point44.y;
                    canvas.drawLine((float) i98, (float) (i99 - 5), (float) i98, (float) (i99 + 5), paint);
                }
                Point point45 = mjpegView.f2025E;
                int i100 = point45.x;
                if (!(i100 == 0 || (i25 = point45.y) == 0 || (i26 = (point9 = mjpegView.f2026F).x) == 0 || (i27 = point9.y) == 0 || (i28 = (point10 = mjpegView.f2027G).x) == 0 || (i29 = point10.y) == 0 || (i30 = (point11 = mjpegView.f2028H).x) == 0 || (i31 = point11.y) == 0)) {
                    jVar2 = new C0820j(4, mjpegView.f2058u, mjpegView.f2059v, mjpegView.f2063z, mjpegView.f2021A, mjpegView.f2061x, mjpegView.f2060w, mjpegView.f2062y);
                    jVar2.f2267a.add(new Point(i100, i25));
                    jVar2.f2267a.add(new Point(i26, i27));
                    jVar2.f2267a.add(new Point(i28, i29));
                    arrayList2 = jVar2.f2267a;
                    point6 = new Point(i30, i31);
                }
                paint3 = paint;
                size = mjpegView.f2022B.size();
                while (i < size) {
                }
            } else {
                if (i60 == 5) {
                    paint3 = paint;
                    paint3.setColor(mjpegView.f2021A);
                    paint3.setStrokeWidth(((float) mjpegView.f2063z) * f);
                    Point point46 = mjpegView.f2025E;
                    int i101 = point46.x;
                    if (!(i101 == 0 || (i24 = point46.y) == 0)) {
                        canvas.drawLine((float) (i101 - 5), (float) i24, (float) (i101 + 5), (float) i24, paint);
                        Point point47 = mjpegView.f2025E;
                        int i102 = point47.x;
                        int i103 = point47.y;
                        canvas.drawLine((float) i102, (float) (i103 - 5), (float) i102, (float) (i103 + 5), paint);
                    }
                    Point point48 = mjpegView.f2026F;
                    int i104 = point48.x;
                    if (!(i104 == 0 || (i23 = point48.y) == 0)) {
                        canvas.drawLine((float) (i104 - 5), (float) i23, (float) (i104 + 5), (float) i23, paint);
                        Point point49 = mjpegView.f2026F;
                        int i105 = point49.x;
                        int i106 = point49.y;
                        canvas.drawLine((float) i105, (float) (i106 - 5), (float) i105, (float) (i106 + 5), paint);
                    }
                    Point point50 = mjpegView.f2025E;
                    int i107 = point50.x;
                    if (!(i107 == 0 || (i18 = point50.y) == 0 || (i19 = (point7 = mjpegView.f2026F).x) == 0 || (i20 = point7.y) == 0 || (i21 = (point8 = mjpegView.f2027G).x) == 0 || (i22 = point8.y) == 0)) {
                        jVar = new C0820j(5, mjpegView.f2058u, mjpegView.f2059v, mjpegView.f2063z, mjpegView.f2021A, mjpegView.f2061x, mjpegView.f2060w, mjpegView.f2062y);
                        jVar.f2267a.add(new Point(i107, i18));
                        jVar.f2267a.add(new Point(i19, i20));
                        arrayList = jVar.f2267a;
                        point = new Point(i21, i22);
                    }
                } else {
                    if (i60 == 8) {
                        paint.setColor(mjpegView.f2021A);
                        paint.setStrokeWidth(((float) mjpegView.f2063z) * f);
                        Point point51 = mjpegView.f2025E;
                        int i108 = point51.x;
                        if (!(i108 == 0 || (i17 = point51.y) == 0)) {
                            canvas.drawLine((float) (i108 - 5), (float) i17, (float) (i108 + 5), (float) i17, paint);
                            Point point52 = mjpegView.f2025E;
                            int i109 = point52.x;
                            int i110 = point52.y;
                            canvas.drawLine((float) i109, (float) (i110 - 5), (float) i109, (float) (i110 + 5), paint);
                        }
                        Point point53 = mjpegView.f2026F;
                        int i111 = point53.x;
                        if (!(i111 == 0 || (i16 = point53.y) == 0)) {
                            canvas.drawLine((float) (i111 - 5), (float) i16, (float) (i111 + 5), (float) i16, paint);
                            Point point54 = mjpegView.f2026F;
                            int i112 = point54.x;
                            int i113 = point54.y;
                            canvas.drawLine((float) i112, (float) (i113 - 5), (float) i112, (float) (i113 + 5), paint);
                        }
                        Point point55 = mjpegView.f2027G;
                        int i114 = point55.x;
                        if (!(i114 == 0 || (i15 = point55.y) == 0)) {
                            canvas.drawLine((float) (i114 - 5), (float) i15, (float) (i114 + 5), (float) i15, paint);
                            Point point56 = mjpegView.f2027G;
                            int i115 = point56.x;
                            int i116 = point56.y;
                            canvas.drawLine((float) i115, (float) (i116 - 5), (float) i115, (float) (i116 + 5), paint);
                        }
                        Point point57 = mjpegView.f2025E;
                        int i117 = point57.x;
                        if (!(i117 == 0 || (i8 = point57.y) == 0 || (i9 = (point3 = mjpegView.f2026F).x) == 0 || (i10 = point3.y) == 0 || (i11 = (point4 = mjpegView.f2027G).x) == 0 || (i12 = point4.y) == 0 || (i13 = (point5 = mjpegView.f2028H).x) == 0 || (i14 = point5.y) == 0)) {
                            jVar2 = new C0820j(8, mjpegView.f2058u, mjpegView.f2059v, mjpegView.f2063z, mjpegView.f2021A, mjpegView.f2061x, mjpegView.f2060w, mjpegView.f2062y);
                            jVar2.f2267a.add(new Point(i117, i8));
                            jVar2.f2267a.add(new Point(i9, i10));
                            jVar2.f2267a.add(new Point(i11, i12));
                            arrayList2 = jVar2.f2267a;
                            point6 = new Point(i13, i14);
                        }
                    } else if (i60 == 6) {
                        Point point58 = mjpegView.f2025E;
                        if (!(point58.x == 0 || point58.y == 0)) {
                            Point point59 = mjpegView.f2026F;
                            if (!(point59.x == 0 || point59.y == 0)) {
                                String str2 = mjpegView.f2023C;
                                if (str2 != null && !str2.equals("")) {
                                    Point point60 = mjpegView.f2026F;
                                    int i118 = point60.x;
                                    int i119 = point60.y;
                                    C0820j jVar4 = new C0820j(6, mjpegView.f2058u, mjpegView.f2059v, mjpegView.f2063z, mjpegView.f2021A, mjpegView.f2061x, mjpegView.f2060w, mjpegView.f2062y);
                                    jVar4.f2267a.add(new Point(i118, i119));
                                    jVar4.f2273g = mjpegView.f2023C;
                                    mjpegView.f2022B.add(jVar4);
                                }
                                mjpegView.f2023C = "";
                                Point point61 = mjpegView.f2025E;
                                i6 = 0;
                                point61.y = 0;
                                point61.x = 0;
                                point2 = mjpegView.f2026F;
                                point2.y = i6;
                                point2.x = i6;
                            }
                        }
                    } else {
                        if (i60 == 11) {
                            paint3 = paint;
                            paint3.setColor(mjpegView.f2021A);
                            paint3.setStrokeWidth(((float) mjpegView.f2063z) * f);
                            float f6 = (float) rect.left;
                            int i120 = mjpegView.f2032L;
                            paint2 = paint;
                            canvas.drawLine(f6, (float) i120, (float) rect.right, (float) i120, paint2);
                            i7 = mjpegView.f2029I;
                        } else {
                            paint3 = paint;
                            if (i60 == 12) {
                                paint3.setColor(mjpegView.f2021A);
                                paint3.setStrokeWidth(((float) mjpegView.f2063z) * f);
                                float f7 = (float) rect.left;
                                int i121 = mjpegView.f2033M;
                                paint2 = paint;
                                canvas.drawLine(f7, (float) i121, (float) rect.right, (float) i121, paint2);
                                float f8 = (float) rect.left;
                                int i122 = mjpegView.f2034N;
                                canvas.drawLine(f8, (float) i122, (float) rect.right, (float) i122, paint2);
                                int i123 = mjpegView.f2030J;
                                canvas.drawLine((float) i123, (float) rect.top, (float) i123, (float) rect.bottom, paint2);
                                i7 = mjpegView.f2031K;
                            }
                        }
                    }
                    paint3 = paint;
                }
                size = mjpegView.f2022B.size();
                while (i < size) {
                    i++;
                }
            }
            arrayList2.add(point6);
            mjpegView.f2022B.add(jVar2);
            Point point62 = mjpegView.f2025E;
            i6 = 0;
            point62.y = 0;
            point62.x = 0;
            Point point63 = mjpegView.f2026F;
            point63.y = 0;
            point63.x = 0;
            Point point64 = mjpegView.f2027G;
            point64.y = 0;
            point64.x = 0;
            point2 = mjpegView.f2028H;
            point2.y = i6;
            point2.x = i6;
            paint3 = paint;
            size = mjpegView.f2022B.size();
            while (i < size) {
            }
        }
        arrayList.add(point);
        mjpegView.f2022B.add(jVar);
        Point point242 = mjpegView.f2025E;
        point242.y = 0;
        point242.x = 0;
        Point point252 = mjpegView.f2026F;
        point252.y = 0;
        point252.x = 0;
        Point point262 = mjpegView.f2027G;
        point262.y = 0;
        point262.x = 0;
        size = mjpegView.f2022B.size();
        while (i < size) {
        }
    }

    /* renamed from: b */
    public boolean mo6053b() {
        return this.f2043f;
    }

    /* renamed from: d */
    public void mo6055d() {
        if (this.f2048k && this.f2042e != null) {
            this.f2043f = true;
            SurfaceHolder holder = getHolder();
            holder.addCallback(this);
            SaveImageThread aVar = new SaveImageThread(holder, this.context);
            this.saveImageThread = aVar;
            aVar.start();
            this.f2048k = false;
        }
    }

    /* renamed from: e */
    public void mo6056e() {
        if (this.f2042e != null) {
            this.f2043f = true;
            if (this.saveImageThread == null) {
                this.saveImageThread = new SaveImageThread(this.surfaceHolder, this.context);
            }
            this.saveImageThread.start();
        }
    }

    /* renamed from: f */
    public void mo6057f() {
        boolean z = true;
        if (this.f2043f) {
            this.f2048k = true;
        }
        this.f2043f = false;
        if (this.saveImageThread != null) {
            while (z) {
                try {
                    this.saveImageThread.join();
                    z = false;
                } catch (InterruptedException unused) {
                }
            }
            this.saveImageThread = null;
        }
        C0815f fVar = this.f2042e;
        if (fVar != null) {
            try {
                fVar.close();
            } catch (IOException unused2) {
            }
            this.f2042e = null;
        }
    }

    public void setDisplayMode(int i) {
        this.f2047j = i;
    }

    public void setSource(C0815f fVar) {
        this.f2042e = fVar;
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
