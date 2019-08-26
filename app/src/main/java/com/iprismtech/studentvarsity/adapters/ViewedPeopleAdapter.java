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
import com.iprismtech.studentvarsity.pojos.AllViewedPeoplePojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewedPeopleAdapter extends RecyclerView.Adapter<ViewedPeopleAdapter.ViewHolder> {
    private Context context;
  //  private List<ActivityDetailsPojo.ViewsBean> views;
    private List<AllViewedPeoplePojo.ViewsBean> views;
    //private AllViewedPeoplePojo views;

//    public ViewedPeopleAdapter(ActivityDetailsActvity activityDetailsActvity, List<ActivityDetailsPojo.ViewsBean> views) {
//        this.context = activityDetailsActvity;
//        this.views = views;
//
//    }

    public ViewedPeopleAdapter(Context activityDetailsActvity, List<AllViewedPeoplePojo.ViewsBean> allViewedPeoplePojo) {
        this.context = activityDetailsActvity;
        this.views = allViewedPeoplePojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewed_people, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + views.get(i).getProfile_pic())
                .error(R.drawable.no_image)
                .into(viewHolder.iv_person);

        if (views.get(i).getOnline_status().equalsIgnoreCase("Offline")) {
            viewHolder.iv_online_status.setVisibility(View.GONE);
            viewHolder.iv_offlie.setVisibility(View.VISIBLE);
        } else {
            viewHolder.iv_online_status.setVisibility(View.VISIBLE);
            viewHolder.iv_offlie.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (views.size() > 6) {
            return 6;
        } else {
            return views.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_person, iv_online_status, iv_offlie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_person = itemView.findViewById(R.id.iv_person);
            iv_online_status = itemView.findViewById(R.id.iv_online_status);
            iv_offlie = itemView.findViewById(R.id.iv_offlie);
        }
    }
}
