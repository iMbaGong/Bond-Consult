package com.example.bondconsult;


import android.graphics.Bitmap;

import org.json.JSONObject;

import java.util.Date;

public class Comment {
    private User user;
    private String commentContent;
    private String commentTime;


    public Comment(User suser, String commentContent, String commentTime) {
        this.user = suser;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
    }

    /*static Comment createByJson(JSONObject object){

    }*/
    public Bitmap getCommentAvatar(){
        return user.getBitmap();
    }
    public String getCommentName(){
        return user.getName();
    }
    public String getCommentContent(){
        return commentContent;
    }
    public String getCommentTime(){
        return commentTime;
    }
}
