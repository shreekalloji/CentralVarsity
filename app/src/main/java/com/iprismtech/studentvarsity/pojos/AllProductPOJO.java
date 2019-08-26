package com.iprismtech.studentvarsity.pojos;

import java.util.List;

/**
 * Created by intel on 04-Dec-18.
 */

public class AllProductPOJO {


    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":1,"department_id":1,"section_id":1,"category_id":1,"sub_category_id":2,"product_name":"Half Sleeves T-shirts","product_a_name":"","brand_id":1,"quantity":"2500","price":520,"size":"XL","color":"#fff","description":"saf","a_description":"","offer_price":"260","percentage_offer":"50","product_code":0,"delivery_duration":"","image_1":"storage/img/5b90d0c313a2e1536217283.jpg","image_2":"","image_3":"","image_4":"","created_at":"2018-09-22 08:56:33","updated_at":"0000-00-00 00:00:00","brand_title":"Levis","brand_a_title":"ليفيس"},{"id":4,"department_id":1,"section_id":1,"category_id":1,"sub_category_id":2,"product_name":"Full Sleeves T-Shirts","product_a_name":"","brand_id":2,"quantity":"2500","price":1950,"size":"XL,M,S","color":"#0fee1e","description":"sadfgv fghnj dfh fghngf","a_description":"","offer_price":"0","percentage_offer":"","product_code":0,"delivery_duration":"","image_1":"storage/img/5b927a81f2f321536326273.jpg","image_2":"storage/img/5b927a81f2fe21536326273.jpg","image_3":"storage/img/5b927a81f30581536326273.jpg","image_4":"storage/img/5b927a81f30c91536326273.jpg","created_at":"2018-09-07 13:17:53","updated_at":"0000-00-00 00:00:00","brand_title":"US Polo","brand_a_title":"بولو الولايات المتحدة"},{"id":2,"department_id":1,"section_id":1,"category_id":1,"sub_category_id":2,"product_name":"Round Neck T-shirts","product_a_name":"","brand_id":3,"quantity":"8000","price":600,"size":"L","color":"#ffffff","description":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley ","a_description":"","offer_price":"0","percentage_offer":"","product_code":0,"delivery_duration":"","image_1":"storage/img/5b9106a4581c11536231076.jpeg","image_2":"","image_3":"","image_4":"","created_at":"2018-09-06 10:51:16","updated_at":"0000-00-00 00:00:00","brand_title":"Jack & Jones","brand_a_title":"جاك جونز"}]
     * Categroy : [{"id":2,"department_id":1,"section_id":1,"category_id":1,"title":"T-shirts","a_title":"تي شيرت","image":"storage/img/5b8e2064d67041536041060.jpg","created_at":"2018-09-04 06:11:34","updated_at":"2018-09-04 06:11:34"},{"id":3,"department_id":1,"section_id":1,"category_id":1,"title":"Casual Shirts","a_title":"قمصان","image":"storage/img/5b8e24ee64af61536042222.jpeg","created_at":"2018-09-07 06:18:35","updated_at":"0000-00-00 00:00:00"},{"id":4,"department_id":1,"section_id":1,"category_id":1,"title":"Formal Shirts","a_title":"قمصان رسمية","image":"storage/img/5c00ddfbd08b91543560699.jpeg","created_at":"2018-11-30 06:51:39","updated_at":"2018-11-30 12:21:39"}]
     */

    private boolean status;
    private String message;
    /**
     * id : 1
     * department_id : 1
     * section_id : 1
     * category_id : 1
     * sub_category_id : 2
     * product_name : Half Sleeves T-shirts
     * product_a_name :
     * brand_id : 1
     * quantity : 2500
     * price : 520
     * size : XL
     * color : #fff
     * description : saf
     * a_description :
     * offer_price : 260
     * percentage_offer : 50
     * product_code : 0
     * delivery_duration :
     * image_1 : storage/img/5b90d0c313a2e1536217283.jpg
     * image_2 :
     * image_3 :
     * image_4 :
     * created_at : 2018-09-22 08:56:33
     * updated_at : 0000-00-00 00:00:00
     * brand_title : Levis
     * brand_a_title : ليفيس
     */

