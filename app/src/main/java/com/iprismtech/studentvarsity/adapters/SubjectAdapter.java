package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.SubjectsPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.YearAdapterView> {
    //  LayoutInflater inflater;
    Context context;
    SubjectsPOJO cartPojo;
    private UserSession userSession;
    private List<SubjectsPOJO.ResponseBean> responseBeans = null;
    ArrayList<String> subjectcount = new ArrayList<>();
    List<String> myList;


    private OnitemClickListener mListner;

    public static ArrayList<String> sub_ids = new ArrayList<>();

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position, ArrayList<String> sub_ids);
    }


    public SubjectAdapter(SubjectsPOJO cartPojo, Context context) {
        this.context = context;
        this.cartPojo = cartPojo;
        //  inflater = LayoutInflater.from(context);
        userSession = new UserSession(context);
    }

    @Override
    public YearAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.semester_item, parent, false);
        return new YearAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(YearAdapterView holder, final int position) {


        String KEY_SUBJECTS = userSession.getUserDetails().get("subjects");

       /* Glide.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + cartPojo.getResponse().get(position).getImage())
                .into(holder.productimageiv);*/

        holder.semesterName.setText("Semester - " + cartPojo.getResponse().get(position).getSemester());


        if (KEY_SUBJECTS != null) {
         myList = new ArrayList<String>(Arrays.asList(KEY_SUBJECTS.split(",")));

            Log.d("selected_subjects", myList.toString());
            if (cartPojo.getResponse().get(position).getSubjetcs().size() > 0) {
                for (int i = 0; i < cartPojo.getResponse().get(position).getSubjetcs().size(); i++) {
                    String checkstream = cartPojo.getResponse().get(position).getSubjetcs().get(i).getId();

                    if (myList.contains(checkstream)) {
                        subjectcount.add("1");
                    } else {
                        subjectcount.add("0");
                    }

//                                streamcount.add("0");
                }
            }
        }

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        //setting horizontal list
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.subject_recycler_view.setLayoutManager(mLayoutManager);
        holder.subject_recycler_view.setItemAnimator(new DefaultItemAnimator());

        //Adding Adapter to recyclerView
        final SubjectSubAdapter adapter = new SubjectSubAdapter(cartPojo.getResponse().get(position).getSubjetcs(), context, myList );
        holder.subject_recycler_view.setAdapter(adapter);


    }


    @Override
    public int getItemCount() {
        return cartPojo.getResponse().size();
    }

    public List<SubjectsPOJO.ResponseBean> getSemesterList() {
        return responseBeans;
    }

    public void setSemesterList(final List<SubjectsPOJO.ResponseBean> semesterListNew) {
        if (responseBeans == null) {
            responseBeans = semesterListNew;
            //notifyItemRangeInserted(0, semesterListNew.size());
            notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return responseBeans.size();
                }

                @Override
                public int getNewListSize() {
                    return semesterListNew.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return responseBeans.get(oldItemPosition).getSemester().equals(semesterListNew.get(newItemPosition).getSemester());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return responseBeans.get(oldItemPosition).getSemester().equals(semesterListNew.get(newItemPosition).getSemester());
                }
            });
            responseBeans = semesterListNew;
            result.dispatchUpdatesTo(this);
        }
        Log.i("semesterList", "" + responseBeans.size());
    }

    public class YearAdapterView extends RecyclerView.ViewHolder {
//        TextView tv_sem;
//        RecyclerView rv_subjectsub;


        TextView semesterName;
        RecyclerView subject_recycler_view;

        public YearAdapterView(View itemView) {
            super(itemView);
//            tv_sem = itemView.findViewById(R.id.tv_sem);
//            rv_subjectsub = itemView.findViewById(R.id.rv_subjectsub);
//            itemView.setOnClickListener(this);

            semesterName = itemView.findViewById(R.id.semesterName);
            subject_recycler_view = itemView.findViewById(R.id.subject_recycler_view);

        }

//        @Override
//        public void onClick(View v) {
//
//            if (mListner != null) {
//                mListner.onItemClick(v, getAdapterPosition(), sub_ids);
//            }
//        }
    }
}


