package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailView;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.pojos.ActivityPojo;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.iprismtech.studentvarsity.adapters.MyActivityPostsAdapter.extractYoutubeVideoId;

public class ActvityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ActivityPojo.ResponseBean> activityPojo;
    private static final int TYPE_ACTIVITY = 1;
    private static final int TYPE_DISCUSS = 2;
    private static final int TYPE_NOTES = 3;
    private static final int TYPE_VIDEOS = 4;
    private static final int TYPE_FAQS = 5;
    private static final int TYPE_MCQS = 6;
    // private static final int TYPE_QUIZ = 7;


    public ActvityAdapter(FragmentActivity activity, List<ActivityPojo.ResponseBean> activityPojo1) {
        this.context = activity;
        this.activityPojo = activityPojo1;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == TYPE_ACTIVITY) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_actvity_activty, viewGroup, false);
            return new ViewHolderActvity(view);
        } else if (i == TYPE_DISCUSS) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activity_discuss, viewGroup, false);
            return new ViewHolderDiscuss(view);
        } else if (i == TYPE_NOTES) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activty_notes, viewGroup, false);
            return new ViewHolderNotes(view);
        } else if (i == TYPE_VIDEOS) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activity_videos, viewGroup, false);
            return new ViewHolderVideos(view);
        } else if (i == TYPE_FAQS) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activity_faqs, viewGroup, false);
            return new ViewHolderFAQs(view);
        } else if (i == TYPE_MCQS) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activity_mcqs, viewGroup, false);
            return new ViewHolderMCQS(view);
        }
