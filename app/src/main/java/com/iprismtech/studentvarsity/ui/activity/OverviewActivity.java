package com.iprismtech.studentvarsity.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.OverviewActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.OverviewActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.SummaryPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class OverviewActivity extends BaseAbstractActivity<OverviewActivitylmpl> implements OverviewActivityContract.IView, View.OnClickListener, APIResponseCallback {
    TextView tv_suggests, aboutus, contactus, tv_notescount, tv_videoscount, tv_faqscount, tv_mcqscount, tv_quizcount, tv_discusscount;
    TextView tv_subjectnames;
    CircleImageView img_prof;

    ImageView im_close;
    LinearLayout ll_profile, ll_quiz, ll_notes, ll_videos, ll_faqs, ll_mcqs, ll_rate_us, ll_frds,
            ll_sent, ll_about_us, ll_requests_rece, ll_contact_us, ll_suggestions;

    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, user_id, stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //setContentView(R.layout.activity_overview);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_overview, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new OverviewActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(OverviewActivity.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        stream = userSession.getUserDetails().get("stream");
        apiResponseCallback = this;

        im_close = findViewById(R.id.im_close);
        tv_notescount = findViewById(R.id.tv_notescount);
        tv_videoscount = findViewById(R.id.tv_videoscount);
        tv_faqscount = findViewById(R.id.tv_faqscount);
        tv_mcqscount = findViewById(R.id.tv_mcqscount);
        tv_quizcount = findViewById(R.id.tv_quizcount);
        tv_discusscount = findViewById(R.id.tv_discusscount);
        tv_subjectnames = findViewById(R.id.tv_subjectnames);
        img_prof = findViewById(R.id.img_prof);
        tv_suggests = findViewById(R.id.tv_suggestions);
        aboutus = findViewById(R.id.tv_aboutus);
        contactus = findViewById(R.id.tv_contact);
        ll_profile = findViewById(R.id.ll_profile);
        ll_rate_us = findViewById(R.id.ll_rate_us);
        ll_suggestions = findViewById(R.id.ll_suggestions);

        ll_contact_us = findViewById(R.id.ll_contact_us);

        ll_quiz = findViewById(R.id.ll_quiz);
        ll_notes = findViewById(R.id.ll_notes);
        ll_videos = findViewById(R.id.ll_videos);
        ll_faqs = findViewById(R.id.ll_faqs);
        ll_mcqs = findViewById(R.id.ll_mcqs);
        ll_sent = findViewById(R.id.ll_sent);
        ll_frds = findViewById(R.id.ll_frds);
        ll_requests_rece = findViewById(R.id.ll_requests_rece);
        ll_about_us = findViewById(R.id.ll_about_us);


        ll_rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
            }
        });

        ll_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("comimg_through", "overview_quiz");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);

            }
        });
        ll_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("comimg_through", "overview_notes");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);

            }
        });
        ll_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("comimg_through", "overview_videos");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);

            }
        });
        ll_faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("comimg_through", "overview_faqs");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);

            }
        });
        ll_mcqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("comimg_through", "overview_mcqs");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);

            }
        });


        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("comimg_through", "overview");
//                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);
                finish();
            }
        });

        ll_suggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OverviewActivity.this, SuggestionDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ll_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OverviewActivity.this, ViewProfile_Activity.class);
                startActivity(intent);
            }
        });

        ll_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OverviewActivity.this, AboutUsActivity.class);
                startActivity(i);
                finish();

            }
        });

        ll_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OverviewActivity.this, ContactActivity.class);
                startActivity(intent);
                finish();
            }
        });


        if (token != null && token.length() > 0) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            presenter.summary(OverviewActivity.this, apiResponseCallback, requestBody);
        }

        ll_frds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRIENDS_LIST);
                finish();
            }
        });
        ll_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRIENDS_REQUESTS_SENT);
                finish();
            }
        });
        ll_requests_rece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRIENDS_REQUESTS);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString,
                                  @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.SUMMARY == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    // Util.getInstance().cusToast(context, message);
                    final SummaryPOJO summaryPOJO = new Gson().fromJson(responseString, SummaryPOJO.class);

                    tv_notescount.setText(summaryPOJO.getResponse().getNotes_read_count() + "/" + summaryPOJO.getResponse().getNotes_count());
                    tv_videoscount.setText(summaryPOJO.getResponse().getVideos_read_count() + "/" + summaryPOJO.getResponse().getVideos_count());
                    tv_faqscount.setText(summaryPOJO.getResponse().getFaqs_read_count() + "/" + summaryPOJO.getResponse().getFaqs_count());
                    tv_mcqscount.setText(summaryPOJO.getResponse().getMcqs_read_count() + "/" + summaryPOJO.getResponse().getMcqs_count());
                    tv_quizcount.setText(summaryPOJO.getResponse().getQuiz_completed_count() + "/" + summaryPOJO.getResponse().getQuiz_count());
                    tv_discusscount.setText(summaryPOJO.getResponse().getDicussed_count() + "/" + summaryPOJO.getResponse().getDicuss_count());
                    tv_subjectnames.setText(stream);

                    Glide.with(OverviewActivity.this)
                            .load(NetworkConstants.URL.Imagepath_URL + summaryPOJO.getResponse().getUser_details().getProfile_pic())
                            .error(R.drawable.no_image)
                            .into(img_prof);

                } else {
                    Util.getInstance().cusToast(context, message);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
//        Bundle bundle = new Bundle();
//        bundle.putString("comimg_through", "overview");
//        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);
        finish();
    }

}

