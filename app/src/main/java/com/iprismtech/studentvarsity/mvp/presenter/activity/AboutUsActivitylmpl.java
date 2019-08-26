package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.AboutUsActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class AboutUsActivitylmpl extends BasePresenter implements AboutUsActivityContract.IPresenter {
    private AboutUsActivityContract.IView mainContainView;
    private Context context;

    public AboutUsActivitylmpl(AboutUsActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void userLogin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.checkUserLogin(NetworkConstants.RequestCode.USER_LOGIN,requestBody);

    }
}
