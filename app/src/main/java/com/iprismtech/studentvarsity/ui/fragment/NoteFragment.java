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
import com.iprismtech.studentvarsity.adapters.NotesAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.base.BaseAbstractFragment;
import com.iprismtech.studentvarsity.mvp.contract.fragment.NotesFragContract;
import com.iprismtech.studentvarsity.mvp.presenter.fragment.NotesFragImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.AddBannerPojo;
import com.iprismtech.studentvarsity.pojos.ChaptersPojo;
import com.iprismtech.studentvarsity.pojos.NotesPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
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

public class NoteFragment extends BaseAbstractFragment<NotesFragImpl> implements View.OnClickListener, NotesFragContract.IView, APIResponseCallback {

    private LinearLayout ll_note, ll_noteCategeory;
    private TextView tv_note, tv_noteCategeory;
    private RecyclerView rview_notes;
    private LinearLayoutManager manager;
    private NotesAdapter notesAdapter;
    private NotesPojo notesPojo;
    private ExpandableListView expandableListView;
    private ChaptersPojo chaptersPojo;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> parentHeaderInformation;
    private HashMap<String, List<String>> allChildItems = new HashMap<>();
    String subtitle;
    ArrayList arrayList, subtitleslist;
    private String chapter_id, subject_id;
    private int selected_postion;
    private String token, user_id, user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects, education_id;
    private UserSession userSession;
    private ImageView iv_static_search, iv_search;
    private EditText et_search;
    private List<NotesPojo.ResponseBean> notesBeans;
    private NestedScrollView ll_nested_scroll;
    private int page_number = 0;
    private boolean response_status = false;
    private ImageView iv_banner;
    private AddBannerPojo addBannerPojo;

    String cmntrefresh = "";
    private boolean scroll_status = true;

