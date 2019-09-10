package com.example.bondconsult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Util {

    static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    static Bitmap byte2Bitmap(byte[] data) {
        if (data.length!=0)
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        else
            return null;
    }
}
