package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class SearchUniversityPOJO {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"university":"Geetham College & University"}]
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
         * university : Geetham College & University
         */

        private String university;

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }
    }
}
