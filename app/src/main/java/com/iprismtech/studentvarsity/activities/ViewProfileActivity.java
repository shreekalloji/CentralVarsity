package com.iprismtech.studentvarsity.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.MyActivityPostsAdapter;
import com.iprismtech.studentvarsity.adapters.MyFriendsAdapter;
import com.iprismtech.studentvarsity.adapters.MyPostedDiscussionsAdapter;
import com.iprismtech.studentvarsity.adapters.MyQuizzesAdapter;
import com.iprismtech.studentvarsity.adapters.MySuggestionsAdapter;
import com.iprismtech.studentvarsity.base.BaseAbstractActivity;
import com.iprismtech.studentvarsity.mvp.contract.activity.ViewProfileActContarct;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ViewProfileActImpl;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.FriendsPojo;
import com.iprismtech.studentvarsity.pojos.MyActivityPostsPojo;
import com.iprismtech.studentvarsity.pojos.MyPostedDiscussionsPojo;
import com.iprismtech.studentvarsity.pojos.MyPostedSuggestionsPojo;
import com.iprismtech.studentvarsity.pojos.MyQuizzesPojo;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewProfileActivity extends BaseAbstractActivity<ViewProfileActImpl> implements ViewProfileActContarct.IView, View.OnClickListener, APIResponseCallback {

    private ImageView iv_settings, iv_edit,iv_profile_img;
    private LinearLayout ll_friends, ll_post, ll_comment, ll_question, ll_suggestions;
    private RecyclerView rview_friends, rview_posts, rview_comments, rview_questions, rview_suggestions;
    private LinearLayoutManager manager;
    private FriendsPojo friendsPojo;

    private MyFriendsAdapter myFriendsAdapter;
    private MyActivityPostsAdapter myActivityPostsAdapter;
    private MySuggestionsAdapter mySuggestionsAdapter;
    private MyPostedDiscussionsAdapter myPostedDiscussionsAdapter;
    private MyQuizzesAdapter myQuizzesAdapter;

    private MyQuizzesPojo myQuizzesPojo;
    private MyActivityPostsPojo myActivityPostsPojo;
    private MyPostedDiscussionsPojo myPostedDiscussionsPojo;
    private MyPostedSuggestionsPojo myPostedSuggestionsPojo;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_profile_);
