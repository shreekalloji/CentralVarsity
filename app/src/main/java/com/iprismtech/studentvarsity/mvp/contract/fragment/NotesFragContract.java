package com.iprismtech.studentvarsity.mvp.contract.fragment;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.IBaseContract;
import com.iprismtech.studentvarsity.mvp.base.IBaseDataManager;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;

import java.util.Map;

public interface NotesFragContract {
    interface IPermissionIds extends IBaseContract {
        //todo add permissions
        int LAUNCH_ALL_SERVICE_FRAGMENT_SCREEN = 10;
    }

    /**
     * Declared the methods here which can be used in Home Screen.
     */
    interface IView {


    }

    /**
     * Declared the methods here which can be used in Home Screen.
     */
    interface IPresenter {
        void getNotesData(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

        void getChapters(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

        void submit_as_read(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

        void advBanners(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);


    }

    /**
     * Declared the methods here which can be used in Data manager of Home Screen.
     */
    interface IDataManager extends IBaseDataManager {

    }

}
