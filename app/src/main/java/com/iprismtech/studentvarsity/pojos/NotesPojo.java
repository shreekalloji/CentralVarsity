package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class NotesPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":"2","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","heading":"abcd","description":"efgh","status":"1","created_on":"2019-05-21 05:08:08","modified_on":null,"chapter":"1.1 Physical World","pings":"2","comments":"0","commented":"no","readed":"no","views":"0","viewed":"no"},{"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","heading":"commodo consequat.","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","status":"1","created_on":"2019-05-02 01:02:05","modified_on":null,"chapter":"1.1 Physical World","pings":"1","comments":"1","commented":"yes","readed":"yes","views":"1","viewed":"yes"}]
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
         * education_id : 3
         * stream_id : 4
         * subject_id : 3
         * chapter_id : 1
         * heading : abcd
         * description : efgh
         * status : 1
         * created_on : 2019-05-21 05:08:08
         * modified_on : null
         * chapter : 1.1 Physical World
         * pings : 2
         * comments : 0
         * commented : no
         * readed : no
         * views : 0
         * viewed : no
         */

        private String id;
        private String education_id;
        private String stream_id;
        private String subject_id;
        private String chapter_id;
        private String heading;
        private String description;
        private String status;
        private String created_on;
        private Object modified_on;
        private String chapter;
        private String pings;
        private String comments;
        private String commented;
        private String readed;
        private String views;
        private String viewed;

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

        public String getHeading() {
            return heading;
        }

        public void setHeading(String heading) {
            this.heading = heading;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getCommented() {
            return commented;
        }

        public void setCommented(String commented) {
            this.commented = commented;
        }

        public String getReaded() {
            return readed;
        }

        public void setReaded(String readed) {
            this.readed = readed;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getViewed() {
            return viewed;
        }

        public void setViewed(String viewed) {
            this.viewed = viewed;
        }
    }
}
