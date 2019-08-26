package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.YearPOJO;

import java.util.ArrayList;

public class YearAdapter extends RecyclerView.Adapter<YearAdapter.YearAdapterView> {
    LayoutInflater inflater;
    Context context;
    YearPOJO cartPojo;
   public static ArrayList<String> yearcount;

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    public YearAdapter(YearPOJO cartPojo, Context context, ArrayList<String> yearcount) {
        this.context = context;
        this.cartPojo = cartPojo;
        inflater = LayoutInflater.from(context);
        this.yearcount = new ArrayList<>();
        this.yearcount = yearcount;
    }

    @Override
    public YearAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_year, parent, false);
        return new YearAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(YearAdapterView holder, int position) {

       /* Glide.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + cartPojo.getResponse().get(position).getImage())
                .into(holder.productimageiv);*/

        if (yearcount.get(position).equalsIgnoreCase("0")) {
            holder.tv_year.setBackground(null);
            holder.tv_year.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            //android:background="@drawable/courseyeartext_background"

            holder.tv_year.setBackgroundResource(R.drawable.courseyeartext_background);
            holder.tv_year.setTextColor(context.getResources().getColor(R.color.white));
        }

        holder.tv_year.setText(cartPojo.getYears().get(position).getTitle());


    }


    @Override
    public int getItemCount() {
        return cartPojo.getYears().size();
    }

    public class YearAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_year;

        public YearAdapterView(View itemView) {
            super(itemView);
            tv_year = itemView.findViewById(R.id.tv_year);
            tv_year.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }

        }
    }
}

