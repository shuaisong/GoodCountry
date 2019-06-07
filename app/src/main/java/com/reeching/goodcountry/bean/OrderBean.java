package com.reeching.goodcountry.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2019/1/30.
 * auther:lenovo
 * Date：2019/1/30
 */
public class OrderBean implements Serializable {
    private int type;//0:待付款  1：待发货 2：待评价

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    private List<GoodBean> good;

    public List<GoodBean> getGood() {
        return good;
    }

    public void setGood(List<GoodBean> good) {
        this.good = good;
    }



    public static class GoodBean  {
        /**
         * name : 22
         * price : 2
         * num : 3
         * img : WWW
         */

        private String name;
        private int price;
        private int num;
        private String img;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }


    }
}
