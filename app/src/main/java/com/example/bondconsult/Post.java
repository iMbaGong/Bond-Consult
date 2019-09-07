package com.example.bondconsult;

import android.graphics.Bitmap;

public class Post {
    private String post_title;
    private Bitmap poster_avatar;
    private String poster_name;
    private String post_text;
    private Bitmap post_image;
    private String post_time;

    public Post(Bitmap poster_avatar, String poster_name, String post_text, Bitmap post_image,String post_time,String title) {
        this.poster_avatar = poster_avatar;
        this.poster_name = poster_name;
        this.post_text = post_text;
        this.post_image = post_image;
        this.post_time=post_time;
        this.post_title = title;
    }
    public Bitmap getPoster_avatar()
    {
        return poster_avatar;
    }
    public String getPoster_name(){
        return poster_name;
    }
    public String getPost_text()
    {
        return post_text;
    }
    public Bitmap getPost_image()
    {
        return post_image;
    }
    public String getPost_time()
    {
        return post_time;
    }
    public String getPost_title() {
        return post_title;
    }
}
