package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class VideosDetailPOJO {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : {"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","heading":"Lorem ipsum dolor sit amet","video_link":"https://www.youtube.com/watch?v=IzQsYnLz2Gc","status":"1","created_on":"2019-05-02 01:02:03","modified_on":null,"chapter":"1.1 Physical World","pings":"1","comments":"5","commented":"yes","readed":"yes","views":"3","viewed":"yes"}
     * comments : [{"name":"Jhon Doe","mobile":"9999999999","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"5","user_id":"1","sections_id":"1","comment":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":null,"type":"videos","created_on":"2019-05-02 18:52:38","posted_on":"02 May 2019"},{"name":"Jhon Doe","mobile":"9999999999","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"8","user_id":"1","sections_id":"1","comment":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":"storage/images/5cd3cfd302eda.png","type":"videos","created_on":"2019-05-09 12:29:31","posted_on":"09 May 2019"},{"name":"Jhon Doe","mobile":"9999999999","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"18","user_id":"1","sections_id":"1","comment":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":"storage/images/5cd519a7b5aa4.png","type":"videos","created_on":"2019-05-10 11:56:47","posted_on":"Just Now"},{"name":"Jhon Doe","mobile":"9999999999","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"19","user_id":"1","sections_id":"1","comment":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":"storage/images/5cd51ca96a0e9.png","type":"videos","created_on":"2019-05-10 12:09:37","posted_on":"Just Now"},{"name":"Jhon Doe","mobile":"9999999999","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"20","user_id":"1","sections_id":"1","comment":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":"storage/images/5cd51d0bec6e1.gif","type":"videos","created_on":"2019-05-10 12:11:15","posted_on":"Just Now"}]
     * views : []
     */

    private boolean status;
    private String message;
    private ResponseBean response;
    private List<CommentsBean> comments;
    private List<AllViewedPeoplePojo.ViewsBean> views;

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

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public List<AllViewedPeoplePojo.ViewsBean> getViews() {
        return views;
    }

    public void setViews(List<AllViewedPeoplePojo.ViewsBean> views) {
        this.views = views;
    }

    public static class ResponseBean {
        /**
         * id : 1
         * education_id : 3
         * stream_id : 4
         * subject_id : 3
         * chapter_id : 1
         * heading : Lorem ipsum dolor sit amet
         * video_link : https://www.youtube.com/watch?v=IzQsYnLz2Gc
         * status : 1
         * created_on : 2019-05-02 01:02:03
         * modified_on : null
         * chapter : 1.1 Physical World
         * pings : 1
         * comments : 5
         * commented : yes
         * readed : yes
         * views : 3
         * viewed : yes
         */

        private String id;
        private String education_id;
        private String stream_id;
        private String subject_id;
        private String chapter_id;
        private String heading;
        private String video_link;
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

        public String getVideo_link() {
            return video_link;
        }

        public void setVideo_link(String video_link) {
            this.video_link = video_link;
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

    public static class CommentsBean {
        /**
         * name : Jhon Doe
         * mobile : 9999999999
         * university : Geetham College & University
         * profile_pic : storage/profile_pics/5cc052c7c35f5.
         * id : 5
         * user_id : 1
         * sections_id : 1
         * comment : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * image : null
         * type : videos
         * created_on : 2019-05-02 18:52:38
         * posted_on : 02 May 2019
         */

        private String name;
        private String mobile;
        private String university;
        private String profile_pic;
        private String id;
        private String user_id;
        private String sections_id;
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
