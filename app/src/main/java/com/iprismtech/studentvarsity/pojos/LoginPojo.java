package com.iprismtech.studentvarsity.pojos;

/**
 * Created by intel on 11/16/2018.
 */

public class LoginPojo {

    /**
     * status : true
     * message : Login Successful!
     * response : {"id":3,"f_name":"athar-3","l_name":"aamir-3","username":"1234567893","email_id":"athar.m3@iprismtech.com","mobile":"1234567893","password":"$2y$10$WnJWBDc3evgMor2lbM6Sp.M9mwtz4rXVvI/j1d2ZN7F9KAQfu96ui","token":"","ios_token":"","profile_image":"","gender":"Male","type":"user","status":"active","remember_token":"","created_at":"2018-08-20 13:29:57","updated_at":null}
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
         * id : 3
         * f_name : athar-3
         * l_name : aamir-3
         * username : 1234567893
         * email_id : athar.m3@iprismtech.com
         * mobile : 1234567893
         * password : $2y$10$WnJWBDc3evgMor2lbM6Sp.M9mwtz4rXVvI/j1d2ZN7F9KAQfu96ui
         * token :
         * ios_token :
         * profile_image :
         * gender : Male
         * type : user
         * status : active
         * remember_token :
         * created_at : 2018-08-20 13:29:57
         * updated_at : null
         */

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
        private String gender;
        private String type;
        private String status;
        private String remember_token;
        private String created_at;
        private Object updated_at;

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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
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

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }
}
