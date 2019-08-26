package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class EducationPOJO {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"5","title":"Architecture","created_on":"2019-04-24 03:04:08","modified_on":null},{"id":"3","title":"Engineering","created_on":"2019-04-24 03:05:12","modified_on":null},{"id":"1","title":"High School","created_on":"2019-04-24 03:05:12","modified_on":null},{"id":"2","title":"Intermediate (11th or 12th)","created_on":"2019-04-24 03:04:08","modified_on":null},{"id":"4","title":"Medicine","created_on":"2019-04-24 03:04:08","modified_on":null}]
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
         * id : 5
         * title : Architecture
         * created_on : 2019-04-24 03:04:08
         * modified_on : null
         */

        private String id;
        private String title;

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
    }
}
