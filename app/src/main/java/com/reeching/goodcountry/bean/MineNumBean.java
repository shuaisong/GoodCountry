package com.reeching.goodcountry.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2019/3/6.
 * auther:lenovo
 * Date：2019/3/6
 */
public class MineNumBean implements Serializable {

    /**
     * result : 1
     * msg : 查询成功
     * caseSize : 7
     * orderSize : 2
     */

    private String result;
    private String msg;
    private int caseSize;
    private int orderSize;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCaseSize() {
        return caseSize;
    }

    public void setCaseSize(int caseSize) {
        this.caseSize = caseSize;
    }

    public int getOrderSize() {
        return orderSize;
    }

    public void setOrderSize(int orderSize) {
        this.orderSize = orderSize;
    }
}
