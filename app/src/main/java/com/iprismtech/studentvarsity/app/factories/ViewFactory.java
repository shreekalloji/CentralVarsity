package com.iprismtech.studentvarsity.app.factories;

import android.support.annotation.IntDef;

import com.iprismtech.studentvarsity.activities.ViewProfileActivity;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.ui.activity.ActivityDetailsActvity;
import com.iprismtech.studentvarsity.ui.activity.AddPartcipantsActivity;
import com.iprismtech.studentvarsity.ui.activity.ConnectFriendRequestSentActivity;
import com.iprismtech.studentvarsity.ui.activity.ConnectFriendRequestsActivity;
import com.iprismtech.studentvarsity.ui.activity.ConnectFriendsActivity;
import com.iprismtech.studentvarsity.ui.activity.ConnectMyFriendsActivity;
import com.iprismtech.studentvarsity.ui.activity.ConnectUniversityFrds;
import com.iprismtech.studentvarsity.ui.activity.Createpost;
import com.iprismtech.studentvarsity.ui.activity.FaqDetailActivity;
import com.iprismtech.studentvarsity.ui.activity.FinalRegistrationActivity;
import com.iprismtech.studentvarsity.ui.activity.ForgotPasswordActivity;
import com.iprismtech.studentvarsity.ui.activity.GroupDetailsActivity;
import com.iprismtech.studentvarsity.ui.activity.LoginActivity;
import com.iprismtech.studentvarsity.ui.activity.MCQsQuestionDetailActivity;
import com.iprismtech.studentvarsity.ui.activity.MainActivity;
import com.iprismtech.studentvarsity.ui.activity.NewGroupActivity;
import com.iprismtech.studentvarsity.ui.activity.NotesDetailsDescriptionActivity;
import com.iprismtech.studentvarsity.ui.activity.OTPActivity;
import com.iprismtech.studentvarsity.ui.activity.PingFriendsGroup;
import com.iprismtech.studentvarsity.ui.activity.PingToFriendsGroupActivity;
import com.iprismtech.studentvarsity.ui.activity.ProfileActivity;
import com.iprismtech.studentvarsity.ui.activity.QuestionActivity;
import com.iprismtech.studentvarsity.ui.activity.QuestionRightWrongActivity;
import com.iprismtech.studentvarsity.ui.activity.RegistrationActivity;
import com.iprismtech.studentvarsity.ui.activity.ResetPasswordActivity;
import com.iprismtech.studentvarsity.ui.activity.SettingActivity;
import com.iprismtech.studentvarsity.ui.activity.SignUpActivity;
import com.iprismtech.studentvarsity.ui.activity.Subject;
import com.iprismtech.studentvarsity.ui.activity.VideoDetailActivity;
import com.iprismtech.studentvarsity.ui.activity.WelcomeActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.ACTVITY_DETAILS_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.ADDRESS_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.ADD_PARTICIPANTS_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.CREATE_GROUP;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.BAG_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.CLOTHING_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.CONNECTFRIENDSUNIVERSITY_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.CONNECTFRIENDS_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.CREATE_POST_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.DATEPICKER_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.DIALOG_FRAGMENT;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FAQDETAIL_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FEEDBACK_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FILTERSAPPLIED_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FILTER_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FINALREGISTRATION_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FORGOTPASSWORD_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FORGOT_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FRIENDS_LIST;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FRIENDS_REQUESTS;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.FRIENDS_REQUESTS_SENT;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.GROUP_MEMBERS_DETAILS_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.HISTORY_PREVIEW_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.HISTORY_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.HOME_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.LOGIN_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.MAIN_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.MCQS_DETAILING;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.MENSFASHION_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.NOTES_DETAILING;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.OBSERVATION_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.ORDERDETAILS_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.PARAMEDICAL_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.PATIENTS_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.PING_GROUPS_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.PING_TO_FRIENDS_GROUPS;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.PREVIEW_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.PROFILE_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.QUESTIONRIGHTWRONG_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.QUIZ_LIST_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.REGISTRATION_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.RESETPASSWORD_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.SETTINGS_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.SIGNUP_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.SOCIALMEDIA_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.SPECIALCASES_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.STOREDESC_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.SUBJECT_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.VERIFICATION_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.VIDEODETAIL_SCREEN;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.VIEW_PROFILE;
import static com.iprismtech.studentvarsity.app.factories.ViewFactory.ScreenIds.WELCOME_SCREEN;

//import static com.iprismtech.proffers.app.constants.AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN;


/**
 * Created by prasad on 05/07/2017.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */
public class ViewFactory {

