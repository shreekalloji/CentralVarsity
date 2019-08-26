package com.iprismtech.studentvarsity.ui.activity;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.PaginationAdapter;
import com.iprismtech.studentvarsity.mvp.contract.activity.Main2ActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.Main2Activitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.Movie;
import com.iprismtech.studentvarsity.utils.PaginationScrollListener;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends BaseAbstractActivity<Main2Activitylmpl> implements Main2ActivityContract.IView, View.OnClickListener, APIResponseCallback {

    private static final String TAG = "MainActivity";

    PaginationAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    RecyclerView rv;
    ProgressBar progressBar;

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 3;
    private int currentPage = PAGE_START;

    APIResponseCallback apiResponseCallback;

    String abc="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //  setContentView(R.layout.activity_main2);

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_main2, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new Main2Activitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        rv = (RecyclerView) findViewById(R.id.main_recycler);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);

        adapter = new PaginationAdapter(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new PaginationAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "dffgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfg: ");
            }
        });

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


        // mocking network delay for API call
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadFirstPage();
            }
        }, 1000);
    }


    private void loadFirstPage() {
        abc="1";

        Log.d(TAG, "loadFirstPage: ");
     //   List<Movie> movies = Movie.createMovies(adapter.getItemCount());
//        {
//            "shopID":"vw19014418", "start_from":2, "userID":"app190221180317"
//        }
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("shopID", "vw19014418");
        requestBody.put("start_from", String.valueOf(currentPage));
        requestBody.put("userID", "app190221180317");
        presenter.shop_reviews(Main2Activity.this, apiResponseCallback, requestBody);

       // progressBar.setVisibility(View.GONE);
       // adapter.addAll(movies);

       // if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
       // else isLastPage = true;

    }

    private void loadNextPage() {

        abc="2";
        Log.d(TAG, "loadNextPage: " + currentPage);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("shopID", "vw19014418");
        requestBody.put("start_from", String.valueOf(currentPage));
        requestBody.put("userID", "app190221180317");
        presenter.shop_reviews(Main2Activity.this, apiResponseCallback, requestBody);

//        List<Movie> movies = Movie.createMovies(adapter.getItemCount());
//
//        adapter.removeLoadingFooter();
//        isLoading = false;
//
//        adapter.addAll(movies);
//
//        if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
//        else isLastPage = true;
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
            } else if (NetworkConstants.RequestCode.SHOP_REVIEWS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {

                    Util.getInstance().cusToast(context, message);

                    JSONObject jsonArrayresponse = jsonObject.optJSONObject("response");
                    JSONArray jsonArray = jsonArrayresponse.optJSONArray("Reviews");

                    List<Movie> movies = new ArrayList<>();

                    if (jsonArray != null && jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject item = jsonArray.getJSONObject(i);
                            //add items to model

                            String reviews = item.optString("reviews");

                            Movie movie = new Movie(reviews);
                            movies.add(movie);
                        }
                        List<Movie> moviesmain = movies;
                        if (abc.equalsIgnoreCase("1")) {

                            progressBar.setVisibility(View.GONE);
                            adapter.addAll(moviesmain);

                            if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                            else isLastPage = true;
                        }else {
                            adapter.removeLoadingFooter();
                            isLoading = false;

                            adapter.addAll(moviesmain);

                            if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                            else isLastPage = true;
                        }


                    } else {

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
