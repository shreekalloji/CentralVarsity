package com.iprismtech.studentvarsity.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.ProfileActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ProfileActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;
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

public class ProfileActivity extends BaseAbstractActivity<ProfileActivitylmpl> implements ProfileActivityContract.IView, View.OnClickListener, APIResponseCallback {
    ImageView imgNext, im_back, iv_banner;
    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, user_id;
    Bundle bundle;
    String id, stream_id = "", years = "", subjects = "", name = "", university = "", edt_City = "", edt_Country = "";
    TextView tv_name, tv_addprof, tv_addbg;
    CircleImageView profile_image;
    EditText et_bio;
    public static final int MY_REQUEST_CODE = 2;
    final int CAMERA_CAPTURE = 1;
    final int PICK_IMAGE = 2;
    String PickedImgPath = "";
    String encodeStringbg = "";
    String encodeStringprof = "";
    int which;
    private static final int PERMISSION_REQUEST_CONTACT = 700;
    private String reg_university;
    private String itemimageurl;

    public void permissions() {

        if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_REQUEST_CODE);
        } else if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_REQUEST_CODE);
        } else if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_REQUEST_CODE);
        } else if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.CAMERA},
                    MY_REQUEST_CODE);
        } else {
            selectImage();
        }
    }

    public void askForContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this,
                        Manifest.permission.READ_CONTACTS)) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProfileActivity.this);
                    builder.setTitle(R.string.contacts_access_needed);
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage(R.string.please_confirm_contacts_access);//TODO put real question
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {Manifest.permission.READ_CONTACTS}
                                    , PERMISSION_REQUEST_CONTACT);
                        }
                    });
                    builder.show();
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(ProfileActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            PERMISSION_REQUEST_CONTACT);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {


                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CONNECTFRIENDS_SCREEN);
                finishAffinity();
//                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
//                finishAffinity();
            }
        } else {
            // bundle.putString("university", reg_university);
            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CONNECTFRIENDS_SCREEN);
            finishAffinity();
//            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
//            finishAffinity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CONNECTFRIENDS_SCREEN);
                    finishAffinity();
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
//                    finishAffinity();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this, R.string.no_permission_for_contacts, Toast.LENGTH_SHORT).show();
                    // ToastMaster.showMessage(getActivity(),"No permission for contacts");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void selectImage() {

        final CharSequence[] options = {getResources().getString(R.string.takephoto), getResources().getString(R.string.choosefrmgallery), getResources().getString(R.string.cancel)};

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
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
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK_IMAGE);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_CAPTURE);

        //  showImagePickerOptions();
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

//                if (which == 1) {
//
//                    CropImage.activity(picUri)
//                            .setMinCropResultSize(1200, 600)
//                            .setMaxCropResultSize(1200, 600)
//                            .start(this);
//                } else {
//                    CropImage.activity(picUri)
//                            .setMinCropResultSize(1000, 1000)
//                            .setMaxCropResultSize(1000, 1000)
//                            .start(this);
//                }


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

