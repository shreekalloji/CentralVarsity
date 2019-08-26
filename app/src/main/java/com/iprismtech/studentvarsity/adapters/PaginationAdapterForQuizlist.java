package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.QuizQuestionModel;
import com.iprismtech.studentvarsity.ui.activity.QuestionActivity;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapterForQuizlist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public static List<QuizQuestionModel> movies;
    private Context context;
    private int questions_count_int;

    private boolean isLoadingAdded = false;

    public static ArrayList<String> arroptionsselected;

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    public PaginationAdapterForQuizlist(Context context, int questions_count_int) {
        this.context = context;
        movies = new ArrayList<>();
        this.questions_count_int = questions_count_int;
    }

    public List<QuizQuestionModel> getMovies() {
        return movies;
    }

    public void setMovies(List<QuizQuestionModel> movies) {
        this.movies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_quiz_question, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final QuizQuestionModel movie = movies.get(position);

        ArrayList<String> options = movie.getArroptions();
        arroptionsselected = movie.getArroptionsselected();

        switch (getItemViewType(position)) {
            case ITEM:
                MovieVH movieVH = (MovieVH) holder;

                movieVH.tv_question.setText(movie.getQuestion());
                movieVH.tv_questionnoof.setText("Q.no." + String.valueOf(position + 1));

                if (position + 1 == questions_count_int) {
                    movieVH.tv_next.setText("Submit");
                }


                if (options.size() == 2) {

                    movieVH.ll_opt1.setVisibility(View.VISIBLE);
                    movieVH.ll_opt2.setVisibility(View.VISIBLE);
                    movieVH.ll_opt3.setVisibility(View.GONE);
                    movieVH.ll_opt4.setVisibility(View.GONE);
                    movieVH.ll_opt5.setVisibility(View.GONE);


                    movieVH.tv_opt1.setText(options.get(0));
                    movieVH.tv_opt2.setText(options.get(1));


                    if (arroptionsselected.get(0).equalsIgnoreCase("0")) {
                        movieVH.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt1.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_A.setBackgroundResource(R.drawable.opt_a);
                    } else {
                        movieVH.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt1.setTextColor(context.getResources().getColor(R.color.white));

                        movieVH.iv_selected_A.setBackgroundResource(R.drawable.opt_a_blue);

                    }
                    if (arroptionsselected.get(1).equalsIgnoreCase("0")) {
                        movieVH.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt2.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_B.setBackgroundResource(R.drawable.opt_b);
                    } else {
                        movieVH.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt2.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_B.setBackgroundResource(R.drawable.opt_b_blue);
                    }


                } else if (options.size() == 3) {
                    movieVH.ll_opt1.setVisibility(View.VISIBLE);
                    movieVH.ll_opt2.setVisibility(View.VISIBLE);
                    movieVH.ll_opt3.setVisibility(View.VISIBLE);
                    movieVH.ll_opt4.setVisibility(View.GONE);
                    movieVH.ll_opt5.setVisibility(View.GONE);


                    movieVH.tv_opt1.setText(options.get(0));
                    movieVH.tv_opt2.setText(options.get(1));
                    movieVH.tv_opt3.setText(options.get(2));


                    if (arroptionsselected.get(0).equalsIgnoreCase("0")) {
                        movieVH.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt1.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_A.setBackgroundResource(R.drawable.opt_a);
                    } else {
                        movieVH.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt1.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_A.setBackgroundResource(R.drawable.opt_a_blue);
                    }
                    if (arroptionsselected.get(1).equalsIgnoreCase("0")) {
                        movieVH.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt2.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_B.setBackgroundResource(R.drawable.opt_b);
                    } else {
                        movieVH.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt2.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_B.setBackgroundResource(R.drawable.opt_b_blue);
                    }
                    if (arroptionsselected.get(2).equalsIgnoreCase("0")) {
                        movieVH.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt3.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_C.setBackgroundResource(R.drawable.opt_c);
                    } else {
                        movieVH.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt3.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_C.setBackgroundResource(R.drawable.opt_c_blue);

                    }
                } else if (options.size() == 4) {
                    movieVH.ll_opt1.setVisibility(View.VISIBLE);
                    movieVH.ll_opt2.setVisibility(View.VISIBLE);
                    movieVH.ll_opt3.setVisibility(View.VISIBLE);
                    movieVH.ll_opt4.setVisibility(View.VISIBLE);
                    movieVH.ll_opt5.setVisibility(View.GONE);


                    movieVH.tv_opt1.setText(options.get(0));
                    movieVH.tv_opt2.setText(options.get(1));
                    movieVH.tv_opt3.setText(options.get(2));
                    movieVH.tv_opt4.setText(options.get(3));


                    if (arroptionsselected.get(0).equalsIgnoreCase("0")) {
                        movieVH.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt1.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_A.setBackgroundResource(R.drawable.opt_a);

                    } else {
                        movieVH.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt1.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_A.setBackgroundResource(R.drawable.opt_a_blue);
                    }
                    if (arroptionsselected.get(1).equalsIgnoreCase("0")) {
                        movieVH.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt2.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_B.setBackgroundResource(R.drawable.opt_b);
                    } else {
                        movieVH.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt2.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_B.setBackgroundResource(R.drawable.opt_b_blue);
                    }
                    if (arroptionsselected.get(2).equalsIgnoreCase("0")) {
                        movieVH.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt3.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_C.setBackgroundResource(R.drawable.opt_c);
                    } else {
                        movieVH.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt3.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_C.setBackgroundResource(R.drawable.opt_c_blue);

                    }
                    if (arroptionsselected.get(3).equalsIgnoreCase("0")) {
                        movieVH.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt4.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_D.setBackgroundResource(R.drawable.opt_d);
                    } else {
                        movieVH.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt4.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_D.setBackgroundResource(R.drawable.opt_d_blue);
                    }
                } else if (options.size() == 5) {
                    movieVH.ll_opt1.setVisibility(View.VISIBLE);
                    movieVH.ll_opt2.setVisibility(View.VISIBLE);
                    movieVH.ll_opt3.setVisibility(View.VISIBLE);
                    movieVH.ll_opt4.setVisibility(View.VISIBLE);
                    movieVH.ll_opt5.setVisibility(View.VISIBLE);


                    movieVH.tv_opt1.setText(options.get(0));
                    movieVH.tv_opt2.setText(options.get(1));
                    movieVH.tv_opt3.setText(options.get(2));
                    movieVH.tv_opt4.setText(options.get(4));
                    movieVH.tv_opt5.setText(options.get(5));


                    if (arroptionsselected.get(0).equalsIgnoreCase("0")) {
                        movieVH.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt1.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_A.setBackgroundResource(R.drawable.opt_a);
                    } else {
                        movieVH.ll_opt1.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt1.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_A.setBackgroundResource(R.drawable.opt_a_blue);
                    }
                    if (arroptionsselected.get(1).equalsIgnoreCase("0")) {
                        movieVH.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt2.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_B.setBackgroundResource(R.drawable.opt_b);
                    } else {
                        movieVH.ll_opt2.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt2.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_B.setBackgroundResource(R.drawable.opt_b_blue);
                    }
                    if (arroptionsselected.get(2).equalsIgnoreCase("0")) {
                        movieVH.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt3.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_C.setBackgroundResource(R.drawable.opt_c);

                    } else {
                        movieVH.ll_opt3.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt3.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_C.setBackgroundResource(R.drawable.opt_c_blue);

                    }
                    if (arroptionsselected.get(3).equalsIgnoreCase("0")) {
                        movieVH.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt4.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_D.setBackgroundResource(R.drawable.opt_d);
                    } else {
                        movieVH.ll_opt4.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt4.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_D.setBackgroundResource(R.drawable.opt_d_blue);
                    }

                    if (arroptionsselected.get(4).equalsIgnoreCase("0")) {
                        movieVH.ll_opt5.setBackground(context.getResources().getDrawable(R.drawable.normal_background));
                        movieVH.tv_opt5.setTextColor(context.getResources().getColor(R.color.light_black));
                        movieVH.iv_selected_E.setBackgroundResource(R.drawable.opt_e);
                    } else {
                        movieVH.ll_opt5.setBackground(context.getResources().getDrawable(R.drawable.blue_background));
                        movieVH.tv_opt5.setTextColor(context.getResources().getColor(R.color.white));
                        movieVH.iv_selected_E.setBackgroundResource(R.drawable.opt_e_blue);
                    }

                }
//
//                movieVH.tv_opt1.setText(options.get(0));
//                movieVH.tv_opt2.setText(options.get(1));
//                movieVH.tv_opt3.setText(options.get(2));
//                movieVH.tv_opt4.setText(options.get(3));
                //  movieVH.tv_opt5.setText(options.get(4));


//                movieVH.ll_opt1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for (int i = 0; i < arroptionsselected.size(); i++) {
//                            if (i == 0) {
//                                arroptionsselected.set(0, "1");
//                            } else {
//                                arroptionsselected.set(i, "0");
//                            }
//                        }
//                        notifyDataSetChanged();
//                    }
//                });
//                movieVH.ll_opt2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for (int i = 0; i < arroptionsselected.size(); i++) {
//                            if (i == 1) {
//                                arroptionsselected.set(1, "1");
//                            } else {
//                                arroptionsselected.set(i, "0");
//                            }
//                        }
//                        notifyDataSetChanged();
//                    }
//                });
//                movieVH.ll_opt3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for (int i = 0; i < arroptionsselected.size(); i++) {
//                            if (i == 2) {
//                                arroptionsselected.set(2, "1");
//                            } else {
//                                arroptionsselected.set(i, "0");
//                            }
//                        }
//                        notifyDataSetChanged();
//                    }
//                });
//                movieVH.ll_opt4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for (int i = 0; i < arroptionsselected.size(); i++) {
//                            if (i == 3) {
//                                arroptionsselected.set(3, "1");
//                            } else {
//                                arroptionsselected.set(i, "0");
//                            }
//                        }
//                        notifyDataSetChanged();
//                    }
//                });
                break;
            case LOADING:
//                Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movies.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(QuizQuestionModel mc) {
        movies.add(mc);
        notifyItemInserted(movies.size() - 1);
    }

    public void addAll(List<QuizQuestionModel> mcList) {
        for (QuizQuestionModel mc : mcList) {
            add(mc);
        }
    }

    public void remove(QuizQuestionModel city) {
        int position = movies.indexOf(city);
        if (position > -1) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new QuizQuestionModel());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movies.size() - 1;
        QuizQuestionModel item = getItem(position);

        if (item != null) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public QuizQuestionModel getItem(int position) {
        return movies.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class MovieVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_question, tv_questionnoof, tv_next;
        private TextView tv_opt1;
        private TextView tv_opt2;
        private TextView tv_opt3;
        private TextView tv_opt4;
        private TextView tv_opt5;
        private LinearLayout ll_opt1, ll_opt2, ll_opt3, ll_opt4, ll_opt5;
        private ImageView iv_selected_A, iv_selected_B, iv_selected_C, iv_selected_D, iv_selected_E;

        private LinearLayout ll_discuss, ll_next, ll_prev;


        public MovieVH(View itemView) {
            super(itemView);

            tv_question = (TextView) itemView.findViewById(R.id.tv_question);
            tv_questionnoof = (TextView) itemView.findViewById(R.id.tv_questionnoof);

            tv_next = (TextView) itemView.findViewById(R.id.tv_next);
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


            iv_selected_A = itemView.findViewById(R.id.iv_selected_A);
            iv_selected_B = itemView.findViewById(R.id.iv_selected_B);
            iv_selected_C = itemView.findViewById(R.id.iv_selected_C);
            iv_selected_D = itemView.findViewById(R.id.iv_selected_D);
            iv_selected_E = itemView.findViewById(R.id.iv_selected_E);

            tv_question.setOnClickListener(this);
            ll_opt1.setOnClickListener(this);
            ll_opt2.setOnClickListener(this);
            ll_opt3.setOnClickListener(this);
            ll_opt4.setOnClickListener(this);
            ll_opt5.setOnClickListener(this);


            ll_discuss.setOnClickListener(this);
            ll_next.setOnClickListener(this);
            ll_prev.setOnClickListener(this);


//            ll_opt1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for (int i = 0; i < arroptionsselected.size(); i++) {
//                        if (i == 0) {
//                            arroptionsselected.set(0, "1");
//                        } else {
//                            arroptionsselected.set(i, "0");
//                        }
//                    }
//                    notifyDataSetChanged();
//                }
//            });
//            ll_opt2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for (int i = 0; i < arroptionsselected.size(); i++) {
//                        if (i == 1) {
//                            arroptionsselected.set(1, "1");
//                        } else {
//                            arroptionsselected.set(i, "0");
//                        }
//                    }
//                    notifyDataSetChanged();
//                }
//            });
//            ll_opt3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for (int i = 0; i < arroptionsselected.size(); i++) {
//                        if (i == 2) {
//                            arroptionsselected.set(2, "1");
//                        } else {
//                            arroptionsselected.set(i, "0");
//                        }
//                    }
//                    notifyDataSetChanged();
//                }
//            });
//            ll_opt4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for (int i = 0; i < arroptionsselected.size(); i++) {
//                        if (i == 3) {
//                            arroptionsselected.set(3, "1");
//                        } else {
//                            arroptionsselected.set(i, "0");
//                        }
//                    }
//                    notifyDataSetChanged();
//                }
//            });
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}

