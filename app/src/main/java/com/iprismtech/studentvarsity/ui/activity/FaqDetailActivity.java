package com.iprismtech.studentvarsity.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.AllViewedPeopleAdapter;
import com.iprismtech.studentvarsity.adapters.FAQsCommentsAdapter;
import com.iprismtech.studentvarsity.adapters.NotesCommentsAdapter;
import com.iprismtech.studentvarsity.adapters.ViewAllRepliesAdapter;
import com.iprismtech.studentvarsity.adapters.ViewedPeopleAdapter;
import com.iprismtech.studentvarsity.customviews.CustomTextViewBold;
import com.iprismtech.studentvarsity.customviews.CustomTextViewNormal;
import com.iprismtech.studentvarsity.mvp.contract.activity.FaqDetailActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.FaqDetailActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.AllCommentsPojo;
import com.iprismtech.studentvarsity.pojos.AllViewedPeoplePojo;
import com.iprismtech.studentvarsity.pojos.CommentRepliesPojo;
import com.iprismtech.studentvarsity.pojos.FaqsDetailPOJO;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FaqDetailActivity extends BaseAbstractActivity<FaqDetailActivitylmpl> implements FaqDetailActivityContract.IView, View.OnClickListener, APIResponseCallback {
    private APIResponseCallback apiResponseCallback;
    private TextView tv_viewall, tv_ping, tv_chapter, tv_question, tv_ans, tv_scrolling;
    private TextView tv_pingcount, tv_cmntcount, tv_viewcount;
    private ImageView img_video, img_stcmnt, iv_share, iv_profile, tv_captured_image;
    private PlayerView player_view;
    private SimpleExoPlayer player;
    private ImageView img_read, iv_back;
    private TextView tv_cmnt;
    private EditText et_cmnt;
    private String token, user_id, topic_id;
    private LinearLayout ll_unread, ll_unread_blue, ll_view_all;
    private FaqsDetailPOJO faqsDetailPOJO;
    private RecyclerView rview_faqs;
    private LinearLayoutManager manager, manager_viewed_people;
    private FAQsCommentsAdapter faQsCommentsAdapter;
    private Bundle bundle;
    private String str_reply, user_profile_pic, user_name;
    private AlertDialog alertDialog;
    private int selected_position;
    private CommentRepliesPojo commentRepliesPojo;
    private ViewAllRepliesAdapter viewAllRepliesAdapter;
    private String view_stats = "";
    private ImageView iv_capture;
    String cmntrefresh = "";
    List<AllCommentsPojo.ResponseBean> commentsBean;
    private AllCommentsPojo allCommentsPojo;
    private NotesCommentsAdapter notesCommentsAdapter;

    private ViewedPeopleAdapter viewedPeopleAdapter;
    private AllViewedPeopleAdapter allViewedPeopleAdapter;
    private List<AllViewedPeoplePojo.ViewsBean> allViewedPeoplePojo;
    private RecyclerView rview_viewed_people;
    private NestedScrollView ll_nested_scroll;
    private boolean response_status = false;
    private int page_number = 0;


    private ContentValues contentValue;
    private int GALLERY_DOC = 101, CAMERA_DOC = 102;
    private Uri imageUri;
    private Bitmap profile_bit, bp;

    private String base64profile = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //  setContentView(R.layout.activity_video_detail);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_faq_detail, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new FaqDetailActivitylmpl(this, this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!cmntrefresh.isEmpty()) {
            cmntrefresh = "";
            if (user_id != null) {
                page_number = 0;
                commentsBean = new ArrayList<>();
                callFaqView();

            }
        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        commentsBean = new ArrayList<>();
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        user_name = userSession.getUserDetails().get("name");
        user_profile_pic = userSession.getUserDetails().get("profileImg");

        bundle = getIntent().getExtras();
        if (bundle != null) {
            topic_id = bundle.getString("faq_id");

        }
        ll_nested_scroll = findViewById(R.id.ll_nested_scroll_faq_details);
        tv_viewall = findViewById(R.id.tv_viewall);
        tv_ping = findViewById(R.id.tv_ping);
        tv_chapter = findViewById(R.id.tv_chapter);
        tv_pingcount = findViewById(R.id.tv_pingcount);
        tv_cmntcount = findViewById(R.id.tv_cmntcount);
        tv_viewcount = findViewById(R.id.tv_viewcount);
        tv_question = findViewById(R.id.tv_question);
        tv_ans = findViewById(R.id.tv_ans);
        iv_capture = findViewById(R.id.iv_capture);
        iv_share = findViewById(R.id.iv_share);
        iv_back = findViewById(R.id.iv_back);
        ll_view_all = findViewById(R.id.ll_view_all);
        rview_viewed_people = findViewById(R.id.rview_viewed_people);
        iv_profile = findViewById(R.id.iv_profile);
        tv_captured_image = findViewById(R.id.tv_captured_image);

        tv_scrolling = findViewById(R.id.tv_scrolling);
        tv_scrolling.setSelected(true);
        tv_scrolling.setText(Variables.scrolling_text);

        ll_unread = findViewById(R.id.ll_unread);
        // ll_topic = findViewById(R.id.ll_topic);
        ll_unread_blue = findViewById(R.id.ll_unread_blue);
        //    tv_read = findViewById(R.id.tv_read);
        et_cmnt = findViewById(R.id.et_cmnt);
        img_stcmnt = findViewById(R.id.img_stcmnt);
        tv_cmnt = findViewById(R.id.tv_cmnt);
        player_view = findViewById(R.id.player_view);
        rview_faqs = findViewById(R.id.rview_faqs);

        manager_viewed_people = new LinearLayoutManager(this);
        manager_viewed_people.setOrientation(LinearLayoutManager.HORIZONTAL);
        rview_viewed_people.setLayoutManager(manager_viewed_people);


        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_faqs.setLayoutManager(manager);


        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + user_profile_pic)
                .error(R.drawable.no_image)
                .into(iv_profile);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ll_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllViewedPeopleLsit();
            }
        });

        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Student Varsity");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "http://play.google.com/store/apps/details?id=" + activity.getPackageName();
                intent.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(intent, "choose one"));
            }
        });


        if (user_id != null) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("faq_id", topic_id);
            presenter.view_faq(FaqDetailActivity.this, apiResponseCallback, requestBody);
        }

        tv_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog viewall_dialogue = new Dialog(FaqDetailActivity.this, R.style.Theme_Dialog);

                viewall_dialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
                viewall_dialogue.setContentView(R.layout.view_all_dialouge);
                ImageView imageView = viewall_dialogue.findViewById(R.id.im_close);
                Window window = viewall_dialogue.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.BOTTOM);
                viewall_dialogue.show();

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewall_dialogue.dismiss();
                    }
                });

            }
        });


        tv_ping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaqDetailActivity.this, PingFriendsGroup.class);
                startActivity(intent);
                finish();
            }
        });
        iv_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // cmntrefresh = "abc";

                base64profile = "";

                showPictureDialog("");
