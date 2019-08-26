package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.MyQuizzesPojo;
import com.iprismtech.studentvarsity.ui.activity.ViewProfile_Activity;

public class MyQuizzesAdapter extends RecyclerView.Adapter<MyQuizzesAdapter.ViewHolder> {
    private Context context;
    private MyQuizzesPojo myQuizzesPojo;

    public MyQuizzesAdapter(ViewProfile_Activity viewProfileActivity, MyQuizzesPojo myQuizzesPojo) {
        this.context = viewProfileActivity;
        this.myQuizzesPojo = myQuizzesPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_quiz, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_question.setText(myQuizzesPojo.getResponse().get(position).getQuestion());
        holder.tv_questionnoof.setText("Q.no." + String.valueOf(position + 1));


        try {

            holder.tv_opt1.setText(myQuizzesPojo.getResponse().get(position).getOptions().get(0).getOptions());
            holder.tv_opt2.setText(myQuizzesPojo.getResponse().get(position).getOptions().get(1).getOptions());
            holder.tv_opt3.setText(myQuizzesPojo.getResponse().get(position).getOptions().get(2).getOptions());
            holder.tv_opt4.setText(myQuizzesPojo.getResponse().get(position).getOptions().get(3).getOptions());

            if (myQuizzesPojo.getResponse().get(position).getCorrect_answer().equalsIgnoreCase("A")) {
                holder.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.green_background));
            } else {
                holder.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
            }
            if (myQuizzesPojo.getResponse().get(position).getCorrect_answer().equalsIgnoreCase("B")) {
                holder.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.green_background));
            } else {
                holder.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
            }
            if (myQuizzesPojo.getResponse().get(position).getCorrect_answer().equalsIgnoreCase("C")) {
                holder.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.green_background));
            } else {
                holder.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
            }
            if (myQuizzesPojo.getResponse().get(position).getCorrect_answer().equalsIgnoreCase("D")) {
                holder.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.green_background));
            } else {
                holder.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
            }
            if (myQuizzesPojo.getResponse().get(position).getAnswer().equalsIgnoreCase("A")) {
                holder.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.wrong_background));
            } else if (myQuizzesPojo.getResponse().get(position).getAnswer().equalsIgnoreCase("B")) {
                holder.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.wrong_background));
            } else if (myQuizzesPojo.getResponse().get(position).getAnswer().equalsIgnoreCase("C")) {
                holder.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.wrong_background));
            } else if (myQuizzesPojo.getResponse().get(position).getAnswer().equalsIgnoreCase("D")) {
                holder.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.wrong_background));
            }

            if (myQuizzesPojo.getResponse().get(position).getCorrect_answer().equalsIgnoreCase("A")) {
                holder.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.green_background));
            } else if (myQuizzesPojo.getResponse().get(position).getCorrect_answer().equalsIgnoreCase("B")) {
                holder.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.green_background));
            } else if (myQuizzesPojo.getResponse().get(position).getCorrect_answer().equalsIgnoreCase("C")) {
                holder.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.green_background));
            } else if (myQuizzesPojo.getResponse().get(position).getCorrect_answer().equalsIgnoreCase("D")) {
                holder.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.green_background));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return myQuizzesPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_question, tv_questionnoof;
        private TextView tv_opt1;
        private TextView tv_opt2;
        private TextView tv_opt3;
        private TextView tv_opt4;
        private TextView tv_opt5;
        private LinearLayout ll_opt1, ll_opt2, ll_opt3, ll_opt4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_question = (TextView) itemView.findViewById(R.id.quiz_question);
            tv_questionnoof = (TextView) itemView.findViewById(R.id.tv_questionnoof);
            tv_opt1 = (TextView) itemView.findViewById(R.id.tv_opt1);
            tv_opt2 = (TextView) itemView.findViewById(R.id.tv_opt2);
            tv_opt3 = (TextView) itemView.findViewById(R.id.tv_opt3);
            tv_opt4 = (TextView) itemView.findViewById(R.id.tv_opt4);
            tv_opt5 = (TextView) itemView.findViewById(R.id.tv_opt5);
            ll_opt1 = (LinearLayout) itemView.findViewById(R.id.ll_opt1);
            ll_opt2 = (LinearLayout) itemView.findViewById(R.id.ll_opt2);
            ll_opt3 = (LinearLayout) itemView.findViewById(R.id.ll_opt3);
            ll_opt4 = (LinearLayout) itemView.findViewById(R.id.ll_opt4);

        }
    }
}
