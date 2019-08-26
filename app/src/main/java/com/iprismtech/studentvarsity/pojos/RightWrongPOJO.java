package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class RightWrongPOJO {


    /**
     * status : true
     * message : Data fetched Successfully!
     * right_answers : [{"question":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","quiz_answer":"A","solution":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","id":"3","user_id":"1","quizs_id":"1","quiz_id":"1","option_id":"1","answer":"A","correct_answer":"A","answer_status":"correct","created_on":"2019-05-15 10:36:15","options":[{"id":"1","quiz_id":"1","options":"Ut enim ad minim veniam","created_on":"2019-05-08 02:02:11"},{"id":"2","quiz_id":"1","options":"quis nostrud exercitation ullamco","created_on":"2019-05-08 02:04:12"},{"id":"3","quiz_id":"1","options":"Duis aute irure dolor in reprehenderit","created_on":"2019-05-08 02:02:11"},{"id":"4","quiz_id":"1","options":"Excepteur sint occaecat cupidatat","created_on":"2019-05-08 02:04:12"}]}]
     * wrong_answers : [{"question":"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium","quiz_answer":"B","solution":"Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt","id":"4","user_id":"1","quizs_id":"1","quiz_id":"2","option_id":"6","answer":"A","correct_answer":"B","answer_status":"wrong","created_on":"2019-05-15 15:52:39","options":[{"id":"5","quiz_id":"2","options":"totam rem aperiam","created_on":"2019-05-09 00:00:00"},{"id":"6","quiz_id":"2","options":"eaque ipsa quae ab illo","created_on":"2019-05-09 00:00:00"},{"id":"7","quiz_id":"2","options":"Neque porro quisquam ","created_on":"2019-05-09 00:00:00"},{"id":"8","quiz_id":"2","options":"rem aperiam","created_on":"2019-05-09 00:00:00"}]}]
     * quiz_details : {"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"5","title":"Quiz1","time":"00:30:00","created_on":"2019-05-07 04:06:01","modified_on":null}
     * total_marks : 2
     * right_answers_count : 1
     */

    private boolean status;
    private String message;
    private QuizDetailsBean quiz_details;
    private int total_marks;
    private int right_answers_count;
    private List<RightAnswersBean> right_answers;
    private List<WrongAnswersBean> wrong_answers;

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

    public QuizDetailsBean getQuiz_details() {
        return quiz_details;
    }

    public void setQuiz_details(QuizDetailsBean quiz_details) {
        this.quiz_details = quiz_details;
    }

    public int getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }

    public int getRight_answers_count() {
        return right_answers_count;
    }

    public void setRight_answers_count(int right_answers_count) {
        this.right_answers_count = right_answers_count;
    }

    public List<RightAnswersBean> getRight_answers() {
        return right_answers;
    }

    public void setRight_answers(List<RightAnswersBean> right_answers) {
        this.right_answers = right_answers;
    }

    public List<WrongAnswersBean> getWrong_answers() {
        return wrong_answers;
    }

    public void setWrong_answers(List<WrongAnswersBean> wrong_answers) {
        this.wrong_answers = wrong_answers;
    }

    public static class QuizDetailsBean {
        /**
         * id : 1
         * education_id : 3
         * stream_id : 4
         * subject_id : 3
         * chapter_id : 5
         * title : Quiz1
         * time : 00:30:00
         * created_on : 2019-05-07 04:06:01
         * modified_on : null
         */

        private String id;
        private String education_id;
        private String stream_id;
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

    public static class RightAnswersBean {
        /**
         * question : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * quiz_answer : A
         * solution : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * id : 3
         * user_id : 1
         * quizs_id : 1
         * quiz_id : 1
         * option_id : 1
         * answer : A
         * correct_answer : A
         * answer_status : correct
         * created_on : 2019-05-15 10:36:15
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

    public static class WrongAnswersBean {
        /**
         * question : Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium
         * quiz_answer : B
         * solution : Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt
         * id : 4
         * user_id : 1
         * quizs_id : 1
         * quiz_id : 2
         * option_id : 6
         * answer : A
         * correct_answer : B
         * answer_status : wrong
         * created_on : 2019-05-15 15:52:39
         * options : [{"id":"5","quiz_id":"2","options":"totam rem aperiam","created_on":"2019-05-09 00:00:00"},{"id":"6","quiz_id":"2","options":"eaque ipsa quae ab illo","created_on":"2019-05-09 00:00:00"},{"id":"7","quiz_id":"2","options":"Neque porro quisquam ","created_on":"2019-05-09 00:00:00"},{"id":"8","quiz_id":"2","options":"rem aperiam","created_on":"2019-05-09 00:00:00"}]
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
        private List<OptionsBeanX> options;

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

        public List<OptionsBeanX> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsBeanX> options) {
            this.options = options;
        }

        public static class OptionsBeanX {
            /**
             * id : 5
             * quiz_id : 2
             * options : totam rem aperiam
             * created_on : 2019-05-09 00:00:00
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
