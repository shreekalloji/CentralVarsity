package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.FinalRegistrationActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class FinalRegistrationActivitylmpl extends BasePresenter implements FinalRegistrationActivityContract.IPresenter {
    private FinalRegistrationActivityContract.IView mainContainView;
    private Context context;

    public FinalRegistrationActivitylmpl(FinalRegistrationActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void search_universities(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.search_universities(NetworkConstants.RequestCode.SEARCH_UNIVERSITIES,requestBody);

    }
}
