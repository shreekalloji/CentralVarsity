package com.iprismtech.studentvarsity.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.ActvityAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.base.BaseAbstractFragment;
import com.iprismtech.studentvarsity.mvp.contract.fragment.HomeFragmntContract;
import com.iprismtech.studentvarsity.mvp.presenter.fragment.HomeFragImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.ActivityPojo;
import com.iprismtech.studentvarsity.pojos.AddBannerPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends BaseAbstractFragment<HomeFragImpl> implements View.OnClickListener, HomeFragmntContract.IView, APIResponseCallback {
    private ImageView createpost;
    //VideoView videoview;
    private ImageView iv_adv_banner;
    private View view;
    private RecyclerView rview_activity;
    private TextView tv_scrolling;
    private LinearLayoutManager manager;
    private ActivityPojo activityPojo;
    private List<ActivityPojo.ResponseBean> responseBeanList;
    private ActvityAdapter actvityAdapter;
    private int selected_postion;
    private String selected_type;
    private UserSession userSession;
    private LinearLayout ll_filter;
    private String slelcted_filter = "all_data";
    private Dialog viewall_dialogue;
    String cmntrefresh = "";
    private PlayerView player_view;
    private SimpleExoPlayer player;
    private YouTubePlayerView video_youtubePlayerView_activity;
    private AddBannerPojo addBannerPojo;
    private ImageView iv_static_search, iv_search;
    private EditText et_search;
    private NestedScrollView ll_nested_scroll;
    private int page_number = 0;
    private ProgressBar progress_bar;
    private String token, KEY_EDUCATION_ID, KEY_YEARS, KEY_STREAM_ID, KEY_SUBJECTS, KEY_USERID, KEY_UserName, KEY_MOBILENO, KEY_PROFILE, KEY_UNIVERSITY, KEY_COUNTRY_ID, KEY_CITY_ID, KEY_BIO;
    private int page_number_test = 0;

    private ProgressDialog dialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new HomeFragImpl(this, getActivity());
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
//        frg_video = view.findViewById(R.id.frg_video);
        createpost = view.findViewById(R.id.fab_creatrpost);
        rview_activity = view.findViewById(R.id.rview_activity);
        ll_nested_scroll = view.findViewById(R.id.ll_nested_scroll_activity);

        dialog = new ProgressDialog(getActivity());


        ll_filter = view.findViewById(R.id.ll_filter);
        progress_bar = view.findViewById(R.id.progress_bar);
        iv_adv_banner = view.findViewById(R.id.iv_adv_banner);
        tv_scrolling = view.findViewById(R.id.tv_scrolling);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        tv_scrolling.setSelected(true);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_activity.setLayoutManager(manager);

        //  iv_static_search = (ImageView) getActivity().findViewById(R.id.iv_static_search);
        iv_search = (ImageView) getActivity().findViewById(R.id.iv_search);
        et_search = (EditText) getActivity().findViewById(R.id.et_search);

        responseBeanList = new ArrayList<>();

        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iv_static_search.setVisibility(View.GONE);
                iv_search.setVisibility(View.VISIBLE);
            }
        });
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.search_text = et_search.getText().toString();
                callActivtyDataWs();
                dialog.setMessage("Doing something, please wait.");
                dialog.show();
            }
        });

        userSession = new UserSession(getActivity());
        token = userSession.getUserDetails().get("token");
        KEY_EDUCATION_ID = userSession.getUserDetails().get("education_id");
        KEY_STREAM_ID = userSession.getUserDetails().get("stream_id");
        KEY_YEARS = userSession.getUserDetails().get("years");
        KEY_SUBJECTS = userSession.getUserDetails().get("subjects");
        KEY_USERID = userSession.getUserDetails().get("id");
        KEY_UserName = userSession.getUserDetails().get("name");
        KEY_MOBILENO = userSession.getUserDetails().get("mobileno");
        KEY_PROFILE = userSession.getUserDetails().get("profileImg");
        KEY_UNIVERSITY = userSession.getUserDetails().get("university");
        KEY_COUNTRY_ID = userSession.getUserDetails().get("country_id");
        KEY_CITY_ID = userSession.getUserDetails().get("city_id");
        KEY_BIO = userSession.getUserDetails().get("bio");


        iv_adv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(addBannerPojo.getAdv_banners().getAdvt_url()));
                startActivity(browserIntent);
            }
        });


        createpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmntrefresh = "abc";
                Bundle bundle = new Bundle();
                bundle.putString("from", "activity");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CREATE_POST_SCREEN, bundle);
            }
        });
        ll_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewall_dialogue = new Dialog(getActivity(), R.style.Theme_Dialog);
                viewall_dialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
                viewall_dialogue.setContentView(R.layout.filter_dialog);
                viewall_dialogue.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                TextView tv_filter_notes, tv_filter_videos, tv_filter_quiz, tv_filter_faqs, tv_filter_mcqs, tv_filter_discuss, tv_filter_activty, tv_filter_my_activity;
                ImageView imageView = viewall_dialogue.findViewById(R.id.im_close);
                tv_filter_notes = viewall_dialogue.findViewById(R.id.tv_filter_notes);
                tv_filter_videos = viewall_dialogue.findViewById(R.id.tv_filter_videos);
                tv_filter_faqs = viewall_dialogue.findViewById(R.id.tv_filter_faqs);
                tv_filter_mcqs = viewall_dialogue.findViewById(R.id.tv_filter_mcqs);
                tv_filter_discuss = viewall_dialogue.findViewById(R.id.tv_filter_discuss);
                tv_filter_activty = viewall_dialogue.findViewById(R.id.tv_filter_activty);
                tv_filter_my_activity = viewall_dialogue.findViewById(R.id.tv_filter_my_activity);
                tv_filter_quiz = viewall_dialogue.findViewById(R.id.tv_filter_quiz);
                tv_filter_notes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slelcted_filter = "notes";
                        viewall_dialogue.dismiss();
                        callActivtyDataWs();
                    }
                });
                tv_filter_videos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slelcted_filter = "videos";
                        viewall_dialogue.dismiss();
                        callActivtyDataWs();
                    }
                });
                tv_filter_faqs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slelcted_filter = "faqs";
                        viewall_dialogue.dismiss();
                        callActivtyDataWs();
                    }
                });
                tv_filter_mcqs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slelcted_filter = "mcqs";
                        viewall_dialogue.dismiss();
                        callActivtyDataWs();
                    }
                });
                tv_filter_discuss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slelcted_filter = "discuss";
                        viewall_dialogue.dismiss();
                        callActivtyDataWs();
                    }
                });
                tv_filter_activty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slelcted_filter = "activity";
                        viewall_dialogue.dismiss();
                        callActivtyDataWs();
                    }
                });
                tv_filter_notes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slelcted_filter = "user_activity";
                        viewall_dialogue.dismiss();
                        callActivtyDataWs();
                    }
                });
                tv_filter_my_activity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slelcted_filter = "friends_activity";
                        viewall_dialogue.dismiss();
                        callActivtyDataWs();
                    }
                });
                tv_filter_quiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slelcted_filter = "quiz";
                        viewall_dialogue.dismiss();
                        callActivtyDataWs();
                    }
                });

                Window window = viewall_dialogue.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                viewall_dialogue.show();

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewall_dialogue.dismiss();
                    }
                });
            }
        });
        /*{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
"user_id":"1",
"education_id":"3",
"stream_id":"4",
"subject_ids":"1,2,3",
"chapter_id":"1",
"count":"0"
}*/
        callActivtyDataWs();
        callAdvBannersWs();


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page_number = 0;
                responseBeanList = new ArrayList<>();
                callActivtyDataWs();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void callAdvBannersWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        presenter.advBanners(getActivity(), this, requestBody);
    }

    private void callActivtyDataWs() {
        page_number = 0;
        responseBeanList = new ArrayList<>();
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", KEY_SUBJECTS);
        requestBody.put("chapter_id", "0");
        requestBody.put("count", String.valueOf(page_number));
        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }
        if (slelcted_filter.equalsIgnoreCase("all_data")) {
        } else {
            requestBody.put("filter", slelcted_filter);
        }
        presenter.activityData(getActivity(), this, requestBody);
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

    private void perfirmPagination() {
        progress_bar.setVisibility(View.VISIBLE);
        page_number_test = page_number;
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", KEY_SUBJECTS);
        requestBody.put("chapter_id", "0");
        requestBody.put("count", String.valueOf(page_number));
        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }
        if (slelcted_filter.equalsIgnoreCase("all_data")) {
        } else {
            responseBeanList = new ArrayList<>();
            requestBody.put("filter", slelcted_filter);
        }
        presenter.activityData(getActivity(), this, requestBody);

        //Toast.makeText(context, "First Page is Loaded", Toast.LENGTH_SHORT).show();
