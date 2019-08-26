package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.MCQsPojo;

import java.util.List;

public class MCQsAdapter extends RecyclerView.Adapter<MCQsAdapter.ViewHolder> {
    private Context context;
    private List<MCQsPojo.ResponseBean> mcQsPojo;

    public MCQsAdapter(FragmentActivity activity, List<MCQsPojo.ResponseBean> mcQsPojo) {
        this.context = activity;
        this.mcQsPojo = mcQsPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mcqs, viewGroup, false);
        return new ViewHolder(view);
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv_chapter.setText(mcQsPojo.get(i).getChapter());
        viewHolder.tv_question.setText(mcQsPojo.get(i).getQuestion());
        viewHolder.tv_ans.setText("Ans: " + mcQsPojo.get(i).getAnswer() +'\n' + "Solution: " + mcQsPojo.get(i).getSolution());


        final ColorStateList colorStateList = new ColorStateList(
                new int[][]{

                        new int[]{-android.R.attr.state_enabled}, //disabled
                        new int[]{android.R.attr.state_enabled} //enabled
                },
                new int[]{

                        Color.GREEN //disabled
                        , Color.RED //enabled

                }
        );


        if (mcQsPojo.get(i).getOptions().size() == 2) {
            viewHolder.ll_mcq_A.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_B.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_C.setVisibility(View.GONE);
            viewHolder.ll_mcq_D.setVisibility(View.GONE);
            viewHolder.ll_mcq_E.setVisibility(View.GONE);
            viewHolder.tv_optionA.setText(mcQsPojo.get(i).getOptions().get(0).getOptions());
            viewHolder.tv_optionB.setText(mcQsPojo.get(i).getOptions().get(1).getOptions());
        } else if (mcQsPojo.get(i).getOptions().size() == 3) {
            viewHolder.ll_mcq_A.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_B.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_C.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_D.setVisibility(View.GONE);
            viewHolder.ll_mcq_E.setVisibility(View.GONE);
            viewHolder.tv_optionA.setText(mcQsPojo.get(i).getOptions().get(0).getOptions());
            viewHolder.tv_optionB.setText(mcQsPojo.get(i).getOptions().get(1).getOptions());
            viewHolder.tv_optionC.setText(mcQsPojo.get(i).getOptions().get(2).getOptions());
        } else if (mcQsPojo.get(i).getOptions().size() == 4) {
            viewHolder.ll_mcq_A.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_B.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_C.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_D.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_E.setVisibility(View.GONE);
            viewHolder.tv_optionA.setText(mcQsPojo.get(i).getOptions().get(0).getOptions());
            viewHolder.tv_optionB.setText(mcQsPojo.get(i).getOptions().get(1).getOptions());
            viewHolder.tv_optionC.setText(mcQsPojo.get(i).getOptions().get(2).getOptions());
            viewHolder.tv_optionD.setText(mcQsPojo.get(i).getOptions().get(3).getOptions());
        } else if (mcQsPojo.get(i).getOptions().size() == 5) {
            viewHolder.ll_mcq_A.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_B.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_C.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_D.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_E.setVisibility(View.VISIBLE);
            viewHolder.tv_optionA.setText(mcQsPojo.get(i).getOptions().get(0).getOptions());
            viewHolder.tv_optionB.setText(mcQsPojo.get(i).getOptions().get(1).getOptions());
            viewHolder.tv_optionC.setText(mcQsPojo.get(i).getOptions().get(2).getOptions());
            viewHolder.tv_optionD.setText(mcQsPojo.get(i).getOptions().get(3).getOptions());
            viewHolder.tv_optionE.setText(mcQsPojo.get(i).getOptions().get(4).getOptions());
        }

