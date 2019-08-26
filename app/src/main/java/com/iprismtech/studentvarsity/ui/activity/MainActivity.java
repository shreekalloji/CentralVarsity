package com.iprismtech.studentvarsity.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.fragments.DiscussionFragment;
import com.iprismtech.studentvarsity.fragments.HomeFragment;
import com.iprismtech.studentvarsity.fragments.NotificationFragment;
import com.iprismtech.studentvarsity.fragments.QuizFragment;
import com.iprismtech.studentvarsity.mvp.contract.activity.MainActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.MainActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.ui.fragment.FrequentQuestionFragment;
import com.iprismtech.studentvarsity.ui.fragment.MaxQuestionFragment;
import com.iprismtech.studentvarsity.ui.fragment.NoteFragment;
import com.iprismtech.studentvarsity.ui.fragment.VideoFragment;
import com.iprismtech.studentvarsity.utils.CommonUtil;
import com.iprismtech.studentvarsity.utils.Util;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseAbstractActivity<MainActivitylmpl> implements MainActivityContract.IView, View.OnClickListener, APIResponseCallback {
    FrameLayout fm_container;
    private FragmentManager manager;
    //private BottomNavigationView bottomNavigationView;
    ImageView iv_ping, iv_profile, iv_overview;
    TextView badge_text_view;
    private TextView tv_read_notes, tv_read_videos, tv_read_faq, tv_read_mcq, tv_read_quiz;
    LinearLayout ll_horizantal, ll_over_view;
    String cmntrefresh = "";
    private Bundle bundle;
    private String comimg_through, user_profile_pic;
    ImageView iv_static_search;
    private String refreshedToken, token, user_id;
    private LinearLayout ll_activity_bottom, ll_learn_bottom, ll_discuss_bottom, ll_Notification_bottom;


    private ImageView iv_activity_bottom, iv_learn_bottom, iv_discuss_bottom, iv_notification_bottom;
    private TextView tv_activity_bottom, tv_learn_bottom, tv_discuss_bottom, tv_noti_bottom;

    AppBarLayout app_bar;
    private int unseen_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


//        fm_container
    }


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new MainActivitylmpl(this, this);
    }


    public void replacementFragment(Fragment fragment) {
        try {
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.fm_container, fragment, "");
            fragmentTransaction.commit();
            app_bar.setExpanded(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_read_notes.setOnClickListener(this);
        tv_read_videos.setOnClickListener(this);
        tv_read_faq.setOnClickListener(this);
        tv_read_mcq.setOnClickListener(this);
        tv_read_quiz.setOnClickListener(this);
        iv_profile.setOnClickListener(this);
        ll_over_view.setOnClickListener(this);
        ll_activity_bottom.setOnClickListener(this);
        ll_learn_bottom.setOnClickListener(this);
        ll_discuss_bottom.setOnClickListener(this);
        ll_Notification_bottom.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        tv_read_notes = findViewById(R.id.tv_read_notes);
        tv_read_videos = findViewById(R.id.tv_read_video);
        tv_read_faq = findViewById(R.id.tv_read_faq);
        tv_read_mcq = findViewById(R.id.tv_read_mcq);
        tv_read_quiz = findViewById(R.id.tv_read_quiz);
        ll_horizantal = findViewById(R.id.ll_horizantal);
        iv_profile = findViewById(R.id.iv_profile);
        ll_over_view = findViewById(R.id.ll_over_view);
        iv_overview = findViewById(R.id.iv_overview);
        app_bar = findViewById(R.id.app_bar);
        badge_text_view = findViewById(R.id.badge_text_view);


        ll_activity_bottom = findViewById(R.id.ll_activity_bottom);
        ll_learn_bottom = findViewById(R.id.ll_learn_bottom);
        ll_discuss_bottom = findViewById(R.id.ll_discuss_bottom);
        ll_Notification_bottom = findViewById(R.id.ll_Notification_bottom);


        iv_activity_bottom = findViewById(R.id.iv_activity_bottom);
        iv_learn_bottom = findViewById(R.id.iv_learn_bottom);
        iv_discuss_bottom = findViewById(R.id.iv_discuss_bottom);
        iv_notification_bottom = findViewById(R.id.iv_notification_bottom);


        tv_activity_bottom = findViewById(R.id.tv_activity_bottom);
        tv_learn_bottom = findViewById(R.id.tv_learn_bottom);
        tv_discuss_bottom = findViewById(R.id.tv_discuss_bottom);
        tv_noti_bottom = findViewById(R.id.tv_noti_bottom);


        userSession = new UserSession(MainActivity.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        user_profile_pic = userSession.getUserDetails().get("profileImg");

        // FirebaseApp.initializeApp(context);

        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + user_profile_pic)
                .error(R.drawable.ic_blue_background)
                .into(iv_profile);

        refreshedToken = FirebaseInstanceId.getInstance().getToken();


        callUpdateDeviceToken();
        CommonUtil.initializeLocationAlaram(MainActivity.this);

//
        //  bottomNavigationView = findViewById(R.id.bnv);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            comimg_through = bundle.getString("comimg_through");
        }


        if (comimg_through.equalsIgnoreCase("overview_notes")) {
            ll_horizantal.setVisibility(View.VISIBLE);
            tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
            tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

            iv_activity_bottom.setBackgroundResource(R.drawable.ic_news);
            iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
            iv_learn_bottom.setBackgroundResource(R.drawable.ic_learn_act);
            iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
            iv_overview.setBackgroundResource(R.drawable.ic_setup);

//            Menu menulist = bottomNavigationView.getMenu();
//            MenuItem menuItem = menulist.findItem(R.id.action_read);
//
//            menuItem.setEnabled(true);
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fm_container, new NoteFragment(), "").commit();

        } else if (comimg_through.equalsIgnoreCase("overview_quiz")) {
            ll_horizantal.setVisibility(View.VISIBLE);
            tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
            tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

            iv_activity_bottom.setBackgroundResource(R.drawable.ic_news);
            iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
            iv_learn_bottom.setBackgroundResource(R.drawable.ic_learn_act);
            iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
            iv_overview.setBackgroundResource(R.drawable.ic_setup);
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fm_container, new QuizFragment(), "").commit();

        } else if (comimg_through.equalsIgnoreCase("overview_videos")) {
            ll_horizantal.setVisibility(View.VISIBLE);
            tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
            tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

            iv_activity_bottom.setBackgroundResource(R.drawable.ic_news);
            iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
            iv_learn_bottom.setBackgroundResource(R.drawable.ic_learn_act);
            iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
            iv_overview.setBackgroundResource(R.drawable.ic_setup);
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fm_container, new VideoFragment(), "").commit();

        } else if (comimg_through.equalsIgnoreCase("overview_faqs")) {
            ll_horizantal.setVisibility(View.VISIBLE);
            tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
            tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

            iv_activity_bottom.setBackgroundResource(R.drawable.ic_news);
            iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
            iv_learn_bottom.setBackgroundResource(R.drawable.ic_learn_act);
            iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
            iv_overview.setBackgroundResource(R.drawable.ic_setup);
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fm_container, new FrequentQuestionFragment(), "").commit();

        } else if (comimg_through.equalsIgnoreCase("overview_mcqs")) {
            ll_horizantal.setVisibility(View.VISIBLE);
            tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
            tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

            iv_activity_bottom.setBackgroundResource(R.drawable.ic_news);
            iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
            iv_learn_bottom.setBackgroundResource(R.drawable.ic_learn_act);
            iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
            iv_overview.setBackgroundResource(R.drawable.ic_setup);
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fm_container, new MaxQuestionFragment(), "").commit();

        } else {

            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fm_container, new HomeFragment(), "").commit();
            iv_activity_bottom.setBackgroundResource(R.drawable.ic_actvity_act);
            iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
            iv_learn_bottom.setBackgroundResource(R.drawable.learn);
            iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
            iv_overview.setBackgroundResource(R.drawable.ic_setup);

            tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
            tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
            tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

        }


