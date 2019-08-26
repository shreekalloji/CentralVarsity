package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ProfileEditContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class PofileEditImpl extends BasePresenter implements ProfileEditContract.IPresenter {
    public PofileEditImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void update_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.updateProfile(NetworkConstants.RequestCode.UPDATE_PROFILE, requestBody);

    }
    @Override
    public void search_universities(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.search_universities(NetworkConstants.RequestCode.SEARCH_UNIVERSITIES,requestBody);

    }

    @Override
    public void user_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.user_profile(NetworkConstants.RequestCode.USER_PROFILE, requestBody);
    }
}