        viewHolder.rb_A.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                //viewHolder.rb_A.setChecked(true);
                viewHolder.rb_B.setClickable(false);
                viewHolder.rb_C.setClickable(false);
                viewHolder.rb_D.setClickable(false);
                viewHolder.rb_E.setClickable(false);
                viewHolder.tv_ans.setVisibility(View.VISIBLE);
                //  mcQsPojo.get(i).getOptions().get(i).setSelected_option("A");
                if (mcQsPojo.get(i).getAnswer().equalsIgnoreCase("A")) {
                    // mcQsPojo.get(i).getOptions().get(i).setRight_attempt("true");
                    viewHolder.tv_optionA.setTextColor(ContextCompat.getColor(context, R.color.green));
                    viewHolder.rb_A.setBackgroundResource(R.mipmap.ic_correct);

                } else {
                    // mcQsPojo.get(i).getOptions().get(i).setRight_attempt("false");
                    viewHolder.tv_optionA.setTextColor(ContextCompat.getColor(context, R.color.dark_red));
                    viewHolder.rb_A.setBackgroundResource(R.mipmap.ic_wrong);
                }
            }
        });
        viewHolder.rb_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // viewHolder.rb_B.setChecked(true);
                viewHolder.rb_A.setClickable(false);
                viewHolder.rb_C.setClickable(false);
                viewHolder.rb_D.setClickable(false);
                viewHolder.rb_E.setClickable(false);
                viewHolder.tv_ans.setVisibility(View.VISIBLE);
                //mcQsPojo.get(i).getOptions().get(i).setSelected_option("B");
                if (mcQsPojo.get(i).getAnswer().equalsIgnoreCase("B")) {
                    // mcQsPojo.get(i).getOptions().get(i).setRight_attempt("true");
                    viewHolder.tv_optionB.setTextColor(ContextCompat.getColor(context, R.color.green));
                    viewHolder.rb_B.setBackgroundResource(R.mipmap.ic_correct);

                } else {
                    // mcQsPojo.get(i).getOptions().get(i).setRight_attempt("false");
                    viewHolder.tv_optionB.setTextColor(ContextCompat.getColor(context, R.color.dark_red));
                    viewHolder.rb_B.setBackgroundResource(R.mipmap.ic_wrong);
                }
            }
        });
        viewHolder.rb_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // viewHolder.rb_C.setChecked(true);
                viewHolder.rb_A.setClickable(false);
                viewHolder.rb_B.setClickable(false);
                viewHolder.rb_D.setClickable(false);
                viewHolder.rb_E.setClickable(false);
                viewHolder.tv_ans.setVisibility(View.VISIBLE);
                //   mcQsPojo.get(i).getOptions().get(i).setSelected_option("C");
                if (mcQsPojo.get(i).getAnswer().equalsIgnoreCase("C")) {
                    // mcQsPojo.get(i).getOptions().get(i).setRight_attempt("true");
                    viewHolder.tv_optionC.setTextColor(ContextCompat.getColor(context, R.color.green));
                    viewHolder.rb_C.setBackgroundResource(R.mipmap.ic_correct);

                } else {
                    //  mcQsPojo.get(i).getOptions().get(i).setRight_attempt("false");
                    viewHolder.tv_optionC.setTextColor(ContextCompat.getColor(context, R.color.dark_red));
                    viewHolder.rb_C.setBackgroundResource(R.mipmap.ic_wrong);
                }

            }
        });
        viewHolder.rb_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   viewHolder.rb_D.setChecked(true);
                viewHolder.rb_A.setClickable(false);
                viewHolder.rb_C.setClickable(false);
                viewHolder.rb_B.setClickable(false);
                viewHolder.rb_E.setClickable(false);
                viewHolder.tv_ans.setVisibility(View.VISIBLE);

                //     mcQsPojo.get(i).getOptions().get(i).setSelected_option("D");
                if (mcQsPojo.get(i).getAnswer().equalsIgnoreCase("D")) {
                    //mcQsPojo.get(i).getOptions().get(i).setRight_attempt("true");
                    viewHolder.tv_optionD.setTextColor(ContextCompat.getColor(context, R.color.green));
                    viewHolder.rb_D.setBackgroundResource(R.mipmap.ic_correct);

                } else {
                    // mcQsPojo.get(i).getOptions().get(i).setRight_attempt("false");
                    viewHolder.tv_optionD.setTextColor(ContextCompat.getColor(context, R.color.dark_red));
                    viewHolder.rb_D.setBackgroundResource(R.mipmap.ic_wrong);
                }
            }
        });
        viewHolder.rb_E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // viewHolder.rb_E.setChecked(true);
                viewHolder.rb_A.setClickable(false);
                viewHolder.rb_C.setClickable(false);
                viewHolder.rb_D.setClickable(false);
                viewHolder.rb_B.setClickable(false);
                viewHolder.tv_ans.setVisibility(View.VISIBLE);

                //       mcQsPojo.get(i).getOptions().get(i).setSelected_option("D");
                if (mcQsPojo.get(i).getAnswer().equalsIgnoreCase("D")) {
                    // mcQsPojo.get(i).getOptions().get(i).setRight_attempt("true");
                    viewHolder.tv_optionD.setTextColor(ContextCompat.getColor(context, R.color.green));
                    viewHolder.rb_D.setBackgroundResource(R.mipmap.ic_correct);

                } else {
                    //mcQsPojo.get(i).getOptions().get(i).setRight_attempt("false");
                    viewHolder.tv_optionD.setTextColor(ContextCompat.getColor(context, R.color.dark_red));
                    viewHolder.rb_D.setBackgroundResource(R.mipmap.ic_wrong);
                }
            }
        });


        if (mcQsPojo.get(i).getOptions().size() == 3) {
            viewHolder.ll_mcq_A.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_B.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_C.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_D.setVisibility(View.GONE);
            viewHolder.ll_mcq_E.setVisibility(View.GONE);

            viewHolder.tv_optionA.setText(mcQsPojo.get(i).getOptions().get(0).getOptions());
            viewHolder.tv_optionB.setText(mcQsPojo.get(i).getOptions().get(1).getOptions());
            viewHolder.tv_optionC.setText(mcQsPojo.get(i).getOptions().get(2).getOptions());
        }
        if (mcQsPojo.get(i).getOptions().size() == 4) {
            viewHolder.ll_mcq_A.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_B.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_C.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_D.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_E.setVisibility(View.GONE);

            viewHolder.tv_optionA.setText(mcQsPojo.get(i).getOptions().get(0).getOptions());
            viewHolder.tv_optionB.setText(mcQsPojo.get(i).getOptions().get(1).getOptions());
            viewHolder.tv_optionC.setText(mcQsPojo.get(i).getOptions().get(2).getOptions());
            viewHolder.tv_optionD.setText(mcQsPojo.get(i).getOptions().get(3).getOptions());
        }
        if (mcQsPojo.get(i).getOptions().size() == 5) {
            viewHolder.ll_mcq_A.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_B.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_C.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_D.setVisibility(View.VISIBLE);
            viewHolder.ll_mcq_E.setVisibility(View.VISIBLE);

            viewHolder.tv_optionA.setText(mcQsPojo.get(i).getOptions().get(0).getOptions());
            viewHolder.tv_optionB.setText(mcQsPojo.get(i).getOptions().get(1).getOptions());
            viewHolder.tv_optionC.setText(mcQsPojo.get(i).getOptions().get(2).getOptions());
            viewHolder.tv_optionD.setText(mcQsPojo.get(i).getOptions().get(3).getOptions());
            viewHolder.tv_optionE.setText(mcQsPojo.get(i).getOptions().get(4).getOptions());
        }


