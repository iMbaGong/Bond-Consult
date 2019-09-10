package com.example.bondconsult;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name;
    private byte[] avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
