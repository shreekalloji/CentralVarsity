package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.CreateNewGroupContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class CreateNewGroupImpl extends BasePresenter implements CreateNewGroupContract.IPresenter {
    public CreateNewGroupImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void getList(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.my_friends(NetworkConstants.RequestCode.MY_FRIENDS, requestBody);
    }
    @Override
    public void createGroup(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.create_new_grp(NetworkConstants.RequestCode.CREATE_GROUP, requestBody);
    }
}
