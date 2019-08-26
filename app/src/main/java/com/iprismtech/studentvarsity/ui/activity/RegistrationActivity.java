package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.StreamAdapter;
import com.iprismtech.studentvarsity.adapters.YearAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.RegistrationActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.RegistrationActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.EducationPOJO;
import com.iprismtech.studentvarsity.pojos.YearPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationActivity extends BaseAbstractActivity<RegistrationActivitylmpl> implements RegistrationActivityContract.IView, View.OnClickListener, APIResponseCallback, AdapterView.OnItemSelectedListener {

    ImageView imageview;
    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, KEY_USERID, KEY_EDUCATION_ID, KEY_STREAM_ID, KEY_YEARS, KEY_SUBJECTS;
    private Spinner spinner;
    String[] myarrayid;
    String[] myarraytitle;
    RecyclerView rv_stream, rv_year;
    StreamAdapter adapter;
    YearAdapter adapteryear;
    Bundle bundle;
    String id, position, stream_id = "", years = "";
    ArrayList<String> yearsidlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_registration);
    }

    @Override
    public void onBackPressed() {

        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_WELCOME_SCREEN);
        finish();
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_registration, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new RegistrationActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(RegistrationActivity.this);

        token = userSession.getUserDetails().get("token");
        KEY_USERID = userSession.getUserDetails().get("id");
        KEY_EDUCATION_ID = userSession.getUserDetails().get("education_id");
        KEY_STREAM_ID = userSession.getUserDetails().get("stream_id");
        KEY_YEARS = userSession.getUserDetails().get("years");
        KEY_SUBJECTS = userSession.getUserDetails().get("subjects");
        apiResponseCallback = this;

        bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            position = bundle.getString("position");
        }

        imageview = findViewById(R.id.im_fab);
        rv_stream = findViewById(R.id.rv_stream);
        rv_year = findViewById(R.id.rv_year);

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(RegistrationActivity.this, Subject.class);
//                startActivity(intent);
                if (!stream_id.isEmpty()) {
                    if (!years.isEmpty()) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("id", id);
                        bundle2.putString("stream_id", stream_id);
                        bundle2.putString("years", years);
                        bundle2.putString("sending_through", "registration");
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SUBJECT_SCREEN, bundle2);
                    } else {
                        Util.getInstance().cusToast(context, "please select years atleast one");
                    }
                } else {
                    Util.getInstance().cusToast(context, "please choose your stream");
                }
            }
        });


        if (token != null && token.length() > 0) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            presenter.education(RegistrationActivity.this, apiResponseCallback, requestBody);
        }


        spinner = (Spinner) findViewById(R.id.spinnerEducation);
        spinner.setOnItemSelectedListener(this);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, education);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (myarraytitle != null) {
            if (position != 0) {
                //  Toast.makeText(getApplicationContext(), myarraytitle[position], Toast.LENGTH_LONG).show();
                if (token != null && token.length() > 0) {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("education_id", myarrayid[position]);
                    presenter.streams_years(RegistrationActivity.this, apiResponseCallback, requestBody);
                }
            }
        }
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
            } else if (NetworkConstants.RequestCode.EDUCATION == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    //Util.getInstance().cusToast(context, message);
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
                    if (position != null) {
                        spinner.setSelection(Integer.parseInt(position));
                    }

                    if (id != null) {
                        if (token != null && token.length() > 0) {
                            Map<String, String> requestBody = new HashMap<>();
                            requestBody.put("token", token);
                            requestBody.put("education_id", id);
                            presenter.streams_years(RegistrationActivity.this, apiResponseCallback, requestBody);
                        }
                    } else if (KEY_EDUCATION_ID != null) {
                        if (token != null && token.length() > 0) {
                            Map<String, String> requestBody = new HashMap<>();
                            requestBody.put("token", token);
                            requestBody.put("education_id", KEY_EDUCATION_ID);
                            presenter.streams_years(RegistrationActivity.this, apiResponseCallback, requestBody);
                        }
                    }


                } else {
                    Util.getInstance().cusToast(context, message);
                }
            }
