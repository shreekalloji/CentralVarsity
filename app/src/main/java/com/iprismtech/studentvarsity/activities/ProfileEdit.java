package com.iprismtech.studentvarsity.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.base.BaseAbstractActivity;
import com.iprismtech.studentvarsity.mvp.contract.activity.ProfileEditContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.PofileEditImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.SearchUniversityPOJO;
import com.iprismtech.studentvarsity.pojos.UserProfilePojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileEdit extends BaseAbstractActivity<PofileEditImpl> implements ProfileEditContract.IView, View.OnClickListener, APIResponseCallback {
    ImageView save_changes;
    private TextView iv_savechanges;
    private EditText edt_User_number, edt_user_mail, edt_User_name, edt_user_university, edt_User_city, edt_User_country;

    AutoCompleteTextView edt_Collage;
    public ArrayAdapter<String> aAdapter;
    public List<String> suggest;
    private ImageView iv_back;

    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private UserProfilePojo userProfilePojo;
    private String token, user_id, user_profile_pic, user_name, user_university, number, city, country, user_subjects, education_id, email;


    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_profile_edit, null);
        return view;
    }


    protected void initializeViews() {
        super.initializeViews();
        userSession = new UserSession(ProfileEdit.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        apiResponseCallback = this;

        iv_savechanges = findViewById(R.id.iv_savechanges);
        edt_User_number = findViewById(R.id.edt_User_number);
        edt_user_mail = findViewById(R.id.edt_user_mail);
        edt_User_name = findViewById(R.id.edt_User_name);
        edt_Collage = findViewById(R.id.edt_Collage);
        edt_User_city = findViewById(R.id.edt_User_city);
        edt_User_country = findViewById(R.id.edt_User_country);
        iv_back = findViewById(R.id.iv_back);

        suggest = new ArrayList<String>();


//        user_name = userSession.getUserDetails().get("name");
//        user_university = userSession.getUserDetails().get("university");
//        education_id = userSession.getUserDetails().get("education_id");
//        number = userSession.getUserDetails().get("mobileno");
//        user_profile_pic = userSession.getUserDetails().get("profileImg");
//        city = userSession.getUserDetails().get("city_id");
//        country = userSession.getUserDetails().get("country_id");
//        user_subjects = userSession.getUserDetails().get("subject_names");
//        email = userSession.getUserDetails().get("email");


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.user_profile(ProfileEdit.this, this, requestBody);


//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("token", token);
//        requestBody.put("keyword", "gee");
//        presenter.search_universities(ProfileEdit.this, apiResponseCallback, requestBody);
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
                presenter.search_universities(ProfileEdit.this, apiResponseCallback, requestBody);

            }

        });
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_savechanges.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_savechanges:
                /*{
"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjgzNDkxNDAzZGU2NWZiZmY5MmUxYWI1ZjVlNWExZmU0In0.hF2rqwyVE0dZO8eZ4Zu-x2n9EQvEVidlEmtWLAwaHpk",
"user_id":"1",
"name":"Jhon Doe",
"mobile":"9999999999",
"email_id":"johndoe@gmail.com",
"university":"Geetham College & University",
"country_id":"1",
"city_id":"1"
}
*/
                if (isUserCredentialsValid()) {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("name", edt_User_name.getText().toString());
                    requestBody.put("mobile", edt_User_number.getText().toString());
                    requestBody.put("email_id", edt_user_mail.getText().toString());
                    requestBody.put("university", edt_Collage.getText().toString());
                    requestBody.put("country", edt_User_country.getText().toString());
                    requestBody.put("city", edt_User_city.getText().toString());
                    presenter.update_profile(ProfileEdit.this, apiResponseCallback, requestBody);
                }

                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    private boolean isUserCredentialsValid() {
        String email = edt_user_mail.getText().toString().trim();

        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (edt_User_name.getText().toString().length() == 0) {
            edt_User_name.setError("please enter name");
            //todo: Add shake animation
            return false;
        } else if (edt_Collage.getText().toString().length() == 0) {
            edt_Collage.setError("please enter college/university");
            return false;
        } else if (edt_User_city.getText().toString().length() == 0) {
            edt_User_city.setError("please enter city");
            return false;
        } else if (edt_User_country.getText().toString().length() == 0) {
            edt_User_country.setError("please enter country");
            return false;
        } else if (edt_User_number.length() == 0) {
            edt_User_number.setError(getResources().getString(R.string.please_enter_your_phone_number));
            return false;
        } else if (edt_User_number.length() < 10) {
            edt_User_number.setError("please enter 10 digits");
            return false;
        } else if (email.length() == 0) {
            edt_user_mail.setError("please enter  email address");
            return false;

        } else if (!email.matches(emailPattern)) {
            edt_user_mail.setError("please enter valid email address");
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
    public void setPresenter() {
        presenter = new PofileEditImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                //  Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);

            } else if (NetworkConstants.RequestCode.UPDATE_PROFILE == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();

                    finish();
                }
            } else if (NetworkConstants.RequestCode.USER_PROFILE == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {

                    userProfilePojo = new Gson().fromJson(responseString, UserProfilePojo.class);


                    edt_User_name.setText(userProfilePojo.getResponse().getName());
                    edt_Collage.setText(userProfilePojo.getResponse().getUniversity());


                    edt_user_mail.setText(userProfilePojo.getResponse().getEmail_id());
                    edt_User_number.setText(userProfilePojo.getResponse().getMobile());
                    edt_User_city.setText(userProfilePojo.getResponse().getCity());
                    edt_User_country.setText(userProfilePojo.getResponse().getCountry());

                }
            } else if (NetworkConstants.RequestCode.SEARCH_UNIVERSITIES == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);

                    final SearchUniversityPOJO yearPOJO = new Gson().fromJson(responseString, SearchUniversityPOJO.class);


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

                } else {
                    Util.getInstance().cusToast(context, message);
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

