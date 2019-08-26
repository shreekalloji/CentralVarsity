package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.pojos.PingGroupsListPojo;
import com.iprismtech.studentvarsity.ui.activity.PingFriendsGroup;
import com.squareup.picasso.Picasso;

public class PingGroupsListAdapter extends RecyclerView.Adapter<PingGroupsListAdapter.ViewHolder> {
    private Context context;
    private PingGroupsListPojo pingGroupsListPojo;

    public PingGroupsListAdapter(PingFriendsGroup pingFriendsGroup, PingGroupsListPojo pingGroupsListPojo) {
        this.context = pingFriendsGroup;
        this.pingGroupsListPojo = pingGroupsListPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ping_groups, viewGroup, false);
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
        viewHolder.tv_grp_name.setText(pingGroupsListPojo.getResponse().get(i).getTitle());
        viewHolder.tv_numbers.setText(pingGroupsListPojo.getResponse().get(i).getGroup_members() + " Members");
//        Picasso.with(context)
//                .load(NetworkConstants.URL.Imagepath_URL + pingGroupsListPojo.getResponse().get(i).get())
//                .error(R.drawable.no_image)
//                .into(viewHolder.profile_pic);

       // viewHolder.ll_item_grp.setBackgroundColor(Color.parseColor("#" + pingGroupsListPojo.getResponse().get(i).getColor_code()));
       // viewHolder.ll_item_grp.setBackgroundColor(ContextCompat.getColor(context,Color.parseColor("#" + pingGroupsListPojo.getResponse().get(i).getColor_code())));
        String colorcode="#"+pingGroupsListPojo.getResponse().get(i).getColor_code();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor(colorcode));
        Picasso.with(context).load(NetworkConstants.URL.BASE_URL).placeholder(colorDrawable).into(viewHolder.ll_item_grp);
    }

    @Override
    public int getItemCount() {
        return pingGroupsListPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ll_item_grp;
        ImageView iv_group_icon;
        TextView tv_grp_name, tv_numbers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_item_grp = itemView.findViewById(R.id.ll_item_grp);
           // iv_group_icon = itemView.findViewById(R.id.iv_group_icon);
            tv_grp_name = itemView.findViewById(R.id.tv_grp_name);
            tv_numbers = itemView.findViewById(R.id.tv_numbers);
            ll_item_grp.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