    @Retention(RetentionPolicy.CLASS)
    @IntDef({LOGIN_SCREEN, HOME_SCREEN, CREATE_GROUP, QUIZ_LIST_SCREEN, MCQS_DETAILING, NOTES_DETAILING, CONNECTFRIENDSUNIVERSITY_SCREEN,
            CONNECTFRIENDS_SCREEN, FAQDETAIL_SCREEN, VIDEODETAIL_SCREEN, MAIN_SCREEN, FINALREGISTRATION_SCREEN, SUBJECT_SCREEN,
            REGISTRATION_SCREEN, ORDERDETAILS_SCREEN, FORGOTPASSWORD_SCREEN, WELCOME_SCREEN, RESETPASSWORD_SCREEN, PROFILE_SCREEN,
            FEEDBACK_SCREEN, BAG_SCREEN, ADDRESS_SCREEN, FILTERSAPPLIED_SCREEN, FILTER_SCREEN, CLOTHING_SCREEN, MENSFASHION_SCREEN,
            VERIFICATION_SCREEN, SOCIALMEDIA_SCREEN, STOREDESC_SCREEN, OBSERVATION_SCREEN, PARAMEDICAL_SCREEN, DIALOG_FRAGMENT,
            SIGNUP_SCREEN, PATIENTS_SCREEN, SPECIALCASES_SCREEN, PREVIEW_SCREEN, HISTORY_SCREEN, HISTORY_PREVIEW_SCREEN,
            DATEPICKER_SCREEN, GROUP_MEMBERS_DETAILS_SCREEN, ADD_PARTICIPANTS_SCREEN, PING_GROUPS_SCREEN,
            ACTVITY_DETAILS_SCREEN, VIEW_PROFILE, PING_TO_FRIENDS_GROUPS, FORGOT_SCREEN, QUESTIONRIGHTWRONG_SCREEN, CREATE_POST_SCREEN,
            SETTINGS_SCREEN, FRIENDS_LIST, FRIENDS_REQUESTS, FRIENDS_REQUESTS_SENT})
    public @interface ScreenIds {
        int LOGIN_SCREEN = 1001;
        int HOME_SCREEN = 1002;
        int STOREDESC_SCREEN = 1003;
        int OBSERVATION_SCREEN = 1004;
        int DIALOG_FRAGMENT = 1005;
        int SIGNUP_SCREEN = 1006;
        int PATIENTS_SCREEN = 1007;
        int SPECIALCASES_SCREEN = 1008;
        int PREVIEW_SCREEN = 1009;
        int HISTORY_SCREEN = 1010;
        int COUNTRYWISE_HISTORY_SCREEN = 1011;
        int HISTORY_PREVIEW_SCREEN = 1012;
        int PARAMEDICAL_SCREEN = 1013;
        int DATEPICKER_SCREEN = 1014;
        int SOCIALMEDIA_SCREEN = 1015;
        int VERIFICATION_SCREEN = 1016;
        int CLOTHING_SCREEN = 1017;
        int MENSFASHION_SCREEN = 1018;
        int FILTER_SCREEN = 1019;
        int FILTERSAPPLIED_SCREEN = 1020;
        int BAG_SCREEN = 1021;
        int ADDRESS_SCREEN = 1022;
        int FEEDBACK_SCREEN = 1023;
        int ORDERDETAILS_SCREEN = 1024;
        int PROFILE_SCREEN = 1025;
        int RESETPASSWORD_SCREEN = 1026;
        int FORGOTPASSWORD_SCREEN = 1027;
        int WELCOME_SCREEN = 1028;
        int REGISTRATION_SCREEN = 1029;
        int SUBJECT_SCREEN = 1030;
        int FINALREGISTRATION_SCREEN = 1031;
        int MAIN_SCREEN = 1032;
        int GROUP_MEMBERS_DETAILS_SCREEN = 1033;
        int ADD_PARTICIPANTS_SCREEN = 1034;
        int PING_GROUPS_SCREEN = 1035;
        int VIEW_PROFILE = 1036;
        int PING_TO_FRIENDS_GROUPS = 1037;
        int ACTVITY_DETAILS_SCREEN = 1038;
        int VIDEODETAIL_SCREEN = 1062;
        int FAQDETAIL_SCREEN = 1063;
        int CONNECTFRIENDS_SCREEN = 1064;
        int CONNECTFRIENDSUNIVERSITY_SCREEN = 1065;
        int NOTES_DETAILING = 1066;
        int MCQS_DETAILING = 1067;
        int QUIZ_LIST_SCREEN = 1068;
        int CREATE_GROUP = 1069;
        int FORGOT_SCREEN = 1074;
        int QUESTIONRIGHTWRONG_SCREEN = 1070;
        int CREATE_POST_SCREEN = 1071;
        int SETTINGS_SCREEN = 1072;
        int FRIENDS_LIST = 1073;
        int FRIENDS_REQUESTS = 1075;
        int FRIENDS_REQUESTS_SENT = 1076;

    }

