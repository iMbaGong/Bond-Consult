package com.example.bondconsult;


import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name;
    private Drawable avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
