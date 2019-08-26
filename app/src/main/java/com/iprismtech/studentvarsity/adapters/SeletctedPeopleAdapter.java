package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.pojos.FriendsPojo;
import com.iprismtech.studentvarsity.ui.activity.NewGroupActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SeletctedPeopleAdapter extends RecyclerView.Adapter<SeletctedPeopleAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FriendsPojo.ResponseBean> selectedList;

    public SeletctedPeopleAdapter(NewGroupActivity newGroupActivity, ArrayList<FriendsPojo.ResponseBean> selectedList) {
        this.selectedList = selectedList;
        this.context = newGroupActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seleted_people, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + selectedList.get(i).getProfile_pic())
                .error(R.drawable.no_image)
                .into(viewHolder.ic_selected_image);
    }

    @Override
    public int getItemCount() {
        return selectedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ic_selected_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ic_selected_image = itemView.findViewById(R.id.ic_selected_image);
        }
    }
}
