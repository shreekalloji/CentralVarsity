package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class MyPostedSuggestionsPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":"3","user_id":"1","suggestion_type":"I am looking for good college","exam_type":"Regular","exam_name":"NEET","confidence":"Good","country":"India","suggestion":"Nice app","created_on":"2019-05-16 15:24:48"}]
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
         * id : 3
         * user_id : 1
         * suggestion_type : I am looking for good college
         * exam_type : Regular
         * exam_name : NEET
         * confidence : Good
         * country : India
         * suggestion : Nice app
         * created_on : 2019-05-16 15:24:48
         */

        private String id;
        private String user_id;
        private String suggestion_type;
        private String exam_type;
        private String exam_name;
        private String confidence;
        private String country;
        private String suggestion;
        private String created_on;

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

        public String getSuggestion_type() {
            return suggestion_type;
        }

        public void setSuggestion_type(String suggestion_type) {
            this.suggestion_type = suggestion_type;
        }

        public String getExam_type() {
            return exam_type;
        }

        public void setExam_type(String exam_type) {
            this.exam_type = exam_type;
        }

        public String getExam_name() {
            return exam_name;
        }

        public void setExam_name(String exam_name) {
            this.exam_name = exam_name;
        }

        public String getConfidence() {
            return confidence;
        }

        public void setConfidence(String confidence) {
            this.confidence = confidence;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(String suggestion) {
            this.suggestion = suggestion;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }
}
