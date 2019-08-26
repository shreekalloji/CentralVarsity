package com.iprismtech.studentvarsity.app.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.iprismtech.studentvarsity.app.application.MyApplication;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.factories.ViewFactory;


/**
 * Created by prasad on 05-07-17.
 * ApplicationController.java
 * <p/>
 * The ApplicationController Class, which helps to manage different Controllers,
 * Models, Views. This Class will be initialize as the platform requirement.
 */

public class ApplicationController {

    /**
     * private instance of ApplicationController for singleton Design Pattern
     */
    private static ApplicationController instance;

    /**
     * private instance of UIController for managing different AbstractViews
     */
    public UiController uiController;

    /**
     * private instance of ViewFactory for fast accessing.
     */
    public ViewFactory viewFactory;

    public Activity mActivity;
    public Context mContext;

    private MyApplication application;

    /**
     * Constructor of ApplicationController
     */
    private ApplicationController() {
        uiController = UiController.getInstance();
    }

    /**
     * Get Single instance of ApplicationController
     *
     * @return ApplicationController single instance
     */
    public static ApplicationController getInstance() {
        if (instance == null) {
            // creating new instance of application controller
            instance = new ApplicationController();
        }
        return instance;
    }
//
//    /**
//     * This Function will get called from LoginActivity or Any Activity which is
//     * going to display first screen after launching this application
//     */
//    public void initialize() {
//
//        // initialize the ModelFacade
//        modelFacade.initialize();
//
//        // set the reference for UI Controller
//        uiController = UIController.getInstance();
//
//        // initialize the UIController
//        uiController.initialize();
//
//        // set the viewFactory reference for further use in code.
//        viewFactory = ViewFactory.getInstance();
//
//    }

    /**
     * returns the current mActivity
     *
     * @return
     */
    public Activity getActivity() {
        return mActivity;
    }


    public void setActivity(@NonNull Activity mActivity) {
        this.mActivity = mActivity;
    }


    public MyApplication getApplication() {
        return application;
    }


    public void setApplication(MyApplication application) {
        this.application = application;
    }


    public Context getContext() {
        return mContext;
    }


    public void setContext(@NonNull Context mContext) {
        this.mContext = mContext;
    }


    public void handleEvent(@AppConstants.EventIds int eventId) {
        handleEvent(eventId, null);
    }


    /**
     * Handle Event Based on Event ID and Object
     *
     * @param eventId      Event Id based on user actions
     * @param eventObjects It stores the data for the given Event. so it can forward to
     *                     other events
     */
    @SuppressLint("WrongConstant")
    public void handleEvent(@AppConstants.EventIds int eventId, Object eventObjects) {
        Log.d(AppConstants.LOG_CAT, "handleEvent : " + eventId);

        switch (eventId) {

            case AppConstants.EventIds.LAUNCH_LOGIN_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.LOGIN_SCREEN);
                break;


            case AppConstants.EventIds.LAUNCH_MENSFASHION_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.MENSFASHION_SCREEN, (Bundle) eventObjects);
                break;


            case AppConstants.EventIds.LAUNCH_FEEDBACK_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.FEEDBACK_SCREEN);
                break;


            case AppConstants.EventIds.LAUNCH_RESETPASSWORD_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.RESETPASSWORD_SCREEN, (Bundle) eventObjects);
                break;


            case AppConstants.EventIds.LAUNCH_FORGOTPASSWORD_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.FORGOTPASSWORD_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_WELCOME_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.WELCOME_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_REGISTRATION_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.REGISTRATION_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_SUBJECT_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.SUBJECT_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_FINALREGISTRATION_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.FINALREGISTRATION_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_MAIN_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.MAIN_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.VIDEODETAIL_SCREEN,(Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.FAQDETAIL_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_CONNECTFRIENDS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.CONNECTFRIENDS_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_CONNECTFRIENDSUNIVERSITY_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.CONNECTFRIENDSUNIVERSITY_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.NOTES_DETAILING, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.MCQS_DETAILING, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_PING_GROUPS_LIST_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.PING_GROUPS_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_VIEW_PROFILE_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.VIEW_PROFILE);
                break;

            case AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.PING_TO_FRIENDS_GROUPS, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_QUIZ_LIST_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.QUIZ_LIST_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_GROUP_DETAILS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.GROUP_MEMBERS_DETAILS_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_ADD_PARTCIPANTS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ADD_PARTICIPANTS_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_ACTIVITY_DETAILS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ACTVITY_DETAILS_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_CREATE_GROUP_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.CREATE_GROUP);
                break;

            case AppConstants.EventIds.LAUNCH_PROFILE_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.PROFILE_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_ORDERDETAILS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ORDERDETAILS_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_FILTER_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.FILTER_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_FILTERSAPPLIED_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.FILTERSAPPLIED_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_ADDRESS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ADDRESS_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_HOME_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.HOME_SCREEN, (Bundle) eventObjects);
                break;


            case AppConstants.EventIds.LAUNCH_BAG_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.BAG_SCREEN);
                break;


            case AppConstants.EventIds.LAUNCH_SOCIALMEDIA_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.SOCIALMEDIA_SCREEN);
                break;


            case AppConstants.EventIds.LAUNCH_CLOTHING_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.CLOTHING_SCREEN, (Bundle) eventObjects);
                break;


            case AppConstants.EventIds.LAUNCH_VERIFICATION_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.VERIFICATION_SCREEN, (Bundle) eventObjects);
                break;


            case AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.SIGNUP_SCREEN);
                break;


            case AppConstants.EventIds.LAUNCH_FORGOT_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.FORGOT_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_FRIENDS_LIST:
                uiController.launchActivity(ViewFactory.ScreenIds.FRIENDS_LIST);
                break;
            case AppConstants.EventIds.LAUNCH_FRIENDS_REQUESTS:
                uiController.launchActivity(ViewFactory.ScreenIds.FRIENDS_REQUESTS);
                break;
            case AppConstants.EventIds.LAUNCH_FRIENDS_REQUESTS_SENT:
                uiController.launchActivity(ViewFactory.ScreenIds.FRIENDS_REQUESTS_SENT);
                break;

            case AppConstants.EventIds.LAUNCH_QUESTIONRIGHTWRONG_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.QUESTIONRIGHTWRONG_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_CREATE_POST_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.CREATE_POST_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_SETTINGS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.SETTINGS_SCREEN, (Bundle) eventObjects);
                break;


            default:
                throw new IllegalStateException("Invalid Event id");
        }
    }
}
