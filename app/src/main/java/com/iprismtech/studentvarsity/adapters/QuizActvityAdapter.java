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
import com.iprismtech.studentvarsity.pojos.QuizsListPOJO;

import java.util.List;

public class QuizActvityAdapter extends RecyclerView.Adapter<QuizActvityAdapter.ViewHolder> {
    private Context context;
    private List<QuizsListPOJO.ResponseBean> quizList;

    public QuizActvityAdapter(List<QuizsListPOJO.ResponseBean> quizList, Context context) {
        this.context = context;
        this.quizList = quizList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_quizs, viewGroup, false);
        return new ViewHolder(itemView);
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(quizList.get(position).getTitle());
        holder.tv_date.setText(quizList.get(position).getCreated_on());
        holder.tv_total.setText("" + quizList.get(position).getRight_answers() + "/" + quizList.get(position).getTotal_questions());

    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_question;
        TextView tv_answer, tv_pingcount, tv_cmntcount, tv_viewcount;
        TextView tv_title, tv_total, tv_date;
        LinearLayout ll_quiz;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_total = itemView.findViewById(R.id.tv_total);
            ll_quiz = itemView.findViewById(R.id.ll_quiz);
            tv_date = itemView.findViewById(R.id.tv_date);
//            tv_title.setOnClickListener(this);
//            tv_total.setOnClickListener(this);
            ll_quiz.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
