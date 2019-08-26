package com.iprismtech.studentvarsity.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.ActvityCommentsAdapter;
import com.iprismtech.studentvarsity.adapters.AllViewedPeopleAdapter;
import com.iprismtech.studentvarsity.adapters.NotesCommentsAdapter;
import com.iprismtech.studentvarsity.adapters.PaginationCommentsAdapter;
import com.iprismtech.studentvarsity.adapters.ViewAllRepliesAdapter;
import com.iprismtech.studentvarsity.adapters.ViewedPeopleAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.ActivityDetailsContact;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ActivityDetailsImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.ActivityDetailsPojo;
import com.iprismtech.studentvarsity.pojos.AllCommentsPojo;
import com.iprismtech.studentvarsity.pojos.AllViewedPeoplePojo;
import com.iprismtech.studentvarsity.pojos.CommentRepliesPojo;
import com.iprismtech.studentvarsity.pojos.ViewAllRepliesPojo;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityDetailsActvity extends BaseAbstractActivity<ActivityDetailsImpl> implements ActivityDetailsContact.IView, View.OnClickListener, APIResponseCallback, SwipeRefreshLayout.OnRefreshListener {


    private ActivityDetailsPojo activityDetailsPojo;
    private ImageView iv_topic_img, iv_comment_submit, iv_image, iv_profile;
    private TextView tv_topic_title, tv_topic_sub, tv_pings_count, tv_comments_count, tv_view_count, tv_description, tv_scrolling;
    private LinearLayout ll_ping, ll_comment, ll_unread, ll_topic, ll_unread_blue, ll_view_all;
    private String title, sub_title, pings_count, comments_count, views_count, description;
    private NestedScrollView ll_nested_scroll;
    private APIResponseCallback apiResponseCallback;
    private RecyclerView rview_comments;
    private LinearLayoutManager manager, manager_viewed_people;
    private ActvityCommentsAdapter actvityCommentsAdapter;
    private EditText et_comment;
    private String view_stats = "", user_id, token;
    private ImageView iv_capture, iv_back;
    private String image, comment;
    private int selected_position;
    private TextView tv_cmnt, activity_tv_first_char;
    private ViewAllRepliesPojo viewAllRepliesPojo;
    private CommentRepliesPojo commentRepliesPojo;
    private AlertDialog alertDialog;
    private ViewAllRepliesAdapter viewAllRepliesAdapter;
    private Bundle bundle;
    private SimpleExoPlayer player;
    private String topic_id, sending_through;
    private String str_reply;
    private YouTubePlayerView video_youtubePlayerView_activity;
    private String user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects;
    private ImageView activty_image;
    private String cmntrefresh = "";
    private String subject_id, chapter_id;
    private AllCommentsPojo allCommentsPojo;
    List<AllCommentsPojo.ResponseBean> commentsBean;
    private NotesCommentsAdapter notesCommentsAdapter;
    ProgressBar progressBar;
    PaginationCommentsAdapter paginationCommentsAdapter;
    private ImageView tv_captured_image;


    private int page_number = 0;
    private int item_count = 5;


    private boolean isLoading = true;
    private boolean isLastPage = false;
    private int pastVisibleItems, visibleitemCount, totoalitemCount, previous_total = 0;
    private int view_threshold = 10;


    private int PAGE_SIZE = 5;
    int startFrom = 0;
    private ViewedPeopleAdapter viewedPeopleAdapter;


    private AllViewedPeopleAdapter allViewedPeopleAdapter;
    private List<AllViewedPeoplePojo.ViewsBean> allViewedPeoplePojo;


    private int totalPage = 10;

    int itemCount = 0;

    private RecyclerView rview_viewed_people;

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean mHasReachedBottomOnce = false;
    private String base64profile = "";
    private ContentValues contentValue;
    private int GALLERY_DOC = 101, CAMERA_DOC = 102;
    private Uri imageUri;
    private Bitmap profile_bit, bp;


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_comment_submit.setOnClickListener(this);
        ll_unread.setOnClickListener(this);
        ll_unread.setOnClickListener(this);
        iv_capture.setOnClickListener(this);
        ll_ping.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        ll_view_all.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!cmntrefresh.isEmpty()) {
            cmntrefresh = "";
            page_number = 0;
            commentsBean = new ArrayList<>();
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("activity_post_id", topic_id);
            // requestBody.put("discuss_id", "1");
            presenter.getActivtyDetails(ActivityDetailsActvity.this, this, requestBody);
        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            topic_id = bundle.getString("topic_id");
        }

        commentsBean = new ArrayList<>();
        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        user_name = userSession.getUserDetails().get("name");
        user_university = userSession.getUserDetails().get("university");
        user_bio = userSession.getUserDetails().get("bio");
        user_profile_pic = userSession.getUserDetails().get("profileImg");
        stream = userSession.getUserDetails().get("stream_id");
        years = userSession.getUserDetails().get("years");
        user_subjects = userSession.getUserDetails().get("subject_names");

        tv_scrolling = findViewById(R.id.tv_scrolling);
        tv_scrolling.setSelected(true);
        tv_scrolling.setText(Variables.scrolling_text);


        tv_topic_title = findViewById(R.id.tv_title);
        ll_nested_scroll = findViewById(R.id.ll_nested_scroll);
        iv_profile = findViewById(R.id.iv_profile);

        rview_viewed_people = findViewById(R.id.rview_viewed_people);
        tv_topic_sub = findViewById(R.id.tv_sub_topic);
        tv_pings_count = findViewById(R.id.tv_pings_count);
        tv_comments_count = findViewById(R.id.tv_comments_count);
        tv_view_count = findViewById(R.id.tv_view_count);
        tv_description = findViewById(R.id.tv_description);
        ll_ping = findViewById(R.id.ll_ping);
        ll_comment = findViewById(R.id.ll_comment);
        ll_unread = findViewById(R.id.ll_unread);
        ll_view_all = findViewById(R.id.ll_view_all);
        iv_image = findViewById(R.id.iv_image);
        iv_back = findViewById(R.id.iv_back);
        activity_tv_first_char = findViewById(R.id.activity_tv_first_char);
        // ll_topic = findViewById(R.id.ll_topic);
        ll_unread_blue = findViewById(R.id.ll_unread_blue);
        et_comment = findViewById(R.id.et_comment);
        iv_comment_submit = findViewById(R.id.iv_comment_submit);
        iv_capture = findViewById(R.id.iv_capture);
        tv_captured_image = findViewById(R.id.tv_captured_image);


        tv_cmnt = findViewById(R.id.tv_cmnt);
        activty_image = findViewById(R.id.activty_image);
        video_youtubePlayerView_activity = findViewById(R.id.video_youtubePlayerView_activity);


        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + user_profile_pic)
                .error(R.drawable.no_image)
                .into(iv_profile);

        rview_comments = findViewById(R.id.rview_comments);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager_viewed_people = new LinearLayoutManager(this);
        manager_viewed_people.setOrientation(LinearLayoutManager.HORIZONTAL);

        rview_comments.setLayoutManager(manager);
        rview_viewed_people.setLayoutManager(manager_viewed_people);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("activity_post_id", topic_id);
        // requestBody.put("discuss_id", "1");
        presenter.getActivtyDetails(ActivityDetailsActvity.this, this, requestBody);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_comment_submit:
                if (et_comment.getText().toString().length() == 0) {
                    Toast.makeText(this, "Write Comment", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("sections_id", topic_id);
                    requestBody.put("image", base64profile);
                    requestBody.put("type", "activity");
                    requestBody.put("comment", et_comment.getText().toString());
                    requestBody.put("name", user_name);

                    if (activityDetailsPojo.getResponse().getDescription().length() > 50) {
                        requestBody.put("discussion", activityDetailsPojo.getResponse().getDescription().substring(0, 50));
                    } else {
                        requestBody.put("discussion", activityDetailsPojo.getResponse().getDescription());
                    }

                    presenter.submit_comment(ActivityDetailsActvity.this, this, requestBody);
                    et_comment.setText("");
                }
                break;
            case R.id.ll_unread:
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                requestBody.put("user_id", user_id);
                requestBody.put("sections_id", topic_id);
                requestBody.put("subject_id", subject_id);
                requestBody.put("chapter_id", chapter_id);
                requestBody.put("type", "activity");
                presenter.submit_as_read(ActivityDetailsActvity.this, apiResponseCallback, requestBody);
                break;
            case R.id.iv_capture:
                cmntrefresh = "abc";
//                Intent i = new Intent(ActivityDetailsActvity.this, ImageCaptureActivity.class);
//                i.putExtra("sending_through", "activity");
//                i.putExtra("section_id", activityDetailsPojo.getResponse().getId());
//                startActivityForResult(i, 1);
//                Intent i = new Intent(NotesDetailsDescriptionActivity.this, ImageCaptureActivity.class);
//                startActivity(i);
                base64profile = "";

                showPictureDialog("");
                break;
            case R.id.ll_ping:
                Bundle bundle = new Bundle();
                bundle.putString("sections_id", activityDetailsPojo.getResponse().getId());
                bundle.putString("type", "notes");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle);

                break;
            case R.id.iv_back:
                onBackPressed();

                break;
            case R.id.ll_view_all:

                openAllViewedPeopleLsit();
                break;
        }
    }

    private void showPictureDialog(final String base64) {
        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary(base64);
                                break;
                            case 1:
                                takePhotoFromCamera(base64);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }


    public void choosePhotoFromGallary(String base64) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), GALLERY_DOC);

    }

    private void takePhotoFromCamera(String base64) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(ActivityDetailsActvity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(ActivityDetailsActvity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        try {
            //   FileName = System.currentTimeMillis() + ".jpg";
            //  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //    startActivityForResult(intent, CAMERA_DOC);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            contentValue = new ContentValues();
            contentValue.put(MediaStore.Images.Media.TITLE, "New Picture");
            contentValue.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
            imageUri = getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, CAMERA_DOC);


//            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(cameraIntent, CAMERA_DOC);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                notesCommentsAdapter.notifyDataSetChanged();
                et_comment.setText("");
            }
        } else if (requestCode == GALLERY_DOC) {
            if (data != null) {
                Uri choosenImage = data.getData();
                if (choosenImage != null) {
                    bp = decodeUri(choosenImage, 300);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bp.compress(Bitmap.CompressFormat.PNG, 70, bytes);
                    byte[] byte_arr = bytes.toByteArray();
                    base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                    tv_captured_image.setVisibility(View.VISIBLE);
                    tv_captured_image.setImageBitmap(bp);
                }
            }
        } else if (requestCode == CAMERA_DOC) {
            try {
                profile_bit = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //profile_bit = (Bitmap) data.getExtras().get("data");
            new Async_BitmapWorkerTaskForProfile().execute();
        }

    }

    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(this.getContentResolver().openInputStream(selectedImage), null, o);
            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;
            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(this.getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    class Async_BitmapWorkerTaskForProfile extends AsyncTask<Integer, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        // Compress and Decode image in background.
        @Override
        protected String doInBackground(Integer... params) {

            Bitmap profilebit = profile_bit;
            bp = decodeUri(imageUri, 200);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bp.compress(Bitmap.CompressFormat.PNG, 60, stream);
            byte[] byte_arr = stream.toByteArray();
            base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);
            return base64profile;
        }

        // This method is run on the UI thread
        @Override
        protected void onPostExecute(String string) {
            try {
                tv_captured_image.setVisibility(View.VISIBLE);
                tv_captured_image.setImageBitmap(bp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void openAllViewedPeopleLsit() {
        LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.dailog_all_replies, null);

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams wlp = alertDialog.getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        alertDialog.setCancelable(true);
        RecyclerView rview_all_replies;
        ImageView im_close;
        rview_all_replies = view1.findViewById(R.id.rview_all_replies);
        im_close = view1.findViewById(R.id.im_close);

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_all_replies.setLayoutManager(manager);
        allViewedPeopleAdapter = new AllViewedPeopleAdapter(context, allViewedPeoplePojo);
        rview_all_replies.setAdapter(allViewedPeopleAdapter);

        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void setPresenter() {
        presenter = new ActivityDetailsImpl(this, this);

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }


    private void callAllRepliesWs(int position) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("section_id", topic_id);
        requestBody.put("comment_id", commentsBean.get(position).getId());
        requestBody.put("type", "activity");
        presenter.viewAllCopmmentReplies(ActivityDetailsActvity.this, this, requestBody);
    }

    private void openDialogforReplytoComment() {
        LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.comment_reply_dialog, null);

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams wlp = alertDialog.getWindow().getAttributes();
        wlp.gravity = Gravity.CENTER;
        alertDialog.setCancelable(true);
        TextView tv_comment_submit;
        final EditText et_comment_reply;
        tv_comment_submit = view1.findViewById(R.id.tv_comment_submit);
        et_comment_reply = view1.findViewById(R.id.et_comment_reply);
        tv_comment_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_comment_reply.getText().toString().isEmpty() || et_comment_reply.getText().toString().equalsIgnoreCase("") || et_comment_reply.getText().toString().length() < 1) {
                    Toast.makeText(context, "Please write reply", Toast.LENGTH_SHORT).show();
                } else {
                    str_reply = et_comment_reply.getText().toString();
                    callReplyWs();
                }
            }
        });

        alertDialog.show();
    }

    private void callReplyWs() {


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("sections_id", topic_id);
        requestBody.put("comment", str_reply);
        requestBody.put("type", "activity");
        requestBody.put("comment_id", commentsBean.get(selected_position).getId());
        presenter.submit_comment_reply(ActivityDetailsActvity.this, this, requestBody);

    }

    //    public static String extractYoutubeVideoId(String ytUrl) {
