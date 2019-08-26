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
import com.iprismtech.studentvarsity.pojos.AllCommentsPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PaginationCommentsAdapter extends RecyclerView.Adapter<PaginationCommentsAdapter.ViewHolder> {

    List<AllCommentsPojo.ResponseBean> comments;
    List<AllCommentsPojo.ResponseBean> comments_temp;
    private Context context;


    public PaginationCommentsAdapter(List<AllCommentsPojo.ResponseBean> responseBeans, Context activityDetailsActvity) {
        this.comments = responseBeans;
        this.context = activityDetailsActvity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comments_notes_view, viewGroup, false);
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
        viewHolder.tv_comment.setText(comments.get(i).getComment());
        viewHolder.tv_time.setText(comments.get(i).getPosted_on());
        viewHolder.tv_Name.setText(comments.get(i).getName());
        viewHolder.tv_uni_name.setText(comments.get(i).getUniversity());
        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + comments.get(i).getProfile_pic())
                .error(R.drawable.no_image)
                .into(viewHolder.profile_pic);
        if (comments.get(i).getImage() != null) {


            Picasso.with(context)
                    .load(NetworkConstants.URL.Imagepath_URL + comments.get(i).getImage())
                    .into(viewHolder.iv_posted_img);

//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            lp.setMargins(70, 0, 70, 0);
//            viewHolder.iv_posted_img.setLayoutParams(lp);

//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            lp.setMargins(120, 20, 120, 20);
//            viewHolder.iv_posted_img.setLayoutParams(lp);
            //viewHolder.iv_posted_img.setPadding(100, 0, 100, 0);
        } else {
            viewHolder.iv_posted_img.setVisibility(View.GONE);
        }
        if (comments.get(i).getComment_replies().size() > 0) {
            viewHolder.tv_replies_txt.setVisibility(View.VISIBLE);
            viewHolder.tv_all_replies.setVisibility(View.VISIBLE);
            // viewHolder.rview_sub_comments.setVisibility(View.VISIBLE);
            SubReplyAdapter itemListDataAdapter = new SubReplyAdapter(context, comments.get(i).getComment_replies());
            viewHolder.rview_sub_comments.setHasFixedSize(true);
            viewHolder.rview_sub_comments.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            viewHolder.rview_sub_comments.setAdapter(itemListDataAdapter);

        } else {
            viewHolder.tv_replies_txt.setVisibility(View.GONE);
            viewHolder.tv_all_replies.setVisibility(View.GONE);
            //   viewHolder.rview_sub_comments.setVisibility(View.GONE);

        }
    }

    public void add(List<AllCommentsPojo.ResponseBean> mc) {
        for (AllCommentsPojo.ResponseBean pojo : mc) {
            comments.add(pojo);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView tv_img, profile_pic, iv_posted_img;
        TextView tv_Name, tv_uni_name, tv_time, tv_comment, tv_all_replies, tv_replies_txt;
        LinearLayout ll_ping_sub, ll_reply;
        RecyclerView rview_sub_comments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_Name);
            tv_uni_name = itemView.findViewById(R.id.tv_uni_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            profile_pic = itemView.findViewById(R.id.tv_img);
            iv_posted_img = itemView.findViewById(R.id.iv_posted_img);
            //ll_ping_sub = itemView.findViewById(R.id.ll_ping_sub);
            ll_reply = itemView.findViewById(R.id.ll_reply);
            tv_all_replies = itemView.findViewById(R.id.tv_all_replies);
            rview_sub_comments = itemView.findViewById(R.id.rview_sub_comments);
            tv_replies_txt = itemView.findViewById(R.id.tv_replies_txt);
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
