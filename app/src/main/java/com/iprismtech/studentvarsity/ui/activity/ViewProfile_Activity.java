package com.iprismtech.studentvarsity.ui.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.activities.ProfileEdit;
import com.iprismtech.studentvarsity.adapters.MyActivityPostsAdapter;
import com.iprismtech.studentvarsity.adapters.MyFriendsAdapter;
import com.iprismtech.studentvarsity.adapters.MyPostedDiscussionsAdapter;
import com.iprismtech.studentvarsity.adapters.MyQuizzesAdapter;
import com.iprismtech.studentvarsity.adapters.MySuggestionsAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.ViewProfile_ActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ViewProfile_Activitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.FriendsPojo;
import com.iprismtech.studentvarsity.pojos.MyActivityPostsPojo;
import com.iprismtech.studentvarsity.pojos.MyPostedDiscussionsPojo;
import com.iprismtech.studentvarsity.pojos.MyPostedSuggestionsPojo;
import com.iprismtech.studentvarsity.pojos.MyQuizzesPojo;
import com.iprismtech.studentvarsity.pojos.UserProfilePojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfile_Activity extends BaseAbstractActivity<ViewProfile_Activitylmpl> implements ViewProfile_ActivityContract.IView, View.OnClickListener, APIResponseCallback {

    ImageView iv_settings, iv_edit, img_cover;
    LinearLayout ll_friend, ll_post, ll_question, ll_comment;
    TextView tv_friend, tv_post, tv_questions, tv_comments, tv_suggestions;
    ImageView im_friends, im_post, im_questions, im_comments, ic_suggestions, iv_back;

    private boolean banner_status = false;
    private boolean profile_status = false;


    APIResponseCallback apiResponseCallback;

    private UserSession userSession;
    private String token, user_id, user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects, itemimageurl;
    TextView tv_user_name, tv_user_univarsity, tv_bio, tv_course_year, tv_subjects;

    TextView tv_coverpic;
    CircleImageView profile_image;

    public static final int MY_REQUEST_CODE = 4;
    final int CAMERA_CAPTURE = 1;
    final int PICK_IMAGE = 2;
    final int PIC_CROP = 3;
    String PickedImgPath = "empty";
    String encodeStringbg = "empty";
    String encodeStringprof = "empty";
    int which;

    private ImageView iv_profile_img;
    private LinearLayout ll_friends, ll_suggestions;
    private RecyclerView rview_friends, rview_posts, rview_comments, rview_questions, rview_suggestions;
    private LinearLayoutManager manager;
    private FriendsPojo friendsPojo;

    private MyFriendsAdapter myFriendsAdapter;
    private MyActivityPostsAdapter myActivityPostsAdapter;
    private MySuggestionsAdapter mySuggestionsAdapter;
    private MyPostedDiscussionsAdapter myPostedDiscussionsAdapter;
    private MyQuizzesAdapter myQuizzesAdapter;

    private MyQuizzesPojo myQuizzesPojo;
    private MyActivityPostsPojo myActivityPostsPojo;
    private MyPostedDiscussionsPojo myPostedDiscussionsPojo;
    private MyPostedSuggestionsPojo myPostedSuggestionsPojo;
    private UserProfilePojo userProfilePojo;
    private String user_pic;


    public void permissions() {

        if (ActivityCompat.checkSelfPermission(ViewProfile_Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(ViewProfile_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(ViewProfile_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ViewProfile_Activity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_REQUEST_CODE);
        } else if (ActivityCompat.checkSelfPermission(ViewProfile_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ViewProfile_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_REQUEST_CODE);
        } else if (ActivityCompat.checkSelfPermission(ViewProfile_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ViewProfile_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_REQUEST_CODE);
        } else if (ActivityCompat.checkSelfPermission(ViewProfile_Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ViewProfile_Activity.this, new String[]{Manifest.permission.CAMERA},
                    MY_REQUEST_CODE);
        } else {
            selectImage();
        }

    }

    private void selectImage() {

        final CharSequence[] options = {getResources().getString(R.string.takephoto), getResources().getString(R.string.choosefrmgallery), getResources().getString(R.string.cancel)};

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ViewProfile_Activity.this);
        builder.setTitle(getResources().getString(R.string.addphoto));
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].toString().equalsIgnoreCase(getResources().getString(R.string.takephoto))) {
                    cameraIntent();

                } else if (options[item].toString().equalsIgnoreCase(getResources().getString(R.string.choosefrmgallery))) {
                    browse();

                } else if (options[item].toString().equalsIgnoreCase(getResources().getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void browse() {
        try {
//            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(galleryIntent, PICK_IMAGE);
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    private void cameraIntent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_CAPTURE);


    }

    private void performCrop(Uri picUri) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties here
            cropIntent.putExtra("crop", true);
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_CAPTURE) {
            if (resultCode == RESULT_OK) {
                onCaptureImageResult(data);


            }

        } else if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {

                Uri picUri = data.getData();
//                String[] filePath = {MediaStore.Images.Media.DATA};
//                Cursor c = ViewProfile_Activity.this.getContentResolver().query(picUri, filePath, null, null, null);
//                c.moveToFirst();
//                int columnIndex = c.getColumnIndex(filePath[0]);
//                PickedImgPath = GalleryUriToPath.getPath(ViewProfile_Activity.this, picUri);
//                File pickedfile = new File(PickedImgPath);


                if (which == 1) {
                    //performCrop(picUri);

                    CropImage.activity(picUri)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setAspectRatio(120, 120)
                            .start(this);

                } else {
                    // performCrop(picUri);

                    CropImage.activity(picUri)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setAspectRatio(120, 50)
                            .start(this);
                }
                try {
//                    if (which == 1) {
//                        encodeStringbg = encodeFileToBase64Binary(pickedfile);
//
//                        Map<String, String> requestBody = new HashMap<>();
//                        requestBody.put("token", token);
//                        requestBody.put("user_id", user_id);
//                        requestBody.put("cover_pic", encodeStringbg);
//                        presenter.update_cover_pic(ViewProfile_Activity.this, apiResponseCallback, requestBody);
//                    } else {
//                        encodeStringprof = encodeFileToBase64Binary(pickedfile);
//
//                        Map<String, String> requestBody = new HashMap<>();
//                        requestBody.put("token", token);
//                        requestBody.put("user_id", user_id);
//                        requestBody.put("profile_pic", encodeStringprof);
//                        presenter.update_profile_pic(ViewProfile_Activity.this, apiResponseCallback, requestBody);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Bitmap bm = BitmapFactory.decodeStream(
                            ViewProfile_Activity.this.getContentResolver().openInputStream(picUri));
                    if (which == 1) {
//                        BitmapDrawable background = new BitmapDrawable(getResources(), bm);
//                        lv.setBackground(background);
                    } else {
                        //  profile_image.setImageBitmap(bm);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //  c.close();
            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            Log.e("onActivityResult: ", "" + result.getUri());

//                String url123 = mCropImageUri.toString().substring(8);
            itemimageurl = result.getUri().toString();
            System.out.println("dfmgvkl'" + itemimageurl);


            if (which == 1) {
                img_cover.setImageURI(result.getUri());
                Bitmap bitmap = ((BitmapDrawable) img_cover.getDrawable()).getBitmap();
                SaveImage(bitmap);

            } else {
                profile_image.setImageURI(result.getUri());
                Bitmap bitmap = ((BitmapDrawable) profile_image.getDrawable()).getBitmap();
                SaveImage(bitmap);
            }
        }
    }

    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            itemimageurl = file.getAbsolutePath();
            Log.e("file name", itemimageurl);
            out.flush();
            out.close();


            try {
                if (which == 1) {
                    encodeStringbg = encodeFileToBase64Binary(file);

                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("cover_pic", encodeStringbg);
                    presenter.update_cover_pic(ViewProfile_Activity.this, apiResponseCallback, requestBody);
                } else {
                    encodeStringprof = encodeFileToBase64Binary(file);

                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("profile_pic", encodeStringprof);
                    presenter.update_profile_pic(ViewProfile_Activity.this, apiResponseCallback, requestBody);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*CropImage.activity(Uri.fromFile(new File(itemimageurl)))
                    .start(this);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onCaptureImageResult(Intent data) {
        byte[] bt = null;


        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
            bt = bytes.toByteArray();
            // encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
            itemimageurl = destination.getAbsolutePath();
            Log.e("Camera Path", destination.getAbsolutePath());


            Uri myUri = Uri.parse(itemimageurl);

            if (which == 1) {

                CropImage.activity(Uri.fromFile(new File(itemimageurl)))
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setOutputCompressQuality(100)
                        .setAspectRatio(120, 50)
                        .start(this);
            } else {
                CropImage.activity(Uri.fromFile(new File(itemimageurl)))
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setOutputCompressQuality(100)
                        .setAspectRatio(120, 120)
                        .start(this);
            }
            Log.e("fjgd", itemimageurl);


            PickedImgPath = destination.getAbsolutePath();

            File pickedfile = new File(PickedImgPath);

//            try {
//                if (which == 1) {
//                    encodeStringbg = encodeFileToBase64Binary(pickedfile);
//
//                    Map<String, String> requestBody = new HashMap<>();
//                    requestBody.put("token", token);
//                    requestBody.put("user_id", user_id);
//                    requestBody.put("cover_pic", encodeStringbg);
//                    presenter.update_cover_pic(ViewProfile_Activity.this, apiResponseCallback, requestBody);
//                } else {
//                    encodeStringprof = encodeFileToBase64Binary(pickedfile);
//
//                    Map<String, String> requestBody = new HashMap<>();
//                    requestBody.put("token", token);
//                    requestBody.put("user_id", user_id);
//                    requestBody.put("profile_pic", encodeStringprof);
//                    presenter.update_profile_pic(ViewProfile_Activity.this, apiResponseCallback, requestBody);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            Log.e("Camera Path", destination.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (which == 1) {
//            BitmapDrawable background = new BitmapDrawable(getResources(), thumbnail);
//            lv.setBackground(background);
        } else {
            //  profile_image.setImageBitmap(thumbnail);
        }

    }

    private static String encodeFileToBase64Binary(File file) throws Exception {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_friends.setOnClickListener(this);
        ll_post.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
        ll_question.setOnClickListener(this);
        ll_suggestions.setOnClickListener(this);
        iv_back.setOnClickListener(this);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //setContentView(R.layout.activity_view_profile_);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_view_profile_, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new ViewProfile_Activitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(ViewProfile_Activity.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        user_name = userSession.getUserDetails().get("name");
        user_university = userSession.getUserDetails().get("university");
        user_bio = userSession.getUserDetails().get("bio");
        user_profile_pic = userSession.getUserDetails().get("profileImg");
        stream = userSession.getUserDetails().get("stream");
        years = userSession.getUserDetails().get("years");
        user_subjects = userSession.getUserDetails().get("subject_names");

        apiResponseCallback = this;
        iv_settings = findViewById(R.id.iv_settings);
        iv_edit = findViewById(R.id.iv_edit);
        img_cover = findViewById(R.id.img_cover);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_user_univarsity = findViewById(R.id.tv_user_univarsity);
        tv_bio = findViewById(R.id.tv_bio);
        tv_suggestions = findViewById(R.id.tv_suggestions);
        ic_suggestions = findViewById(R.id.ic_suggestions);
        tv_course_year = findViewById(R.id.tv_course_year);
        tv_subjects = findViewById(R.id.tv_subjects);
        iv_back = findViewById(R.id.iv_back);


        ll_friends = findViewById(R.id.ll_friends);
        ll_post = findViewById(R.id.ll_post);
        ll_comment = findViewById(R.id.ll_comment);
        ll_question = findViewById(R.id.ll_question);
        ll_suggestions = findViewById(R.id.ll_suggestions);


        tv_post = findViewById(R.id.tv_post);
        tv_questions = findViewById(R.id.tv_questions);
        tv_comments = findViewById(R.id.tv_comments);
        im_friends = findViewById(R.id.im_friend);
        im_post = findViewById(R.id.im_post);
        im_questions = findViewById(R.id.im_question);
        im_comments = findViewById(R.id.im_comments);
        tv_coverpic = findViewById(R.id.tv_coverpic);
        profile_image = findViewById(R.id.profile_image);
        tv_friend = findViewById(R.id.tv_friend);


        rview_friends = findViewById(R.id.rview_friends);
        rview_posts = findViewById(R.id.rview_posts);
        rview_comments = findViewById(R.id.rview_comments);
        rview_questions = findViewById(R.id.rview_questions);
        rview_suggestions = findViewById(R.id.rview_suggestions);

        manager = new LinearLayoutManager(ViewProfile_Activity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_friends.setLayoutManager(manager);
        rview_friends.setVisibility(View.VISIBLE);
        rview_posts.setVisibility(View.GONE);
        rview_comments.setVisibility(View.GONE);
        rview_questions.setVisibility(View.GONE);
        rview_suggestions.setVisibility(View.GONE);


        ll_friends.setBackgroundResource(R.drawable.red_corner_bg);
        ll_comment.setBackgroundColor(Color.WHITE);
        ll_post.setBackgroundColor(Color.WHITE);
        ll_question.setBackgroundColor(Color.WHITE);
        ll_suggestions.setBackgroundColor(Color.WHITE);
        tv_friend.setTextColor(Color.WHITE);
        tv_post.setTextColor(Color.BLACK);
        tv_questions.setTextColor(Color.BLACK);
        tv_comments.setTextColor(Color.BLACK);
        tv_suggestions.setTextColor(Color.BLACK);
        // im_friends.setBackgroundColor(Color.WHITE);


        im_friends.setBackgroundResource(R.drawable.ic_category_white);
        im_post.setBackgroundResource(R.drawable.ic_category_grey);
        im_questions.setBackgroundResource(R.drawable.ic_category_grey);
        ic_suggestions.setBackgroundResource(R.drawable.ic_category_grey);
        im_comments.setColorFilter(im_comments.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.user_profile(ViewProfile_Activity.this, this, requestBody);

        iv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ViewProfile_Activity.this, SettingActivity.class);
//                intent.putExtra("notification_status", userProfilePojo.getResponse().getNotifications());
//                intent.putExtra("ping_notification_status", userProfilePojo.getResponse().getPing_notifications());
//                startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putString("notification_status", userProfilePojo.getResponse().getNotifications());
                bundle.putString("ping_notification_status", userProfilePojo.getResponse().getPing_notifications());
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SETTINGS_SCREEN, bundle);
                finish();
            }
        });

        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewProfile_Activity.this, ProfileEdit.class);
                startActivity(intent);
            }
        });

        tv_coverpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which = 1;
                permissions();
            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which = 2;
                permissions();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_friends:
                rview_friends.setVisibility(View.VISIBLE);
                rview_posts.setVisibility(View.GONE);
                rview_comments.setVisibility(View.GONE);
                rview_questions.setVisibility(View.GONE);
                rview_suggestions.setVisibility(View.GONE);

                ll_friends.setBackgroundResource(R.drawable.red_corner_bg);
                ll_comment.setBackgroundColor(Color.WHITE);
                ll_post.setBackgroundColor(Color.WHITE);
                ll_question.setBackgroundColor(Color.WHITE);
                ll_suggestions.setBackgroundColor(Color.WHITE);
                tv_friend.setTextColor(Color.WHITE);
                tv_post.setTextColor(Color.BLACK);
                tv_questions.setTextColor(Color.BLACK);
                tv_comments.setTextColor(Color.BLACK);
                tv_suggestions.setTextColor(Color.BLACK);
                // im_friends.setBackgroundColor(Color.WHITE);


                im_friends.setBackgroundResource(R.drawable.ic_category_white);
                im_post.setBackgroundResource(R.drawable.ic_category_grey);
                im_questions.setBackgroundResource(R.drawable.ic_category_grey);
                ic_suggestions.setBackgroundResource(R.drawable.ic_category_grey);
                im_comments.setColorFilter(im_comments.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);