//        else if (i == TYPE_QUIZ) {
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_actvity_quiz, viewGroup, false);
//            return new ViewHolderQuiz(view);
//        }
        else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }


    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position, String type);
    }


    public class ViewHolderActvity extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView activity_tv_first_char, tv_activity_user_name, activity_tv_posted_on, tv_activty_chapter_name,
                tv_activity_pings_count, tv_activity_comments_count, tv_activity_view_count, tv_activity_user_post,tv_comment_count;
        ImageView activty_image;
        YouTubePlayerView youtubePlayerView_activity_video;
        LinearLayout ll_activity;

        LinearLayout all_ll_ping, all_ll_comment, all_ll_unread, all_ll_unread_blue;

        public ViewHolderActvity(View itemView) {
            super(itemView);

            activity_tv_first_char = itemView.findViewById(R.id.activity_tv_first_char);
            tv_activity_user_name = itemView.findViewById(R.id.tv_activity_user_name);
            activity_tv_posted_on = itemView.findViewById(R.id.activity_tv_posted_on);
            tv_activty_chapter_name = itemView.findViewById(R.id.tv_activty_chapter_name);
            tv_activity_pings_count = itemView.findViewById(R.id.tv_activity_pings_count);
            tv_activity_comments_count = itemView.findViewById(R.id.tv_activity_comments_count);
            tv_activity_view_count = itemView.findViewById(R.id.tv_activity_view_count);
            tv_activity_user_post = itemView.findViewById(R.id.tv_activity_user_post);
            activty_image = itemView.findViewById(R.id.activty_image);
            tv_comment_count = itemView.findViewById(R.id.tv_comment_count);
            ll_activity = itemView.findViewById(R.id.ll_activity);
            youtubePlayerView_activity_video = itemView.findViewById(R.id.youtubePlayerView_activity_video);


            all_ll_ping = itemView.findViewById(R.id.all_ll_ping);
            all_ll_comment = itemView.findViewById(R.id.all_ll_comment);
            all_ll_unread = itemView.findViewById(R.id.all_ll_unread);
            all_ll_unread_blue = itemView.findViewById(R.id.all_ll_unread_blue);

            ll_activity.setOnClickListener(this);

            all_ll_ping.setOnClickListener(this);
            all_ll_comment.setOnClickListener(this);
            all_ll_unread.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition(), activityPojo.get(getAdapterPosition()).getType());
            }
        }
    }

    public class ViewHolderDiscuss extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_first_char, tv_discuss_chapter_name,tv_discuss_comment_count, tv_discuss_user_name, tv_discuss_user_post, tv_posted_on, tv_discuss_pings_count, tv_discuss_comments_count, tv_discuss_view_count;
        ImageView discuss_image;
        LinearLayout ll_discussion;
        LinearLayout all_ll_ping, all_ll_comment, all_ll_unread, all_ll_unread_blue;
        YouTubePlayerView video_youtubePlayerView_activity;

        public ViewHolderDiscuss(View itemView) {
            super(itemView);


            tv_first_char = itemView.findViewById(R.id.tv_first_char);
            tv_discuss_user_name = itemView.findViewById(R.id.tv_discuss_user_name);
            tv_discuss_chapter_name = itemView.findViewById(R.id.tv_discuss_chapter_name);
            tv_discuss_user_post = itemView.findViewById(R.id.tv_discuss_user_post);
            tv_discuss_pings_count = itemView.findViewById(R.id.tv_discuss_pings_count);
            tv_discuss_comments_count = itemView.findViewById(R.id.tv_discuss_comments_count);
            tv_discuss_view_count = itemView.findViewById(R.id.tv_discuss_view_count);
            tv_posted_on = itemView.findViewById(R.id.tv_posted_on);
            ll_discussion = itemView.findViewById(R.id.ll_discussion);
            discuss_image = itemView.findViewById(R.id.discuss_image);
            tv_discuss_comment_count = itemView.findViewById(R.id.tv_discuss_comment_count);
            video_youtubePlayerView_activity = itemView.findViewById(R.id.video_youtubePlayerView_activity);

            all_ll_ping = itemView.findViewById(R.id.all_ll_ping);
            all_ll_comment = itemView.findViewById(R.id.all_ll_comment);
            all_ll_unread = itemView.findViewById(R.id.all_ll_unread);
            all_ll_unread_blue = itemView.findViewById(R.id.all_ll_unread_blue);

            ll_discussion.setOnClickListener(this);

            all_ll_ping.setOnClickListener(this);
            all_ll_comment.setOnClickListener(this);
            all_ll_unread.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition(), activityPojo.get(getAdapterPosition()).getType());
            }
        }
    }

    public class ViewHolderNotes extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView notes_tv_topic_title, notes_tv_topic_sub, notes_tv_pings_count,tv_notes_comment_count, notes_tv_comments_count, notes_tv_view_count, tv_notes_desc;
        LinearLayout notes_ll_ping, notes_ll_comment, notes_ll_unread, notes_ll_topic, ll_notes;

        LinearLayout all_ll_ping, all_ll_comment, all_ll_unread, all_ll_unread_blue;


        public ViewHolderNotes(View itemView) {
            super(itemView);


            notes_tv_topic_title = itemView.findViewById(R.id.notes_tv_topic_title);
            notes_tv_topic_sub = itemView.findViewById(R.id.notes_tv_topic_sub);
            notes_tv_pings_count = itemView.findViewById(R.id.notes_tv_pings_count);
            notes_tv_comments_count = itemView.findViewById(R.id.notes_tv_comments_count);
            notes_tv_view_count = itemView.findViewById(R.id.notes_tv_view_count);
            notes_ll_ping = itemView.findViewById(R.id.notes_ll_ping);
            notes_ll_comment = itemView.findViewById(R.id.notes_ll_comment);
            notes_ll_unread = itemView.findViewById(R.id.notes_ll_unread);
            notes_ll_topic = itemView.findViewById(R.id.notes_ll_topic);
            ll_notes = itemView.findViewById(R.id.ll_notes);
            tv_notes_desc = itemView.findViewById(R.id.tv_notes_desc);
            tv_notes_comment_count = itemView.findViewById(R.id.tv_notes_comment_count);

            all_ll_ping = itemView.findViewById(R.id.all_ll_ping);
            all_ll_comment = itemView.findViewById(R.id.all_ll_comment);
            all_ll_unread = itemView.findViewById(R.id.all_ll_unread);
            all_ll_unread_blue = itemView.findViewById(R.id.all_ll_unread_blue);

            ll_notes.setOnClickListener(this);

            all_ll_ping.setOnClickListener(this);
            all_ll_comment.setOnClickListener(this);
            all_ll_unread.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition(), activityPojo.get(getAdapterPosition()).getType());
            }
        }
    }

    public class ViewHolderVideos extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView video_tv_heading,tv_videos_count;
        ImageView video_img_video, video_img_play;
        protected RelativeLayout video_relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView video_youTubeThumbnailView;
        protected ImageView video_playButton;
        LinearLayout ll_ping_video, ll_comment_video, ll_unread_video, ll_unread_blue_video, ll_item_video;

        LinearLayout all_ll_ping, all_ll_comment, all_ll_unread, all_ll_unread_blue;


        YouTubePlayerView video_youtubePlayerView;
        TextView video_pings, video_comments, video_views;

        public ViewHolderVideos(View itemView) {
            super(itemView);


            video_tv_heading = itemView.findViewById(R.id.video_tv_heading);
            video_youtubePlayerView = itemView.findViewById(R.id.video_youtubePlayerView);
            video_pings = itemView.findViewById(R.id.video_pings);
            video_comments = itemView.findViewById(R.id.video_comments);
            video_views = itemView.findViewById(R.id.video_views);
            ll_ping_video = itemView.findViewById(R.id.ll_ping_video);
            ll_comment_video = itemView.findViewById(R.id.ll_comment_video);
            ll_unread_video = itemView.findViewById(R.id.ll_unread_video);
            ll_unread_blue_video = itemView.findViewById(R.id.ll_unread_blue_video);
            ll_item_video = itemView.findViewById(R.id.ll_item_video);
            tv_videos_count = itemView.findViewById(R.id.tv_videos_count);

            all_ll_ping = itemView.findViewById(R.id.all_ll_ping);
            all_ll_comment = itemView.findViewById(R.id.all_ll_comment);
            all_ll_unread = itemView.findViewById(R.id.all_ll_unread);
            all_ll_unread_blue = itemView.findViewById(R.id.all_ll_unread_blue);

            ll_item_video.setOnClickListener(this);
            all_ll_ping.setOnClickListener(this);
            all_ll_comment.setOnClickListener(this);
            all_ll_unread.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition(), activityPojo.get(getAdapterPosition()).getType());
            }
        }
    }

    public class ViewHolderFAQs extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView faqs_tv_question,tv_faq_comment_count;
        TextView faqs_tv_answer, faqs_tv_pingcount, faqs_tv_cmntcount, faqs_tv_viewcount;
        TextView faqs_tv_chapter;
        LinearLayout ll_ping_faq, ll_comment_faq, ll_unread_faq, ll_unread_blue_faq, ll_item_faqs, ll_faqs;

        LinearLayout all_ll_ping, all_ll_comment, all_ll_unread, all_ll_unread_blue;


        public ViewHolderFAQs(View itemView) {
            super(itemView);

            faqs_tv_question = itemView.findViewById(R.id.faqs_tv_question);
            faqs_tv_answer = itemView.findViewById(R.id.faqs_tv_answer);
            faqs_tv_pingcount = itemView.findViewById(R.id.faqs_tv_pingcount);
            faqs_tv_cmntcount = itemView.findViewById(R.id.faqs_tv_cmntcount);
            faqs_tv_viewcount = itemView.findViewById(R.id.faqs_tv_viewcount);
            faqs_tv_chapter = itemView.findViewById(R.id.faqs_tv_chapter);
            ll_ping_faq = itemView.findViewById(R.id.ll_ping_faq);
            ll_comment_faq = itemView.findViewById(R.id.ll_comment_faq);
            ll_unread_faq = itemView.findViewById(R.id.ll_unread_faq);
            ll_unread_blue_faq = itemView.findViewById(R.id.ll_unread_blue_faq);
            ll_item_faqs = itemView.findViewById(R.id.ll_item_faqs);
            ll_faqs = itemView.findViewById(R.id.ll_faqs);
            tv_faq_comment_count = itemView.findViewById(R.id.tv_faq_comment_count);

            all_ll_ping = itemView.findViewById(R.id.all_ll_ping);
            all_ll_comment = itemView.findViewById(R.id.all_ll_comment);
            all_ll_unread = itemView.findViewById(R.id.all_ll_unread);
            all_ll_unread_blue = itemView.findViewById(R.id.all_ll_unread_blue);


            ll_item_faqs.setOnClickListener(this);
            ll_faqs.setOnClickListener(this);

            all_ll_ping.setOnClickListener(this);
            all_ll_comment.setOnClickListener(this);
            all_ll_unread.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition(), activityPojo.get(getAdapterPosition()).getType());
            }
        }
    }

    public class ViewHolderMCQS extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mcqs_tv_chapter, mcqs_tv_question, mcqs_tv_ans, mcqs_tv_pings, mcqs_tv_comments, mcqs_tv_views,tv_mcq_comment_count;
        RecyclerView mcqs_rview_choices;
        LinearLayout mcqs_ll_question, ll_ping_mcq, ll_comment_mcq, ll_unread_mcq, ll_unread_blue_mcq;
        LinearLayout all_ll_ping, all_ll_comment, all_ll_unread, all_ll_unread_blue;


        public ViewHolderMCQS(View itemView) {
            super(itemView);

            mcqs_tv_chapter = itemView.findViewById(R.id.mcqs_tv_chapter);
            mcqs_tv_question = itemView.findViewById(R.id.mcqs_tv_question);
            mcqs_tv_ans = itemView.findViewById(R.id.mcqs_tv_ans);
            mcqs_rview_choices = itemView.findViewById(R.id.mcqs_rview_choices);
            mcqs_ll_question = itemView.findViewById(R.id.mcqs_ll_question);
            mcqs_tv_pings = itemView.findViewById(R.id.mcqs_pings);
            mcqs_tv_comments = itemView.findViewById(R.id.mcqs_comments);
            mcqs_tv_views = itemView.findViewById(R.id.mcqs_views);
            all_ll_comment = itemView.findViewById(R.id.all_ll_comment);
            all_ll_unread = itemView.findViewById(R.id.all_ll_unread);
            tv_mcq_comment_count = itemView.findViewById(R.id.tv_mcq_comment_count);
            all_ll_unread_blue = itemView.findViewById(R.id.all_ll_unread_blue);
            all_ll_ping = itemView.findViewById(R.id.all_ll_ping);

            mcqs_ll_question.setOnClickListener(this);
            all_ll_ping.setOnClickListener(this);
            all_ll_comment.setOnClickListener(this);
            all_ll_unread.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition(), activityPojo.get(getAdapterPosition()).getType());
            }
        }
    }

