package com.example.bondconsult;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;



public class Bond extends DataSupport {

    public Bond(JSONObject src){
        try{
            id = src.getString("序号");
            code = src.getString("债券代码");
            nameAbbr = src.getString("债券简称");
            name = src.getString("债券全称");
            pledgeAbbr = src.getString("质押券简称");
            pledgeCode = src.getString("质押券代码");
            term = src.getString("期限(年)");
            intBear = src.getString("计息方式");
            intPay = src.getString("付息方式");
            circulation = src.getString("发行量(亿元)");
            listDate = src.getString("上市日期");
            ddl = src.getString("到期日");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*public String string(){
        return
    }*/
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
