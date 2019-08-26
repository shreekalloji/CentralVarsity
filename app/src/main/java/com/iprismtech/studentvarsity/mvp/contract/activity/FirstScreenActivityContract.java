package com.iprismtech.studentvarsity.mvp.contract.activity;

import android.support.v4.app.Fragment;

import com.iprismtech.studentvarsity.mvp.base.IBaseContract;
import com.iprismtech.studentvarsity.mvp.base.IBaseDataManager;

public interface FirstScreenActivityContract {
    interface IPermissionIds extends IBaseContract {
        //todo add permissions
        int FINISH_FLASH_SCREEN = 13;
        int LAUNCH_LOCATION_SCREEN=14;
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
    }

    /**
     * Declared the methods here which can be used in Data manager of Login Screen.
     */
    interface IDataManager extends IBaseDataManager {


    }
}
