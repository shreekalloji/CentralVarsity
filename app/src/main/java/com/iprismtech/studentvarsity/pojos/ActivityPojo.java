package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class ActivityPojo {


    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","question":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","answer":"A","solution":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","status":"1","created_on":"2019-05-03 03:02:04","modified_on":null,"subject":"Physics","chapter":"1.1 Physical World","pings":"1","comments":"0","commented":"yes","readed":"yes","views":"0","viewed":"yes","posted_on":"03 May 2019","mcq_options":[{"id":"1","mcq_id":"1","options":"Ut enim ad minim veniam","created_on":"2019-05-03 01:03:03"},{"id":"2","mcq_id":"1","options":"quis nostrud exercitation ullamco","created_on":"2019-05-03 02:06:09"},{"id":"3","mcq_id":"1","options":"laboris nisi ut aliquip ex ea commodo consequat","created_on":"2019-05-03 01:03:03"},{"id":"4","mcq_id":"1","options":"Duis aute irure dolor","created_on":"2019-05-03 02:06:09"}],"type":"mcqs"},{"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","question":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","answer":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","status":"1","created_on":"2019-05-02 03:06:03","modified_on":null,"subject":"Physics","chapter":"1.1 Physical World","pings":"1","comments":"0","commented":"yes","readed":"yes","views":"0","viewed":"yes","posted_on":"02 May 2019","type":"faqs"},{"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","heading":"commodo consequat.","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","status":"1","created_on":"2019-05-02 01:02:05","modified_on":null,"subject":"Physics","chapter":"1.1 Physical World","pings":"1","comments":"1","commented":"yes","readed":"yes","views":"1","viewed":"yes","posted_on":"02 May 2019","type":"notes"},{"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","heading":"Lorem ipsum dolor sit amet","video_link":"https://www.youtube.com/watch?v=IzQsYnLz2Gc","status":"1","created_on":"2019-05-02 01:02:03","modified_on":null,"subject":"Physics","chapter":"1.1 Physical World","pings":"1","comments":"0","commented":"yes","readed":"yes","views":"0","viewed":"yes","posted_on":"02 May 2019","type":"videos"},{"id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","quizs_id":"1","question":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","answer":"A","solution":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","marks":"10","status":"1","created_on":"2019-05-08 01:04:01","modified_on":null,"subject":"Physics","chapter":"1.1 Physical World","pings":"1","comments":"0","commented":"no","readed":"yes","views":"0","viewed":"no","posted_on":"08 May 2019","quiz_options":[{"id":"1","quiz_id":"1","options":"Ut enim ad minim veniam","created_on":"2019-05-08 02:02:11"},{"id":"2","quiz_id":"1","options":"quis nostrud exercitation ullamco","created_on":"2019-05-08 02:04:12"},{"id":"3","quiz_id":"1","options":"Duis aute irure dolor in reprehenderit","created_on":"2019-05-08 02:02:11"},{"id":"4","quiz_id":"1","options":"Excepteur sint occaecat cupidatat","created_on":"2019-05-08 02:04:12"}],"type":"quiz"},{"id":"1","user_id":"1","education_id":"3","stream_id":"4","subject_id":"3","chapter_id":"1","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","image":"storage/profile_pics/5ce27d6bc8084.","youtube":"","status":"1","created_on":"2019-05-20 15:41:55","modified_on":null,"subject":"Physics","chapter":"1.1 Physical World","pings":"1","comments":"0","commented":"no","readed":"yes","views":"0","viewed":"no","posted_on":"20 May 2019","type":"activity"}]
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
         * subject : Physics
         * chapter : 1.1 Physical World
         * pings : 1
         * comments : 0
         * commented : yes
         * readed : yes
         * views : 0
         * viewed : yes
         * posted_on : 03 May 2019
         * mcq_options : [{"id":"1","mcq_id":"1","options":"Ut enim ad minim veniam","created_on":"2019-05-03 01:03:03"},{"id":"2","mcq_id":"1","options":"quis nostrud exercitation ullamco","created_on":"2019-05-03 02:06:09"},{"id":"3","mcq_id":"1","options":"laboris nisi ut aliquip ex ea commodo consequat","created_on":"2019-05-03 01:03:03"},{"id":"4","mcq_id":"1","options":"Duis aute irure dolor","created_on":"2019-05-03 02:06:09"}]
         * type : mcqs
         * heading : commodo consequat.
         * description : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
         * video_link : https://www.youtube.com/watch?v=IzQsYnLz2Gc
         * quizs_id : 1
         * marks : 10
         * quiz_options : [{"id":"1","quiz_id":"1","options":"Ut enim ad minim veniam","created_on":"2019-05-08 02:02:11"},{"id":"2","quiz_id":"1","options":"quis nostrud exercitation ullamco","created_on":"2019-05-08 02:04:12"},{"id":"3","quiz_id":"1","options":"Duis aute irure dolor in reprehenderit","created_on":"2019-05-08 02:02:11"},{"id":"4","quiz_id":"1","options":"Excepteur sint occaecat cupidatat","created_on":"2019-05-08 02:04:12"}]
         * user_id : 1
         * image : storage/profile_pics/5ce27d6bc8084.
         * youtube :
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
        private String subject;
        private String chapter;
        private String pings;
        private String comments;
        private String commented;
        private String readed;
        private String views;
        private String viewed;
        private String posted_on;
        private String type;
        private String heading;
        private String description;
        private String video_link;
        private String quizs_id;
        private String marks;
        private String user_id;
        private String image;
        private String youtube;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

        private List<McqOptionsBean> mcq_options;
        private List<QuizOptionsBean> quiz_options;

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

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
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

        public String getPosted_on() {
            return posted_on;
        }

        public void setPosted_on(String posted_on) {
            this.posted_on = posted_on;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getHeading() {
            return heading;
        }

        public void setHeading(String heading) {
            this.heading = heading;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideo_link() {
            return video_link;
        }

        public void setVideo_link(String video_link) {
            this.video_link = video_link;
        }

        public String getQuizs_id() {
            return quizs_id;
        }

        public void setQuizs_id(String quizs_id) {
            this.quizs_id = quizs_id;
        }

        public String getMarks() {
            return marks;
        }

        public void setMarks(String marks) {
            this.marks = marks;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getYoutube() {
            return youtube;
        }

        public void setYoutube(String youtube) {
            this.youtube = youtube;
        }

        public List<McqOptionsBean> getMcq_options() {
            return mcq_options;
        }

        public void setMcq_options(List<McqOptionsBean> mcq_options) {
            this.mcq_options = mcq_options;
        }

        public List<QuizOptionsBean> getQuiz_options() {
            return quiz_options;
        }

        public void setQuiz_options(List<QuizOptionsBean> quiz_options) {
            this.quiz_options = quiz_options;
        }

        public static class McqOptionsBean {
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

        public static class QuizOptionsBean {
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
