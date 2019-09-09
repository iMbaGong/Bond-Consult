package com.example.bondconsult;

import android.graphics.Bitmap;

public class User {

    private int id;
    private String name;
    private Bitmap avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }
}
