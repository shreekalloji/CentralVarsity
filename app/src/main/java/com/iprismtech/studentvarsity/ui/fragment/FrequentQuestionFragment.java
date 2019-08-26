package com.iprismtech.studentvarsity.ui.fragment;

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
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.ExpandableListViewAdapter;
import com.iprismtech.studentvarsity.adapters.FaqsAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.fragment.FrequentQuestionFragmentContract;
import com.iprismtech.studentvarsity.mvp.presenter.fragment.FrequentQuestionFragmentlmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.AddBannerPojo;
import com.iprismtech.studentvarsity.pojos.ChaptersPojo;
import com.iprismtech.studentvarsity.pojos.FaqsListPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.ui.activity.FaqActivity;
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

public class FrequentQuestionFragment extends BaseAbstractFragment<FrequentQuestionFragmentlmpl> implements FrequentQuestionFragmentContract.IView, APIResponseCallback {

    LinearLayout ll_freqAllquestion, ll_freqCategeory;
    TextView tv_freqAllquestion, tv_freqCategeory;

    APIResponseCallback apiResponseCallback;
    private NestedScrollView ll_nested_scroll;
    private boolean response_status = false;


    RecyclerView rv_faqs;
    FaqsAdapter adapter;
    private ChaptersPojo chaptersPojo;
    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> parentHeaderInformation;
    private HashMap<String, List<String>> allChildItems = new HashMap<>();
    private String subtitle;
    private ArrayList arrayList, subtitleslist;
    private String chapter_id, subject_id;
    private FaqsListPOJO departmentPOJO;
    private List<FaqsListPOJO.ResponseBean> faqsList;
    private int selected_postion;
    private int page_number = 0;
    private String token, user_id, user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects, education_id;

    private ImageView iv_static_search, iv_search;
    private EditText et_search;
    private ImageView iv_banner;

    private AddBannerPojo addBannerPojo;
    private String cmntrefresh = "";
    private boolean scroll_status = true;

    @Override
    public void setPresenter() {
        presenter = new FrequentQuestionFragmentlmpl(this, getContext());
    }


    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_read_unread_frequestions, null);
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
        user_id = userSession.getUserDetails().get("id");
        user_name = userSession.getUserDetails().get("name");
        user_university = userSession.getUserDetails().get("university");
        education_id = userSession.getUserDetails().get("education_id");
        user_bio = userSession.getUserDetails().get("bio");
        user_profile_pic = userSession.getUserDetails().get("profileImg");
        stream = userSession.getUserDetails().get("stream_id");
        years = userSession.getUserDetails().get("years");
        user_subjects = userSession.getUserDetails().get("subject_names");
        subject_id = userSession.getUserDetails().get("subjects");

        faqsList = new ArrayList<>();

        TextView textview = (TextView) getActivity().findViewById(R.id.tv_read_faq);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.white_corner_draw);

        TextView textview_video = (TextView) getActivity().findViewById(R.id.tv_read_notes);
        textview_video.setTextColor(Color.WHITE);
        textview_video.setBackgroundResource(R.drawable.primary_color_bg);

        TextView textview_faq = (TextView) getActivity().findViewById(R.id.tv_read_video);
        textview_faq.setTextColor(Color.WHITE);
        textview_faq.setBackgroundResource(R.color.primary_light);

        TextView textview_mcq = (TextView) getActivity().findViewById(R.id.tv_read_mcq);
        textview_mcq.setTextColor(Color.WHITE);
        textview_mcq.setBackgroundResource(R.color.primary_light);

        TextView textview_quiz = (TextView) getActivity().findViewById(R.id.tv_read_quiz);
        textview_quiz.setTextColor(Color.WHITE);
        textview_quiz.setBackgroundResource(R.drawable.primary_color_bg);


        rv_faqs = view.findViewById(R.id.rview_mcqs);
        ll_freqAllquestion = view.findViewById(R.id.ll_freqAllquestion);
        ll_freqCategeory = view.findViewById(R.id.ll_freqCategeory);
        tv_freqAllquestion = view.findViewById(R.id.tv_freqAllquestion);
        tv_freqCategeory = view.findViewById(R.id.tv_freqCategeory);
        expandableListView = view.findViewById(R.id.expandableListView);
        ll_nested_scroll = view.findViewById(R.id.ll_nested_scroll_faqs);
        iv_banner = view.findViewById(R.id.iv_banner);

        //  iv_static_search = (ImageView) getActivity().findViewById(R.id.iv_static_search);
        iv_search = (ImageView) getActivity().findViewById(R.id.iv_search);
        et_search = (EditText) getActivity().findViewById(R.id.et_search);

        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   iv_static_search.setVisibility(View.GONE);
                iv_search.setVisibility(View.VISIBLE);
            }
        });
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.search_text = et_search.getText().toString();
                callFAQsWs();
            }
        });


        Picasso.with(getActivity())
                .load(NetworkConstants.URL.Imagepath_URL + Variables.adv_banner)
                .error(R.drawable.ic_blue_background)
                .into(iv_banner);
        tv_freqAllquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_freqAllquestion.setVisibility(View.VISIBLE);
                ll_freqCategeory.setVisibility(View.GONE);
                // tv_freqAllquestion.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
                tv_freqAllquestion.setBackgroundResource(R.drawable.faqs_selected);
                // tv_freqCategeory.setBackgroundColor(Color.WHITE);
                tv_freqCategeory.setBackgroundResource(R.drawable.categories_unselelcted);
                tv_freqAllquestion.setTextColor(Color.WHITE);
                tv_freqCategeory.setTextColor(Color.BLACK);


                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                requestBody.put("user_id", user_id);
                requestBody.put("education_id", education_id);
                requestBody.put("stream_id", stream);
                requestBody.put("subject_ids", subject_id);
                requestBody.put("chapter_id", " ");
                requestBody.put("count", String.valueOf(page_number));
                if (Variables.search_text != null) {
                    requestBody.put("search", Variables.search_text);
                }
                presenter.faqs(getActivity(), apiResponseCallback, requestBody);


            }
        });
        iv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(addBannerPojo.getAdv_banners().getAdvt_url()));
                startActivity(browserIntent);
            }
        });


        tv_freqCategeory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_freqAllquestion.setVisibility(View.GONE);
                ll_freqCategeory.setVisibility(View.VISIBLE);
                //  tv_freqCategeory.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
                tv_freqCategeory.setBackgroundResource(R.drawable.categories_selected);
                //tv_freqAllquestion.setBackgroundColor(Color.WHITE);
                tv_freqAllquestion.setBackgroundResource(R.drawable.faqs_unselect);
                tv_freqCategeory.setTextColor(Color.WHITE);
                tv_freqAllquestion.setTextColor(Color.BLACK);
                scroll_status = false;
                callFAqsWs();

            }
        });

