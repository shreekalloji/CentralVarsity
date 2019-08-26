package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.customviews.CustomTextViewNormal;
import com.iprismtech.studentvarsity.pojos.MyPostedSuggestionsPojo;
import com.iprismtech.studentvarsity.ui.activity.ViewProfile_Activity;

public class MySuggestionsAdapter extends RecyclerView.Adapter<MySuggestionsAdapter.ViewHolder> {
    private Context context;
    private MyPostedSuggestionsPojo myPostedSuggestionsPojo;

    public MySuggestionsAdapter(ViewProfile_Activity viewProfileActivity, MyPostedSuggestionsPojo myPostedSuggestionsPojo) {
        this.context = viewProfileActivity;
        this.myPostedSuggestionsPojo = myPostedSuggestionsPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_suggestions, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_st.setText(myPostedSuggestionsPojo.getResponse().get(i).getSuggestion_type());
        viewHolder.tv_wk.setText(myPostedSuggestionsPojo.getResponse().get(i).getExam_type());
        viewHolder.tv_examname.setText(myPostedSuggestionsPojo.getResponse().get(i).getExam_name());
        viewHolder.tv_confidence.setText(myPostedSuggestionsPojo.getResponse().get(i).getConfidence());
        viewHolder.tv_country.setText(myPostedSuggestionsPojo.getResponse().get(i).getCountry());
        viewHolder.tv_desc.setText(myPostedSuggestionsPojo.getResponse().get(i).getSuggestion());
    }

    @Override
    public int getItemCount() {
        return myPostedSuggestionsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CustomTextViewNormal tv_st, tv_wk, tv_examname, tv_confidence, tv_country, tv_desc;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_st = itemView.findViewById(R.id.tv_st);
            tv_wk = itemView.findViewById(R.id.tv_wk);
            tv_examname = itemView.findViewById(R.id.tv_examname);
            tv_confidence = itemView.findViewById(R.id.tv_confidence);
            tv_country = itemView.findViewById(R.id.tv_country);
            tv_desc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
