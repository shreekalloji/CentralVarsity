package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.PingGroupsListPojo;
import com.iprismtech.studentvarsity.ui.activity.PingToFriendsGroupActivity;

import java.util.ArrayList;
import java.util.List;

public class PingToGroupAdapter extends RecyclerView.Adapter<PingToGroupAdapter.ViewHolder> implements Filterable {
    private Context context;

    private List<PingGroupsListPojo.ResponseBean> groupssList;
    private List<PingGroupsListPojo.ResponseBean> temp;

    public PingToGroupAdapter(PingToFriendsGroupActivity pingToFriendsGroupActivity, List<PingGroupsListPojo.ResponseBean> groupssList) {
        this.context = pingToFriendsGroupActivity;
        this.groupssList = groupssList;
        this.temp = groupssList;
    }


    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
        // void onItemClickEvent(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_newgroup, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv_uni_name.setText(groupssList.get(i).getTitle());
        viewHolder.tv_uni_numbers.setText(groupssList.get(i).getGroup_members().length() + " Members");
//        Picasso.with(context)
//                .load(NetworkConstants.URL.Imagepath_URL + groupssList.get(i).get())
//                .error(R.drawable.no_image)
//                .into(viewHolder.iv_img);
        final PingGroupsListPojo.ResponseBean model = groupssList.get(i);
        if (groupssList.get(i).isClicked()) {
            viewHolder.ic_radio.setBackgroundResource(R.drawable.ic_circle_image_selected);
        } else {
            viewHolder.ic_radio.setBackgroundResource(R.drawable.ic_circle_image);
        }
        viewHolder.ic_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setClicked(!model.isClicked());
                if (!groupssList.get(i).isClicked()) {
//                        list.get(i).setClicked(true);
                    viewHolder.ic_radio.setBackgroundResource(R.drawable.ic_circle_image_selected);


                } else if (groupssList.get(i).isClicked()) {
//                        list.get(i).setClicked(false);
                    viewHolder.ic_radio.setBackgroundResource(R.drawable.ic_circle_image);

                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupssList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    groupssList = temp;

                } else {


                    ArrayList<PingGroupsListPojo.ResponseBean> filteredList = new ArrayList<>();
                    for (PingGroupsListPojo.ResponseBean row : temp) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle() != null) {
                            if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {

                                filteredList.add(row);
                            }
                        }
                    }

                    groupssList = filteredList;

                }
                FilterResults results = new FilterResults();
                results.values = groupssList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                groupssList = (ArrayList<PingGroupsListPojo.ResponseBean>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public ArrayList<PingGroupsListPojo.ResponseBean> getselectedList() {
        ArrayList<PingGroupsListPojo.ResponseBean> newSelctedList = new ArrayList<>();
        for (int y = 0; y < groupssList.size(); y++) {
            if (groupssList.get(y).isClicked()) {
                newSelctedList.add(groupssList.get(y));
            }
        }
        return newSelctedList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ic_radio, iv_img;
        TextView tv_uni_name, tv_uni_numbers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ic_radio = itemView.findViewById(R.id.ic_radio);
            iv_img = itemView.findViewById(R.id.iv_img);


            // id's are correct only , bcoz used same layput of new group

            tv_uni_numbers = itemView.findViewById(R.id.tv_uni_name);
            tv_uni_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
