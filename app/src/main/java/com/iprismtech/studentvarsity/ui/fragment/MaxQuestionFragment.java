package com.iprismtech.studentvarsity.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.iprismtech.studentvarsity.adapters.MCQsAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.base.BaseAbstractFragment;
import com.iprismtech.studentvarsity.mvp.contract.fragment.MCQsFragContract;
import com.iprismtech.studentvarsity.mvp.presenter.fragment.MCQsFragImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.AddBannerPojo;
import com.iprismtech.studentvarsity.pojos.ChaptersPojo;
import com.iprismtech.studentvarsity.pojos.MCQsPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.ui.activity.McqActivity;
import com.iprismtech.studentvarsity.ui.activity.NotesActivty;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxQuestionFragment extends BaseAbstractFragment<MCQsFragImpl> implements View.OnClickListener, MCQsFragContract.IView, APIResponseCallback {
    private LinearLayout ll_freqAllquestion, ll_freqCategeory;
    private TextView tv_freqAllquestion, tv_freqCategeory;
    private RecyclerView rview_mcqs;
    private MCQsPojo mcQsPojo;
    private List<MCQsPojo.ResponseBean> quizList;
    private LinearLayoutManager manager;
    private NestedScrollView ll_nested_scroll;
    private int count = 0;
    private MCQsAdapter mcQsAdapter;
    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> parentHeaderInformation;
    private HashMap<String, List<String>> allChildItems = new HashMap<>();
    String subtitle;
    ArrayList arrayList, subtitleslist;
    private ChaptersPojo chaptersPojo;
    private String chapter_id, subject_id;
    private int selected_position;
    private String token, user_id, user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects, education_id;
    private UserSession userSession;
    private ImageView iv_static_search, iv_search;
    private EditText et_search;
    private boolean response_status = false;
    private int page_number = 0;
    private ImageView iv_banner;
    private String cmntrefresh = "";
    private AddBannerPojo addBannerPojo;
    private boolean scroll_status = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.start();
        return view;
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_read_unread_mcqs, null);
        return view;
    }


    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        TextView textview = (TextView) getActivity().findViewById(R.id.tv_read_mcq);
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

        TextView textview_quiz = (TextView) getActivity().findViewById(R.id.tv_read_quiz);
        textview_quiz.setTextColor(Color.WHITE);
        textview_quiz.setBackgroundResource(R.drawable.primary_color_bg);

        quizList = new ArrayList<>();
        ll_freqAllquestion = view.findViewById(R.id.ll_freqAllquestion);
        ll_freqCategeory = view.findViewById(R.id.ll_freqCategeory);
        tv_freqAllquestion = view.findViewById(R.id.tv_freqAllquestion);
        tv_freqCategeory = view.findViewById(R.id.tv_freqCategeory);
        rview_mcqs = view.findViewById(R.id.rview_mcqs);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_mcqs.setLayoutManager(manager);
        Variables.rb_chekced = "false";
        expandableListView = view.findViewById(R.id.expandableListView);
        ll_nested_scroll = view.findViewById(R.id.ll_nested_scroll_mcqs);
        iv_banner = view.findViewById(R.id.iv_banner);


        //  iv_static_search = (ImageView) getActivity().findViewById(R.id.iv_static_search);
        iv_search = (ImageView) getActivity().findViewById(R.id.iv_search);
        et_search = (EditText) getActivity().findViewById(R.id.et_search);
        et_search = (EditText) getActivity().findViewById(R.id.et_search);

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
                callMCQsWs();
            }
        });


        Picasso.with(getActivity())
                .load(NetworkConstants.URL.Imagepath_URL + Variables.adv_banner)
                .error(R.drawable.ic_blue_background)
                .into(iv_banner);

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

        callMCQsWs();

        tv_freqAllquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_freqAllquestion.setVisibility(View.VISIBLE);
                ll_freqCategeory.setVisibility(View.GONE);
                //   tv_freqAllquestion.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
                tv_freqAllquestion.setBackgroundResource(R.drawable.faqs_selected);
                //tv_freqCategeory.setBackgroundColor(Color.WHITE);
                tv_freqCategeory.setBackgroundResource(R.drawable.categories_unselelcted);
                tv_freqAllquestion.setTextColor(Color.WHITE);
                tv_freqCategeory.setTextColor(Color.BLACK);

                callMCQsWs();


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
                // tv_freqCategeory.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
                tv_freqCategeory.setBackgroundResource(R.drawable.categories_selected);
                // tv_freqAllquestion.setBackgroundColor(Color.WHITE);
                tv_freqAllquestion.setBackgroundResource(R.drawable.faqs_unselect);
                tv_freqCategeory.setTextColor(Color.WHITE);
                tv_freqAllquestion.setTextColor(Color.BLACK);

                scroll_status = false;
                callCatWs();


            }
        });

        callAdvBannersWs();
    }

    private void callAdvBannersWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        presenter.advBanners(getActivity(), this, requestBody);
    }

    private void callCatWs() {
        Map<String, String> requestBody_chapters = new HashMap<>();
        requestBody_chapters.put("token", token);
        requestBody_chapters.put("education_id", education_id);
        requestBody_chapters.put("stream_id", stream);
        requestBody_chapters.put("user_id", user_id);
        requestBody_chapters.put("subject_ids", subject_id);
        presenter.getChapters(getActivity(), this, requestBody_chapters);
        parentHeaderInformation = new ArrayList<String>();
        HashMap<String, List<String>> allChildItems = new HashMap<>();
    }

    private void callMCQsWs() {
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
        presenter.getMCQs(getActivity(), this, requestBody);
    }


    @Override
    public void setPresenter() {
        presenter = new MCQsFragImpl(this, getActivity());
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());

            } else if (NetworkConstants.RequestCode.GET_MCQS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    response_status = true;
                    rview_mcqs.setVisibility(View.VISIBLE);
                    mcQsPojo = new Gson().fromJson(responseString, MCQsPojo.class);
                    quizList.addAll(mcQsPojo.getResponse());
                    if (page_number == 0) {

                        mcQsAdapter = new MCQsAdapter(getActivity(), quizList);
                        rview_mcqs.setAdapter(mcQsAdapter);
                        mcQsAdapter.notifyDataSetChanged();
                    } else {
                        mcQsAdapter.notifyDataSetChanged();
                    }
//                    ll_nested_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//                        @Override
//                        public void onScrollChanged() {
//                            View view = (View) ll_nested_scroll.getChildAt(ll_nested_scroll.getChildCount() - 1);
//
//                            int diff = (view.getBottom() - (ll_nested_scroll.getHeight() + ll_nested_scroll
//                                    .getScrollY()));
//
//                            if (diff == 0) {
//                                // your pagination code
//                                page_number ++;
//                                //perfirmPagination();
//                            }
//                        }
//                    });


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

                    mcQsAdapter.setOnItemClickListener(new MCQsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_position = position;
                            switch (view.getId()) {
                                case R.id.ll_question:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("topic_id", quizList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN, bundle);
                                    break;
                                case R.id.ll_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", quizList.get(position).getId());
                                    bundle_ping.putString("type", "mcqs");
                                    if (quizList.get(position).getQuestion().length() > 70) {
                                        bundle_ping.putString("content", quizList.get(position).getQuestion().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", quizList.get(position).getQuestion());
                                    }
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);
                                    break;
                                case R.id.ll_comment:

                                    Bundle bundle_comment = new Bundle();
                                    bundle_comment.putString("topic_id", quizList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN, bundle_comment);
                                    break;
                                case R.id.tv_comments_count:

                                    Bundle bundle_comment_ = new Bundle();
                                    bundle_comment_.putString("topic_id", quizList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN, bundle_comment_);
                                    break;
                                case R.id.ll_unread:
                                    callMCQsReadStatus();
                                    break;
                            }
                        }
                    });

                } else {
                    //Toast.makeText(getActivity(), "No Data for selected Category", Toast.LENGTH_SHORT).show();
                    response_status = false;
                    if (page_number == 0) {
                        rview_mcqs.setVisibility(View.GONE);
                        mcQsAdapter.notifyDataSetChanged();
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
                                                Intent intent = new Intent(getActivity(), McqActivity.class);
                                                intent.putExtra("chapter_id", chapter_id);
                                                intent.putExtra("subject_id", subject_id);
                                                intent.putExtra("topic", chaptersPojo.getResponse().get(groupPosition).getSubject() + " " + chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getChapter());
                                                intent.putExtra("read_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getRead_count());
                                                intent.putExtra("topic_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getTopic_count());

                                                Bundle screenLaunchAnimation =
                                                        ActivityOptions.makeCustomAnimation(ApplicationController.getInstance().getContext(), R.anim.animation_click, R.anim.animation_show).toBundle();
                                                getActivity().startActivity(intent, screenLaunchAnimation);
                                                // callMCQsFilterdWs();
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
                    quizList.get(selected_position).setReaded("yes");
                    mcQsAdapter.notifyItemChanged(selected_position);
                    mcQsAdapter.notifyDataSetChanged();
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
        } catch (Exception e) {
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
        presenter.getMCQs(getActivity(), this, requestBody);

    }

    private void callMCQsReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("subject_id", quizList.get(selected_position).getSubject_id());
        requestBody.put("chapter_id", quizList.get(selected_position).getChapter_id());
        requestBody.put("sections_id", quizList.get(selected_position).getId());
        requestBody.put("type", "mcqs");
        presenter.submit_as_read(getActivity(), this, requestBody);

    }

    private void callMCQsFilterdWs() {

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
        requestBody.put("count", String.valueOf(count));
        presenter.getMCQs(getActivity(), this, requestBody);


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
//            // tv_freqCategeory.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
//            tv_freqCategeory.setBackgroundResource(R.drawable.categories_selected);
//            // tv_freqAllquestion.setBackgroundColor(Color.WHITE);
//            tv_freqAllquestion.setBackgroundResource(R.drawable.faqs_unselect);
//            tv_freqCategeory.setTextColor(Color.WHITE);
//            tv_freqAllquestion.setTextColor(Color.BLACK);
//
//
//            callCatWs();
            expandableListViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void onClick(View v) {

    }
}
