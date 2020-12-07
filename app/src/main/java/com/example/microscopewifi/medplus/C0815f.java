package com.example.microscopewifi.medplus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* renamed from: com.italeco.micfimedplus.f */
public class C0815f extends DataInputStream {

    /* renamed from: b */
    private final byte[] f2245b = {-1, -40};

    /* renamed from: c */
    private final byte[] f2246c = {-1, -39};

    /* renamed from: d */
    private int f2247d = -1;

    /* renamed from: e */
    byte[] f2248e = null;

    /* renamed from: f */
    byte[] f2249f = null;

    /* renamed from: g */
    final int f2250g = -1;

    /* renamed from: h */
    int f2251h = -1;

    /* renamed from: i */
    int f2252i = 0;

    public C0815f(InputStream inputStream) {
        super(new BufferedInputStream(inputStream, 40100));
    }

    /* renamed from: a */
    private int m2774a(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        int i = 0;
        for (int i2 = 0; i2 < 40100; i2++) {
            if (((byte) dataInputStream.readUnsignedByte()) == bArr[i]) {
                i++;
                if (i == bArr.length) {
                    return i2 + 1;
                }
            } else {
                i = 0;
            }
        }
        return -1;
    }

    /* renamed from: a */
    private int m2775a(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Properties properties = new Properties();
        properties.load(byteArrayInputStream);
        return Integer.parseInt(properties.getProperty("Content-Length"));
    }

    /* renamed from: b */
    private int m2776b(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        int i = this.f2247d;
        int i2 = i / 2;
        int i3 = (i * 3) / 2;
        skipBytes(this.f2250g + i2);
        int i4 = 0;
        for (int i5 = 0; i5 < i3 - i2; i5++) {
            if (((byte) dataInputStream.readUnsignedByte()) == bArr[i4]) {
                i4++;
                if (i4 == bArr.length) {
                    return this.f2250g + i2 + i5 + 1;
                }
            } else {
                i4 = 0;
            }
        }
        return -1;
    }

    /* renamed from: c */
    private int m2777c(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        int a = m2774a(dataInputStream, bArr);
        if (a < 0) {
            return -1;
        }
        return a - bArr.length;
    }

    /* renamed from: a */
    public Bitmap mo6140a() throws IOException {
        int i;
        mark(40100);
        try {
            int c = m2777c(this, this.f2245b);
            reset();
            if (this.f2248e == null || c != this.f2251h) {
                this.f2248e = new byte[c];
            }
            this.f2251h = c;
            readFully(this.f2248e);
            try {
                i = m2775a(this.f2248e);
            } catch (IllegalArgumentException unused) {
                i = m2776b(this, this.f2246c);
                if (i < 0) {
                    reset();
                    i = m2774a(this, this.f2246c);
                }
            } catch (IOException unused3) {
                reset();
                return null;
            }
            this.f2247d = i;
            reset();
            if (this.f2249f == null) {
                this.f2249f = new byte[40100];
            }
            int i2 = this.f2247d;
            if (i2 + 100 > 40100) {
                this.f2249f = new byte[(i2 + 100)];
            }
            skipBytes(c);
            readFully(this.f2249f, 0, this.f2247d);
            if (this.f2252i >= 100) {
                this.f2252i = 0;
            }
            return BitmapFactory.decodeStream(new ByteArrayInputStream(this.f2249f, 0, this.f2247d));
        } catch (IOException unused4) {
            reset();
            return null;
        }
    }
}
