package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ViewProfile_ActivityContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class ViewProfile_Activitylmpl extends BasePresenter implements ViewProfile_ActivityContract.IPresenter {
    private ViewProfile_ActivityContract.IView mainContainView;
    private Context context;

    public ViewProfile_Activitylmpl(ViewProfile_ActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }


    @Override
    public void update_cover_pic(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.update_cover_pic(NetworkConstants.RequestCode.UPDATE_COVER_PIC, requestBody);
    }

    @Override
    public void user_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.user_profile(NetworkConstants.RequestCode.USER_PROFILE, requestBody);
    }

    @Override
    public void update_profile_pic(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.update_profile_pic(NetworkConstants.RequestCode.UPDATE_PROFILE_PIC, requestBody);
    }

    @Override
    public void update_notification_status(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.update_notification_status(NetworkConstants.RequestCode.UPDATE_NOTIFICATION_STATUS, requestBody);
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


//    @Override
//    public void launchHomeFragment() {
//        HomeFragment homeFragment = new HomeFragment();
//        mainContainView.replaceRespectiveFragment(homeFragment, null, "homefrag");
//    }
}