package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.SuggestionDetailsActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class SuggestionDetailsActivitylmpl extends BasePresenter implements SuggestionDetailsActivityContract.IPresenter {
    private SuggestionDetailsActivityContract.IView mainContainView;
    private Context context;

    public SuggestionDetailsActivitylmpl(SuggestionDetailsActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void suggestions(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.suggestions(NetworkConstants.RequestCode.SUGGESTIONS, requestBody);

    }

    @Override
    public void submit_suggestion(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_suggestion(NetworkConstants.RequestCode.SUBMIT_SUGGESTION, requestBody);

    }
}