//
//        String vId = null;
//
//        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
//
//        Pattern compiledPattern = Pattern.compile(pattern);
//        Matcher matcher = compiledPattern.matcher(ytUrl);
//
//        if (matcher.find()) {
//            vId = matcher.group();
//        }
//        return vId;
//    }
    public static String extractYoutubeVideoId(String ytUrl) {

        if (ytUrl.contains("youtube.com")) {
            String vId = null;

            String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(ytUrl);

            if (matcher.find()) {
                vId = matcher.group();
                Log.d("Video ID", vId);
            }
            return vId;
        } else {
            String vId2 = null;
            Pattern pattern = Pattern.compile(
                    "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(ytUrl);
            if (matcher.matches()) {
                vId2 = matcher.group(1);
            }
            return vId2;


        }
    }


    private void callAllComments() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("id", topic_id);
        //requestBody.put("type", "discuss");
        requestBody.put("type", "activity");
        requestBody.put("count", String.valueOf(page_number));
        presenter.getComments(ActivityDetailsActvity.this, this, requestBody);

    }


    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);

            } else if (NetworkConstants.RequestCode.ACTIVITY_DETAILS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    activityDetailsPojo = new Gson().fromJson(responseString, ActivityDetailsPojo.class);
                    allViewedPeoplePojo = activityDetailsPojo.getViews();
                    tv_topic_title.setText(activityDetailsPojo.getResponse().getName());
                    tv_pings_count.setText(activityDetailsPojo.getResponse().getPings() + " Pings");
                    tv_comments_count.setText(activityDetailsPojo.getResponse().getComments() + " Comments");
                    tv_view_count.setText(activityDetailsPojo.getResponse().getViews() + " Views");
                    tv_description.setText(activityDetailsPojo.getResponse().getDescription());

                    activity_tv_first_char.setText(activityDetailsPojo.getResponse().getDescription().charAt(0) + "");

                    subject_id = activityDetailsPojo.getResponse().getSubject_id();
                    chapter_id = activityDetailsPojo.getResponse().getChapter_id();

                    viewedPeopleAdapter = new ViewedPeopleAdapter(this, allViewedPeoplePojo);
                    rview_viewed_people.setAdapter(viewedPeopleAdapter);
                    viewedPeopleAdapter.notifyDataSetChanged();

                    callAllComments();

//                    actvityCommentsAdapter = new ActvityCommentsAdapter(this, activityDetailsPojo.getComments());
//                    rview_comments.setAdapter(actvityCommentsAdapter);
//                    actvityCommentsAdapter.notifyDataSetChanged();
//
//                    callAllComments();
//
//                    actvityCommentsAdapter.setOnItemClickListener(new ActvityCommentsAdapter.OnitemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position) {
//                            switch (view.getId()) {
//                                case R.id.tv_all_replies:
//                                    callAllRepliesWs(position);
//                                    break;
//                                case R.id.ll_reply:
//                                    openDialogforReplytoComment();
//                                    break;
//
//                            }
//                        }
//                    });


                    if (activityDetailsPojo.getResponse().getYoutube().equalsIgnoreCase("") || activityDetailsPojo.getResponse().getYoutube().isEmpty()) {
                        video_youtubePlayerView_activity.setVisibility(View.GONE);
                        iv_image.setVisibility(View.VISIBLE);
                        Picasso.with(context)
                                .load(NetworkConstants.URL.Imagepath_URL + activityDetailsPojo.getResponse().getImage())
                                .into(iv_image);
                    } else {
                        video_youtubePlayerView_activity.setVisibility(View.VISIBLE);
                        iv_image.setVisibility(View.GONE);

                        video_youtubePlayerView_activity.initialize(new YouTubePlayerInitListener() {
                            @Override
                            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                                    @Override
                                    public void onReady() {
                                        String videoId = extractYoutubeVideoId(activityDetailsPojo.getResponse().getYoutube());
                                        initializedYouTubePlayer.cueVideo(videoId, 0);
                                    }
                                });
                            }
                        }, true);

                    }


                    if (activityDetailsPojo.getResponse().getReaded().equalsIgnoreCase("yes")) {
                        ll_unread.setVisibility(View.GONE);
                        ll_unread_blue.setVisibility(View.VISIBLE);
                    }

                    if (activityDetailsPojo.getResponse().getCommented().equalsIgnoreCase("yes")) {
                        tv_cmnt.setTextColor(getResources().getColor(R.color.blue_dark));
                    }


                }
            } else if (NetworkConstants.RequestCode.GET_COMMENTS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    // commentsBean = new ArrayList<>();


//                    JSONArray jsonArray = jsonObject.optJSONArray("response");
//
//                    List<AllCommentsPojo.ResponseBean> responseBeans = new ArrayList<>();
//
//                    if (jsonArray != null && jsonArray.length() > 0) {
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject item = jsonArray.getJSONObject(i);
//                            String id = item.optString("id");
//                            String user_id = item.optString("user_id");
//                            String section_id = item.optString("sections_id");
//                            String comment = item.optString("comment");
//                            String image = item.optString("image");
//                            String type = item.optString("type");
//
//                        }
//
//                    }


                    allCommentsPojo = new Gson().fromJson(responseString, AllCommentsPojo.class);
                    // commentsBean = allCommentsPojo.getResponse();


                    //notesCommentsAdapter.add(commentsBean);
                    commentsBean.addAll(allCommentsPojo.getResponse());

                    if (page_number == 0) {
                        notesCommentsAdapter = new NotesCommentsAdapter(this, commentsBean);
                        rview_comments.setAdapter(notesCommentsAdapter);
                        notesCommentsAdapter.notifyDataSetChanged();
                        rview_comments.setNestedScrollingEnabled(false);
                    } else {
                        notesCommentsAdapter.notifyDataSetChanged();
                    }


                    notesCommentsAdapter.setOnItemClickListener(new NotesCommentsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_position = position;
                            switch (view.getId()) {
                                case R.id.tv_all_replies:
                                    callAllRepliesWs(position);
                                    break;
                                case R.id.ll_reply:
                                    openDialogforReplytoComment();
                                    break;

                            }
                        }
                    });


                    ll_nested_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                        @Override
                        public void onScrollChanged() {
                            View view = (View) ll_nested_scroll.getChildAt(ll_nested_scroll.getChildCount() - 1);

                            int diff = (view.getBottom() - (ll_nested_scroll.getHeight() + ll_nested_scroll
                                    .getScrollY()));

                            if (diff == 0) {
                                // your pagination code
                                page_number++;
                                perfirmPagination();
                            }
                        }
                    });

