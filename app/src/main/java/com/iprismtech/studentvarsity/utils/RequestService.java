package com.iprismtech.studentvarsity.utils;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.sharepref.UserSession;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.content.Context.ACTIVITY_SERVICE;

public class RequestService extends BroadcastReceiver {
    Context context;
    //Helper helper;
    String strUserId, strLanguage;
    public static String tripidfromservice = "";
    // Settings appSettings;
    GPSTracker gpsTracker;
    public static final String ACTION_LOC_ALARM = "com.volive.calltrack.alarms.ACTION_LOC_ALARM";
    // PreferenceUtils preferenceUtils;
    String langauage;
    String user_mode;
    String stoken;
    String new_request;
    private UserSession userSession;
    private String token, user_id, session_type;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        userSession = new UserSession(context);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        session_type = userSession.getUserDetails().get("session_type");

        if (token != null && token.length() > 0) {
            if (user_id != null && user_id.length() > 0) {
                locationupdate();
            }
        }

    }

    private boolean isAppRunning() {
        ActivityManager m = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = m.getRunningTasks(10);
        Iterator<ActivityManager.RunningTaskInfo> itr = runningTaskInfoList.iterator();
        int n = 0;
        while (itr.hasNext()) {
            n++;
            itr.next();
        }
        if (n == 1) { // App is killed
            return false;
        }

        return true; // App is in background or foreground
    }

    public void locationupdate() {
        boolean status = isAppRunning();
        // boolean status = false;
        Log.d("Response>>>>", "" + status);
        showToast("" + status);
        if (session_type != null && session_type.length() > 0) {
            if (status) {
                if (!session_type.equalsIgnoreCase("online")) {
                    if (!isNetworkAvailable()) {
                        showToast(context.getResources().getString(R.string.please_check_your_network_connection));
                        return;
                    } else {
                        Map<String, String> params = new HashMap<>();

                        params.put("token", token);
                        params.put("user_id", user_id);
                        params.put("session_type", "offline");
                        sendRequest(params, "offline");
                        //ServiceCallsubmit_rider_lat_lng(params);
//                sendRequest(params);
                    }
                }
            } else {
                if (!session_type.equalsIgnoreCase("offline")) {
                    if (!isNetworkAvailable()) {
                        showToast(context.getResources().getString(R.string.please_check_your_network_connection));
                        return;
                    } else {
                        Map<String, String> params = new HashMap<>();

                        params.put("token", token);
                        params.put("user_id", user_id);
                        params.put("session_type", "online");
                        sendRequest(params, "online");
                        //ServiceCallsubmit_rider_lat_lng(params);
//                sendRequest(params);
                    }
                }
            }
        } else {
            if (!isNetworkAvailable()) {
                showToast(context.getResources().getString(R.string.please_check_your_network_connection));
                return;
            } else {
                Map<String, String> params = new HashMap<>();

                params.put("token", token);
                params.put("user_id", user_id);
                if (status) {
                    params.put("session_type", "online");
                    sendRequest(params, "online");
                } else {
                    params.put("session_type", "offline");
                    sendRequest(params, "offline");
                }
                //ServiceCallsubmit_rider_lat_lng(params);
//                sendRequest(params);
            }
        }


    }


    private void sendRequest(final Map<String, String> params, final String session_type) {
        JsonObjectRequest jsonRequest = null;
        try {
            jsonRequest = new JsonObjectRequest(Request.Method.POST, NetworkConstants.URL.SUBMIT_USERS_ONLINE_STATUS, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("Response>>>>", response.toString() + "  URL " + NetworkConstants.URL.SUBMIT_USERS_ONLINE_STATUS + "  params " + params);
                                //dismiss dialog once response has been received from the server.
                                if (response != null && response.length() > 0) {
                                    //send the response the particular model where all data will parse their.
                                    //handelResponse(requestId, response.toString());
                                    JSONObject jsonObject = new JSONObject(response.toString());
                                    boolean status = jsonObject.getBoolean("status");
                                    if (status) {
                                        userSession.setlanguage(session_type);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> paramsHeader = new HashMap<>();
                    paramsHeader.put("Content-Type", "application/json");
                    paramsHeader.put("Authorization", "Basic");
                    return paramsHeader;
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        setJsonRequestQueue(context, jsonRequest);
        //MyApplication.getInstance().addToRequestQueue(jsonRequest, requestId);
    }

    private void setJsonRequestQueue(Context context, JsonObjectRequest request) {
        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

//    private void ServiceCallsubmit_rider_lat_lng(Map params) {
//
//        final API_class service = Retrofit_funtion_class.getClient().create(API_class.class);
//        Call<JsonElement> callRetrofit = null;
//
//        Gson gsone = new Gson();
//        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
//
//        callRetrofit = service.submit_rider_lat_lng(res);
//
////        final ProgressDialog progressDoalog;
////        progressDoalog = new ProgressDialog(LoginPage.this);
////        progressDoalog.setCancelable(false);
////        progressDoalog.setMessage(getString(R.string.loading));
////        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
////        progressDoalog.show();
//
//        callRetrofit.enqueue(new Callback<JsonElement>() {
//
//            @Override
//            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
//                // progressDoalog.dismiss();
//
//                if (response.isSuccessful()) {
//                    String searchResponse = response.body().toString();
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(response.body().toString());
//                        Log.e("verif_response", "" + response.body());
//
//                        Boolean status = jsonObject.getBoolean("status");
////                        String message = jsonObject.optString("message");
//
////                        showToast(message);
//                        if (status) {
//                            // Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                        } else {
//                            //  Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<JsonElement> call, Throwable t) {
//                // progressDoalog.dismiss();
//                Log.d("Error Call", ">>>>" + call.toString());
//                Log.d("Error", ">>>>" + t.toString());
//            }
//        });
//
//
//    }

    public void showToast(String message) {
        //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        return nf != null && nf.isConnected();
    }
}
