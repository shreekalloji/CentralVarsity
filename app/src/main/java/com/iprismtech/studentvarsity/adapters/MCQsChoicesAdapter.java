package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.MCQsPojo;
import com.iprismtech.studentvarsity.utils.Variables;

import java.util.List;

public class MCQsChoicesAdapter extends RecyclerView.Adapter<MCQsChoicesAdapter.ViewHolder> {
    private Context context;
    private List<MCQsPojo.ResponseBean.OptionsBean> options;
    private String answer;
    private TextView textView;

    public MCQsChoicesAdapter(Context context, List<MCQsPojo.ResponseBean.OptionsBean> options, String answer, TextView tv_ans) {
        this.context = context;
        this.options = options;
        this.answer = answer;
        this.textView = tv_ans;
    }

    private NotesAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(NotesAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mcq_option, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        //viewHolder.tv_option.setText(options.get(i).getOptions());
        //  viewHolder.tv_ans.setText(answer);
        viewHolder.rb_option.setText(options.get(i).getOptions());
        viewHolder.rb_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!Variables.rb_chekced.equalsIgnoreCase("true"))
                    Variables.rb_chekced = "false";
                else {
                    Variables.rb_chekced = "true";
                }
                if (Variables.rb_chekced.equalsIgnoreCase("true")) {
                    viewHolder.rb_option.setChecked(false);
                } else {
                    viewHolder.rb_option.setChecked(true);
                }

//                if (isChecked) {
//                    viewHolder.rb_option.setChecked(true);
//                } else {
//                    viewHolder.rb_option.setChecked(false);
//                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RadioButton rb_option;
        TextView tv_option, tv_ans;
        LinearLayout ll_option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rb_option = itemView.findViewById(R.id.rb_option);
          //  tv_option = itemView.findViewById(R.id.tv_option);
            ll_option = itemView.findViewById(R.id.ll_option);
            //  ll_option.setOnClickListener(this);
            rb_option.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mListner != null) {
                Variables.rb_chekced = "true";
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
