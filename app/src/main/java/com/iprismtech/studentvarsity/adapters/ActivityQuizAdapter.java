package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.ActivityPojo;

import java.util.List;

public class ActivityQuizAdapter extends RecyclerView.Adapter<ActivityQuizAdapter.ViewHolder> {
    private Context context;
    private List<ActivityPojo.ResponseBean.QuizOptionsBean> quiz_options;

    public ActivityQuizAdapter(Context context, List<ActivityPojo.ResponseBean.QuizOptionsBean> quiz_options) {
        this.context = context;
        this.quiz_options = quiz_options;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activty_quiz, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_opt1.setText(quiz_options.get(i).getOptions());
        viewHolder.tv_number.setText(i + 1 + ". ");
    }

    @Override
    public int getItemCount() {
        return quiz_options.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_number, tv_opt1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_opt1 = itemView.findViewById(R.id.tv_opt1);
        }
    }
}
