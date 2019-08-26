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
import com.iprismtech.studentvarsity.pojos.NotesPojo;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    List<NotesPojo.ResponseBean> notesPojo;
    private Context context;

    public NotesAdapter(Context activity, List<NotesPojo.ResponseBean> notesPojo) {
        this.notesPojo = notesPojo;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notes, viewGroup, false);
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_topic_title.setText(notesPojo.get(i).getHeading());
        viewHolder.tv_topic_sub.setText(notesPojo.get(i).getHeading());
        viewHolder.tv_note.setText(notesPojo.get(i).getDescription());
        viewHolder.tv_comments_count.setText(notesPojo.get(i).getComments() + " Comments");
        viewHolder.tv_pings_count.setText("Pings");
        viewHolder.tv_view_count.setText(notesPojo.get(i).getViews() + " Views");

        if (notesPojo.get(i).getReaded().equalsIgnoreCase("yes")) {
            viewHolder.ll_unread.setVisibility(View.GONE);
            viewHolder.ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ll_unread.setVisibility(View.VISIBLE);
            viewHolder.ll_unread_blue.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return notesPojo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_topic_img;
        TextView tv_topic_title, tv_topic_sub, tv_pings_count, tv_comments_count, tv_view_count, tv_note;
        LinearLayout ll_ping, ll_comment, ll_unread, ll_topic, ll_unread_blue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_topic_img = itemView.findViewById(R.id.iv_topic_img);
            tv_topic_title = itemView.findViewById(R.id.tv_topic_title);
            tv_topic_sub = itemView.findViewById(R.id.tv_topic_sub);
            tv_pings_count = itemView.findViewById(R.id.tv_pings_count);
            tv_comments_count = itemView.findViewById(R.id.tv_comments_count);
            tv_view_count = itemView.findViewById(R.id.tv_view_count);
            ll_ping = itemView.findViewById(R.id.ll_ping);
            ll_comment = itemView.findViewById(R.id.ll_comment);
            ll_unread = itemView.findViewById(R.id.ll_unread);
            ll_unread_blue = itemView.findViewById(R.id.ll_unread_blue);
            ll_topic = itemView.findViewById(R.id.ll_topic);
            tv_note = itemView.findViewById(R.id.tv_note);
            ll_topic.setOnClickListener(this);
            ll_ping.setOnClickListener(this);
            ll_comment.setOnClickListener(this);
            ll_unread.setOnClickListener(this);
            ll_unread_blue.setOnClickListener(this);
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
