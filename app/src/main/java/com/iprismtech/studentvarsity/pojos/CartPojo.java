package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class CartPojo {


    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":1,"user_id":4,"delivery_id":1,"product_id":2,"quantity":2,"price":500,"created_at":"2018-09-11 11:37:29","updated_at":"0000-00-00 00:00:00","product_name":"Round Neck T-shirts","product_a_name":null,"name":"Athar","alternate_mobile":"8686946690","address":"Banjara Hills","state":"Telengana","city":"Hyderabad","pincode":500034,"locality_town":"Soceity","area":"Banjara"},{"id":1,"user_id":4,"delivery_id":1,"product_id":1,"quantity":2,"price":500,"created_at":"2018-09-11 11:37:29","updated_at":"0000-00-00 00:00:00","product_name":"Half Sleeves T-shirts","product_a_name":null,"name":"Athar","alternate_mobile":"8686946690","address":"Banjara Hills","state":"Telengana","city":"Hyderabad","pincode":500034,"locality_town":"Soceity","area":"Banjara"}]
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
         * id : 1
         * user_id : 4
         * delivery_id : 1
         * product_id : 2
         * quantity : 2
         * price : 500
         * created_at : 2018-09-11 11:37:29
         * updated_at : 0000-00-00 00:00:00
         * product_name : Round Neck T-shirts
         * product_a_name : null
         * name : Athar
         * alternate_mobile : 8686946690
         * address : Banjara Hills
         * state : Telengana
         * city : Hyderabad
         * pincode : 500034
         * locality_town : Soceity
         * area : Banjara
         */

        private int id;
        private int user_id;
        private int delivery_id;
        private int product_id;
        private int quantity;
        private int price;
        private String created_at;
        private String updated_at;
        private String product_name;
        private Object product_a_name;
        private String name;
        private String alternate_mobile;
        private String address;
        private String state;
        private String city;
        private int pincode;
        private String locality_town;
        private String area;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getDelivery_id() {
            return delivery_id;
        }

        public void setDelivery_id(int delivery_id) {
            this.delivery_id = delivery_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
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

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public Object getProduct_a_name() {
            return product_a_name;
        }

        public void setProduct_a_name(Object product_a_name) {
            this.product_a_name = product_a_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlternate_mobile() {
            return alternate_mobile;
        }

        public void setAlternate_mobile(String alternate_mobile) {
            this.alternate_mobile = alternate_mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getPincode() {
            return pincode;
        }

        public void setPincode(int pincode) {
            this.pincode = pincode;
        }

        public String getLocality_town() {
            return locality_town;
        }

        public void setLocality_town(String locality_town) {
            this.locality_town = locality_town;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}
