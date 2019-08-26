package com.iprismtech.studentvarsity.pojos;

public class AddBannerPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * scrolling_text : {"id":"3","education_id":"3","stream_id":"4","year":"1","semester":"1","subject_id":"3","scrolling_text":"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo....","url":"https://www.google.com/","created_on":"2019-06-03 14:41:15","modified_on":"2019-06-12 15:07:19"}
     * adv_banners : {"id":"2","education_id":"3","stream_id":"4","year":"1","semester":"1","subject_id":"3","title":"Computer Science (CSE)","youtube_url":"https://www.youtube.com/watch?v=HTcmrHzrayc","image":"storage/images/1.jpg","advt_url":"","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...","type":"image","created_on":"2019-06-03 15:56:18","modified_on":"2019-06-03 16:03:24"}
     */

    private boolean status;
    private String message;
    private ScrollingTextBean scrolling_text;
    private AdvBannersBean adv_banners;

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

    public ScrollingTextBean getScrolling_text() {
        return scrolling_text;
    }

    public void setScrolling_text(ScrollingTextBean scrolling_text) {
        this.scrolling_text = scrolling_text;
    }

    public AdvBannersBean getAdv_banners() {
        return adv_banners;
    }

    public void setAdv_banners(AdvBannersBean adv_banners) {
        this.adv_banners = adv_banners;
    }

    public static class ScrollingTextBean {
        /**
         * id : 3
         * education_id : 3
         * stream_id : 4
         * year : 1
         * semester : 1
         * subject_id : 3
         * scrolling_text : Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo....
         * url : https://www.google.com/
         * created_on : 2019-06-03 14:41:15
         * modified_on : 2019-06-12 15:07:19
         */

        private String id;
        private String education_id;
        private String stream_id;
        private String year;
        private String semester;
        private String subject_id;
        private String scrolling_text;
        private String url;
        private String created_on;
        private String modified_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getScrolling_text() {
            return scrolling_text;
        }

        public void setScrolling_text(String scrolling_text) {
            this.scrolling_text = scrolling_text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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
    }

    public static class AdvBannersBean {
        /**
         * id : 2
         * education_id : 3
         * stream_id : 4
         * year : 1
         * semester : 1
         * subject_id : 3
         * title : Computer Science (CSE)
         * youtube_url : https://www.youtube.com/watch?v=HTcmrHzrayc
         * image : storage/images/1.jpg
         * advt_url :
         * description : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...
         * type : image
         * created_on : 2019-06-03 15:56:18
         * modified_on : 2019-06-03 16:03:24
         */

        private String id;
        private String education_id;
        private String stream_id;
        private String year;
        private String semester;
        private String subject_id;
        private String title;
        private String youtube_url;
        private String image;
        private String advt_url;
        private String description;
        private String type;
        private String created_on;
        private String modified_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYoutube_url() {
            return youtube_url;
        }

        public void setYoutube_url(String youtube_url) {
            this.youtube_url = youtube_url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAdvt_url() {
            return advt_url;
        }

        public void setAdvt_url(String advt_url) {
            this.advt_url = advt_url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
    }
}
