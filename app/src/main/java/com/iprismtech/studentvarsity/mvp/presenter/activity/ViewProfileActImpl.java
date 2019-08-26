package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ViewProfileActContarct;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class ViewProfileActImpl extends BasePresenter implements ViewProfileActContarct.IPresenter {
    public ViewProfileActImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void getFriends(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.my_friends(NetworkConstants.RequestCode.MY_FRIENDS, requestBody);
    }

    @Override
    public void myQuizzes(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.my_Quizzes(NetworkConstants.RequestCode.MY_QUIZZES, requestBody);
    }

    @Override
    public void myActivityPosts(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.my_Activty_Posts(NetworkConstants.RequestCode.MY_ACTIVITY_POSTS, requestBody);
    }

    @Override
    public void myPostedDiscussions(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.my_Posted_discussions(NetworkConstants.RequestCode.MY_POSTED_DISCUSSIONS, requestBody);
    }

    @Override
    public void myPostedSuggestions(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.my_Posted_suggestions(NetworkConstants.RequestCode.MY_POSTED_SUGGESTIONS, requestBody);
    }
}
