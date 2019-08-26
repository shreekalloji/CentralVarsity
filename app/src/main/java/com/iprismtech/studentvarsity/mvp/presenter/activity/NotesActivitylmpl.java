package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.NotesActivityContract;
import com.iprismtech.studentvarsity.mvp.contract.activity.OTPActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class NotesActivitylmpl extends BasePresenter implements NotesActivityContract.IPresenter {
    private NotesActivityContract.IView mainContainView;
    private Context context;

    public NotesActivitylmpl(NotesActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void userRegister(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getNotesReadSection(NetworkConstants.RequestCode.GET_NOTES_DATA, requestBody);
    }

    @Override
    public void submit_as_read(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_as_read(NetworkConstants.RequestCode.SUBMIT_AS_READ, requestBody);
    }




//    @Override
//    public void launchHomeFragment() {
//        HomeFragment homeFragment = new HomeFragment();
//        mainContainView.replaceRespectiveFragment(homeFragment, null, "homefrag");
//    }
}