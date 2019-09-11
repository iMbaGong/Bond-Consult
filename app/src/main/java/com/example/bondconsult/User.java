package com.example.bondconsult;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {

    static User createByJson(JSONObject jsonObject){
        User user = new User();
        try {
            user.setId(jsonObject.getInt("id"));
            user.setName(jsonObject.getString("name"));
            String strAvatar = jsonObject.getString("avatar");
            user.setAvatar(Base64.decode(strAvatar,Base64.DEFAULT));
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

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
