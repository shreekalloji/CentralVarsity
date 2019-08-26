package com.iprismtech.studentvarsity.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;


/**
 * Created by DEVELOPER on 23-Mar-17.
 */

public class Util {

    private static Util utilObj;
    private Toast toast;
    private String OTP;
    public static String token = "";
    public JSONArray categoryResponse;
    public int countCategoryrequet = 0;
    private String datePickerValue;


    public Util() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }


    public int getCountCategoryrequet() {
        return countCategoryrequet;
    }

    public void setCountCategoryrequet(int countCategoryrequet) {
        this.countCategoryrequet = countCategoryrequet;
    }

    public JSONArray getCategoryResponse() {
        return categoryResponse;
    }

    public void setCategoryResponse(JSONArray categoryResponse) {
        this.categoryResponse = categoryResponse;
    }

    public String getDatePickerValue() {
        return datePickerValue;
    }

    public void setDatePickerValue(String datePickerValue) {
        this.datePickerValue = datePickerValue;
    }

    /**
     * Create a static method to get instance.
     */
    public static Util getInstance() {
        if (utilObj == null) {
            utilObj = new Util();
        }
        return utilObj;
    }

    /**
     * Here checking the response first if user not connected with internet then show the error dialog that please connect to internet.
     *
     * @param context
     * @param response
     * @return
     */
    public Boolean checkResponse(Context context, JSONObject response) {
        try {
            String status = response.optString("status");
            String message = response.optString("message");
            if (status.equalsIgnoreCase("true")) {
                return true;
            } else {
                if (!message.isEmpty()) {
                    cusToast(context, message);
                } else {
                    cusToast(context, "Unknown error occurred");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * This method is use to get the border of image withe white border.
     *
     * @param bitmap
     * @param borderWidth
     * @return
     */
    public Bitmap getCircularBitmapWithWhiteBorder(Bitmap bitmap,
                                                   int borderWidth) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }

        final int width = bitmap.getWidth() + borderWidth;
        final int height = bitmap.getHeight() + borderWidth;

        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        Canvas canvas = new Canvas(canvasBitmap);
        float radius = width > height ? ((float) height) / 2f : ((float) width) / 2f;
        canvas.drawCircle(width / 2, height / 2, radius, paint);
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawCircle(width / 2, height / 2, radius - borderWidth / 2, paint);
        return canvasBitmap;
    }


    /**
     * This method is use to load the image to show the progressbar.
     *
     * @param context
     * @param showProgressImageView
     */
   /* public void loadImage(Context context, ImageView showProgressImageView) {
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(showProgressImageView);
        Glide.with(context).load(R.raw.loader).into(imageViewTarget);
     *//*   Glide.with(context)
                .load(R.drawable.progress)
                .asGif()
                .into(showProgressImageView);*//*
    }*/

    /**
     * Show the toast when user need to going to offline while data not fount in application.
     *
     * @param context
     * @param message
     */
    public void cusToast(final Context context, final String message) {
        try {
            Handler UIHandler = new Handler(Looper.getMainLooper());
            UIHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (toast != null) {
                        toast.cancel();
                    }
                    toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Function is use to hide the keyboard by passing current screen view.
     *
     * @param v
     */
    public void HideKeyboard(View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * This method is use to get the json array from cursor.
     *
     * @param cursor
     * @return
     */

    public static JSONArray GetJSONArray(Cursor cursor) {
        JSONArray resultSet = new JSONArray();
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int totalColumn = cursor.getColumnCount();
                JSONObject rowObject = new JSONObject();
                for (int i = 0; i < totalColumn; i++) {
                    if (cursor.getColumnName(i) != null) {
                        try {
                            if (cursor.getString(i) != null) {
                                rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                            } else {
                                rowObject.put(cursor.getColumnName(i), "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                resultSet.put(rowObject);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public int ArrayIndex(String searchString, String[] domain) {
        for (int i = 0; i < domain.length; i++) {
            if (searchString.equals(domain[i])) {
                return i;
            }
        }
        return -1;
    }

    //To get the location name base on lat and long value from google map .
    public String getLocationName(Context context, double latitude, double longitude) {
        String cityName = "Area Not Found";
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(latitude, longitude, 1);
            Address adrs = addresses.get(0);
            if (adrs != null) {
                String area = adrs.getFeatureName();
                String city = adrs.getLocality();
                String state = adrs.getAdminArea();
                String country = adrs.getCountryName();
                String sublocality = adrs.getSubLocality();
                if (area == null) {
                    cityName = sublocality + " , " + city + " , " + state + " , " + country;
                } else if (sublocality == null) {
                    cityName = area + ", " + state + " , " + country;
                } else if (city == null) {
                    cityName = state + " , " + country;
                } else if (state == null) {
                    cityName = state + " , " + country;
                } else {
                    cityName = sublocality + " , " + area + "  ," + city + " , " + state + " , " + country;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityName;
    }

    /**
     * Created the long toast here.
     *
     * @param context
     * @param message
     */
    public void cusToastLong(final Context context, final String message) {
        try {
            Handler UIHandler = new Handler(Looper.getMainLooper());
            UIHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (toast != null) {
                        toast.cancel();
                    }
                    toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String formatAmount(Integer price) {
        try {
            String result = String.valueOf(new DecimalFormat("##.##").format(price));
            Double valueDouble = Double.parseDouble(result);
            int splitter = valueDouble.toString().length();
            DecimalFormat format;
            if (splitter > 7) {
                format = new DecimalFormat("#0,00,000.00");
            } else if (splitter > 5) {
                format = new DecimalFormat("#0,000.00");
            } else {
                format = new DecimalFormat("#0.00");
            }
            return format.format(valueDouble);
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * This method is use to check mail id valid or not?
     *
     * @param email
     * @return
     */
    public boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


    public void setRoundImage(Bitmap bitmap, ImageView imageView) {
        try {
            bitmap = getCircularBitmap(bitmap);
            bitmap = addBorderToCircularBitmap(bitmap, 3, Color.WHITE);
            bitmap = addShadowToCircularBitmap(bitmap, 2, Color.GRAY);
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getCircularBitmap(Bitmap srcBitmap) {
        int squareBitmapWidth = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());
        Bitmap dstBitmap = Bitmap.createBitmap(
                squareBitmapWidth, // Width
                squareBitmapWidth, // Height
                Bitmap.Config.ARGB_8888 // Config
        );
        // Initialize a new Canvas to draw circular bitmap
        Canvas canvas = new Canvas(dstBitmap);
        // Initialize a new Paint instance
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        // Initialize a new Rect instance
        Rect rect = new Rect(0, 0, squareBitmapWidth, squareBitmapWidth);
        // Initialize a new RectF instance
        RectF rectF = new RectF(rect);
        // Draw an oval shape on Canvas
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // Calculate the left and top of copied bitmap
        float left = (squareBitmapWidth - srcBitmap.getWidth()) / 2;
        float top = (squareBitmapWidth - srcBitmap.getHeight()) / 2;
        // Make a rounded image by copying at the exact center position of source image
        canvas.drawBitmap(srcBitmap, left, top, paint);
        // Free the native object associated with this bitmap.
        //srcBitmap.recycle();
        // Return the circular bitmap
        return dstBitmap;
    }

    public Bitmap addBorderToCircularBitmap(Bitmap srcBitmap, int borderWidth, int borderColor) {
        // Calculate the circular bitmap width with border
        int dstBitmapWidth = srcBitmap.getWidth() + borderWidth * 2;
        // Initialize a new Bitmap to make it bordered circular bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888);
        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(dstBitmap);
        // Draw source bitmap to canvas
        canvas.drawBitmap(srcBitmap, borderWidth, borderWidth, null);
        // Initialize a new Paint instance to draw border
        Paint paint = new Paint();
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        paint.setAntiAlias(true);
        // Draw the circular border around circular bitmap
        canvas.drawCircle(
                canvas.getWidth() / 2, // cx
                canvas.getWidth() / 2, // cy
                canvas.getWidth() / 2 - borderWidth / 2, // Radius
                paint // Paint
        );
        // Free the native object associated with this bitmap.
        //srcBitmap.recycle();
        // Return the bordered circular bitmap
        return dstBitmap;
    }

    public Bitmap addShadowToCircularBitmap(Bitmap srcBitmap, int shadowWidth, int shadowColor) {
        // Calculate the circular bitmap width with shadow
        int dstBitmapWidth = srcBitmap.getWidth() + shadowWidth * 2;
        Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888);
        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(dstBitmap);
        canvas.drawBitmap(srcBitmap, shadowWidth, shadowWidth, null);
        // Paint to draw circular bitmap shadow
        Paint paint = new Paint();
        paint.setColor(shadowColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(shadowWidth);
        paint.setAntiAlias(true);
        // Draw the shadow around circular bitmap
        canvas.drawCircle(
                dstBitmapWidth / 2, // cx
                dstBitmapWidth / 2, // cy
                dstBitmapWidth / 2 - shadowWidth / 2, // Radius
                paint // Paint
        );
        //srcBitmap.recycle();
        // Return the circular bitmap with shadow
        return dstBitmap;
    }

    // Calcualte the sum of single item here.
    public Integer getSingleItemPrice(Integer quantity, Integer price) {
        Integer total = 0;
        total = quantity * price;
        return total;
    }

    //To check weather mobile number is valid or not.
    public boolean isValidPhoneNumber(CharSequence target) {
        if (target.length() != 10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }

    //To check weather email address valid or not.
    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    } //


    public void hideProgressDialog(ProgressDialog pDialog) {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public ProgressDialog showProgressDialog(Context context) {
        ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        return pDialog;
    }


    //This method is use to get the data from the server and post the data on the server.
    // This method is use to upload the file on server.
  /*  public FTPClient GetFTPClient(String Directory) {
        try {
            FTPClient client = new FTPClient();
            client.connect(AppWebDefine.FTP_HOST, 21);
            client.login(AppWebDefine.FTP_USER, AppWebDefine.FTP_PASS);
            client.setType(FTPClient.TYPE_BINARY);
            client.changeDirectory(Directory);
            client.setPassive(true);
            client.noop();
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

   /* public void DeleteSDFolder(String FolderName) {
        try {
            String SDPath = Environment.getExternalStorageDirectory().toString();
            File dir = new File(SDPath, FolderName);
            if (dir.exists() && dir.isDirectory()) {
                FileUtils.deleteDirectory(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void DeleteInternalFolder(Context context) {
        try {
            String appPath = context.getFilesDir().getAbsolutePath();
            File dir = new File(appPath, "Temp");
            if (dir.exists() && dir.isDirectory()) {
                FileUtils.deleteDirectory(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    public File CreateInternalFile(Context context, String FileName) {
        String appPath = context.getFilesDir().getAbsolutePath();
        File dir = new File(appPath, "Temp");
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        File Destination = new File(dir, FileName);
        if (Destination.exists()) {
            Destination.delete();
        }
        try {
            Destination.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Destination;
    }


    // create the directory in sd cards.
    public File CreateSDFile(String FileName, String FolderName) {
        File dir = new File(Environment.getExternalStorageDirectory(), FolderName);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        File Destination = new File(dir, FileName);
        if (Destination.exists()) {
            Destination.delete();
        }
        try {
            Destination.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Destination;
    }


    public String getRealPathFromURI(Context context, Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is DropBox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public String GetJson(ArrayList<String> ValuesList) {
        //String json = "{\"json\":[{";
        String json = "{";
        for (int i = 0; i < ValuesList.size(); i++) {
            String[] Split = ValuesList.get(i).split(" ☢");
            if (Split.length == 1) {
                Split = new String[]{Split[0], " "};
            }
            json += '"' + Split[0] + '"' + ':' + '"' + Split[1] + '"';
            if (i + 1 != ValuesList.size()) {
                json += ",";
            }
        }
        //json += "}]}";
        json += "}";
        return json;
    }

    public static File CreateThumbImage(File Source, File Destination, int quality) {
        try {
            Bitmap scaledBitmap = null;
            BitmapFactory.Options options = new BitmapFactory.Options();
//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
            options.inJustDecodeBounds = true;
            Bitmap bmp = BitmapFactory.decodeFile(Source.getPath(), options);
            int actualHeight = options.outHeight;
            int actualWidth = options.outWidth;
//      max Height and width values of the compressed image is taken as 816x612
            float maxHeight = 816.0f;
            float maxWidth = 612.0f;
            float imgRatio = actualWidth / actualHeight;
            float maxRatio = maxWidth / maxHeight;
//      width and height values are set maintaining the aspect ratio of the image
            if (actualHeight > maxHeight || actualWidth > maxWidth) {
                if (imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight;
                    actualWidth = (int) (imgRatio * actualWidth);
                    actualHeight = (int) maxHeight;
                } else if (imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth;
                    actualHeight = (int) (imgRatio * actualHeight);
                    actualWidth = (int) maxWidth;
                } else {
                    actualHeight = (int) maxHeight;
                    actualWidth = (int) maxWidth;

                }
            }
//      setting inSampleSize value allows to load a scaled down version of the original image
            options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);
//      inJustDecodeBounds set to false to load the actual bitmap
            options.inJustDecodeBounds = false;
//      this options allow android to claim the bitmap memory if it runs low on memory
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inTempStorage = new byte[16 * 1024];
            try {
                //          load the bitmap from its path
                bmp = BitmapFactory.decodeFile(Source.getPath(), options);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();
            }
            try {
                scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();
            }
            float ratioX = actualWidth / (float) options.outWidth;
            float ratioY = actualHeight / (float) options.outHeight;
            float middleX = actualWidth / 2.0f;
            float middleY = actualHeight / 2.0f;
            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);
            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
//      check the rotation of the image and display it properly
            ExifInterface exif;
            try {
                exif = new ExifInterface(Source.getPath());
                int orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, 0);
                Matrix matrix = new Matrix();
                if (orientation == 6) {
                    matrix.postRotate(90);
                } else if (orientation == 3) {
                    matrix.postRotate(180);
                } else if (orientation == 8) {
                    matrix.postRotate(270);
                }
                if (scaledBitmap != null) {
                    scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(),
                            scaledBitmap.getHeight(), matrix, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (scaledBitmap != null) {
                try {
                    FileOutputStream outStream = new FileOutputStream(Destination);
                    scaledBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outStream);
                    outStream.flush();
                    outStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Destination;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }
        return inSampleSize;
    }

    public String formatAmount(Double price) {
        try {
            String result = String.valueOf(new DecimalFormat("##.##").format(price));
            Double valueDouble = Double.parseDouble(result);
            int splitter = valueDouble.toString().length();
            DecimalFormat format;
            if (splitter > 7) {
                format = new DecimalFormat("#0,00,000.00");
            } else if (splitter > 5) {
                format = new DecimalFormat("#0,000.00");
            } else {
                format = new DecimalFormat("#0.00");
            }
            return format.format(valueDouble);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * This method is use to hide the keyboard.
     */
    public void hideSoftKeyboard(Activity context) {
        if (context.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);

        }
    }

    //This method is use to eccode the bitmap into base64 string format.
    public String convertInBase64(Bitmap imageBitmap) {
        String encodedString = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedString;
    }

   /* public boolean checkPlayServices(Context context) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            try {
                if (apiAvailability.isUserResolvableError(resultCode)) {
                    apiAvailability.getErrorDialog((Activity) context, resultCode, UiAppConstant.PLAY_SERVICES_RESOLUTION_REQUEST).show();
                } else {
                    //HelperObj.cusToast(context,"This device is not support play services");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }*/

    public void DeleteInternalFolder(Context context) {
        try {
            String appPath = context.getFilesDir().getAbsolutePath();
            File dir = new File(appPath, "Temp");
            if (dir.exists() && dir.isDirectory()) {
                //  FileUtils.deleteDirectory(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setGlideImage(Context context, final ImageView imageView, String Url, final Boolean isRound) {
        try {
            Glide.with(context)
                    .load(NetworkConstants.URL.Imagepath_URL + Url)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                            if (resource != null) {
                                if (isRound) {
                                    setRoundImage(resource, imageView);
                                } else {
                                    imageView.setImageBitmap(resource);
                                }
                            }
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            imageView.setImageResource(R.mipmap.ic_launcher);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteSDFolder(String FolderName) {
        try {
            String SDPath = Environment.getExternalStorageDirectory().toString();
            File dir = new File(SDPath, FolderName);
            if (dir.exists() && dir.isDirectory()) {
                //FileUtils.deleteDirectory(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openDialog(String message, String internet_status, boolean b, Context context) {

        final Dialog dialog1 = new Dialog(context, R.style.df_dialog);
        dialog1.setContentView(R.layout.dialog_no_internet);
        dialog1.setCancelable(true);
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.findViewById(R.id.btnSpinAndWinRedeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }
}




























