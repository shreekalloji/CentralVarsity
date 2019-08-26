package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.QuestionActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import org.json.JSONObject;

import java.util.Map;

public class QuestionActivitylmpl extends BasePresenter implements QuestionActivityContract.IPresenter {
    private QuestionActivityContract.IView mainContainView;
    private Context context;

    public QuestionActivitylmpl(QuestionActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }


    @Override
    public void quiz_list(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.quiz_list(NetworkConstants.RequestCode.QUIZ_LIST, requestBody);
    }

    @Override
    public void submit_quiz_answers(Context context, APIResponseCallback apiResponseCallback, JSONObject requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_quiz_answers(NetworkConstants.RequestCode.SUBMIT_QUIZ_ANSWERS, requestBody);
    }

    @Override
    public void create_post(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.create_post(NetworkConstants.RequestCode.CREATE_POST, requestBody);

    }


//    @Override
//    public void launchHomeFragment() {
//        HomeFragment homeFragment = new HomeFragment();
//        mainContainView.replaceRespectiveFragment(homeFragment, null, "homefrag");
//    }
}