//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.action_activity:
//                        replacementFragment(new HomeFragment());
//                        ll_horizantal.setVisibility(View.GONE);
//                        break;
//                    case R.id.action_read:
//                        replacementFragment(new NoteFragment());
//                        ll_horizantal.setVisibility(View.VISIBLE);
//                        break;
//                    case R.id.action_discuss:
//                        replacementFragment(new DiscussionFragment());
//                        ll_horizantal.setVisibility(View.GONE);
//
//                        break;
//                    case R.id.action_notification:
//                        replacementFragment(new NotificationFragment());
//                        ll_horizantal.setVisibility(View.GONE);
//                        removeBadge(bottomNavigationView, R.id.action_notification);
//                        break;
////                    case R.id.action_delivery:
////
////                        Intent intent = new Intent(MainActivity.this, OverviewActivity.class);
////                        startActivity(intent);
//////                        replacementFragment(new MoreFragment());
//////                        ll_horizantal.setVisibility(View.GONE);
////
////                        break;
//                }
//                return true;
//            }
//        });


        iv_ping = findViewById(R.id.iv_ping);
        iv_ping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_GROUPS_LIST_SCREEN);
            }
        });


    }

    public static void showBadge(Context context, BottomNavigationView
            bottomNavigationView, @IdRes int itemId, String value) {
        removeBadge(bottomNavigationView, itemId);
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        View badge = LayoutInflater.from(context).inflate(R.layout.badge_layout, bottomNavigationView, false);

        TextView text = badge.findViewById(R.id.badge_text_view);
        text.setText(value);
        itemView.addView(badge);
    }

    public static void removeBadge(BottomNavigationView bottomNavigationView, @IdRes int itemId) {
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        if (itemView.getChildCount() == 3) {
            itemView.removeViewAt(2);
        }
    }


    private void callUnseennotification() {
        Map<String, String> params = new HashMap<>();
        params.put("android_token", refreshedToken);
        params.put("user_id", user_id);
        presenter.unseen_notification(MainActivity.this, this, params);
    }

    private void callUpdateDeviceToken() {
        Map<String, String> params = new HashMap<>();
        params.put("android_token", refreshedToken);
        params.put("user_id", user_id);
        params.put("ios_token", "");
        presenter.update_user_device_token(MainActivity.this, this, params);


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_read_notes:
                replacementFragment(new NoteFragment());

                break;
            case R.id.tv_read_video:
                replacementFragment(new VideoFragment());
                break;
            case R.id.tv_read_faq:
                replacementFragment(new FrequentQuestionFragment());
                break;
            case R.id.tv_read_mcq:
                replacementFragment(new MaxQuestionFragment());
                break;
            case R.id.tv_read_quiz:
                replacementFragment(new QuizFragment());
                break;
            case R.id.ll_over_view:
//                iv_overview.setBackgroundResource(R.drawable.ic_setup);
//                iv_activity_bottom.setBackgroundResource(R.drawable.ic_news);
//                iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
//                iv_learn_bottom.setBackgroundResource(R.drawable.learn);
//                iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
//
//
//                tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
//                tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
//                tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
//                tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

                Intent intent1 = new Intent(MainActivity.this, OverviewActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_profile:


                //          ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIEW_PROFILE_SCREEN);
                Intent intent = new Intent(MainActivity.this, ViewProfile_Activity.class);
                startActivity(intent);
                break;
            case R.id.ll_activity_bottom:
                replacementFragment(new HomeFragment());

                iv_activity_bottom.setBackgroundResource(R.drawable.ic_actvity_act);
                iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
                iv_learn_bottom.setBackgroundResource(R.drawable.learn);
                iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
                iv_overview.setBackgroundResource(R.drawable.ic_setup);

                tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
                tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

                //   tv_activity_bottom.setTextColor(R.color.primary_light);


                ll_horizantal.setVisibility(View.GONE);

                break;
            case R.id.ll_learn_bottom:


                replacementFragment(new NoteFragment());

                tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
                tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

                iv_activity_bottom.setBackgroundResource(R.drawable.ic_news);
                iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
                iv_learn_bottom.setBackgroundResource(R.drawable.ic_learn_act);
                iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
                iv_overview.setBackgroundResource(R.drawable.ic_setup);

                ll_horizantal.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_discuss_bottom:
                replacementFragment(new DiscussionFragment());


                tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
                tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));

                iv_activity_bottom.setBackgroundResource(R.drawable.ic_news);
                iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss_act);
                iv_learn_bottom.setBackgroundResource(R.drawable.learn);
                iv_notification_bottom.setBackgroundResource(R.drawable.ic_notification);
                iv_overview.setBackgroundResource(R.drawable.ic_setup);

                ll_horizantal.setVisibility(View.GONE);
                break;
            case R.id.ll_Notification_bottom:

                replacementFragment(new NotificationFragment());

                tv_activity_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                tv_discuss_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                tv_learn_bottom.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                tv_noti_bottom.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
                iv_activity_bottom.setBackgroundResource(R.drawable.ic_news);
                iv_discuss_bottom.setBackgroundResource(R.drawable.ic_discuss);
                iv_learn_bottom.setBackgroundResource(R.drawable.learn);
                iv_notification_bottom.setBackgroundResource(R.drawable.ic_noti_act);
                iv_overview.setBackgroundResource(R.drawable.ic_setup);

                ll_horizantal.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //Util.getInstance().cusToast(this, jsonObject.optString("message"));
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);

            } else if (NetworkConstants.RequestCode.UPDATE_TOKEN == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    callUnseennotification();
                }
            } else if (NetworkConstants.RequestCode.UNSEEN_NOTIFICATION == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    unseen_count = jsonObject.optInt("response");

                    if (unseen_count > 0) {
                        badge_text_view.setVisibility(View.VISIBLE);
                        badge_text_view.setText(String.valueOf(unseen_count));
                    }
                    else {
                        badge_text_view.setVisibility(View.GONE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
