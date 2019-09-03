package com.example.bondconsult;

import android.graphics.Bitmap;

public class myPicture {
    private int imageId;
    private Bitmap image;

    public myPicture(Bitmap image) {
        this.image = image;
    }

    public myPicture(int imageId) {
        this.imageId = imageId;
    }
    public int getImageId()
    {
        return imageId;
    }

    public Bitmap getImage()
    {
        return image;
    }
}
