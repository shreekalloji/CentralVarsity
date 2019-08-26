package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.SignUpActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class SignUpActivitylmpl extends BasePresenter implements SignUpActivityContract.IPresenter {
    private SignUpActivityContract.IView mainContainView;
    private Context context;

    public SignUpActivitylmpl(SignUpActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }


    @Override
    public void userRegister(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.checkUserRegister(NetworkConstants.RequestCode.USER_REGISTER,requestBody);
    }


//    @Override
//    public void launchHomeFragment() {
//        HomeFragment homeFragment = new HomeFragment();
//        mainContainView.replaceRespectiveFragment(homeFragment, null, "homefrag");
//    }
}
