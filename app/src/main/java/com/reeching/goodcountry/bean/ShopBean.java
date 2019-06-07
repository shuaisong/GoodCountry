package com.reeching.goodcountry.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2019/3/5.
 * auther:lenovo
 * Date：2019/3/5
 */
public class ShopBean implements Serializable{

    /**
     * result : 1
     * msg : 请求成功
     * jsonShop : [{"id":"50d6720262e94545823c5ec528d37827","isNewRecord":false,"remarks":"代诊","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"systemAdmin":false,"roleNames":""},"createDate":"2018-11-12 17:25:00","updateDate":"2019-02-11 11:49:00","shopName":"代诊1","shopType":"2","shopRmbPrice":"20","shopDabPrice":"20","costMoney":"","shopSoldCount":"10","shopRestCount":"20","shopIntroduce":"代诊","shopShow":"1","photo":"/diai/userfiles/1/images/shopPhoto/2019/01/lake_near_mountain-1920x1080.jpg","photoDetail":"||/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145328.jpg|/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145355.jpg|/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145410.jpg|/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145404.jpg","photoArray":["/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145404.jpg","/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145410.jpg","/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145355.jpg","/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145328.jpg"],"isRegister":"","registerMoney":""},{"id":"ae611b34afa34ca188c27a6beb875458","isNewRecord":false,"remarks":"","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"systemAdmin":false,"roleNames":""},"createDate":"2019-01-22 15:31:43","updateDate":"2019-01-22 15:31:43","shopName":"201","shopType":"1","shopRmbPrice":"","shopDabPrice":"1000","costMoney":"100","shopSoldCount":"10","shopRestCount":"3","shopIntroduce":"","shopShow":"0","photo":"","photoDetail":"","hotelId":"e086bd0d6853442c8408e1f25cd5e1c2","hotelName":"酒店1","hotelPhone":"13200201101","hotelAddress":"北京市","hotelPrice":"100-500","hotelPhoto":"/diai/userfiles/1/images/diai/hotel/2019/02/IMG_20180219_150215.jpg","photoDesc":"","hotelDesc":"|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_151001.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152510.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152844.jpg","hotelDescArray":["/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152844.jpg","/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152510.jpg","/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_151001.jpg"],"mapLng":"110","mapLat":"123"},{"id":"d36f37a3d99946c0a52f886b08ef890e","isNewRecord":false,"remarks":"备注","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"systemAdmin":false,"roleNames":""},"createDate":"2018-11-12 17:17:50","updateDate":"2019-01-17 13:08:57","shopName":"酒店住宿","shopType":"1","shopRmbPrice":"111","shopDabPrice":"111","shopSoldCount":"12","shopRestCount":"24","shopIntroduce":"商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍商品介绍","shopShow":"1","photo":"/diai/userfiles/1/images/shopPhoto/2019/01/12.jpg","hotelId":"e086bd0d6853442c8408e1f25cd5e1c2","hotelName":"酒店1","hotelPhone":"13200201101","hotelAddress":"北京市","hotelPrice":"100-500","hotelPhoto":"/diai/userfiles/1/images/diai/hotel/2019/02/IMG_20180219_150215.jpg","photoDesc":"","hotelDesc":"|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_151001.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152510.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152844.jpg","hotelDescArray":["/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152844.jpg","/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152510.jpg","/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_151001.jpg"],"mapLng":"110","mapLat":"123"}]
     */

    private String result;
    private String msg;
    private List<JsonShopBean> jsonShop;

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

    public List<JsonShopBean> getJsonShop() {
        return jsonShop;
    }

    public void setJsonShop(List<JsonShopBean> jsonShop) {
        this.jsonShop = jsonShop;
    }

