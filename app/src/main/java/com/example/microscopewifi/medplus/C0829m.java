package com.example.microscopewifi.medplus;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: com.italeco.micfimedplus.m */
public class C0829m {
    /* renamed from: a */
    public static void m2815a(Bitmap bitmap, String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str + str2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
