package com.iprismtech.studentvarsity.network.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.ACCEPT_FRD_REQUEST;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.ACTIVITY_DATA;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.ACTIVITY_DETAILS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.ADD_PARTICIPANTS_MEMBERS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.ADD_PARTICIPANTS_TO_GROUP;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.ADV_BANNERS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.ALLPRODUCTS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.CHANGE_SUBJECTS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.COMPLETE_REGISTRATION_PROCESS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.COUNTRY_WISE_NATIONALITES;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.CREATE_ACTIVITY;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.CREATE_POST;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.DEPARTMENTS_CALL;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.DEPARTMENTS_CATEGORY;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.DEPARTMENTS_SECTION;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.DISCUSSIONS_DATA;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.EDUCATION;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.FAQS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.FORGOT_PASSWORD;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.FRIEND_REQUEST;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.FRIEND_REQUEST_SENT;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GETCART;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GETPRODUCTS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_CHAPTERS_DATA;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_COMMENTS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_DISCUSS_VIEW;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_GROUP_MEMBERS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_MCQS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_NOTES_DATA;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_NOTES_VIEW;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_NOTOFICATIONS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_USERS_FROM_CONTACTS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_USERS_USING_UNIVERSITY;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_VIDEOS_DATA;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.HISTORY;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.HISTORY_FETCH;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.MCQS_VIEW;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.MY_ACTIVITY_POSTS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.MY_FRIENDS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.MY_POSTED_DISCUSSIONS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.MY_POSTED_SUGGESTIONS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.MY_QUIZZES;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.NATIONALITIES;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.QUIZS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.QUIZ_LIST;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.REMOVE_PARTICIPANT;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.RIGHT_WRONG_ATTEMPTS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SEARCH_UNIVERSITIES;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.CREATE_GROUP;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SEND_FRIEND_REQUEST;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SHOP_REVIEWS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.STREAMS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.STREAMS_YEARS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBJECTS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBMIT_AS_READ;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBMIT_CASE_DETAILS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBMIT_COMMENT;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBMIT_COMMENT_REPLY;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBMIT_FEEDBACK;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBMIT_PING;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBMIT_QUIZ_ANSWERS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBMIT_SUGGESTION;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUBMIT_VIEW;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUGGESTIONS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.SUMMARY;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.UNSEEN_NOTIFICATION;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.UPDATE_COVER_PIC;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.UPDATE_NOTIFICATION_STATUS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.UPDATE_PASSWORD;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.UPDATE_PROFILE;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.UPDATE_PROFILE_PIC;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.UPDATE_TOKEN;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.GET_PING_GROUPS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.USER_PROFILE;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.USER_REGISTER;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.VIDEOS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.VIEW_ALL_COMMENT_REPLIES;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.VIEW_ALL_REPLIES;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.VIEW_FAQS;
import static com.iprismtech.studentvarsity.network.constants.NetworkConstants.RequestCode.VIEW_VIDEOS;


public interface NetworkConstants {
    String PAGE = "?page=";
    String LIMIT = "&limit=";

    public static final String DEVELOPER_KEY = "AIzaSyA60BDL4qE5zPlej1Wq1jcrpcpaD5hl1us";

    interface Headers {
        String X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String BASIC_AUTH_TOKEN = "Authorization";
    }

    /**
     * This is the network request to all apis.
     */
    interface URL {
        //String PRIMARY_BASE_URL = BuildConfig.BASE_URL;
        String Imagepath_URL = "http://appilabz.com/centralvarsity/";
        //  String Imagepath_URL = "http://iprismconstructions.com/centralvarsity/";


        // String BASE_URL = "http://iprismconstructions.com/centralvarsity/app/ws/";
        String BASE_URL = "http://appilabz.com/centralvarsity/app/ws/";

