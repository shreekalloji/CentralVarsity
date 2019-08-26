package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class ChaptersPojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"3","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Physics","created_on":"2019-04-25 02:04:01","modified_on":null,"chapters":[{"id":"1","education_id":"3","stream_id":"4","year":"2","semester":"1","subject_id":"3","category":"notes","chapter":"1.1 Physical World","created_on":"2019-04-27 02:06:01","modified_on":null,"topic_count":1,"read_count":1},{"id":"2","education_id":"3","stream_id":"4","year":"2","semester":"1","subject_id":"3","category":"notes","chapter":"1.2 Units and Measurements","created_on":"2019-04-27 01:03:13","modified_on":null,"topic_count":0,"read_count":0},{"id":"3","education_id":"3","stream_id":"4","year":"2","semester":"1","subject_id":"3","category":"notes","chapter":"1.3 Motion in Straight Line","created_on":"2019-04-27 02:06:01","modified_on":null,"topic_count":0,"read_count":0},{"id":"4","education_id":"3","stream_id":"4","year":"2","semester":"1","subject_id":"3","category":"notes","chapter":"1.4 Motion in a plane","created_on":"2019-04-27 01:03:13","modified_on":null,"topic_count":0,"read_count":0}]},{"id":"2","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Chemistry","created_on":"2019-04-25 02:10:02","modified_on":null,"chapters":[]},{"id":"1","education_id":"3","stream_id":"4","year":"2","semester":"1","subject":"Calculus and Diffrential Equations","created_on":"2019-04-25 02:04:01","modified_on":null,"chapters":[]}]
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
         * id : 3
         * education_id : 3
         * stream_id : 4
         * year : 2
         * semester : 1
         * subject : Physics
         * created_on : 2019-04-25 02:04:01
         * modified_on : null
         * chapters : [{"id":"1","education_id":"3","stream_id":"4","year":"2","semester":"1","subject_id":"3","category":"notes","chapter":"1.1 Physical World","created_on":"2019-04-27 02:06:01","modified_on":null,"topic_count":1,"read_count":1},{"id":"2","education_id":"3","stream_id":"4","year":"2","semester":"1","subject_id":"3","category":"notes","chapter":"1.2 Units and Measurements","created_on":"2019-04-27 01:03:13","modified_on":null,"topic_count":0,"read_count":0},{"id":"3","education_id":"3","stream_id":"4","year":"2","semester":"1","subject_id":"3","category":"notes","chapter":"1.3 Motion in Straight Line","created_on":"2019-04-27 02:06:01","modified_on":null,"topic_count":0,"read_count":0},{"id":"4","education_id":"3","stream_id":"4","year":"2","semester":"1","subject_id":"3","category":"notes","chapter":"1.4 Motion in a plane","created_on":"2019-04-27 01:03:13","modified_on":null,"topic_count":0,"read_count":0}]
         */

        private String id;
        private String education_id;
        private String stream_id;
        private String year;
        private String semester;
        private String subject;
        private String created_on;
        private Object modified_on;
        private List<ChaptersBean> chapters;

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

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public Object getModified_on() {
            return modified_on;
        }

        public void setModified_on(Object modified_on) {
            this.modified_on = modified_on;
        }

        public List<ChaptersBean> getChapters() {
            return chapters;
        }

        public void setChapters(List<ChaptersBean> chapters) {
            this.chapters = chapters;
        }

        public static class ChaptersBean {
            /**
             * id : 1
             * education_id : 3
             * stream_id : 4
             * year : 2
             * semester : 1
             * subject_id : 3
             * category : notes
             * chapter : 1.1 Physical World
             * created_on : 2019-04-27 02:06:01
             * modified_on : null
             * topic_count : 1
             * read_count : 1
             */

            private String id;
            private String education_id;
            private String stream_id;
            private String year;
            private String semester;
            private String subject_id;
            private String category;
            private String chapter;
            private String created_on;
            private Object modified_on;
            private int topic_count;
            private int read_count;

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

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getChapter() {
                return chapter;
            }

            public void setChapter(String chapter) {
                this.chapter = chapter;
            }

            public String getCreated_on() {
                return created_on;
            }

            public void setCreated_on(String created_on) {
                this.created_on = created_on;
            }

            public Object getModified_on() {
                return modified_on;
            }

            public void setModified_on(Object modified_on) {
                this.modified_on = modified_on;
            }

            public int getTopic_count() {
                return topic_count;
            }

            public void setTopic_count(int topic_count) {
                this.topic_count = topic_count;
            }

            public int getRead_count() {
                return read_count;
            }

            public void setRead_count(int read_count) {
                this.read_count = read_count;
            }
        }
    }
}
