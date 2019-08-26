package com.iprismtech.studentvarsity.app.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_ACTIVITY_DETAILS_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_ADDRESS_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_ADD_PARTCIPANTS_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_BAG_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_CLOTHING_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_CONNECTFRIENDSUNIVERSITY_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_CONNECTFRIENDS_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_COUNTRYWISE_HISTORY_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_CREATE_GROUP_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_CREATE_POST_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_DATEPICKER_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_DIALOG_FRAGMENT;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FEEDBACK_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FILTERSAPPLIED_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FILTER_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FINALREGISTRATION_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FORGOTPASSWORD_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FORGOT_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FRIENDS_LIST;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FRIENDS_REQUESTS;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_FRIENDS_REQUESTS_SENT;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_GROUP_DETAILS_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_HISTORY_PREVIEW_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_HISTORY_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_HOME_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_LOGIN_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_MAIN_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_MENSFASHION_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_OBSERVATION_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_ORDERDETAILS_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_PARAMEDICAL_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_PATIENTS_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_PING_GROUPS_LIST_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_PREVIEW_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_PROFILE_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_QUESTIONRIGHTWRONG_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_QUIZ_LIST_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_REGISTRATION_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_RESETPASSWORD_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_SETTINGS_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_SOCIALMEDIA_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_SPECIALCASES_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_STORE_DESC_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_SUBJECT_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_VERIFICATION_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_VIEW_PROFILE_SCREEN;
import static com.iprismtech.studentvarsity.app.constants.AppConstants.EventIds.LAUNCH_WELCOME_SCREEN;


public interface AppConstants {

    String LOG_CAT = ">>logs : ";


    @Retention(RetentionPolicy.CLASS)
    @IntDef({LAUNCH_LOGIN_SCREEN, LAUNCH_CREATE_GROUP_SCREEN, LAUNCH_QUIZ_LIST_SCREEN, LAUNCH_MCQS_DETAILING_SCREEN,
            LAUNCH_NOTES_DETAILING_SCREEN, LAUNCH_CONNECTFRIENDSUNIVERSITY_SCREEN, LAUNCH_CONNECTFRIENDS_SCREEN,
            LAUNCH_FAQDETAIL_SCREEN, LAUNCH_VIDEODETAIL_SCREEN, LAUNCH_MAIN_SCREEN, LAUNCH_FINALREGISTRATION_SCREEN,
            LAUNCH_SUBJECT_SCREEN, LAUNCH_REGISTRATION_SCREEN, LAUNCH_BAG_SCREEN, LAUNCH_FORGOTPASSWORD_SCREEN,
            LAUNCH_RESETPASSWORD_SCREEN, LAUNCH_PROFILE_SCREEN, LAUNCH_ORDERDETAILS_SCREEN, LAUNCH_FEEDBACK_SCREEN,
            LAUNCH_ADDRESS_SCREEN, LAUNCH_FILTERSAPPLIED_SCREEN, LAUNCH_VERIFICATION_SCREEN, LAUNCH_FILTER_SCREEN,
            LAUNCH_MENSFASHION_SCREEN, LAUNCH_CLOTHING_SCREEN, LAUNCH_HOME_SCREEN, LAUNCH_SOCIALMEDIA_SCREEN,
            LAUNCH_STORE_DESC_SCREEN, LAUNCH_OBSERVATION_SCREEN, LAUNCH_PARAMEDICAL_SCREEN, LAUNCH_DIALOG_FRAGMENT,
            LAUNCH_SIGNUP_SCREEN, LAUNCH_PATIENTS_SCREEN, LAUNCH_SPECIALCASES_SCREEN, LAUNCH_PREVIEW_SCREEN,
            LAUNCH_HISTORY_SCREEN, LAUNCH_COUNTRYWISE_HISTORY_SCREEN, LAUNCH_HISTORY_PREVIEW_SCREEN, LAUNCH_DATEPICKER_SCREEN,
            LAUNCH_WELCOME_SCREEN, LAUNCH_GROUP_DETAILS_SCREEN, LAUNCH_ADD_PARTCIPANTS_SCREEN, LAUNCH_PING_GROUPS_LIST_SCREEN,
            LAUNCH_VIEW_PROFILE_SCREEN, LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN,
            LAUNCH_ACTIVITY_DETAILS_SCREEN, LAUNCH_FORGOT_SCREEN, LAUNCH_QUESTIONRIGHTWRONG_SCREEN,
            LAUNCH_CREATE_POST_SCREEN, LAUNCH_SETTINGS_SCREEN, LAUNCH_FRIENDS_LIST, LAUNCH_FRIENDS_REQUESTS, LAUNCH_FRIENDS_REQUESTS_SENT})
    @interface EventIds {
        int LAUNCH_LOGIN_SCREEN = 101;
        int LAUNCH_HOME_SCREEN = 102;
        int LAUNCH_STORE_DESC_SCREEN = 103;
        int LAUNCH_OBSERVATION_SCREEN = 104;
        int LAUNCH_DIALOG_FRAGMENT = 105;
        int LAUNCH_SIGNUP_SCREEN = 106;
        int LAUNCH_PATIENTS_SCREEN = 107;
        int LAUNCH_SPECIALCASES_SCREEN = 108;
        int LAUNCH_PREVIEW_SCREEN = 109;
        int LAUNCH_HISTORY_SCREEN = 110;
        int LAUNCH_COUNTRYWISE_HISTORY_SCREEN = 111;
        int LAUNCH_HISTORY_PREVIEW_SCREEN = 112;
        int LAUNCH_PARAMEDICAL_SCREEN = 113;
        int LAUNCH_DATEPICKER_SCREEN = 114;
        int LAUNCH_SOCIALMEDIA_SCREEN = 115;
        int LAUNCH_VERIFICATION_SCREEN = 116;
        int LAUNCH_CLOTHING_SCREEN = 117;
        int LAUNCH_MENSFASHION_SCREEN = 118;
        int LAUNCH_FILTER_SCREEN = 119;
        int LAUNCH_FILTERSAPPLIED_SCREEN = 120;
        int LAUNCH_BAG_SCREEN = 121;
        int LAUNCH_ADDRESS_SCREEN = 122;
        int LAUNCH_FEEDBACK_SCREEN = 123;
        int LAUNCH_ORDERDETAILS_SCREEN = 124;
        int LAUNCH_PROFILE_SCREEN = 125;
        int LAUNCH_RESETPASSWORD_SCREEN = 126;
        int LAUNCH_FORGOTPASSWORD_SCREEN = 127;
        int LAUNCH_WELCOME_SCREEN = 128;
        int LAUNCH_REGISTRATION_SCREEN = 129;