//
//                    rview_comments.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                        @Override
//                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                            super.onScrolled(recyclerView, dx, dy);
//                            visibleitemCount = manager.getChildCount();
//                            totoalitemCount = manager.getItemCount();
//                            pastVisibleItems = manager.findFirstCompletelyVisibleItemPosition();
//
//                            if (dy > 0) {
//                                if (isLoading) {
//                                    if (totoalitemCount > previous_total) {
//                                        isLoading = false;
//                                        previous_total = totoalitemCount;
//                                    }
//                                }
//                                if (!isLoading && (totoalitemCount - visibleitemCount <= pastVisibleItems + view_threshold)) {
//                                    page_number++;
//                                    perfirmPagination();
//                                    isLoading = true;
//                                }
//
//                            }
//
//                        }
//                    });

                }
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                requestBody.put("user_id", user_id);
                requestBody.put("sections_id", topic_id);
                requestBody.put("type", "activity");
                presenter.submit_as_view(ActivityDetailsActvity.this, apiResponseCallback, requestBody);

            } else if (NetworkConstants.RequestCode.GET_DISCUSS_VIEW == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    activityDetailsPojo = new Gson().fromJson(responseString, ActivityDetailsPojo.class);
                    tv_topic_title.setText(activityDetailsPojo.getResponse().getChapter());
                    // tv_topic_sub.setText(activityDetailsPojo.getResponse().getHeading());
                    tv_pings_count.setText(activityDetailsPojo.getResponse().getPings() + " Pings");
                    tv_comments_count.setText(activityDetailsPojo.getResponse().getComments() + " Comments");
                    tv_view_count.setText(activityDetailsPojo.getResponse().getViews() + " Views");
                    tv_description.setText(activityDetailsPojo.getResponse().getDescription());


                    callAllComments();

//                    if (activityDetailsPojo.getComments().size() > 0) {
//
//                        actvityCommentsAdapter = new ActvityCommentsAdapter(this, activityDetailsPojo.getComments());
//                        rview_comments.setAdapter(actvityCommentsAdapter);
//                        actvityCommentsAdapter.notifyDataSetChanged();
//                        rview_comments.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                            @Override
//                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                                super.onScrollStateChanged(recyclerView, newState);
//
//                                if (!recyclerView.canScrollVertically(1)) {
//                                    Toast.makeText(ActivityDetailsActvity.this, "Last", Toast.LENGTH_LONG).show();
//
//                                }
//                            }
//                        });
//
//                        actvityCommentsAdapter.setOnItemClickListener(new ActvityCommentsAdapter.OnitemClickListener() {
//
//                            @Override
//                            public void onItemClick(View view, int position) {
//
//                                selected_position = position;
//                                switch (view.getId()) {
//                                    case R.id.tv_all_replies:
//                                        callAllRepliesWs(position);
//                                        break;
//                                    case R.id.ll_reply:
//                                        openDialogforReplytoComment();
//                                        break;
//
//                                }
//                            }
//                        });
//                    }


                    if (activityDetailsPojo.getResponse().getReaded().equalsIgnoreCase("yes")) {
                        ll_unread.setVisibility(View.GONE);
                        ll_unread_blue.setVisibility(View.VISIBLE);
                    }

                    if (activityDetailsPojo.getResponse().getCommented().equalsIgnoreCase("yes")) {
                        tv_cmnt.setTextColor(getResources().getColor(R.color.blue_dark));
                    }

                    if (view_stats == "") {
                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("token", token);
                        requestBody.put("user_id", user_id);
                        requestBody.put("sections_id", topic_id);
                        requestBody.put("type", "activity");
                        presenter.submit_as_view(ActivityDetailsActvity.this, apiResponseCallback, requestBody);
                    }


                }
            } else if (NetworkConstants.RequestCode.SUBMIT_VIEW == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    tv_view_count.setText(jsonObject.optString("views") + " Views");
                }


            } else if (NetworkConstants.RequestCode.SUBMIT_COMMENT_REPLY == requestId) {
                boolean status_reply = jsonObject.getBoolean("status");
                if (status_reply == true) {
                    Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();

                    commentsBean = new ArrayList<>();
                    page_number = 0;
                    callAllComments();
                    notesCommentsAdapter.notifyDataSetChanged();
                }
            } else if (NetworkConstants.RequestCode.VIEW_ALL_REPLIES == requestId) {
                boolean status_replies = jsonObject.getBoolean("status");
                if (status_replies == true) {
                    viewAllRepliesPojo = new Gson().fromJson(responseString, ViewAllRepliesPojo.class);
                    openAllRepliesDilog();
                }
            } else if (NetworkConstants.RequestCode.VIEW_ALL_COMMENT_REPLIES == requestId) {
                boolean status_replies = jsonObject.getBoolean("status");
                if (status_replies == true) {
                    commentRepliesPojo = new Gson().fromJson(responseString, CommentRepliesPojo.class);
                    openAllRepliesDilog();
                }
            } else if (NetworkConstants.RequestCode.SUBMIT_COMMENT == requestId) {
                boolean status_comment = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status_comment) {
                    Toast.makeText(this, "Comment Submitted", Toast.LENGTH_SHORT).show();
                    view_stats = "seen";
                    commentsBean = new ArrayList<>();
                    page_number = 0;
                    //callActivityViewWs();
                    callAllComments();
                    notesCommentsAdapter.notifyDataSetChanged();
                    tv_captured_image.setVisibility(View.GONE);
                    base64profile = "";

                    View view = this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                }
            } else if (NetworkConstants.RequestCode.SUBMIT_AS_READ == requestId) {
                boolean status_comment = jsonObject.getBoolean("status");
                if (status_comment) {
                    ll_unread.setVisibility(View.GONE);
                    ll_unread_blue.setVisibility(View.VISIBLE);
                }
            }

        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

    }

    private boolean lastPageItemDisplaying(RecyclerView recyclerView) {

        if (recyclerView.getAdapter().getItemCount() != 0) {
            //get The postion of last item

            int lastVisibleItemPostion = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();

            if (lastVisibleItemPostion != RecyclerView.NO_POSITION && lastVisibleItemPostion == recyclerView.getAdapter().getItemCount() - 1) {
                return true;
            }
        }

        return false;

    }

    private void perfirmPagination() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("id", topic_id);
        //requestBody.put("type", "discuss");
        requestBody.put("type", "activity");
        requestBody.put("count", String.valueOf(page_number));
        presenter.getComments(ActivityDetailsActvity.this, this, requestBody);


        //Toast.makeText(context, "First Page is Loaded", Toast.LENGTH_SHORT).show();
