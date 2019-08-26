package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class NotesDescriptionViewPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : {"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","heading":"commodo consequat.","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","status":"1","created_on":"2019-05-02 01:02:05","modified_on":null,"subject":"Physics","chapter":"1.1 Physical World","pings":"1","comments":"1","commented":"yes","readed":"yes","views":"1","viewed":"no","posted_on":"02 May 2019"}
     * comments : [{"name":"Smith Max","mobile":"8888888888","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"1","user_id":"2","sections_id":"1","comment":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":null,"type":"notes","created_on":"2019-05-02 15:03:55","posted_on":"02 May 2019","comment_replies":[{"name":"Smith Max","mobile":"8888888888","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"1","user_id":"2","sections_id":"1","comment_id":"1","comment":"Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":null,"type":"notes","created_on":"2019-05-20 03:04:06","posted_on":"20 May 2019"}]}]
     * views : [{"name":"Smith Max","mobile":"8888888888","profile_pic":"storage/profile_pics/5cc052c7c35f5.","online_status":"Online"}]
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
         * heading : commodo consequat.
         * description : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
         * status : 1
         * created_on : 2019-05-02 01:02:05
         * modified_on : null
         * subject : Physics
         * chapter : 1.1 Physical World
         * pings : 1
         * comments : 1
         * commented : yes
         * readed : yes
         * views : 1
         * viewed : no
         * posted_on : 02 May 2019
         */

        private String id;
        private String name;
        private String education_id;
        private String stream_id;
        private String subject_id;
        private String chapter_id;
        private String heading;
        private String description;
        private String status;
        private String created_on;
        private Object modified_on;
        private String subject;
        private String chapter;
        private String pings;
        private String comments;
        private String commented;
        private String readed;
        private String views;
        private String viewed;
        private String posted_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getPosted_on() {
            return posted_on;
        }

        public void setPosted_on(String posted_on) {
            this.posted_on = posted_on;
        }
    }

    public static class CommentsBean {
        /**
         * name : Smith Max
         * mobile : 8888888888
         * university : Geetham College & University
         * profile_pic : storage/profile_pics/5cc052c7c35f5.
         * id : 1
         * user_id : 2
         * sections_id : 1
         * comment : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * image : null
         * type : notes
         * created_on : 2019-05-02 15:03:55
         * posted_on : 02 May 2019
         * comment_replies : [{"name":"Smith Max","mobile":"8888888888","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"1","user_id":"2","sections_id":"1","comment_id":"1","comment":"Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":null,"type":"notes","created_on":"2019-05-20 03:04:06","posted_on":"20 May 2019"}]
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
        private List<CommentRepliesBean> comment_replies;

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

        public List<CommentRepliesBean> getComment_replies() {
            return comment_replies;
        }

        public void setComment_replies(List<CommentRepliesBean> comment_replies) {
            this.comment_replies = comment_replies;
        }

        public static class CommentRepliesBean {
            /**
             * name : Smith Max
             * mobile : 8888888888
             * university : Geetham College & University
             * profile_pic : storage/profile_pics/5cc052c7c35f5.
             * id : 1
             * user_id : 2
             * sections_id : 1
             * comment_id : 1
             * comment : Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
             * image : null
             * type : notes
             * created_on : 2019-05-20 03:04:06
             * posted_on : 20 May 2019
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

//    public static class ViewsBean {
//        /**
//         * name : Smith Max
//         * mobile : 8888888888
//         * profile_pic : storage/profile_pics/5cc052c7c35f5.
//         * online_status : Online
//         */
//
//        private String name;
//        private String mobile;
//        private String profile_pic;
//        private String online_status;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//
//        public String getProfile_pic() {
//            return profile_pic;
//        }
//
//        public void setProfile_pic(String profile_pic) {
//            this.profile_pic = profile_pic;
//        }
//
//        public String getOnline_status() {
//            return online_status;
//        }
//
//        public void setOnline_status(String online_status) {
//            this.online_status = online_status;
//        }
//    }
}
