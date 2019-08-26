package com.iprismtech.studentvarsity.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.mvp.base.IBaseView;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.MyExceptionHandler;

import java.util.Locale;


public abstract class BaseAbstractActivity<T> extends AppCompatActivity implements IBaseView<T> {

    private FrameLayout activityContainerLayout;
    private ProgressDialog pDialog;
    protected Context context;
    protected Activity activity;
    protected FragmentManager fragmentManager;
    protected T presenter;
    protected UserSession userSession;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInstances();
        loadView();
        initializeViews();
        setListenerToViews();
        registerViews();
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this, FirstScreenActivity.class));
    }

    /**
     * Set listener to all the view here.
     * Make sure to override the setListenerToViews in the activity
     * Make sure the make call super and then set all listeners to views.
     */
    protected void setListenerToViews() {

    }

    private void loadView() {
        setContentView(R.layout.activity_mainbasic);
        activityContainerLayout = (FrameLayout) findViewById(R.id.fragment_container_layout);
        activityContainerLayout.addView(getView());
    }

    /**
     * get the view of activity
     * Need to set the view
     * Compulsory to Override in each activity
     *
     * @return
     */
    protected abstract View getView();


    @Override
    public void initializeInstances() {

        setPresenter();
    }

    /**
     * Register events to the views
     */
    private void registerViews() {
    }

    /**
     * initialize all the view here
     * Make sure to override the initializeViews in the activity
     * make sure the make call super and then initialize use views
     */
    protected void initializeViews() {
        context = this;
        activity = this;
        userSession = new UserSession(context);
        fragmentManager = getSupportFragmentManager();


        // LocaleUtils.updateConfig(this);
    }

    public void setLanguage(String language) {
        // Create a new Locale object
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        // Create a new configuration object
        Configuration config = new Configuration();
        // Set the locale of the new configuration
        config.locale = locale;
        // Update the configuration of the Accplication context
        getResources().updateConfiguration(
                config,
                getResources().getDisplayMetrics()
        );
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //drawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * Here we can show the dialog box on login fail
     *
     * @param dialogMessage
     */
    protected void showDialog(String dialogTitle, String dialogMessage, final int request_id, boolean isOutsideCancalabel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialogTitle);
        builder.setMessage(dialogMessage);

        builder.setPositiveButton(getString(R.string.ok_txt),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        onPositiveButtonClick(request_id);
                        dialog.dismiss();

                    }
                });
        builder.setNegativeButton(getString(R.string.cancel_txt),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        dialog.dismiss();
                        onCancelButtonClick(request_id);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(isOutsideCancalabel);
        dialog.show();
    }

    /**
     * Get called when we click on positive button
     *
     * @param request_id
     */
    protected void onPositiveButtonClick(int request_id) {
        switch (request_id) {
//            case REQ_LOGOUT:
//
//                break;
//
//            case REQ_LOGIN:
//
//                break;
        }

    }

    /**
     * Get called when we click on negative button
     *
     * @param request_id
     */
    protected void onCancelButtonClick(int request_id) {
        switch (request_id) {
        }
    }


    /**
     * Show loading.
     */
    public void showLoading(final Context context, final String loadingText, final boolean isCanceledOnTouchOutside) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    pDialog = new ProgressDialog(context);
                    pDialog.setMessage(loadingText);
                    pDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
                    pDialog.show();
                } catch (Exception e) {
                    Log.d("AlertDialog", "Progress dialog can not be shown. ;-(");
                }
            }
        });
    }


    /**
     * Hide loading.
     */
    public void hideLoading() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (pDialog != null)
                        pDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
