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
import com.iprismtech.studentvarsity.pojos.VideosDetailPOJO;
import com.iprismtech.studentvarsity.ui.activity.VideoDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoCommentsAdapter extends RecyclerView.Adapter<VideoCommentsAdapter.ViewHolder> {
    private Context context;
    private List<VideosDetailPOJO.CommentsBean> comments;
    private OnitemClickListener mListner;

    public VideoCommentsAdapter(VideoDetailActivity videoDetailActivity, List<VideosDetailPOJO.CommentsBean> comments) {
        this.context = videoDetailActivity;
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
                .error(R.drawable.no_image)
                .into(viewHolder.iv_posted_img);

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tv_img, profile_pic, iv_posted_img;
        TextView tv_Name, tv_uni_name, tv_time, tv_comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_img = itemView.findViewById(R.id.tv_img);
            tv_Name = itemView.findViewById(R.id.tv_Name);
            tv_uni_name = itemView.findViewById(R.id.tv_uni_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            profile_pic = itemView.findViewById(R.id.tv_img);
            iv_posted_img = itemView.findViewById(R.id.iv_posted_img);
        }
    }
}
