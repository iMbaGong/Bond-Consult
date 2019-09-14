package com.example.bondconsult;

import android.graphics.Bitmap;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;


public class Post implements Serializable {
    private String post_title;
    private String post_time;
    private String post_text;
    private User poster;
    private int thumbUp;
    private int commNum;
    private List<Comment> commentList;

    public Post(String title, String text, String time
            ,String user,int tUp,int comment_num) {
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
        this.commNum = comment_num;

    }
    public Post(String title,String text , String time
            ,String user,int tUp,int comment_num,String comments) {
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
        this.commNum = comment_num;
        try {
            if(comment_num>1){
                /*JSONArray array = new JSONArray(comments);
                for(int i=0;i<array.length();i++){
                    Comment comment = array.getJSONObject(i);
                }*/
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public Post(String title, String text, String time
            ,User user,int tUp,int comment_num) {
        this.post_title = title;
        this.post_time = time;
        this.post_text = text;
        this.poster = user;
        this.thumbUp = tUp;
        this.commNum = comment_num;

    }
    public Bitmap getPoster_avatar()
    {
        return poster.getBitmap();
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

    public void addThumbUp(int thumbUp) {
        this.thumbUp += thumbUp;
    }

    public int getCommNum() {
        return commNum;
    }

    public void addCommNum(int commNum) {
        this.commNum += commNum;
    }

    public void setThumbUp(int thumbUp) {
        this.thumbUp = thumbUp;
    }

    public void setCommNum(int commNum) {
        this.commNum = commNum;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
