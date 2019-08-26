package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class StreamsPOJO {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"4","education_id":"3","title":"Civil","created_on":"2019-04-24 01:06:02","modified_on":null},{"id":"1","education_id":"3","title":"Computer Science (CSE)","created_on":"2019-04-24 03:04:12","modified_on":null},{"id":"3","education_id":"3","title":"Electronics & Communication (ECE)","created_on":"2019-04-24 03:04:12","modified_on":null},{"id":"5","education_id":"3","title":"Information Technology (IT)","created_on":"2019-04-24 03:04:12","modified_on":null},{"id":"2","education_id":"3","title":"Mechanical (MECH)","created_on":"2019-04-24 01:06:02","modified_on":null}]
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
         * id : 4
         * education_id : 3
         * title : Civil
         * created_on : 2019-04-24 01:06:02
         * modified_on : null
         */

        private String id;
        private String education_id;
        private String title;
        private String created_on;
        private Object modified_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEducation_id() {
            return education_id;
        }

        public void setEducation_id(String education_id) {
            this.education_id = education_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
    }
}
