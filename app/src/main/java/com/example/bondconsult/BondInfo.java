package com.example.bondconsult;


public class BondInfo {
    private String name;
    private String content;

    public BondInfo(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName(){
        return name;
    }
    public String getContent(){
        return content;
    }
}
