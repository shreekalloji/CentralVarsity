package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.GetusersfromcontactsPOJO;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    List<GetusersfromcontactsPOJO.ResponseBean> arrayList;
    Context context;

    public FriendsAdapter(List<GetusersfromcontactsPOJO.ResponseBean> arrayList, Context connectMyFriendsActivity) {
        this.arrayList = arrayList;
        this.context = connectMyFriendsActivity;
    }

    @NonNull
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_get_users_contacts, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(arrayList.get(i).getName());
        viewHolder.tv_university.setText(arrayList.get(i).getUniversity());
        viewHolder.tv_add.setVisibility(View.GONE);
        viewHolder.ll_added.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_university, tv_add;
        CircleImageView img_prof;
        LinearLayout ll_added;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_university = itemView.findViewById(R.id.tv_university);
            tv_add = itemView.findViewById(R.id.tv_add);
            ll_added = itemView.findViewById(R.id.ll_added);
        }
    }
}
