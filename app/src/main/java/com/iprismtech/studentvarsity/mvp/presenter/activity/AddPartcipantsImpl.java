package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.AddPartcipantsContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class AddPartcipantsImpl extends BasePresenter implements AddPartcipantsContract.IPresenter {
    public AddPartcipantsImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void getnewParList(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getNewAddPartcipants(NetworkConstants.RequestCode.ADD_PARTICIPANTS_MEMBERS, requestBody);
    }
    @Override
    public void addParticipants(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.addParticipants(NetworkConstants.RequestCode.ADD_PARTICIPANTS_TO_GROUP, requestBody);
    }
}
