package com.reeching.goodcountry.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2019/2/14.
 * auther:lenovo
 * Date：2019/2/14
 */
public class InOutBean implements Serializable {
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    private String type;
    private long money;
    private String date;



    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
