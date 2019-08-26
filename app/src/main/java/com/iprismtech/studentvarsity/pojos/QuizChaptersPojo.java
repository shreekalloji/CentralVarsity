package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class QuizChaptersPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"148","education_id":"3","stream_id":"3","year":"1","semester":"1","subject":"ENGINEERING PHYSICS  I","created_on":"2019-06-12 11:58:32","modified_on":null,"chapters":[{"id":"222","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","category":"quiz","chapter":"PHYSICS-1","created_on":"2019-06-25 12:58:52","modified_on":null,"topic_count":6,"topics_completed_count":3,"quiz":[{"id":"27","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"PHYSICAL SCIENCE","time":"01:30:00","created_on":"2019-06-27 15:34:38","modified_on":null},{"id":"22","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-1","time":"05:00:00","created_on":"2019-06-25 14:20:30","modified_on":null},{"id":"23","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-2","time":"01:00:00","created_on":"2019-06-25 14:21:19","modified_on":null},{"id":"24","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-3","time":"02:00:00","created_on":"2019-06-25 14:21:50","modified_on":null},{"id":"25","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-4","time":"02:00:00","created_on":"2019-06-25 14:22:18","modified_on":null},{"id":"26","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-5","time":"02:00:00","created_on":"2019-06-25 14:22:50","modified_on":null}]}]}]
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
         * id : 148
         * education_id : 3
         * stream_id : 3
         * year : 1
         * semester : 1
         * subject : ENGINEERING PHYSICS  I
         * created_on : 2019-06-12 11:58:32
         * modified_on : null
         * chapters : [{"id":"222","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","category":"quiz","chapter":"PHYSICS-1","created_on":"2019-06-25 12:58:52","modified_on":null,"topic_count":6,"topics_completed_count":3,"quiz":[{"id":"27","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"PHYSICAL SCIENCE","time":"01:30:00","created_on":"2019-06-27 15:34:38","modified_on":null},{"id":"22","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-1","time":"05:00:00","created_on":"2019-06-25 14:20:30","modified_on":null},{"id":"23","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-2","time":"01:00:00","created_on":"2019-06-25 14:21:19","modified_on":null},{"id":"24","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-3","time":"02:00:00","created_on":"2019-06-25 14:21:50","modified_on":null},{"id":"25","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-4","time":"02:00:00","created_on":"2019-06-25 14:22:18","modified_on":null},{"id":"26","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-5","time":"02:00:00","created_on":"2019-06-25 14:22:50","modified_on":null}]}]
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
             * id : 222
             * education_id : 3
             * stream_id : 3
             * year : 1
             * semester : 1
             * subject_id : 148
             * category : quiz
             * chapter : PHYSICS-1
             * created_on : 2019-06-25 12:58:52
             * modified_on : null
             * topic_count : 6
             * topics_completed_count : 3
             * quiz : [{"id":"27","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"PHYSICAL SCIENCE","time":"01:30:00","created_on":"2019-06-27 15:34:38","modified_on":null},{"id":"22","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-1","time":"05:00:00","created_on":"2019-06-25 14:20:30","modified_on":null},{"id":"23","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-2","time":"01:00:00","created_on":"2019-06-25 14:21:19","modified_on":null},{"id":"24","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-3","time":"02:00:00","created_on":"2019-06-25 14:21:50","modified_on":null},{"id":"25","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-4","time":"02:00:00","created_on":"2019-06-25 14:22:18","modified_on":null},{"id":"26","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-5","time":"02:00:00","created_on":"2019-06-25 14:22:50","modified_on":null}]
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
            private int topics_completed_count;
            private List<QuizBean> quiz;

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

            public int getTopics_completed_count() {
                return topics_completed_count;
            }

            public void setTopics_completed_count(int topics_completed_count) {
                this.topics_completed_count = topics_completed_count;
            }

            public List<QuizBean> getQuiz() {
                return quiz;
            }

            public void setQuiz(List<QuizBean> quiz) {
                this.quiz = quiz;
            }

            public static class QuizBean {
                /**
                 * id : 27
                 * education_id : 3
                 * stream_id : 3
                 * year : 1
                 * semester : 1
                 * subject_id : 148
                 * chapter_id : 222
                 * title : PHYSICAL SCIENCE
                 * time : 01:30:00
                 * created_on : 2019-06-27 15:34:38
                 * modified_on : null
                 */

                private String id;
                private String education_id;
                private String stream_id;
                private String year;
                private String semester;
                private String subject_id;
                private String chapter_id;
                private String title;
                private String time;
                private String created_on;
                private Object modified_on;

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

                public String getChapter_id() {
                    return chapter_id;
                }

                public void setChapter_id(String chapter_id) {
                    this.chapter_id = chapter_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
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
            }
        }
    }
}