    public static class JsonShopBean implements Serializable {
        /**
         * id : 50d6720262e94545823c5ec528d37827
         * isNewRecord : false
         * remarks : 代诊
         * createBy : {"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"systemAdmin":false,"roleNames":""}
         * createDate : 2018-11-12 17:25:00
         * updateDate : 2019-02-11 11:49:00
         * shopName : 代诊1
         * shopType : 2
         * shopRmbPrice : 20
         * shopDabPrice : 20
         * costMoney :
         * shopSoldCount : 10
         * shopRestCount : 20
         * shopIntroduce : 代诊
         * shopShow : 1
         * photo : /diai/userfiles/1/images/shopPhoto/2019/01/lake_near_mountain-1920x1080.jpg
         * photoDetail : ||/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145328.jpg|/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145355.jpg|/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145410.jpg|/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145404.jpg
         * photoArray : ["/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145404.jpg","/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145410.jpg","/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145355.jpg","/diai/userfiles/1/images/shopPhoto/2019/02/IMG_20180818_145328.jpg"]
         * isRegister :
         * registerMoney :
         * hotelId : e086bd0d6853442c8408e1f25cd5e1c2
         * hotelName : 酒店1
         * hotelPhone : 13200201101
         * hotelAddress : 北京市
         * hotelPrice : 100-500
         * hotelPhoto : /diai/userfiles/1/images/diai/hotel/2019/02/IMG_20180219_150215.jpg
         * photoDesc :
         * hotelDesc : |/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_151001.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152510.jpg|/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152844.jpg
         * hotelDescArray : ["/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152844.jpg","/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_152510.jpg","/diai/userfiles/1/images/hotel/diaiHotel/2019/02/IMG_20180219_151001.jpg"]
         * mapLng : 110
         * mapLat : 123
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private CreateByBean createBy;
        private String createDate;
        private String updateDate;
        private String shopName;
        private String shopType;
        private String shopRmbPrice;
        private String shopDabPrice;
        private String costMoney;
        private String shopSoldCount;
        private String shopRestCount;
        private String shopIntroduce;
        private String shopShow;
        private String photo;
        private String photoDetail;
        private String isRegister;
        private String registerMoney;
        private String hotelId;
        private String hotelName;
        private String hotelPhone;
        private String hotelAddress;
        private String hotelPrice;
        private String hotelPhoto;
        private String photoDesc;
        private String hotelDesc;
        private String mapLng;
        private String mapLat;
        private List<String> photoArray;
        private List<String> hotelDescArray;

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

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getShopRmbPrice() {
            return shopRmbPrice;
        }

        public void setShopRmbPrice(String shopRmbPrice) {
            this.shopRmbPrice = shopRmbPrice;
        }

        public String getShopDabPrice() {
            return shopDabPrice;
        }

        public void setShopDabPrice(String shopDabPrice) {
            this.shopDabPrice = shopDabPrice;
        }

        public String getCostMoney() {
            return costMoney;
        }

        public void setCostMoney(String costMoney) {
            this.costMoney = costMoney;
        }

        public String getShopSoldCount() {
            return shopSoldCount;
        }

        public void setShopSoldCount(String shopSoldCount) {
            this.shopSoldCount = shopSoldCount;
        }

        public String getShopRestCount() {
            return shopRestCount;
        }

        public void setShopRestCount(String shopRestCount) {
            this.shopRestCount = shopRestCount;
        }

        public String getShopIntroduce() {
            return shopIntroduce;
        }

        public void setShopIntroduce(String shopIntroduce) {
            this.shopIntroduce = shopIntroduce;
        }

        public String getShopShow() {
            return shopShow;
        }

        public void setShopShow(String shopShow) {
            this.shopShow = shopShow;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPhotoDetail() {
            return photoDetail;
        }

        public void setPhotoDetail(String photoDetail) {
            this.photoDetail = photoDetail;
        }

        public String getIsRegister() {
            return isRegister;
        }

        public void setIsRegister(String isRegister) {
            this.isRegister = isRegister;
        }

        public String getRegisterMoney() {
            return registerMoney;
        }

        public void setRegisterMoney(String registerMoney) {
            this.registerMoney = registerMoney;
        }

        public String getHotelId() {
            return hotelId;
        }

        public void setHotelId(String hotelId) {
            this.hotelId = hotelId;
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

        public String getHotelPhoto() {
            return hotelPhoto;
        }

        public void setHotelPhoto(String hotelPhoto) {
            this.hotelPhoto = hotelPhoto;
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

        public List<String> getPhotoArray() {
            return photoArray;
        }

        public void setPhotoArray(List<String> photoArray) {
            this.photoArray = photoArray;
        }

        public List<String> getHotelDescArray() {
            return hotelDescArray;
        }

        public void setHotelDescArray(List<String> hotelDescArray) {
            this.hotelDescArray = hotelDescArray;
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
