package com.reeching.goodcountry.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2019/1/25.
 * auther:lenovo
 * Date：2019/1/25
 */
public class HotelBean implements Serializable {

    /**
     * result : 1
     * msg : 请求成功
     * jsonHot : [{"id":"e086bd0d6853442c8408e1f25cd5e1c2","isNewRecord":false,"remarks":"","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"systemAdmin":false,"roleNames":""},"createDate":"2019-01-22 15:30:10","updateDate":"2019-02-11 10:37:53","hotelName":"酒店1","hotelPhone":"13200201101","hotelAddress":"北京市","hotelPrice":"100-500","photo":"/diai/userfiles/1/images/diai/hotel/2019/02/IMG_20180219_150215.jpg","photoDesc":"","hotelDesc":"|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_151001.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152510.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152844.jpg","mapLng":"110","mapLat":"123"}]
     */

    private String result;
    private String msg;
    private List<JsonHotBean> jsonHot;

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

    public List<JsonHotBean> getJsonHot() {
        return jsonHot;
    }

    public void setJsonHot(List<JsonHotBean> jsonHot) {
        this.jsonHot = jsonHot;
    }

    public static class JsonHotBean implements Serializable {
        /**
         * id : e086bd0d6853442c8408e1f25cd5e1c2
         * isNewRecord : false
         * remarks :
         * createBy : {"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"systemAdmin":false,"roleNames":""}
         * createDate : 2019-01-22 15:30:10
         * updateDate : 2019-02-11 10:37:53
         * hotelName : 酒店1
         * hotelPhone : 13200201101
         * hotelAddress : 北京市
         * hotelPrice : 100-500
         * photo : /diai/userfiles/1/images/diai/hotel/2019/02/IMG_20180219_150215.jpg
         * photoDesc :
         * hotelDesc : |/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_151001.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152510.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152844.jpg
         * mapLng : 110
         * mapLat : 123
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private CreateByBean createBy;
        private String createDate;
        private String updateDate;
        private String hotelName;
        private String hotelPhone;
        private String hotelAddress;
        private String hotelPrice;
        private String photo;
        private String photoDesc;
        private String hotelDesc;
        private String mapLng;
        private String mapLat;

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

        public String getHotelName() {
            return hotelName;
        }

        public void setHotelName(String hotelName) {
            this.hotelName = hotelName;
        }

        public String getHotelPhone() {
            return hotelPhone;
        }

        public void setHotelPhone(String hotelPhone) {
            this.hotelPhone = hotelPhone;
        }

        public String getHotelAddress() {
            return hotelAddress;
        }

        public void setHotelAddress(String hotelAddress) {
            this.hotelAddress = hotelAddress;
        }

        public String getHotelPrice() {
            return hotelPrice;
        }

        public void setHotelPrice(String hotelPrice) {
            this.hotelPrice = hotelPrice;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPhotoDesc() {
            return photoDesc;
        }

        public void setPhotoDesc(String photoDesc) {
            this.photoDesc = photoDesc;
        }

        public String getHotelDesc() {
            return hotelDesc;
        }

        public void setHotelDesc(String hotelDesc) {
            this.hotelDesc = hotelDesc;
        }

        public String getMapLng() {
            return mapLng;
        }

        public void setMapLng(String mapLng) {
            this.mapLng = mapLng;
        }

        public String getMapLat() {
            return mapLat;
        }

        public void setMapLat(String mapLat) {
            this.mapLat = mapLat;
        }

        public static class CreateByBean implements Serializable{
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
