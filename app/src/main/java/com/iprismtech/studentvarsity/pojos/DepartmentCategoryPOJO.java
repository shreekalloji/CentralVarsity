package com.iprismtech.studentvarsity.pojos;

import java.util.List;

/**
 * Created by intel on 26-Nov-18.
 */

public class DepartmentCategoryPOJO {


    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"category_id":1,"department_id":1,"section_id":1,"title":"Mens Top Wear","a_title":"ملابس رجالي","image":"storage/img/5b8d3ff5eb6751535983605.jpg","created_at":"2018-09-03 07:27:11","updated_at":"2018-09-04 06:48:19","Subcategory":[{"id":2,"department_id":1,"section_id":1,"category_id":1,"title":"T-shirts","a_title":"تي شيرت","image":"storage/img/5b8e2064d67041536041060.jpg","created_at":"2018-09-04 06:11:34","updated_at":"2018-09-04 06:11:34"},{"id":3,"department_id":1,"section_id":1,"category_id":1,"title":"Casual Shirts","a_title":"قمصان","image":"storage/img/5b8e24ee64af61536042222.jpeg","created_at":"2018-09-07 06:18:35","updated_at":"0000-00-00 00:00:00"},{"id":4,"department_id":1,"section_id":1,"category_id":1,"title":"Formal Shirts","a_title":"قمصان رسمية","image":"storage/img/5c00ddfbd08b91543560699.jpeg","created_at":"2018-11-30 06:51:39","updated_at":"2018-11-30 12:21:39"}]},{"category_id":2,"department_id":1,"section_id":1,"title":"Mens Bottom Wear","a_title":"ملابس رجالية أسفل","image":"storage/img/5b8cf1e2f352b1535963618.jpg","created_at":"2018-09-03 08:33:39","updated_at":"2018-09-04 06:36:46","Subcategory":[{"id":5,"department_id":1,"section_id":1,"category_id":2,"title":"Jeans","a_title":"جينز","image":"storage/img/5c00dd4c44f0f1543560524.jpeg","created_at":"2018-11-30 12:18:44","updated_at":"0000-00-00 00:00:00"}]},{"category_id":4,"department_id":1,"section_id":1,"title":"Sports & Active Wear","a_title":"ملابس رياضية وفعالة","image":"storage/img/5b8e23d4611dc1536041940.jpg","created_at":"2018-09-04 06:19:00","updated_at":null,"Subcategory":[]},{"category_id":5,"department_id":1,"section_id":1,"title":"Mens Party Wear","a_title":"ملابس رجالي","image":"storage/img/5b8e246dcb44e1536042093.jpeg","created_at":"2018-09-04 06:21:33","updated_at":null,"Subcategory":[{"id":6,"department_id":1,"section_id":1,"category_id":5,"title":"party wear","a_title":"ارتداء الحزب","image":"storage/img/5c00dd77573931543560567.jpeg","created_at":"2018-11-30 12:19:27","updated_at":"0000-00-00 00:00:00"}]}]
     */

    private boolean status;
    private String message;
    /**
     * category_id : 1
     * department_id : 1
     * section_id : 1
     * title : Mens Top Wear
     * a_title : ملابس رجالي
     * image : storage/img/5b8d3ff5eb6751535983605.jpg
     * created_at : 2018-09-03 07:27:11
     * updated_at : 2018-09-04 06:48:19
     * Subcategory : [{"id":2,"department_id":1,"section_id":1,"category_id":1,"title":"T-shirts","a_title":"تي شيرت","image":"storage/img/5b8e2064d67041536041060.jpg","created_at":"2018-09-04 06:11:34","updated_at":"2018-09-04 06:11:34"},{"id":3,"department_id":1,"section_id":1,"category_id":1,"title":"Casual Shirts","a_title":"قمصان","image":"storage/img/5b8e24ee64af61536042222.jpeg","created_at":"2018-09-07 06:18:35","updated_at":"0000-00-00 00:00:00"},{"id":4,"department_id":1,"section_id":1,"category_id":1,"title":"Formal Shirts","a_title":"قمصان رسمية","image":"storage/img/5c00ddfbd08b91543560699.jpeg","created_at":"2018-11-30 06:51:39","updated_at":"2018-11-30 12:21:39"}]
     */

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
        private int category_id;
        private int department_id;
        private int section_id;
        private String title;
        private String a_title;
        private String image;
        private String created_at;
        private String updated_at;
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

        private List<SubcategoryBean> Subcategory;

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
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

        public List<SubcategoryBean> getSubcategory() {
            return Subcategory;
        }

        public void setSubcategory(List<SubcategoryBean> Subcategory) {
            this.Subcategory = Subcategory;
        }

        public static class SubcategoryBean {
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
}
