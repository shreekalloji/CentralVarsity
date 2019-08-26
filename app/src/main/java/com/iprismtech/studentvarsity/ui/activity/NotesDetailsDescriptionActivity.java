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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.AllViewedPeopleAdapter;
import com.iprismtech.studentvarsity.adapters.NotesCommentsAdapter;
import com.iprismtech.studentvarsity.adapters.ViewAllRepliesAdapter;
import com.iprismtech.studentvarsity.adapters.ViewedPeopleAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.base.BaseAbstractActivity;
import com.iprismtech.studentvarsity.mvp.contract.activity.NotesDetailsDescriptionContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.NotesDetailsDescriptionImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.AllCommentsPojo;
import com.iprismtech.studentvarsity.pojos.AllViewedPeoplePojo;
import com.iprismtech.studentvarsity.pojos.CommentRepliesPojo;
import com.iprismtech.studentvarsity.pojos.NotesDescriptionViewPojo;
import com.iprismtech.studentvarsity.pojos.ViewAllRepliesPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NotesDetailsDescriptionActivity extends BaseAbstractActivity<NotesDetailsDescriptionImpl> implements NotesDetailsDescriptionContract.IView, View.OnClickListener, APIResponseCallback {

    private ImageView iv_topic_img, iv_comment_submit, iv_share, tv_captured_image;
    private TextView tv_topic_title, tv_topic_sub, tv_pings_count, tv_comments_count, tv_view_count, tv_description,
            tv_discuss_user_name, tv_posted_on, tv_discuss_chapter_name, tv_first_char;
    private LinearLayout ll_ping, ll_comment, ll_unread, ll_topic, ll_unread_blue, ll_view_all;
    private String title, sub_title, pings_count, comments_count, views_count, description;
    private NotesDescriptionViewPojo notesDescriptionViewPojo;
    private APIResponseCallback apiResponseCallback;
    private RecyclerView rview_comments;
    private LinearLayoutManager manager, manager_viewed_people;
    private AllCommentsPojo allCommentsPojo;
    private NestedScrollView ll_nested_scroll;

    private String user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects;

    private NotesCommentsAdapter notesCommentsAdapter;
    private ImageView iv_profile;
    private EditText et_comment;
    private String view_stats = "", user_id, token;
    private ImageView iv_capture;
    private String image, comment;
    private int selected_position;
    private TextView tv_cmnt, tv_scrolling;
    private ViewAllRepliesPojo viewAllRepliesPojo;
    private CommentRepliesPojo commentRepliesPojo;
    private AlertDialog alertDialog;
    private ViewAllRepliesAdapter viewAllRepliesAdapter;
    private Bundle bundle;
    private String topic_id, sending_through;
    private String str_reply;
    private LinearLayout ll_discuss_detail, ll_notes_details;
    private ImageView iv_back;
    String cmntrefresh = "";
    private UserSession userSession;
    List<AllCommentsPojo.ResponseBean> commentsBean;
    private View adaptet_item_view;
    private ViewedPeopleAdapter viewedPeopleAdapter;
    private AllViewedPeopleAdapter allViewedPeopleAdapter;
    private List<AllViewedPeoplePojo.ViewsBean> allViewedPeoplePojo;
    private RecyclerView rview_viewed_people;
    private boolean response_status = false;
    private int page_number = 0;


    private ContentValues contentValue;
    private int GALLERY_DOC = 101, CAMERA_DOC = 102;
    private Uri imageUri;
    private Bitmap profile_bit, bp;

    private String base64profile = "";


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_comment_submit.setOnClickListener(this);
        ll_unread.setOnClickListener(this);
        ll_unread.setOnClickListener(this);
        iv_capture.setOnClickListener(this);
        ll_ping.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        ll_view_all.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;

        commentsBean = new ArrayList<>();
        //iv_topic_img = findViewById(R.id.iv_topic_img);
        tv_topic_title = findViewById(R.id.tv_title);
        tv_topic_sub = findViewById(R.id.tv_sub_topic);
        tv_pings_count = findViewById(R.id.tv_pings_count);
        tv_comments_count = findViewById(R.id.tv_comments_count);
        tv_scrolling = findViewById(R.id.tv_scrolling);
        tv_view_count = findViewById(R.id.tv_view_count);
        tv_description = findViewById(R.id.tv_description);
        iv_profile = findViewById(R.id.iv_profile);
        ll_ping = findViewById(R.id.ll_ping);
        ll_comment = findViewById(R.id.ll_comment);
        ll_unread = findViewById(R.id.ll_unread);
        iv_share = findViewById(R.id.iv_share);
        ll_view_all = findViewById(R.id.ll_view_all);
        tv_discuss_user_name = findViewById(R.id.tv_discuss_user_name);
        tv_posted_on = findViewById(R.id.tv_posted_on);
        tv_discuss_chapter_name = findViewById(R.id.tv_discuss_chapter_name);
        tv_first_char = findViewById(R.id.tv_first_char);
        rview_viewed_people = findViewById(R.id.rview_viewed_people);
        tv_captured_image = findViewById(R.id.tv_captured_image);
        // ll_topic = findViewById(R.id.ll_topic);
        ll_unread_blue = findViewById(R.id.ll_unread_blue);
        et_comment = findViewById(R.id.et_comment);
        iv_comment_submit = findViewById(R.id.iv_comment_submit);
        iv_capture = findViewById(R.id.iv_capture);
//        tv_read = findViewById(R.id.tv_read);
//        iv_ticks = findViewById(R.id.iv_ticks);
        tv_cmnt = findViewById(R.id.tv_cmnt);
        ll_discuss_detail = findViewById(R.id.ll_discuss_detail);
        ll_notes_details = findViewById(R.id.ll_notes_details);
        iv_back = findViewById(R.id.iv_back);
        ll_nested_scroll = findViewById(R.id.nested_notes_detail);


        tv_scrolling.setSelected(true);
        tv_scrolling.setText(Variables.scrolling_text);

        rview_comments = findViewById(R.id.rview_comments);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_comments.setLayoutManager(manager);

        manager_viewed_people = new LinearLayoutManager(this);
        manager_viewed_people.setOrientation(LinearLayoutManager.HORIZONTAL);
        rview_viewed_people.setLayoutManager(manager_viewed_people);

        bundle = getIntent().getExtras();

        userSession = new UserSession(NotesDetailsDescriptionActivity.this);
        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        user_name = userSession.getUserDetails().get("name");
        user_university = userSession.getUserDetails().get("university");
        user_bio = userSession.getUserDetails().get("bio");
        user_profile_pic = userSession.getUserDetails().get("profileImg");
        stream = userSession.getUserDetails().get("stream_id");
        years = userSession.getUserDetails().get("years");
        user_subjects = userSession.getUserDetails().get("subject_names");

        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + user_profile_pic)
                .error(R.drawable.no_image)
                .into(iv_profile);

        if (bundle != null) {
            topic_id = bundle.getString("topic_id");
            sending_through = bundle.getString("sending_through");
        }

        if (sending_through.equalsIgnoreCase("discuss")) {
            ll_notes_details.setVisibility(View.GONE);
            ll_discuss_detail.setVisibility(View.VISIBLE);
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("discuss_id", topic_id);
            // requestBody.put("discuss_id", "1");
            presenter.getDiscussDate(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);

        } else {
            ll_notes_details.setVisibility(View.VISIBLE);
            ll_discuss_detail.setVisibility(View.GONE);
            callNotesViewWs();
        }

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//
//          /*  "title"
//            "subtitle"
//            "description"
//            "ping_count"
//            "comments_count"
//            "views_count"*/
//            title = bundle.getString("title");
//            sub_title = bundle.getString("subtitle");
//            pings_count = bundle.getString("ping_count");
//            comments_count = bundle.getString("comments_count");
//            views_count = bundle.getString("views_count");
//            description = bundle.getString("description");
//            tv_topic_title.setText(title);
//            tv_topic_sub.setText(sub_title);
//            tv_pings_count.setText(pings_count + " Pings");
//            tv_comments_count.setText(comments_count + " Comments");
//            tv_view_count.setText(views_count + " Views");
//            tv_description.setText(description);
//        }

    }

    private void callNotesViewWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("note_id", topic_id);
        requestBody.put("count", String.valueOf(page_number));
        presenter.getNotesDesc(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);
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
                    && ContextCompat.checkSelfPermission(NotesDetailsDescriptionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(NotesDetailsDescriptionActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_comment_submit:
                if (et_comment.getText().toString().length() == 0) {
                    Toast.makeText(this, "Write Comment", Toast.LENGTH_SHORT).show();
                } else if (sending_through.equalsIgnoreCase("discuss")) {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("sections_id", topic_id);
                    requestBody.put("image", base64profile);
                    requestBody.put("type", "discuss");
                    requestBody.put("comment", et_comment.getText().toString());
                    requestBody.put("name", user_name);
                    if (notesDescriptionViewPojo.getResponse().getDescription().length() > 50) {
                        requestBody.put("discussion", notesDescriptionViewPojo.getResponse().getDescription().substring(0, 50));
                    } else {
                        requestBody.put("discussion", notesDescriptionViewPojo.getResponse().getDescription());
                    }
                    presenter.submit_comment(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);
                    et_comment.setText("");
                    tv_captured_image.setVisibility(View.GONE);
                    base64profile = "";
                } else {

                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("sections_id", topic_id);
                    requestBody.put("image", base64profile);
                    requestBody.put("type", "notes");
                    requestBody.put("comment", et_comment.getText().toString());
                    requestBody.put("name", user_name);
                    if (notesDescriptionViewPojo.getResponse().getDescription().length() > 50) {
                        requestBody.put("discussion", notesDescriptionViewPojo.getResponse().getDescription().substring(0, 50));
                    } else {
                        requestBody.put("discussion", notesDescriptionViewPojo.getResponse().getDescription());
                    }
                    presenter.submit_comment(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);
                    et_comment.setText("");
                    tv_captured_image.setVisibility(View.GONE);
                    base64profile = "";
                }
                break;
            case R.id.ll_unread:
                if (sending_through.equalsIgnoreCase("discuss")) {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("subject_id", notesDescriptionViewPojo.getResponse().getSubject_id());
                    requestBody.put("chapter_id", notesDescriptionViewPojo.getResponse().getChapter_id());
                    requestBody.put("sections_id", topic_id);
                    requestBody.put("type", "discuss");
                    presenter.submit_as_read(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);


                } else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("subject_id", notesDescriptionViewPojo.getResponse().getSubject_id());
                    requestBody.put("chapter_id", notesDescriptionViewPojo.getResponse().getChapter_id());
                    requestBody.put("sections_id", topic_id);
                    requestBody.put("type", "notes");
                    presenter.submit_as_read(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);
                }
                break;
            case R.id.iv_capture:

                base64profile = "";

                showPictureDialog("");


//                cmntrefresh = "abc";
//                if (sending_through.equalsIgnoreCase("discuss")) {
//                    Intent i = new Intent(NotesDetailsDescriptionActivity.this, ImageCaptureActivity.class);
//                    i.putExtra("sending_through", "discuss");
//                    i.putExtra("section_id", notesDescriptionViewPojo.getResponse().getId());
//                    startActivityForResult(i, 1);
//                } else {
//                    Intent i = new Intent(NotesDetailsDescriptionActivity.this, ImageCaptureActivity.class);
//                    i.putExtra("sending_through", "notes");
//                    i.putExtra("section_id", notesDescriptionViewPojo.getResponse().getId());
//                    startActivityForResult(i, 1);
//                }
//                Intent i = new Intent(NotesDetailsDescriptionActivity.this, ImageCaptureActivity.class);
//                startActivity(i);


                break;

            case R.id.ll_ping:
                Bundle bundle = new Bundle();
                bundle.putString("sections_id", notesDescriptionViewPojo.getResponse().getId());
                if (sending_through.equalsIgnoreCase("discuss")) {
                    bundle.putString("type", "discuss");
                } else {
                    bundle.putString("type", "notes");
                }
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle);

                break;
            case R.id.iv_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Student Varsity");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "http://play.google.com/store/apps/details?id=" + activity.getPackageName();
                intent.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(intent, "choose one"));
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.ll_view_all:

                openAllViewedPeopleLsit();
                break;
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


        allViewedPeopleAdapter = new AllViewedPeopleAdapter(context, notesDescriptionViewPojo.getViews());
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!cmntrefresh.isEmpty()) {
            cmntrefresh = "";
            page_number = 0;
            commentsBean = new ArrayList<>();
            if (sending_through.equalsIgnoreCase("discuss")) {
                callDiscussWs();
            } else {
                callNotesViewWs();
            }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == 1) {
//            if (resultCode == Activity.RESULT_OK) {
//                notesCommentsAdapter.notifyDataSetChanged();
//                et_comment.setText("");
//
//            }
//            if (resultCode == Activity.RESULT_CANCELED) {
//                //Write your code if there's no result
//            }
//        }
//    }

    @Override
    public void setPresenter() {
        presenter = new NotesDetailsDescriptionImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);

            } else if (NetworkConstants.RequestCode.GET_NOTES_VIEW == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    notesDescriptionViewPojo = new Gson().fromJson(responseString, NotesDescriptionViewPojo.class);
                    allViewedPeoplePojo = notesDescriptionViewPojo.getViews();
                    tv_topic_sub.setText(notesDescriptionViewPojo.getResponse().getHeading());
                    tv_topic_title.setText(notesDescriptionViewPojo.getResponse().getChapter());

                    tv_pings_count.setText("Pings");
                    tv_comments_count.setText(notesDescriptionViewPojo.getResponse().getComments() + " Comments");
                    tv_view_count.setText(notesDescriptionViewPojo.getResponse().getViews() + " Views");
                    tv_description.setText(notesDescriptionViewPojo.getResponse().getDescription());

                    viewedPeopleAdapter = new ViewedPeopleAdapter(this, allViewedPeoplePojo);
                    rview_viewed_people.setAdapter(viewedPeopleAdapter);
                    viewedPeopleAdapter.notifyDataSetChanged();
                    callAllComments();

                    if (notesDescriptionViewPojo.getResponse().getReaded().equalsIgnoreCase("yes")) {
                        ll_unread.setVisibility(View.GONE);
                        ll_unread_blue.setVisibility(View.VISIBLE);
                    }
                    if (notesDescriptionViewPojo.getResponse().getCommented().equalsIgnoreCase("yes")) {
                        tv_cmnt.setTextColor(getResources().getColor(R.color.blue_dark));
                    }
                }
            } else if (NetworkConstants.RequestCode.GET_COMMENTS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    response_status = true;

                    allCommentsPojo = new Gson().fromJson(responseString, AllCommentsPojo.class);

                    commentsBean.addAll(allCommentsPojo.getResponse());
                    if (page_number == 0) {
                        notesCommentsAdapter = new NotesCommentsAdapter(this, commentsBean);
                        rview_comments.setAdapter(notesCommentsAdapter);
                        notesCommentsAdapter.notifyDataSetChanged();
                    } else {
                        notesCommentsAdapter.notifyDataSetChanged();

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


                    notesCommentsAdapter.setOnItemClickListener(new NotesCommentsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_position = position;

                            switch (view.getId()) {

                                case R.id.tv_all_replies:
                                    callAllRepliesWs(position);
                                    break;
                                case R.id.ll_reply:


//                                    LinearLayout ll_comment_reply = (LinearLayout) view.findViewById(R.id.ll_comment_reply);
//                                    ll_comment_reply.setVisibility(View.VISIBLE);
//
//                                    EditText et_reply_comment = (EditText) view.findViewById(R.id.et_reply_comment);
//                                    et_reply_comment.setVisibility(View.VISIBLE);

                                    openDialogforReplytoComment();
                                    break;
                                case R.id.tv_reply_comment_post:
//                                    adaptet_item_view = view;
//                                    EditText et_reply_comment = view.getRootView().findViewById(R.id.et_reply_comment);
//
//
//
//                                    if (et_reply_comment.getText().toString().isEmpty() || et_reply_comment.getText().toString().equalsIgnoreCase("") || et_reply_comment.getText().toString().length() < 1) {
//                                        Toast.makeText(context, "Please write reply", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        str_reply = et_reply_comment.getText().toString();
//                                        callReplyWs();
//                                    }
                                    break;
                            }
                        }
                    });
                } else {
                    response_status = false;

                }
                if (sending_through.equalsIgnoreCase("discuss")) {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("sections_id", topic_id);
                    requestBody.put("type", "discuss");
                    presenter.submit_as_view(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);

                } else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("sections_id", topic_id);
                    requestBody.put("type", "notes");
                    presenter.submit_as_view(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);
                }

            } else if (NetworkConstants.RequestCode.GET_DISCUSS_VIEW == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    ll_notes_details.setVisibility(View.GONE);
                    ll_discuss_detail.setVisibility(View.VISIBLE);
                    notesDescriptionViewPojo = new Gson().fromJson(responseString, NotesDescriptionViewPojo.class);
                    allViewedPeoplePojo = notesDescriptionViewPojo.getViews();
                    tv_discuss_user_name.setText(notesDescriptionViewPojo.getResponse().getName());
                    tv_posted_on.setText(notesDescriptionViewPojo.getResponse().getPosted_on());
                    tv_discuss_chapter_name.setText(notesDescriptionViewPojo.getResponse().getChapter());


                    tv_pings_count.setText("Pings");
                    tv_comments_count.setText(notesDescriptionViewPojo.getResponse().getComments() + " Comments");
                    tv_view_count.setText(notesDescriptionViewPojo.getResponse().getViews() + " Views");
                    tv_description.setText(notesDescriptionViewPojo.getResponse().getDescription());
                    tv_first_char.setText(notesDescriptionViewPojo.getResponse().getName().charAt(0) + "");

                    viewedPeopleAdapter = new ViewedPeopleAdapter(this, allViewedPeoplePojo);
                    rview_viewed_people.setAdapter(viewedPeopleAdapter);
                    viewedPeopleAdapter.notifyDataSetChanged();

                    callAllComments();
                    if (notesDescriptionViewPojo.getComments().size() > 0) {

                        //callAllComments();
//                        notesCommentsAdapter = new NotesCommentsAdapter(this, notesDescriptionViewPojo.getComments());
//                        rview_comments.setAdapter(notesCommentsAdapter);
//                        notesCommentsAdapter.notifyDataSetChanged();
//
//                        notesCommentsAdapter.setOnItemClickListener(new NotesCommentsAdapter.OnitemClickListener() {
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
                    }
                    if (notesDescriptionViewPojo.getResponse().getReaded().equalsIgnoreCase("yes")) {
                        ll_unread.setVisibility(View.GONE);
                        ll_unread_blue.setVisibility(View.VISIBLE);
                    }
                    if (notesDescriptionViewPojo.getResponse().getCommented().equalsIgnoreCase("yes")) {
                        tv_cmnt.setTextColor(getResources().getColor(R.color.blue_dark));
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
                    if (sending_through.equalsIgnoreCase("discuss")) {
                        //callDiscussWs();
                        commentsBean = new ArrayList<>();
                        page_number = 0;
                        callAllComments();
                        notesCommentsAdapter.notifyDataSetChanged();
                        tv_captured_image.setVisibility(View.GONE);
                        base64profile = "";

                        View view = this.getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }

                    } else {
                        commentsBean = new ArrayList<>();
                        page_number = 0;
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

    private void perfirmPagination() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("id", topic_id);
        requestBody.put("count", String.valueOf(page_number));

        if (sending_through.equalsIgnoreCase("discuss")) {
            requestBody.put("type", "discuss");
        } else {
            requestBody.put("type", "notes");
        }
        requestBody.put("count", String.valueOf(page_number));
        presenter.getComments(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);

    }

    private void callAllComments() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("id", topic_id);
        if (sending_through.equalsIgnoreCase("discuss")) {
            requestBody.put("type", "discuss");
        } else {
            requestBody.put("type", "notes");
        }
        requestBody.put("count", String.valueOf(page_number));
        presenter.getComments(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);

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
        ImageView tv_img_dlg, iv_posted_img_dlg;
        TextView tv_Name_dlg, tv_uni_name_dlg, tv_time_dlg, tv_comment_dlg;

        tv_comment_submit = view1.findViewById(R.id.tv_comment_submit);
        et_comment_reply = view1.findViewById(R.id.et_comment_reply);

        tv_img_dlg = view1.findViewById(R.id.tv_img_dlg);
        tv_Name_dlg = view1.findViewById(R.id.tv_Name_dlg);
        tv_uni_name_dlg = view1.findViewById(R.id.tv_uni_name_dlg);
        tv_time_dlg = view1.findViewById(R.id.tv_time_dlg);
        tv_comment_dlg = view1.findViewById(R.id.tv_comment_dlg);
        iv_posted_img_dlg = view1.findViewById(R.id.iv_posted_img_dlg);

        tv_Name_dlg.setText(commentsBean.get(selected_position).getName());
        tv_uni_name_dlg.setText(commentsBean.get(selected_position).getUniversity());
        tv_time_dlg.setText(commentsBean.get(selected_position).getPosted_on());
        tv_comment_dlg.setText(commentsBean.get(selected_position).getComment());


        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + commentsBean.get(selected_position).getProfile_pic())
                .error(R.drawable.no_image)
                .into(tv_img_dlg);

        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + commentsBean.get(selected_position).getImage())
                .into(iv_posted_img_dlg);


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

        if (sending_through.equalsIgnoreCase("discuss")) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("sections_id", topic_id);
            requestBody.put("comment", str_reply);
            requestBody.put("type", "discuss");
            requestBody.put("comment_id", commentsBean.get(selected_position).getId());
            presenter.submit_comment_reply(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);
        } else {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("sections_id", topic_id);
            requestBody.put("comment", str_reply);
            requestBody.put("type", "notes");
            requestBody.put("comment_id", commentsBean.get(selected_position).getId());
            presenter.submit_comment_reply(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);

        }
    }

    private void callDiscussWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("discuss_id", topic_id);
        presenter.getDiscussDate(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);

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

    private void callAllRepliesWs(int position) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("section_id", topic_id);
        requestBody.put("comment_id", commentsBean.get(position).getId());
        requestBody.put("type", "notes");
        presenter.viewAllCopmmentReplies(NotesDetailsDescriptionActivity.this, apiResponseCallback, requestBody);
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.notes_discription_details, null);
        return view;
    }
}
