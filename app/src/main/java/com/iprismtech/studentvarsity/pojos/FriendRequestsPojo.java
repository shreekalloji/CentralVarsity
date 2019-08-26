package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class FriendRequestsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"name":"It's You","university":"Geetham College & University","profile_pic":"storage/profile_pics/5d14ce92d1445.jpeg","user_id":"34","friend_id":"33"}]
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
         * name : It's You
         * university : Geetham College & University
         * profile_pic : storage/profile_pics/5d14ce92d1445.jpeg
         * user_id : 34
         * friend_id : 33
         */

        private String name;
        private String university;
        private String profile_pic;
        private String user_id;
        private String friend_id;
        private boolean isFriend;

        public boolean isFriend() {
            return isFriend;
        }

        public void setFriend(boolean friend) {
            isFriend = friend;
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFriend_id() {
            return friend_id;
        }

        public void setFriend_id(String friend_id) {
            this.friend_id = friend_id;
        }
    }
}
