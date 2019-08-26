package com.iprismtech.studentvarsity.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
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
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.ExpandableListViewAdapter;
import com.iprismtech.studentvarsity.mvp.contract.activity.CreatepostContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.Createpostlmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.ChaptersPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Createpost extends BaseAbstractActivity<Createpostlmpl> implements CreatepostContract.IView, View.OnClickListener, APIResponseCallback {

    TextView tv_select_topic, tv_name;
    LinearLayout ll_home, ll_discuss;
    RelativeLayout ll_post;
    EditText et_desc;
    String from;
    private LinearLayout ll_cancel;

    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, KEY_EDUCATION_ID, KEY_YEARS, KEY_STREAM_ID, KEY_SUBJECTS, KEY_USERID, KEY_UserName, KEY_MOBILENO, KEY_PROFILE, KEY_UNIVERSITY, KEY_COUNTRY_ID, KEY_CITY_ID, KEY_BIO;
    private Bundle bundle;
    private ImageView im_gallery, im_youlink, iv_profile;
    private AlertDialog alertDialog;
    private String str_youtube_link;
    private ChaptersPojo chaptersPojo;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> parentHeaderInformation;
    private HashMap<String, List<String>> allChildItems = new HashMap<>();
    String subtitle;
    ArrayList arrayList, subtitleslist;
    private ExpandableListView expandableListView;
    private String chapter_id, subject_id;


    ImageView iv_capture;
    TextView tv_comment_submit;
    EditText et_commnet;
    ContentValues contentValue;
    int GALLERY_DOC = 101, CAMERA_DOC = 102;
    Uri imageUri;
    Bitmap profile_bit, bp;
    String base64profile, sending_through, section_id;
    private Dialog select_topic_dialogue;
    private String chapter_name;
    ImageView tv_captured_image;
    private EditText et_video_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_createpost);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_createpost, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new Createpostlmpl(this, this);
    }


    @Override
    protected void initializeViews() {
        super.initializeViews();

        tv_select_topic = findViewById(R.id.tv_topicselect);
        im_gallery = findViewById(R.id.im_gallery);
        im_youlink = findViewById(R.id.im_youlink);
        iv_profile = findViewById(R.id.iv_profile);
        tv_name = findViewById(R.id.tv_name);
        tv_captured_image = findViewById(R.id.tv_captured_image);
        et_video_url = findViewById(R.id.et_video_url);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            from = bundle.getString("from");
        }

        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        if (from.equalsIgnoreCase("discuss")) {
            tv_select_topic.setVisibility(View.VISIBLE);
        } else {
            tv_select_topic.setVisibility(View.GONE);
        }


        apiResponseCallback = this;

        userSession = new UserSession(Createpost.this);


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

        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + KEY_PROFILE)
                .error(R.drawable.no_image)
                .into(iv_profile);

        tv_name.setText(KEY_UserName);
        ll_post = findViewById(R.id.ll_post);
        et_desc = findViewById(R.id.et_desc);
        ll_cancel = findViewById(R.id.ll_cancel);
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        /*select_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog select_topic_dialogue = new Dialog(Createpost.this);

                select_topic_dialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
                select_topic_dialogue.setContentView(R.layout.item_select_topic);

                Window window = select_topic_dialogue.getWindow();
                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.BOTTOM);
                select_topic_dialogue.show();
                select_topic_dialogue.dismiss();
            }
        });*/

        im_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                base64profile = "";

                showPictureDialog("");

