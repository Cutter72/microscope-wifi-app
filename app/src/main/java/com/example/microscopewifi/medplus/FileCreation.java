package com.example.microscopewifi.medplus;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCreation {

    public static void saveImage(Bitmap bitmap, String filePath, String fileName) {
        File fileDirectory = new File(filePath);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }
        try {
            if (new File(filePath + fileName + ".jpg").createNewFile()) {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName + ".jpg");
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
