package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class MCQsPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","question":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","answer":"A","solution":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","status":"1","created_on":"2019-05-03 03:02:04","modified_on":null,"chapter":"1.1 Physical World","pings":"1","comments":"0","commented":"no","readed":"yes","views":"0","viewed":"no","options":[{"id":"1","mcq_id":"1","options":"Ut enim ad minim veniam","created_on":"2019-05-03 01:03:03"},{"id":"2","mcq_id":"1","options":"quis nostrud exercitation ullamco","created_on":"2019-05-03 02:06:09"},{"id":"3","mcq_id":"1","options":"laboris nisi ut aliquip ex ea commodo consequat","created_on":"2019-05-03 01:03:03"},{"id":"4","mcq_id":"1","options":"Duis aute irure dolor","created_on":"2019-05-03 02:06:09"}]}]
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
         * education_id : 3
         * stream_id : 4
         * subject_id : 3
         * chapter_id : 1
         * question : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * answer : A
         * solution : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * status : 1
         * created_on : 2019-05-03 03:02:04
         * modified_on : null
         * chapter : 1.1 Physical World
         * pings : 1
         * comments : 0
         * commented : no
         * readed : yes
         * views : 0
         * viewed : no
         * options : [{"id":"1","mcq_id":"1","options":"Ut enim ad minim veniam","created_on":"2019-05-03 01:03:03"},{"id":"2","mcq_id":"1","options":"quis nostrud exercitation ullamco","created_on":"2019-05-03 02:06:09"},{"id":"3","mcq_id":"1","options":"laboris nisi ut aliquip ex ea commodo consequat","created_on":"2019-05-03 01:03:03"},{"id":"4","mcq_id":"1","options":"Duis aute irure dolor","created_on":"2019-05-03 02:06:09"}]
         */

        private String id;
        private String education_id;
        private String stream_id;
        private String subject_id;
        private String chapter_id;
        private String question;
        private String answer;
        private String solution;
        private String status;
        private String created_on;
        private Object modified_on;
        private String chapter;
        private String pings;
        private String comments;
        private String commented;
        private String readed;
        private String views;
        private String viewed;
        private List<OptionsBean> options;

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

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getSolution() {
            return solution;
        }

        public void setSolution(String solution) {
            this.solution = solution;
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

        public Object getModified_on() {
            return modified_on;
        }

        public void setModified_on(Object modified_on) {
            this.modified_on = modified_on;
        }

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
        }

        public String getPings() {
            return pings;
        }

        public void setPings(String pings) {
            this.pings = pings;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getCommented() {
            return commented;
        }

        public void setCommented(String commented) {
            this.commented = commented;
        }

        public String getReaded() {
            return readed;
        }

        public void setReaded(String readed) {
            this.readed = readed;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getViewed() {
            return viewed;
        }

        public void setViewed(String viewed) {
            this.viewed = viewed;
        }

        public List<OptionsBean> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsBean> options) {
            this.options = options;
        }

        public static class OptionsBean {
            /**
             * id : 1
             * mcq_id : 1
             * options : Ut enim ad minim veniam
             * created_on : 2019-05-03 01:03:03
             */

            private String id;
            private String mcq_id;
            private String options;
            private String created_on;

            private String right_attempt;

            public String getSelected_option() {
                return selected_option;
            }

            public void setSelected_option(String selected_option) {
                this.selected_option = selected_option;
            }

            private String selected_option;

            public String getRight_attempt() {
                return right_attempt;
            }

            public void setRight_attempt(String right_attempt) {
                this.right_attempt = right_attempt;
            }


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMcq_id() {
                return mcq_id;
            }

            public void setMcq_id(String mcq_id) {
                this.mcq_id = mcq_id;
            }

            public String getOptions() {
                return options;
            }

            public void setOptions(String options) {
                this.options = options;
            }

            public String getCreated_on() {
                return created_on;
            }

            public void setCreated_on(String created_on) {
                this.created_on = created_on;
            }
        }
    }
}
