package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.PingFriendsGroupContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class PingFriendsGroupImpl extends BasePresenter implements PingFriendsGroupContract.IPresenter {

    public PingFriendsGroupImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void groupsList(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getPingGroupList(NetworkConstants.RequestCode.GET_PING_GROUPS, requestBody);
    }
}
