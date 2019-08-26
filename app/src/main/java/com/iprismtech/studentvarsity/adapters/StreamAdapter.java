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

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.StreamAdapterView> {
    LayoutInflater inflater;
    Context context;
    YearPOJO cartPojo;
    public static ArrayList<String> streamcount;

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    public StreamAdapter(YearPOJO cartPojo, Context context, ArrayList<String> streamcount) {
        this.context = context;
        this.cartPojo = cartPojo;
        inflater = LayoutInflater.from(context);
        this.streamcount = new ArrayList<>();
        this.streamcount = streamcount;
    }

    @Override
    public StreamAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_streams, parent, false);
        return new StreamAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(StreamAdapterView holder, int position) {

       /* Glide.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + cartPojo.getResponse().get(position).getImage())
                .into(holder.productimageiv);*/


        if (streamcount.get(position).equalsIgnoreCase("0")) {
            holder.tv_stream.setBackgroundResource(R.drawable.bg_storke);
            holder.tv_stream.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            //android:background="@drawable/courseyeartext_background"

            holder.tv_stream.setBackgroundResource(R.drawable.courseyeartext_background);
            holder.tv_stream.setTextColor(context.getResources().getColor(R.color.white));
        }

        holder.tv_stream.setText(cartPojo.getStreams().get(position).getTitle());


    }


    @Override
    public int getItemCount() {
        return cartPojo.getStreams().size();
    }

    public class StreamAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_stream;

        public StreamAdapterView(View itemView) {
            super(itemView);
            tv_stream = itemView.findViewById(R.id.tv_stream);
            tv_stream.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }

        }
    }
}
