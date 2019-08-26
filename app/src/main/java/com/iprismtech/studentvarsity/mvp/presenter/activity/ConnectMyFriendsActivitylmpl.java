package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ConnectMyFriendsActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class ConnectMyFriendsActivitylmpl extends BasePresenter implements ConnectMyFriendsActivityContract.IPresenter {
    private ConnectMyFriendsActivityContract.IView mainContainView;
    private Context context;

    public ConnectMyFriendsActivitylmpl(ConnectMyFriendsActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void my_friends(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.my_friends(NetworkConstants.RequestCode.MY_FRIENDS, requestBody);

    }

    @Override
    public void send_friend_request(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.send_friend_request(NetworkConstants.RequestCode.SEND_FRIEND_REQUEST, requestBody);

    }
}

