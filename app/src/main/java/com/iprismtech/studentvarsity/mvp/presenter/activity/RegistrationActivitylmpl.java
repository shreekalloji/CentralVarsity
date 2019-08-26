package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.RegistrationActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class RegistrationActivitylmpl extends BasePresenter implements RegistrationActivityContract.IPresenter {
    private RegistrationActivityContract.IView mainContainView;
    private Context context;

    public RegistrationActivitylmpl(RegistrationActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }


    @Override
    public void education(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.education(NetworkConstants.RequestCode.EDUCATION, requestBody);
    }

    @Override
    public void streams(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.streams(NetworkConstants.RequestCode.STREAMS, requestBody);
    }

    @Override
    public void streams_years(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.streams_years(NetworkConstants.RequestCode.STREAMS_YEARS, requestBody);
    }


//    @Override
//    public void launchHomeFragment() {
//        HomeFragment homeFragment = new HomeFragment();
//        mainContainView.replaceRespectiveFragment(homeFragment, null, "homefrag");
//    }
}