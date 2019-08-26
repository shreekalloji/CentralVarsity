package com.iprismtech.studentvarsity.mvp.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;


public abstract class BasePresenter<T> implements APIResponseCallback {

    protected T view;
    protected Context context;

    public BasePresenter(T view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void start(){
        ApplicationController.getInstance().setContext(context);
    }

    public void stop(){

    }
    public void launchLocatinScreen(@IBaseContract.PermissionIds int permissionId, @Nullable Object data){

    }

    public void onFragmentLaunch(@IBaseContract.PermissionIds int permissionId, @Nullable Object data){

    }

    @Override
    public void onSuccessResponse(@NetworkConstants.RequestCode int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString) {

    }
}