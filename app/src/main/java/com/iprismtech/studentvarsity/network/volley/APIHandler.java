package com.iprismtech.studentvarsity.network.volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.IDialogCallback;
import com.iprismtech.studentvarsity.network.responcedos.CommonResponse;
import com.iprismtech.studentvarsity.network.utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Prasad on 6/20/2017.
 */
public class APIHandler implements IDialogCallback {


    private static final int MY_SOCKET_TIMEOUT_MS = 10000;
    private Context context;
    private int requestId;
    private int methodType;
    private boolean showLoading = false;
    private String loadingText;
    private String url;
    private ProgressDialog pDialog = null;
    private APICallback apiCallback = null;
    private Map<String, String> headers = null;
    private Map<String, String> params = null;
    private JSONObject params3 = null;
    private String params2 = null;
    private boolean showToastOnResponse = true;
    private boolean isCanceledOnTouchOutside = false;

    public APIHandler(Context context, APICallback apiCallback, int requestId, int methodType, String url,
                      boolean showLoading, boolean isCanceledOnTouchOutside, String loadingText, Map<String, String> params, Map<String, String> headers) {

        this.context = context;
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.methodType = methodType;
        this.url = url;
        this.showLoading = showLoading;
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
        this.loadingText = loadingText;
        this.params = params;
        this.headers = headers;
    }

    public APIHandler(Context context, APICallback apiCallback, int requestId, int methodType, String url,
                      boolean showLoading, boolean isCanceledOnTouchOutside, String loadingText, JSONObject params, Map<String, String> headers) {

        this.context = context;
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.methodType = methodType;
        this.url = url;
        this.showLoading = showLoading;
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
        this.loadingText = loadingText;
        this.params3 = params;
        this.headers = headers;
    }

    public APIHandler(Context context, APICallback apiCallback, int requestId, int methodType, String url,
                      boolean showLoading, boolean isCanceledOnTouchOutside, String loadingText, String params, Map<String, String> headers) {

        this.context = context;
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.methodType = methodType;
        this.url = url;
        this.showLoading = showLoading;
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
        this.loadingText = loadingText;
        this.params2 = params;
        this.headers = headers;
    }

    private void showLoading() {
        try {
//            pDialog = new ProgressDialog(context);
//            pDialog.setMessage(loadingText);
//            pDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
//            pDialog.show();

            pDialog = new ProgressDialog(context);
            // pDialog = new ProgressDialog(context);
            pDialog.getWindow().setBackgroundDrawable(new
                    ColorDrawable(android.graphics.Color.TRANSPARENT));
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.setContentView(R.layout.progressxml);
            pDialog.setMessage(loadingText);
            // pDialog.setIndeterminate(true);
            pDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
            pDialog.show();


        } catch (Exception e) {
            Log.d("AlertDialog", "Progress dialog can not be shown. ;-(");
        }
    }

