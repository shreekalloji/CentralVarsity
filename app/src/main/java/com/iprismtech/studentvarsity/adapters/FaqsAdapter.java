package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.FaqsListPOJO;

import java.util.List;


public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.YearAdapterView> {
    LayoutInflater inflater;
    Context context;
    List<FaqsListPOJO.ResponseBean> cartPojo;

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    public FaqsAdapter(List<FaqsListPOJO.ResponseBean> cartPojo, Context context) {
        this.context = context;
        this.cartPojo = cartPojo;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public YearAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_faqs, parent, false);
        return new YearAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(YearAdapterView holder, int position) {

        holder.tv_question.setText(cartPojo.get(position).getQuestion());
        holder.tv_answer.setText(cartPojo.get(position).getAnswer());
        holder.tv_chapter.setText(cartPojo.get(position).getChapter());
        holder.tv_pingcount.setText("Pings");
        holder.tv_cmntcount.setText(cartPojo.get(position).getComments() + " Comments");
        holder.tv_viewcount.setText(cartPojo.get(position).getViews() + " Views");

        if (cartPojo.get(position).getReaded().equalsIgnoreCase("yes")) {
            holder.ll_unread.setVisibility(View.GONE);
            holder.ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            holder.ll_unread.setVisibility(View.VISIBLE);
            holder.ll_unread_blue.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return cartPojo.size();
    }

    public class YearAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_question;
        TextView tv_answer, tv_pingcount, tv_cmntcount, tv_viewcount;
        TextView tv_chapter;
        LinearLayout ll_ping, ll_comment, ll_unread, ll_topic, ll_unread_blue;


        public YearAdapterView(View itemView) {
            super(itemView);
            tv_question = itemView.findViewById(R.id.tv_question);
            tv_answer = itemView.findViewById(R.id.tv_answer);
            tv_pingcount = itemView.findViewById(R.id.tv_pingcount);
            tv_cmntcount = itemView.findViewById(R.id.tv_cmntcount);
            tv_viewcount = itemView.findViewById(R.id.tv_viewcount);
            tv_chapter = itemView.findViewById(R.id.tv_chapter);

            ll_ping = itemView.findViewById(R.id.ll_ping);
            ll_comment = itemView.findViewById(R.id.ll_comment);
            ll_unread = itemView.findViewById(R.id.ll_unread);
            ll_unread_blue = itemView.findViewById(R.id.ll_unread_blue);


            tv_question.setOnClickListener(this);
            tv_answer.setOnClickListener(this);
            tv_pingcount.setOnClickListener(this);
            tv_cmntcount.setOnClickListener(this);
            tv_viewcount.setOnClickListener(this);
            tv_chapter.setOnClickListener(this);

            ll_ping.setOnClickListener(this);
            ll_comment.setOnClickListener(this);
            ll_unread.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }

        }
    }
}



