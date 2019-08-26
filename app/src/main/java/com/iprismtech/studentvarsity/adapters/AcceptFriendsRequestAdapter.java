package com.iprismtech.studentvarsity.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.FriendRequestsPojo;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AcceptFriendsRequestAdapter extends RecyclerView.Adapter<AcceptFriendsRequestAdapter.StreamAdapterView> {

    LayoutInflater inflater;
    Context context;
    FriendRequestsPojo cartPojo;
    public static ArrayList<String> friendscount;
    ArrayList<FriendRequestsPojo.ResponseBean> filteredList;

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    public AcceptFriendsRequestAdapter(FriendRequestsPojo cartPojo, Context context, ArrayList<String> friendscount) {
        this.context = context;
        this.cartPojo = cartPojo;
        inflater = LayoutInflater.from(context);
        filteredList = new ArrayList<FriendRequestsPojo.ResponseBean>();
        this.filteredList.addAll(this.cartPojo.getResponse());
        this.friendscount = new ArrayList<>();
        this.friendscount = friendscount;
    }

    public void filter(final String text) {

        // Searching could be complex..so we will dispatch it to a different thread...
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Clear the filter list
                filteredList.clear();

                // If there is no search value, then add all original list items to filter list
                if (TextUtils.isEmpty(text)) {

                    filteredList.addAll(cartPojo.getResponse());

                } else {
                    // Iterate in the original List and add it to filter list...
                    for (FriendRequestsPojo.ResponseBean item : cartPojo.getResponse()) {
                        if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                            // Adding Matched items
                            filteredList.add(item);
                        }
                    }
                }

                // Set on UI Thread
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });

            }
        }).start();

    }

    @Override
    public StreamAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_get_users_contacts, parent, false);
        return new StreamAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(StreamAdapterView holder, int position) {

       /* Glide.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + cartPojo.getResponse().get(position).getImage())
                .into(holder.productimageiv);*/

        if (cartPojo.getResponse().get(position).isFriend()) {
            holder.tv_add.setVisibility(View.GONE);
            holder.ll_added.setVisibility(View.VISIBLE);

        } else {
            //android:background="@drawable/courseyeartext_background"

            holder.tv_add.setVisibility(View.VISIBLE);
            holder.ll_added.setVisibility(View.GONE);
        }

        holder.tv_name.setText(filteredList.get(position).getName());
        holder.tv_university.setText(filteredList.get(position).getUniversity());


    }


    @Override
    public int getItemCount() {
        // return cartPojo.getResponse().size();
        return (null != filteredList ? filteredList.size() : 0);
    }

    public class StreamAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name, tv_university, tv_add;
        CircleImageView img_prof;
        LinearLayout ll_added;

        public StreamAdapterView(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_university = itemView.findViewById(R.id.tv_university);
            tv_add = itemView.findViewById(R.id.tv_add);
            ll_added = itemView.findViewById(R.id.ll_added);
            tv_name.setOnClickListener(this);
            tv_university.setOnClickListener(this);
            tv_add.setOnClickListener(this);
            //ll_added.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }

        }
    }
}