//        MCQsChoicesAdapter itemListDataAdapter = new MCQsChoicesAdapter(context, mcQsPjo.get(i).getOptions(), mcQsPojo.get(i).getAnswer(), viewHolder.tv_ans);
//        viewHolder.rview_choices.setHasFixedSize(true);o
//        viewHolder.rview_choices.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//        viewHolder.rview_choices.setAdapter(itemListDataAdapter);
//        itemListDataAdapter.setOnItemClickListener(new NotesAdapter.OnitemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                switch (view.getId()) {
//                    case R.id.rb_option:
//                        Variables.rb_chekced = "false";
//                        viewHolder.tv_ans.setVisibility(View.VISIBLE);
//                        if (mcQsPojo.get(i).getAnswer().equalsIgnoreCase("A") && position == 0) {
//                            RadioButton radioButton = view.findViewById(R.id.rb_option);
//                            radioButton.setTextColor(ContextCompat.getColor(context, R.color.green));
//                        } else if (mcQsPojo.get(i).getAnswer().equalsIgnoreCase("B") && position == 1) {
//                            RadioButton radioButton = view.findViewById(R.id.rb_option);
//                            radioButton.setTextColor(ContextCompat.getColor(context, R.color.green));
//                        } else if (mcQsPojo.get(i).getAnswer().equalsIgnoreCase("C") && position == 2) {
//                            RadioButton radioButton = view.findViewById(R.id.rb_option);
//                            radioButton.setTextColor(ContextCompat.getColor(context, R.color.green));
//                        } else if (mcQsPojo.get(i).getAnswer().equalsIgnoreCase("D") && position == 3) {
//                            RadioButton radioButton = view.findViewById(R.id.rb_option);
//                            radioButton.setTextColor(ContextCompat.getColor(context, R.color.green));
//                        }
//                        else {
//                            RadioButton radioButton = view.findViewById(R.id.rb_option);
//                            radioButton.setTextColor(ContextCompat.getColor(context, R.color.primary_light));
//
//                        }
//                        break;
//                }
//            }
//        });
//


        viewHolder.tv_comments_count.setText(mcQsPojo.get(i).getComments() + " Comments");
        viewHolder.tv_pings_count.setText("Pings");
        viewHolder.tv_view_count.setText(mcQsPojo.get(i).getViews() + " Views");

        if (mcQsPojo.get(i).getReaded().equalsIgnoreCase("yes")) {
            viewHolder.ll_unread.setVisibility(View.GONE);
            viewHolder.ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ll_unread.setVisibility(View.VISIBLE);
            viewHolder.ll_unread_blue.setVisibility(View.GONE);
        }
        //viewHolder.tv_ans.setText(mcQsPojo.get(i).getAnswer());


    }

    @Override
    public int getItemCount() {
        return mcQsPojo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_chapter, tv_question, tv_ans, tv_pings_count, tv_comments_count, tv_view_count;

        RecyclerView rview_choices;
        LinearLayout ll_question;
        LinearLayout ll_ping, ll_comment, ll_unread, ll_topic, ll_unread_blue;

        LinearLayout ll_mcq_A, ll_mcq_B, ll_mcq_C, ll_mcq_D, ll_mcq_E;
        ImageView rb_A, rb_B, rb_C, rb_D, rb_E;
        //AppCompatRadioButton rb_A;
        TextView tv_optionA, tv_optionB, tv_optionC, tv_optionD, tv_optionE;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_chapter = itemView.findViewById(R.id.tv_chapter);
            tv_question = itemView.findViewById(R.id.tv_question);
            tv_ans = itemView.findViewById(R.id.tv_answer);
            rview_choices = itemView.findViewById(R.id.rview_choices);
            ll_question = itemView.findViewById(R.id.ll_question);
            ll_comment = itemView.findViewById(R.id.ll_comment);
            ll_ping = itemView.findViewById(R.id.ll_ping);
            ll_unread = itemView.findViewById(R.id.ll_unread);
            ll_unread_blue = itemView.findViewById(R.id.ll_unread_blue);

            ll_mcq_A = itemView.findViewById(R.id.ll_mcq_A);
            ll_mcq_B = itemView.findViewById(R.id.ll_mcq_B);
            ll_mcq_C = itemView.findViewById(R.id.ll_mcq_C);
            ll_mcq_D = itemView.findViewById(R.id.ll_mcq_D);
            ll_mcq_E = itemView.findViewById(R.id.ll_mcq_E);

            rb_A = itemView.findViewById(R.id.rb_A);
            rb_B = itemView.findViewById(R.id.rb_B);
            rb_C = itemView.findViewById(R.id.rb_C);
            rb_D = itemView.findViewById(R.id.rb_D);
            rb_E = itemView.findViewById(R.id.rb_E);

            tv_optionA = itemView.findViewById(R.id.tv_optionA);
            tv_optionB = itemView.findViewById(R.id.tv_optionB);
            tv_optionC = itemView.findViewById(R.id.tv_optionC);
            tv_optionD = itemView.findViewById(R.id.tv_optionD);
            tv_optionE = itemView.findViewById(R.id.tv_optionE);


            tv_pings_count = itemView.findViewById(R.id.tv_pings_count);
            tv_comments_count = itemView.findViewById(R.id.tv_comments_count);
            tv_view_count = itemView.findViewById(R.id.tv_view_count);

            ll_ping.setOnClickListener(this);
            ll_comment.setOnClickListener(this);
            ll_unread.setOnClickListener(this);
            ll_question.setOnClickListener(this);
            tv_comments_count.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