//                    allCommentsPojo = new Gson().fromJson(responseString, AllCommentsPojo.class);
//                    commentsBean.addAll(allCommentsPojo.getResponse());
//                    notesCommentsAdapter = new notesCommentsAdapter(this, commentsBean);
//                    rview_comments.setAdapter(notesCommentsAdapter);
//                    notesCommentsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                //  Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());
            } else if (NetworkConstants.RequestCode.ACTIVITY_DATA == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    progress_bar.setVisibility(View.GONE);
                    dialog.dismiss();
                    activityPojo = new Gson().fromJson(responseString, ActivityPojo.class);
                    int count = activityPojo.getResponse().size();
                    int position = responseBeanList.size();
                    responseBeanList.addAll(activityPojo.getResponse());
                    page_number_test++;
                    if (page_number == 0) {
                        actvityAdapter = new ActvityAdapter(getActivity(), responseBeanList);
                        rview_activity.setAdapter(actvityAdapter);
                    } else {
                        actvityAdapter.notifyItemRangeChanged(position, count);
                        //actvityAdapter.notifyDataSetChanged();
                    }

                    ll_nested_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                        @Override
                        public void onScrollChanged() {
                            View view = (View) ll_nested_scroll.getChildAt(ll_nested_scroll.getChildCount() - 1);

                            int diff = (view.getBottom() - (ll_nested_scroll.getHeight() + ll_nested_scroll
                                    .getScrollY()));

                            if (diff == 0) {
                                // your pagination code

                                if (page_number_test - page_number == 1) {
                                    page_number++;
                                    perfirmPagination();
                                }
                            }
                        }
                    });
                    actvityAdapter.setOnItemClickListener(new ActvityAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position, String type) {
                            selected_postion = position;
                            selected_type = type;
                            switch (view.getId()) {
                                case R.id.all_ll_ping:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("sections_id", responseBeanList.get(position).getId());
                                    bundle.putString("type", type);
                                    if (type.equalsIgnoreCase("faqs") || type.equalsIgnoreCase("mcqs") || type.equalsIgnoreCase("quiz")) {

                                        if (responseBeanList.get(position).getQuestion().length() > 70) {
                                            bundle.putString("content", responseBeanList.get(position).getQuestion().substring(0, 70));
                                        } else {
                                            bundle.putString("content", responseBeanList.get(position).getQuestion());
                                        }

                                    } else {
                                        if (responseBeanList.get(position).getDescription().length() > 70) {
                                            bundle.putString("content", responseBeanList.get(position).getDescription().substring(0, 70));
                                        } else {
                                            bundle.putString("content", responseBeanList.get(position).getDescription());
                                        }

                                    }
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle);
                                    break;
                                case R.id.all_ll_unread:
                                    callReadStatus();
                                    break;
                                case R.id.all_ll_comment:
                                    if (selected_type.equalsIgnoreCase("notes")) {
                                        Bundle bundle_notes = new Bundle();
                                        bundle_notes.putString("title", responseBeanList.get(position).getHeading());
                                        bundle_notes.putString("subtitle", responseBeanList.get(position).getHeading());
                                        bundle_notes.putString("description", responseBeanList.get(position).getDescription());
                                        bundle_notes.putString("ping_count", responseBeanList.get(position).getPings());
                                        bundle_notes.putString("comments_count", responseBeanList.get(position).getComments());
                                        bundle_notes.putString("views_count", responseBeanList.get(position).getViews());
                                        bundle_notes.putString("sending_through", "notes");
                                        bundle_notes.putString("topic_id", responseBeanList.get(position).getId());

                                        Log.d("topic_id", responseBeanList.get(position).getId());
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_notes);

                                    } else if (selected_type.equalsIgnoreCase("discuss")) {
                                        Bundle bundle_discuss = new Bundle();
                                        bundle_discuss.putString("title", responseBeanList.get(position).getChapter());
                                        bundle_discuss.putString("subtitle", responseBeanList.get(position).getChapter());
                                        bundle_discuss.putString("description", responseBeanList.get(position).getDescription());
                                        bundle_discuss.putString("ping_count", responseBeanList.get(position).getPings());
                                        bundle_discuss.putString("comments_count", responseBeanList.get(position).getComments());
                                        bundle_discuss.putString("views_count", responseBeanList.get(position).getViews());
                                        bundle_discuss.putString("topic_id", responseBeanList.get(position).getId());
                                        bundle_discuss.putString("sending_through", "discuss");
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_discuss);

                                    } else if (selected_type.equalsIgnoreCase("faqs")) {
                                        Bundle bundle_faq = new Bundle();
                                        bundle_faq.putString("faq_id", responseBeanList.get(position).getId());
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN, bundle_faq);

                                    } else if (selected_type.equalsIgnoreCase("mcqs")) {

                                        Bundle bundle_mcq = new Bundle();
                                        bundle_mcq.putString("topic_id", responseBeanList.get(position).getId());
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN, bundle_mcq);

                                    } else if (selected_type.equalsIgnoreCase("videos")) {
                                        Bundle bundle2 = new Bundle();
                                        bundle2.putString("video_id", responseBeanList.get(position).getId());
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN, bundle2);
                                    } else if (selected_type.equalsIgnoreCase("activity")) {
                                        Bundle bundle_activty = new Bundle();
                                        bundle_activty.putString("topic_id", responseBeanList.get(position).getId());
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ACTIVITY_DETAILS_SCREEN, bundle_activty);
                                    }
                                    break;

                                    /* ll_item_faqs.setOnClickListener(this);
            ll_notes.setOnClickListener(this);
            ll_item_video.setOnClickListener(this);
            mcqs_ll_question.setOnClickListener(this);
            quiz_question.setOnClickListener(this);
            ll_discussion.setOnClickListener(this);
            ll_activity.setOnClickListener(this);*/
                                case R.id.ll_notes:
                                    Bundle bundle_notes = new Bundle();
                                    bundle_notes.putString("title", responseBeanList.get(position).getHeading());
                                    bundle_notes.putString("subtitle", responseBeanList.get(position).getHeading());
                                    bundle_notes.putString("description", responseBeanList.get(position).getDescription());
                                    bundle_notes.putString("ping_count", responseBeanList.get(position).getPings());
                                    bundle_notes.putString("comments_count", responseBeanList.get(position).getComments());
                                    bundle_notes.putString("views_count", responseBeanList.get(position).getViews());
                                    bundle_notes.putString("sending_through", "notes");
                                    bundle_notes.putString("topic_id", responseBeanList.get(position).getId());

                                    Log.d("topic_id", responseBeanList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_notes);

                                    break;
                                case R.id.ll_item_video:
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("video_id", responseBeanList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN, bundle2);
                                    break;
                                case R.id.mcqs_ll_question:
                                    Bundle bundle_mcq = new Bundle();
                                    bundle_mcq.putString("topic_id", responseBeanList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN, bundle_mcq);
                                    break;
                                case R.id.ll_item_faqs:

                                    Bundle bundle_faq = new Bundle();
                                    bundle_faq.putString("faq_id", responseBeanList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN, bundle_faq);
                                    break;
                                case R.id.ll_activity:
                                    Bundle bundle_activty = new Bundle();
                                    bundle_activty.putString("topic_id", responseBeanList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ACTIVITY_DETAILS_SCREEN, bundle_activty);
                                    break;
                                case R.id.ll_discussion:
                                    Bundle bundle_discuss = new Bundle();
                                    bundle_discuss.putString("title", responseBeanList.get(position).getChapter());
                                    bundle_discuss.putString("subtitle", responseBeanList.get(position).getChapter());
                                    bundle_discuss.putString("description", responseBeanList.get(position).getDescription());
                                    bundle_discuss.putString("ping_count", responseBeanList.get(position).getPings());
                                    bundle_discuss.putString("comments_count", responseBeanList.get(position).getComments());
                                    bundle_discuss.putString("views_count", responseBeanList.get(position).getViews());
                                    bundle_discuss.putString("topic_id", responseBeanList.get(position).getId());
                                    bundle_discuss.putString("sending_through", "discuss");
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_discuss);
                                    break;
                            }
                        }
                    });
                } else {

                    ll_nested_scroll.setSmoothScrollingEnabled(true);
                    progress_bar.setVisibility(View.GONE);
                    dialog.dismiss();
                }
            } else if (NetworkConstants.RequestCode.SUBMIT_AS_READ == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    responseBeanList.get(selected_postion).setReaded("yes");
                    actvityAdapter.notifyItemChanged(selected_postion);
                }
            } else if (NetworkConstants.RequestCode.ADV_BANNERS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    addBannerPojo = new Gson().fromJson(responseString, AddBannerPojo.class);
                    Picasso.with(getActivity())
                            .load(NetworkConstants.URL.Imagepath_URL + addBannerPojo.getAdv_banners().getImage())
                            .error(R.drawable.ic_blue_background)
                            .into(iv_adv_banner);
                    tv_scrolling.setText(addBannerPojo.getScrolling_text().getScrolling_text());
                    Variables.adv_banner = addBannerPojo.getAdv_banners().getImage();
                    Variables.scrolling_text = addBannerPojo.getScrolling_text().getScrolling_text();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("subject_id", responseBeanList.get(selected_postion).getSubject_id());
        requestBody.put("chapter_id", responseBeanList.get(selected_postion).getChapter());
        requestBody.put("sections_id", responseBeanList.get(selected_postion).getId());
        requestBody.put("type", selected_type);
        presenter.submit_as_read(getActivity(), this, requestBody);

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!cmntrefresh.isEmpty()) {
            cmntrefresh = "";
            callActivtyDataWs();

        }
    }


    @Override
    public void onPause() {
        super.onPause();
//        JCVideoPlayer.releaseAllVideos();

//        player_view.setPlayer(null);
        if (player != null) {
            player.setPlayWhenReady(false);
            player.stop();
            player.seekTo(0);
        }

    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (player != null) {
            player.setPlayWhenReady(false);
            player.stop();
            player.seekTo(0);
        }
    }


}
