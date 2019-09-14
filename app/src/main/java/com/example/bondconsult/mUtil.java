package com.example.bondconsult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.TimeZone;

public class mUtil {
////some data and convenient func////
    static User user;
    static boolean isInit = false;
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

    static Bitmap base64ToBitmap(String src){
        return byte2Bitmap(Base64.decode(src,Base64.DEFAULT));
    }

    static String bitmapToBase64(Bitmap src){
       return Base64.encodeToString(mUtil.bitmap2Bytes(src),Base64.DEFAULT);
    }
}
