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
import com.iprismtech.studentvarsity.pojos.FaqsDetailPOJO;
import com.iprismtech.studentvarsity.ui.activity.FaqDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FAQsCommentsAdapter extends RecyclerView.Adapter<FAQsCommentsAdapter.ViewHolder> {
    private Context context;
    private List<FaqsDetailPOJO.CommentsBean> comments;

    private OnitemClickListener mListner;

    public FAQsCommentsAdapter(FaqDetailActivity faqDetailActivity, List<FaqsDetailPOJO.CommentsBean> comments) {
        this.context = faqDetailActivity;
        this.comments = comments;
    }

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
        FAQSubReplyAdapter itemListDataAdapter = new FAQSubReplyAdapter(context, comments.get(i).getComment_replies());
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
        LinearLayout ll_reply;
        RecyclerView rview_sub_comments;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_img = itemView.findViewById(R.id.tv_img);
            tv_Name = itemView.findViewById(R.id.tv_Name);
            tv_uni_name = itemView.findViewById(R.id.tv_uni_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            profile_pic = itemView.findViewById(R.id.tv_img);
            iv_posted_img = itemView.findViewById(R.id.iv_posted_img);
            ll_reply = itemView.findViewById(R.id.ll_reply);
            tv_all_replies = itemView.findViewById(R.id.tv_all_replies);
            rview_sub_comments = itemView.findViewById(R.id.rview_sub_comments);
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
