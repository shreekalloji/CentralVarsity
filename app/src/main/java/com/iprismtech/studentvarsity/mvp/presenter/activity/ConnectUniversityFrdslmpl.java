package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ConnectUniversityFrdsContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class ConnectUniversityFrdslmpl extends BasePresenter implements ConnectUniversityFrdsContract.IPresenter {
    private ConnectUniversityFrdsContract.IView mainContainView;
    private Context context;

    public ConnectUniversityFrdslmpl(ConnectUniversityFrdsContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void get_users_using_university(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.get_users_using_university(NetworkConstants.RequestCode.GET_USERS_USING_UNIVERSITY,requestBody);

    }

    @Override
    public void send_friend_request(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.send_friend_request(NetworkConstants.RequestCode.SEND_FRIEND_REQUEST, requestBody);

    }
}