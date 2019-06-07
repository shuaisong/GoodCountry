package com.reeching.goodcountry.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2019/1/31.
 * auther:lenovo
 * Date：2019/1/31
 */
public class CouponBean implements Serializable {

    /**
     * result : 1
     * msg : 查询优惠券成功
     * couponList : [{"id":"3e2f232d051d424280be4028d7255134","isNewRecord":false,"remarks":"","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"systemAdmin":false,"roleNames":""},"createDate":"2019-01-15 10:50:07","updateDate":"2019-01-17 13:09:39","name":"被邀请","type":"1","money":"50","discount":"95","fullDec":"100","startDate":"2019-01-01 00:00:00","endDate":"2019-01-31 00:00:00","usaged":"2","couponStatus":"1","strEndDate":"2019-01-31","photo":"/diai/userfiles/1/images/diai/diaiUser/2019/01/1b4c510fd9f9d72aa186ae04d72a2834359bbbcd.jpg"}]
     */

    private String result;
    private String msg;
    private List<CouponListBean> couponList;

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

    public List<CouponListBean> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponListBean> couponList) {
        this.couponList = couponList;
    }

    public static class CouponListBean {
        /**
         * id : 3e2f232d051d424280be4028d7255134
         * isNewRecord : false
         * remarks :
         * createBy : {"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"systemAdmin":false,"roleNames":""}
         * createDate : 2019-01-15 10:50:07
         * updateDate : 2019-01-17 13:09:39
         * name : 被邀请
         * type : 1
         * money : 50
         * discount : 95
         * fullDec : 100
         * startDate : 2019-01-01 00:00:00
         * endDate : 2019-01-31 00:00:00
         * usaged : 2
         * couponStatus : 1
         * strEndDate : 2019-01-31
         * photo : /diai/userfiles/1/images/diai/diaiUser/2019/01/1b4c510fd9f9d72aa186ae04d72a2834359bbbcd.jpg
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private CreateByBean createBy;
        private String createDate;
        private String updateDate;
        private String name;
        private String type;
        private String money;
        private String discount;
        private String fullDec;
        private String startDate;
        private String endDate;
        private String usaged;
        private String couponStatus;
        private String strEndDate;
        private String photo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public CreateByBean getCreateBy() {
            return createBy;
        }

        public void setCreateBy(CreateByBean createBy) {
            this.createBy = createBy;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getFullDec() {
            return fullDec;
        }

        public void setFullDec(String fullDec) {
            this.fullDec = fullDec;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getUsaged() {
            return usaged;
        }

        public void setUsaged(String usaged) {
            this.usaged = usaged;
        }

        public String getCouponStatus() {
            return couponStatus;
        }

        public void setCouponStatus(String couponStatus) {
            this.couponStatus = couponStatus;
        }

        public String getStrEndDate() {
            return strEndDate;
        }

        public void setStrEndDate(String strEndDate) {
            this.strEndDate = strEndDate;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public static class CreateByBean {
            /**
             * id : 1
             * isNewRecord : false
             * loginFlag : 1
             * admin : true
             * systemAdmin : false
             * roleNames :
             */

            private String id;
            private boolean isNewRecord;
            private String loginFlag;
            private boolean admin;
            private boolean systemAdmin;
            private String roleNames;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }

            public boolean isSystemAdmin() {
                return systemAdmin;
            }

            public void setSystemAdmin(boolean systemAdmin) {
                this.systemAdmin = systemAdmin;
            }

            public String getRoleNames() {
                return roleNames;
            }

            public void setRoleNames(String roleNames) {
                this.roleNames = roleNames;
            }
        }
    }
}