//    public class ViewHolderQuiz extends RecyclerView.ViewHolder implements View.OnClickListener {
//        RecyclerView quiz_rview_choices;
//        TextView quiz_question;
//        LinearLayout ll_ping_quiz, ll_comment_quiz, ll_unread_quiz, ll_unread_blue_quiz;
//        LinearLayout all_ll_ping, all_ll_comment, all_ll_unread, all_ll_unread_blue;
//
//
//        public ViewHolderQuiz(View itemView) {
//            super(itemView);
//
//            quiz_rview_choices = itemView.findViewById(R.id.quiz_rview_choices);
//            quiz_question = itemView.findViewById(R.id.quiz_question);
//            ll_ping_quiz = itemView.findViewById(R.id.ll_ping_quiz);
//            ll_comment_quiz = itemView.findViewById(R.id.ll_comment_quiz);
//            ll_unread_quiz = itemView.findViewById(R.id.ll_unread_quiz);
//            ll_unread_blue_quiz = itemView.findViewById(R.id.ll_unread_blue_quiz);
//
//            all_ll_comment = itemView.findViewById(R.id.all_ll_comment);
//            all_ll_unread = itemView.findViewById(R.id.all_ll_unread);
//            all_ll_unread_blue = itemView.findViewById(R.id.all_ll_unread_blue);
//            all_ll_ping = itemView.findViewById(R.id.all_ll_ping);
//
//            quiz_question.setOnClickListener(this);
//            all_ll_ping.setOnClickListener(this);
//            all_ll_comment.setOnClickListener(this);
//            all_ll_unread.setOnClickListener(this);
//
//
//        }


