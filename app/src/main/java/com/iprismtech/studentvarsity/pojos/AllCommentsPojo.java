package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class AllCommentsPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"52","user_id":"12","sections_id":"14","comment":"Comment here ","image":null,"type":"notes","created_on":"2019-06-12 19:44:10","posted_on":"12 Jun 2019","comment_replies":[{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"3","user_id":"12","sections_id":"14","comment_id":"52","comment":"Reply to comments","image":null,"type":"notes","created_on":"2019-06-12 20:09:10","posted_on":"12 Jun 2019"},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"4","user_id":"12","sections_id":"14","comment_id":"52","comment":"Reply to given comment","image":null,"type":"notes","created_on":"2019-06-12 20:14:04","posted_on":"12 Jun 2019"}]},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"53","user_id":"12","sections_id":"14","comment":"Twat and ","image":"storage/images/5d0108d27f369.png","type":"notes","created_on":"2019-06-12 19:44:42","posted_on":"12 Jun 2019","comment_replies":[]},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"54","user_id":"12","sections_id":"14","comment":"Tatkal ","image":"storage/images/5d010ac2e2817.png","type":"notes","created_on":"2019-06-12 19:52:58","posted_on":"12 Jun 2019","comment_replies":[]},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"55","user_id":"12","sections_id":"14","comment":"Tatkal ","image":"storage/images/5d010ad891a8a.png","type":"notes","created_on":"2019-06-12 19:53:20","posted_on":"12 Jun 2019","comment_replies":[]},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"57","user_id":"12","sections_id":"14","comment":"Testing 5555","image":"storage/images/5d010b8e65041.jpeg","type":"notes","created_on":"2019-06-12 19:56:22","posted_on":"12 Jun 2019","comment_replies":[]},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"58","user_id":"12","sections_id":"14","comment":"Testing 5555","image":null,"type":"notes","created_on":"2019-06-12 19:56:35","posted_on":"12 Jun 2019","comment_replies":[]},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"59","user_id":"12","sections_id":"14","comment":"Tatkal ","image":"storage/images/5d010ba91642f.png","type":"notes","created_on":"2019-06-12 19:56:49","posted_on":"12 Jun 2019","comment_replies":[]},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"60","user_id":"12","sections_id":"14","comment":"Testing 5555","image":null,"type":"notes","created_on":"2019-06-12 20:00:01","posted_on":"12 Jun 2019","comment_replies":[]},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"61","user_id":"12","sections_id":"14","comment":"Testing 5555","image":null,"type":"notes","created_on":"2019-06-12 20:02:58","posted_on":"12 Jun 2019","comment_replies":[]},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"62","user_id":"12","sections_id":"14","comment":"Testing 5555","image":null,"type":"notes","created_on":"2019-06-12 20:03:00","posted_on":"12 Jun 2019","comment_replies":[]}]
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

    public  class ResponseBean {
        /**
         * name : chinnam rama krishna
         * mobile : 1111111111
         * university : Imperial
         * profile_pic : storage/profile_pics/5d01408991d3b.jpeg
         * id : 52
         * user_id : 12
         * sections_id : 14
         * comment : Comment here
         * image : null
         * type : notes
         * created_on : 2019-06-12 19:44:10
         * posted_on : 12 Jun 2019
         * comment_replies : [{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"3","user_id":"12","sections_id":"14","comment_id":"52","comment":"Reply to comments","image":null,"type":"notes","created_on":"2019-06-12 20:09:10","posted_on":"12 Jun 2019"},{"name":"chinnam rama krishna","mobile":"1111111111","university":"Imperial","profile_pic":"storage/profile_pics/5d01408991d3b.jpeg","id":"4","user_id":"12","sections_id":"14","comment_id":"52","comment":"Reply to given comment","image":null,"type":"notes","created_on":"2019-06-12 20:14:04","posted_on":"12 Jun 2019"}]
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

        public  class CommentRepliesBean {
            /**
             * name : chinnam rama krishna
             * mobile : 1111111111
             * university : Imperial
             * profile_pic : storage/profile_pics/5d01408991d3b.jpeg
             * id : 3
             * user_id : 12
             * sections_id : 14
             * comment_id : 52
             * comment : Reply to comments
             * image : null
             * type : notes
             * created_on : 2019-06-12 20:09:10
             * posted_on : 12 Jun 2019
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
}
