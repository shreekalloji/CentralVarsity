package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class ViewAllRepliesPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"name":"Smith Max","mobile":"8888888888","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"1","user_id":"2","sections_id":"1","comment":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":null,"type":"notes","created_on":"2019-05-02 15:03:55","posted_on":"02 May 2019","comment_replies":[{"name":"Smith Max","mobile":"8888888888","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5.","id":"1","user_id":"2","sections_id":"1","comment_id":"1","comment":"Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","image":null,"type":"notes","created_on":"2019-05-20 03:04:06","posted_on":"20 May 2019"}]}]
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
}
