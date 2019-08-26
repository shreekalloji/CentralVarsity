package com.iprismtech.studentvarsity.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.GetusersfromcontactsAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.contacts.Contacts;
import com.iprismtech.studentvarsity.mvp.contract.activity.ConnectFriendsActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ConnectFriendsActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.GetusersfromcontactsPOJO;
import com.iprismtech.studentvarsity.pojos.Member;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectFriendsActivity extends BaseAbstractActivity<ConnectFriendsActivitylmpl> implements ConnectFriendsActivityContract.IView, View.OnClickListener, APIResponseCallback {
    TextView txtSkip;
    APIResponseCallback apiResponseCallback;


    ArrayList<Contacts> selectUsers;
    List<String> selectUsersphone;
    Cursor phones;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private UserSession userSession;
    private String token, user_id;
    RecyclerView rv_connectfriends;
    GetusersfromcontactsAdapter adapter;
    List<GetusersfromcontactsPOJO.ResponseBean> arrayList = new ArrayList<>();
    ;

    EditText search_bar;

    int selectedpos;
    GetusersfromcontactsPOJO yearPOJO;
    ArrayList<String> friendscount = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_connect_friends);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_connect_friends, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new ConnectFriendsActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(ConnectFriendsActivity.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        search_bar = findViewById(R.id.search_bar);
        txtSkip = findViewById(R.id.tv_skip);
        rv_connectfriends = findViewById(R.id.rv_connectfriends);
        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(ConnectFriendsActivity.this, ConnectUniversityFrds.class));
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CONNECTFRIENDSUNIVERSITY_SCREEN);
            }
        });
        selectUsers = new ArrayList<Contacts>();
        selectUsersphone = new ArrayList<String>();
        showContacts();

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub

                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            phones = getApplicationContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
            LoadContact loadContact = new LoadContact();
            loadContact.execute();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LoadContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //   showProgressDialog("loading...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Get Contact list from Phone

            if (phones != null) {
                Log.e("count", "" + phones.getCount());
                if (phones.getCount() == 0) {

                }

                while (phones.moveToNext()) {

                    Bitmap bit_thumb = null;
                    String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                    Contacts selectUser = new Contacts();
                    selectUser.setName(name);
                    selectUser.setPhone(phoneNumber);

                    String result = phoneNumber.replaceAll("[^0-9\\+]", "");
//                    selectUsersphone.add(result);

                    if (result.startsWith("+")) {
                        if (result.length() == 13) {
                            String str_getMOBILE = result.substring(1);
                            selectUsersphone.add(str_getMOBILE);

//                            if (ContactsAdapter.selectable_id.contains(str_getMOBILE)) {
//                                selectUser.setIs_selected(1);
//                            }else {
//                                selectUser.setIs_selected(0);
//                            }
                        }
                    } else {
//                        if (ContactsAdapter.selectable_id.contains(result)) {
//                            selectUser.setIs_selected(1);
//                        }else {
//                            selectUser.setIs_selected(0);
//                        }

                        selectUsersphone.add(result);

                    }

                    selectUsers.add(selectUser);


                }
            } else {
                Log.e("Cursor close 1", "----------------");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //  dismissProgressDialog();
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // sortContacts();
            int count = selectUsers.size();
            ArrayList<Contacts> removed = new ArrayList<>();
            ArrayList<Contacts> contacts = new ArrayList<>();
            for (int i = 0; i < selectUsers.size(); i++) {
                Contacts inviteFriendsProjo = selectUsers.get(i);

                if (inviteFriendsProjo.getName().matches("\\d+(?:\\.\\d+)?") || inviteFriendsProjo.getName().trim().length() == 0) {
                    removed.add(inviteFriendsProjo);
                    Log.d("Removed Contact", new Gson().toJson(inviteFriendsProjo));
                } else {
                    contacts.add(inviteFriendsProjo);
                }
            }
            contacts.addAll(removed);
            selectUsers = contacts;
//            adapterContacts = new ContactsAdapter(AddPeopleToEventActivity.this, selectUsers);
//            rv_notification.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//            rv_notification.setAdapter(adapterContacts);

            if (token != null || !token.isEmpty()) {
                Member member = new Member();
                member.setToken(token);
                member.setMobile_numbers(selectUsersphone);
                Gson gson = new Gson();
                String json = gson.toJson(member);

                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                //  requestBody.put("mobile_numbers", selectUsersphone.toString());
                requestBody.put("mobile_numbers", json);

                presenter.get_users_from_contacts(ConnectFriendsActivity.this, apiResponseCallback, json);
            }

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
            } else if (NetworkConstants.RequestCode.GET_USERS_FROM_CONTACTS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);

                    yearPOJO = new Gson().fromJson(responseString, GetusersfromcontactsPOJO.class);

                    if (yearPOJO.getResponse().size() > 0) {
                        for (int i = 0; i < yearPOJO.getResponse().size(); i++) {
                            friendscount.add(i, "0");
                        }
                    }
                    arrayList = yearPOJO.getResponse();
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(ConnectFriendsActivity.this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rv_connectfriends.setLayoutManager(mLayoutManager);
                    rv_connectfriends.setItemAnimator(new DefaultItemAnimator());

                    //Adding Adapter to recyclerView
                    adapter = new GetusersfromcontactsAdapter(arrayList, ConnectFriendsActivity.this, friendscount);
                    rv_connectfriends.setAdapter(adapter);

                    adapter.setOnItemClickListener(new GetusersfromcontactsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position, List<GetusersfromcontactsPOJO.ResponseBean> arrayList1) {
                            selectedpos = position;
                            arrayList = arrayList1;
                            //   stream_id = yearPOJO.getStreams().get(position).getId();
                            if (token != null || !token.isEmpty()) {
                                Map<String, String> requestBody = new HashMap<>();
                                requestBody.put("token", token);
                                requestBody.put("user_id", user_id);
                                requestBody.put("friend_id", arrayList1.get(position).getUser_id());
                                presenter.send_friend_request(ConnectFriendsActivity.this, apiResponseCallback, requestBody);
                            }
                        }
                    });

                }
                else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.SEND_FRIEND_REQUEST == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);

                    arrayList.get(selectedpos).setChecked(true);
                    adapter.notifyDataSetChanged();

//                    yearPOJO.getResponse().get(selectedpos).set

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
