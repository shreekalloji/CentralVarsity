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
import com.iprismtech.studentvarsity.pojos.FriendsPojo;
import com.iprismtech.studentvarsity.ui.activity.ViewProfile_Activity;
import com.squareup.picasso.Picasso;

public class MyFriendsAdapter extends RecyclerView.Adapter<MyFriendsAdapter.ViewHolder> {
    private Context context;
    private FriendsPojo friendsPojo;

    public MyFriendsAdapter(ViewProfile_Activity viewProfileActivity, FriendsPojo friendsPojo) {
        this.context = viewProfileActivity;
        this.friendsPojo = friendsPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friends, viewGroup, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_frd_name.setText(friendsPojo.getResponse().get(i).getName());
        viewHolder.tv_frd_uni.setText(friendsPojo.getResponse().get(i).getUniversity());
        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + friendsPojo.getResponse().get(i).getProfile_pic())
                .error(R.drawable.no_image)
                .into(viewHolder.iv_frd_img);
    }

    @Override
    public int getItemCount() {
        return friendsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_frd_img;
        TextView tv_frd_name, tv_frd_uni;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_frd_img = itemView.findViewById(R.id.iv_frd_img);
            tv_frd_name = itemView.findViewById(R.id.tv_frd_name);
            tv_frd_uni = itemView.findViewById(R.id.tv_frd_uni);
        }
    }
}
