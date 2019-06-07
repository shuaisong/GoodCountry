package com.reeching.goodcountry.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by lenovo on 2019/1/25.
 * auther:lenovo
 * Date：2019/1/25
 */
public class Communitybean implements Serializable, MultiItemEntity {
    private int level;
    private String name;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return level;
    }
}