//
//                LayoutInflater inflater = LayoutInflater.from(context);
////        getLayoutInflater().inflate(R.layout.alert_alerts,null);
//                View view1 = inflater.inflate(R.layout.dialog_image_select, null);
//
//                alertDialog = new AlertDialog.Builder(context).create();
//                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                alertDialog.setView(view1);
//                WindowManager.LayoutParams wlp = alertDialog.getWindow().getAttributes();
//                wlp.gravity = Gravity.CENTER;
//                alertDialog.setCancelable(true);
//
//                TextView tv_gallery, tv_camera;
//
//
//                iv_capture = view1.findViewById(R.id.iv_capture);
//                et_commnet = view1.findViewById(R.id.et_commnet);
//                tv_comment_submit = view1.findViewById(R.id.tv_comment_submit);
//                tv_gallery = view1.findViewById(R.id.tv_gallery);
//                tv_camera = view1.findViewById(R.id.tv_camera);
//
//                tv_gallery.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        choosePhotoFromGallary();
//                    }
//                });
//                tv_camera.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        takePhotoFromCamera();
//                    }
//                });
//
//                tv_comment_submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (base64profile == "") {
//                            Toast.makeText(Createpost.this, "Please Select Image", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(Createpost.this, "Image Selected", Toast.LENGTH_SHORT).show();
//                            alertDialog.dismiss();
//
//                        }
//                    }
//                });
//
//                alertDialog.show();
//            }


            }
        });


        im_youlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
                View view1 = inflater.inflate(R.layout.dialog_youtube_link, null);

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
                            Toast.makeText(context, "Please Paste Youtube link", Toast.LENGTH_SHORT).show();
                        } else {

                            str_youtube_link = et_comment_reply.getText().toString();

                            Toast.makeText(context, "Youtube Link selected", Toast.LENGTH_SHORT).show();

                            alertDialog.dismiss();
                        }
                    }
                });

                alertDialog.show();
            }
        });


        tv_select_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                select_topic_dialogue = new Dialog(Createpost.this, R.style.Theme_Dialog);

                select_topic_dialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
                select_topic_dialogue.setContentView(R.layout.item_select_topic);


                expandableListView = select_topic_dialogue.findViewById(R.id.expandableListView);


                callChaptersWS();


                Window window = select_topic_dialogue.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.BOTTOM);
                select_topic_dialogue.show();

            }
        });


        ll_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserCredentialsValid()) {

                    str_youtube_link = et_video_url.getText().toString();

                    if (from.equalsIgnoreCase("discuss")) {
                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("token", token);
                        requestBody.put("user_id", KEY_USERID);
                        requestBody.put("education_id", KEY_EDUCATION_ID);
                        requestBody.put("stream_id", KEY_STREAM_ID);
                        requestBody.put("subject_id", subject_id);
                        requestBody.put("chapter_id", chapter_id);
                        requestBody.put("image", base64profile);
                        requestBody.put("youtube", str_youtube_link);
                        requestBody.put("description", et_desc.getText().toString().trim());
                        presenter.create_post(Createpost.this, apiResponseCallback, requestBody);
                    } else {
                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("token", token);
                        requestBody.put("user_id", KEY_USERID);
                        requestBody.put("education_id", KEY_EDUCATION_ID);
                        requestBody.put("stream_id", KEY_STREAM_ID);
                        requestBody.put("subject_id", "0");
                        requestBody.put("chapter_id", "0");
                        requestBody.put("image", base64profile);
                        requestBody.put("youtube", str_youtube_link);
                        requestBody.put("description", et_desc.getText().toString().trim());
                        presenter.create_activity(Createpost.this, apiResponseCallback, requestBody);
                    }

//                    Map<String, String> requestBody = new HashMap<>();
//                    requestBody.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs");
//                    requestBody.put("user_id", "1");
//                    requestBody.put("education_id", "3");
//                    requestBody.put("stream_id", "4");
//                    requestBody.put("subject_id", "3");
//                    requestBody.put("chapter_id", "1");
//                    requestBody.put("image", "");
//                    requestBody.put("youtube", "");
//                    requestBody.put("description", et_desc.getText().toString().trim());
//                    presenter.create_activity(Createpost.this, apiResponseCallback, requestBody);

                }
            }
        });

    }

    private void callChaptersWS() {
        Map<String, String> requestBody_chapters = new HashMap<>();
        requestBody_chapters.put("token", token);
        requestBody_chapters.put("education_id", KEY_EDUCATION_ID);
        requestBody_chapters.put("stream_id", KEY_STREAM_ID);
        requestBody_chapters.put("user_id", KEY_USERID);
        requestBody_chapters.put("subject_ids", KEY_SUBJECTS);
        requestBody_chapters.put("type", "notes");
        presenter.getChapters(Createpost.this, this, requestBody_chapters);

        parentHeaderInformation = new ArrayList<String>();
        HashMap<String, List<String>> allChildItems = new HashMap<>();
    }

    private void showPictureDialog(final String base64) {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(context);
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
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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
        } else if (requestCode == GALLERY_DOC) {
            if (data != null) {
                Uri choosenImage = data.getData();
                if (choosenImage != null) {
                    bp = decodeUri(choosenImage, 400);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bp.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                    byte[] byte_arr = bytes.toByteArray();
                    base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);
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
                tv_captured_image.setImageBitmap(bp);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    private boolean isUserCredentialsValid() {
        if (et_desc.getText().toString().length() == 0) {
            et_desc.setError("please enter description");
            Toast.makeText(Createpost.this, "", Toast.LENGTH_SHORT).show();
            //todo: Add shake animation
            return false;
        }
//        else if (edt_loginPasscode.getText().toString().length() == 0) {
//            edt_loginPasscode.setError("please enter password");
//            return false;
//        }
//        else if (edt_loginPasscode.getText().length() < 5) {
//            Util.getInstance().cusToast(context, "password have atleast 5 characters");
//            return false;
//        }
        //todo: Set bg color for gender
        else {
            return true;
//            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN);
            //  Toast.makeText(getApplicationContext(), "user not registered with us!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.CREATE_ACTIVITY == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);
                    finish();

                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.CREATE_POST == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);
                    finish();

                } else {
                    Util.getInstance().cusToast(context, message);
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
                    if (allChildItems.size() == 0) {
                        Toast.makeText(context, "No Chapters Assigned", Toast.LENGTH_SHORT).show();
                    } else {
                        expandableListViewAdapter = new ExpandableListViewAdapter(chaptersPojo, parentHeaderInformation, allChildItems, context);
                        expandableListView.setAdapter(expandableListViewAdapter);
                        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                            @Override
                            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                                if (chaptersPojo.getResponse().get(groupPosition).getChapters().size() < 1) {
                                    Toast.makeText(context, "No Chapters Assigned for selected category", Toast.LENGTH_SHORT).show();
                                } else {
                                    chapter_id = chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getId();
                                    subject_id = chaptersPojo.getResponse().get(groupPosition).getId();
                                    chapter_name = chaptersPojo.getResponse().get(groupPosition).getChapters().get(childPosition).getChapter();
                                    select_topic_dialogue.dismiss();
                                    tv_select_topic.setText(chapter_name);
                                }
                                return false;
                            }
                        });
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
    }
}
