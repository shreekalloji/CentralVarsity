package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class MyQuizzesPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"question":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","quiz_answer":"A","solution":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","id":"5","user_id":"1","quizs_id":"1","quiz_id":"1","option_id":"1","answer":"A","correct_answer":"A","answer_status":"correct","created_on":"2019-05-15 11:31:06","options":[{"id":"1","quiz_id":"1","options":"Ut enim ad minim veniam","created_on":"2019-05-08 02:02:11"},{"id":"2","quiz_id":"1","options":"quis nostrud exercitation ullamco","created_on":"2019-05-08 02:04:12"},{"id":"3","quiz_id":"1","options":"Duis aute irure dolor in reprehenderit","created_on":"2019-05-08 02:02:11"},{"id":"4","quiz_id":"1","options":"Excepteur sint occaecat cupidatat","created_on":"2019-05-08 02:04:12"}]}]
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
         * question : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * quiz_answer : A
         * solution : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * id : 5
         * user_id : 1
         * quizs_id : 1
         * quiz_id : 1
         * option_id : 1
         * answer : A
         * correct_answer : A
         * answer_status : correct
         * created_on : 2019-05-15 11:31:06
         * options : [{"id":"1","quiz_id":"1","options":"Ut enim ad minim veniam","created_on":"2019-05-08 02:02:11"},{"id":"2","quiz_id":"1","options":"quis nostrud exercitation ullamco","created_on":"2019-05-08 02:04:12"},{"id":"3","quiz_id":"1","options":"Duis aute irure dolor in reprehenderit","created_on":"2019-05-08 02:02:11"},{"id":"4","quiz_id":"1","options":"Excepteur sint occaecat cupidatat","created_on":"2019-05-08 02:04:12"}]
         */

        private String question;
        private String quiz_answer;
        private String solution;
        private String id;
        private String user_id;
        private String quizs_id;
        private String quiz_id;
        private String option_id;
        private String answer;
        private String correct_answer;
        private String answer_status;
        private String created_on;
        private List<OptionsBean> options;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getQuiz_answer() {
            return quiz_answer;
        }

        public void setQuiz_answer(String quiz_answer) {
            this.quiz_answer = quiz_answer;
        }

        public String getSolution() {
            return solution;
        }

        public void setSolution(String solution) {
            this.solution = solution;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getQuizs_id() {
            return quizs_id;
        }

        public void setQuizs_id(String quizs_id) {
            this.quizs_id = quizs_id;
        }

        public String getQuiz_id() {
            return quiz_id;
        }

        public void setQuiz_id(String quiz_id) {
            this.quiz_id = quiz_id;
        }

        public String getOption_id() {
            return option_id;
        }

        public void setOption_id(String option_id) {
            this.option_id = option_id;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getCorrect_answer() {
            return correct_answer;
        }

        public void setCorrect_answer(String correct_answer) {
            this.correct_answer = correct_answer;
        }

        public String getAnswer_status() {
            return answer_status;
        }

        public void setAnswer_status(String answer_status) {
            this.answer_status = answer_status;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
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
             * quiz_id : 1
             * options : Ut enim ad minim veniam
             * created_on : 2019-05-08 02:02:11
             */

            private String id;
            private String quiz_id;
            private String options;
            private String created_on;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getQuiz_id() {
                return quiz_id;
            }

            public void setQuiz_id(String quiz_id) {
                this.quiz_id = quiz_id;
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
