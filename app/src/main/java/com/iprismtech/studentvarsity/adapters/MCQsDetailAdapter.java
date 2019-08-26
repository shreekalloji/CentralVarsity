package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.pojos.MCQsDetailsPojo;
import com.iprismtech.studentvarsity.ui.activity.MCQsQuestionDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MCQsDetailAdapter extends RecyclerView.Adapter<MCQsDetailAdapter.ViewHolder> {
    private Context context;
    private List<MCQsDetailsPojo.CommentsBean> comments;

    public MCQsDetailAdapter(MCQsQuestionDetailActivity mcQsQuestionDetailActivity, List<MCQsDetailsPojo.CommentsBean> comments) {
        this.context = mcQsQuestionDetailActivity;
        this.comments = comments;
    }


    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comments_notes_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_comment.setText(comments.get(i).getComment());
        viewHolder.tv_time.setText(comments.get(i).getPosted_on());
        viewHolder.tv_Name.setText(comments.get(i).getName());
        viewHolder.tv_uni_name.setText(comments.get(i).getUniversity());
        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + comments.get(i).getProfile_pic())
                .error(R.drawable.no_image)
                .into(viewHolder.profile_pic);

        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + comments.get(i).getImage())
                .into(viewHolder.iv_posted_img);

        MCQsSubReplyAdapter itemListDataAdapter = new MCQsSubReplyAdapter(context, comments.get(i).getComment_replies());
        viewHolder.rview_sub_comments.setHasFixedSize(true);
        viewHolder.rview_sub_comments.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        viewHolder.rview_sub_comments.setAdapter(itemListDataAdapter);

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView tv_img, profile_pic, iv_posted_img;
        TextView tv_Name, tv_uni_name, tv_time, tv_comment, tv_all_replies;
        RecyclerView rview_sub_comments;
        LinearLayout ll_ping_sub, ll_reply;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_Name);
            tv_uni_name = itemView.findViewById(R.id.tv_uni_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            profile_pic = itemView.findViewById(R.id.tv_img);
            iv_posted_img = itemView.findViewById(R.id.iv_posted_img);
            rview_sub_comments = itemView.findViewById(R.id.rview_sub_comments);
            tv_all_replies = itemView.findViewById(R.id.tv_all_replies);
            ll_reply = itemView.findViewById(R.id.ll_reply);
            tv_all_replies.setOnClickListener(this);
            ll_reply.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