        String SHOP_REVIEWS = BASE_URL + "shop_reviews";
        String TOKEN = BASE_URL + "get_token";
        String USER_LOGIN = BASE_URL + "user_login";
        String USER_REGISTER = BASE_URL + "register_user";
        String EDUCATION = BASE_URL + "education";
        String STREAMS = BASE_URL + "streams";
        String STREAMS_YEARS = BASE_URL + "streams_years";
        String SUBJECTS = BASE_URL + "subjects";
        String COMPLETE_REGISTRATION_PROCESS = BASE_URL + "complete_registration_process";
        String VIDEOS = BASE_URL + "videos";
        String VIEW_VIDEOS = BASE_URL + "view_video";
        String SUBMIT_COMMENT = BASE_URL + "submit_comment";
        String SUBMIT_VIEW = BASE_URL + "submit_as_view";
        String SUBMIT_AS_READ = BASE_URL + "submit_as_read";
        String FAQS = BASE_URL + "faqs";
        String VIEW_FAQS = BASE_URL + "view_faq";
        String GET_USERS_FROM_CONTACTS = BASE_URL + "get_users_from_contacts";
        String GET_USERS_USING_UNIVERSITY = BASE_URL + "get_users_using_university";
        String SEND_FRIEND_REQUEST = BASE_URL + "send_friend_request";
        String ACCEPT_FRD_REQUEST = BASE_URL + "accept_friend_request";
        String FRIEND_REQUEST = BASE_URL + "friend_requests";
        String FRIEND_REQUEST_SENT = BASE_URL + "friend_requests_sent";
        String MY_FRIENDS = BASE_URL + "my_friends";
        String MY_QUIZZES = BASE_URL + "my_quizzes";
        String MY_ACTIVITY_POSTS = BASE_URL + "my_activity_posts";
        String MY_POSTED_DISCUSSIONS = BASE_URL + "my_posted_discussions";
        String MY_POSTED_SUGGESTIONS = BASE_URL + "my_posted_suggestions";
        String QUIZS = BASE_URL + "quizs";
        String QUIZ_LIST = BASE_URL + "quiz_list";
        String GET_VIDEOS_SECTION = BASE_URL + "video_notes";
        String GET_NOTES_DATA_SECTION = BASE_URL + "notes";
        String GET_NOTES_DATA_VIEW_DESC = BASE_URL + "view_notes";
        String GET_CHAPTERS_DATA = BASE_URL + "chapters";
        String GET_MCQS_DATA = BASE_URL + "mcqs";
        String SEARCH_UNIVERSITIES = BASE_URL + "search_universities";
        String CRATE_NEW_GROUP = BASE_URL + "create_group";
        String GET_PIBNG_GROUPS = BASE_URL + "groups";
        String GET_ACTIVITY_DATA = BASE_URL + "activity";
        String GET_VIEW_ALL_REPLIES = BASE_URL + "comments";
        String VIEW_ALL_COMMENT_REPLIES = BASE_URL + "comment_replies";
        String GET_DISCUSSIONS_DATA = BASE_URL + "discussions";
        String GET_DISCUSSIONS_VIEW_DATA = BASE_URL + "view_discuss";
        String SUBMIT_PING = BASE_URL + "submit_ping";
        String MCQS_VIEW = BASE_URL + "view_mcq";
        String SUBMIT_COMMENT_REPLY = BASE_URL + "submit_comment_reply";
        String GET_NOTOFICATIONS = BASE_URL + "notifications";
        String ACTIVITY_DETAILS = BASE_URL + "view_activity_post";
        String SUBMIT_USERS_ONLINE_STATUS = BASE_URL + "submit_users_online_status";

        String DEPARTMENTS_CALL = BASE_URL + "departments";
        String DEPARTMENTS_SECTION = BASE_URL + "sections";
        String DEPARTMENTS_CATEGORY = BASE_URL + "category";
        String UPDATE_PROFILE = BASE_URL + "update_profile";
        String ALLPRODUCTS = BASE_URL + "all_products";
        String GETPRODUCTS = BASE_URL + "get_products";
        String GETCART = BASE_URL + "get_cart";
        String GET_GROUP_MEMBERS = BASE_URL + "group_members";
        String GET_PARTCIPANT_MEMBERS = BASE_URL + "find_participants";
        String ADD_PARTCIPANT_TO_GROUP_MEMBERS = BASE_URL + "add_participants";
        String REMOVE_PARTCIPANT = BASE_URL + "remove_participants";


        String NATIONALITIES = BASE_URL + "nationalities";
        String SUBMIT_CASE_DETAILS = BASE_URL + "submit_case_details";
        String HISTORY = BASE_URL + "history";
        String COUNTRY_WISE_NATIONALITES = BASE_URL + "nationalities";


