package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class VideosListPOJO {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":"51","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"219","heading":"Physics video","video_link":"https://www.youtube.com/watch?v=PUZkE1vIvIA","description":"Physical video","status":"1","created_on":"2019-06-27 14:43:08","modified_on":null,"chapter":"PHYSICS-1","pings":"51","comments":"8","commented":"yes","readed":"no","views":"2","viewed":"yes"},{"id":"50","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"219","heading":"Physics Video5","video_link":"https://www.youtube.com/watch?v=zDj9EZqZ5Fo","description":"About  learning video-5","status":"1","created_on":"2019-06-27 13:19:50","modified_on":"2019-06-27 13:19:50","chapter":"PHYSICS-1","pings":"50","comments":"0","commented":"no","readed":"no","views":"0","viewed":"no"},{"id":"49","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"219","heading":"Physics Notes-4","video_link":"https://www.youtube.com/watch?v=0iSJVT4OwDM","description":"About Learning physics  video-4","status":"1","created_on":"2019-06-25 13:55:09","modified_on":null,"chapter":"PHYSICS-1","pings":"49","comments":"0","commented":"no","readed":"no","views":"0","viewed":"no"},{"id":"48","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"219","heading":"Physics Video-3","video_link":"https://www.youtube.com/watch?v=Ma6tMkO1zhg","description":"About learning physics video-3","status":"1","created_on":"2019-06-25 13:52:46","modified_on":null,"chapter":"PHYSICS-1","pings":"48","comments":"0","commented":"no","readed":"no","views":"0","viewed":"no"},{"id":"47","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"219","heading":"Physics video-2","video_link":"https://www.youtube.com/watch?v=yS4wP3NKVMc","description":"Physics Video-2","status":"1","created_on":"2019-06-25 13:51:22","modified_on":null,"chapter":"PHYSICS-1","pings":"47","comments":"0","commented":"no","readed":"yes","views":"0","viewed":"no"},{"id":"46","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"219","heading":"Physics video-1","video_link":"https://www.youtube.com/watch?v=zCdBwDr3EcA","description":"About learning Physics","status":"1","created_on":"2019-06-25 13:50:30","modified_on":null,"chapter":"PHYSICS-1","pings":"46","comments":"0","commented":"no","readed":"yes","views":"1","viewed":"yes"},{"id":"14","education_id":"3","stream_id":"3","year":"1","semester":"2","subject_id":"148","chapter_id":"100","heading":"LASER","video_link":"https://youtu.be/cCP_IZa0yWc?list=PL-eLHEko-mgJmL0lizj9NelH38vYfiJfi","description":"LASER PART 3.0 PROPERTIES , SPATIAL AND TEMPORAL COHERENCE","status":"1","created_on":"2019-06-13 15:27:12","modified_on":null,"chapter":"PHYSICS1","pings":"14","comments":"0","commented":"no","readed":"yes","views":"0","viewed":"no"},{"id":"13","education_id":"3","stream_id":"3","year":"1","semester":"2","subject_id":"148","chapter_id":"100","heading":"GRAVITY","video_link":"https://youtu.be/OiTiKOy59o4","description":"Gravity - Official Main Trailer ","status":"1","created_on":"2019-06-13 15:24:26","modified_on":null,"chapter":"PHYSICS1","pings":"13","comments":"0","commented":"no","readed":"no","views":"0","viewed":"no"}]
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
         * id : 51
         * education_id : 3
         * stream_id : 3
         * year : 1
         * semester : 1
         * subject_id : 148
         * chapter_id : 219
         * heading : Physics video
         * video_link : https://www.youtube.com/watch?v=PUZkE1vIvIA
         * description : Physical video
         * status : 1
         * created_on : 2019-06-27 14:43:08
         * modified_on : null
         * chapter : PHYSICS-1
         * pings : 51
         * comments : 8
         * commented : yes
         * readed : no
         * views : 2
         * viewed : yes
         */

        private String id;
        private String education_id;
        private String stream_id;
        private String year;
        private String semester;
        private String subject_id;
        private String chapter_id;
        private String heading;
        private String video_link;
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

        public String getVideo_link() {
            return video_link;
        }

        public void setVideo_link(String video_link) {
            this.video_link = video_link;
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
