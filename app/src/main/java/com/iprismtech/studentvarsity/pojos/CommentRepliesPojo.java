package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class CommentRepliesPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"name":"Jhon Doe","mobile":"9999999999","university":"Geetham College & University","profile_pic":"storage/profile_pics/5ceba75270652.jpeg","id":"2","user_id":"1","sections_id":"2","comment_id":"1","comment":"Reply to comment","image":null,"type":"notes","created_on":"2019-05-28 12:49:02","posted_on":"Just Now"},{"name":"Jhon Doe","mobile":"9999999999","university":"Geetham College & University","profile_pic":"storage/profile_pics/5ceba75270652.jpeg","id":"3","user_id":"1","sections_id":"2","comment_id":"1","comment":"Reply to comment","image":null,"type":"notes","created_on":"2019-05-28 12:50:39","posted_on":"Just Now"}]
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
         * name : Jhon Doe
         * mobile : 9999999999
         * university : Geetham College & University
         * profile_pic : storage/profile_pics/5ceba75270652.jpeg
         * id : 2
         * user_id : 1
         * sections_id : 2
         * comment_id : 1
         * comment : Reply to comment
         * image : null
         * type : notes
         * created_on : 2019-05-28 12:49:02
         * posted_on : Just Now
         */

        private String name;
        private String mobile;
        private String university;
        private String profile_pic;
        private String id;
        private String user_id;
        private String sections_id;
        private String comment_id;
        private String comment;
        private Object image;
        private String type;
        private String created_on;
        private String posted_on;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSections_id() {
            return sections_id;
        }

        public void setSections_id(String sections_id) {
            this.sections_id = sections_id;
        }

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getPosted_on() {
            return posted_on;
        }

        public void setPosted_on(String posted_on) {
            this.posted_on = posted_on;
        }
    }
}