    private List<ResponseBean> response;
    /**
     * id : 2
     * department_id : 1
     * section_id : 1
     * category_id : 1
     * title : T-shirts
     * a_title : تي شيرت
     * image : storage/img/5b8e2064d67041536041060.jpg
     * created_at : 2018-09-04 06:11:34
     * updated_at : 2018-09-04 06:11:34
     */

    private List<CategroyBean> Categroy;

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

    public List<CategroyBean> getCategroy() {
        return Categroy;
    }

    public void setCategroy(List<CategroyBean> Categroy) {
        this.Categroy = Categroy;
    }

    public static class ResponseBean {
        private int id;
        private int department_id;
        private int section_id;
        private int category_id;
        private int sub_category_id;
        private String product_name;
        private String product_a_name;
        private int brand_id;
        private String quantity;
        private int price;
        private String size;
        private String color;
        private String description;
        private String a_description;
        private String offer_price;
        private String percentage_offer;
        private int product_code;
        private String delivery_duration;
        private String image_1;
        private String image_2;
        private String image_3;
        private String image_4;
        private String created_at;
        private String updated_at;
        private String brand_title;
        private String brand_a_title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(int department_id) {
            this.department_id = department_id;
        }

        public int getSection_id() {
            return section_id;
        }

        public void setSection_id(int section_id) {
            this.section_id = section_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getSub_category_id() {
            return sub_category_id;
        }

        public void setSub_category_id(int sub_category_id) {
            this.sub_category_id = sub_category_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_a_name() {
            return product_a_name;
        }

        public void setProduct_a_name(String product_a_name) {
            this.product_a_name = product_a_name;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getA_description() {
            return a_description;
        }

        public void setA_description(String a_description) {
            this.a_description = a_description;
        }

        public String getOffer_price() {
            return offer_price;
        }

        public void setOffer_price(String offer_price) {
            this.offer_price = offer_price;
        }

        public String getPercentage_offer() {
            return percentage_offer;
        }

        public void setPercentage_offer(String percentage_offer) {
            this.percentage_offer = percentage_offer;
        }

        public int getProduct_code() {
            return product_code;
        }

        public void setProduct_code(int product_code) {
            this.product_code = product_code;
        }

        public String getDelivery_duration() {
            return delivery_duration;
        }

        public void setDelivery_duration(String delivery_duration) {
            this.delivery_duration = delivery_duration;
        }

        public String getImage_1() {
            return image_1;
        }

        public void setImage_1(String image_1) {
            this.image_1 = image_1;
        }

        public String getImage_2() {
            return image_2;
        }

        public void setImage_2(String image_2) {
            this.image_2 = image_2;
        }

        public String getImage_3() {
            return image_3;
        }

        public void setImage_3(String image_3) {
            this.image_3 = image_3;
        }

        public String getImage_4() {
            return image_4;
        }

        public void setImage_4(String image_4) {
            this.image_4 = image_4;
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

        public String getBrand_title() {
            return brand_title;
        }

        public void setBrand_title(String brand_title) {
            this.brand_title = brand_title;
        }

        public String getBrand_a_title() {
            return brand_a_title;
        }

        public void setBrand_a_title(String brand_a_title) {
            this.brand_a_title = brand_a_title;
        }
    }

    public static class CategroyBean {
        private int id;
        private int department_id;
        private int section_id;
        private int category_id;
        private String title;
        private String a_title;
        private String image;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(int department_id) {
            this.department_id = department_id;
        }

        public int getSection_id() {
            return section_id;
        }

        public void setSection_id(int section_id) {
            this.section_id = section_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getA_title() {
            return a_title;
        }

        public void setA_title(String a_title) {
            this.a_title = a_title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
    }
}
