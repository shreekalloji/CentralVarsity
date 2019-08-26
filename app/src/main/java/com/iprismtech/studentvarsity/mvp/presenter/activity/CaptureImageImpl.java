package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.CaptureImageContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class CaptureImageImpl extends BasePresenter implements CaptureImageContract.IPresenter {
    public CaptureImageImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void submit_comment(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_comment(NetworkConstants.RequestCode.SUBMIT_COMMENT, requestBody);
    }
}
