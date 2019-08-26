package com.iprismtech.studentvarsity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.SemisterAdapeter;
import com.iprismtech.studentvarsity.adapters.SubjectAdapter;
import com.iprismtech.studentvarsity.adapters.SubjectSubAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;

import com.iprismtech.studentvarsity.mvp.contract.activity.SubjectContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.Subjectlmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.SemisterModel;
import com.iprismtech.studentvarsity.pojos.SubjectsModel;
import com.iprismtech.studentvarsity.pojos.SubjectsPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject extends BaseAbstractActivity<Subjectlmpl> implements SubjectContract.IView, View.OnClickListener, APIResponseCallback, AdapterView.OnItemSelectedListener {
    ImageView imageView, im_back;
    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, KEY_SUBJECTS, sending_through, KEY_SUBJECT_NAMES, STREAM, KEY_USERID, KEY_UserName, KEY_MOBILENO, KEY_PROFILE, KEY_UNIVERSITY, KEY_COUNTRY_ID, KEY_CITY_ID, KEY_BIO;
    RecyclerView rv_subject;
    SubjectAdapter adapter;
    SubjectSubAdapter adaptersub;
    Bundle bundle;
    private SubjectsPOJO subjectsPOJO;
    CheckBox cb_select_all;
    String id, stream_id = "", years = "", subject_ids = "", selected_ids_str;
    List<SubjectsPOJO.ResponseBean.SubjetcsBean> newSelctedList1 = new ArrayList<>();
    private StringBuilder selectedidsBilder;

    private StringBuilder selected_subjects;

    ArrayList<SemisterModel> semisterModels = new ArrayList<>();

    EditText search_bar;

    SemisterAdapeter semisterAdapeter;

    ArrayList<String> myList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_subject);
    }

    @Override
    public void onBackPressed() {
        if (sending_through.equalsIgnoreCase("settings")) {
            Intent intent = new Intent(Subject.this, ViewProfile_Activity.class);
            startActivity(intent);
            finish();
        } else {
            finish();
        }
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_subject, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new Subjectlmpl(this, this);
    }


    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(Subject.this);

        token = userSession.getUserDetails().get("token");
        KEY_SUBJECTS = userSession.getUserDetails().get("subjects");
        KEY_USERID = userSession.getUserDetails().get("id");
        KEY_UserName = userSession.getUserDetails().get("name");
        KEY_MOBILENO = userSession.getUserDetails().get("mobileno");
        KEY_PROFILE = userSession.getUserDetails().get("profileImg");
        KEY_UNIVERSITY = userSession.getUserDetails().get("university");
        KEY_COUNTRY_ID = userSession.getUserDetails().get("country_id");
        KEY_CITY_ID = userSession.getUserDetails().get("city_id");
        KEY_BIO = userSession.getUserDetails().get("bio");
        KEY_SUBJECT_NAMES = userSession.getUserDetails().get("subject_names");
        STREAM = userSession.getUserDetails().get("stream");
        KEY_BIO = userSession.getUserDetails().get("bio");
        apiResponseCallback = this;

        bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            stream_id = bundle.getString("stream_id");
            years = bundle.getString("years");
            sending_through = bundle.getString("sending_through");
        }
        String KEY_SUBJECTS123 = userSession.getUserDetails().get("subjects");

        if (KEY_SUBJECTS123 != null) {
            myList = new ArrayList<String>(Arrays.asList(KEY_SUBJECTS.split(",")));


        }


        imageView = findViewById(R.id.fab_subject);
        rv_subject = findViewById(R.id.rv_subject);
        search_bar = findViewById(R.id.search_bar);
        cb_select_all = findViewById(R.id.cb_select_all);

        cb_select_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // if(isChecked){
                if (semisterModels.size() > 0) {
                    for (int j = 0; j < SemisterAdapeter.filetr_semisterModels.size(); j++) {
                        for (int k = 0; k < SemisterAdapeter.filetr_semisterModels.get(j).getSubjectsModels().size(); k++) {
                            SubjectsModel subjectsModel1 = SemisterAdapeter.filetr_semisterModels.get(j).getSubjectsModels().get(k);


                            SemisterAdapeter.filetr_semisterModels.get(j).getSubjectsModels().get(k).setCheked(isChecked);

                        }

                    }

                    for (int j = 0; j < semisterModels.size(); j++) {
                        for (int k = 0; k < semisterModels.get(j).getSubjectsModels().size(); k++) {
                            SubjectsModel subjectsModel1 = semisterModels.get(j).getSubjectsModels().get(k);


                            semisterModels.get(j).getSubjectsModels().get(k).setCheked(isChecked);


                        }

                    }
                    semisterAdapeter.notifyDataSetChanged();
               /* }else {

                }*/


                /*if (isChecked) {
                    for (int i = 0; i < subjectsPOJO.getResponse().size(); i++) {
                        for (int j = 0; j < subjectsPOJO.getResponse().get(i).getSubjetcs().size(); j++) {
                            subjectsPOJO.getResponse().get(i).getSubjetcs().get(j).setSelected(true);
                            adapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    for (int i = 0; i < subjectsPOJO.getResponse().size(); i++) {
                        for (int j = 0; j < subjectsPOJO.getResponse().get(i).getSubjetcs().size(); j++) {
                            subjectsPOJO.getResponse().get(i).getSubjetcs().get(j).setSelected(false);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }*/
                }
            }
        });

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
               /* if (adaptersub.getItemCount() != 0 || adaptersub != null) {
                    // filter your list from your input

                    //you can use runnable postDelayed like 500 ms to delay search text
                }*/
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                semisterAdapeter.filter(search_bar.getText().toString());

              /*  if (adaptersub.getItemCount() != 0 || adaptersub != null) {
                    // filter your list from your input

                    //you can use runnable postDelayed like 500 ms to delay search text
                }*/
            }
        });

        im_back = findViewById(R.id.im_back);
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Subject.this, FinalRegistrationActivity.class);
//                startActivity(intent);

