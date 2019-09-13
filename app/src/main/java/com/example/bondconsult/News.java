package com.example.bondconsult;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

public class News extends DataSupport {


    static public News createIntanceByJson(JSONObject src){
        News news = new News();
        try{
            news.setTitle(src.getString("title"));
            news.setTime(src.getString("time"));
            news.setLink(src.getString("link"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }
    /*public String string(){
        return
    }*/
    private String title;
    private String time;
    private String link;

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
    public String getTime(){return time;}
    public void setTime(String time){this.time=time;}
    public String getLink(){return link;}
    public void setLink(String link){this.link=link;}

}
