package com.reeching.goodcountry.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class BannerBean implements Serializable {

    /**
     * code : 0
     * msg : success
     * data : [{"src":" /diai/userfiles / 1 / images / diai / banner / 2019 / 02 / 0020032811404538 _b.jpg"},{"src":"/diai/userfiles / 1 / images / diai / banner / 2019 / 02 / 0034034409450565 _b.jpg"},{"src":"/diai/userfiles / 1 / images / diai / banner / 2019 / 02 / 13179846 _1345796441966.jpg "},{"src":"/diai/userfiles / 1 / images / diai / banner / 2019 / 02 / 84. jpg"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * src :  /diai/userfiles / 1 / images / diai / banner / 2019 / 02 / 0020032811404538 _b.jpg
         */

        private String src;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
}
