package com.iprismtech.studentvarsity.mvp.contract.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.IBaseContract;
import com.iprismtech.studentvarsity.mvp.base.IBaseDataManager;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;

import org.json.JSONObject;

import java.util.Map;

public interface QuestionActivityContract {

    interface IPermissionIds extends IBaseContract {
        //todo add permissions
        int FINISH_FLASH_SCREEN = 13;
        int LAUNCH_LOCATION_SCREEN=14;
    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IView {
        //void replaceRespectiveFragment(Fragment fragment, String[] data, String tag);

    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IPresenter {

        //This method is use to login.
        // void launchHomeFragment();
        void quiz_list(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void create_post(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void submit_quiz_answers(Context context, APIResponseCallback apiResponseCallback, JSONObject requestBody);


    }

    /**
     * Declared the methods here which can be used in Data manager of Login Screen.
     */
    interface IDataManager extends IBaseDataManager {


    }

}