//        @Override
//        public void onClick(View v) {
//            if (mListner != null) {
//                mListner.onItemClick(v, getAdapterPosition(), activityPojo.get(getAdapterPosition()).getType());
//            }
//        }
//    }


//        "type":"notes" (or) “videos” (or) “faqs” (or) “mcqs” (or) “quiz” (or) “discuss” (or) “activity”

    @Override
    public int getItemViewType(int position) {
        ActivityPojo.ResponseBean item = activityPojo.get(position);
        if (item.getType().equalsIgnoreCase("activity"))
            return TYPE_ACTIVITY;
        else if (item.getType().equalsIgnoreCase("discuss"))
            return TYPE_DISCUSS;
        else if (item.getType().equalsIgnoreCase("notes"))
            return TYPE_NOTES;
        else if (item.getType().equalsIgnoreCase("videos"))
            return TYPE_VIDEOS;
        else if (item.getType().equalsIgnoreCase("faqs"))
            return TYPE_FAQS;
        else if (item.getType().equalsIgnoreCase("mcqs"))
            return TYPE_MCQS;
            //  else if (item.getType().equalsIgnoreCase("quiz"))
            //   return TYPE_QUIZ;
        else {
            return -1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        switch (viewHolder.getItemViewType()) {
            case TYPE_ACTIVITY:
                initActivity((ViewHolderActvity) viewHolder, i);
                break;
            case TYPE_DISCUSS:
                initDiscuss((ViewHolderDiscuss) viewHolder, i);
                break;
            case TYPE_NOTES:
                initNotes((ViewHolderNotes) viewHolder, i);
                break;
            case TYPE_VIDEOS:
                initVideos((ViewHolderVideos) viewHolder, i);
                break;
            case TYPE_FAQS:
                initFAQs((ViewHolderFAQs) viewHolder, i);
                break;
            case TYPE_MCQS:
                initMCQS((ViewHolderMCQS) viewHolder, i);
                break;
//            case TYPE_QUIZ:
//
//                initQuiz((ViewHolderQuiz) viewHolder, i);

            //      break;
            default:
                break;
        }
    }

    private void initMCQS(ViewHolderMCQS viewHolder, int i) {
        if (activityPojo.get(i).getReaded().equalsIgnoreCase("yes")) {
            viewHolder.all_ll_unread.setVisibility(View.GONE);
            viewHolder.all_ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            viewHolder.all_ll_unread.setVisibility(View.VISIBLE);
            viewHolder.all_ll_unread_blue.setVisibility(View.GONE);
        }
        //   viewHolder.mcqs_tv_chapter.setText(activityPojo.get(i).getChapter());
        viewHolder.mcqs_tv_chapter.setText("MCQs");
        viewHolder.mcqs_tv_question.setText(activityPojo.get(i).getQuestion());
        viewHolder.mcqs_tv_ans.setText(activityPojo.get(i).getAnswer());

        ActivityMCQsAdapter itemListDataAdapter = new ActivityMCQsAdapter(context, activityPojo.get(i).getMcq_options(), activityPojo.get(i).getAnswer(), viewHolder.mcqs_tv_ans);
        viewHolder.mcqs_rview_choices.setHasFixedSize(true);
        viewHolder.mcqs_rview_choices.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        viewHolder.mcqs_rview_choices.setAdapter(itemListDataAdapter);

        //  viewHolder.mcqs_tv_pings.setText(activityPojo.get(i).getPings() + " Pings");
        //viewHolder.mcqs_tv_comments.setText(activityPojo.get(i).getComments() + " Comments");
        viewHolder.tv_mcq_comment_count.setText(activityPojo.get(i).getComments() + " Comments");
        //viewHolder.mcqs_tv_views.setText(activityPojo.get(i).getViews() + " Views");

    }

    private void initFAQs(ViewHolderFAQs viewHolder, int i) {
        if (activityPojo.get(i).getReaded().equalsIgnoreCase("yes")) {
            viewHolder.all_ll_unread.setVisibility(View.GONE);
            viewHolder.all_ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            viewHolder.all_ll_unread.setVisibility(View.VISIBLE);
            viewHolder.all_ll_unread_blue.setVisibility(View.GONE);
        }
        viewHolder.faqs_tv_question.setText(activityPojo.get(i).getQuestion());
        viewHolder.faqs_tv_answer.setText(activityPojo.get(i).getAnswer());
        // viewHolder.faqs_tv_chapter.setText(activityPojo.get(i).getChapter());
        viewHolder.faqs_tv_chapter.setText("FAQs");
        //viewHolder.faqs_tv_pingcount.setText(activityPojo.get(i).getPings() + " Pings");
     //   viewHolder.faqs_tv_cmntcount.setText(activityPojo.get(i).getComments() + " Comments");
        viewHolder.tv_faq_comment_count.setText(activityPojo.get(i).getComments() + " Comments");
       // viewHolder.faqs_tv_viewcount.setText(activityPojo.get(i).getViews() + " Views");

    }

    private void initVideos(ViewHolderVideos viewHolder, final int i) {
        viewHolder.video_youtubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String videoId = extractYoutubeVideoId(activityPojo.get(i).getVideo_link());
                        initializedYouTubePlayer.cueVideo(videoId, 0);
                    }
                });
            }
        }, true);

        // viewHolder.video_tv_heading.setText(activityPojo.get(i).getHeading());
        viewHolder.video_tv_heading.setText("Videos");
        viewHolder.tv_videos_count.setText(activityPojo.get(i).getComments() + " Comments");
      //  viewHolder.video_comments.setText(activityPojo.get(i).getComments() + " Comments");
        // viewHolder.video_pings.setText(activityPojo.get(i).getPings() + " Pings");
       // viewHolder.video_views.setText(activityPojo.get(i).getViews() + " Views");
    }

    private void initNotes(ViewHolderNotes viewHolder, int i) {
        if (activityPojo.get(i).getReaded().equalsIgnoreCase("yes")) {
            viewHolder.all_ll_unread.setVisibility(View.GONE);
            viewHolder.all_ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            viewHolder.all_ll_unread.setVisibility(View.VISIBLE);
            viewHolder.all_ll_unread_blue.setVisibility(View.GONE);
        }
        //viewHolder.notes_tv_topic_title.setText(activityPojo.get(i).getHeading());
        viewHolder.notes_tv_topic_title.setText("Notes");
        viewHolder.notes_tv_topic_sub.setText(activityPojo.get(i).getHeading());
       // viewHolder.notes_tv_comments_count.setText(activityPojo.get(i).getComments() + " Comments");
        viewHolder.tv_notes_comment_count.setText(activityPojo.get(i).getComments() + " Comments");
        //  viewHolder.notes_tv_pings_count.setText(activityPojo.get(i).getPings() + " Pings");
      //  viewHolder.notes_tv_view_count.setText(activityPojo.get(i).getViews() + " Views");
        viewHolder.tv_notes_desc.setText(activityPojo.get(i).getDescription());
    }

    private void initDiscuss(ViewHolderDiscuss viewHolder, final int i) {
        if (activityPojo.get(i).getReaded().equalsIgnoreCase("yes")) {
            viewHolder.all_ll_unread.setVisibility(View.GONE);
            viewHolder.all_ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            viewHolder.all_ll_unread.setVisibility(View.VISIBLE);
            viewHolder.all_ll_unread_blue.setVisibility(View.GONE);
        }
        viewHolder.tv_discuss_chapter_name.setText(activityPojo.get(i).getChapter());
      //  viewHolder.tv_discuss_comments_count.setText(activityPojo.get(i).getComments() + " Comments");
        viewHolder.tv_discuss_comment_count.setText(activityPojo.get(i).getComments() + " Comments");
        viewHolder.tv_discuss_user_name.setText(activityPojo.get(i).getName());
        //viewHolder.tv_discuss_pings_count.setText(activityPojo.get(i).getPings() + " Pings");
      //  viewHolder.tv_discuss_view_count.setText(activityPojo.get(i).getViews() + " Views");
        viewHolder.tv_discuss_user_post.setText(activityPojo.get(i).getDescription());
        viewHolder.tv_first_char.setText(activityPojo.get(i).getName().charAt(0) + "");
        viewHolder.tv_posted_on.setText(activityPojo.get(i).getPosted_on() + " -");

        try {


            // if (activityPojo.get(i).getYoutube().equalsIgnoreCase("") || activityPojo.get(i).getYoutube().isEmpty() || activityPojo.get(i).getYoutube().length() < 1) {
            if (activityPojo.get(i).getImage() != null && activityPojo.get(i).getImage().length() > 6) {
                viewHolder.video_youtubePlayerView_activity.setVisibility(View.GONE);
                viewHolder.discuss_image.setVisibility(View.VISIBLE);
                Picasso.with(context)
                        .load(NetworkConstants.URL.Imagepath_URL + activityPojo.get(i).getImage())
                        .into(viewHolder.discuss_image);
            } else if (activityPojo.get(i).getYoutube().equalsIgnoreCase("") && activityPojo.get(i).getImage() == null) {
                viewHolder.video_youtubePlayerView_activity.setVisibility(View.GONE);
                viewHolder.discuss_image.setVisibility(View.GONE);
            } else if (activityPojo.get(i).getImage() == null) {
                viewHolder.video_youtubePlayerView_activity.setVisibility(View.VISIBLE);
                viewHolder.discuss_image.setVisibility(View.GONE);

                viewHolder.video_youtubePlayerView_activity.initialize(new YouTubePlayerInitListener() {
                    @Override
                    public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                        initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady() {
                                String videoId = extractYoutubeVideoId(activityPojo.get(i).getYoutube());
                                initializedYouTubePlayer.cueVideo(videoId, 0);
                            }
                        });
                    }
                }, true);

            } else {
                viewHolder.video_youtubePlayerView_activity.setVisibility(View.GONE);
                viewHolder.discuss_image.setVisibility(View.GONE);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("you tube error", e.getMessage());
        }
    }

    private void initActivity(ViewHolderActvity viewHolder, final int i) {

        if (activityPojo.get(i).getReaded().equalsIgnoreCase("yes")) {
            viewHolder.all_ll_unread.setVisibility(View.GONE);
            viewHolder.all_ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            viewHolder.all_ll_unread.setVisibility(View.VISIBLE);
            viewHolder.all_ll_unread_blue.setVisibility(View.GONE);
        }
        viewHolder.tv_activty_chapter_name.setText(activityPojo.get(i).getChapter());
        //viewHolder.tv_activity_comments_count.setText(activityPojo.get(i).getComments() + " Comments");
        viewHolder.tv_comment_count.setText(activityPojo.get(i).getComments() + " Comments");
        // viewHolder.tv_activity_pings_count.setText(activityPojo.get(i).getPings() + " Pings");
       // viewHolder.tv_activity_view_count.setText(activityPojo.get(i).getViews() + " Views");
        viewHolder.tv_activity_user_post.setText(activityPojo.get(i).getDescription());
        viewHolder.activity_tv_first_char.setText(activityPojo.get(i).getName().charAt(0) + "");
        viewHolder.activity_tv_posted_on.setText(activityPojo.get(i).getPosted_on() + " -");
        viewHolder.tv_activity_user_name.setText(activityPojo.get(i).getName());
        // viewHolder.tv_activity_user_name.setText(activityPojo.get(i).getName());

//
        try {


            // if (activityPojo.get(i).getYoutube().equalsIgnoreCase("") || activityPojo.get(i).getYoutube().isEmpty() || activityPojo.get(i).getYoutube().length() < 1) {
            if (activityPojo.get(i).getImage() != null && activityPojo.get(i).getImage().length() > 6) {
                viewHolder.youtubePlayerView_activity_video.setVisibility(View.GONE);
                viewHolder.activty_image.setVisibility(View.VISIBLE);
                Picasso.with(context)
                        .load(NetworkConstants.URL.Imagepath_URL + activityPojo.get(i).getImage())
                        .into(viewHolder.activty_image);
            } else if (activityPojo.get(i).getYoutube().length() > 5) {
                viewHolder.youtubePlayerView_activity_video.setVisibility(View.VISIBLE);
                viewHolder.activty_image.setVisibility(View.GONE);

                viewHolder.youtubePlayerView_activity_video.initialize(new YouTubePlayerInitListener() {
                    @Override
                    public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                        initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady() {
                                String videoId = extractYoutubeVideoId(activityPojo.get(i).getYoutube());
                                initializedYouTubePlayer.cueVideo(videoId, 0);
                            }
                        });
                    }
                }, true);
            } else if (activityPojo.get(i).getYoutube().equalsIgnoreCase("") && activityPojo.get(i).getImage() == null) {
                viewHolder.youtubePlayerView_activity_video.setVisibility(View.GONE);
                viewHolder.activty_image.setVisibility(View.GONE);
            } else if (activityPojo.get(i).getImage() == null) {
                viewHolder.youtubePlayerView_activity_video.setVisibility(View.VISIBLE);
                viewHolder.activty_image.setVisibility(View.GONE);

                viewHolder.youtubePlayerView_activity_video.initialize(new YouTubePlayerInitListener() {
                    @Override
                    public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                        initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady() {
                                String videoId = extractYoutubeVideoId(activityPojo.get(i).getYoutube());
                                initializedYouTubePlayer.cueVideo(videoId, 0);
                            }
                        });
                    }
                }, true);

            } else {
                viewHolder.youtubePlayerView_activity_video.setVisibility(View.GONE);
                viewHolder.activty_image.setVisibility(View.GONE);

            }

