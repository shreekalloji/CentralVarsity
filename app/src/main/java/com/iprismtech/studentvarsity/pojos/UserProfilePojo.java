package com.iprismtech.studentvarsity.pojos;

public class UserProfilePojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"1","name":"Jhon Doe","university":"Geetham College & University","country":"1","city":"1","bio":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","profile_pic":"storage/profile_pics/5cffb433d95ff.","cover_pic":"storage/profile_pics/5ce3afdfdf04f.","mobile":"9999999999","password":"e10adc3949ba59abbe56e057f20f883e","email_id":"johndoe@gmail.com","education_id":"2","stream_id":"2","years":"2,3","subjects":"1,2,3","friends_added":"no","notifications":"","ping_notifications":"on","token":null,"ios_token":null,"status":"1","delete_status":"1","created_on":"2019-04-24 16:50:18","modified_on":"2019-06-11 19:31:23","stream":"Mechanical (MECH)","subject_names":"Calculus and Diffrential Equations, Chemistry, Physics"}
     */

    private boolean status;
    private String message;
    private ResponseBean response;

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

    public static class ResponseBean {
        /**
         * id : 1
         * name : Jhon Doe
         * university : Geetham College & University
         * country : 1
         * city : 1
         * bio : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
         * profile_pic : storage/profile_pics/5cffb433d95ff.
         * cover_pic : storage/profile_pics/5ce3afdfdf04f.
         * mobile : 9999999999
         * password : e10adc3949ba59abbe56e057f20f883e
         * email_id : johndoe@gmail.com
         * education_id : 2
         * stream_id : 2
         * years : 2,3
         * subjects : 1,2,3
         * friends_added : no
         * notifications :
         * ping_notifications : on
         * token : null
         * ios_token : null
         * status : 1
         * delete_status : 1
         * created_on : 2019-04-24 16:50:18
         * modified_on : 2019-06-11 19:31:23
         * stream : Mechanical (MECH)
         * subject_names : Calculus and Diffrential Equations, Chemistry, Physics
         */

        private String id;
        private String name;
        private String university;
        private String country;
        private String city;
        private String bio;
        private String profile_pic;
        private String cover_pic;
        private String mobile;
        private String password;
        private String email_id;
        private String education_id;
        private String stream_id;
        private String years;
        private String subjects;
        private String friends_added;
        private String notifications;
        private String ping_notifications;
        private String token;
        private Object ios_token;
        private String status;
        private String delete_status;
        private String created_on;
        private String modified_on;
        private String stream;
        private String subject_names;

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

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getCover_pic() {
            return cover_pic;
        }

        public void setCover_pic(String cover_pic) {
            this.cover_pic = cover_pic;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
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

        public String getYears() {
            return years;
        }

        public void setYears(String years) {
            this.years = years;
        }

        public String getSubjects() {
            return subjects;
        }

        public void setSubjects(String subjects) {
            this.subjects = subjects;
        }

        public String getFriends_added() {
            return friends_added;
        }

        public void setFriends_added(String friends_added) {
            this.friends_added = friends_added;
        }

        public String getNotifications() {
            return notifications;
        }

        public void setNotifications(String notifications) {
            this.notifications = notifications;
        }

        public String getPing_notifications() {
            return ping_notifications;
        }

        public void setPing_notifications(String ping_notifications) {
            this.ping_notifications = ping_notifications;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getIos_token() {
            return ios_token;
        }

        public void setIos_token(Object ios_token) {
            this.ios_token = ios_token;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelete_status() {
            return delete_status;
        }

        public void setDelete_status(String delete_status) {
            this.delete_status = delete_status;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getModified_on() {
            return modified_on;
        }

        public void setModified_on(String modified_on) {
            this.modified_on = modified_on;
        }

        public String getStream() {
            return stream;
        }

        public void setStream(String stream) {
            this.stream = stream;
        }

        public String getSubject_names() {
            return subject_names;
        }

        public void setSubject_names(String subject_names) {
            this.subject_names = subject_names;
        }
    }
}
