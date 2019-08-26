package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class ActivityDetailsPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : {"id":"9","user_id":"6","education_id":"3","stream_id":"1","subject_id":"10","chapter_id":"12","description":"Again with YouTube","image":null,"youtube":"https://youtu.be/XH104T-Zeo0","status":"1","created_on":"2019-06-14 13:05:17","modified_on":null,"subject":"M1","chapter":"Numerical Approximation","pings":"9","comments":"4","commented":"yes","readed":"no","views":"5","viewed":"yes","posted_on":"14 Jun 2019"}
     * comments : [{"name":"Srikanth K","mobile":"7878787878","university":"Geetham College & University","profile_pic":"storage/profile_pics/5d00a6639c172.jpeg","id":"105","user_id":"6","sections_id":"9","comment":"Testing boss ","image":"storage/images/5d0a1d021fabb.png","type":"activity","created_on":"2019-06-19 17:01:14","posted_on":"19 Jun 2019","comment_replies":[]},{"name":"Srikanth K","mobile":"7878787878","university":"Geetham College & University","profile_pic":"storage/profile_pics/5d00a6639c172.jpeg","id":"106","user_id":"6","sections_id":"9","comment":"Study of embedded systems and vlsi its not a course its a career for further has to aware of this language and build their careers in their profession \n generation mana","image":"storage/images/5d0a1e26f2e2e.png","type":"activity","created_on":"2019-06-19 17:06:07","posted_on":"19 Jun 2019","comment_replies":[]},{"name":"Srikanth K","mobile":"7878787878","university":"Geetham College & University","profile_pic":"storage/profile_pics/5d00a6639c172.jpeg","id":"107","user_id":"6","sections_id":"9","comment":"Yshshhshsjsjhshhsjdjdd\nDhndn bdvdv gz dbzbzbj hdhd zhbs hsvs hzbvz zhbzb jsnbsnsn hhshdhd hdhhd hdhdhd hdbdbdb jnxnndndnxndjd hshdhdbottle guard submit Srikanth ki bagodu image sariga lekapothe","image":"storage/images/5d0a2f14201d4.png","type":"activity","created_on":"2019-06-19 18:18:20","posted_on":"19 Jun 2019","comment_replies":[]},{"name":"Srikanth K","mobile":"7878787878","university":"Geetham College & University","profile_pic":"storage/profile_pics/5d00a6639c172.jpeg","id":"108","user_id":"6","sections_id":"9","comment":"Second ","image":"storage/images/5d0a35df4e562.png","type":"activity","created_on":"2019-06-19 18:47:19","posted_on":"19 Jun 2019","comment_replies":[]}]
     * views : [{"name":"Srikanth K","mobile":"7878787878","profile_pic":"storage/profile_pics/5d00a6639c172.jpeg","online_status":"Offline"},{"name":"Srikanth K","mobile":"7878787878","profile_pic":"storage/profile_pics/5d00a6639c172.jpeg","online_status":"Offline"},{"name":"Srikanth K","mobile":"7878787878","profile_pic":"storage/profile_pics/5d00a6639c172.jpeg","online_status":"Offline"},{"name":"Srikanth K","mobile":"7878787878","profile_pic":"storage/profile_pics/5d00a6639c172.jpeg","online_status":"Offline"},{"name":"Srikanth K","mobile":"7878787878","profile_pic":"storage/profile_pics/5d00a6639c172.jpeg","online_status":"Offline"}]
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
         * id : 9
         * user_id : 6
         * education_id : 3
         * stream_id : 1
         * subject_id : 10
         * chapter_id : 12
         * description : Again with YouTube
         * image : null
         * youtube : https://youtu.be/XH104T-Zeo0
         * status : 1
         * created_on : 2019-06-14 13:05:17
         * modified_on : null
         * subject : M1
         * chapter : Numerical Approximation
         * pings : 9
         * comments : 4
         * commented : yes
         * readed : no
         * views : 5
         * viewed : yes
         * posted_on : 14 Jun 2019
         */

        private String id;
        private String user_id;
        private String education_id;
        private String stream_id;
        private String subject_id;
        private String chapter_id;
        private String description;
        private Object image;
        private String youtube;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

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

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
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
         * name : Srikanth K
         * mobile : 7878787878
         * university : Geetham College & University
         * profile_pic : storage/profile_pics/5d00a6639c172.jpeg
         * id : 105
         * user_id : 6
         * sections_id : 9
         * comment : Testing boss
         * image : storage/images/5d0a1d021fabb.png
         * type : activity
         * created_on : 2019-06-19 17:01:14
         * posted_on : 19 Jun 2019
         * comment_replies : []
         */

        private String name;
        private String mobile;
        private String university;
        private String profile_pic;
        private String id;
        private String user_id;
        private String sections_id;
        private String comment;
        private String image;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
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
//         * name : Srikanth K
//         * mobile : 7878787878
//         * profile_pic : storage/profile_pics/5d00a6639c172.jpeg
//         * online_status : Offline
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