//
//        iv_settings = findViewById(R.id.iv_settings);
//        iv_edit = findViewById(R.id.iv_edit);
//
//        iv_settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewProfileActivity.this, SettingActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        iv_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewProfileActivity.this, ProfileEdit.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_friends.setOnClickListener(this);
        ll_post.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
        ll_question.setOnClickListener(this);
        ll_suggestions.setOnClickListener(this);


    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        iv_settings = findViewById(R.id.iv_settings);
        iv_edit = findViewById(R.id.iv_edit);
        ll_friends = findViewById(R.id.ll_friends);
        ll_post = findViewById(R.id.ll_post);
        ll_comment = findViewById(R.id.ll_comment);
        ll_question = findViewById(R.id.ll_question);
        ll_suggestions = findViewById(R.id.ll_suggestions);
    //    iv_profile_img = findViewById(R.id.iv_profile_img);




        rview_friends = findViewById(R.id.rview_friends);
        rview_posts = findViewById(R.id.rview_posts);
        rview_comments = findViewById(R.id.rview_comments);
        rview_questions = findViewById(R.id.rview_questions);
        rview_suggestions = findViewById(R.id.rview_suggestions);

        manager = new LinearLayoutManager(ViewProfileActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_friends.setLayoutManager(manager);
        rview_friends.setVisibility(View.VISIBLE);
        rview_posts.setVisibility(View.GONE);
        rview_comments.setVisibility(View.GONE);
        rview_questions.setVisibility(View.GONE);
        rview_suggestions.setVisibility(View.GONE);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs");
        requestBody.put("user_id", "1");
        presenter.getFriends(ViewProfileActivity.this, this, requestBody);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_view_profile_, null);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_friends:
                rview_friends.setVisibility(View.VISIBLE);
                rview_posts.setVisibility(View.GONE);
                rview_comments.setVisibility(View.GONE);
                rview_questions.setVisibility(View.GONE);
                rview_suggestions.setVisibility(View.GONE);

                manager = new LinearLayoutManager(ViewProfileActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_friends.setLayoutManager(manager);

                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs");
                requestBody.put("user_id", "1");
                presenter.getFriends(ViewProfileActivity.this, this, requestBody);


                break;
            case R.id.ll_post:
                rview_friends.setVisibility(View.GONE);
                rview_posts.setVisibility(View.VISIBLE);
                rview_comments.setVisibility(View.GONE);
                rview_questions.setVisibility(View.GONE);
                rview_suggestions.setVisibility(View.GONE);


                manager = new LinearLayoutManager(ViewProfileActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_posts.setLayoutManager(manager);



                /*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "user_id":"1",
    "count":"0"
}
*/

                Map<String, String> requestBody_post = new HashMap<>();
                requestBody_post.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs");
                requestBody_post.put("user_id", "1");
                requestBody_post.put("count", "0");
                presenter.myActivityPosts(ViewProfileActivity.this, this, requestBody_post);

                break;
            case R.id.ll_comment:
                rview_friends.setVisibility(View.GONE);
                rview_posts.setVisibility(View.GONE);
                rview_comments.setVisibility(View.VISIBLE);
                rview_questions.setVisibility(View.GONE);
                rview_suggestions.setVisibility(View.GONE);

                manager = new LinearLayoutManager(ViewProfileActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_comments.setLayoutManager(manager);


                /*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "user_id":"1",
    "count":"0"
}
*/

                Map<String, String> requestBody_discussions = new HashMap<>();
                requestBody_discussions.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs");
                requestBody_discussions.put("user_id", "1");
                requestBody_discussions.put("count", "0");
                presenter.myPostedDiscussions(ViewProfileActivity.this, this, requestBody_discussions);

                break;
            case R.id.ll_question:
                rview_friends.setVisibility(View.GONE);
                rview_posts.setVisibility(View.GONE);
                rview_comments.setVisibility(View.GONE);
                rview_questions.setVisibility(View.VISIBLE);
                rview_suggestions.setVisibility(View.GONE);

                manager = new LinearLayoutManager(ViewProfileActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_questions.setLayoutManager(manager);



                /*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "user_id":"1",
    "count":"0"
}
*/

                Map<String, String> requestBody_quiz = new HashMap<>();
                requestBody_quiz.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs");
                requestBody_quiz.put("user_id", "1");
                requestBody_quiz.put("count", "0");
                presenter.myActivityPosts(ViewProfileActivity.this, this, requestBody_quiz);

                break;
            case R.id.ll_suggestions:
                rview_friends.setVisibility(View.GONE);
                rview_posts.setVisibility(View.GONE);
                rview_comments.setVisibility(View.GONE);
                rview_questions.setVisibility(View.GONE);
                rview_suggestions.setVisibility(View.VISIBLE);

                manager = new LinearLayoutManager(ViewProfileActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_suggestions.setLayoutManager(manager);


                /*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "user_id":"1",
    "count":"0"
}
*/

                Map<String, String> requestBody_suggestions = new HashMap<>();
                requestBody_suggestions.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs");
                requestBody_suggestions.put("user_id", "1");
                requestBody_suggestions.put("count", "0");
                presenter.myPostedSuggestions(ViewProfileActivity.this, this, requestBody_suggestions);

                break;

        }
    }

    @Override
    public void setPresenter() {
presenter=new ViewProfileActImpl(this,this);
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

            }
//            else if (NetworkConstants.RequestCode.MY_FRIENDS == requestId) {
//                boolean status = jsonObject.getBoolean("status");
//                if (status == true) {
//                    friendsPojo = new Gson().fromJson(responseString, FriendsPojo.class);
//                    myFriendsAdapter = new MyFriendsAdapter(ViewProfileActivity.this, friendsPojo);
//                    rview_friends.setAdapter(myFriendsAdapter);
////
//                }
//            } else if (NetworkConstants.RequestCode.MY_ACTIVITY_POSTS == requestId) {
//                myActivityPostsPojo = new Gson().fromJson(responseString, MyActivityPostsPojo.class);
//                myActivityPostsAdapter = new MyActivityPostsAdapter(ViewProfileActivity.this, myActivityPostsPojo);
//                rview_posts.setAdapter(myActivityPostsAdapter);
//            } else if (NetworkConstants.RequestCode.MY_POSTED_DISCUSSIONS == requestId) {
//                myPostedDiscussionsPojo = new Gson().fromJson(responseString, MyPostedDiscussionsPojo.class);
//                myPostedDiscussionsAdapter = new MyPostedDiscussionsAdapter(ViewProfileActivity.this, myPostedDiscussionsPojo);
//                rview_comments.setAdapter(myPostedDiscussionsAdapter);
//            } else if (NetworkConstants.RequestCode.MY_POSTED_SUGGESTIONS == requestId) {
//                myPostedSuggestionsPojo = new Gson().fromJson(responseString, MyPostedSuggestionsPojo.class);
//                mySuggestionsAdapter = new MySuggestionsAdapter(ViewProfileActivity.this, myPostedSuggestionsPojo);
//                rview_suggestions.setAdapter(mySuggestionsAdapter);
//            } else if (NetworkConstants.RequestCode.MY_QUIZZES == requestId) {
//                myQuizzesPojo = new Gson().fromJson(responseString, MyQuizzesPojo.class);
//                myQuizzesAdapter = new MyQuizzesAdapter(ViewProfileActivity.this, myQuizzesPojo);
//                rview_questions.setAdapter(myQuizzesAdapter);
//            }


        } catch (
                Exception e)

        {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
