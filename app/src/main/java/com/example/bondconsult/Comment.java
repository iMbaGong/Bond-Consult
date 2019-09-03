package com.example.bondconsult;


import android.graphics.Bitmap;
import android.widget.ScrollView;

public class Comment {
    private Bitmap commentAvatar;
    private String commentName;
    private String commentContent;
    private String commentTime;

    public Comment(Bitmap commentAvatar, String commentName, String commentContent, String commentTime) {
        this.commentAvatar = commentAvatar;
        this.commentName = commentName;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
    }
    public Bitmap getCommentAvatar(){
        return commentAvatar;
    }
    public String getCommentName(){
        return commentName;
    }
    public String getCommentContent(){
        return commentContent;
    }
    public String getCommentTime(){
        return commentTime;
    }
}
