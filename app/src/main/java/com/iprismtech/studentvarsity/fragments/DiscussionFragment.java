package com.iprismtech.studentvarsity.fragments;

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
import com.iprismtech.studentvarsity.adapters.DiscussionsAdapter;
import com.iprismtech.studentvarsity.adapters.ExpandableListViewAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.fragment.DiscussionFragContract;
import com.iprismtech.studentvarsity.mvp.presenter.fragment.DiscussionsFragImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.AddBannerPojo;
import com.iprismtech.studentvarsity.pojos.ChaptersPojo;
import com.iprismtech.studentvarsity.pojos.DiscussionsPojo;
import com.iprismtech.studentvarsity.pojos.NotesPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.base.BaseAbstractFragment;
import com.iprismtech.studentvarsity.customviews.CustomTextViewBold;
import com.iprismtech.studentvarsity.ui.activity.DiscussActivity;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscussionFragment extends BaseAbstractFragment<DiscussionsFragImpl> implements View.OnClickListener, DiscussionFragContract.IView, APIResponseCallback {

    CustomTextViewBold tv_discussion, tv_discussCategory, tv_videoview;
    LinearLayout ll_discuss, ll_category;

    ImageView iv_next, fab_creatrpost;
    private ExpandableListView expandableListView;
    private ChaptersPojo chaptersPojo;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> parentHeaderInformation;
    private HashMap<String, List<String>> allChildItems = new HashMap<>();
    String subtitle;
    ArrayList arrayList, subtitleslist;
    private String chapter_id, subject_id;
    private RecyclerView rview_discussions;
    private LinearLayoutManager manager;
    private DiscussionsPojo discussionsPojo;
    private DiscussionsAdapter discussionsAdapter;
    private int selected_position;
    private ImageView iv_static_search, iv_search, iv_banner;
    private EditText et_search;
    private TextView tv_scrolling;
    private String cmntrefresh = "";
    private UserSession userSession;
    private int page_number = 0;
    private NestedScrollView ll_nested_scroll;
    private List<DiscussionsPojo.ResponseBean> notesBeans;
    private String token, KEY_EDUCATION_ID, KEY_YEARS, KEY_STREAM_ID, KEY_SUBJECTS, KEY_USERID, KEY_UserName, KEY_MOBILENO, KEY_PROFILE, KEY_UNIVERSITY, KEY_COUNTRY_ID, KEY_CITY_ID, KEY_BIO;

    private AddBannerPojo addBannerPojo;
    private boolean scroll_status = true;
    private boolean response_status = false;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_discuss, null);
        return view;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


       /*v = LayoutInflater.from(getActivity()).inflate(R.layout.frag_discuss, null);
        return v;*/

        //  v = inflater.inflate(R.layout.frag_discuss_categories, container, false);
        return view;


    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        ll_discuss = view.findViewById(R.id.ll_discuss_layout);
        ll_category = view.findViewById(R.id.ll_disussion_layout);
        tv_discussCategory = view.findViewById(R.id.tv_discussCategory);
        tv_discussion = view.findViewById(R.id.tv_discussion);
        //iv_next = view.findViewById(R.id.iv_discuus_next);
        //tv_videoview = view.findViewById(R.id.tv_video);
        rview_discussions = view.findViewById(R.id.rview_discussions);
        fab_creatrpost = view.findViewById(R.id.fab_creatrpost);
        tv_scrolling = view.findViewById(R.id.tv_scrolling);
        iv_banner = view.findViewById(R.id.ic_banner);
        expandableListView = view.findViewById(R.id.expandableListView);
        ll_nested_scroll = view.findViewById(R.id.ll_nested_scroll_discuss);
        tv_scrolling.setSelected(true);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_discussions.setLayoutManager(manager);

        notesBeans = new ArrayList<>();
        userSession = new UserSession(getActivity());
        Picasso.with(getActivity())
                .load(NetworkConstants.URL.Imagepath_URL + Variables.adv_banner)
                .error(R.drawable.ic_blue_background)
                .into(iv_banner);
        tv_scrolling.setText(Variables.scrolling_text);
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

        //  iv_static_search = (ImageView) getActivity().findViewById(R.id.iv_static_search);
        iv_search = (ImageView) getActivity().findViewById(R.id.iv_search);
        et_search = (EditText) getActivity().findViewById(R.id.et_search);

        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  iv_static_search.setVisibility(View.GONE);
                iv_search.setVisibility(View.VISIBLE);
            }
        });
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.search_text = et_search.getText().toString();
                callDiscussionData();
            }
        });

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
        presenter.getDiscussions(getActivity(), this, requestBody);
        HashMap<String, List<String>> allChildItems = new HashMap<>();
        tv_discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_discuss.setVisibility(View.VISIBLE);
                ll_category.setVisibility(View.GONE);
                //  tv_discussion.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_discussion_bg));
                tv_discussion.setBackgroundResource(R.drawable.discuss_selected);
                tv_discussCategory.setBackgroundResource(R.drawable.categories_unselelcted);
                tv_discussion.setTextColor(Color.WHITE);
                tv_discussCategory.setTextColor(Color.BLACK);
                callDiscussionData();

            }
        });
        fab_creatrpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmntrefresh = "abc";
                Bundle bundle = new Bundle();
                bundle.putString("from", "discuss");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CREATE_POST_SCREEN, bundle);

            }
        });
        iv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(addBannerPojo.getAdv_banners().getAdvt_url()));
                startActivity(browserIntent);
            }
        });

        tv_discussCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_discuss.setVisibility(View.GONE);
                ll_category.setVisibility(View.VISIBLE);
                tv_discussCategory.setBackgroundResource(R.drawable.categories_selected);
                tv_discussion.setBackgroundResource(R.drawable.discuss_unselect);
                tv_discussCategory.setTextColor(Color.WHITE);
                tv_discussion.setTextColor(Color.BLACK);


                callChapters();

            }
        });
        callAdvBannersWs();

    }

    private void callAdvBannersWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        presenter.advBanners(getActivity(), this, requestBody);
    }

    private void callChapters() {

        Map<String, String> requestBody_chapters = new HashMap<>();
        requestBody_chapters.put("token", token);
        requestBody_chapters.put("education_id", KEY_EDUCATION_ID);
        requestBody_chapters.put("stream_id", KEY_STREAM_ID);
        requestBody_chapters.put("user_id", KEY_USERID);
        requestBody_chapters.put("subject_ids", KEY_SUBJECTS);
        requestBody_chapters.put("type", "discuss");
        presenter.getChapters(getActivity(), this, requestBody_chapters);
        parentHeaderInformation = new ArrayList<String>();
        HashMap<String, List<String>> allChildItems = new HashMap<>();

    }

    private void callDiscussionData() {
        page_number = 0;
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
        presenter.getDiscussions(getActivity(), this, requestBody);

    }

    @Override
    public void setPresenter() {
        presenter = new DiscussionsFragImpl(this, getActivity());

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());

            } else if (NetworkConstants.RequestCode.DISCUSSIONS_DATA == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {

                    rview_discussions.setVisibility(View.VISIBLE);
                    discussionsPojo = new Gson().fromJson(responseString, DiscussionsPojo.class);
                    notesBeans.addAll(discussionsPojo.getResponse());
                    response_status = true;

                    if (page_number == 0) {
                        discussionsAdapter = new DiscussionsAdapter(notesBeans, getActivity());
                        rview_discussions.setAdapter(discussionsAdapter);
                    } else {
                        discussionsAdapter.notifyDataSetChanged();
                    }


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

                    discussionsAdapter.setOnItemClickListener(new DiscussionsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            selected_position = position;
                            switch (view.getId()) {
                                case R.id.ll_discuss_comment:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", notesBeans.get(position).getChapter());
                                    bundle.putString("subtitle", notesBeans.get(position).getChapter());
                                    bundle.putString("description", notesBeans.get(position).getDescription());
                                    bundle.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle.putString("views_count", notesBeans.get(position).getViews());
                                    bundle.putString("topic_id", notesBeans.get(position).getId());
                                    bundle.putString("sending_through", "discuss");
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle);

                                    break;
                                case R.id.tv_discuss_comments_count:
                                    Bundle bundle_comment = new Bundle();
                                    bundle_comment.putString("title", notesBeans.get(position).getChapter());
                                    bundle_comment.putString("subtitle", notesBeans.get(position).getChapter());
                                    bundle_comment.putString("description", notesBeans.get(position).getDescription());
                                    bundle_comment.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle_comment.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle_comment.putString("views_count", notesBeans.get(position).getViews());
                                    bundle_comment.putString("topic_id", notesBeans.get(position).getId());
                                    bundle_comment.putString("sending_through", "discuss");
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_comment);

                                    break;
                                case R.id.tv_discuss_user_post:
                                    Bundle bundle_comment_ = new Bundle();
                                    bundle_comment_.putString("title", notesBeans.get(position).getChapter());
                                    bundle_comment_.putString("subtitle", notesBeans.get(position).getChapter());
                                    bundle_comment_.putString("description", notesBeans.get(position).getDescription());
                                    bundle_comment_.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle_comment_.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle_comment_.putString("views_count", notesBeans.get(position).getViews());
                                    bundle_comment_.putString("topic_id", notesBeans.get(position).getId());
                                    bundle_comment_.putString("sending_through", "discuss");
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_comment_);

                                    break;
                                case R.id.ll_discuss_unread:
                                    callNotesReadStatus();
                                    break;

                                case R.id.ll_discuss_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", notesBeans.get(position).getId());
                                    bundle_ping.putString("type", "discuss");
                                    if (notesBeans.get(position).getDescription().length() > 70) {
                                        bundle_ping.putString("content", notesBeans.get(position).getDescription().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", notesBeans.get(position).getDescription());
                                    }

                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);

                            }

                        }
                    });
                } else {
                    response_status = false;
                    //Toast.makeText(getActivity(), "No Data for selected Category", Toast.LENGTH_SHORT).show();
             //       rview_discussions.setVisibility(View.GONE);
                //    discussionsAdapter.notifyDataSetChanged();

                }
            } else if (NetworkConstants.RequestCode.SUBMIT_AS_READ == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    notesBeans.get(selected_position).setReaded("yes");
                    discussionsAdapter.notifyItemChanged(selected_position);
                    discussionsAdapter.notifyDataSetChanged();
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
                                //String text = "Test" + "<font color=#397f00>" + "(" + jsobjsub.optString("read_count") + "/" + jsobjsub.optString("topic_count") + ")" + "</font> ";

                                //  subtitle = jsobjsub.optString("chapter") + "(" + jsobjsub.optString("read_count") + "/" + jsobjsub.optString("topic_count") + ")";
                                subtitle = text;
                            } else {
                                String text = jsobjsub.optString("chapter") + "<font color=#e82f2f>" + "(" + jsobjsub.optString("read_count") + "/" + jsobjsub.optString("topic_count") + ")" + "</font> ";
                                //  String text = "Test" + "<font color=#e82f2f>" + "(" + jsobjsub.optString("read_count") + "/" + jsobjsub.optString("topic_count") + ")" + "</font> ";
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
                                            } else if (chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getRead_count() == 0 && chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getTopic_count() == 0) {
                                                Toast.makeText(getActivity(), "No Topics to Read", Toast.LENGTH_SHORT).show();
                                            } else {

                                                chapter_id = chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getId();
                                                subject_id = chaptersPojo.getResponse().get(groupPosition).getId();

                                                cmntrefresh = "abc";
                                                Intent intent = new Intent(getActivity(), DiscussActivity.class);
                                                intent.putExtra("chapter_id", chapter_id);
                                                intent.putExtra("subject_id", subject_id);
                                                intent.putExtra("read_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getRead_count());
                                                intent.putExtra("topic_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getTopic_count());
                                                intent.putExtra("topic", chaptersPojo.getResponse().get(groupPosition).getSubject() + " " + chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getChapter());
                                                Bundle screenLaunchAnimation =
                                                        ActivityOptions.makeCustomAnimation(ApplicationController.getInstance().getContext(), R.anim.animation_click, R.anim.animation_show).toBundle();
                                                getActivity().startActivity(intent, screenLaunchAnimation);
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
        } catch (
                Exception e) {
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
        requestBody.put("chapter_id", "0");
        requestBody.put("count", String.valueOf(page_number));
        presenter.getDiscussions(getActivity(), this, requestBody);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!cmntrefresh.isEmpty()) {
            cmntrefresh = "";
            callDiscussionData();

        }
    }

    private void callNotesReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("subject_id", notesBeans.get(selected_position).getSubject_id());
        requestBody.put("chapter_id", notesBeans.get(selected_position).getChapter());
        requestBody.put("sections_id", notesBeans.get(selected_position).getId());
        requestBody.put("type", "discuss");
        presenter.submit_as_read(getActivity(), this, requestBody);

    }

    private void callDiscussionsWs() {
        page_number = 0;
        ll_discuss.setVisibility(View.VISIBLE);
        ll_category.setVisibility(View.GONE);
        tv_discussion.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_discussion_bg));
        tv_discussCategory.setBackgroundColor(Color.WHITE);
        tv_discussion.setTextColor(Color.WHITE);
        tv_discussCategory.setTextColor(Color.BLACK);
        ll_category.setVisibility(View.GONE);
        ll_discuss.setVisibility(View.VISIBLE);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", String.valueOf(page_number));

        presenter.getChapters(getActivity(), this, requestBody);

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
