package com.reeching.goodcountry.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2019/1/24.
 * auther:lenovo
 * Date：2019/1/24
 */
public class ArticleBean implements Serializable {

    /**
     * result : 1
     * msg : 查询成功
     * aritleList : [{"publishName":"11234","picArray":[],"isReward":"","creatDate":"2019-01-03 19:00:46","userId":"0eb91d251e5e4c1da9a17549d8687d61","content":"文章返回文章返回文章返回文章返回","wzType":"疾病知识","txlj":"/message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.XSu38NliIbKR98c055cb82b26baaf306102fc45aca79.jpg","wzWt":"1","tile":"文章返回","aritleInfo":{"rewardCount":"0","praiseCount":"0","collection":"0","commentCount":"1"},"id":"1db24197ebf049bb9c8c2f8fc7d82f23","jbType":""},{"publishName":"11234","picArray":["message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.7GLqgvF9GKVD51ff636379bd51006fc8f4c0fc7de3fe.jpg"],"isReward":"","creatDate":"2019-01-18 14:10:00","userId":"0eb91d251e5e4c1da9a17549d8687d61","content":"演示","wzType":"病友经验","txlj":"/message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.XSu38NliIbKR98c055cb82b26baaf306102fc45aca79.jpg","wzWt":"1","tile":"演示","aritleInfo":{"rewardCount":"0","praiseCount":"2","collection":"1","commentCount":"1"},"id":"2c1a3583965742de9158c4fd3f8b9f36","jbType":"脑癌"},{"publishName":"11234","picArray":[],"isReward":"","creatDate":"2018-11-30 14:54:02","userId":"0eb91d251e5e4c1da9a17549d8687d61","content":"文章内容1文章内容1文章内容1文章内容1文章内容1","wzType":"病友经验","txlj":"/message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.XSu38NliIbKR98c055cb82b26baaf306102fc45aca79.jpg","wzWt":"1","tile":"文章1","aritleInfo":{"rewardCount":"0","praiseCount":"0","collection":"0","commentCount":"0"},"id":"4743f86c9a424abfb8a040b2a6c5ac99","jbType":""},{"publishName":"张三","picArray":["/message/timg.jpg","/message/t.jpg"],"isReward":"","creatDate":"2018-11-30 18:25:05","userId":"7ebdfb725a104ea290349ce7ab560e27","content":"测试内容测试内容测试内容测试内容测试内容测试内容","wzType":"疾病知识","txlj":"","wzWt":"1","tile":"测试5","aritleInfo":{"rewardCount":"22","praiseCount":"9","collection":"11","commentCount":"110"},"id":"611f8dfd11fd45efa4bc936758071558","jbType":"脑癌"},{"publishName":"11234","picArray":["message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.mbLMrl7w4upK9865a8b1003979e7c7f6d544822c155e.jpg","/message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.WadDBFW5Rpifdf59051ad5ce9563b859ad8002cc5edf.jpg","/message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.2FBuS8Se01l951ff636379bd51006fc8f4c0fc7de3fe.jpg"],"isReward":"","creatDate":"2019-01-17 11:38:40","userId":"0eb91d251e5e4c1da9a17549d8687d61","content":"图片数组图片数组图片数组图片数组","wzType":"病友经验","txlj":"/message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.XSu38NliIbKR98c055cb82b26baaf306102fc45aca79.jpg","wzWt":"1","tile":"图片数组","aritleInfo":{"rewardCount":"0","praiseCount":"3","collection":"1","commentCount":"1"},"id":"6c18ff70cfbc45289a5b5e4d98573a2f","jbType":"脑癌"},{"publishName":"11234","picArray":[],"isReward":"","creatDate":"2019-01-15 17:50:59","userId":"0eb91d251e5e4c1da9a17549d8687d61","content":"测试类型测试类型测试类型测试类型测试类型测试类型","wzType":"","txlj":"/message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.XSu38NliIbKR98c055cb82b26baaf306102fc45aca79.jpg","wzWt":"1","tile":"测试类型","aritleInfo":{"rewardCount":"0","praiseCount":"10","collection":"1","commentCount":"6"},"id":"87b8e843ea7443248edc7b121943ae95","jbType":""},{"publishName":"11234","picArray":[],"isReward":"","creatDate":"2019-01-21 18:12:54","userId":"0eb91d251e5e4c1da9a17549d8687d61","content":"测试疾病二级测试疾病二级测试疾病二级测试疾病二级测试疾病二级","wzType":"病友经验","txlj":"/message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.XSu38NliIbKR98c055cb82b26baaf306102fc45aca79.jpg","wzWt":"1","tile":"测试疾病二级","aritleInfo":{"rewardCount":"70","praiseCount":"0","collection":"2","commentCount":"0"},"id":"cc28f799363c418eba58ac9c79d61353","jbType":"脑癌"}]
     */

