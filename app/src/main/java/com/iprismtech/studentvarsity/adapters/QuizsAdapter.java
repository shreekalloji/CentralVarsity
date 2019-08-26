package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.customviews.CustomTextViewBold;
import com.iprismtech.studentvarsity.customviews.CustomTextViewNormal;
import com.iprismtech.studentvarsity.pojos.QuizsListPOJO;

import java.util.List;

public class QuizsAdapter extends RecyclerView.Adapter<QuizsAdapter.YearAdapterView> {
    LayoutInflater inflater;
    Context context;
    List<QuizsListPOJO.ResponseBean> cartPojo;

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    public QuizsAdapter(List<QuizsListPOJO.ResponseBean> cartPojo, Context context) {
        this.context = context;
        this.cartPojo = cartPojo;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public YearAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_quizs, parent, false);
        return new YearAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(YearAdapterView holder, int position) {

        holder.tv_title.setText(cartPojo.get(position).getTitle());
        holder.tv_date.setText(cartPojo.get(position).getCreated_on());
        holder.tv_total.setText("" + cartPojo.get(position).getRight_answers() + "/" + cartPojo.get(position).getTotal_questions());

    }


    @Override
    public int getItemCount() {
        return cartPojo.size();
    }

    public class YearAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_question;
        TextView tv_answer, tv_pingcount, tv_cmntcount, tv_viewcount;
        TextView tv_title, tv_total, tv_date;
        LinearLayout ll_quiz;


        public YearAdapterView(View itemView) {
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

            if (mListner != null) {
                System.out.println("mnjknnknk");
                mListner.onItemClick(v, getAdapterPosition());
            }

        }
    }
}



