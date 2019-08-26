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
import com.iprismtech.studentvarsity.pojos.CommentRepliesPojo;
import com.squareup.picasso.Picasso;

public class ViewAllRepliesAdapter extends RecyclerView.Adapter<ViewAllRepliesAdapter.ViewHolder> {
    private Context context;
    private CommentRepliesPojo viewAllRepliesPojo;

    public ViewAllRepliesAdapter(CommentRepliesPojo viewAllRepliesPojo, Context notesDetailsDescriptionActivity) {
        this.context = notesDetailsDescriptionActivity;
        this.viewAllRepliesPojo = viewAllRepliesPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sub_replies, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // viewHolder.tv_Name.setText(viewAllRepliesPojo.getResponse().get(0).getComment_replies().get(i).getName());
        viewHolder.tv_Name.setText(viewAllRepliesPojo.getResponse().get(i).getName());
        //  viewHolder.tv_uni_name.setText(viewAllRepliesPojo.getResponse().get(0).getComment_replies().get(i).getUniversity());
        viewHolder.tv_uni_name.setText(viewAllRepliesPojo.getResponse().get(i).getUniversity());
        // viewHolder.tv_replies.setText(viewAllRepliesPojo.getResponse().get(0).getComment_replies().get(i).getComment());
        viewHolder.tv_replies.setText(viewAllRepliesPojo.getResponse().get(i).getComment());
        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + viewAllRepliesPojo.getResponse().get(i).getProfile_pic())
                .error(R.drawable.no_image)
                .into(viewHolder.iv_img);

    }

    @Override
    public int getItemCount() {
        return viewAllRepliesPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_Name, tv_uni_name, tv_replies;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_uni_name = itemView.findViewById(R.id.tv_uni_name);
            tv_Name = itemView.findViewById(R.id.tv_Name);
            tv_replies = itemView.findViewById(R.id.tv_replies);
        }
    }
}
