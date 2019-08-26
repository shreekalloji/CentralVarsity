package com.iprismtech.studentvarsity.sharepref;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by iprismTech on 12/07/2017.
 */

public class UserSession {
    // Shared Preferences reference
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    public static final String PREFER_NAME = "ATOZ";

    public static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String KEY_USERID = "id";

    public static final String KEY_UserName = "name";

    public static final String KEY_EMAIL = "email";

    public static final String KEY_MOBILENO = "mobileno";

    public static final String KEY_UNIVERSITY = "university";

    public static final String KEY_PASSWORD = "password";

    public static final String KEY_FIRST_NAME = "firstName";

    public static final String KEY_LAST_NAME = "lastName";

    public static final String KEY_PROFILE = "profileImg";

    public static final String KEY_STATUS = "status";

    public static final String LOGIN_ID = "id";

    public static final String KEY_SESSION_TYPE = "session_type";

    public static final String KEY_GENDER = "gender";

    public static final String KEY_DEVICE_TOKEN = "devicetoken";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_COUNTRY_ID = "country_id";
    public static final String KEY_CITY_ID = "city_id";
    public static final String KEY_BIO = "bio";
    public static final String KEY_EDUCATION_ID = "education_id";
    public static final String KEY_STREAM_ID = "stream_id";
    public static final String KEY_YEARS = "years";
    public static final String KEY_SUBJECTS = "subjects";
    public static final String KEY_SUBJECT_NAMES = "subject_names";
    public static final String KEY_STREAM = "stream";


    // Constructor
    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setlanguage(String session_type) {

        editor.putString(KEY_SESSION_TYPE, session_type);
        editor.commit();
    }


    //Create login session
    public void StoreUserDetails(String id, String username, String mobile, String emailId, String password, String profileImg, String gender, String university, String token, String country_id, String city_id, String bio, String education_id, String stream_id, String years, String subjects, String subject_names, String stream) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        editor.putString(KEY_UserName, username);

        editor.putString(KEY_USERID, id);

        editor.putString(KEY_PROFILE, profileImg);

        // Storing name in preferences
        editor.putString(KEY_UserName, username);

        // Storing mobile in preferences
        editor.putString(KEY_MOBILENO, mobile);

        // Storing email in preferences
        editor.putString(KEY_EMAIL, emailId);

        //Storing the password in preferences.
        editor.putString(KEY_PASSWORD, password);

//        //Storing the password in preferences.
//        editor.putString(KEY_FIRST_NAME, firstName);
//
//        //Storing the password in preferences.
//        editor.putString(KEY_LAST_NAME, lastName);

        //Storing the password in preferences.


        //Storing the password in preferences.
        editor.putString(KEY_GENDER, gender);

        //Storing the password in preferences.
        editor.putString(KEY_PASSWORD, password);

        editor.putString(KEY_UNIVERSITY, university);


        editor.putString(KEY_TOKEN, token);

        editor.putString(KEY_COUNTRY_ID, country_id);

        editor.putString(KEY_CITY_ID, city_id);

        editor.putString(KEY_BIO, bio);

        editor.putString(KEY_EDUCATION_ID, education_id);

        editor.putString(KEY_STREAM_ID, stream_id);

        editor.putString(KEY_YEARS, years);

        editor.putString(KEY_SUBJECTS, subjects);
        editor.putString(KEY_SUBJECT_NAMES, subject_names);
        editor.putString(KEY_STREAM, stream);

        editor.commit();
    }


//    public boolean checkLogin() {
//        // Check login status
//        if (!this.isUserLoggedIn()) {
//
//            // user is not logged in redirect him to Login Activity
//            Intent i = new Intent(_context, LoginActivity.class);
//
//            // Closing all the Activities from stack
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            // Staring Login Activity
//            _context.startActivity(i);
//
//            return true;
//        }
//        return false;
//    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_USERID, pref.getString(KEY_USERID, null));

        // getting name from sharedPreferences
        user.put(KEY_UserName, pref.getString(KEY_UserName, null));

        // getting name from sharedPreferences
        user.put(KEY_MOBILENO, pref.getString(KEY_MOBILENO, null));

        // getting name from sharedPreferences
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // getting name from sharedPreferences
        user.put(KEY_FIRST_NAME, pref.getString(KEY_FIRST_NAME, null));

        // getting name from sharedPreferences
        user.put(KEY_LAST_NAME, pref.getString(KEY_LAST_NAME, null));

        String img = pref.getString(KEY_PROFILE, null);
        user.put(KEY_PROFILE, pref.getString(KEY_PROFILE, null));

        // getting name from sharedPreferences
        user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));

        // getting name from sharedPreferences
        user.put(KEY_STATUS, pref.getString(KEY_STATUS, null));


        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));

        user.put(KEY_COUNTRY_ID, pref.getString(KEY_COUNTRY_ID, null));

        user.put(KEY_CITY_ID, pref.getString(KEY_CITY_ID, null));

        user.put(KEY_BIO, pref.getString(KEY_BIO, null));

        user.put(KEY_EDUCATION_ID, pref.getString(KEY_EDUCATION_ID, null));

        user.put(KEY_STREAM_ID, pref.getString(KEY_STREAM_ID, null));

        user.put(KEY_YEARS, pref.getString(KEY_YEARS, null));

        user.put(KEY_SUBJECTS, pref.getString(KEY_SUBJECTS, null));
        user.put(KEY_SUBJECT_NAMES, pref.getString(KEY_SUBJECT_NAMES, null));
        user.put(KEY_STREAM, pref.getString(KEY_STREAM, null));

        //user.put(NATIONALITY_ID, pref.getString(NATIONALITY_ID, null));

        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

    }

    /**
     * When user is logout then change the login screen details as user logout.
     */
    public void changeUserLoginScreenStatus() {
        editor.putBoolean(IS_USER_LOGIN, false);

        editor.commit();
    }

    // set the status of the user for read and unread here.
    public void setCheckTutorialScreenStatus(String status) {

        editor.putString(KEY_STATUS, status);

        editor.commit();
    }

    public void setDeviceToken(String deviceToken) {

        editor.putString(KEY_DEVICE_TOKEN, deviceToken);

        editor.commit();

    }

    // Check for login
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }


}
