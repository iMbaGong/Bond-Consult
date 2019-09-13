package com.example.bondconsult;


import android.graphics.Bitmap;

import java.util.Date;

public class Comment {
    private User user;
    private String commentContent;
    private Date commentTime;

    public Comment(User suser, String commentContent, Date commentTime) {
        this.user = suser;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
    }
    public Bitmap getCommentAvatar(){
        return mUtil.byte2Bitmap(user.getAvatar());
    }
    public String getCommentName(){
        return user.getName();
    }
    public String getCommentContent(){
        return commentContent;
    }
    public Date getCommentTime(){
        return commentTime;
    }
}
