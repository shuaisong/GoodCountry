package com.reeching.goodcountry.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2019/3/6.
 * auther:lenovo
 * Date：2019/3/6
 */
public class ReleaseArticle implements Serializable {

    /**
     * result : 1
     * msg : 发表成功
     */

    private int result;
    private String msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