        int LAUNCH_SUBJECT_SCREEN = 130;
        int LAUNCH_FINALREGISTRATION_SCREEN = 131;
        int LAUNCH_MAIN_SCREEN = 132;
        int LAUNCH_GROUP_DETAILS_SCREEN = 133;
        int LAUNCH_ADD_PARTCIPANTS_SCREEN = 134;
        int LAUNCH_PING_GROUPS_LIST_SCREEN = 135;
        int LAUNCH_VIEW_PROFILE_SCREEN = 136;
        int LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN = 137;
        int LAUNCH_ACTIVITY_DETAILS_SCREEN = 138;
        int LAUNCH_VIDEODETAIL_SCREEN = 162;
        int LAUNCH_FAQDETAIL_SCREEN = 163;
        int LAUNCH_CONNECTFRIENDS_SCREEN = 164;
        int LAUNCH_CONNECTFRIENDSUNIVERSITY_SCREEN = 165;
        int LAUNCH_NOTES_DETAILING_SCREEN = 166;
        int LAUNCH_MCQS_DETAILING_SCREEN = 167;
        int LAUNCH_QUIZ_LIST_SCREEN = 168;
        int LAUNCH_CREATE_GROUP_SCREEN = 169;
        int LAUNCH_FORGOT_SCREEN = 174;
        int LAUNCH_QUESTIONRIGHTWRONG_SCREEN = 170;
        int LAUNCH_CREATE_POST_SCREEN = 171;
        int LAUNCH_SETTINGS_SCREEN = 172;
        int LAUNCH_FRIENDS_LIST = 173;
        int LAUNCH_FRIENDS_REQUESTS = 175;
        int LAUNCH_FRIENDS_REQUESTS_SENT = 176;
    }

    interface PREFERENCES {
        String IS_LAUNCH_HOME_SCREEN = "isLaunchHomeScreen";
        String KEY_PREF_X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String KEY_HOST_URL = "hostUrl";
    }
}
