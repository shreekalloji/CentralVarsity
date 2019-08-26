package com.iprismtech.studentvarsity.mvp.presenter.fragment;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.fragment.NotesFragContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class NotesFragImpl extends BasePresenter implements NotesFragContract.IPresenter {
    public NotesFragImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void getNotesData(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getNotesReadSection(NetworkConstants.RequestCode.GET_NOTES_DATA, requestBody);
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
}
