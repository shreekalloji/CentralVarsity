package com.iprismtech.studentvarsity.mvp.presenter.fragment;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.fragment.NotificationFragmentContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class NotificationsFragImpl extends BasePresenter implements NotificationFragmentContract.IPresenter {
    public NotificationsFragImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void getNotifications(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getNotifications(NetworkConstants.RequestCode.GET_NOTOFICATIONS, requestBody);

    }

    @Override
    public void launchHomeFragment() {

    }
}
