package com.iprismtech.studentvarsity.mvp.presenter.fragment;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.fragment.VideoFragmentContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class VideoFragmentlmpl extends BasePresenter implements VideoFragmentContract.IPresenter {
    public VideoFragmentlmpl(Object view, Context context) {
        super(view, context);
    }


    @Override
    public void videos(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.videos(NetworkConstants.RequestCode.VIDEOS, requestBody);
    }

    @Override
    public void getChapters(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getChaptersData(NetworkConstants.RequestCode.GET_CHAPTERS_DATA, requestBody);
    }

    @Override
    public void submit_as_read(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_as_read(NetworkConstants.RequestCode.SUBMIT_AS_READ, requestBody);
    }

    @Override
    public void advBanners(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.advBanners(NetworkConstants.RequestCode.ADV_BANNERS, requestBody);
    }
    @Override
    public void launchHomeFragment() {

    }
}