package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ConnectFriendRequestSentActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class ConnectFriendRequestSentActivitylmpl extends BasePresenter implements ConnectFriendRequestSentActivityContract.IPresenter {
    private ConnectFriendRequestSentActivityContract.IView mainContainView;
    private Context context;

    public ConnectFriendRequestSentActivitylmpl(ConnectFriendRequestSentActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void friend_requests_sent(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.friend_requests_sent(NetworkConstants.RequestCode.FRIEND_REQUEST_SENT, requestBody);

    }

    @Override
    public void send_friend_request(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.send_friend_request(NetworkConstants.RequestCode.SEND_FRIEND_REQUEST, requestBody);

    }
}
