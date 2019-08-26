package com.iprismtech.studentvarsity.pojos;

public class SummaryPOJO {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"user_details":{"id":"1","name":"Jhon Doe","university":"Geetham College & University","country_id":"1","city_id":"1","bio":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","profile_pic":"storage/profile_pics/5cc052c7c35f5.","mobile":"9999999999","password":"e10adc3949ba59abbe56e057f20f883e","education_id":"1","stream_id":"1","years":"2,3","subjects":"1,2,3","friends_added":"no","notifications":"no","ping_notifications":"no","token":null,"ios_token":null,"status":"1","delete_status":"1","created_on":"2019-04-24 16:50:18","modified_on":"2019-04-24 17:42:55","subject_names":"Calculus and Diffrential Equations, Chemistry, Physics"},"notes_count":1,"notes_read_count":1,"videos_count":1,"videos_read_count":1,"faqs_count":1,"faqs_read_count":0,"mcqs_count":1,"mcqs_read_count":0,"quiz_count":1,"quiz_completed_count":1,"dicuss_count":0,"dicussed_count":0}
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
         * user_details : {"id":"1","name":"Jhon Doe","university":"Geetham College & University","country_id":"1","city_id":"1","bio":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","profile_pic":"storage/profile_pics/5cc052c7c35f5.","mobile":"9999999999","password":"e10adc3949ba59abbe56e057f20f883e","education_id":"1","stream_id":"1","years":"2,3","subjects":"1,2,3","friends_added":"no","notifications":"no","ping_notifications":"no","token":null,"ios_token":null,"status":"1","delete_status":"1","created_on":"2019-04-24 16:50:18","modified_on":"2019-04-24 17:42:55","subject_names":"Calculus and Diffrential Equations, Chemistry, Physics"}
         * notes_count : 1
         * notes_read_count : 1
         * videos_count : 1
         * videos_read_count : 1
         * faqs_count : 1
         * faqs_read_count : 0
         * mcqs_count : 1
         * mcqs_read_count : 0
         * quiz_count : 1
         * quiz_completed_count : 1
         * dicuss_count : 0
         * dicussed_count : 0
         */

        private UserDetailsBean user_details;
        private int notes_count;
        private int notes_read_count;
        private int videos_count;
        private int videos_read_count;
        private int faqs_count;
        private int faqs_read_count;
        private int mcqs_count;
        private int mcqs_read_count;
        private int quiz_count;
        private int quiz_completed_count;
        private int dicuss_count;
        private int dicussed_count;

        public UserDetailsBean getUser_details() {
            return user_details;
        }

        public void setUser_details(UserDetailsBean user_details) {
            this.user_details = user_details;
        }

        public int getNotes_count() {
            return notes_count;
        }

        public void setNotes_count(int notes_count) {
            this.notes_count = notes_count;
        }

        public int getNotes_read_count() {
            return notes_read_count;
        }

        public void setNotes_read_count(int notes_read_count) {
            this.notes_read_count = notes_read_count;
        }

        public int getVideos_count() {
            return videos_count;
        }

        public void setVideos_count(int videos_count) {
            this.videos_count = videos_count;
        }

        public int getVideos_read_count() {
            return videos_read_count;
        }

        public void setVideos_read_count(int videos_read_count) {
            this.videos_read_count = videos_read_count;
        }

        public int getFaqs_count() {
            return faqs_count;
        }

        public void setFaqs_count(int faqs_count) {
            this.faqs_count = faqs_count;
        }

        public int getFaqs_read_count() {
            return faqs_read_count;
        }

        public void setFaqs_read_count(int faqs_read_count) {
            this.faqs_read_count = faqs_read_count;
        }

        public int getMcqs_count() {
            return mcqs_count;
        }

        public void setMcqs_count(int mcqs_count) {
            this.mcqs_count = mcqs_count;
        }

        public int getMcqs_read_count() {
            return mcqs_read_count;
        }

        public void setMcqs_read_count(int mcqs_read_count) {
            this.mcqs_read_count = mcqs_read_count;
        }

        public int getQuiz_count() {
            return quiz_count;
        }

        public void setQuiz_count(int quiz_count) {
            this.quiz_count = quiz_count;
        }

        public int getQuiz_completed_count() {
            return quiz_completed_count;
        }

        public void setQuiz_completed_count(int quiz_completed_count) {
            this.quiz_completed_count = quiz_completed_count;
        }

        public int getDicuss_count() {
            return dicuss_count;
        }

        public void setDicuss_count(int dicuss_count) {
            this.dicuss_count = dicuss_count;
        }

        public int getDicussed_count() {
            return dicussed_count;
        }

        public void setDicussed_count(int dicussed_count) {
            this.dicussed_count = dicussed_count;
        }

        public static class UserDetailsBean {
            /**
             * id : 1
             * name : Jhon Doe
             * university : Geetham College & University
             * country_id : 1
             * city_id : 1
             * bio : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
             * profile_pic : storage/profile_pics/5cc052c7c35f5.
             * mobile : 9999999999
             * password : e10adc3949ba59abbe56e057f20f883e
             * education_id : 1
             * stream_id : 1
             * years : 2,3
             * subjects : 1,2,3
             * friends_added : no
             * notifications : no
             * ping_notifications : no
             * token : null
             * ios_token : null
             * status : 1
             * delete_status : 1
             * created_on : 2019-04-24 16:50:18
             * modified_on : 2019-04-24 17:42:55
             * subject_names : Calculus and Diffrential Equations, Chemistry, Physics
             */

            private String id;
            private String name;
            private String university;
            private String country_id;
            private String city_id;
            private String bio;
            private String profile_pic;
            private String mobile;
            private String password;
            private String education_id;
            private String stream_id;
            private String years;
            private String subjects;
            private String friends_added;
            private String notifications;
            private String ping_notifications;
            private Object token;
            private Object ios_token;
            private String status;
            private String delete_status;
            private String created_on;
            private String modified_on;
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

            public String getCountry_id() {
                return country_id;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
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

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
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

            public String getSubject_names() {
                return subject_names;
            }

            public void setSubject_names(String subject_names) {
                this.subject_names = subject_names;
            }
        }
    }
}
