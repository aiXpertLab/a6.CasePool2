package com.iwangzhe.mvpchart.model;

/**
 * 类：ChartDataInfo
 * 作者： qxc
 * 日期：2018/4/18.
 */
public class ChartDataInfo {
    private String date;
    private int num;

    public ChartDataInfo(String date, int num) {
        this.date = date;
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
