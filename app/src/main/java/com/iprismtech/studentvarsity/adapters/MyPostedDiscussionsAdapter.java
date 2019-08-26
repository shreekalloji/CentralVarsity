package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.pojos.MyPostedDiscussionsPojo;
import com.iprismtech.studentvarsity.ui.activity.ViewProfile_Activity;
import com.squareup.picasso.Picasso;

public class MyPostedDiscussionsAdapter extends RecyclerView.Adapter<MyPostedDiscussionsAdapter.ViewHolder> {
    private Context context;
    private MyPostedDiscussionsPojo myPostedDiscussionsPojo;
    private String profile_pic;

    public MyPostedDiscussionsAdapter(ViewProfile_Activity viewProfileActivity, MyPostedDiscussionsPojo myPostedDiscussionsPojo, String user_pic) {
        this.context = viewProfileActivity;
        this.myPostedDiscussionsPojo = myPostedDiscussionsPojo;
        this.profile_pic=user_pic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_discussions, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_my_discussion.setText(myPostedDiscussionsPojo.getResponse().get(i).getDescription());
        viewHolder.tv_title.setText(myPostedDiscussionsPojo.getResponse().get(i).getSubject());
        viewHolder.tv_sub_topic.setText(myPostedDiscussionsPojo.getResponse().get(i).getChapter());
        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + profile_pic)
                .error(R.drawable.no_image)
                .into(viewHolder.iv_image);

    }

    @Override
    public int getItemCount() {
        return myPostedDiscussionsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_my_discussion, tv_title, tv_sub_topic;
        ImageView iv_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_my_discussion = itemView.findViewById(R.id.tv_my_discussion);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_sub_topic = itemView.findViewById(R.id.tv_sub_topic);
            iv_image = itemView.findViewById(R.id.iv_image);

        }
    }
}