//                selectedidsBilder = new StringBuilder();
//                if (selectionData != null && selectionData.size() > 0) {
//                    for (int i = 0; i < selectionData.size(); i++) {
//                        selectedid = selectionData.get(i).getAsString() + ",";
//                        selectedidsBilder.append(selectedid);
//                    }
//
//                    selected_ids_str = selectedidsBilder.toString();
//                    selected_ids_str = selected_ids_str.substring(0, selected_ids_str.length() - 1);
//                }


                // Log.d("sibjectr_res", selectedidsBilder.toString());


                if (!KEY_SUBJECTS.equalsIgnoreCase("null")) {
//                    String select_id_array = SubjectAdapter.sub_ids.toString();
//                    subject_ids = select_id_array.substring(1, select_id_array.length() - 1);
//
//                    Map<String, String> requestBody = new HashMap<>();
//                    requestBody.put("token", token);
//                    requestBody.put("user_id", KEY_USERID);
//                    requestBody.put("education_id", id);
//                    requestBody.put("stream_id", stream_id);
//                    requestBody.put("years", years);
//                    requestBody.put("subjects", subject_ids);
//                    presenter.change_subjects(Subject.this, apiResponseCallback, requestBody);


                    String selectedid = "";
                    JsonArray selectionData = new JsonArray();
                  //  List<SubjectsPOJO.ResponseBean> semesters = adapter.getSemesterList();


                    selectedidsBilder = new StringBuilder();

                    for (int i = 0; i <SemisterAdapeter.filetr_semisterModels.size() ; i++) {

                        for(int k=0;k<SemisterAdapeter.filetr_semisterModels.get(i).getSubjectsModels().size();k++){
                            SubjectsModel subjectsModel=SemisterAdapeter.filetr_semisterModels.get(i).getSubjectsModels().get(k);

                            if(subjectsModel.getCheked()){
                                selectedid = subjectsModel.getId() + ",";

                                selectedidsBilder.append(selectedid);
                            }
                        }

                    }


                    /*for (SubjectsPOJO.ResponseBean semester : semesters) {
                        List<SubjectsPOJO.ResponseBean.SubjetcsBean> subjects = semester.getSubjetcs();
                        for (SubjectsPOJO.ResponseBean.SubjetcsBean subject : subjects) {

                            if (subject.getSelected()) {


                                selectedid = subject.getId() + ",";

                                selectedidsBilder.append(selectedid);
                                //   Log.d("sibjectr_resulry", selectedidsBilder.toString());

//                            JsonObject selected = new JsonObject();
//                            //selected.addProperty("subject", subject.getSubject());
//                            selected.addProperty("id", subject.getId());
//                            selectionData.add(selected);
                            }
                        }
                    }*/

                    if(selectedidsBilder.toString().isEmpty()){
                        Toast.makeText(context, "Select Subject(s)", Toast.LENGTH_SHORT).show();

                    }else {
                        Map<String, String> requestBody = new HashMap<>();

                        requestBody.put("token", token);
                        requestBody.put("user_id", KEY_USERID);
                        requestBody.put("education_id", id);
                        requestBody.put("stream_id", stream_id);
                        requestBody.put("years", years);
                        requestBody.put("subjects", selectedidsBilder.toString());
                        System.out.println("requestBody" + requestBody);

                        presenter.change_subjects(Subject.this, apiResponseCallback, requestBody);
                    }
                } else {


                    String selectedid = "";
                    JsonArray selectionData = new JsonArray();
                 //   List<SubjectsPOJO.ResponseBean> semesters = adapter.getSemesterList();


                    selectedidsBilder = new StringBuilder();

                    for (int i = 0; i <SemisterAdapeter.filetr_semisterModels.size() ; i++) {

                        for(int k=0;k<SemisterAdapeter.filetr_semisterModels.get(i).getSubjectsModels().size();k++){
                            SubjectsModel subjectsModel=SemisterAdapeter.filetr_semisterModels.get(i).getSubjectsModels().get(k);

                            if(subjectsModel.getCheked()){
                                selectedid = subjectsModel.getId() + ",";

                                selectedidsBilder.append(selectedid);
                            }
                        }

                    }

                  /*  for (SubjectsPOJO.ResponseBean semester : semesters) {
                        List<SubjectsPOJO.ResponseBean.SubjetcsBean> subjects = semester.getSubjetcs();
                        for (SubjectsPOJO.ResponseBean.SubjetcsBean subject : subjects) {

                            if (subject.getSelected()) {


                                selectedid = subject.getId() + ",";

                                selectedidsBilder.append(selectedid);
                                //   Log.d("sibjectr_resulry", selectedidsBilder.toString());

//                            JsonObject selected = new JsonObject();
//                            //selected.addProperty("subject", subject.getSubject());
//                            selected.addProperty("id", subject.getId());
//                            selectionData.add(selected);
                            }
                        }
                    }*/


//                        String select_id_array = SubjectAdapter.sub_ids.toString();
//                        subject_ids = select_id_array.substring(1, select_id_array.length() - 1);

                    if (selectedidsBilder.toString().isEmpty() || selectedidsBilder.toString() == null) {
                        Toast.makeText(context, "Select Subject(s)", Toast.LENGTH_SHORT).show();
                    } else {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("id", id);
                        bundle2.putString("stream_id", stream_id);
                        bundle2.putString("years", years);
                        bundle2.putString("subjects", selectedidsBilder.toString());
                        // bundle2.putString("subjects", "1,2,3");
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FINALREGISTRATION_SCREEN, bundle2);
                    }
                }

            }
        });


        if (token != null && token.length() > 0) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("education_id", id);
            requestBody.put("stream_id", stream_id);
            requestBody.put("years", years);
            presenter.subjects(Subject.this, apiResponseCallback, requestBody);
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
            } else if (NetworkConstants.RequestCode.SUBJECTS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {

                    //Util.getInstance().cusToast(context, message);
                    subjectsPOJO = new Gson().fromJson(responseString, SubjectsPOJO.class);

                    JSONArray json_semester = jsonObject.getJSONArray("response");

                    for (int i = 0; i < json_semester.length(); i++) {
                        ArrayList<SubjectsModel> subjectsModels = new ArrayList<>();
                        String semester = json_semester.getJSONObject(i).getString("semester");

                        JSONArray json_subjetcs = json_semester.getJSONObject(i).getJSONArray("subjetcs");

                        for (int j = 0; j < json_subjetcs.length(); j++) {

                            String id = json_subjetcs.getJSONObject(j).getString("id");

                            String subject = json_subjetcs.getJSONObject(j).getString("subject");

                            System.out.println("myList" + myList + "df" + id);
                            Boolean cheked = false;
                            if (myList.contains(id)) {
                                cheked = true;
                               /* CheckedModel checkedModel=new CheckedModel(id, true);
                                System.out.println("chegfdgdg"+checkedModel.getId());
                                checkedModels.add(checkedModel);*/
                            } else {
                                cheked = false;
                               /* CheckedModel checkedModel=new CheckedModel(id, false);
                                checkedModels.add(checkedModel);*/
                            }
                           // System.out.println("checkedModels" + checkedModels.size());

                            SubjectsModel subjectsModel = new SubjectsModel(id, subject, cheked);
                            subjectsModels.add(subjectsModel);


                        }

                        SemisterModel semisterModel = new SemisterModel(semester, subjectsModels);
                        semisterModels.add(semisterModel);

                    }

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(Subject.this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rv_subject.setLayoutManager(mLayoutManager);
                    rv_subject.setItemAnimator(new DefaultItemAnimator());
                    semisterAdapeter = new SemisterAdapeter(context, semisterModels);
                    rv_subject.setAdapter(semisterAdapeter);

                    //Adding Adapter to recyclerView
                    //  adapter = new SubjectAdapter(subjectsPOJO, Subject.this,semisterModels);
                    //  rv_subject.setAdapter(adapter);

                    // adapter.setSemesterList(subjectsPOJO.getResponse());

//                    adapter.setOnItemClickListener(new SubjectAdapter.OnitemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position, ArrayList<String> sub_ids) {
//                            if (sub_ids != null || sub_ids.size() > 0) {
//                                String fromsubject_ids = sub_ids.toString();
//                                subject_ids = fromsubject_ids.substring(0, sub_ids.size() - 1);
//                            }
//                        }
//                    });

                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.CHANGE_SUBJECTS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);
                    userSession.StoreUserDetails(KEY_USERID, KEY_UserName, KEY_MOBILENO, "", "", KEY_PROFILE, "", KEY_UNIVERSITY, token, KEY_COUNTRY_ID, KEY_CITY_ID, KEY_BIO, id, stream_id, years, subject_ids, KEY_SUBJECT_NAMES, STREAM);

                    Intent intent = new Intent(Subject.this, ViewProfile_Activity.class);
                    startActivity(intent);
                    finish();

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
