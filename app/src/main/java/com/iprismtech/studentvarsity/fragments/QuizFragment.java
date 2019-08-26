package com.iprismtech.studentvarsity.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.ExpandableListViewAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.pojos.AddBannerPojo;
import com.iprismtech.studentvarsity.pojos.ChaptersPojo;
import com.iprismtech.studentvarsity.pojos.QuizChaptersPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.adapters.QuizsAdapter;
import com.iprismtech.studentvarsity.mvp.contract.fragment.QuizFragmentContract;
import com.iprismtech.studentvarsity.mvp.presenter.fragment.QuizFragmentlmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.QuizsListPOJO;
import com.iprismtech.studentvarsity.ui.activity.QuizActivity;
import com.iprismtech.studentvarsity.ui.fragment.BaseAbstractFragment;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizFragment extends BaseAbstractFragment<QuizFragmentlmpl> implements QuizFragmentContract.IView, APIResponseCallback {

    private LinearLayout ll_quize, ll_quiz_cat_layout;
    private TextView tv_quize, tv_quizeCategeory, ll_quiz_xam;
    private APIResponseCallback apiResponseCallback;
    private RecyclerView rv_quizs;
    private QuizsAdapter adapter;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private ExpandableListView expandableListView;
    private QuizsListPOJO departmentPOJO;
    private List<QuizsListPOJO.ResponseBean> quizList;
    private List<String> parentHeaderInformation;
    private HashMap<String, List<String>> allChildItems = new HashMap<>();
    String subtitle;
    private NestedScrollView ll_nested_scroll;
    ArrayList arrayList, subtitleslist;
    private ChaptersPojo chaptersPojo;
    private String token, KEY_EDUCATION_ID, KEY_YEARS, KEY_STREAM_ID, KEY_SUBJECTS, KEY_USERID, KEY_UserName, KEY_MOBILENO, KEY_PROFILE, KEY_UNIVERSITY, KEY_COUNTRY_ID, KEY_CITY_ID, KEY_BIO;

    private String chapter_id, subject_id;
    private boolean response_status = false;
    private int page_number = 0;
    private ImageView iv_banner;
    private AddBannerPojo addBannerPojo;
    private String cmntrefresh = "";
    private boolean scroll_status = true;

    @Override
    public void setPresenter() {
        presenter = new QuizFragmentlmpl(this, getContext());
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_read_unread_quiz, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        apiResponseCallback = this;


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


        quizList = new ArrayList<>();

        TextView textview = (TextView) getActivity().findViewById(R.id.tv_read_quiz);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.white_corner_draw);

        TextView textview_video = (TextView) getActivity().findViewById(R.id.tv_read_notes);
        textview_video.setTextColor(Color.WHITE);
        textview_video.setBackgroundResource(R.drawable.primary_color_bg);

        TextView textview_faq = (TextView) getActivity().findViewById(R.id.tv_read_video);
        textview_faq.setTextColor(Color.WHITE);
        textview_faq.setBackgroundResource(R.color.primary_light);

        TextView textview_mcq = (TextView) getActivity().findViewById(R.id.tv_read_faq);
        textview_mcq.setTextColor(Color.WHITE);
        textview_mcq.setBackgroundResource(R.color.primary_light);

        TextView textview_quiz = (TextView) getActivity().findViewById(R.id.tv_read_mcq);
        textview_quiz.setTextColor(Color.WHITE);
        textview_quiz.setBackgroundResource(R.color.primary_light);
        ll_quize = view.findViewById(R.id.ll_quize);
        ll_quiz_cat_layout = view.findViewById(R.id.ll_freqCategeory);
        tv_quize = view.findViewById(R.id.tv_quize);
        tv_quizeCategeory = view.findViewById(R.id.tv_quizeCategory);
        expandableListView = view.findViewById(R.id.expandableListView);
        ll_nested_scroll = view.findViewById(R.id.ll_nested_scroll_quiz);
        iv_banner = view.findViewById(R.id.iv_banner);


        Picasso.with(getActivity())
                .load(NetworkConstants.URL.Imagepath_URL + Variables.adv_banner)
                .error(R.drawable.ic_blue_background)
                .into(iv_banner);

        tv_quize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_quize.setVisibility(View.VISIBLE);
                ll_quiz_cat_layout.setVisibility(View.GONE);
                // tv_quize.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
                tv_quize.setBackgroundResource(R.drawable.notes_selected);
                //  tv_quizeCategeory.setBackgroundColor(Color.WHITE);
                tv_quizeCategeory.setBackgroundResource(R.drawable.categories_unselelcted);
                tv_quize.setTextColor(Color.WHITE);
                tv_quizeCategeory.setTextColor(Color.BLACK);

                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                requestBody.put("user_id", KEY_USERID);
                requestBody.put("education_id", KEY_EDUCATION_ID);
                requestBody.put("stream_id", KEY_STREAM_ID);
                requestBody.put("subject_ids", KEY_SUBJECTS);
                requestBody.put("count", String.valueOf(page_number));

                if (Variables.search_text != null) {
                    requestBody.put("search", Variables.search_text);
                }
                presenter.quizs(getActivity(), apiResponseCallback, requestBody);


            }
        });

        tv_quizeCategeory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_quiz_cat_layout.setVisibility(View.VISIBLE);
                ll_quize.setVisibility(View.GONE);
                // tv_quizeCategeory.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
                tv_quizeCategeory.setBackgroundResource(R.drawable.categories_selected);
                //tv_quize.setBackgroundColor(Color.WHITE);
                tv_quize.setBackgroundResource(R.drawable.notes_unselected);
                tv_quizeCategeory.setTextColor(Color.WHITE);
                tv_quize.setTextColor(Color.BLACK);
                scroll_status = false;
                callChapterWs();

            }
        });

        iv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(addBannerPojo.getAdv_banners().getAdvt_url()));
                startActivity(browserIntent);
            }
        });


        rv_quizs = view.findViewById(R.id.rv_quizs);