    /**
     * Hide loading.
     */
    private void hideLoading() {
        try {
            if (pDialog != null && pDialog.isShowing())
                pDialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Request API to get response for respective request.
     */
    public void requestAPI() {
        //check if internet connect found or not
        try {
            if (!Utils.checkInternetConnection(context)) {
                String networkErrorBody = Utils.getNetworkErrorBody(context);
                apiCallback.onAPISuccessResponse(requestId, networkErrorBody);
            } else {
                System.out.println("[API] request url = " + url);
                System.out.println("[API] request body = " + params);
                System.out.println("[API] request Auth_Token: = " + headers.get(NetworkConstants.Headers.X_AUTH_TOKEN));
                if (showLoading) {
                    showLoading();
                }
                //Send the request to get the response.
                sendRequest(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestAPI2() {
        //check if internet connect found or not
        try {
            if (!Utils.checkInternetConnection(context)) {
                String networkErrorBody = Utils.getNetworkErrorBody(context);
                apiCallback.onAPISuccessResponse(requestId, networkErrorBody);
            } else {
                System.out.println("[API] request url = " + url);
                System.out.println("[API] request body = " + params2);
                System.out.println("[API] request Auth_Token: = " + headers.get(NetworkConstants.Headers.X_AUTH_TOKEN));
                if (showLoading) {
                    showLoading();
                }
                //Send the request to get the response.
                sendRequest(params2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestAPI3() {
        //check if internet connect found or not
        try {
            if (!Utils.checkInternetConnection(context)) {
                String networkErrorBody = Utils.getNetworkErrorBody(context);
                apiCallback.onAPISuccessResponse(requestId, networkErrorBody);
            } else {
                System.out.println("[API] request url = " + url);
                System.out.println("[API] request body = " + params3);
                System.out.println("[API] request Auth_Token: = " + headers.get(NetworkConstants.Headers.X_AUTH_TOKEN));
                if (showLoading) {
                    showLoading();
                }
                //Send the request to get the response.
                sendRequest(params3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendRequest(final Map<String, String> params) {
        JsonObjectRequest jsonRequest = null;
        try {
            jsonRequest = new JsonObjectRequest(methodType, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (pDialog != null && pDialog.isShowing())
                                    pDialog.dismiss();
                                Log.d("Response>>>>", response.toString() + "  URL " + url + "  params " + params);
                                //dismiss dialog once response has been received from the server.
                                if (response != null && response.length() > 0) {
                                    //send the response the particular model where all data will parse their.
                                    handelResponse(requestId, response.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (pDialog != null && pDialog.isShowing())
                                pDialog.hide();
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

    private void sendRequest(final String params) {
        JsonObjectRequest jsonRequest = null;
        try {
            jsonRequest = new JsonObjectRequest(methodType, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (pDialog != null && pDialog.isShowing())
                                    pDialog.hide();
                                //dismiss dialog once response has been received from the server.
                                if (response != null && response.length() > 0) {
                                    //send the response the particular model where all data will parse their.

                                    Log.d("Response>>>>", response.toString() + "  URL " + url + "  params " + params);
                                    handelResponse(requestId, response.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (pDialog != null && pDialog.isShowing())
                                pDialog.hide();
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

    private void sendRequest(final JSONObject params) {
        JsonObjectRequest jsonRequest = null;
        try {
            jsonRequest = new JsonObjectRequest(methodType, url, params,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (pDialog != null && pDialog.isShowing())
                                    pDialog.hide();
                                //dismiss dialog once response has been received from the server.
                                if (response != null && response.length() > 0) {
                                    //send the response the particular model where all data will parse their.

                                    Log.d("Response>>>>", response.toString() + "  URL " + url + "  params " + params);
                                    handelResponse(requestId, response.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (pDialog != null && pDialog.isShowing())
                                pDialog.hide();
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

    /**
     * Handel The API response
     *
     * @param requestId
     * @param response
     */
    private void handelResponse(int requestId, String response) {
        Gson gson = new Gson();
        CommonResponse commonResponse = gson.fromJson(response, CommonResponse.class);
        if (commonResponse != null) {
            String message = commonResponse.getMessage();
            int status_code = commonResponse.getStatus_code();

            switch (status_code) {
                case NetworkConstants.RequestCode.SESSION_EXPIRE:
                    Utils.showErrorDialog(context, context.getString(R.string.ok_txt), message,
                            true, this, NetworkConstants.RequestCode.SESSION_EXPIRE);
                    break;
                default:
                    try {
                        apiCallback.onAPISuccessResponse(requestId, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    /**
     * If any error occurs during data parsing then all error will handle here only.
     *
     * @param error
     */
    private void handleErrorResponse(VolleyError error) {
        String jsonBody = "";
        System.out.println("[API] response body volly = " + error);
        try {
            String errorMessage = "";
            if (error != null) {
                if (error instanceof TimeoutError) {
                    jsonBody = Utils.constructJSONBody(context.getString(R.string.timeOutTxt));
                    System.out.println("[API] response body volly = " + errorMessage);
                } else if (error instanceof ServerError) {
                    jsonBody = Utils.constructJSONBody(context.getString(R.string.serverError));
                    System.out.println("[API] response body volly = " + errorMessage);
                } else if (error instanceof NoConnectionError) {
                    jsonBody = Utils.constructJSONBody(context.getString(R.string.unknownErrorMsg));
                    System.out.println("[API] response body volly = " + errorMessage);
                } else {
                    jsonBody = Utils.constructJSONBody(context.getString(R.string.unknownErrorMsg));
                    System.out.println("[API] response body volly = " + errorMessage);
                }
            } else {
                errorMessage = context.getResources().getString(R.string.unknownErrorMsg);
                jsonBody = Utils.constructJSONBody(errorMessage);
                System.out.println("[API] response body volly = " + errorMessage);
            }

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } finally {
            if (apiCallback != null) {
                apiCallback.onAPISuccessResponse(requestId, jsonBody);
                hideLoading();
            }
        }

    }

    /**
     * Check for error msg from volly
     *
     * @param error
     * @param defaultMessage
     * @return
     */
    private String getMessage(String error, String defaultMessage) {
        String finalMsg = null;
        if (error == null || error.isEmpty()) {
            finalMsg = defaultMessage;
        } else {
            finalMsg = error;
        }

        return finalMsg;
    }

    public boolean isShowToastOnResponse() {
        return showToastOnResponse;
    }

    public void setShowToastOnResponse(boolean showToastOnResponse) {
        this.showToastOnResponse = showToastOnResponse;
    }


    @Override
    public void onPositiveButtonPress(int requestCode) {

    }

    public void onNegativeButtonPress(int requestCode) {

    }
}
