package com.iprismtech.studentvarsity.pojos;

/**
 * Created by intel on 29-Nov-18.
 */

public class UpdateProfile {

    /**
     * status : true
     * message : Profile updated Successfully!
     * response : {"id":17,"f_name":"Sarfu","l_name":"Khan","username":"9835518388","email_id":"sarfuahmed88@gmail.com","mobile":"9835518388","password":"$2y$10$NPFbU5QN674paFAkPKaPyO3mp9sWqSZv9C22MyrjMiqxsyOBcSnx.","token":"","ios_token":"","profile_image":"storage/img/5bfe5afee597e.png","gender":null,"type":"user","status":"active","remember_token":"","created_at":"2018-11-28 14:38:15","updated_at":"2018-11-29 16:19:20","user_rating":null}
     */

    private boolean status;
    private String message;
    /**
     * id : 17
     * f_name : Sarfu
     * l_name : Khan
     * username : 9835518388
     * email_id : sarfuahmed88@gmail.com
     * mobile : 9835518388
     * password : $2y$10$NPFbU5QN674paFAkPKaPyO3mp9sWqSZv9C22MyrjMiqxsyOBcSnx.
     * token :
     * ios_token :
     * profile_image : storage/img/5bfe5afee597e.png
     * gender : null
     * type : user
     * status : active
     * remember_token :
     * created_at : 2018-11-28 14:38:15
     * updated_at : 2018-11-29 16:19:20
     * user_rating : null
     */

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
        private int id;
        private String f_name;
        private String l_name;
        private String username;
        private String email_id;
        private String mobile;
        private String password;
        private String token;
        private String ios_token;
        private String profile_image;
        private Object gender;
        private String type;
        private String status;
        private String remember_token;
        private String created_at;
        private String updated_at;
        private Object user_rating;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getL_name() {
            return l_name;
        }

        public void setL_name(String l_name) {
            this.l_name = l_name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIos_token() {
            return ios_token;
        }

        public void setIos_token(String ios_token) {
            this.ios_token = ios_token;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRemember_token() {
            return remember_token;
        }

        public void setRemember_token(String remember_token) {
            this.remember_token = remember_token;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Object getUser_rating() {
            return user_rating;
        }

        public void setUser_rating(Object user_rating) {
            this.user_rating = user_rating;
        }
    }
}
