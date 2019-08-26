package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class MyPostedDiscussionsPojo {
    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":"2","user_id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","image":"storage/profile_pics/5ce39ce4a6ac3.","youtube":"","status":"1","created_on":"2019-05-21 12:08:28","modified_on":null,"subject":"Physics","chapter":"1.1 Physical World","pings":"2"},{"id":"1","user_id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","image":null,"youtube":null,"status":"1","created_on":"2019-05-16 02:05:16","modified_on":null,"subject":"Physics","chapter":"1.1 Physical World","pings":"1"}]
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
         * id : 2
         * user_id : 1
         * education_id : 3
         * stream_id : 4
         * subject_id : 3
         * chapter_id : 1
         * description : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
         * image : storage/profile_pics/5ce39ce4a6ac3.
         * youtube :
         * status : 1
         * created_on : 2019-05-21 12:08:28
         * modified_on : null
         * subject : Physics
         * chapter : 1.1 Physical World
         * pings : 2
         */

        private String id;
        private String user_id;
        private String education_id;
        private String stream_id;
        private String subject_id;
        private String chapter_id;
        private String description;
        private String image;
        private String youtube;
        private String status;
        private String created_on;
        private Object modified_on;
        private String subject;
        private String chapter;
        private String pings;

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

        public String getEducation_id() {
            return education_id;
        }

        public void setEducation_id(String education_id) {
            this.education_id = education_id;
        }

        public String getStream_id() {
            return stream_id;
        }

        public void setStream_id(String stream_id) {
            this.stream_id = stream_id;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getChapter_id() {
            return chapter_id;
        }

        public void setChapter_id(String chapter_id) {
            this.chapter_id = chapter_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getYoutube() {
            return youtube;
        }

        public void setYoutube(String youtube) {
            this.youtube = youtube;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public Object getModified_on() {
            return modified_on;
        }

        public void setModified_on(Object modified_on) {
            this.modified_on = modified_on;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
        }

        public String getPings() {
            return pings;
        }

        public void setPings(String pings) {
            this.pings = pings;
        }
    }
}
