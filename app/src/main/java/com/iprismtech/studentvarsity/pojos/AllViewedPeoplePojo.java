package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class AllViewedPeoplePojo {

    private List<ViewsBean> views;

    public List<ViewsBean> getViews() {
        return views;
    }

    public void setViews(List<ViewsBean> views) {
        this.views = views;
    }

    public static class ViewsBean {
        /**
         * name : Smith Max
         * mobile : 8888888888
         * profile_pic : storage/profile_pics/5cc052c7c35f5.
         * online_status : Online
         */

        private String name;
        private String mobile;
        private String profile_pic;
        private String online_status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getOnline_status() {
            return online_status;
        }

        public void setOnline_status(String online_status) {
            this.online_status = online_status;
        }
    }
}
