package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class SubjectsPOJO {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"semester":"1","subjetcs":[{"id":"6","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Engineering Graphics","created_on":"2019-04-25 02:10:02","modified_on":null},{"id":"5","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Computer Programming","created_on":"2019-04-25 02:04:01","modified_on":null},{"id":"4","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Environmental Studies","created_on":"2019-04-25 02:10:02","modified_on":null},{"id":"3","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Physics","created_on":"2019-04-25 02:04:01","modified_on":null},{"id":"2","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Chemistry","created_on":"2019-04-25 02:10:02","modified_on":null},{"id":"1","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Calculus and Diffrential Equations","created_on":"2019-04-25 02:04:01","modified_on":null}]}]
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
         * semester : 1
         * subjetcs : [{"id":"6","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Engineering Graphics","created_on":"2019-04-25 02:10:02","modified_on":null},{"id":"5","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Computer Programming","created_on":"2019-04-25 02:04:01","modified_on":null},{"id":"4","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Environmental Studies","created_on":"2019-04-25 02:10:02","modified_on":null},{"id":"3","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Physics","created_on":"2019-04-25 02:04:01","modified_on":null},{"id":"2","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Chemistry","created_on":"2019-04-25 02:10:02","modified_on":null},{"id":"1","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Calculus and Diffrential Equations","created_on":"2019-04-25 02:04:01","modified_on":null}]
         */

        private String semester;
        private List<SubjetcsBean> subjetcs;

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public List<SubjetcsBean> getSubjetcs() {
            return subjetcs;
        }

        public void setSubjetcs(List<SubjetcsBean> subjetcs) {
            this.subjetcs = subjetcs;
        }

        public static class SubjetcsBean {
            /**
             * id : 6
             * education_id : 3
             * stream_id : 4
             * year : 2
             * semester : 1
             * subject : Engineering Graphics
             * created_on : 2019-04-25 02:10:02
             * modified_on : null
             */


            private String id;
            private String education_id;
            private String stream_id;
            private String year;
            private String semester;
            private String subject;
            private String created_on;
            private Object modified_on;


            private Boolean isSelected = false;

            public Boolean getSelected() {
                return isSelected;
            }

            public void setSelected(Boolean selected) {
                isSelected = selected;
            }


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

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getSemester() {
                return semester;
            }

            public void setSemester(String semester) {
                this.semester = semester;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
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
}