//
//        String KEY_USERID = userSession.getUserDetails().get("id");
//        String KEY_TOKEN = userSession.getUserDetails().get("token");
//        String KEY_EDUCATION_ID = userSession.getUserDetails().get("education_id");
//        String KEY_STREAM_ID = userSession.getUserDetails().get("stream_id");
//        String KEY_SUBJECTS = userSession.getUserDetails().get("subjects");


        if (KEY_USERID != null) {

//            {
//                "token":
//                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
//                        "education_id":"3",
//                    "stream_id":"4",
//                    "subject_ids":"1,2,3"
//            }

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", KEY_USERID);
            requestBody.put("education_id", KEY_EDUCATION_ID);
            requestBody.put("stream_id", KEY_STREAM_ID);
            requestBody.put("subject_ids", KEY_SUBJECTS);
            requestBody.put("count", String.valueOf(page_number));

            if (Variables.search_text != null) {
                requestBody.put("search", Variables.search_text);
            }
            presenter.quizs(getActivity(), apiResponseCallback, requestBody);
        }

        callAdvBannersWs();

    }

    private void callAdvBannersWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        presenter.advBanners(getActivity(), this, requestBody);
    }

    private void callChapterWs() {
        Map<String, String> requestBody_chapters = new HashMap<>();
        requestBody_chapters.put("token", token);
        requestBody_chapters.put("education_id", KEY_EDUCATION_ID);
        requestBody_chapters.put("stream_id", KEY_STREAM_ID);
        requestBody_chapters.put("user_id", KEY_USERID);
        requestBody_chapters.put("subject_ids", KEY_SUBJECTS);
        requestBody_chapters.put("type", "quiz");
        presenter.getChapters(getActivity(), this, requestBody_chapters);
        parentHeaderInformation = new ArrayList<String>();
        HashMap<String, List<String>> allChildItems = new HashMap<>();


    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        //  Toast.makeText(getActivity(), responseString, Toast.LENGTH_SHORT).show();

        Log.d("responseDepartment", responseString);

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

            } else if (NetworkConstants.RequestCode.QUIZS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    rv_quizs.setVisibility(View.VISIBLE);
                    response_status = true;
                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                    departmentPOJO = new Gson().fromJson(responseString, QuizsListPOJO.class);

                    quizList.addAll(departmentPOJO.getResponse());


                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rv_quizs.setLayoutManager(mLayoutManager);
                    rv_quizs.setItemAnimator(new DefaultItemAnimator());

                    //Adding Adapter to recyclerView
                    adapter = new QuizsAdapter(quizList, getActivity());
                    rv_quizs.setAdapter(adapter);


                    if (scroll_status) {

                        if (response_status) {
                            ll_nested_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                                @Override
                                public void onScrollChanged() {
                                    View view = (View) ll_nested_scroll.getChildAt(ll_nested_scroll.getChildCount() - 1);

                                    int diff = (view.getBottom() - (ll_nested_scroll.getHeight() + ll_nested_scroll
                                            .getScrollY()));

                                    if (diff == 0) {
                                        // your pagination code
                                        page_number += 1;
                                        if (response_status) {
                                            perfirmPagination();
                                        }
                                    }
                                }
                            });
                        } else {
                            ll_nested_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                                @Override
                                public void onScrollChanged() {
                                    View view = (View) ll_nested_scroll.getChildAt(ll_nested_scroll.getChildCount() - 1);

                                    int diff = (view.getBottom() - (ll_nested_scroll.getHeight() + ll_nested_scroll
                                            .getScrollY()));

                                    if (diff == 0) {
                                        // your pagination cod
                                    }
                                }
                            });
                        }
                    }

                    adapter.setOnItemClickListener(new QuizsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (view.getId()) {
                                case R.id.ll_quiz:
                                    if (quizList.get(position).getTotal_questions() > 0) {

                                        Bundle bundle2 = new Bundle();
                                        bundle2.putString("id", quizList.get(position).getId());
                                        bundle2.putString("right_count", String.valueOf(quizList.get(position).getRight_answers()));
                                        bundle2.putString("total_count", String.valueOf(quizList.get(position).getTotal_questions()));
                                        bundle2.putString("quiz_name", quizList.get(position).getTitle());
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_QUESTIONRIGHTWRONG_SCREEN, bundle2);

                                    } else if (quizList.get(position).getQuestions_count() == 0) {
                                        Toast.makeText(getActivity(), "No questions to Attempt ", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Bundle bundle2 = new Bundle();
                                        bundle2.putString("quizs_id", quizList.get(position).getId());
                                        bundle2.putString("questions_count", String.valueOf(quizList.get(position).getQuestions_count()));
                                        bundle2.putString("quiz_name", quizList.get(position).getTitle());
                                        bundle2.putString("quiz_time", quizList.get(position).getTime());
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_QUIZ_LIST_SCREEN, bundle2);
                                    }
                                    break;
                            }
                        }
                    });

                } else {

                    response_status = false;
                    if (page_number == 0) {
                        rv_quizs.setVisibility(View.GONE);
                    }

                }
            } else if (NetworkConstants.RequestCode.GET_CHAPTERS_DATA == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    chaptersPojo = new Gson().fromJson(responseString, ChaptersPojo.class);
                    JSONArray jsoncat = jsonObject.optJSONArray("response");


                    // allChildItems = new HashMap<>();

                    for (int i = 0; i < jsoncat.length(); i++) {
                        JSONObject jsonObject1 = jsoncat.optJSONObject(i);
                        String title = jsonObject1.optString("subject");

                        JSONArray jsonsub_categories = jsonObject1.optJSONArray("chapters");


                        parentHeaderInformation.add(title);
                        arrayList = new ArrayList();
                        for (int j = 0; j < jsonsub_categories.length(); j++) {
                            JSONObject jsobjsub = jsonsub_categories.optJSONObject(j);
                            if (jsobjsub.optString("topic_count").equalsIgnoreCase(jsobjsub.optString("topics_completed_count"))) {
                                String text = jsobjsub.optString("chapter") + "<font color=#397f00>" + "(" + jsobjsub.optString("topics_completed_count") + "/" + jsobjsub.optString("topic_count") + ")" + "</font> ";

                                //  subtitle = jsobjsub.optString("chapter") + "(" + jsobjsub.optString("read_count") + "/" + jsobjsub.optString("topic_count") + ")";
                                subtitle = text;
                            } else {


                                String text = jsobjsub.optString("chapter") + "<font color=#e82f2f>" + "(" + jsobjsub.optString("topics_completed_count") + "/" + jsobjsub.optString("topic_count") + ")" + "</font> ";
                                subtitle = text;
                            }
                            arrayList.add(subtitle);
                            allChildItems.put(title, arrayList);
                        }
                    }
                    expandableListViewAdapter = new ExpandableListViewAdapter(chaptersPojo, parentHeaderInformation, allChildItems, getActivity());
                    expandableListView.setAdapter(expandableListViewAdapter);
                    expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                            try {
                                if (chaptersPojo.getResponse().get(groupPosition).getChapters().size() < 1) {
                                    Toast.makeText(getActivity(), "No Data", Toast.LENGTH_SHORT).show();
                                } else {

                                    expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                                        @Override
                                        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                                            if (chaptersPojo.getResponse().get(groupPosition).getChapters().size() < 1) {
                                                Toast.makeText(getActivity(), "No Chapters Assigned for selected category", Toast.LENGTH_SHORT).show();
                                            } else {
                                                chapter_id = chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getId();
                                                subject_id = chaptersPojo.getResponse().get(groupPosition).getId();
                                                cmntrefresh = "abc";
                                                Intent intent = new Intent(getActivity(), QuizActivity.class);
                                                intent.putExtra("chapter_id", chapter_id);
                                                intent.putExtra("subject_id", subject_id);
                                                intent.putExtra("read_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getRead_count());
                                                intent.putExtra("topic_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getTopic_count());
                                                intent.putExtra("topic", chaptersPojo.getResponse().get(groupPosition).getSubject() + " " + chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getChapter());

                                                Bundle screenLaunchAnimation =
                                                        ActivityOptions.makeCustomAnimation(ApplicationController.getInstance().getContext(), R.anim.animation_click, R.anim.animation_show).toBundle();
                                                getActivity().startActivity(intent, screenLaunchAnimation);
                                                //  callQuizFiltedWs();
                                            }
                                            return false;
                                        }
                                    });
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            return false;
                        }
                    });

                }
            } else if (NetworkConstants.RequestCode.ADV_BANNERS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    addBannerPojo = new Gson().fromJson(responseString, AddBannerPojo.class);
                    Picasso.with(getActivity())
                            .load(NetworkConstants.URL.Imagepath_URL + addBannerPojo.getAdv_banners().getImage())
                            .error(R.drawable.ic_blue_background)
                            .into(iv_banner);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void perfirmPagination() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", KEY_SUBJECTS);
        requestBody.put("chapter_id", " ");
        requestBody.put("count", String.valueOf(page_number));

        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }
        presenter.quizs(getActivity(), apiResponseCallback, requestBody);

    }

    private void callQuizFiltedWs() {
        quizList = new ArrayList<>();
        page_number = 0;
        ll_quize.setVisibility(View.VISIBLE);
        ll_quiz_cat_layout.setVisibility(View.GONE);
        tv_quize.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
        tv_quizeCategeory.setBackgroundColor(Color.WHITE);
        tv_quize.setTextColor(Color.WHITE);
        tv_quizeCategeory.setTextColor(Color.BLACK);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", String.valueOf(page_number));
        presenter.quizs(getActivity(), apiResponseCallback, requestBody);

    }


    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {
        Toast.makeText(getActivity(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!cmntrefresh.isEmpty()) {
            cmntrefresh = "";
            userSession = new UserSession(getActivity());
            token = userSession.getUserDetails().get("token");
            subject_id = userSession.getUserDetails().get("subjects");

            ll_quiz_cat_layout.setVisibility(View.VISIBLE);
            ll_quize.setVisibility(View.GONE);
            // tv_quizeCategeory.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
            tv_quizeCategeory.setBackgroundResource(R.drawable.categories_selected);
            //tv_quize.setBackgroundColor(Color.WHITE);
            tv_quize.setBackgroundResource(R.drawable.notes_unselected);
            tv_quizeCategeory.setTextColor(Color.WHITE);
            tv_quize.setTextColor(Color.BLACK);

            callChapterWs();
        }
    }
}
