package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.ActivityPojo;

import java.util.List;

public class ActivityMCQsAdapter extends RecyclerView.Adapter<ActivityMCQsAdapter.ViewHolder> {
    private Context context;
    private List<ActivityPojo.ResponseBean.McqOptionsBean> options;

    public ActivityMCQsAdapter(Context context, List<ActivityPojo.ResponseBean.McqOptionsBean> mcq_options, String answer, TextView mcqs_tv_ans) {
        this.context = context;
        this.options = mcq_options;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mcq_option, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_option.setText(options.get(i).getOptions());
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class
    ViewHolder extends RecyclerView.ViewHolder {
        RadioButton rb_option;
        TextView tv_option, tv_ans;
        LinearLayout ll_option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rb_option = itemView.findViewById(R.id.rb_option);
           // tv_option = itemView.findViewById(R.id.tv_option);
            ll_option = itemView.findViewById(R.id.ll_option);
            tv_option = itemView.findViewById(R.id.tv_option);

        }
    }
}