//                    allCommentsPojo = new Gson().fromJson(responseString, AllCommentsPojo.class);
//                    commentsBean.addAll(allCommentsPojo.getResponse());
//                    notesCommentsAdapter = new notesCommentsAdapter(this, commentsBean);
//                    rview_comments.setAdapter(notesCommentsAdapter);
//                    notesCommentsAdapter.notifyDataSetChanged();


    }


    private void callActivityViewWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("activity_post_id", topic_id);
        // requestBody.put("discuss_id", "1");
        presenter.getActivtyDetails(ActivityDetailsActvity.this, this, requestBody);
    }

    private void openAllRepliesDilog() {
        LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.dailog_all_replies, null);

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams wlp = alertDialog.getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        alertDialog.setCancelable(true);
        RecyclerView rview_all_replies;
        ImageView im_close;
        rview_all_replies = view1.findViewById(R.id.rview_all_replies);
        im_close = view1.findViewById(R.id.im_close);

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_all_replies.setLayoutManager(manager);
        viewAllRepliesAdapter = new ViewAllRepliesAdapter(commentRepliesPojo, this);
        rview_all_replies.setAdapter(viewAllRepliesAdapter);
        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.actvity_details_layout, null);
        return view;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        if (player != null) {
            player.setPlayWhenReady(false);
            player.stop();
            player.seekTo(0);
        }
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

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
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.setPlayWhenReady(false);
            player.stop();
            player.seekTo(0);
        }
    }

    @Override
    public void onRefresh() {
//        itemCount = 0;
//        currentPage = PAGE_START;
//        isLastPage = false;
//
//        loadMoreItems();
    }
}
