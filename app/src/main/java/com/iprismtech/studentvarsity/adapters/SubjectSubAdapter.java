package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.SubjectsPOJO;

import java.util.ArrayList;
import java.util.List;


public class SubjectSubAdapter extends RecyclerView.Adapter<SubjectSubAdapter.YearAdapterView> {
    LayoutInflater inflater;
    Context context;
    List<SubjectsPOJO.ResponseBean.SubjetcsBean> cartPojo;
    public static ArrayList<String> subjectcount;
    List<String> myList = new ArrayList<>();

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    public SubjectSubAdapter(List<SubjectsPOJO.ResponseBean.SubjetcsBean> cartPojo, Context context, List<String> myList) {

        this.context = context;
        this.cartPojo = cartPojo;
        this.myList = myList;

    }

//    public void filter(final String text) {
//
//        // Searching could be complex..so we will dispatch it to a different thread...
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                // Clear the filter list
//                filteredList.clear();
//
//                // If there is no search value, then add all original list items to filter list
//                if (TextUtils.isEmpty(text)) {
//
//                    filteredList.addAll(cartPojo);
//
//                } else {
//                    // Iterate in the original List and add it to filter list...
//                    for (SubjectsPOJO.ResponseBean.SubjetcsBean item : cartPojo) {
//                        if (item.getSubject().toLowerCase().contains(text.toLowerCase())) {
//                            // Adding Matched items
//                            filteredList.add(item);
//                        }
//                    }
//                }
//
//                // Set on UI Thread
//                ((Activity) context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Notify the List that the DataSet has changed...
//                        notifyDataSetChanged();
//                    }
//                });
//
//            }
//        }).start();
//
//    }

    @Override
    public YearAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.subject_item, parent, false);
        return new YearAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(YearAdapterView holder, final int position) {

       /* Glide.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + cartPojo.getResponse().get(position).getImage())
                .into(holder.productimageiv);*/

        for (int j = 0; j < myList.size(); j++) {
            if (myList.get(j).equalsIgnoreCase(cartPojo.get(position).getId())) {
                cartPojo.get(position).setSelected(true);
            }
        }

        holder.subjectName.setText(cartPojo.get(position).getSubject());

        holder.subjectCheck.setChecked(cartPojo.get(position).getSelected());

        holder.subjectCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cartPojo.get(position).setSelected(isChecked);

            }
        });
    }

    @Override
    public int getItemCount() {
        // return cartPojo.size();
        //return (null != filteredList ? filteredList.size() : 0);
        return (null != cartPojo ? cartPojo.size() : 0);
    }

    public class YearAdapterView extends RecyclerView.ViewHolder {

        TextView subjectName;
        CheckBox subjectCheck;

        public YearAdapterView(View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectName);
            subjectCheck = itemView.findViewById(R.id.subjectCheck);
        }

    }
}



