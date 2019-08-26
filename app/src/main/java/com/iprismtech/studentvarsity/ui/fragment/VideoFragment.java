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
import com.iprismtech.studentvarsity.adapters.VideosAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.dao.VideoCallBack;
import com.iprismtech.studentvarsity.mvp.contract.fragment.VideoFragmentContract;
import com.iprismtech.studentvarsity.mvp.presenter.fragment.VideoFragmentlmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.AddBannerPojo;
import com.iprismtech.studentvarsity.pojos.ChaptersPojo;
import com.iprismtech.studentvarsity.pojos.VideosListPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.ui.activity.ChaptersVideoList;
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

public class VideoFragment extends BaseAbstractFragment<VideoFragmentlmpl> implements VideoFragmentContract.IView, APIResponseCallback ,VideoCallBack {

    private LinearLayout ll_video, ll_video_layout;
    private TextView tv_videos, tv_videoCategory;
    private APIResponseCallback apiResponseCallback;
    private RecyclerView rv_video;
    private VideosAdapter adapter;
    private ChaptersPojo chaptersPojo;
    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> parentHeaderInformation;
    private HashMap<String, List<String>> allChildItems = new HashMap<>();
    private String subtitle;
    private ArrayList arrayList, subtitleslist;
    private String chapter_id, subject_id;
    private VideosListPOJO departmentPOJO;
    private int selected_postion;
    private String KEY_USERID, KEY_TOKEN, KEY_EDUCATION_ID, KEY_STREAM_ID, KEY_SUBJECTS;
    private ImageView iv_static_search, iv_search;
    private EditText et_search;
    private ImageView iv_banner;
    private int page_number = 0;
    private List<VideosListPOJO.ResponseBean> videosList;

    private NestedScrollView ll_nested_scroll;
    private boolean response_status = false;

    private AddBannerPojo addBannerPojo;
    private String cmntrefresh = "";
    private boolean scroll_status = true;
    VideoCallBack videoCallBack;
    @Override
    public void setPresenter() {
        presenter = new VideoFragmentlmpl(this, getContext());
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_read_unread_video, null);
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


