package com.iprismtech.studentvarsity.mvp.presenter.activity;

import android.content.Context;

import com.iprismtech.studentvarsity.mvp.base.BasePresenter;
import com.iprismtech.studentvarsity.mvp.contract.activity.NotesDetailsDescriptionContract;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.network.models.UserApiModel;

import java.util.Map;

public class NotesDetailsDescriptionImpl extends BasePresenter implements NotesDetailsDescriptionContract.IPresenter {

    public NotesDetailsDescriptionImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void getNotesDesc(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getNotesView(NetworkConstants.RequestCode.GET_NOTES_VIEW, requestBody);
    }
    @Override
    public void getDiscussDate(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getDiscussData(NetworkConstants.RequestCode.GET_DISCUSS_VIEW, requestBody);
    } @Override
    public void submit_comment_reply(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_comment_reply(NetworkConstants.RequestCode.SUBMIT_COMMENT_REPLY, requestBody);
    }
    @Override
    public void submit_comment(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_comment(NetworkConstants.RequestCode.SUBMIT_COMMENT, requestBody);
    }
    @Override
    public void getComments(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.getComments(NetworkConstants.RequestCode.GET_COMMENTS, requestBody);
    }

    @Override
    public void submit_as_view(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_as_view(NetworkConstants.RequestCode.SUBMIT_VIEW, requestBody);
    }

    @Override
    public void submit_as_read(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.submit_as_read(NetworkConstants.RequestCode.SUBMIT_AS_READ, requestBody);
    }

//    @Override
//    public void viewAllReplies(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
//        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
//        userApiModel.viewAllReplies(NetworkConstants.RequestCode.VIEW_ALL_REPLIES, requestBody);
//
//    }
    @Override
    public void viewAllCopmmentReplies(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.viewAllCopmmentReplies(NetworkConstants.RequestCode.VIEW_ALL_COMMENT_REPLIES, requestBody);

    }
}