//                im_friends.setColorFilter(im_friends.getContext().getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//                im_post.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_questions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_comments.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                ic_suggestions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);


                //  im_post.setBackgroundColor(Color.BLACK);
                // im_questions.setBackgroundColor(Color.BLACK);
                //  im_comments.setBackgroundColor(Color.BLACK);


                manager = new LinearLayoutManager(ViewProfile_Activity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_friends.setLayoutManager(manager);

                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                requestBody.put("user_id", user_id);
                presenter.getFriends(ViewProfile_Activity.this, this, requestBody);


                break;
            case R.id.ll_post:
                rview_friends.setVisibility(View.GONE);
                rview_posts.setVisibility(View.VISIBLE);
                rview_comments.setVisibility(View.GONE);
                rview_questions.setVisibility(View.GONE);
                rview_suggestions.setVisibility(View.GONE);


                ll_post.setBackgroundResource(R.drawable.red_corner_bg);
                ll_question.setBackgroundColor(Color.WHITE);
                ll_friends.setBackgroundColor(Color.WHITE);
                ll_comment.setBackgroundColor(Color.WHITE);
                ll_suggestions.setBackgroundColor(Color.WHITE);
                tv_post.setTextColor(Color.WHITE);
                tv_questions.setTextColor(Color.BLACK);
                tv_comments.setTextColor(Color.BLACK);
                tv_friend.setTextColor(Color.BLACK);
                tv_suggestions.setTextColor(Color.BLACK);

//                im_post.setColorFilter(im_friends.getContext().getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//                im_friends.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_questions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_comments.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                ic_suggestions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);


                im_friends.setBackgroundResource(R.drawable.ic_category_grey);
                im_post.setBackgroundResource(R.drawable.ic_category_white);
                im_questions.setBackgroundResource(R.drawable.ic_category_grey);
                ic_suggestions.setBackgroundResource(R.drawable.ic_category_grey);
                im_comments.setColorFilter(im_comments.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);


                manager = new LinearLayoutManager(ViewProfile_Activity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_posts.setLayoutManager(manager);



                /*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "user_id":"1",
    "count":"0"
}
*/

                Map<String, String> requestBody_post = new HashMap<>();
                requestBody_post.put("token", token);
                requestBody_post.put("user_id", user_id);
                requestBody_post.put("count", "0");
                presenter.myActivityPosts(ViewProfile_Activity.this, this, requestBody_post);

                break;
            case R.id.ll_comment:
                rview_friends.setVisibility(View.GONE);
                rview_posts.setVisibility(View.GONE);
                rview_comments.setVisibility(View.VISIBLE);
                rview_questions.setVisibility(View.GONE);
                rview_suggestions.setVisibility(View.GONE);


                ll_comment.setBackgroundResource(R.drawable.red_corner_bg);
                ll_question.setBackgroundColor(Color.WHITE);
                ll_friends.setBackgroundColor(Color.WHITE);
                ll_post.setBackgroundColor(Color.WHITE);
                ll_suggestions.setBackgroundColor(Color.WHITE);

                tv_post.setTextColor(Color.BLACK);
                tv_questions.setTextColor(Color.BLACK);
                tv_comments.setTextColor(Color.WHITE);
                tv_friend.setTextColor(Color.BLACK);
                tv_suggestions.setTextColor(Color.BLACK);

//                im_comments.setColorFilter(im_friends.getContext().getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//                im_friends.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_questions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_post.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                ic_suggestions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);

                im_friends.setBackgroundResource(R.drawable.ic_category_grey);
                im_post.setBackgroundResource(R.drawable.ic_category_grey);
                im_questions.setBackgroundResource(R.drawable.ic_category_grey);
                ic_suggestions.setBackgroundResource(R.drawable.ic_category_grey);
                im_comments.setColorFilter(im_comments.getContext().getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

                manager = new LinearLayoutManager(ViewProfile_Activity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_comments.setLayoutManager(manager);


                /*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "user_id":"1",
    "count":"0"
}
*/

                Map<String, String> requestBody_discussions = new HashMap<>();
                requestBody_discussions.put("token", token);
                requestBody_discussions.put("user_id", user_id);
                requestBody_discussions.put("count", "0");
                presenter.myPostedDiscussions(ViewProfile_Activity.this, this, requestBody_discussions);

                break;
            case R.id.ll_question:
                rview_friends.setVisibility(View.GONE);
                rview_posts.setVisibility(View.GONE);
                rview_comments.setVisibility(View.GONE);
                rview_questions.setVisibility(View.VISIBLE);
                rview_suggestions.setVisibility(View.GONE);

                manager = new LinearLayoutManager(ViewProfile_Activity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_questions.setLayoutManager(manager);

                ll_question.setBackgroundResource(R.drawable.red_corner_bg);
                ll_post.setBackgroundColor(Color.WHITE);
                ll_friends.setBackgroundColor(Color.WHITE);
                ll_comment.setBackgroundColor(Color.WHITE);
                ll_suggestions.setBackgroundColor(Color.WHITE);

                tv_questions.setTextColor(Color.WHITE);
                tv_post.setTextColor(Color.BLACK);
                tv_comments.setTextColor(Color.BLACK);
                tv_friend.setTextColor(Color.BLACK);
                tv_suggestions.setTextColor(Color.BLACK);

                /*im_questions.setBackgroundColor(Color.WHITE);
                im_post.setBackgroundColor(Color.BLACK);
                im_friends.setBackgroundColor(Color.BLACK);
                im_comments.setBackgroundColor(Color.BLACK);*/
//                im_questions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//                im_friends.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_comments.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_post.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                ic_suggestions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);

                im_friends.setBackgroundResource(R.drawable.ic_category_grey);
                im_post.setBackgroundResource(R.drawable.ic_category_grey);
                im_questions.setBackgroundResource(R.drawable.ic_category_white);
                ic_suggestions.setBackgroundResource(R.drawable.ic_category_grey);
                im_comments.setColorFilter(im_comments.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);


                /*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "user_id":"1",
    "count":"0"
}
*/

                Map<String, String> requestBody_quiz = new HashMap<>();
                requestBody_quiz.put("token", token);
                requestBody_quiz.put("user_id", user_id);
                requestBody_quiz.put("count", "0");
                presenter.myQuizzes(ViewProfile_Activity.this, this, requestBody_quiz);

                break;
            case R.id.ll_suggestions:
                rview_friends.setVisibility(View.GONE);
                rview_posts.setVisibility(View.GONE);
                rview_comments.setVisibility(View.GONE);
                rview_questions.setVisibility(View.GONE);
                rview_suggestions.setVisibility(View.VISIBLE);

                manager = new LinearLayoutManager(ViewProfile_Activity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_suggestions.setLayoutManager(manager);


                ll_suggestions.setBackgroundResource(R.drawable.red_corner_bg);
                ll_post.setBackgroundColor(Color.WHITE);
                ll_friends.setBackgroundColor(Color.WHITE);
                ll_comment.setBackgroundColor(Color.WHITE);
                ll_question.setBackgroundColor(Color.WHITE);

                tv_questions.setTextColor(Color.BLACK);
                tv_post.setTextColor(Color.BLACK);
                tv_comments.setTextColor(Color.BLACK);
                tv_friend.setTextColor(Color.BLACK);
                tv_suggestions.setTextColor(Color.WHITE);

                /*im_questions.setBackgroundColor(Color.WHITE);
                im_post.setBackgroundColor(Color.BLACK);
                im_friends.setBackgroundColor(Color.BLACK);
                im_comments.setBackgroundColor(Color.BLACK);*/
//                //  im_questions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                ic_suggestions.setBackgroundResource(R.drawable.ic_category_white);
//                im_friends.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_comments.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_post.setColorFilter(im_friends.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//                im_questions.setColorFilter(im_friends.getContext().getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


                im_friends.setBackgroundResource(R.drawable.ic_category_grey);
                im_post.setBackgroundResource(R.drawable.ic_category_grey);
                im_questions.setBackgroundResource(R.drawable.ic_category_grey);
                ic_suggestions.setBackgroundResource(R.drawable.ic_category_white);
                im_comments.setColorFilter(im_comments.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);


                /*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "user_id":"1",
    "count":"0"
}
*/

                Map<String, String> requestBody_suggestions = new HashMap<>();
                requestBody_suggestions.put("token", token);
                requestBody_suggestions.put("user_id", user_id);
                requestBody_suggestions.put("count", "0");
                presenter.myPostedSuggestions(ViewProfile_Activity.this, this, requestBody_suggestions);

                break;
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString,
                                  @Nullable Object object) {
        try {
            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.UPDATE_COVER_PIC == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    JSONObject jsonObjectresponse = jsonObject.getJSONObject("response");
                    String cover_pic = jsonObjectresponse.getString("cover_pic");

                    Picasso.with(ViewProfile_Activity.this)
                            .load(NetworkConstants.URL.Imagepath_URL + cover_pic)
                            .error(R.drawable.no_image)
                            .into(img_cover);
                    Util.getInstance().cusToast(context, message);
                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.UPDATE_NOTIFICATION_STATUS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);
                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.UPDATE_PROFILE_PIC == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    JSONObject jsonObjectresponse = jsonObject.getJSONObject("response");
                    String profile_pic = jsonObjectresponse.getString("profile_pic");

                    Picasso.with(ViewProfile_Activity.this)
                            .load(NetworkConstants.URL.Imagepath_URL + profile_pic)
                            .error(R.drawable.no_image)
                            .into(profile_image);

                    Util.getInstance().cusToast(context, message);
                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.MY_FRIENDS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    friendsPojo = new Gson().fromJson(responseString, FriendsPojo.class);
                    myFriendsAdapter = new MyFriendsAdapter(ViewProfile_Activity.this, friendsPojo);
                    rview_friends.setAdapter(myFriendsAdapter);
//
                }
            } else if (NetworkConstants.RequestCode.USER_PROFILE == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {

                    userProfilePojo = new Gson().fromJson(responseString, UserProfilePojo.class);

                    user_pic = userProfilePojo.getResponse().getProfile_pic();
                    Picasso.with(ViewProfile_Activity.this)
                            .load(NetworkConstants.URL.Imagepath_URL + userProfilePojo.getResponse().getProfile_pic())
                            .error(R.drawable.no_image)
                            .into(profile_image);

                    Picasso.with(ViewProfile_Activity.this)
                            .load(NetworkConstants.URL.Imagepath_URL + userProfilePojo.getResponse().getCover_pic())
                            .error(R.drawable.no_image)
                            .into(img_cover);


                    tv_user_name.setText(userProfilePojo.getResponse().getName());
                    tv_user_univarsity.setText(userProfilePojo.getResponse().getUniversity());
                    tv_bio.setText(userProfilePojo.getResponse().getBio());

                    tv_course_year.setText(userProfilePojo.getResponse().getStream() + " ( " + userProfilePojo.getResponse().getYears() + " ) Years");
                    tv_subjects.setText(userProfilePojo.getResponse().getStream());
                    userSession.StoreUserDetails(userProfilePojo.getResponse().getId(), userProfilePojo.getResponse().getName(), userProfilePojo.getResponse().getMobile(), "", "", userProfilePojo.getResponse().getProfile_pic(), "", userProfilePojo.getResponse().getUniversity(), token, userProfilePojo.getResponse().getCountry(), userProfilePojo.getResponse().getCity(), userProfilePojo.getResponse().getBio(), userProfilePojo.getResponse().getEducation_id(), userProfilePojo.getResponse().getStream_id(), userProfilePojo.getResponse().getYears(), userProfilePojo.getResponse().getSubjects(), userProfilePojo.getResponse().getSubject_names(), userProfilePojo.getResponse().getStream());


                    callfriendsWs();
                }
            } else if (NetworkConstants.RequestCode.MY_ACTIVITY_POSTS == requestId) {
                myActivityPostsPojo = new Gson().fromJson(responseString, MyActivityPostsPojo.class);
                myActivityPostsAdapter = new MyActivityPostsAdapter(ViewProfile_Activity.this, myActivityPostsPojo);
                rview_posts.setAdapter(myActivityPostsAdapter);
            } else if (NetworkConstants.RequestCode.MY_POSTED_DISCUSSIONS == requestId) {
                myPostedDiscussionsPojo = new Gson().fromJson(responseString, MyPostedDiscussionsPojo.class);
                myPostedDiscussionsAdapter = new MyPostedDiscussionsAdapter(ViewProfile_Activity.this, myPostedDiscussionsPojo, user_pic);
                rview_comments.setAdapter(myPostedDiscussionsAdapter);
            } else if (NetworkConstants.RequestCode.MY_POSTED_SUGGESTIONS == requestId) {
                myPostedSuggestionsPojo = new Gson().fromJson(responseString, MyPostedSuggestionsPojo.class);
                mySuggestionsAdapter = new MySuggestionsAdapter(ViewProfile_Activity.this, myPostedSuggestionsPojo);
                rview_suggestions.setAdapter(mySuggestionsAdapter);
            } else if (NetworkConstants.RequestCode.MY_QUIZZES == requestId) {
                myQuizzesPojo = new Gson().fromJson(responseString, MyQuizzesPojo.class);
                myQuizzesAdapter = new MyQuizzesAdapter(ViewProfile_Activity.this, myQuizzesPojo);
                rview_questions.setAdapter(myQuizzesAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void callfriendsWs() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.getFriends(ViewProfile_Activity.this, this, requestBody);

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
    }
}
