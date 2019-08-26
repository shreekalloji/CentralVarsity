package com.iprismtech.studentvarsity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.WelcomeActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.WelcomeActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.EducationPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WelcomeActivity extends BaseAbstractActivity<WelcomeActivitylmpl> implements WelcomeActivityContract.IView, View.OnClickListener, APIResponseCallback, AdapterView.OnItemSelectedListener {

    ImageView fab_next;

    String[] education = {"HighSchool", "Intermediate(11th or 12th)", "Engineering", "Medicine", "Architecture"};

    APIResponseCallback apiResponseCallback;

    private UserSession userSession;
    private String token;

    private Spinner spinner;
    String[] myarrayid;
    String[] myarraytitle;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_welcome);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_welcome, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new WelcomeActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        bundle = new Bundle();

        userSession = new UserSession(WelcomeActivity.this);

        apiResponseCallback = this;

        fab_next = findViewById(R.id.fab);

        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        token = userSession.getUserDetails().get("token");


        if (token != null && token.length() > 0) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            presenter.education(WelcomeActivity.this, apiResponseCallback, requestBody);
        }


        spinner = (Spinner) findViewById(R.id.spinnerEducation);
        spinner.setOnItemSelectedListener(this);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, education);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(arrayAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        if (myarraytitle != null) {
            if (position != 0) {
                bundle.putString("id", myarrayid[position]);
                bundle.putString("position", position + "");
                //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_REGISTRATION_SCREEN, bundle);
                finish();

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
            } else if (NetworkConstants.RequestCode.EDUCATION == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    //  Util.getInstance().cusToast(context, message);
                    EducationPOJO educationPOJO = new Gson().fromJson(responseString, EducationPOJO.class);

                    myarrayid = new String[educationPOJO.getResponse().size() + 1];
                    myarraytitle = new String[educationPOJO.getResponse().size() + 1];
                    myarrayid[0] = "0";
                    myarraytitle[0] = " -Select Education- ";

                    for (int i = 0; i < educationPOJO.getResponse().size(); i++) {
                        String id = educationPOJO.getResponse().get(i).getId();
                        String title = educationPOJO.getResponse().get(i).getTitle();

                        myarrayid[i + 1] = id;
                        myarraytitle[i + 1] = title;
                    }

                    ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, myarraytitle);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);


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

    @Override
    protected void onResume() {
        super.onResume();
    }
}
