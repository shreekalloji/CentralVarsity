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
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.pojos.FriendsPojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupAdapter extends RecyclerView.Adapter<CreateGroupAdapter.ViewHolder> implements Filterable {
    private Context context;
    private FriendsPojo friendsPojo;
    private List<FriendsPojo.ResponseBean> friendsList;
    private List<FriendsPojo.ResponseBean> temp;


    public CreateGroupAdapter(Context newGroupActivity, List<FriendsPojo.ResponseBean> friendsPojo) {
        this.context = newGroupActivity;
        this.friendsList = friendsPojo;
        this.temp = friendsPojo;
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
        final FriendsPojo.ResponseBean model = friendsList.get(i);
        viewHolder.tv_name.setText(friendsList.get(i).getName());
        viewHolder.tv_uni_name.setText(friendsList.get(i).getUniversity());
        Picasso.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + friendsList.get(i).getProfile_pic())
                .error(R.drawable.no_image)
                .into(viewHolder.iv_img);


        if (friendsList.get(i).isClicked()) {
            viewHolder.ic_radio.setBackgroundResource(R.drawable.ic_circle_image_selected);
        }
        else {
            viewHolder.ic_radio.setBackgroundResource(R.drawable.ic_circle_image);
        }

        viewHolder.ic_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setClicked(!model.isClicked());
                if (!friendsList.get(i).isClicked()) {
//                        list.get(i).setClicked(true);
                    viewHolder.ic_radio.setBackgroundResource(R.drawable.ic_circle_image_selected);


                } else if (friendsList.get(i).isClicked()) {
//                        list.get(i).setClicked(false);
                    viewHolder.ic_radio.setBackgroundResource(R.drawable.ic_circle_image);

                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }



    public ArrayList<FriendsPojo.ResponseBean> getselectedList() {
        ArrayList<FriendsPojo.ResponseBean> newSelctedList = new ArrayList<>();
        for (int y = 0; y < friendsList.size(); y++) {
            if (friendsList.get(y).isClicked()) {
                newSelctedList.add(friendsList.get(y));
            }
        }
        return newSelctedList;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    friendsList = temp;

                } else {


                    ArrayList<FriendsPojo.ResponseBean> filteredList = new ArrayList<>();
                    for (FriendsPojo.ResponseBean row : temp) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName() != null) {
                            if (row.getName().toLowerCase().contains(charString.toLowerCase())) {

                                filteredList.add(row);
                            }
                        }
                    }

                    friendsList = filteredList;

                }
                FilterResults results = new FilterResults();
                results.values = friendsList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                friendsList = (ArrayList<FriendsPojo.ResponseBean>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ic_radio, iv_img;
        TextView tv_uni_name, tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ic_radio = itemView.findViewById(R.id.ic_radio);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_uni_name = itemView.findViewById(R.id.tv_uni_name);
            tv_name = itemView.findViewById(R.id.tv_name);
            // ic_radio.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
