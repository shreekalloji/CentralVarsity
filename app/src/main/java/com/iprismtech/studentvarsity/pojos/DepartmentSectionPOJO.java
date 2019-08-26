package com.iprismtech.studentvarsity.pojos;

import java.util.List;

/**
 * Created by intel on 23-Nov-18.
 */

public class DepartmentSectionPOJO {


    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":1,"department_id":1,"title":"Mens","a_title":"مِلك الرجال","image":"storage/img/5b7fd9a94e72e1535105449.jpeg","created_at":"2018-08-24 10:10:49","updated_at":null},{"id":2,"department_id":1,"title":"Womens","a_title":"للنساء","image":"storage/img/5b7fdb3756f791535105847.png","created_at":"2018-08-24 10:17:27","updated_at":null},{"id":3,"department_id":1,"title":"Kids","a_title":"أطفال","image":"storage/img/5b7fdbc270fa01535105986.jpg","created_at":"2018-08-24 10:19:46","updated_at":null},{"id":4,"department_id":2,"title":"Footwear","a_title":"حذاء","image":"storage/img/5b7fdca5dc0ea1535106213.jpeg","created_at":"2018-08-24 10:23:33","updated_at":null},{"id":5,"department_id":2,"title":"Wallets & Purses","a_title":"محافظ & المحافظ","image":"storage/img/5b7fdd41c33b61535106369.jpeg","created_at":"2018-08-24 10:26:09","updated_at":null},{"id":6,"department_id":2,"title":"Belts","a_title":"أحزمة","image":"storage/img/5b7fdd92639cf1535106450.jpeg","created_at":"2018-08-24 10:27:30","updated_at":null},{"id":7,"department_id":3,"title":"Rings","a_title":"خواتم","image":"storage/img/5b7fde2aabcfe1535106602.jpeg","created_at":"2018-08-24 10:30:02","updated_at":null},{"id":8,"department_id":3,"title":"Bangles","a_title":"أساور","image":"storage/img/5b7fde537bd791535106643.jpeg","created_at":"2018-08-24 10:30:43","updated_at":null}]
     */

    private boolean status;
    private String message;
    /**
     * id : 1
     * department_id : 1
     * title : Mens
     * a_title : مِلك الرجال
     * image : storage/img/5b7fd9a94e72e1535105449.jpeg
     * created_at : 2018-08-24 10:10:49
     * updated_at : null
     */

    private List<ResponseBean> response;

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

    public static class ResponseBean {
        private int id;
        private int department_id;
        private String title;
        private String a_title;
        private String image;
        private String created_at;
        private Object updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(int department_id) {
            this.department_id = department_id;
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