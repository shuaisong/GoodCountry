package com.reeching.goodcountry.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2019/2/14.
 * auther:lenovo
 * Date：2019/2/14
 */
public class CommentBean implements Serializable {
    private String head;
    private String date;
    private String content;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Comment2> mComment2s;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment2> getComment2s() {
        return mComment2s;
    }

    public void setComment2s(List<Comment2> comment2s) {
        mComment2s = comment2s;
    }

    public static class Comment2 {
        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getDate() {
            return date;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private String head;
        private String date;
        private String content;
    }
}
