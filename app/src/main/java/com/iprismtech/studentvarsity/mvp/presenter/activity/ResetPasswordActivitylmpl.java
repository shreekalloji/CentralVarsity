package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ResetPasswordActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class ResetPasswordActivitylmpl extends BasePresenter implements ResetPasswordActivityContract.IPresenter {
    private ResetPasswordActivityContract.IView mainContainView;
    private Context context;

    public ResetPasswordActivitylmpl(ResetPasswordActivityContract.IView mainContainView, Context context) {
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
