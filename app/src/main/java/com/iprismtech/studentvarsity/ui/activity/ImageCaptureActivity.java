package com.iprismtech.studentvarsity.ui.activity;

import android.Manifest;
import android.content.ContentValues;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.mvp.contract.activity.CaptureImageContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.CaptureImageImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageCaptureActivity extends BaseAbstractActivity<CaptureImageImpl> implements CaptureImageContract.IView, APIResponseCallback {
    private ImageView iv_capture, iv_back;
    private TextView tv_comment_submit;
    private EditText et_commnet;
    private ContentValues contentValue;
    private int GALLERY_DOC = 101, CAMERA_DOC = 102;
    private Uri imageUri;
    private Bitmap profile_bit, bp;
    private String base64profile=" ", sending_through, section_id;
    private String token, user_id, user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_capture_img, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        Intent intent = getIntent();
        sending_through = intent.getStringExtra("sending_through");
        section_id = intent.getStringExtra("section_id");

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        user_name = userSession.getUserDetails().get("name");
        user_university = userSession.getUserDetails().get("university");
        user_bio = userSession.getUserDetails().get("bio");
        user_profile_pic = userSession.getUserDetails().get("profileImg");
        stream = userSession.getUserDetails().get("stream_id");
        years = userSession.getUserDetails().get("years");
        user_subjects = userSession.getUserDetails().get("subject_names");

        iv_capture = findViewById(R.id.iv_capture);
        iv_back = findViewById(R.id.iv_back);
        et_commnet = findViewById(R.id.et_commnet);
        tv_comment_submit = findViewById(R.id.tv_comment_submit);
        showPictureDialog("");
        tv_comment_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFinish();

            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void callFinish() {
//        Intent returnIntent = new Intent();
////        returnIntent.putExtra("image", base64profile);
////        returnIntent.putExtra("comment", et_commnet.getText().toString());
//
//        returnIntent.putExtra("image", base64profile);
//        returnIntent.putExtra("comment", "commnet");
//        setResult(Activity.RESULT_OK, returnIntent);
//        finish();


        if (et_commnet.getText().toString().isEmpty()) {
            Toast.makeText(context, "Write comment", Toast.LENGTH_SHORT).show();
        } else if (sending_through.equalsIgnoreCase("notes")) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("sections_id", section_id);
            requestBody.put("image", base64profile);
            requestBody.put("type", "notes");
            requestBody.put("comment", et_commnet.getText().toString());
            presenter.submit_comment(ImageCaptureActivity.this, this, requestBody);
        } else if (sending_through.equalsIgnoreCase("faqs")) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("sections_id", section_id);
            requestBody.put("image", base64profile);
            requestBody.put("type", "faqs");
            requestBody.put("comment", et_commnet.getText().toString());
            presenter.submit_comment(ImageCaptureActivity.this, this, requestBody);
        } else if (sending_through.equalsIgnoreCase("videos")) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("sections_id", section_id);
            requestBody.put("image", base64profile);
            requestBody.put("type", "videos");
            requestBody.put("comment", et_commnet.getText().toString());
            presenter.submit_comment(ImageCaptureActivity.this, this, requestBody);
        } else if (sending_through.equalsIgnoreCase("mcqs")) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("sections_id", section_id);
            requestBody.put("image", base64profile);
            requestBody.put("type", "mcqs");
            requestBody.put("comment", et_commnet.getText().toString());
            presenter.submit_comment(ImageCaptureActivity.this, this, requestBody);
        } else if (sending_through.equalsIgnoreCase("discuss")) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("sections_id", section_id);
            requestBody.put("image", base64profile);
            requestBody.put("type", "discuss");
            requestBody.put("comment", et_commnet.getText().toString());
            presenter.submit_comment(ImageCaptureActivity.this, this, requestBody);
        } else if (sending_through.equalsIgnoreCase("activity")) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("sections_id", section_id);
            requestBody.put("image", base64profile);
            requestBody.put("type", "activity");
            requestBody.put("comment", et_commnet.getText().toString());
            presenter.submit_comment(ImageCaptureActivity.this, this, requestBody);
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
                    && ContextCompat.checkSelfPermission(ImageCaptureActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(ImageCaptureActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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
                    bp = decodeUri(choosenImage, 300);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bp.compress(Bitmap.CompressFormat.PNG, 70, bytes);
                    byte[] byte_arr = bytes.toByteArray();
                    base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                    iv_capture.setImageBitmap(bp);
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

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                  Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);

            } else if (NetworkConstants.RequestCode.SUBMIT_COMMENT == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    Toast.makeText(this, "Comment Submitted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void setPresenter() {
        presenter = new CaptureImageImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

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
                iv_capture.setImageBitmap(bp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
