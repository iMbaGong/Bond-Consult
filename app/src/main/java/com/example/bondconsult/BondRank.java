package com.example.bondconsult;

public class BondRank {
    private String rankNum;
    private String bondName;
    private String bondId;
    private String bondFigure;
    private String bondEarnRate;

    public BondRank(String rankNum, String bondName, String bondId, String bondFigure, String bondEarnRate) {
        this.rankNum = rankNum;
        this.bondName = bondName;
        this.bondId = bondId;
        this.bondFigure = bondFigure;
        this.bondEarnRate = bondEarnRate;
    }
    public String getRankNum(){
        return rankNum;
    }
    public String getBondName(){
        return bondName;
    }
    public String getBondId(){
        return bondId;
    }
    public String getBondFigure(){
        return bondFigure;
    }
    public String getBondEarnRate(){
        return bondEarnRate;
    }
}
