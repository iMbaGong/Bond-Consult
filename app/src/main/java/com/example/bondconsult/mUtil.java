package com.example.bondconsult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.TimeZone;

public class mUtil {

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

    static String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return calendar.get(Calendar.YEAR)+"-"
                +calendar.get(Calendar.MONTH)+"-"
                +calendar.get(Calendar.DATE)+" "
                +calendar.get(Calendar.HOUR)+":"
                +calendar.get(Calendar.MINUTE);
    }
}