    private String result;
    private String msg;
    private List<AritleListBean> aritleList;

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

    public List<AritleListBean> getAritleList() {
        return aritleList;
    }

    public void setAritleList(List<AritleListBean> aritleList) {
        this.aritleList = aritleList;
    }

    public static class AritleListBean {
        /**
         * publishName : 11234
         * picArray : []
         * isReward :
         * creatDate : 2019-01-03 19:00:46
         * userId : 0eb91d251e5e4c1da9a17549d8687d61
         * content : 文章返回文章返回文章返回文章返回
         * wzType : 疾病知识
         * txlj : /message/wx6d077f932e8ce73d.o6zAJs4AYJE5W5qLIha3Dya8zE4s.XSu38NliIbKR98c055cb82b26baaf306102fc45aca79.jpg
         * wzWt : 1
         * tile : 文章返回
         * aritleInfo : {"rewardCount":"0","praiseCount":"0","collection":"0","commentCount":"1"}
         * id : 1db24197ebf049bb9c8c2f8fc7d82f23
         * jbType :
         */

        private String publishName;
        private String isReward;
        private String creatDate;
        private String userId;
        private String content;
        private String wzType;
        private String txlj;
        private String wzWt;
        private String tile;
        private AritleInfoBean aritleInfo;
        private String id;
        private String jbType;
        private List<?> picArray;

        public String getPublishName() {
            return publishName;
        }

        public void setPublishName(String publishName) {
            this.publishName = publishName;
        }

        public String getIsReward() {
            return isReward;
        }

        public void setIsReward(String isReward) {
            this.isReward = isReward;
        }

        public String getCreatDate() {
            return creatDate;
        }

        public void setCreatDate(String creatDate) {
            this.creatDate = creatDate;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getWzType() {
            return wzType;
        }

        public void setWzType(String wzType) {
            this.wzType = wzType;
        }

        public String getTxlj() {
            return txlj;
        }

        public void setTxlj(String txlj) {
            this.txlj = txlj;
        }

        public String getWzWt() {
            return wzWt;
        }

        public void setWzWt(String wzWt) {
            this.wzWt = wzWt;
        }

        public String getTile() {
            return tile;
        }

        public void setTile(String tile) {
            this.tile = tile;
        }

        public AritleInfoBean getAritleInfo() {
            return aritleInfo;
        }

        public void setAritleInfo(AritleInfoBean aritleInfo) {
            this.aritleInfo = aritleInfo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJbType() {
            return jbType;
        }

        public void setJbType(String jbType) {
            this.jbType = jbType;
        }

        public List<?> getPicArray() {
            return picArray;
        }

        public void setPicArray(List<?> picArray) {
            this.picArray = picArray;
        }

        public static class AritleInfoBean {
            /**
             * rewardCount : 0
             * praiseCount : 0
             * collection : 0
             * commentCount : 1
             */

            private String rewardCount;
            private String praiseCount;
            private String collection;
            private String commentCount;

            public String getRewardCount() {
                return rewardCount;
            }

            public void setRewardCount(String rewardCount) {
                this.rewardCount = rewardCount;
            }

            public String getPraiseCount() {
                return praiseCount;
            }

            public void setPraiseCount(String praiseCount) {
                this.praiseCount = praiseCount;
            }

            public String getCollection() {
                return collection;
            }

            public void setCollection(String collection) {
                this.collection = collection;
            }

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }
        }
    }
}
