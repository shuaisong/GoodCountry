package com.reeching.goodcountry.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2019/3/4.
 * auther:lenovo
 * Date：2019/3/4
 */
public class TwoCategoryBean implements Serializable {

    private List<ObjectMultiArrayBean> objectMultiArray;
    private List<String> listTagId;
    private List<List<String>> multiArray;

    public List<ObjectMultiArrayBean> getObjectMultiArray() {
        return objectMultiArray;
    }

    public void setObjectMultiArray(List<ObjectMultiArrayBean> objectMultiArray) {
        this.objectMultiArray = objectMultiArray;
    }

    public List<String> getListTagId() {
        return listTagId;
    }

    public void setListTagId(List<String> listTagId) {
        this.listTagId = listTagId;
    }

    public List<List<String>> getMultiArray() {
        return multiArray;
    }

    public void setMultiArray(List<List<String>> multiArray) {
        this.multiArray = multiArray;
    }

    public static class ObjectMultiArrayBean implements Serializable {
        /**
         * id : 1a7e4f0de89046b3afb24d352ad68c5b
         * isNewRecord : false
         * remarks :
         * createBy : {"id":"1","isNewRecord":false,"loginFlag":"1","admin":true,"systemAdmin":false,"roleNames":""}
         * createDate : 2019-01-21 11:42:18
         * updateDate : 2019-01-21 11:42:18
         * parentIds : 0,1,
         * name : 基因
         * sort : 30
         * photo :
         * typeLevel : 二级
         * parid : 1
         * parentId : 1
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
