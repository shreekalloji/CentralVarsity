package com.iprismtech.studentvarsity.mvp.contract.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.IBaseContract;
import com.iprismtech.studentvarsity.mvp.base.IBaseDataManager;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;

import java.util.Map;

public interface FaqDetailActivityContract {
    interface IPermissionIds extends IBaseContract {
        //todo add permissions
        int FINISH_FLASH_SCREEN = 13;
        int LAUNCH_LOCATION_SCREEN = 14;
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
        void view_faq(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void submit_comment(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void submit_as_view(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void submit_as_read(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void viewAllCopmmentReplies(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void submit_comment_reply(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void getComments(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

    }

    /**
     * Declared the methods here which can be used in Data manager of Login Screen.
     */
    interface IDataManager extends IBaseDataManager {


    }
}
