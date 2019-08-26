package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ConnectFriendRequestsActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class ConnectFriendRequestsActivitylmpl extends BasePresenter implements ConnectFriendRequestsActivityContract.IPresenter {
    private ConnectFriendRequestsActivityContract.IView mainContainView;
    private Context context;

    public ConnectFriendRequestsActivitylmpl(ConnectFriendRequestsActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void friend_requests(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.friend_requests(NetworkConstants.RequestCode.FRIEND_REQUEST,requestBody);

    }

    @Override
    public void send_friend_request(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.send_friend_request(NetworkConstants.RequestCode.SEND_FRIEND_REQUEST, requestBody);

    }
    @Override
    public void accept_friend_request(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.accept_friend_request(NetworkConstants.RequestCode.ACCEPT_FRD_REQUEST, requestBody);

    }
}