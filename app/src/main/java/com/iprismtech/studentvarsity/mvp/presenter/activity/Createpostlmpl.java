package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.CreatepostContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class Createpostlmpl extends BasePresenter implements CreatepostContract.IPresenter {
    private CreatepostContract.IView mainContainView;
    private Context context;

    public Createpostlmpl(CreatepostContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void create_activity(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.create_activity(NetworkConstants.RequestCode.CREATE_ACTIVITY,requestBody);

    }

    @Override
    public void create_post(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.create_post(NetworkConstants.RequestCode.CREATE_POST, requestBody);

    }
    @Override
    public void getChapters(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getChaptersData(NetworkConstants.RequestCode.GET_CHAPTERS_DATA, requestBody);
    }
}
