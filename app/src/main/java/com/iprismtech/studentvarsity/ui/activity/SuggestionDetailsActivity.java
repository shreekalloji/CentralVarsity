package com.iprismtech.studentvarsity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.mvp.contract.activity.SuggestionDetailsActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.SuggestionDetailsActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.SuggestionsPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SuggestionDetailsActivity extends BaseAbstractActivity<SuggestionDetailsActivitylmpl> implements SuggestionDetailsActivityContract.IView, View.OnClickListener, APIResponseCallback {

    Button submit;
    ImageView im_close;
    EditText et_suggestion;

    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, KEY_USERID;

    private Spinner spinner_s_type, spinner_exam_kind, spinner_exam_name, spinner_exam_confidence, spinner_country;
    private String s_type, exam_kind, exam_name, exam_confidence, country;

    // String[] myarraytypeid, myarraykindid, myarraynameid, myarrayconfidenceid, myarraycountryid;
    String[] myarraytypetitle, myarraykindtitle, myarraynametitle, myarrayconfidencetitle, myarraycountrytitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_suggestion_details);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_suggestion_details, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new SuggestionDetailsActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(SuggestionDetailsActivity.this);

        token = userSession.getUserDetails().get("token");
        KEY_USERID = userSession.getUserDetails().get("id");
        apiResponseCallback = this;


        spinner_s_type = (Spinner) findViewById(R.id.spinner_s_type);
        //spinner_s_type.setOnItemSelectedListener(this);

        spinner_exam_kind = (Spinner) findViewById(R.id.spinner_exam_kind);
        // spinner_exam_kind.setOnItemSelectedListener(this);

        spinner_exam_name = (Spinner) findViewById(R.id.spinner_exam_name);
        //spinner_exam_name.setOnItemSelectedListener(this);

        spinner_exam_confidence = (Spinner) findViewById(R.id.spinner_exam_confidence);
        // spinner_exam_confidence.setOnItemSelectedListener(this);

        spinner_country = (Spinner) findViewById(R.id.spinner_country);
        // spinner_country.setOnItemSelectedListener(this);

        et_suggestion = findViewById(R.id.et_suggestion);

        im_close = findViewById(R.id.im_close);

        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit = findViewById(R.id.btn_sugestion_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (token != null && token.length() > 0) {
                    String str_suggestion = et_suggestion.getText().toString().trim();
                    if (s_type != null) {
                        if (exam_kind != null) {
                            if (exam_name != null) {
                                if (exam_confidence != null) {

                                    if (country != null) {
                                        if (str_suggestion.length() == 0) {
                                            et_suggestion.setError("please enter Your Suggestion");
                                        } else {
                                            Map<String, String> requestBody = new HashMap<>();
//            {
//                "token":
//                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjgzNDkxNDAzZGU2NWZiZmY5MmUxYWI1ZjVlNWExZmU0In0.hF2rqwyVE0dZO8eZ4Zu-x2n9EQvEVidlEmtWLAwaHpk",
//                        "user_id":"1",
//                    "suggestion_type":"I am looking for good college",
//                    "exam_type":"Regular",
//                    "exam_name":"NEET",
//                    "confidence":"Good",
//                    "country":"India",
//                    "suggestion":"Nice app"
//            }
                                            requestBody.put("token", token);
                                            requestBody.put("user_id", KEY_USERID);
                                            requestBody.put("suggestion_type", s_type);
                                            requestBody.put("exam_type", exam_kind);
                                            requestBody.put("exam_name", exam_name);
                                            requestBody.put("confidence", exam_confidence);
                                            requestBody.put("country", country);
                                            requestBody.put("suggestion", "Nice app");
                                            presenter.submit_suggestion(SuggestionDetailsActivity.this, apiResponseCallback, requestBody);

                                        }
                                    } else {
                                        Util.getInstance().cusToast(context, "Please Select Country");
                                    }
                                } else {
                                    Util.getInstance().cusToast(context, "Please Select Confidence");
                                }
                            } else {
                                Util.getInstance().cusToast(context, "Please Select Name of Exam");
                            }
                        } else {
                            Util.getInstance().cusToast(context, "Please Select Kind of Exam");
                        }
//                    } else {
//
                    } else {
                        Util.getInstance().cusToast(context, "Please Select Type");

                    }
                }
            }
        });


        if (token != null && token.length() > 0) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            presenter.suggestions(SuggestionDetailsActivity.this, apiResponseCallback, requestBody);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString,
                                  @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
            } else if (NetworkConstants.RequestCode.SUBMIT_SUGGESTION == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);
                    Intent intent = new Intent(context, OverviewActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.SUGGESTIONS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);
                    SuggestionsPOJO educationPOJO = new Gson().fromJson(responseString, SuggestionsPOJO.class);


                    //myarraytypeid = new String[educationPOJO.getSuggestion_types().size() + 1];
                    myarraytypetitle = new String[educationPOJO.getSuggestion_types().size() + 1];
                    //  myarraytypeid[0] = "0";
                    myarraytypetitle[0] = " -Select Type- ";

                    //  myarraykindid = new String[educationPOJO.getExam_types().size() + 1];
                    myarraykindtitle = new String[educationPOJO.getExam_types().size() + 1];
                    //  myarraykindid[0] = "0";
                    myarraykindtitle[0] = " -Select Kind of Exam- ";

                    // myarraynameid = new String[educationPOJO.getExam_subjects().size() + 1];
                    myarraynametitle = new String[educationPOJO.getExam_subjects().size() + 1];
                    // myarraynameid[0] = "0";
                    myarraynametitle[0] = " -Select Name of Exam- ";

                    // myarrayconfidenceid = new String[educationPOJO.getPerformance().size() + 1];
                    myarrayconfidencetitle = new String[educationPOJO.getPerformance().size() + 1];
                    // myarrayconfidenceid[0] = "0";
                    myarrayconfidencetitle[0] = " -Select Confidence- ";

                    //myarraycountryid = new String[educationPOJO.getCountries().size() + 1];
                    myarraycountrytitle = new String[educationPOJO.getCountries().size() + 1];
                    //myarraycountryid[0] = "0";
                    myarraycountrytitle[0] = " -Select Country- ";

                    for (int i = 0; i < educationPOJO.getSuggestion_types().size(); i++) {
                        //String id = educationPOJO.getSuggestion_types().get(i).getId();
                        String title = educationPOJO.getSuggestion_types().get(i);

                        // myarraytypeid[i + 1] = id;
                        myarraytypetitle[i + 1] = title;
                    }

                    for (int i = 0; i < educationPOJO.getExam_types().size(); i++) {
                        //  String id = educationPOJO.getExam_types().get(i).getId();
                        String title = educationPOJO.getExam_types().get(i);

                        // myarraykindid[i + 1] = id;
                        myarraykindtitle[i + 1] = title;
                    }
                    for (int i = 0; i < educationPOJO.getExam_subjects().size(); i++) {
                        // String id = educationPOJO.getExam_subjects().get(i).getId();
                        String title = educationPOJO.getExam_subjects().get(i);

                        // myarraynameid[i + 1] = id;
                        myarraynametitle[i + 1] = title;
                    }
                    for (int i = 0; i < educationPOJO.getPerformance().size(); i++) {
                        // String id = educationPOJO.getPerformance().get(i).getId();
                        String title = educationPOJO.getPerformance().get(i);

                        //  myarrayconfidenceid[i + 1] = id;
                        myarrayconfidencetitle[i + 1] = title;
                    }
                    for (int i = 0; i < educationPOJO.getCountries().size(); i++) {
                        // String id = educationPOJO.getCountries().get(i).getId();
                        String title = educationPOJO.getCountries().get(i);

                        //myarraycountryid[i + 1] = id;
                        myarraycountrytitle[i + 1] = title;
                    }


                    ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, myarraytypetitle);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_s_type.setAdapter(arrayAdapter);

                    spinner_s_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (myarraytypetitle != null) {
                                if (position != 0) {
                                    s_type = myarraytypetitle[position];
                                    // bundle.putString("id", myarrayid[position]);
                                    //bundle.putString("position", position + "");
                                    //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
                                } else {
                                    s_type = null;
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, myarraykindtitle);
                    arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_exam_kind.setAdapter(arrayAdapter2);

                    spinner_exam_kind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (myarraykindtitle != null) {
                                if (position != 0) {
                                    exam_kind = myarraykindtitle[position];
                                    // bundle.putString("id", myarrayid[position]);
                                    //bundle.putString("position", position + "");
                                    //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
                                }
                            } else {
                                exam_kind = null;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, myarraynametitle);
                    arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_exam_name.setAdapter(arrayAdapter3);
                    spinner_exam_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (myarraynametitle != null) {
                                if (position != 0) {
                                    exam_name = myarraynametitle[position];
                                    // bundle.putString("id", myarrayid[position]);
                                    //bundle.putString("position", position + "");
                                    //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
                                }
                            } else {
                                exam_name = null;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter arrayAdapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, myarrayconfidencetitle);
                    arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_exam_confidence.setAdapter(arrayAdapter4);
                    spinner_exam_confidence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (myarrayconfidencetitle != null) {
                                if (position != 0) {
                                    exam_confidence = myarrayconfidencetitle[position];
                                    // bundle.putString("id", myarrayid[position]);
                                    //bundle.putString("position", position + "");
                                    //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
                                } else {
                                    exam_confidence = null;
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter arrayAdapter5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, myarraycountrytitle);
                    arrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_country.setAdapter(arrayAdapter5);

                    spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (myarraycountrytitle != null) {
                                if (position != 0) {
                                    country = myarraycountrytitle[position];
                                    // bundle.putString("id", myarrayid[position]);
                                    //bundle.putString("position", position + "");
                                    //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
                                } else {
                                    country = null;
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    Util.getInstance().cusToast(context, message);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(context, OverviewActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        switch (view.getId()) {
//            case R.id.spinner_s_type:
//                if (myarraytypetitle != null) {
//                    if (position != 0) {
//                        s_type = myarraytypetitle[position];
//                        // bundle.putString("id", myarrayid[position]);
//                        //bundle.putString("position", position + "");
//                        //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
//                    }
//                }
//                break;
//
//            case R.id.spinner_exam_kind:
//                if (myarraykindtitle != null) {
//                    if (position != 0) {
//                        exam_kind = myarraykindtitle[position];
//                        // bundle.putString("id", myarrayid[position]);
//                        //bundle.putString("position", position + "");
//                        //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
//                    }
//                }
//                break;
//
//            case R.id.spinner_exam_name:
//                if (myarraynametitle != null) {
//                    if (position != 0) {
//                        exam_name = myarraynametitle[position];
//                        // bundle.putString("id", myarrayid[position]);
//                        //bundle.putString("position", position + "");
//                        //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
//                    }
//                }
//                break;
//
//            case R.id.spinner_exam_confidence:
//                if (myarrayconfidencetitle != null) {
//                    if (position != 0) {
//                        exam_confidence = myarrayconfidencetitle[position];
//                        // bundle.putString("id", myarrayid[position]);
//                        //bundle.putString("position", position + "");
//                        //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
//                    }
//                }
//                break;
//
//            case R.id.spinner_country:
//                if (myarraycountrytitle != null) {
//                    if (position != 0) {
//                        country = myarraycountrytitle[position];
//                        // bundle.putString("id", myarrayid[position]);
//                        //bundle.putString("position", position + "");
//                        //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
//                    }
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