//                String[] filePath = {MediaStore.Images.Media.DATA};
//                Cursor c = ProfileActivity.this.getContentResolver().query(picUri, filePath, null, null, null);
//                c.moveToFirst();
//                int columnIndex = c.getColumnIndex(filePath[0]);
//                PickedImgPath = GalleryUriToPath.getPath(ProfileActivity.this, picUri);
//                File pickedfile = new File(PickedImgPath);
//                try {
//                    if (which == 1) {
//                        encodeStringbg = encodeFileToBase64Binary(pickedfile);
//                    } else {
//                        encodeStringprof = encodeFileToBase64Binary(pickedfile);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                try {
                    Bitmap bm = BitmapFactory.decodeStream(
                            ProfileActivity.this.getContentResolver().openInputStream(picUri));
                    if (which == 1) {
                        tv_addbg.setVisibility(View.GONE);
                        //iv_banner.setImageBitmap(bm);
                    } else {
                        tv_addprof.setVisibility(View.GONE);
                        profile_image.setVisibility(View.VISIBLE);
                        //  profile_image.setImageBitmap(bm);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            Log.e("onActivityResult: ", "" + result.getUri());

//                String url123 = mCropImageUri.toString().substring(8);
            PickedImgPath = result.getUri().toString();
            System.out.println("dfmgvkl'" + PickedImgPath);
            if (which == 1) {
                iv_banner.setImageURI(result.getUri());
                Bitmap bitmap = ((BitmapDrawable) iv_banner.getDrawable()).getBitmap();
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

            if (which == 1) {
                FileOutputStream out = new FileOutputStream(file);
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                itemimageurl = file.getAbsolutePath();
                Log.e("file name", itemimageurl);
                encodeStringbg = encodeFileToBase64Binary(file);
                out.flush();
                out.close();
            } else {
                FileOutputStream out = new FileOutputStream(file);
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                itemimageurl = file.getAbsolutePath();
                Log.e("file name", itemimageurl);
                encodeStringprof = encodeFileToBase64Binary(file);
                out.flush();
                out.close();
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
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
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

            PickedImgPath = destination.getAbsolutePath();


            if (which == 1) {

                CropImage.activity(Uri.fromFile(new File(PickedImgPath)))
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(120, 50)
                        .start(this);
            } else {
                CropImage.activity(Uri.fromFile(new File(PickedImgPath)))
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(120, 120)
                        .start(this);
            }


//            if (which == 1) {
//
//                CropImage.activity(Uri.fromFile(new File(PickedImgPath)))
//                        .setMinCropResultSize(300, 150)
//                        .setMaxCropResultSize(300, 150)
//                        .start(this);
//            } else {
//                CropImage.activity(Uri.fromFile(new File(PickedImgPath)))
//                        .start(this);
//            }


            File pickedfile = new File(PickedImgPath);


//            try {
//                if (which == 1) {
//                    encodeStringbg = encodeFileToBase64Binary(pickedfile);
//                } else {
//                    encodeStringprof = encodeFileToBase64Binary(pickedfile);
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
            tv_addbg.setVisibility(View.GONE);
            iv_banner.setImageBitmap(thumbnail);
        } else {
            tv_addprof.setVisibility(View.GONE);
            profile_image.setVisibility(View.VISIBLE);
            profile_image.setImageBitmap(thumbnail);
        }

    }

    private static String encodeFileToBase64Binary(File file) throws Exception {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_profile);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_profile, null);
        return view;
    }

    public void setPresenter() {
        presenter = new ProfileActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(ProfileActivity.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        apiResponseCallback = this;

        bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            stream_id = bundle.getString("stream_id");
            years = bundle.getString("years");
            subjects = bundle.getString("subjects");
            name = bundle.getString("name");
            university = bundle.getString("university");
            edt_City = bundle.getString("edt_City");
            edt_Country = bundle.getString("edt_Country");
        }

        imgNext = findViewById(R.id.profile_imgNext);
        im_back = findViewById(R.id.im_back);
        tv_name = findViewById(R.id.tv_name);
        tv_addprof = findViewById(R.id.tv_addprof);
        tv_addbg = findViewById(R.id.tv_addbg);
        et_bio = findViewById(R.id.et_bio);
        iv_banner = findViewById(R.id.iv_banner);
        profile_image = findViewById(R.id.profile_image);

        if (!name.isEmpty()) {
            tv_name.setText("Hi , " + name);
        }

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                //startActivity(new Intent(ProfileActivity.this, FinalRegistrationActivity.class));
            }
        });


        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(ProfileActivity.this, ConnectFriendsActivity.class));

//                {
//                    "user_id":"1",
//                        "education_id":"1",
//                        "stream_id":"1",
//                        "years":"2,3",
//                        "subjects":"1,2,3",
//                        "name":"Jhon Doe",
//                        "university":"Geetham College & University",
//                        "country_id":"1",
//                        "city_id":"1",
//                        "profile_pic":"base 64",
//                        "bio":
//                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
//                }

                if (isUserCredentialsValid()) {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("user_id", user_id);
                    requestBody.put("education_id", id);
                    requestBody.put("stream_id", stream_id);
                    requestBody.put("years", years);
                    requestBody.put("subjects", subjects);
                    requestBody.put("name", name);
                    requestBody.put("university", university);
                    // requestBody.put("country_id", "1");
                    requestBody.put("cover_pic", encodeStringbg);
                    requestBody.put("country", edt_Country);
                    requestBody.put("city", edt_City);
                    requestBody.put("profile_pic", encodeStringprof);
                    requestBody.put("bio", et_bio.getText().toString().trim());
                    presenter.complete_registration_process(ProfileActivity.this, apiResponseCallback, requestBody);

                }
            }
        });

        tv_addbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which = 1;
                permissions();

            }
        });
        iv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which = 1;
                permissions();

            }
        });

        tv_addprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which = 2;
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

    private boolean isUserCredentialsValid() {
        if (et_bio.getText().toString().length() == 0) {
            et_bio.setError("please enter something about you");
            //todo: Add shake animation
            return false;
        }
        //todo: Set bg color for gender
        else {
            return true;
//            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN);
            //  Toast.makeText(getApplicationContext(), "user not registered with us!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box

                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.COMPLETE_REGISTRATION_PROCESS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    JSONObject response = jsonObject.optJSONObject("response");

                    Util.getInstance().cusToast(context, message);

                    String id = response.optString("id");
                    String name = response.optString("name");
                    reg_university = response.optString("university");
                    String country_id = response.optString("country_id");
                    String city_id = response.optString("city_id");
                    String mobile = response.optString("mobile");
                    String bio = response.optString("bio");
                    String profile_pic = response.optString("profile_pic");
                    String education_id = response.optString("education_id");
                    String stream_id = response.optString("stream_id");
                    String years = response.optString("years");
                    String subjects = response.optString("subjects");
                    String token = userSession.getUserDetails().get("token");

                    userSession.StoreUserDetails(id, name, mobile, "", "", profile_pic, "", university, token, country_id, city_id, bio, education_id, stream_id, years, subjects, "", "");

                    Variables.university = reg_university;

                    askForContactPermission();
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
//                    finishAffinity();

                } else {
                    Util.getInstance().cusToast(context, message);
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