//
//                Intent i = new Intent(FaqDetailActivity.this, ImageCaptureActivity.class);
//                i.putExtra("sending_through", "faqs");
//                i.putExtra("section_id", faqsDetailPOJO.getResponse().getId());
//                startActivityForResult(i, 1);


            }
        });
        img_stcmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_cmnt.getText().toString().length() == 0) {
                    //   et_cmnt.setError("please enter comment");
                    Toast.makeText(context, "Please Enter Comment", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("sections_id", topic_id);
                    requestBody.put("comment", et_cmnt.getText().toString().trim());
                    requestBody.put("image", base64profile);
                    requestBody.put("type", "faqs");
                    requestBody.put("name", user_name);

                    if (faqsDetailPOJO.getResponse().getQuestion().length() > 50) {
                        requestBody.put("discussion", faqsDetailPOJO.getResponse().getQuestion().substring(0, 50));
                    } else {
                        requestBody.put("discussion", faqsDetailPOJO.getResponse().getQuestion());
                    }
                    presenter.submit_comment(FaqDetailActivity.this, apiResponseCallback, requestBody);
                    et_cmnt.setText("");
                    tv_captured_image.setVisibility(View.GONE);
                    base64profile = "";
                }
            }
        });

        ll_unread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                requestBody.put("user_id", user_id);
                requestBody.put("sections_id", topic_id);
                requestBody.put("chapter_id", faqsDetailPOJO.getResponse().getChapter_id());
                requestBody.put("subject_id", faqsDetailPOJO.getResponse().getSubject_id());
                requestBody.put("type", "faqs");
                presenter.submit_as_read(FaqDetailActivity.this, apiResponseCallback, requestBody);
            }
        });

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
                    && ContextCompat.checkSelfPermission(FaqDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(FaqDetailActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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
                et_cmnt.setText("");
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
    public void onClick(View view) {

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
        allViewedPeopleAdapter = new AllViewedPeopleAdapter(context, faqsDetailPOJO.getViews());
        rview_all_replies.setAdapter(allViewedPeopleAdapter);

        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void callAllComments() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("id", topic_id);
        requestBody.put("type", "faqs");
        requestBody.put("count", String.valueOf(page_number));
        presenter.getComments(FaqDetailActivity.this, apiResponseCallback, requestBody);
    }


    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.VIEW_FAQS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    faqsDetailPOJO = new Gson().fromJson(responseString, FaqsDetailPOJO.class);

                    tv_chapter.setText(faqsDetailPOJO.getResponse().getChapter());
                    tv_pingcount.setText("Pings");
                    tv_cmntcount.setText(faqsDetailPOJO.getResponse().getComments() + " Comments");
                    tv_viewcount.setText(faqsDetailPOJO.getResponse().getViews() + " Views");
                    tv_question.setText(faqsDetailPOJO.getResponse().getQuestion());
                    tv_ans.setText("Ans : " + faqsDetailPOJO.getResponse().getAnswer());
                    allViewedPeoplePojo = faqsDetailPOJO.getViews();


                    viewedPeopleAdapter = new ViewedPeopleAdapter(this, allViewedPeoplePojo);
                    rview_viewed_people.setAdapter(viewedPeopleAdapter);
                    viewedPeopleAdapter.notifyDataSetChanged();
                    callAllComments();

                    if (faqsDetailPOJO.getResponse().getReaded().equalsIgnoreCase("yes")) {
                        ll_unread.setVisibility(View.GONE);
                        ll_unread_blue.setVisibility(View.VISIBLE);
                    }

                    if (faqsDetailPOJO.getResponse().getCommented().equalsIgnoreCase("yes")) {
                        //  tv_cmnt.setTextColor(getResources().getColor(R.color.blue_dark));
                    }

                    if (faqsDetailPOJO.getResponse().getViewed().equalsIgnoreCase("yes")) {
                        //   tv_ping.setTextColor(getResources().getColor(R.color.blue_dark));
                    }


//
//                    faQsCommentsAdapter = new FAQsCommentsAdapter(this, faqsDetailPOJO.getComments());
//                    rview_faqs.setAdapter(faQsCommentsAdapter);
//                    faQsCommentsAdapter.notifyDataSetChanged();
//                    faQsCommentsAdapter.setOnItemClickListener(new FAQsCommentsAdapter.OnitemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position) {
//                            selected_position = position;
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


//                    Glide.with(FaqDetailActivity.this)
//                            .load(videosDetailPOJO.getResponse().getVideo_link())
//                            .error(R.drawable.no_image)
//                            .into(img_video);


                    Util.getInstance().cusToast(context, message);

                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.GET_COMMENTS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    response_status = true;
                    allCommentsPojo = new Gson().fromJson(responseString, AllCommentsPojo.class);

                    commentsBean.addAll(allCommentsPojo.getResponse());

                    if (page_number == 0) {
                        notesCommentsAdapter = new NotesCommentsAdapter(this, commentsBean);

                        rview_faqs.setAdapter(notesCommentsAdapter);
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
                                    openDialogforReplytoComment();
                                    break;
                            }
                        }
                    });
                } else {
                    response_status = false;
                }

                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                requestBody.put("user_id", user_id);
                requestBody.put("sections_id", topic_id);
                requestBody.put("type", "faqs");
                presenter.submit_as_view(FaqDetailActivity.this, apiResponseCallback, requestBody);


            } else if (NetworkConstants.RequestCode.SUBMIT_COMMENT == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");


                if (status) {
                    Toast.makeText(this, "Comment Submitted", Toast.LENGTH_SHORT).show();
                    base64profile = "";
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
                    base64profile = "";
                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.SUBMIT_VIEW == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    //Util.getInstance().cusToast(context, message);
                    tv_viewcount.setText(jsonObject.optString("views") + " Views");
                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.SUBMIT_AS_READ == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");
                if (status) {
//                    tv_read.setTextColor(getResources().getColor(R.color.blue_dark));
//                    Util.getInstance().cusToast(context, message);

                    ll_unread.setVisibility(View.GONE);
                    ll_unread_blue.setVisibility(View.VISIBLE);
                    Util.getInstance().cusToast(context, message);
                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.VIEW_ALL_COMMENT_REPLIES == requestId) {
                boolean status_replies = jsonObject.getBoolean("status");
                if (status_replies == true) {
                    commentRepliesPojo = new Gson().fromJson(responseString, CommentRepliesPojo.class);
                    openAllRepliesDilog();
                }
            } else if (NetworkConstants.RequestCode.SUBMIT_COMMENT_REPLY == requestId) {
                boolean status_comment = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status_comment) {
                    alertDialog.dismiss();
                    Toast.makeText(this, "Comment Submitted", Toast.LENGTH_SHORT).show();
                    commentsBean = new ArrayList<>();
                    page_number = 0;
                    callAllComments();
                    notesCommentsAdapter.notifyDataSetChanged();

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
        requestBody.put("id", topic_id);
        requestBody.put("type", "faqs");
        requestBody.put("count", String.valueOf(page_number));
        presenter.getComments(FaqDetailActivity.this, apiResponseCallback, requestBody);

    }

    private void callFaqView() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("faq_id", topic_id);
        presenter.view_faq(FaqDetailActivity.this, apiResponseCallback, requestBody);
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
        requestBody.put("type", "faqs");
        requestBody.put("comment_id", commentsBean.get(selected_position).getId());
        presenter.submit_comment_reply(FaqDetailActivity.this, apiResponseCallback, requestBody);

    }

    private void callAllRepliesWs(int position) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("section_id", topic_id);
        requestBody.put("comment_id", commentsBean.get(position).getId());
        requestBody.put("type", "faqs");
        presenter.viewAllCopmmentReplies(FaqDetailActivity.this, apiResponseCallback, requestBody);

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
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
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.setPlayWhenReady(false);
            player.stop();
            player.seekTo(0);
        }
    }
}

