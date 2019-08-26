package com.iprismtech.studentvarsity.pojos;

import java.util.List;

/**
 * Created by intel on 22-Nov-18.
 */

public class DepartmentPOJO {


    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":1,"title":"Clothing","a_title":"ملابس","icon":"storage/img/5b7fd6c9693f81535104713.png","created_at":"2018-08-24 04:28:33","updated_at":null},{"id":2,"title":"Leather","a_title":"جلد","icon":"storage/img/5b7fd74d8764c1535104845.png","created_at":"2018-08-24 04:30:45","updated_at":null},{"id":3,"title":"Jewellery","a_title":"مجوهرات","icon":"storage/img/5b7fd788a308a1535104904.png","created_at":"2018-08-24 04:31:44","updated_at":null},{"id":4,"title":"Electronics","a_title":"إلكترونيات","icon":"storage/img/5b8e33855e19a1536045957.png","created_at":"2018-08-30 07:41:28","updated_at":"2018-09-04 01:55:57"},{"id":5,"title":"Home Needs","a_title":"احتياجات المنزل","icon":"storage/img/5b8e34564c3141536046166.png","created_at":"2018-09-04 01:59:26","updated_at":null},{"id":6,"title":"Acessories","a_title":"الصوتيات","icon":"storage/img/5b8e34879ab041536046215.png","created_at":"2018-09-04 02:00:15","updated_at":null}]
     * banners : [{"id":1,"image":"storage/img/5a7d7193b54f91518170515.jpg","created_at":"2018-02-09 10:01:55","updated_at":null},{"id":3,"image":"storage/img/5a7d721a27b6b1518170650.jpg","created_at":"2018-02-09 10:04:10","updated_at":null},{"id":4,"image":"storage/img/5b7facc61b9e11535093958.jpg","created_at":"2018-08-24 06:59:18","updated_at":null}]
     */

    private boolean status;
    private String message;
    /**
     * id : 1
     * title : Clothing
     * a_title : ملابس
     * icon : storage/img/5b7fd6c9693f81535104713.png
     * created_at : 2018-08-24 04:28:33
     * updated_at : null
     */

    private List<ResponseBean> response;
    /**
     * id : 1
     * image : storage/img/5a7d7193b54f91518170515.jpg
     * created_at : 2018-02-09 10:01:55
     * updated_at : null
     */

    private List<BannersBean> banners;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class ResponseBean {
        private int id;
        private String title;
        private String a_title;
        private String icon;
        private String created_at;
        private Object updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getA_title() {
            return a_title;
        }

        public void setA_title(String a_title) {
            this.a_title = a_title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }

    public static class BannersBean {
        private int id;
        private String image;
        private String created_at;
        private Object updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }
}
