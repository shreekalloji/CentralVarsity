package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class TahybaPOJO {

    /**
     * status : true
     * message : review found for you!
     * response : {"Reviews":[{"id":"16","shop_id":"vw19014418","user_id":"app190320160901","reviews":"Bdhdhdh","rating":"4.5","status":"0","created_on":"2019-04-04 11:22:41","full_name":"mahesh","image":"storage/default-no-image.png"},{"id":"10","shop_id":"vw19014418","user_id":"app181228194242","reviews":"Test","rating":"3.5","status":"0","created_on":"2019-04-03 19:03:22","full_name":"kadeer","image":"storage/user_pf_doc/5c262f59acc58.png"}],"Ratings":[{"RatedTime":"7","avgRate":"4.0","rate1":"0","rate2":"0","rate3":"0","rate4":"0","rate5":"2"}]}
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
        private List<ReviewsBean> Reviews;
        private List<RatingsBean> Ratings;

        public List<ReviewsBean> getReviews() {
            return Reviews;
        }

        public void setReviews(List<ReviewsBean> Reviews) {
            this.Reviews = Reviews;
        }

        public List<RatingsBean> getRatings() {
            return Ratings;
        }

        public void setRatings(List<RatingsBean> Ratings) {
            this.Ratings = Ratings;
        }

        public static class ReviewsBean {
            /**
             * id : 16
             * shop_id : vw19014418
             * user_id : app190320160901
             * reviews : Bdhdhdh
             * rating : 4.5
             * status : 0
             * created_on : 2019-04-04 11:22:41
             * full_name : mahesh
             * image : storage/default-no-image.png
             */

            private String id;
            private String shop_id;
            private String user_id;
            private String reviews;
            private String rating;
            private String status;
            private String created_on;
            private String full_name;
            private String image;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getReviews() {
                return reviews;
            }

            public void setReviews(String reviews) {
                this.reviews = reviews;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreated_on() {
                return created_on;
            }

            public void setCreated_on(String created_on) {
                this.created_on = created_on;
            }

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class RatingsBean {
            /**
             * RatedTime : 7
             * avgRate : 4.0
             * rate1 : 0
             * rate2 : 0
             * rate3 : 0
             * rate4 : 0
             * rate5 : 2
             */

            private String RatedTime;
            private String avgRate;
            private String rate1;
            private String rate2;
            private String rate3;
            private String rate4;
            private String rate5;

            public String getRatedTime() {
                return RatedTime;
            }

            public void setRatedTime(String RatedTime) {
                this.RatedTime = RatedTime;
            }

            public String getAvgRate() {
                return avgRate;
            }

            public void setAvgRate(String avgRate) {
                this.avgRate = avgRate;
            }

            public String getRate1() {
                return rate1;
            }

            public void setRate1(String rate1) {
                this.rate1 = rate1;
            }

            public String getRate2() {
                return rate2;
            }

            public void setRate2(String rate2) {
                this.rate2 = rate2;
            }

            public String getRate3() {
                return rate3;
            }

            public void setRate3(String rate3) {
                this.rate3 = rate3;
            }

            public String getRate4() {
                return rate4;
            }

            public void setRate4(String rate4) {
                this.rate4 = rate4;
            }

            public String getRate5() {
                return rate5;
            }

            public void setRate5(String rate5) {
                this.rate5 = rate5;
            }
        }
    }
}
