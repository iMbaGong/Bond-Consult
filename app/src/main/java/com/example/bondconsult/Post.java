package com.example.bondconsult;

import android.graphics.Bitmap;


import org.json.JSONObject;

import java.io.Serializable;


public class Post implements Serializable {
    private String post_title;
    private String post_time;
    private String post_text;
    private User poster;
    private int thumbUp;
    private int commNum;

    public Post(String title, String time, String text
            ,String user,int tUp,int comms) {
        this.post_title = title;
        this.post_time = time;
        this.post_text = text;
        try{
            JSONObject object = new JSONObject(user);
            this.poster = User.createByJson(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.thumbUp = tUp;
        this.commNum = comms;

    }
    public Post(String title, String time, String text
            ,User user,int tUp,int comms) {
        this.post_title = title;
        this.post_time = time;
        this.post_text = text;
        this.poster = user;
        this.thumbUp = tUp;
        this.commNum = comms;

    }
    public Bitmap getPoster_avatar()
    {
        return mUtil.byte2Bitmap(poster.getAvatar());
    }
    public String getPoster_name(){
        return poster.getName();
    }
    public String getPost_text()
    {
        return post_text;
    }

    public String getPost_time()
    {
        return post_time;
    }
    public String getPost_title() {
        return post_title;
    }


    public int getThumbUp() {
        return thumbUp;
    }

    public void setThumbUp(int thumbUp) {
        this.thumbUp = thumbUp;
    }

    public int getCommNum() {
        return commNum;
    }

    public void setCommNum(int commNum) {
        this.commNum = commNum;
    }
}
