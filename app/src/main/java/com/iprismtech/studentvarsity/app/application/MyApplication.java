package com.iprismtech.studentvarsity.app.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.iprismtech.studentvarsity.utils.LocaleUtils;


/**
 * Created by Prasad on 6/20/2017.
 */
public class MyApplication extends Application {

    /**
     * private instance of MyApplication for singleton Design Pattern. which will create single object.
     */
    private static MyApplication myApplication = null;

    /**
     * instance of Dao session with getter for interacting with green dao tables.
     */
    // private DaoSession daoSession;

    /**
     * Make to API request in queues
     */
    private RequestQueue mRequestQueue;

    /**
     * Name of database
     */
    private final String DATABASE_NAME = "framework_db";

    private Context context;


    /**
     * Making the Application class single ton
     *
     * @return
     */
    public static MyApplication getInstance() {
        return myApplication;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        setContext(this);
        /*initStetho();
        configureGreenDaoSettings();*/
      /*  LocaleUtils.setLocale(new Locale("ar"));
        LocaleUtils.updateConfig(this, getBaseContext().getResources().getConfiguration());*/
    }

    /**
     * Accessing the green dao session for interacting for table created using green dao
     *
     * @return
     */
    /*public DaoSession getDaoSession() {
        return daoSession;
    }*/


    /**
     * init the Green Dao components for sql lite managements
     */
  /*  private void configureGreenDaoSettings() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DATABASE_NAME);
        // users-db here is the name of our database.
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }*/

    /**
     * init the stetho components to show Local Database
     */
  /*  private void initStetho() {
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }*/
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    /**
     * Method which return the Request into queues
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);
        }
        return mRequestQueue;
    }

    /**
     * Method which added the Request into queues
     *
     * @param req
     * @param tag
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req, int tag) {
        // set the default tag if tag is empty
        // req.setTag(tag);
        getRequestQueue().add(req);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleUtils.updateConfig(this, newConfig);
    }


}
