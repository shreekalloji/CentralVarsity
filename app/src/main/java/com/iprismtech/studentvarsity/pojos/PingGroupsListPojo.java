package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class PingGroupsListPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":"1","title":"Centralvarsity","user_id":"1","color_code":"3259ee","created_on":"2019-05-09 14:45:52","group_members":"3"}]
     */

    private boolean status;
    private String message;
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
        /**
         * id : 1
         * title : Centralvarsity
         * user_id : 1
         * color_code : 3259ee
         * created_on : 2019-05-09 14:45:52
         * group_members : 3
         */

        private String id;
        private String title;
        private String user_id;
        private String color_code;
        private String created_on;
        private String group_members;
        public boolean isClicked = false;

        public boolean isClicked() {
            return isClicked;
        }

        public void setClicked(boolean clicked) {
            isClicked = clicked;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getColor_code() {
            return color_code;
        }

        public void setColor_code(String color_code) {
            this.color_code = color_code;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getGroup_members() {
            return group_members;
        }

        public void setGroup_members(String group_members) {
            this.group_members = group_members;
        }
    }
}
