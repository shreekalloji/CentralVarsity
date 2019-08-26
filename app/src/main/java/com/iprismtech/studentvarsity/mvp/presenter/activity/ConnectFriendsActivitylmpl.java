package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ConnectFriendsActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class ConnectFriendsActivitylmpl extends BasePresenter implements ConnectFriendsActivityContract.IPresenter {
    private ConnectFriendsActivityContract.IView mainContainView;
    private Context context;

    public ConnectFriendsActivitylmpl(ConnectFriendsActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void get_users_from_contacts(Context context, APIResponseCallback apiResponseCallback, String requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.get_users_from_contacts(NetworkConstants.RequestCode.GET_USERS_FROM_CONTACTS,requestBody);

    }

    @Override
    public void send_friend_request(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.send_friend_request(NetworkConstants.RequestCode.SEND_FRIEND_REQUEST, requestBody);

    }
}

