package com.iprismtech.studentvarsity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.NotificationAdapter;
import com.iprismtech.studentvarsity.base.BaseAbstractFragment;
import com.iprismtech.studentvarsity.mvp.contract.fragment.NotificationFragmentContract;
import com.iprismtech.studentvarsity.mvp.presenter.fragment.NotificationsFragImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.NotificationsPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.ui.activity.ActivityDetailsActvity;
import com.iprismtech.studentvarsity.ui.activity.FaqDetailActivity;
import com.iprismtech.studentvarsity.ui.activity.MCQsQuestionDetailActivity;
import com.iprismtech.studentvarsity.ui.activity.MainActivity;
import com.iprismtech.studentvarsity.ui.activity.NotesDetailsDescriptionActivity;
import com.iprismtech.studentvarsity.ui.activity.VideoDetailActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotificationFragment extends BaseAbstractFragment<NotificationsFragImpl> implements View.OnClickListener, NotificationFragmentContract.IView, APIResponseCallback {


    private RecyclerView rview_notifications;
    private NotificationAdapter notificationAdapter;
    private LinearLayoutManager manager;
    private NotificationsPojo notificationsPojo;
    private String token, user_id;
    private UserSession userSession;


    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_notification, null);
        return view;
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        rview_notifications = view.findViewById(R.id.rview_notifications);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_notifications.setLayoutManager(manager);

        userSession = new UserSession(getActivity());

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.getNotifications(getActivity(), this, requestBody);


    }

    @Override
    public void setPresenter() {
        presenter = new NotificationsFragImpl(this, getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                //  Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
            } else if (NetworkConstants.RequestCode.GET_NOTOFICATIONS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    notificationsPojo = new Gson().fromJson(responseString, NotificationsPojo.class);
                    notificationAdapter = new NotificationAdapter(getActivity(), notificationsPojo);
                    rview_notifications.setAdapter(notificationAdapter);
                } else {
                    // Toast.makeText(getActivity(), "No Data for selected Category", Toast.LENGTH_SHORT).show();
                    rview_notifications.setVisibility(View.GONE);
                }
                notificationAdapter.setOnItemClickListener(new NotificationAdapter.OnitemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent;
                        if (notificationsPojo.getResponse().get(position).getType().equalsIgnoreCase("notes")) {
                            intent = new Intent(getActivity(), NotesDetailsDescriptionActivity.class);
                            intent.putExtra("topic_id", notificationsPojo.getResponse().get(position).getPost_id());
                            intent.putExtra("sending_through", "notes");
                            startActivity(intent);

                        } else if (notificationsPojo.getResponse().get(position).getType().equalsIgnoreCase("activity")) {
                            intent = new Intent(getActivity(), ActivityDetailsActvity.class);
                            intent.putExtra("topic_id", notificationsPojo.getResponse().get(position).getPost_id());
                            startActivity(intent);

                        } else if (notificationsPojo.getResponse().get(position).getType().equalsIgnoreCase("discuss")) {
                            intent = new Intent(getActivity(), NotesDetailsDescriptionActivity.class);
                            intent.putExtra("topic_id", notificationsPojo.getResponse().get(position).getPost_id());
                            intent.putExtra("sending_through", "discuss");
                            startActivity(intent);
                        } else if (notificationsPojo.getResponse().get(position).getType().equalsIgnoreCase("faqs")) {
                            intent = new Intent(getActivity(), FaqDetailActivity.class);
                            intent.putExtra("faq_id", notificationsPojo.getResponse().get(position).getPost_id());
                            startActivity(intent);
                        } else if (notificationsPojo.getResponse().get(position).getType().equalsIgnoreCase("videos")) {
                            intent = new Intent(getActivity(), VideoDetailActivity.class);
                            intent.putExtra("video_id", notificationsPojo.getResponse().get(position).getPost_id());
                            startActivity(intent);
                        } else if (notificationsPojo.getResponse().get(position).getType().equalsIgnoreCase("mcqs")) {
                            intent = new Intent(getActivity(), MCQsQuestionDetailActivity.class);
                            intent.putExtra("topic_id", notificationsPojo.getResponse().get(position).getPost_id());
                            startActivity(intent);
                        } else {

                        }
                    }
                });
            }
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