//            else if (NetworkConstants.RequestCode.STREAMS == requestId) {
//                boolean status = jsonObject.getBoolean("status");
//                String message = jsonObject.getString("message");
//
//                if (status) {
//                    Util.getInstance().cusToast(context, message);
//                    StreamsPOJO streamsPOJO = new Gson().fromJson(responseString, StreamsPOJO.class);
//
//                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(RegistrationActivity.this);
//                    //setting horizontal list
//                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    rv_stream.setLayoutManager(mLayoutManager);
//                    rv_stream.setItemAnimator(new DefaultItemAnimator());
//
//                    //Adding Adapter to recyclerView
//                    adapter = new StreamAdapter(streamsPOJO, RegistrationActivity.this);
//                    rv_stream.setAdapter(adapter);
//
//                    adapter.setOnItemClickListener(new StreamAdapter.OnitemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position) {
//                            if (token != null && token.length() > 0) {
//                                Map<String, String> requestBody = new HashMap<>();
//                                requestBody.put("token", token);
//                                requestBody.put("education_id", "3");
//                                presenter.streams_years(RegistrationActivity.this, apiResponseCallback, requestBody);
//                            }
//                        }
//                    });
//
////                    for (int i = 0; i < streamsPOJO.getResponse().size(); i++) {
////                        String id = streamsPOJO.getResponse().get(i).getId();
////                        String education_id = streamsPOJO.getResponse().get(i).getEducation_id();
////                        String title = streamsPOJO.getResponse().get(i).getTitle();
////
////                    }
//
//
//                } else {
//                    Util.getInstance().cusToast(context, message);
//                }
//            }
            else if (NetworkConstants.RequestCode.STREAMS_YEARS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    //   Util.getInstance().cusToast(context, message);
                    final YearPOJO yearPOJO = new Gson().fromJson(responseString, YearPOJO.class);
                    if (yearPOJO.getStreams().size() > 0) {
                        rv_stream.setVisibility(View.VISIBLE);
                        rv_year.setVisibility(View.VISIBLE);
                        ArrayList<String> streamcount = new ArrayList<>();
                        ArrayList<String> yearcount = new ArrayList<>();
                        if (KEY_STREAM_ID != null) {
                            if (yearPOJO.getStreams().size() > 0) {
                                for (int i = 0; i < yearPOJO.getStreams().size(); i++) {
                                    String checkstream = yearPOJO.getStreams().get(i).getId();

                                    if (checkstream.equalsIgnoreCase(KEY_STREAM_ID)) {
                                        streamcount.add("1");
                                    } else {
                                        streamcount.add("0");
                                    }

//                                streamcount.add("0");
                                }
                            }
                        } else {
                            if (yearPOJO.getStreams().size() > 0) {
                                for (int i = 0; i < yearPOJO.getStreams().size(); i++) {
                                    streamcount.add("0");
                                }
                            }
                        }


                        if (KEY_YEARS != null) {
                            List<String> myList = new ArrayList<String>(Arrays.asList(KEY_YEARS.split(",")));

                            if (yearPOJO.getYears().size() > 0) {
                                for (int i = 0; i < yearPOJO.getYears().size(); i++) {
                                    String checkyear = yearPOJO.getYears().get(i).getId();
                                    if (myList.contains(checkyear)) {
                                        yearcount.add("1");
                                    } else {
                                        yearcount.add("0");
                                    }
                                }
                            }
                        } else {
                            if (yearPOJO.getYears().size() > 0) {
                                for (int i = 0; i < yearPOJO.getYears().size(); i++) {
                                    yearcount.add("0");
                                }
                            }
                        }


                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(RegistrationActivity.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        rv_stream.setLayoutManager(mLayoutManager);
                        rv_stream.setItemAnimator(new DefaultItemAnimator());

                        //Adding Adapter to recyclerView

                        rv_stream.setVisibility(View.VISIBLE);
                        adapter = new StreamAdapter(yearPOJO, RegistrationActivity.this, streamcount);
                        rv_stream.setAdapter(adapter);


                        adapter.setOnItemClickListener(new StreamAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                for (int i = 0; i < yearPOJO.getStreams().size(); i++) {
                                    if (i == position) {
                                        StreamAdapter.streamcount.add(position, "1");
                                    } else {
                                        StreamAdapter.streamcount.add(i, "0");
                                    }
                                }
                                stream_id = yearPOJO.getStreams().get(position).getId();

                                adapter.notifyDataSetChanged();

                            }
                        });

                        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(RegistrationActivity.this);
                        //setting horizontal list
                        mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                        rv_year.setLayoutManager(mLayoutManager2);
                        rv_year.setItemAnimator(new DefaultItemAnimator());

                        //Adding Adapter to recyclerView
                        adapteryear = new YearAdapter(yearPOJO, RegistrationActivity.this, yearcount);
                        rv_year.setAdapter(adapteryear);

                        adapteryear.setOnItemClickListener(new YearAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                            TextView textView=view.findViewById(R.id.tv_year);
//                            textView.setBackgroundResource(R.drawable.courseyeartext_background);
//                            textView.setTextColor(R.color.);

                                if (yearsidlist.size() > 0) {
                                    if (yearsidlist.contains(yearPOJO.getYears().get(position).getId())) {
                                        YearAdapter.yearcount.set(position, "0");

                                        yearsidlist.remove(yearPOJO.getYears().get(position).getId());
                                        String select_id_array = yearsidlist.toString();
                                        years = select_id_array.substring(1, select_id_array.length() - 1);

                                        adapteryear.notifyDataSetChanged();
                                    } else {
                                        YearAdapter.yearcount.set(position, "1");

                                        yearsidlist.add(yearPOJO.getYears().get(position).getId());
                                        String select_id_array = yearsidlist.toString();
                                        years = select_id_array.substring(1, select_id_array.length() - 1);

                                        adapteryear.notifyDataSetChanged();
                                    }
                                } else {
                                    YearAdapter.yearcount.set(position, "1");

                                    yearsidlist.add(yearPOJO.getYears().get(position).getId());
                                    String select_id_array = yearsidlist.toString();
                                    years = select_id_array.substring(1, select_id_array.length() - 1);

                                    adapteryear.notifyDataSetChanged();

                                }


                            }
                        });

//                    adapteryear.setOnItemClickListener(new YearAdapter.OnitemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position) {
//
//                        }
//                    });


                    } else {
                        rv_stream.setVisibility(View.GONE);
                        rv_year.setVisibility(View.GONE);
                        Toast.makeText(context, "No Streams", Toast.LENGTH_SHORT).show();
                    }
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