        String SUBMIT_QUIZ_ANSWERS = BASE_URL + "submit_quiz_answers";
        String RIGHT_WRONG_ATTEMPTS = BASE_URL + "right_wrong_attempts";
        String SUMMARY = BASE_URL + "summary";
        String SUBMIT_SUGGESTION = BASE_URL + "submit_suggestion";
        String SUGGESTIONS = BASE_URL + "suggestions";
        String SUBMIT_FEEDBACK = BASE_URL + "submit_feedback";
        String UPDATE_NOTIFICATION_STATUS = BASE_URL + "update_notification_status";
        String CHANGE_SUBJECTS = BASE_URL + "change_subjects";
        String CREATE_ACTIVITY = BASE_URL + "create_activity";
        String UPDATE_COVER_PIC = BASE_URL + "update_cover_pic";
        String UPDATE_PROFILE_PIC = BASE_URL + "update_profile_pic";
        String USER_PROFILE = BASE_URL + "user_profile";
        String FORGOT_PASSWORD = BASE_URL + "forgot_password";
        String UPDATE_PASSWORD = BASE_URL + "update_password";
        String CREATE_POST = BASE_URL + "create_post";
        String GET_COMMENTS = BASE_URL + "comments";
        String ADV_BANNERS = BASE_URL + "adv_banners";

        String UPDATE_USER_TOKEN = BASE_URL + "update_device_token";
        String UNSEEN_NOTIFICATION = BASE_URL + "unseen_notifications";


    }

