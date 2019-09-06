package com.example.bondconsult;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;



public class Bond extends DataSupport {


    static public Bond createIntanceByJson(JSONObject src){
        Bond bond = new Bond();
        try{
            //id = src.getInt("序号");//Integer.parseInt(src.getString("序号"));
            bond.setCode(src.getString("债券代码"));
            bond.setNameAbbr(src.getString("债券简称"));
            bond.setName(src.getString("债券全称"));
            bond.setPledgeAbbr(src.getString("质押券简称"));
            bond.setPledgeCode(src.getString("质押券代码"));
            bond.setTerm(src.getString("期限(年)"));
            bond.setIntBear(src.getString("计息方式"));
            bond.setIntPay(src.getString("付息方式"));
            bond.setCirculation(src.getString("发行量(亿元)"));
            bond.setListDate(src.getString("上市日期"));
            bond.setDdl(src.getString("到期日"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return bond;
    }
    /*public String string(){
        return
    }*/
    private int id;
    private String code;
    private String nameAbbr;
    private String name;
    private String pledgeAbbr;
    private String pledgeCode;
    private String term;
    private String intBear;
    private String intPay;
    private String circulation;
    private String listDate;
    private String ddl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPledgeAbbr() {
        return pledgeAbbr;
    }

    public void setPledgeAbbr(String pledgeAbbr) {
        this.pledgeAbbr = pledgeAbbr;
    }

    public String getPledgeCode() {
        return pledgeCode;
    }

    public void setPledgeCode(String pledgeCode) {
        this.pledgeCode = pledgeCode;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getIntBear() {
        return intBear;
    }

    public void setIntBear(String intBear) {
        this.intBear = intBear;
    }

    public String getIntPay() {
        return intPay;
    }

    public void setIntPay(String intPay) {
        this.intPay = intPay;
    }

    public String getCirculation() {
        return circulation;
    }

    public void setCirculation(String circulation) {
        this.circulation = circulation;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }




}