        TextView textview = (TextView) getActivity().findViewById(R.id.tv_read_video);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.white_corner_draw);

        TextView textview_video = (TextView) getActivity().findViewById(R.id.tv_read_notes);
        textview_video.setTextColor(Color.WHITE);
        textview_video.setBackgroundResource(R.drawable.primary_color_bg);

        TextView textview_faq = (TextView) getActivity().findViewById(R.id.tv_read_faq);
        textview_faq.setTextColor(Color.WHITE);
        textview_faq.setBackgroundResource(R.color.primary_light);

        TextView textview_mcq = (TextView) getActivity().findViewById(R.id.tv_read_mcq);
        textview_mcq.setTextColor(Color.WHITE);
        textview_mcq.setBackgroundResource(R.color.primary_light);

        TextView textview_quiz = (TextView) getActivity().findViewById(R.id.tv_read_quiz);
        textview_quiz.setTextColor(Color.WHITE);
        textview_quiz.setBackgroundResource(R.drawable.primary_color_bg);
        videoCallBack = this;

        apiResponseCallback = this;
        videosList = new ArrayList<>();

        rv_video = view.findViewById(R.id.rv_video);
        ll_video = view.findViewById(R.id.ll_video);
        ll_video_layout = view.findViewById(R.id.ll_video_layout);
        tv_videos = view.findViewById(R.id.tv_videos);
        tv_videoCategory = view.findViewById(R.id.tv_videoCategory);
        expandableListView = view.findViewById(R.id.expandableListView);
        ll_nested_scroll = view.findViewById(R.id.ll_nested_scroll_videos);
        iv_banner = view.findViewById(R.id.iv_banner);

        //   iv_static_search = (ImageView) getActivity().findViewById(R.id.iv_static_search);
        iv_search = (ImageView) getActivity().findViewById(R.id.iv_search);
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
                callNotesWs();
            }
        });

        Picasso.with(getActivity())
                .load(NetworkConstants.URL.Imagepath_URL + Variables.adv_banner)
                .error(R.drawable.ic_blue_background)
                .into(iv_banner);
        tv_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_video.setVisibility(View.VISIBLE);
                ll_video_layout.setVisibility(View.GONE);
                //tv_videos.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
                tv_videos.setBackgroundResource(R.drawable.video_selected);
                // tv_videoCategory.setBackgroundColor(Color.WHITE);
                tv_videoCategory.setBackgroundResource(R.drawable.video_cat_unselect);
                tv_videos.setTextColor(Color.WHITE);
                tv_videoCategory.setTextColor(Color.BLACK);

            }
        });
        tv_videoCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_video.setVisibility(View.GONE);
                ll_video_layout.setVisibility(View.VISIBLE);
                //  tv_videoCategory.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
                tv_videoCategory.setBackgroundResource(R.drawable.video_cat_select);
                tv_videos.setBackgroundColor(Color.WHITE);
                tv_videoCategory.setTextColor(Color.WHITE);
                tv_videos.setTextColor(Color.BLACK);
                scroll_status = false;
                callGetChapterdWs();


            }
        });

        iv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(addBannerPojo.getAdv_banners().getAdvt_url()));
                startActivity(browserIntent);
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

        KEY_USERID = userSession.getUserDetails().get("id");
        KEY_TOKEN = userSession.getUserDetails().get("token");
        KEY_EDUCATION_ID = userSession.getUserDetails().get("education_id");
        KEY_STREAM_ID = userSession.getUserDetails().get("stream_id");
        KEY_SUBJECTS = userSession.getUserDetails().get("subjects");


        if (KEY_USERID != null) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", KEY_TOKEN);
            requestBody.put("user_id", KEY_USERID);
            requestBody.put("education_id", KEY_EDUCATION_ID);
            requestBody.put("stream_id", KEY_STREAM_ID);
            requestBody.put("subject_ids", KEY_SUBJECTS);
            requestBody.put("chapter_id", " ");
            requestBody.put("count", String.valueOf(page_number));

            if (Variables.search_text != null) {
                requestBody.put("search", Variables.search_text);
            }
            presenter.videos(getActivity(), apiResponseCallback, requestBody);
            parentHeaderInformation = new ArrayList<String>();
            HashMap<String, List<String>> allChildItems = new HashMap<>();
        }

        callAdvBannersWs();
    }

    private void callAdvBannersWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", KEY_TOKEN);
        presenter.advBanners(getActivity(), this, requestBody);
    }


    private void callGetChapterdWs() {
        scroll_status = false;
        Map<String, String> requestBody_chapters = new HashMap<>();
        requestBody_chapters.put("token", KEY_TOKEN);
        requestBody_chapters.put("education_id", KEY_EDUCATION_ID);
        requestBody_chapters.put("stream_id", KEY_STREAM_ID);
        requestBody_chapters.put("subject_ids", KEY_SUBJECTS);
        requestBody_chapters.put("user_id", KEY_USERID);
        requestBody_chapters.put("type", "videos");
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
            } else if (NetworkConstants.RequestCode.VIDEOS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    response_status = true;
                    rv_video.setVisibility(View.VISIBLE);
                    //  Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                    departmentPOJO = new Gson().fromJson(responseString, VideosListPOJO.class);
                    int count=departmentPOJO.getResponse().size();
                    int position=videosList.size();
                    videosList.addAll(departmentPOJO.getResponse());
                    if (page_number == 0) {

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        rv_video.setLayoutManager(mLayoutManager);
                        rv_video.setItemAnimator(new DefaultItemAnimator());

                        //Adding Adapter to recyclerView
                        adapter = new VideosAdapter(videosList, getActivity(),videoCallBack);
                        rv_video.setAdapter(adapter);


                    } else {
                        adapter.notifyItemRangeChanged(position,count);
                       // adapter.notifyDataSetChanged();
                    }


                    if (scroll_status) {
                        scroll_status = true;
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
                    adapter.setOnItemClickListener(new VideosAdapter.OnitemClickListener() {
                        @Override

                        public void onItemClick(View view, int position) {
                            selected_postion = position;
                            switch (view.getId()) {
                                case R.id.tv_heading:
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("video_id", videosList.get(position).getId());


                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN, bundle2);
                                    break;

                                case R.id.ll_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", videosList.get(position).getId());
                                    bundle_ping.putString("type", "videos");

                                    if (videosList.get(position).getDescription().length() > 70) {
                                        bundle_ping.putString("content", videosList.get(position).getDescription().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", videosList.get(position).getDescription());
                                    }

                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);
                                    break;
                                case R.id.ll_comment:
                                    Bundle bundle_commnet = new Bundle();
                                    bundle_commnet.putString("video_id", videosList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN, bundle_commnet);

                                    break;
                                case R.id.tv_comments_count:
                                    Bundle bundle_commnet_ = new Bundle();
                                    bundle_commnet_.putString("video_id", videosList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN, bundle_commnet_);
                                    break;
                                case R.id.ll_unread:

                                    callVideossReadStatus();
                                    break;
                            }
                        }
                    });

                } else {
                    //Toast.makeText(getActivity(), "No Data for selected Category", Toast.LENGTH_SHORT).show();
                    response_status = false;
                    if (page_number == 0) {
                        rv_video.setVisibility(View.GONE);
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
                                                Intent intent = new Intent(getActivity(), ChaptersVideoList.class);

                                                intent.putExtra("chapter_id", chapter_id);
                                                intent.putExtra("subject_id", subject_id);
                                                intent.putExtra("topic", chaptersPojo.getResponse().get(groupPosition).getSubject() + " " + chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getChapter());
                                                intent.putExtra("read_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getRead_count());
                                                intent.putExtra("topic_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getTopic_count());

                                                Bundle screenLaunchAnimation =
                                                        ActivityOptions.makeCustomAnimation(ApplicationController.getInstance().getContext(), R.anim.animation_click, R.anim.animation_show).toBundle();
                                                getActivity().startActivity(intent, screenLaunchAnimation);

                                                //   callNotesWs();
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
                    videosList.get(selected_postion).setReaded("yes");
                    adapter.notifyItemChanged(selected_postion);
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
        requestBody.put("token", KEY_TOKEN);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", KEY_SUBJECTS);
        requestBody.put("chapter_id", " ");
        requestBody.put("count", String.valueOf(page_number));

        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }
        presenter.videos(getActivity(), apiResponseCallback, requestBody);

    }

    private void callVideossReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", KEY_TOKEN);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("subject_id", videosList.get(selected_postion).getSubject_id());
        requestBody.put("chapter_id", videosList.get(selected_postion).getChapter_id());
        requestBody.put("sections_id", videosList.get(selected_postion).getId());
        requestBody.put("type", "videos");
        presenter.submit_as_read(getActivity(), this, requestBody);

    }

    private void callNotesWs() {
        scroll_status = true;
        page_number = 0;
        videosList = new ArrayList<>();
        ll_video.setVisibility(View.VISIBLE);
        ll_video_layout.setVisibility(View.GONE);
        tv_videos.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
        tv_videoCategory.setBackgroundColor(Color.WHITE);
        tv_videos.setTextColor(Color.WHITE);
        tv_videoCategory.setTextColor(Color.BLACK);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", KEY_TOKEN);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", String.valueOf(page_number));

        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }

        presenter.videos(getActivity(), apiResponseCallback, requestBody);

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
//            subject_id = userSession.getUserDetails().get("subjects");
//
//            ll_video.setVisibility(View.GONE);
//            ll_video_layout.setVisibility(View.VISIBLE);
//            //  tv_videoCategory.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
//            tv_videoCategory.setBackgroundResource(R.drawable.video_cat_select);
//            tv_videos.setBackgroundColor(Color.WHITE);
//            tv_videoCategory.setTextColor(Color.WHITE);
//            tv_videos.setTextColor(Color.BLACK);
//
//            callGetChapterdWs();

            expandableListViewAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void clickevent(String id, int checked) {
        rv_video.scrollToPosition(checked); //use to focus the item with index
        adapter.notifyItemChanged(checked);
    }
}

