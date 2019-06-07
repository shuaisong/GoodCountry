package com.reeching.goodcountry.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2019/1/29.
 * auther:lenovo
 * Date：2019/1/29
 */
public class CaseBean implements Serializable {
    private String path;
    private String name;
    private String sex;
    private String disease;
    private int age;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
