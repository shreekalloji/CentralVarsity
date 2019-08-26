package com.iprismtech.studentvarsity.network.models;

import android.content.Context;

import com.android.volley.Request;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.volley.APIHandler;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by PRASAD on 09-Dec-17.
 */

public class UserApiModel extends BaseApiModel {

    public UserApiModel(Context context, APIResponseCallback apiResponseCallback) {
        super(context);
        this.context = context;
        this.apiResponseCallback = apiResponseCallback;
    }

    public void checkUserLogin(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.USER_LOGIN, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void checkUserRegister(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.USER_REGISTER, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void education(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.EDUCATION, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void streams(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.STREAMS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void change_subjects(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.CHANGE_SUBJECTS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void streams_years(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.STREAMS_YEARS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void subjects(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUBJECTS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getNewAddPartcipants(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_PARTCIPANT_MEMBERS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getActivtyDetails(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.ACTIVITY_DETAILS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void addParticipants(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.ADD_PARTCIPANT_TO_GROUP_MEMBERS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void complete_registration_process(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.COMPLETE_REGISTRATION_PROCESS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }  public void update_user_token(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UPDATE_USER_TOKEN, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }public void unseen_notification(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UNSEEN_NOTIFICATION, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void videos(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.VIDEOS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void view_video(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.VIEW_VIDEOS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void submit_comment(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUBMIT_COMMENT, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getComments(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_COMMENTS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void mcqsView(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.MCQS_VIEW, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void submit_as_view(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUBMIT_VIEW, false, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void submit_as_read(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUBMIT_AS_READ, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void advBanners(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.ADV_BANNERS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void viewAllReplies(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_VIEW_ALL_REPLIES, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void viewAllCopmmentReplies(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.VIEW_ALL_COMMENT_REPLIES, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void faqs(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.FAQS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getNotifications(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_NOTOFICATIONS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void view_faq(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.VIEW_FAQS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void get_users_from_contacts(@NetworkConstants.RequestCode int requestId, String params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_USERS_FROM_CONTACTS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void get_users_using_university(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_USERS_USING_UNIVERSITY, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void send_friend_request(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SEND_FRIEND_REQUEST, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }  public void accept_friend_request(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.ACCEPT_FRD_REQUEST, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void friend_requests(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.FRIEND_REQUEST, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void friend_requests_sent(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.FRIEND_REQUEST_SENT, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void submit_ping(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUBMIT_PING, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void my_friends(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.MY_FRIENDS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void my_Quizzes(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.MY_QUIZZES, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void my_Posted_suggestions(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.MY_POSTED_SUGGESTIONS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void my_Posted_discussions(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.MY_POSTED_DISCUSSIONS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void my_Activty_Posts(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.MY_ACTIVITY_POSTS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void quizs(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.QUIZS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void quiz_list(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.QUIZ_LIST, false, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getNotesView(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_NOTES_DATA_VIEW_DESC, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getDiscussData(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_DISCUSSIONS_VIEW_DATA, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void submit_comment_reply(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUBMIT_COMMENT_REPLY, false, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getMCQs(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_MCQS_DATA, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getChaptersData(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_CHAPTERS_DATA, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getDiscusions(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_DISCUSSIONS_DATA, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getNotesReadSection(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_NOTES_DATA_SECTION, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void search_universities(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SEARCH_UNIVERSITIES, false, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void create_new_grp(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.CRATE_NEW_GROUP, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getPingGroupList(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_PIBNG_GROUPS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getGroupMembers(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_GROUP_MEMBERS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void remove_participant(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.REMOVE_PARTCIPANT, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void shop_reviews(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                "http://tahyba.com/Ws/shop_reviews", false, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void departmentCall(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Department Call>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.DEPARTMENTS_CALL, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void activity_data(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Department Call>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GET_ACTIVITY_DATA, false, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void departmentSections(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Department Sections>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.DEPARTMENTS_SECTION, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void departmentCategory(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Department Categories>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.DEPARTMENTS_CATEGORY, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void allProducts(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("All Products>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.ALLPRODUCTS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void updateProfile(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("UpdateProfile>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UPDATE_PROFILE, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getProduct(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("GotProducts>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GETPRODUCTS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void getcart(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("GotProducts>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.GETCART, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }


    public void submit_quiz_answers(@NetworkConstants.RequestCode int requestId, JSONObject params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUBMIT_QUIZ_ANSWERS, false, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI3();
    }

    public void right_wrong_attempts(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.RIGHT_WRONG_ATTEMPTS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }


    public void summary(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUMMARY, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void submit_suggestion(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUBMIT_SUGGESTION, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void submit_feedback(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUBMIT_FEEDBACK, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void update_notification_status(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UPDATE_NOTIFICATION_STATUS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void suggestions(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SUGGESTIONS, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }


    public void create_activity(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.CREATE_ACTIVITY, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void create_post(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.CREATE_POST, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void update_cover_pic(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UPDATE_COVER_PIC, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void update_profile_pic(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UPDATE_PROFILE_PIC, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void user_profile(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.USER_PROFILE, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void forgot_password(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.FORGOT_PASSWORD, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void update_password(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UPDATE_PASSWORD, true, true,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }


    @Override
    public void onAPISuccessResponse(int requestId, String responseString) {
        super.onAPISuccessResponse(requestId, responseString);
        apiResponseCallback.onSuccessResponse(requestId, responseString, "");
    }

    @Override
    public void onAPIFailureResponse(int requestId, String errorString) {
        super.onAPIFailureResponse(requestId, errorString);
        apiResponseCallback.onSuccessResponse(requestId, errorString, "");
    }

}
