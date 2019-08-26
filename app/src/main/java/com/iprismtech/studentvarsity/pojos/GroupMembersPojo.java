package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class GroupMembersPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":"2","title":"Centralvarsity","user_id":"1","color_code":"ea6292","created_on":"2019-05-09 15:19:04","name":"Jhon Doe","university":"Geetham College & University","members":[{"id":"7","group_id":"2","user_id":"5","created_on":"2019-05-13 12:46:28","name":"ram","university":"geetam"},{"id":"6","group_id":"2","user_id":"4","created_on":"2019-05-09 15:19:04","name":"Arnold","university":"Geetham College & University"},{"id":"5","group_id":"2","user_id":"3","created_on":"2019-05-09 15:19:04","name":"Leo Smack","university":"Geetham College & University"},{"id":"4","group_id":"2","user_id":"2","created_on":"2019-05-09 15:19:04","name":"Smith Max","university":"Geetham College & University"}]}]
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
         * title : Centralvarsity
         * user_id : 1
         * color_code : ea6292
         * created_on : 2019-05-09 15:19:04
         * name : Jhon Doe
         * university : Geetham College & University
         * members : [{"id":"7","group_id":"2","user_id":"5","created_on":"2019-05-13 12:46:28","name":"ram","university":"geetam"},{"id":"6","group_id":"2","user_id":"4","created_on":"2019-05-09 15:19:04","name":"Arnold","university":"Geetham College & University"},{"id":"5","group_id":"2","user_id":"3","created_on":"2019-05-09 15:19:04","name":"Leo Smack","university":"Geetham College & University"},{"id":"4","group_id":"2","user_id":"2","created_on":"2019-05-09 15:19:04","name":"Smith Max","university":"Geetham College & University"}]
         */

        private String id;
        private String title;
        private String user_id;
        private String color_code;
        private String created_on;
        private String name;
        private String university;
        private String profile_pic;

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        private List<MembersBean> members;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getColor_code() {
            return color_code;
        }

        public void setColor_code(String color_code) {
            this.color_code = color_code;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public List<MembersBean> getMembers() {
            return members;
        }

        public void setMembers(List<MembersBean> members) {
            this.members = members;
        }

        public static class MembersBean {
            /**
             * id : 7
             * group_id : 2
             * user_id : 5
             * created_on : 2019-05-13 12:46:28
             * name : ram
             * university : geetam
             */

            private String id;
            private String group_id;
            private String user_id;
            private String created_on;
            private String name;
            private String university;
            private String profile_pic;

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

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getCreated_on() {
                return created_on;
            }

            public void setCreated_on(String created_on) {
                this.created_on = created_on;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUniversity() {
                return university;
            }

            public void setUniversity(String university) {
                this.university = university;
            }
        }
    }
}