    private ApplicationController mApplicationController = null;


    private ViewFactory() {
        mApplicationController = ApplicationController.getInstance();
    }


    public static Class getActivityClass(@ScreenIds int id) {

        switch (id) {

            case LOGIN_SCREEN: {
                return LoginActivity.class;

            }
            case FRIENDS_LIST: {
                return ConnectMyFriendsActivity.class;

            }
            case FRIENDS_REQUESTS: {
                return ConnectFriendRequestsActivity.class;

            }
            case FRIENDS_REQUESTS_SENT: {
                return ConnectFriendRequestSentActivity.class;

            }

            case WELCOME_SCREEN: {
                return WelcomeActivity.class;

            }
            case PING_GROUPS_SCREEN: {
                return PingFriendsGroup.class;

            }
            case ACTVITY_DETAILS_SCREEN: {
                return ActivityDetailsActvity.class;

            }
            case RESETPASSWORD_SCREEN: {
                return ResetPasswordActivity.class;

            }
            case QUESTIONRIGHTWRONG_SCREEN: {
                return QuestionRightWrongActivity.class;

            }
            case FORGOT_SCREEN: {
                return ForgotPasswordActivity.class;
            }
            case VIEW_PROFILE: {
                return ViewProfileActivity.class;

            }
            case REGISTRATION_SCREEN: {
                return RegistrationActivity.class;

            }
            case SUBJECT_SCREEN: {
                return Subject.class;

            }
            case FINALREGISTRATION_SCREEN: {
                return FinalRegistrationActivity.class;

            }
            case MAIN_SCREEN: {
                return MainActivity.class;
            }
            case GROUP_MEMBERS_DETAILS_SCREEN: {
                return GroupDetailsActivity.class;
            }
            case VIDEODETAIL_SCREEN: {
                return VideoDetailActivity.class;
            }
            case FAQDETAIL_SCREEN: {
                return FaqDetailActivity.class;
            }
            case CONNECTFRIENDS_SCREEN: {
                return ConnectFriendsActivity.class;
            }
            case CONNECTFRIENDSUNIVERSITY_SCREEN: {
                return ConnectUniversityFrds.class;
            }

            case NOTES_DETAILING: {
                return NotesDetailsDescriptionActivity.class;

            }
            case MCQS_DETAILING: {
                return MCQsQuestionDetailActivity.class;

            }
            case ADD_PARTICIPANTS_SCREEN: {
                return AddPartcipantsActivity.class;

            }
            case QUIZ_LIST_SCREEN: {
                return QuestionActivity.class;

            }

            case CREATE_GROUP: {
                return NewGroupActivity.class;

            }
            case PING_TO_FRIENDS_GROUPS: {
                return PingToFriendsGroupActivity.class;

            }


            case SETTINGS_SCREEN: {
                return SettingActivity.class;

            }
//
//            case FORGOTPASSWORD_SCREEN: {
//                return ForgotPasswordActivity.class;
//
//            }
//
//            case RESETPASSWORD_SCREEN: {
//                return ResetPassActivity.class;
//
//            }
//
            case PROFILE_SCREEN: {
                return ProfileActivity.class;

            }
            case CREATE_POST_SCREEN: {
                return Createpost.class;

            }
//
//            case BAG_SCREEN: {
//                return CartActivity.class;
//
//            }
//
//
//            case ADDRESS_SCREEN: {
//                return AddressActivity.class;
//
//            }
//
//
//            case FILTER_SCREEN: {
//                return FiltersActivity.class;
//
//            }
//
//
//            case FILTERSAPPLIED_SCREEN: {
//                return FiltersAppliedActivity.class;
//
//            }
//
//
//            case CLOTHING_SCREEN: {
//                return ClothingActivity.class;
//
//            }
//
//            case MENSFASHION_SCREEN: {
//                return MensFashionActivity.class;
//
//            }
//
//
//            case SOCIALMEDIA_SCREEN: {
//                return SocialMediaActivity.class;
//
//            }
//
//
            case VERIFICATION_SCREEN: {
                return OTPActivity.class;
            }
//
//
            case SIGNUP_SCREEN: {
                return SignUpActivity.class;
            }
//            case HOME_SCREEN: {
//                return HomeActivity.class;
//            }

            default:
                throw new IllegalStateException("Invalid screen id");
        }
    }


}