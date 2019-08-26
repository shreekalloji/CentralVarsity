package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.RightWrongPOJO;

public class RightAttemptAdapter extends RecyclerView.Adapter<RightAttemptAdapter.YearAdapterView> {
    LayoutInflater inflater;
    Context context;
    RightWrongPOJO cartPojo;

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    public RightAttemptAdapter(RightWrongPOJO cartPojo, Context context) {
        this.context = context;
        this.cartPojo = cartPojo;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public YearAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_quiz_question, parent, false);
        return new YearAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(YearAdapterView holder, int position) {

        holder.tv_question.setText(cartPojo.getRight_answers().get(position).getQuestion());
        holder.tv_questionnoof.setText("Q.no." + String.valueOf(position + 1));


        if (cartPojo.getRight_answers().get(position).getOptions().size() == 2) {
            holder.ll_opt1.setVisibility(View.VISIBLE);
            holder.ll_opt2.setVisibility(View.VISIBLE);
            holder.ll_opt3.setVisibility(View.GONE);
            holder.ll_opt4.setVisibility(View.GONE);
            holder.ll_opt5.setVisibility(View.GONE);

            holder.tv_opt1.setText(cartPojo.getRight_answers().get(position).getOptions().get(0).getOptions());
            holder.tv_opt2.setText(cartPojo.getRight_answers().get(position).getOptions().get(1).getOptions());
        } else if (cartPojo.getRight_answers().get(position).getOptions().size() == 3) {
            holder.ll_opt1.setVisibility(View.VISIBLE);
            holder.ll_opt2.setVisibility(View.VISIBLE);
            holder.ll_opt3.setVisibility(View.VISIBLE);
            holder.ll_opt4.setVisibility(View.GONE);
            holder.ll_opt5.setVisibility(View.GONE);

            holder.tv_opt1.setText(cartPojo.getRight_answers().get(position).getOptions().get(0).getOptions());
            holder.tv_opt2.setText(cartPojo.getRight_answers().get(position).getOptions().get(1).getOptions());
            holder.tv_opt3.setText(cartPojo.getRight_answers().get(position).getOptions().get(2).getOptions());
        } else if (cartPojo.getRight_answers().get(position).getOptions().size() == 4) {
            holder.ll_opt1.setVisibility(View.VISIBLE);
            holder.ll_opt2.setVisibility(View.VISIBLE);
            holder.ll_opt3.setVisibility(View.VISIBLE);
            holder.ll_opt4.setVisibility(View.VISIBLE);
            holder.ll_opt5.setVisibility(View.GONE);

            holder.tv_opt1.setText(cartPojo.getRight_answers().get(position).getOptions().get(0).getOptions());
            holder.tv_opt2.setText(cartPojo.getRight_answers().get(position).getOptions().get(1).getOptions());
            holder.tv_opt3.setText(cartPojo.getRight_answers().get(position).getOptions().get(2).getOptions());
            holder.tv_opt4.setText(cartPojo.getRight_answers().get(position).getOptions().get(3).getOptions());
        } else {
            holder.ll_opt1.setVisibility(View.VISIBLE);
            holder.ll_opt2.setVisibility(View.VISIBLE);
            holder.ll_opt3.setVisibility(View.VISIBLE);
            holder.ll_opt4.setVisibility(View.VISIBLE);
            holder.ll_opt5.setVisibility(View.VISIBLE);

            holder.tv_opt1.setText(cartPojo.getRight_answers().get(position).getOptions().get(0).getOptions());
            holder.tv_opt2.setText(cartPojo.getRight_answers().get(position).getOptions().get(1).getOptions());
            holder.tv_opt3.setText(cartPojo.getRight_answers().get(position).getOptions().get(2).getOptions());
            holder.tv_opt4.setText(cartPojo.getRight_answers().get(position).getOptions().get(3).getOptions());
            holder.tv_opt5.setText(cartPojo.getRight_answers().get(position).getOptions().get(4).getOptions());
        }

//        holder.tv_opt1.setText(cartPojo.getRight_answers().get(position).getOptions().get(0).getOptions());
//        holder.tv_opt2.setText(cartPojo.getRight_answers().get(position).getOptions().get(1).getOptions());
//        holder.tv_opt3.setText(cartPojo.getRight_answers().get(position).getOptions().get(2).getOptions());
//        holder.tv_opt4.setText(cartPojo.getRight_answers().get(position).getOptions().get(3).getOptions());


        if (cartPojo.getRight_answers().get(position).getCorrect_answer().equalsIgnoreCase("A")) {
            holder.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.green_background));
        } else {
            holder.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
        }
        if (cartPojo.getRight_answers().get(position).getCorrect_answer().equalsIgnoreCase("B")) {
            holder.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.green_background));
        } else {
            holder.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
        }
        if (cartPojo.getRight_answers().get(position).getCorrect_answer().equalsIgnoreCase("C")) {
            holder.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.green_background));
        } else {
            holder.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
        }
        if (cartPojo.getRight_answers().get(position).getCorrect_answer().equalsIgnoreCase("D")) {
            holder.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.green_background));
        } else {
            holder.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
        }
    }


    @Override
    public int getItemCount() {
        return cartPojo.getRight_answers().size();
    }

    public class YearAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_question, tv_questionnoof;
        private TextView tv_opt1;
        private TextView tv_opt2;
        private TextView tv_opt3;
        private TextView tv_opt4;
        private TextView tv_opt5;
        private LinearLayout ll_opt1, ll_opt2, ll_opt3, ll_opt4, ll_opt5;

        private LinearLayout ll_discuss, ll_next, ll_prev;


        public YearAdapterView(View itemView) {
            super(itemView);
            tv_question = (TextView) itemView.findViewById(R.id.tv_question);
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
            ll_opt5 = (LinearLayout) itemView.findViewById(R.id.ll_opt5);

            ll_discuss = (LinearLayout) itemView.findViewById(R.id.ll_discuss);
            ll_next = (LinearLayout) itemView.findViewById(R.id.ll_next);
            ll_prev = (LinearLayout) itemView.findViewById(R.id.ll_prev);

            ll_discuss.setOnClickListener(this);
            ll_next.setOnClickListener(this);
            ll_prev.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }

        }
    }
}




