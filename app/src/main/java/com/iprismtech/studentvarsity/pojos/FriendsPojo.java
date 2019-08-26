package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class FriendsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"user_id":"3","name":"Leo Smack","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5."},{"user_id":"2","name":"Smith Max","university":"Geetham College & University","profile_pic":"storage/profile_pics/5cc052c7c35f5."}]
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
         * user_id : 3
         * name : Leo Smack
         * university : Geetham College & University
         * profile_pic : storage/profile_pics/5cc052c7c35f5.
         */

        private String user_id;
        private String name;
        private String university;
        private String profile_pic;

        public boolean isClicked = false;

        public boolean isClicked() {
            return isClicked;
        }

        public void setClicked(boolean clicked) {
            isClicked = clicked;
        }



        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }
    }
}
