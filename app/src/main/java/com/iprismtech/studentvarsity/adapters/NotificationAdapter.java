package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.NotificationsPojo;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private Context context;
    private NotificationsPojo notificationsPojo;

    public NotificationAdapter(FragmentActivity activity, NotificationsPojo notificationsPojo) {
        this.context = activity;
        this.notificationsPojo = notificationsPojo;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, viewGroup, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_first_char.setText(notificationsPojo.getResponse().get(i).getName().charAt(0) + "");
        String text = notificationsPojo.getResponse().get(i).getName() + " " + "<font color=#808080>" + notificationsPojo.getResponse().get(i).getMessage() + "</font>";
        viewHolder.tv_noti_user_name.setText(Html.fromHtml(text));
        viewHolder.tv_posted_on.setText(notificationsPojo.getResponse().get(i).getPosted_on());
    }

    @Override
    public int getItemCount() {
        return notificationsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_first_char, tv_noti_user_name, tv_posted_on;
        LinearLayout ll_item_noti;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_first_char = itemView.findViewById(R.id.tv_first_char);
            tv_noti_user_name = itemView.findViewById(R.id.tv_noti_user_name);
            ll_item_noti = itemView.findViewById(R.id.ll_item_noti);

            tv_posted_on = itemView.findViewById(R.id.tv_posted_on);
            ll_item_noti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListner != null) {
                        mListner.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

    }
}
