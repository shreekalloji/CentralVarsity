package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.ChaptersPojo;
import com.iprismtech.studentvarsity.pojos.DiscussionsPojo;

import java.util.List;

public class DiscussionsAdapter extends RecyclerView.Adapter<DiscussionsAdapter.ViewHolder> {
    private Context context;
    private List<DiscussionsPojo.ResponseBean> discussionsPojo;

    public DiscussionsAdapter(List<DiscussionsPojo.ResponseBean> discussionsPojo, Context activity) {
        this.context = activity;
        this.discussionsPojo = discussionsPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_discuss, viewGroup, false);
        return new ViewHolder(view);
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_discuss_chapter_name.setText(discussionsPojo.get(i).getChapter());
        viewHolder.tv_discuss_comments_count.setText(discussionsPojo.get(i).getComments() + " Comments");
        viewHolder.tv_discuss_pings_count.setText("Pings");
        viewHolder.tv_discuss_view_count.setText(discussionsPojo.get(i).getViews() + " Views");
        viewHolder.tv_discuss_user_post.setText(discussionsPojo.get(i).getDescription());
        viewHolder.tv_first_char.setText(discussionsPojo.get(i).getName().charAt(0) + "");
        viewHolder.tv_posted_on.setText(discussionsPojo.get(i).getPosted_on() + " - ");
        viewHolder.tv_discuss_user_name.setText(discussionsPojo.get(i).getName());


        if (discussionsPojo.get(i).getReaded().equalsIgnoreCase("yes")) {
            viewHolder.ll_discuss_unread.setVisibility(View.GONE);
            viewHolder.ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ll_discuss_unread.setVisibility(View.VISIBLE);
            viewHolder.ll_unread_blue.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return discussionsPojo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_first_char, tv_discuss_chapter_name, tv_discuss_user_post, tv_discuss_user_name, tv_posted_on, tv_discuss_pings_count, tv_discuss_comments_count, tv_discuss_view_count;
        LinearLayout ll_discuss_ping, ll_discuss_comment, ll_discuss_unread, ll_topiv_discuss, ll_unread_blue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_first_char = itemView.findViewById(R.id.tv_first_char);
            tv_discuss_chapter_name = itemView.findViewById(R.id.tv_discuss_chapter_name);
            tv_discuss_user_post = itemView.findViewById(R.id.tv_discuss_user_post);
            tv_discuss_pings_count = itemView.findViewById(R.id.tv_discuss_pings_count);
            tv_discuss_comments_count = itemView.findViewById(R.id.tv_discuss_comments_count);
            tv_discuss_view_count = itemView.findViewById(R.id.tv_discuss_view_count);
            tv_posted_on = itemView.findViewById(R.id.tv_posted_on);
            ll_discuss_comment = itemView.findViewById(R.id.ll_discuss_comment);
            ll_discuss_ping = itemView.findViewById(R.id.ll_discuss_ping);
            ll_discuss_unread = itemView.findViewById(R.id.ll_discuss_unread);
            ll_topiv_discuss = itemView.findViewById(R.id.ll_topiv_discuss);
            tv_discuss_user_name = itemView.findViewById(R.id.tv_discuss_user_name);
            ll_unread_blue = itemView.findViewById(R.id.ll_unread_blue);

            ll_discuss_comment.setOnClickListener(this);
            ll_discuss_ping.setOnClickListener(this);
            ll_discuss_unread.setOnClickListener(this);
            ll_topiv_discuss.setOnClickListener(this);
            tv_discuss_comments_count.setOnClickListener(this);
            ll_discuss_ping.setOnClickListener(this);
            tv_discuss_user_post.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
