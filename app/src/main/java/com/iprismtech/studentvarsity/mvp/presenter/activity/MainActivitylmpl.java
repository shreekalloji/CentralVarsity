package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.MainActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class MainActivitylmpl extends BasePresenter implements MainActivityContract.IPresenter {
    private MainActivityContract.IView mainContainView;
    private Context context;

    public MainActivitylmpl(MainActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void complete_registration_process(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.complete_registration_process(NetworkConstants.RequestCode.COMPLETE_REGISTRATION_PROCESS, requestBody);

    }
    @Override
    public void update_user_device_token(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.update_user_token(NetworkConstants.RequestCode.UPDATE_TOKEN, requestBody);

    }
    @Override
    public void unseen_notification(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.unseen_notification(NetworkConstants.RequestCode.UNSEEN_NOTIFICATION, requestBody);

    }
}