//        {
//            "token":
//            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
//                    "user_id":"1",
//                "education_id":"3",
//                "stream_id":"4",
//                "subject_ids":"1,2,3",
//                "chapter_id":"1",
//                "count":"0"
//        }

        String KEY_USERID = userSession.getUserDetails().get("id");
        String KEY_TOKEN = userSession.getUserDetails().get("token");
        String KEY_EDUCATION_ID = userSession.getUserDetails().get("education_id");
        String KEY_STREAM_ID = userSession.getUserDetails().get("stream_id");
        String KEY_SUBJECTS = userSession.getUserDetails().get("subjects");


        if (KEY_USERID != null) {
            Map<String, String> requestBody = new HashMap<>();

            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("education_id", education_id);
            requestBody.put("stream_id", stream);
            requestBody.put("subject_ids", subject_id);
            requestBody.put("chapter_id", " ");
            requestBody.put("count", String.valueOf(page_number));

            if (Variables.search_text != null) {
                requestBody.put("search", Variables.search_text);
            }

            presenter.faqs(getActivity(), apiResponseCallback, requestBody);

        }
        callAdvBannersWs();


    }

    private void callAdvBannersWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        presenter.advBanners(getActivity(), this, requestBody);
    }

    private void callFAqsWs() {
        Map<String, String> requestBody_chapters = new HashMap<>();
        requestBody_chapters.put("token", token);
        requestBody_chapters.put("education_id", education_id);
        requestBody_chapters.put("stream_id", stream);
        requestBody_chapters.put("user_id", user_id);
        requestBody_chapters.put("subject_ids", subject_id);
        requestBody_chapters.put("type", "faqs");
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
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());
            } else if (NetworkConstants.RequestCode.FAQS == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    response_status = true;
                    rv_faqs.setVisibility(View.VISIBLE);
                    //Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                    departmentPOJO = new Gson().fromJson(responseString, FaqsListPOJO.class);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rv_faqs.setLayoutManager(mLayoutManager);
                    rv_faqs.setItemAnimator(new DefaultItemAnimator());

                    faqsList.addAll(departmentPOJO.getResponse());

                    //Adding Adapter to recyclerView
                    if (page_number == 0) {
                        adapter = new FaqsAdapter(faqsList, getActivity());
                        rv_faqs.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } else {
                        adapter.notifyDataSetChanged();
                    }

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
                                        page_number++;
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


                    adapter.setOnItemClickListener(new FaqsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_postion = position;
                            switch (view.getId()) {
                                case R.id.tv_question:
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("faq_id", faqsList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN, bundle2);
                                    break;
                                case R.id.ll_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", faqsList.get(position).getId());
                                    bundle_ping.putString("type", "faqs");

                                    if (faqsList.get(position).getQuestion().length() > 70) {
                                        bundle_ping.putString("content", faqsList.get(position).getQuestion().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", faqsList.get(position).getQuestion());
                                    }

                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);
                                    break;
                                case R.id.ll_comment:
                                    Bundle bundle_comment = new Bundle();
                                    bundle_comment.putString("faq_id", faqsList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN, bundle_comment);
                                    break;
                                case R.id.tv_cmntcount:
                                    Bundle bundle_comment_ = new Bundle();
                                    bundle_comment_.putString("faq_id", faqsList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN, bundle_comment_);
                                    break;
                                case R.id.ll_unread:
                                    callFAQsReadStatus();
                                    break;
                            }


                        }
                    });
                } else {

                    // Toast.makeText(getActivity(), "No Data for selected Category", Toast.LENGTH_SHORT).show();
                    response_status = false;
                    if (page_number == 0) {
                        rv_faqs.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
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
                            if (jsobjsub.optString("topic_count").equalsIgnoreCase(jsobjsub.optString("read_count"))) {
                                String text = jsobjsub.optString("chapter") + "<font color=#397f00>" + "(" + jsobjsub.optString("read_count") + "/" + jsobjsub.optString("topic_count") + ")" + "</font> ";

                                //  subtitle = jsobjsub.optString("chapter") + "(" + jsobjsub.optString("read_count") + "/" + jsobjsub.optString("topic_count") + ")";
                                subtitle = text;
                            } else {


                                String text = jsobjsub.optString("chapter") + "<font color=#e82f2f>" + "(" + jsobjsub.optString("read_count") + "/" + jsobjsub.optString("topic_count") + ")" + "</font> ";
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
                                                Intent intent = new Intent(getActivity(), FaqActivity.class);
                                                intent.putExtra("chapter_id", chapter_id);
                                                intent.putExtra("subject_id", subject_id);
                                                intent.putExtra("topic", chaptersPojo.getResponse().get(groupPosition).getSubject() + " " + chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getChapter());
                                                intent.putExtra("read_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getRead_count());
                                                intent.putExtra("topic_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getTopic_count());

                                                Bundle screenLaunchAnimation =
                                                        ActivityOptions.makeCustomAnimation(ApplicationController.getInstance().getContext(), R.anim.animation_click, R.anim.animation_show).toBundle();
                                                getActivity().startActivity(intent, screenLaunchAnimation);
//                                                callFAQsWs();
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

            } else if (NetworkConstants.RequestCode.SUBMIT_AS_READ == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    faqsList.get(selected_postion).setReaded("yes");
                    adapter.notifyItemChanged(selected_postion);
                    adapter.notifyDataSetChanged();
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
        requestBody.put("user_id", user_id);
        requestBody.put("education_id", education_id);
        requestBody.put("stream_id", stream);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", " ");
        requestBody.put("count", String.valueOf(page_number));
        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }
        presenter.faqs(getActivity(), apiResponseCallback, requestBody);

    }

    private void callFAQsReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("subject_id", faqsList.get(selected_postion).getSubject_id());
        requestBody.put("chapter_id", faqsList.get(selected_postion).getChapter());
        requestBody.put("sections_id", faqsList.get(selected_postion).getId());
        requestBody.put("type", "faqs");
        presenter.submit_as_read(getActivity(), this, requestBody);

    }

    private void callFAQsWs() {
        ll_freqAllquestion.setVisibility(View.VISIBLE);
        ll_freqCategeory.setVisibility(View.GONE);
        tv_freqAllquestion.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
        tv_freqCategeory.setBackgroundColor(Color.WHITE);
        tv_freqAllquestion.setTextColor(Color.WHITE);
        tv_freqCategeory.setTextColor(Color.BLACK);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("education_id", education_id);
        requestBody.put("stream_id", stream);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", String.valueOf(page_number));

//            requestBody.put("token", KEY_TOKEN);
//            requestBody.put("user_id", KEY_USERID);
//            requestBody.put("education_id", KEY_EDUCATION_ID);
//            requestBody.put("stream_id", KEY_STREAM_ID);
//            requestBody.put("subject_ids", KEY_SUBJECTS);
//            requestBody.put("chapter_id", "1");
//            requestBody.put("count", "0");
        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }
        presenter.faqs(getActivity(), apiResponseCallback, requestBody);
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

//            userSession = new UserSession(getActivity());
//            token = userSession.getUserDetails().get("token");
//            user_id = userSession.getUserDetails().get("id");
//            subject_id = userSession.getUserDetails().get("subjects");
//
//            ll_freqAllquestion.setVisibility(View.GONE);
//            ll_freqCategeory.setVisibility(View.VISIBLE);
//            //  tv_freqCategeory.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
//            tv_freqCategeory.setBackgroundResource(R.drawable.categories_selected);
//            //tv_freqAllquestion.setBackgroundColor(Color.WHITE);
//            tv_freqAllquestion.setBackgroundResource(R.drawable.faqs_unselect);
//            tv_freqCategeory.setTextColor(Color.WHITE);
//            tv_freqAllquestion.setTextColor(Color.BLACK);
//            callFAqsWs();

            expandableListViewAdapter.notifyDataSetChanged();
        }
    }

}
