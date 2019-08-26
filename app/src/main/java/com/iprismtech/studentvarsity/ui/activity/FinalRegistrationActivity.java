package com.iprismtech.studentvarsity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.MyAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.FinalRegistrationActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.FinalRegistrationActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.SearchUniversityPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinalRegistrationActivity extends BaseAbstractActivity<FinalRegistrationActivitylmpl> implements FinalRegistrationActivityContract.IView, View.OnClickListener, APIResponseCallback {
    ImageView imgNext, im_back;
    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token;
    Bundle bundle;
    String id, stream_id = "", years = "", subjects = "";
    EditText edt_loginUser, edt_City, edt_Country;
    CheckBox cb, cb2;
    //TextInputEditText  edt_Collage;
    AutoCompleteTextView edt_Collage;
    public ArrayAdapter<String> aAdapter;
    public List<String> suggest;

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //setContentView(R.layout.activity_final_registration);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_final_registration, null);
        return view;
    }

    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    public void setPresenter() {
        presenter = new FinalRegistrationActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(FinalRegistrationActivity.this);

        token = userSession.getUserDetails().get("token");
        apiResponseCallback = this;

        bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            stream_id = bundle.getString("stream_id");
            years = bundle.getString("years");
            subjects = bundle.getString("subjects");
        }

        imgNext = findViewById(R.id.imgNext);
        edt_loginUser = findViewById(R.id.edt_loginUser);
        edt_Collage = findViewById(R.id.edt_Collage);
        edt_City = findViewById(R.id.edt_City);
        edt_Country = findViewById(R.id.edt_Country);
        cb = (CheckBox) findViewById(R.id.checkbox);
        cb2 = (CheckBox) findViewById(R.id.checkbox2);

        suggest = new ArrayList<String>();


//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("token", token);
//        requestBody.put("keyword", "gee");
//        presenter.search_universities(FinalRegistrationActivity.this, apiResponseCallback, requestBody);
        edt_Collage.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newText = s.toString();
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                requestBody.put("keyword", newText);
                presenter.search_universities(FinalRegistrationActivity.this, apiResponseCallback, requestBody);
            }

        });


//        edt_Collage.addTextChangedListener(new TextWatcher() {
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                // no need to do anything
//            }
//
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (((AutoCompleteTextView) edt_Collage).isPerformingCompletion()) {
//                    return;
//                }
//                if (charSequence.length() < 2) {
//                    return;
//                }
//
//                String query = charSequence.toString();
////                adapter.clear();
//
////                List<NameValuePair> data = new ArrayList<NameValuePair>();
////                data.add(new BasicNameValuePair("keyword", query));
////
////                String result = serverConnector.sendRequest(data, ServerConnector.SEARCH);
////                result = result.substring(2, result.length() - 3);
////
////                JSONDecoder decoder = new JSONDecoder(result);
////                JSONObject value = (JSONObject) decoder.decode();
////                Map<String, JSONValue> values = value.getValue();
////                if (values.size() != 0) {
////                    // transform from json to your object
////                    adapter.add(myPOJO);
////                }
//
//                Map<String, String> requestBody = new HashMap<>();
//                requestBody.put("token", token);
//                requestBody.put("keyword", query);
//                presenter.search_universities(FinalRegistrationActivity.this, apiResponseCallback, requestBody);
//
//
//            }
//
//            public void afterTextChanged(Editable editable) {
//            }
//        });

        im_back = findViewById(R.id.im_back);

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
//                Intent intent = new Intent(FinalRegistrationActivity.this, RegistrationActivity.class);
//                startActivity(intent);
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(FinalRegistrationActivity.this, ProfileActivity.class));
                if (isUserCredentialsValid()) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("id", id);
                    bundle2.putString("stream_id", stream_id);
                    bundle2.putString("years", years);
                    bundle2.putString("subjects", subjects);
                    bundle2.putString("name", edt_loginUser.getText().toString().trim());
                    bundle2.putString("university", edt_Collage.getText().toString().trim());
                    bundle2.putString("edt_City", edt_City.getText().toString().trim());
                    bundle2.putString("edt_Country", edt_Country.getText().toString().trim());
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PROFILE_SCREEN, bundle2);

                }
            }
        });

    }

    private boolean isUserCredentialsValid() {
        if (edt_loginUser.getText().toString().length() == 0) {
            //  edt_loginUser.setError("please enter name");
            Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show();
            //todo: Add shake animation
            return false;
        } else if (edt_Collage.getText().toString().length() == 0) {
            //     edt_Collage.setError("please enter college/university");
            Toast.makeText(context, "Please enter college/university", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_City.getText().toString().length() == 0) {
            // edt_City.setError("please enter city");
            Toast.makeText(context, "Please enter city", Toast.LENGTH_SHORT).show();
            return false;
        } else if (edt_Country.getText().toString().length() == 0) {
            // edt_Country.setError("please enter country");
            Toast.makeText(context, "Please enter Country", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!cb.isChecked()) {
            //  Toast.makeText(LoginPage.this, "Please select terms and conditions", Toast.LENGTH_SHORT).show();
            Util.getInstance().cusToast(context, "Please check terms of use");
            return false;
        } else if (!cb2.isChecked()) {
            //  Toast.makeText(LoginPage.this, "Please select terms and conditions", Toast.LENGTH_SHORT).show();
            Util.getInstance().cusToast(context, "Please check Data Protection");
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
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
            } else if (NetworkConstants.RequestCode.SEARCH_UNIVERSITIES == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    //  Util.getInstance().cusToast(context, message);

                    final SearchUniversityPOJO yearPOJO = new Gson().fromJson(responseString, SearchUniversityPOJO.class);
                    suggest = new ArrayList<>();
//                    adapter = new MyAdapter(this, android.R.layout.simple_dropdown_item_1line,yearPOJO);
//
//                    edt_Collage.setAdapter(adapter);
//
//                    //when autocomplete is clicked
//                    edt_Collage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            String countryName = adapter.getItem(position).getUniversity();
//                            edt_Collage.setText(countryName);
//                        }
//                    });

                    for (int i = 0; i < yearPOJO.getResponse().size(); i++) {
                        String SuggestKey = yearPOJO.getResponse().get(i).getUniversity();
                        suggest.add(SuggestKey);
                    }

                    aAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.mytextview, suggest);
                    edt_Collage.setAdapter(aAdapter);
                    // aAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    edt_Collage.setThreshold(1);
                    //  aAdapter.notifyDataSetChanged();

                }
                else {

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {
     //   Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
    }
}
