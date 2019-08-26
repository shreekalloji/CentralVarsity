package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.PingToFGriendsGroupContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class PingToFGriendsGroupImpl extends BasePresenter implements PingToFGriendsGroupContract.IPresenter {
    public PingToFGriendsGroupImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void groupsList(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getPingGroupList(NetworkConstants.RequestCode.GET_PING_GROUPS, requestBody);
    }

    @Override
    public void friendsList(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.my_friends(NetworkConstants.RequestCode.MY_FRIENDS, requestBody);
    }

    @Override
    public void submit_ping(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_ping(NetworkConstants.RequestCode.SUBMIT_PING, requestBody);
    }
}
