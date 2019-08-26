package com.iprismtech.studentvarsity.mvp.base;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//import static com.iprismtech.homeoptima.mvp.contract.activity.HomeActivityContract.IPermissionIds.REQUEST_TO_GET_ACCOUNT_FRAGMENT_SCREEN;

public interface IBaseContract {

    /**
     * Annotations for events id
     * Make here if events id are common
     * otherwise maintain in base class
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({/*REQUEST_TO_GET_OTP_SCREEN*/})
    @interface PermissionIds {

    }
}