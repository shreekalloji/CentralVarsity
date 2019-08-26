package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.pojos.GroupMembersPojo;
import com.iprismtech.studentvarsity.ui.activity.GroupDetailsActivity;
import com.squareup.picasso.Picasso;

public class GroupMembersAdapter extends RecyclerView.Adapter<GroupMembersAdapter.ViewHolder> {
    private Context context;
    private GroupMembersPojo groupMembersPojo;

    public GroupMembersAdapter(GroupDetailsActivity groupDetailsActivity, GroupMembersPojo groupMembersPojo) {
        this.context = groupDetailsActivity;
        this.groupMembersPojo = groupMembersPojo;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_group, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_mem_name.setText(groupMembersPojo.getResponse().get(0).getMembers().get(i).getName());
        viewHolder.tv_mem_uni.setText(groupMembersPojo.getResponse().get(0).getMembers().get(i).getUniversity());


        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + groupMembersPojo.getResponse().get(0).getMembers().get(i).getProfile_pic())
                .error(R.drawable.no_image)
                .into(viewHolder.ic_mem_img);

    }

    @Override
    public int getItemCount() {
        return groupMembersPojo.getResponse().get(0).getMembers().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ic_mem_img;
        TextView tv_mem_name, tv_mem_uni;
        LinearLayout ll_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ic_mem_img = itemView.findViewById(R.id.ic_mem_img);
            tv_mem_name = itemView.findViewById(R.id.tv_mem_name);
            tv_mem_uni = itemView.findViewById(R.id.tv_mem_uni);
            ll_item = itemView.findViewById(R.id.ll_item);
            ll_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }

        }
    }
}