//            //viewHolder.ll_hor_item.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("you tube error", e.getMessage());
        }
    }

//    private void initQuiz(ViewHolderQuiz viewHolder, int i) {
//
//        if (activityPojo.get(i).getReaded().equalsIgnoreCase("yes")) {
//            viewHolder.all_ll_unread.setVisibility(View.GONE);
//            viewHolder.all_ll_unread_blue.setVisibility(View.VISIBLE);
//        } else {
//            viewHolder.all_ll_unread.setVisibility(View.VISIBLE);
//            viewHolder.all_ll_unread_blue.setVisibility(View.GONE);
//        }
//        ActivityQuizAdapter quizListDataAdapter = new ActivityQuizAdapter(context, activityPojo.get(i).getQuiz_options());
//        viewHolder.quiz_rview_choices.setHasFixedSize(true);
//        viewHolder.quiz_rview_choices.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//        viewHolder.quiz_rview_choices.setAdapter(quizListDataAdapter);
//
//        viewHolder.quiz_question.setText(activityPojo.get(i).getQuestion());
//
//    }

    public static String extractYoutubeVideoId(String ytUrl) {

        if (ytUrl.contains("youtube.com")) {
            String vId = null;

            String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(ytUrl);

            if (matcher.find()) {
                vId = matcher.group();
                Log.d("Video ID", vId);
            }
            return vId;
        } else {
            String vId2 = null;
            Pattern pattern = Pattern.compile(
                    "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(ytUrl);
            if (matcher.matches()) {
                vId2 = matcher.group(1);
            }
            return vId2;

//        String vId = null;
//
//        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
//
//        Pattern compiledPattern = Pattern.compile(pattern);
//        Matcher matcher = compiledPattern.matcher(ytUrl);
//
//        if (matcher.find()) {
//            vId = matcher.group();
//            Log.d("Video ID", vId);
//        }
//        return vId;
        }
    }

    @Override
    public int getItemCount() {
        return activityPojo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout ll_faqs, ll_notes, ll_video, ll_mcqs, ll_quiz, ll_discussion, ll_activity;
        LinearLayout ll_hor_item;

        ImageView iv_topic_img;
        TextView notes_tv_topic_title, notes_tv_topic_sub, notes_tv_pings_count, notes_tv_comments_count, notes_tv_view_count;
        LinearLayout notes_ll_ping, notes_ll_comment, notes_ll_unread, notes_ll_topic;


        TextView faqs_tv_question;
        TextView faqs_tv_answer, faqs_tv_pingcount, faqs_tv_cmntcount, faqs_tv_viewcount;
        TextView faqs_tv_chapter;
        LinearLayout ll_ping_faq, ll_comment_faq, ll_unread_faq, ll_unread_blue_faq;


        TextView mcqs_tv_chapter, mcqs_tv_question, mcqs_tv_ans, mcqs_tv_pings, mcqs_tv_comments, mcqs_tv_views;
        RecyclerView mcqs_rview_choices;
        LinearLayout mcqs_ll_question, ll_ping_mcq, ll_comment_mcq, ll_unread_mcq, ll_unread_blue_mcq;


        TextView video_tv_heading;
        ImageView video_img_video, video_img_play;
        protected RelativeLayout video_relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView video_youTubeThumbnailView;
        protected ImageView video_playButton;
        LinearLayout ll_ping_video, ll_comment_video, ll_unread_video, ll_unread_blue_video;

        YouTubePlayerView video_youtubePlayerView, video_youtubePlayerView_activity;
        TextView video_pings, video_comments, video_views;

        RecyclerView quiz_rview_choices;
        TextView quiz_question;
        LinearLayout ll_ping_quiz, ll_comment_quiz, ll_unread_quiz, ll_unread_blue_quiz;
        LinearLayout all_ll_ping, all_ll_comment, all_ll_unread, all_ll_unread_blue, ll_item_video, ll_item_faqs;


        TextView tv_first_char, tv_discuss_chapter_name, tv_discuss_user_post, tv_posted_on, tv_discuss_pings_count, tv_discuss_comments_count, tv_discuss_view_count;


        TextView activity_tv_first_char, tv_activity_user_name, activity_tv_posted_on, tv_activty_chapter_name,
                tv_activity_pings_count, tv_activity_comments_count, tv_activity_view_count, tv_activity_user_post;
        ImageView activty_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_faqs = itemView.findViewById(R.id.ll_faqs);
            ll_notes = itemView.findViewById(R.id.ll_notes);
            ll_video = itemView.findViewById(R.id.ll_video);
            ll_mcqs = itemView.findViewById(R.id.ll_mcqs);
            ll_quiz = itemView.findViewById(R.id.ll_quiz);
            ll_discussion = itemView.findViewById(R.id.ll_discussion);
            ll_hor_item = itemView.findViewById(R.id.ll_hor_item);
            ll_activity = itemView.findViewById(R.id.ll_activity);
            ll_item_faqs = itemView.findViewById(R.id.ll_item_faqs);
            ll_item_video = itemView.findViewById(R.id.ll_item_video);
            mcqs_ll_question = itemView.findViewById(R.id.mcqs_ll_question);
            quiz_question = itemView.findViewById(R.id.quiz_question);
            activty_image = itemView.findViewById(R.id.activty_image);
            video_youtubePlayerView_activity = itemView.findViewById(R.id.video_youtubePlayerView_activity);


            ll_item_faqs.setOnClickListener(this);
            ll_faqs.setOnClickListener(this);
            ll_notes.setOnClickListener(this);
            ll_item_video.setOnClickListener(this);
            mcqs_ll_question.setOnClickListener(this);
            quiz_question.setOnClickListener(this);
            ll_discussion.setOnClickListener(this);
            ll_activity.setOnClickListener(this);


            activity_tv_first_char = itemView.findViewById(R.id.activity_tv_first_char);
            tv_activity_user_name = itemView.findViewById(R.id.tv_activity_user_name);
            activity_tv_posted_on = itemView.findViewById(R.id.activity_tv_posted_on);
            tv_activty_chapter_name = itemView.findViewById(R.id.tv_activty_chapter_name);
            tv_activity_pings_count = itemView.findViewById(R.id.tv_activity_pings_count);
            tv_activity_comments_count = itemView.findViewById(R.id.tv_activity_comments_count);
            tv_activity_view_count = itemView.findViewById(R.id.tv_activity_view_count);
            tv_activity_user_post = itemView.findViewById(R.id.tv_activity_user_post);


            tv_first_char = itemView.findViewById(R.id.tv_first_char);
            tv_discuss_chapter_name = itemView.findViewById(R.id.tv_discuss_chapter_name);
            tv_discuss_user_post = itemView.findViewById(R.id.tv_discuss_user_post);
            tv_discuss_pings_count = itemView.findViewById(R.id.tv_discuss_pings_count);
            tv_discuss_comments_count = itemView.findViewById(R.id.tv_discuss_comments_count);
            tv_discuss_view_count = itemView.findViewById(R.id.tv_discuss_view_count);
            tv_posted_on = itemView.findViewById(R.id.tv_posted_on);


            notes_tv_topic_title = itemView.findViewById(R.id.notes_tv_topic_title);
            notes_tv_topic_sub = itemView.findViewById(R.id.notes_tv_topic_sub);
            notes_tv_pings_count = itemView.findViewById(R.id.notes_tv_pings_count);
            notes_tv_comments_count = itemView.findViewById(R.id.notes_tv_comments_count);
            notes_tv_view_count = itemView.findViewById(R.id.notes_tv_view_count);
            notes_ll_ping = itemView.findViewById(R.id.notes_ll_ping);
            notes_ll_comment = itemView.findViewById(R.id.notes_ll_comment);
            notes_ll_unread = itemView.findViewById(R.id.notes_ll_unread);
            notes_ll_topic = itemView.findViewById(R.id.notes_ll_topic);


            all_ll_ping = itemView.findViewById(R.id.all_ll_ping);
            all_ll_comment = itemView.findViewById(R.id.all_ll_comment);
            all_ll_unread = itemView.findViewById(R.id.all_ll_unread);
            all_ll_unread_blue = itemView.findViewById(R.id.all_ll_unread_blue);

            all_ll_ping.setOnClickListener(this);
            all_ll_comment.setOnClickListener(this);
            all_ll_unread.setOnClickListener(this);

//            notes_ll_ping.setOnClickListener(this);
//            notes_ll_unread.setOnClickListener(this);
//            notes_ll_comment.setOnClickListener(this);


            faqs_tv_question = itemView.findViewById(R.id.faqs_tv_question);
            faqs_tv_answer = itemView.findViewById(R.id.faqs_tv_answer);
            faqs_tv_pingcount = itemView.findViewById(R.id.faqs_tv_pingcount);
            faqs_tv_cmntcount = itemView.findViewById(R.id.faqs_tv_cmntcount);
            faqs_tv_viewcount = itemView.findViewById(R.id.faqs_tv_viewcount);
            faqs_tv_chapter = itemView.findViewById(R.id.faqs_tv_chapter);
            ll_ping_faq = itemView.findViewById(R.id.ll_ping_faq);
            ll_comment_faq = itemView.findViewById(R.id.ll_comment_faq);
            ll_unread_faq = itemView.findViewById(R.id.ll_unread_faq);
            ll_unread_blue_faq = itemView.findViewById(R.id.ll_unread_blue_faq);


//            ll_ping_faq.setOnClickListener(this);
//            ll_comment_faq.setOnClickListener(this);
//            ll_unread_faq.setOnClickListener(this);


            video_tv_heading = itemView.findViewById(R.id.video_tv_heading);
            video_youtubePlayerView = itemView.findViewById(R.id.video_youtubePlayerView);
            video_pings = itemView.findViewById(R.id.video_pings);
            video_comments = itemView.findViewById(R.id.video_comments);
            video_views = itemView.findViewById(R.id.video_views);
            ll_ping_video = itemView.findViewById(R.id.ll_ping_video);
            ll_comment_video = itemView.findViewById(R.id.ll_comment_video);
            ll_unread_video = itemView.findViewById(R.id.ll_unread_video);
            ll_unread_blue_video = itemView.findViewById(R.id.ll_unread_blue_video);


//            ll_ping_video.setOnClickListener(this);
//            ll_comment_video.setOnClickListener(this);
//            ll_unread_video.setOnClickListener(this);
//

            mcqs_tv_chapter = itemView.findViewById(R.id.mcqs_tv_chapter);
            mcqs_tv_question = itemView.findViewById(R.id.mcqs_tv_question);
            mcqs_tv_ans = itemView.findViewById(R.id.mcqs_tv_ans);
            mcqs_rview_choices = itemView.findViewById(R.id.mcqs_rview_choices);
            mcqs_ll_question = itemView.findViewById(R.id.mcqs_ll_question);
            mcqs_tv_pings = itemView.findViewById(R.id.mcqs_pings);
            mcqs_tv_comments = itemView.findViewById(R.id.mcqs_comments);
            mcqs_tv_views = itemView.findViewById(R.id.mcqs_views);


            quiz_rview_choices = itemView.findViewById(R.id.quiz_rview_choices);
            quiz_question = itemView.findViewById(R.id.quiz_question);
            ll_ping_quiz = itemView.findViewById(R.id.ll_ping_quiz);
            ll_comment_quiz = itemView.findViewById(R.id.ll_comment_quiz);
            ll_unread_quiz = itemView.findViewById(R.id.ll_unread_quiz);
            ll_unread_blue_quiz = itemView.findViewById(R.id.ll_unread_blue_quiz);

//            ll_comment_quiz.setOnClickListener(this);
//            ll_unread_quiz.setOnClickListener(this);
//            ll_ping_quiz.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition(), activityPojo.get(getAdapterPosition()).getType());
            }
        }
    }
}
