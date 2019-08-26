package com.iprismtech.studentvarsity.mvp.contract.activity;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.iprismtech.studentvarsity.mvp.base.IBaseContract;
import com.iprismtech.studentvarsity.mvp.base.IBaseDataManager;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;

import java.util.Map;

public interface ProfileEditContract {
    interface IPermissionIds extends IBaseContract {
        //todo add permissions
        int FINISH_FLASH_SCREEN = 13;
        int LAUNCH_LOCATION_SCREEN = 14;
    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IView {
        void replaceRespectiveFragment(Fragment fragment, String[] data, String tag);

    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IPresenter {

        //This method is use to login.
        // void launchHomeFragment();

        void update_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void user_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void search_universities(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);


    }

    /**
     * Declared the methods here which can be used in Data manager of Login Screen.
     */
    interface IDataManager extends IBaseDataManager {


    }
}
