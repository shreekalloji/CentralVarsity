package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.MCQsDetailsPojo;
import com.iprismtech.studentvarsity.ui.activity.MCQsQuestionDetailActivity;

import java.util.List;

public class MCQsOptionsAdapter extends RecyclerView.Adapter<MCQsOptionsAdapter.ViewHolder> {
    private Context context;
    private List<MCQsDetailsPojo.ResponseBean.McqOptionsBean> mcq_options;


    public MCQsOptionsAdapter(MCQsQuestionDetailActivity mcQsQuestionDetailActivity, List<MCQsDetailsPojo.ResponseBean.McqOptionsBean> mcq_options) {
        this.context = mcQsQuestionDetailActivity;
        this.mcq_options = mcq_options;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_options, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MCQsOptionsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_option.setText(mcq_options.get(i).getOptions());
    }


    @Override
    public int getItemCount() {
        return mcq_options.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_option = itemView.findViewById(R.id.tv_option);
        }
    }
}
