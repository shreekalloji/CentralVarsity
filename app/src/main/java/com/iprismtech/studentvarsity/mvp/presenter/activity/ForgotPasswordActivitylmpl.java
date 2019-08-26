package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ForgotPasswordActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class ForgotPasswordActivitylmpl extends BasePresenter implements ForgotPasswordActivityContract.IPresenter {
    private ForgotPasswordActivityContract.IView mainContainView;
    private Context context;

    public ForgotPasswordActivitylmpl(ForgotPasswordActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }


    @Override
    public void forgot_password(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.forgot_password(NetworkConstants.RequestCode.FORGOT_PASSWORD, requestBody);
    }

    @Override
    public void update_password(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.update_password(NetworkConstants.RequestCode.UPDATE_PASSWORD, requestBody);
    }
}