package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.GroupDetailsContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class GroupDetailsImpl extends BasePresenter implements GroupDetailsContract.IPresenter {
    public GroupDetailsImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void getGrpDetails(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getGroupMembers(NetworkConstants.RequestCode.GET_GROUP_MEMBERS, requestBody);

    }

    @Override
    public void remove_participant(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.remove_participant(NetworkConstants.RequestCode.REMOVE_PARTICIPANT, requestBody);

    }
}
