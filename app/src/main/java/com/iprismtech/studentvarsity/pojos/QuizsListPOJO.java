package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class QuizsListPOJO {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":"27","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"PHYSICAL SCIENCE","time":"01:30:00","created_on":"27 Jun 2019","modified_on":null,"right_answers":0,"wrong_answers":0,"total_questions":0,"questions_count":1},{"id":"22","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-1","time":"05:00:00","created_on":"25 Jun 2019","modified_on":null,"right_answers":0,"wrong_answers":0,"total_questions":0,"questions_count":5},{"id":"23","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-2","time":"01:00:00","created_on":"25 Jun 2019","modified_on":null,"right_answers":0,"wrong_answers":0,"total_questions":0,"questions_count":0},{"id":"24","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-3","time":"02:00:00","created_on":"25 Jun 2019","modified_on":null,"right_answers":0,"wrong_answers":0,"total_questions":0,"questions_count":0},{"id":"25","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-4","time":"02:00:00","created_on":"25 Jun 2019","modified_on":null,"right_answers":0,"wrong_answers":0,"total_questions":0,"questions_count":0},{"id":"26","education_id":"3","stream_id":"3","year":"1","semester":"1","subject_id":"148","chapter_id":"222","title":"Physics Quiz-5","time":"02:00:00","created_on":"25 Jun 2019","modified_on":null,"right_answers":0,"wrong_answers":0,"total_questions":0,"questions_count":0}]
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
         * id : 27
         * education_id : 3
         * stream_id : 3
         * year : 1
         * semester : 1
         * subject_id : 148
         * chapter_id : 222
         * title : PHYSICAL SCIENCE
         * time : 01:30:00
         * created_on : 27 Jun 2019
         * modified_on : null
         * right_answers : 0
         * wrong_answers : 0
         * total_questions : 0
         * questions_count : 1
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
        private int right_answers;
        private int wrong_answers;
        private int total_questions;
        private int questions_count;

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

        public int getRight_answers() {
            return right_answers;
        }

        public void setRight_answers(int right_answers) {
            this.right_answers = right_answers;
        }

        public int getWrong_answers() {
            return wrong_answers;
        }

        public void setWrong_answers(int wrong_answers) {
            this.wrong_answers = wrong_answers;
        }

        public int getTotal_questions() {
            return total_questions;
        }

        public void setTotal_questions(int total_questions) {
            this.total_questions = total_questions;
        }

        public int getQuestions_count() {
            return questions_count;
        }

        public void setQuestions_count(int questions_count) {
            this.questions_count = questions_count;
        }
    }
}
