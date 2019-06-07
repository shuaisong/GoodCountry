package com.reeching.goodcountry.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2019/2/19.
 * auther:lenovo
 * Date：2019/2/19
 */
public class ShopCarBean implements Serializable {
    private String path;
    private String name;
    private int num;
    private int money;
    private int coin;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
