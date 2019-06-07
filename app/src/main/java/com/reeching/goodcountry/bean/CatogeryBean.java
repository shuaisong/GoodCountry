package com.reeching.goodcountry.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class CatogeryBean {
    /**
     * result : 1
     * msg : 查询成功
     * typeList : [{"id":"1","isNewRecord":false,"remarks":"","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"roleNames":"","systemAdmin":false},"createDate":"2019-01-10 14:01:56","updateDate":"2019-01-17 13:33:45","parentIds":"0,","name":"癌症","sort":30,"photo":"/diai/userfiles/1/images/shopPhoto/2019/01/%E5%AD%A6%E5%8E%86%E4%BF%A1%E6%81%AF_jpg.png","typeLevel":"一级","parid":"0","parentId":"0"},{"id":"1a7e4f0de89046b3afb24d352ad68c5b","isNewRecord":false,"remarks":"","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"roleNames":"","systemAdmin":false},"createDate":"2019-01-21 11:42:18","updateDate":"2019-01-21 11:42:18","parentIds":"0,1,","name":"基因","sort":30,"photo":"","typeLevel":"二级","parid":"1","parentId":"1"},{"id":"3de27bdb1a274211b28e1995fcbd62e5","isNewRecord":false,"remarks":"","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"roleNames":"","systemAdmin":false},"createDate":"2019-01-21 11:42:51","updateDate":"2019-01-21 11:42:51","parentIds":"0,1,1a7e4f0de89046b3afb24d352ad68c5b,","name":"基因1","sort":30,"photo":"","typeLevel":"三级","parid":"1a7e4f0de89046b3afb24d352ad68c5b","parentId":"1a7e4f0de89046b3afb24d352ad68c5b"},{"id":"69e24862ae1d4fa29fbf5e9841a391dd","isNewRecord":false,"remarks":"","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"roleNames":"","systemAdmin":false},"createDate":"2019-01-21 11:43:01","updateDate":"2019-01-21 11:43:01","parentIds":"0,1,1a7e4f0de89046b3afb24d352ad68c5b,","name":"基因2","sort":30,"photo":"","typeLevel":"三级","parid":"1a7e4f0de89046b3afb24d352ad68c5b","parentId":"1a7e4f0de89046b3afb24d352ad68c5b"},{"id":"6c3ce34af3b348b2bf6b33ba41753eb1","isNewRecord":false,"remarks":"a","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"roleNames":"","systemAdmin":false},"createDate":"2019-01-10 15:05:37","updateDate":"2019-01-15 18:29:13","parentIds":"0,1,","name":"脑癌","sort":30,"photo":"/message/userfiles/1/images/shopPhoto/2019/01/1068886.jpg","typeLevel":"二级","parid":"1","parentId":"1"},{"id":"73a208b0a98243dd922d6d6e018228a1","isNewRecord":false,"remarks":"","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"roleNames":"","systemAdmin":false},"createDate":"2019-01-21 11:42:41","updateDate":"2019-01-21 11:42:41","parentIds":"0,1,6c3ce34af3b348b2bf6b33ba41753eb1,","name":"脑癌2","sort":30,"photo":"","typeLevel":"三级","parid":"6c3ce34af3b348b2bf6b33ba41753eb1","parentId":"6c3ce34af3b348b2bf6b33ba41753eb1"},{"id":"9cc4580802ab4efdbf72b54862ae8550","isNewRecord":false,"remarks":"","createBy":{"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"roleNames":"","systemAdmin":false},"createDate":"2019-01-10 15:16:46","updateDate":"2019-01-10 15:16:46","parentIds":"0,1,6c3ce34af3b348b2bf6b33ba41753eb1,","name":"脑癌1","sort":30,"photo":"","typeLevel":"三级","parid":"6c3ce34af3b348b2bf6b33ba41753eb1","parentId":"6c3ce34af3b348b2bf6b33ba41753eb1"}]
     * nameArr : ["癌症","基因","基因1","基因2","脑癌","脑癌2","脑癌1"]
     * data : {}
     */

    private String result;
    private String msg;
    private DataBean data;
    private List<TypeListBean> typeList;
    private List<String> nameArr;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

    public List<String> getNameArr() {
        return nameArr;
    }

    public void setNameArr(List<String> nameArr) {
        this.nameArr = nameArr;
    }

    public static class DataBean {
    }

    public static class TypeListBean {
        /**
         * id : 1
         * isNewRecord : false
         * remarks :
         * createBy : {"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"roleNames":"","systemAdmin":false}
         * createDate : 2019-01-10 14:01:56
         * updateDate : 2019-01-17 13:33:45
         * parentIds : 0,
         * name : 癌症
         * sort : 30
         * photo : /diai/userfiles/1/images/shopPhoto/2019/01/%E5%AD%A6%E5%8E%86%E4%BF%A1%E6%81%AF_jpg.png
         * typeLevel : 一级
         * parid : 0
         * parentId : 0
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private CreateByBean createBy;
        private String createDate;
        private String updateDate;
        private String parentIds;
        private String name;
        private int sort;
        private String photo;
        private String typeLevel;
        private String parid;
        private String parentId;

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

        public String getParentIds() {
            return parentIds;
        }

        public void setParentIds(String parentIds) {
            this.parentIds = parentIds;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTypeLevel() {
            return typeLevel;
        }

        public void setTypeLevel(String typeLevel) {
            this.typeLevel = typeLevel;
        }

        public String getParid() {
            return parid;
        }

        public void setParid(String parid) {
            this.parid = parid;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public static class CreateByBean {
            /**
             * id : 1
             * isNewRecord : false
             * loginFlag : 1
             * admin : true
             * roleNames :
             * systemAdmin : false
             */

            private String id;
            private boolean isNewRecord;
            private String loginFlag;
            private boolean admin;
            private String roleNames;
            private boolean systemAdmin;

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

            public String getRoleNames() {
                return roleNames;
            }

            public void setRoleNames(String roleNames) {
                this.roleNames = roleNames;
            }

            public boolean isSystemAdmin() {
                return systemAdmin;
            }

            public void setSystemAdmin(boolean systemAdmin) {
                this.systemAdmin = systemAdmin;
            }
        }
    }
}
