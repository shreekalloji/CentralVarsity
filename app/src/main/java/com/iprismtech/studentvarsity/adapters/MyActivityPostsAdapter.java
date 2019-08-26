package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.MyActivityPostsPojo;
import com.iprismtech.studentvarsity.ui.activity.ViewProfile_Activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyActivityPostsAdapter extends RecyclerView.Adapter<MyActivityPostsAdapter.ViewHolder> {
    private Context context;
    MyActivityPostsPojo myActivityPostsPojo;

    public MyActivityPostsAdapter(ViewProfile_Activity viewProfileActivity, MyActivityPostsPojo myActivityPostsPojo) {
        this.context = viewProfileActivity;
        this.myActivityPostsPojo = myActivityPostsPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_posts, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv_my_posts.setText(myActivityPostsPojo.getResponse().get(i).getDescription());
        viewHolder.tv_sub_topic.setText(myActivityPostsPojo.getResponse().get(i).getChapter());
        viewHolder.tv_title.setText(myActivityPostsPojo.getResponse().get(i).getSubject());


    }

    @Override
    public int getItemCount() {
        return myActivityPostsPojo.getResponse().size();
    }

    public static String extractYoutubeVideoId(String ytUrl) {

        String vId = null;

        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(ytUrl);

        if (matcher.find()) {
            vId = matcher.group();
        }
        return vId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_my_posts, tv_sub_topic, tv_title;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_my_posts = itemView.findViewById(R.id.tv_my_posts);
            tv_sub_topic = itemView.findViewById(R.id.tv_sub_topic);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