    //    @Override
//    protected View getFragmentView() {
//        view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_read_unread_notes, null);
//        return view;
//    }
//
//    @Override
//    public void setPresenter() {
//
//    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.start();
        return view;
    }


    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_read_unread_notes, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_note.setOnClickListener(this);
        tv_noteCategeory.setOnClickListener(this);
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        TextView textview = (TextView) getActivity().findViewById(R.id.tv_read_notes);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.white_corner_draw);

        TextView textview_video = (TextView) getActivity().findViewById(R.id.tv_read_video);
        textview_video.setTextColor(Color.WHITE);
        textview_video.setBackgroundResource(R.color.primary_light);

        TextView textview_faq = (TextView) getActivity().findViewById(R.id.tv_read_faq);
        textview_faq.setTextColor(Color.WHITE);
        textview_faq.setBackgroundResource(R.color.primary_light);

        TextView textview_mcq = (TextView) getActivity().findViewById(R.id.tv_read_mcq);
        textview_mcq.setTextColor(Color.WHITE);
        textview_mcq.setBackgroundResource(R.color.primary_light);

        TextView textview_quiz = (TextView) getActivity().findViewById(R.id.tv_read_quiz);
        textview_quiz.setTextColor(Color.WHITE);
        textview_quiz.setBackgroundResource(R.drawable.primary_color_bg);

        notesBeans = new ArrayList<>();
        ll_note = view.findViewById(R.id.ll_notes_layout);
        ll_noteCategeory = view.findViewById(R.id.ll_disussion_layout);
        tv_note = view.findViewById(R.id.tv_notes);
        tv_noteCategeory = view.findViewById(R.id.tv_noteCategory);
        expandableListView = view.findViewById(R.id.expandableListView);
        rview_notes = view.findViewById(R.id.rview_notes);
        ll_nested_scroll = view.findViewById(R.id.ll_nested_scroll_notes);
        iv_banner = view.findViewById(R.id.iv_banner);

        Picasso.with(getActivity())
                .load(NetworkConstants.URL.Imagepath_URL + Variables.adv_banner)
                .error(R.drawable.ic_blue_background)
                .into(iv_banner);

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
                // Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                callNotesWs();
            }
        });


        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_notes.setLayoutManager(manager);

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
        presenter.getNotesData(getActivity(), this, requestBody);


        callAdvBannersWs();
        iv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(addBannerPojo.getAdv_banners().getAdvt_url()));
                startActivity(browserIntent);
            }
        });


    }

    private void callAdvBannersWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        presenter.advBanners(getActivity(), this, requestBody);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_notes:
                scroll_status = true;
                page_number=0;
                notesBeans = new ArrayList<>();

                ll_note.setVisibility(View.VISIBLE);
                tv_note.setBackgroundResource(R.drawable.notes_selected);

                ll_noteCategeory.setVisibility(View.GONE);
                ll_noteCategeory.setVisibility(View.GONE);
                tv_note.setBackgroundResource(R.drawable.notes_selected);
                tv_noteCategeory.setBackgroundResource(R.drawable.categories_unselelcted);
                tv_note.setTextColor(Color.WHITE);
                tv_noteCategeory.setTextColor(Color.BLACK);

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
                presenter.getNotesData(getActivity(), this, requestBody);
                break;

            case R.id.tv_noteCategory:

                ll_note.setVisibility(View.GONE);
                ll_noteCategeory.setVisibility(View.VISIBLE);
                tv_note.setBackgroundResource(R.drawable.notes_unselected);
                tv_noteCategeory.setBackgroundResource(R.drawable.categories_selected);
                tv_noteCategeory.setTextColor(Color.WHITE);
                tv_note.setTextColor(Color.BLACK);

                Map<String, String> requestBody_chapters = new HashMap<>();
                requestBody_chapters.put("token", token);
                requestBody_chapters.put("education_id", education_id);
                requestBody_chapters.put("stream_id", stream);
                requestBody_chapters.put("user_id", user_id);
                requestBody_chapters.put("subject_ids", subject_id);
                requestBody_chapters.put("type", "notes");
                presenter.getChapters(getActivity(), this, requestBody_chapters);

                parentHeaderInformation = new ArrayList<String>();
                HashMap<String, List<String>> allChildItems = new HashMap<>();

                scroll_status = false;

                break;
        }
    }

    @Override
    public void setPresenter() {
        presenter = new NotesFragImpl(this, getActivity());
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());

            } else if (NetworkConstants.RequestCode.GET_NOTES_DATA == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    rview_notes.setVisibility(View.VISIBLE);
                    response_status = true;

                    notesPojo = new Gson().fromJson(responseString, NotesPojo.class);
                    notesBeans.addAll(notesPojo.getResponse());

                    if (page_number == 0) {

                        notesAdapter = new NotesAdapter(getActivity(), notesBeans);
                        rview_notes.setAdapter(notesAdapter);
                        notesAdapter.notifyDataSetChanged();
                    } else {
                        notesAdapter.notifyDataSetChanged();
                    }

                    //rview_notes.setNestedScrollingEnabled(false);

//
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
//                                perfirmPagination();
//                            }
//                        }
//                    });

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

                    notesAdapter.setOnItemClickListener(new NotesAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_postion = position;
                            switch (view.getId()) {

                                case R.id.ll_topic:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", notesBeans.get(position).getHeading());
                                    bundle.putString("subtitle", notesBeans.get(position).getHeading());
                                    bundle.putString("description", notesBeans.get(position).getDescription());
                                    bundle.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle.putString("views_count", notesBeans.get(position).getViews());
                                    bundle.putString("sending_through", "Notes");
                                    bundle.putString("topic_id", notesBeans.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle);

                                    break;
                                case R.id.ll_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", notesBeans.get(position).getId());
                                    bundle_ping.putString("type", "notes");

                                    if (notesBeans.get(position).getDescription().length() > 70) {
                                        bundle_ping.putString("content", notesBeans.get(position).getDescription().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", notesBeans.get(position).getDescription());
                                    }
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);
                                    break;
                                case R.id.ll_comment:
                                    Bundle bundle_comment = new Bundle();
                                    bundle_comment.putString("title", notesBeans.get(position).getHeading());
                                    bundle_comment.putString("subtitle", notesBeans.get(position).getHeading());
                                    bundle_comment.putString("description", notesBeans.get(position).getDescription());
                                    bundle_comment.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle_comment.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle_comment.putString("views_count", notesBeans.get(position).getViews());
                                    bundle_comment.putString("sending_through", "Notes");
                                    bundle_comment.putString("topic_id", notesBeans.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_comment);

                                    break;
                                case R.id.ll_unread:

                                    callNotesReadStatus();
                                    break;
                                case R.id.tv_comments_count:
                                    Bundle bundle_comment_ = new Bundle();
                                    bundle_comment_.putString("title", notesBeans.get(position).getHeading());
                                    bundle_comment_.putString("subtitle", notesBeans.get(position).getHeading());
                                    bundle_comment_.putString("description", notesBeans.get(position).getDescription());
                                    bundle_comment_.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle_comment_.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle_comment_.putString("views_count", notesBeans.get(position).getViews());
                                    bundle_comment_.putString("sending_through", "Notes");
                                    bundle_comment_.putString("topic_id", notesBeans.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_comment_);

                                    break;
                            }
                        }
                    });


                } else {
                    //Toast.makeText(getActivity(), "No Data for selected Category", Toast.LENGTH_SHORT).show();
                    response_status = false;
                    if (page_number == 0) {
                        rview_notes.setVisibility(View.GONE);
                        notesAdapter.notifyDataSetChanged();
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
                                                Intent intent = new Intent(getActivity(), NotesActivty.class);
                                                intent.putExtra("chapter_id", chapter_id);
                                                intent.putExtra("subject_id", subject_id);
                                                intent.putExtra("read_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getRead_count());
                                                intent.putExtra("topic_count", chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getTopic_count());
                                                intent.putExtra("topic", chaptersPojo.getResponse().get(groupPosition).getSubject() + " " + chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getChapter());
                                                Bundle screenLaunchAnimation =
                                                        ActivityOptions.makeCustomAnimation(ApplicationController.getInstance().getContext(), R.anim.animation_click, R.anim.animation_show).toBundle();
                                                getActivity().startActivity(intent, screenLaunchAnimation);
                                                //  callNotesWs();
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
                    notesBeans.get(selected_postion).setReaded("yes");
                    notesAdapter.notifyItemChanged(selected_postion);
                    notesAdapter.notifyDataSetChanged();
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
        presenter.getNotesData(getActivity(), this, requestBody);

    }

    private void callNotesReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("subject_id", notesBeans.get(selected_postion).getSubject_id());
        requestBody.put("chapter_id", notesBeans.get(selected_postion).getChapter_id());
        requestBody.put("sections_id", notesBeans.get(selected_postion).getId());
        requestBody.put("type", "notes");
        presenter.submit_as_read(getActivity(), this, requestBody);

    }

    private void callNotesWs() {

        ll_note.setVisibility(View.VISIBLE);
        ll_noteCategeory.setVisibility(View.GONE);
        tv_note.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_gray_light_corner));
        tv_noteCategeory.setBackgroundColor(Color.WHITE);
        tv_note.setTextColor(Color.WHITE);
        tv_noteCategeory.setTextColor(Color.BLACK);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("education_id", education_id);
        requestBody.put("stream_id", stream);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }
        presenter.getNotesData(getActivity(), this, requestBody);
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }


    @Override
    public void onResume() {
        super.onResume();


//        if (!cmntrefresh.isEmpty()) {
//            cmntrefresh = "";
//            userSession = new UserSession(getActivity());
//            token = userSession.getUserDetails().get("token");
//            user_id = userSession.getUserDetails().get("id");
//            subject_id = userSession.getUserDetails().get("subjects");
//
//            ll_note.setVisibility(View.GONE);
//            ll_noteCategeory.setVisibility(View.VISIBLE);
//            tv_note.setBackgroundResource(R.drawable.notes_unselected);
//            tv_noteCategeory.setBackgroundResource(R.drawable.categories_selected);
//            tv_noteCategeory.setTextColor(Color.WHITE);
//            tv_note.setTextColor(Color.BLACK);
//
//            Map<String, String> requestBody_chapters = new HashMap<>();
//            requestBody_chapters.put("token", token);
//            requestBody_chapters.put("education_id", education_id);
//            requestBody_chapters.put("stream_id", stream);
//            requestBody_chapters.put("user_id", user_id);
//            requestBody_chapters.put("subject_ids", subject_id);
//            requestBody_chapters.put("type", "notes");
//            presenter.getChapters(getActivity(), this, requestBody_chapters);
//
//            parentHeaderInformation = new ArrayList<String>();
//            HashMap<String, List<String>> allChildItems = new HashMap<>();
//        }



    }
}

