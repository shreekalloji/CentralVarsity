package com.iprismtech.studentvarsity.network.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.network.responcedos.CommonResponse;
import com.iprismtech.studentvarsity.network.volley.APIHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by prasad on 05/07/2017.
 */
public class Utils {


    private static final int NETWORK_ERROR_CODE = 5000;
    private static final int ERROR_CODE = 5001;

    /**
     * Check internet availability.
     *
     * @param context
     * @return
     */
    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();
        if (activeNetworkInfo != null) { // connected to the internet
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    /**
     * get the error body for network error
     *
     * @param context
     * @return
     */
    public static String getNetworkErrorBody(Context context) {
        CommonResponse commonResponse = new CommonResponse();
        JSONObject jsonFormat = commonResponse.getJSONFormat(NETWORK_ERROR_CODE, context.getString(R.string.no_internet_txt));
        return jsonFormat.toString();

    }

    /**
     * Construct the error body
     *
     * @param message
     * @return
     */
    public static String constructJSONBody(String message) {
        CommonResponse commonResponse = new CommonResponse();
        JSONObject jsonFormat = commonResponse.getJSONFormat(ERROR_CODE, message);
        return jsonFormat.toString();

    }

    /**
     * Construct json body with eror code and message
     * @param message
     * @param error_code
     * @return
     */
    public static String constructJSONBody(String message, int error_code) {
        CommonResponse commonResponse = new CommonResponse();
        JSONObject jsonFormat = commonResponse.getJSONFormat(error_code, message);
        return jsonFormat.toString();

    }

    /**
     * Show dialog if there is no internet connection.
     *  @param dialogTitle
     * @param dialogMessage
     * @param isOutsideCancalabel
     * @param iDialogCallback
     */
    public static void showErrorDialog(final Context context, final String dialogTitle, final String dialogMessage, final boolean isOutsideCancalabel, final APIHandler iDialogCallback, final int requestCode) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(dialogTitle);
                    builder.setMessage(dialogMessage);

                    builder.setPositiveButton(context.getString(R.string.ok_txt),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (iDialogCallback != null) {
                                        iDialogCallback.onPositiveButtonPress(requestCode);
                                    }
                                    // positive button logic
                                    dialog.dismiss();

                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.setCanceledOnTouchOutside(isOutsideCancalabel);
                    dialog.show();
                }
            });

        }
    }


    /**
     * Encode the string into base64
     *
     * @param text
     * @return
     */
    public static String encodeBase64(String text) {
        byte[] data = new byte[0];
        String encodedString = null;
        try {
            data = text.getBytes("UTF-8");
            encodedString = Base64.encodeToString(data, Base64.DEFAULT);
            Log.d(AppConstants.LOG_CAT, "encodeBase64--" + encodedString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }
}
