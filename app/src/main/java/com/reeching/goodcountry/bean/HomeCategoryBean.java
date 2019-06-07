package com.reeching.goodcountry.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2019/1/23.
 * auther:lenovo
 * Date：2019/1/23
 */
public class HomeCategoryBean implements Serializable{
    private String title;
    private Integer path;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
    }
}