    /**
     * Application Controller events ids
     * Maintain all app level event ids and def of that event ids
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({GET_VIDEOS_DATA, GET_PING_GROUPS, CREATE_GROUP, GET_NOTES_DATA, GET_NOTES_VIEW, GET_MCQS, GET_CHAPTERS_DATA, HISTORY_FETCH,
            NATIONALITIES, SUBMIT_CASE_DETAILS, HISTORY, COUNTRY_WISE_NATIONALITES, USER_REGISTER, DEPARTMENTS_CALL, DEPARTMENTS_SECTION,
            DEPARTMENTS_CATEGORY, UPDATE_PROFILE, ALLPRODUCTS, GETPRODUCTS, GETCART, EDUCATION, SHOP_REVIEWS, SEARCH_UNIVERSITIES,
            QUIZ_LIST, QUIZS, MY_FRIENDS, FRIEND_REQUEST_SENT, FRIEND_REQUEST, SEND_FRIEND_REQUEST, GET_USERS_USING_UNIVERSITY,
            GET_USERS_FROM_CONTACTS, VIEW_FAQS, FAQS, SUBMIT_AS_READ, SUBMIT_VIEW, SUBMIT_COMMENT, VIEW_VIDEOS, VIDEOS,
            COMPLETE_REGISTRATION_PROCESS, SUBJECTS, STREAMS_YEARS, STREAMS, GET_GROUP_MEMBERS, ADD_PARTICIPANTS_MEMBERS,
            ADD_PARTICIPANTS_TO_GROUP, REMOVE_PARTICIPANT, ACTIVITY_DATA, VIEW_ALL_REPLIES, DISCUSSIONS_DATA, GET_DISCUSS_VIEW
            , MY_POSTED_SUGGESTIONS, MY_POSTED_DISCUSSIONS, MY_ACTIVITY_POSTS, MY_QUIZZES, SUBMIT_PING,
            MCQS_VIEW, SUBMIT_COMMENT_REPLY, VIEW_ALL_COMMENT_REPLIES, GET_NOTOFICATIONS, ACTIVITY_DETAILS, CREATE_POST,
            UPDATE_PASSWORD, FORGOT_PASSWORD, UPDATE_PROFILE_PIC, UPDATE_COVER_PIC, CREATE_ACTIVITY, CHANGE_SUBJECTS,
            UPDATE_NOTIFICATION_STATUS, SUBMIT_FEEDBACK, SUGGESTIONS, SUBMIT_SUGGESTION, SUMMARY, RIGHT_WRONG_ATTEMPTS,
            SUBMIT_QUIZ_ANSWERS, GET_COMMENTS, USER_PROFILE, ADV_BANNERS, UPDATE_TOKEN, ACCEPT_FRD_REQUEST,UNSEEN_NOTIFICATION})
    @interface RequestCode {
        int SESSION_EXPIRE = 1017;
        int USER_LOGIN = 110;
        int HISTORY_FETCH = 111;
        int NATIONALITIES = 112;
        int SUBMIT_CASE_DETAILS = 113;
        int HISTORY = 114;
        int COUNTRY_WISE_NATIONALITES = 115;
        int USER_REGISTER = 116;
        int DEPARTMENTS_CALL = 117;
        int DEPARTMENTS_SECTION = 118;
        int DEPARTMENTS_CATEGORY = 119;
        int UPDATE_PROFILE = 120;
        int ALLPRODUCTS = 121;
        int GETPRODUCTS = 122;
        int GETCART = 123;
        int EDUCATION = 124;
        int STREAMS = 125;
        int STREAMS_YEARS = 126;
        int SUBJECTS = 127;
        int COMPLETE_REGISTRATION_PROCESS = 128;
        int GET_VIDEOS_DATA = 175;
        int GET_NOTES_DATA = 129;
        int GET_CHAPTERS_DATA = 131;
        int GET_NOTES_VIEW = 130;
        int GET_DISCUSS_VIEW = 140;
        int SUBMIT_COMMENT_REPLY = 146;
        int GET_MCQS = 132;
        int GET_GROUP_MEMBERS = 133;
        int ADD_PARTICIPANTS_MEMBERS = 134;
        int ADD_PARTICIPANTS_TO_GROUP = 135;
        int REMOVE_PARTICIPANT = 136;
        int ACTIVITY_DATA = 137;
        int VIEW_ALL_REPLIES = 138;
        int DISCUSSIONS_DATA = 139;

        int MY_POSTED_SUGGESTIONS = 150;
        int MY_POSTED_DISCUSSIONS = 141;
        int MY_ACTIVITY_POSTS = 142;
        int MY_QUIZZES = 143;
        int SUBMIT_PING = 144;
        int MCQS_VIEW = 145;
        int VIEW_ALL_COMMENT_REPLIES = 147;

        int GET_NOTOFICATIONS = 148;
        int ACTIVITY_DETAILS = 149;

        int VIDEOS = 160;
        int VIEW_VIDEOS = 161;
        int SUBMIT_COMMENT = 162;
        int SUBMIT_VIEW = 163;
        int SUBMIT_AS_READ = 164;
        int FAQS = 165;
        int VIEW_FAQS = 166;
        int GET_USERS_FROM_CONTACTS = 167;
        int GET_USERS_USING_UNIVERSITY = 168;
        int SEND_FRIEND_REQUEST = 169;
        int FRIEND_REQUEST = 171;
        int FRIEND_REQUEST_SENT = 172;
        int MY_FRIENDS = 173;

        int QUIZS = 174;
        int SEARCH_UNIVERSITIES = 179;
        int QUIZ_LIST = 176;
        int CREATE_GROUP = 177;
        int GET_PING_GROUPS = 178;
        int SHOP_REVIEWS = 170;
        int SUMMARY = 181;
        int RIGHT_WRONG_ATTEMPTS = 180;
        int SUBMIT_SUGGESTION = 183;
        int SUGGESTIONS = 187;
        int SUBMIT_FEEDBACK = 188;
        int UPDATE_NOTIFICATION_STATUS = 189;
        int CHANGE_SUBJECTS = 190;
        int CREATE_ACTIVITY = 191;
        int UPDATE_COVER_PIC = 192;
        int UPDATE_PROFILE_PIC = 193;
        int FORGOT_PASSWORD = 194;
        int UPDATE_PASSWORD = 195;
        int CREATE_POST = 196;
        int SUBMIT_QUIZ_ANSWERS = 197;
        int GET_COMMENTS = 198;
        int USER_PROFILE = 199;
        int ADV_BANNERS = 200;
        int UPDATE_TOKEN = 201;
        int ACCEPT_FRD_REQUEST = 202;
        int UNSEEN_NOTIFICATION = 203;


    }
}