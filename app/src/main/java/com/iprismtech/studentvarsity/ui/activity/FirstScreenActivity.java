package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.FirstScreenActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.FirstScreenActivitylmpl;
import com.iprismtech.studentvarsity.sharepref.UserSession;

public class FirstScreenActivity extends BaseAbstractActivity<FirstScreenActivitylmpl> implements FirstScreenActivityContract.IView, View.OnClickListener {
    TextView txtSignup, txtLogin;
    private UserSession userSession;
    private String KEY_USERID, KEY_UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_first_screen);
        presenter.start();
        initViews();
    }


    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_first_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new FirstScreenActivitylmpl(this, this);
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    private void initViews() {
        setReferences();
        setOnclickListners();
    }

    private void setReferences() {
        userSession = new UserSession(FirstScreenActivity.this);
        txtLogin = findViewById(R.id.txtLogin);
        txtSignup = findViewById(R.id.txtSignup);

        KEY_USERID = userSession.getUserDetails().get("id");
        KEY_UserName = userSession.getUserDetails().get("name");

        if (KEY_USERID != null) {
            if (!KEY_UserName.equalsIgnoreCase("null")) {
                Bundle bundle = new Bundle();
                bundle.putString("comimg_through", "first");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);
                finishAffinity();
            } else {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_WELCOME_SCREEN);
                finishAffinity();
            }
        }


    }

    private void setOnclickListners() {
        txtSignup.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSignup:
                // startActivity(new Intent(FirstScreenActivity.this, SignUpActivity.class));
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN);

                break;
            case R.id.txtLogin:
                //startActivity(new Intent(FirstScreenActivity.this, LoginActivity.class));
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                break;
        }
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }
}
