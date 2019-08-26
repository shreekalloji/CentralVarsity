package com.iprismtech.studentvarsity.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
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
import com.iprismtech.studentvarsity.dao.AdapterCallBack;
import com.iprismtech.studentvarsity.dao.VideoCallBack;
import com.iprismtech.studentvarsity.pojos.VideosListPOJO;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.utils.YouTubePlayerTracker;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChapterVideoListAdapter extends RecyclerView.Adapter<ChapterVideoListAdapter.YearAdapterView> {
    LayoutInflater inflater;
    Activity context;
    List<VideosListPOJO.ResponseBean> cartPojo;
    public static int video_position = 0;
    public static int scrollposition = 0;

    private OnitemClickListener mListner;

    VideoCallBack videoCallBack;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    public ChapterVideoListAdapter(List<VideosListPOJO.ResponseBean> cartPojo, Activity context, VideoCallBack adapterCallBack) {
        this.context = context;
        this.cartPojo = cartPojo;
        this.videoCallBack = adapterCallBack;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public YearAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_videos, parent, false);
        return new YearAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final YearAdapterView holder, final int position) {


        holder.youtubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onStateChange(@NonNull PlayerConstants.PlayerState state) {
                        super.onStateChange(state);
                        Log.d("sssss", String.valueOf(state));
                        if (String.valueOf(state).equalsIgnoreCase("ENDED")) {
                            //video_position = video_position + 1;
                            if(videoCallBack!=null){
                                scrollposition=position+1;
                                video_position=position+1;
                                videoCallBack.clickevent("dummy",video_position);
                            }

                            //onReady();
//                            String videoId = extractYoutubeVideoId(cartPojo.get(video_position).getVideo_link());
//                            initializedYouTubePlayer.loadVideo(videoId, 0);
//
//                            YouTubePlayerTracker tracker = new YouTubePlayerTracker();
////                        tracker.getCurrentSecond();
////                        tracker.getVideoDuration();
//                            initializedYouTubePlayer.addListener(tracker);
//                            Log.d("Video_time", tracker.getCurrentSecond() + " test " + tracker.getVideoDuration() + "");
                        }

                    }

                    @Override
                    public void onReady() {

                        if (video_position == 0 && position == 0) {
                            video_position=position;
                            String videoId = extractYoutubeVideoId(cartPojo.get(position).getVideo_link());
                            initializedYouTubePlayer.loadVideo(videoId, 0);
                        }
//                        if (video_position ==scrollposition) {
//                           // video_position = position;
//                          //  video_position=0;
//                           // scrollposition=0;
//                            String videoId = extractYoutubeVideoId(cartPojo.get(scrollposition).getVideo_link());
//                            initializedYouTubePlayer.loadVideo(videoId, 0);
//
//                        }
                        else if (video_position > 0) {
                            String videoId = extractYoutubeVideoId(cartPojo.get(video_position).getVideo_link());
                            initializedYouTubePlayer.loadVideo(videoId, 0);
                        } else {

                            String videoId = extractYoutubeVideoId(cartPojo.get(position).getVideo_link());
                            initializedYouTubePlayer.cueVideo(videoId, 0);
                        }
                        /*if (video_position == 0 && position == 0) {
                            video_position = position;
                            String videoId = extractYoutubeVideoId(cartPojo.get(position).getVideo_link());
                            initializedYouTubePlayer.loadVideo(videoId, 0);

                        } else if (video_position > 0) {

                            String videoId = extractYoutubeVideoId(cartPojo.get(video_position).getVideo_link());
                            initializedYouTubePlayer.loadVideo(videoId, 0);
                        } else {
                            String videoId = extractYoutubeVideoId(cartPojo.get(position).getVideo_link());
                            initializedYouTubePlayer.cueVideo(videoId, 0);
                        }*/

                    }
                });
            }
        }, true);


        holder.tv_comments_count.setText(cartPojo.get(position).getComments() + " Comments");
        holder.tv_pings_count.setText("Pings");
        holder.tv_view_count.setText(cartPojo.get(position).getViews() + " Views");

        if (cartPojo.get(position).getReaded().equalsIgnoreCase("yes")) {
            holder.ll_unread.setVisibility(View.GONE);
            holder.ll_unread_blue.setVisibility(View.VISIBLE);
        } else {
            holder.ll_unread.setVisibility(View.VISIBLE);
            holder.ll_unread_blue.setVisibility(View.GONE);
        }


//        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
//            @Override
//            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
//
//            }
//
//            @Override
//            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
//                youTubeThumbnailView.setVisibility(View.VISIBLE);
//                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
//            }
//        };
//
//        holder.youTubeThumbnailView.initialize(NetworkConstants.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
//
//                youTubeThumbnailLoader.setVideo(cartPojo.get(position).getVideo_link());
//                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
//                //write something for failure
//            }
//        });
//        Glide.with(context)
//                .load(cartPojo.get(position).getVideo_link())
//                .error(R.drawable.no_image)
//                .into(holder.img_video);
        holder.tv_heading.setText(cartPojo.get(position).getHeading());
        holder.tv_sub_text.setText(cartPojo.get(position).getChapter());
    }

    //    public static String extractYoutubeVideoId(String ytUrl) {
//
//        String vId = null;
//
//        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
//
//        Pattern compiledPattern = Pattern.compile(pattern);
//        Matcher matcher = compiledPattern.matcher(ytUrl);
//
//        if (matcher.find()) {
//            vId = matcher.group();
//        }
//        return vId;
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
        return cartPojo.size();
        // return 1;
    }

    public class YearAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_heading, tv_sub_text, tv_pings_count, tv_comments_count, tv_view_count;
        ImageView img_video, img_play;
        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView playButton;
        YouTubePlayerView youtubePlayerView;
        LinearLayout ll_ping, ll_comment, ll_unread, ll_topic, ll_unread_blue;

        public YearAdapterView(View itemView) {

            super(itemView);

            // playButton = (ImageView) itemView.findViewById(R.id.btnYoutube_player);
            // playButton.setOnClickListener(this);
            // relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            // youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);


            tv_heading = itemView.findViewById(R.id.tv_heading);
            tv_sub_text = itemView.findViewById(R.id.tv_sub_text);
            tv_pings_count = itemView.findViewById(R.id.tv_pings_count);
            tv_comments_count = itemView.findViewById(R.id.tv_comments_count);
            tv_view_count = itemView.findViewById(R.id.tv_view_count);

            // img_video = itemView.findViewById(R.id.img_video);
            //  img_play = itemView.findViewById(R.id.img_play);
            tv_heading.setOnClickListener(this);

            youtubePlayerView = itemView.findViewById(R.id.youtube_player_view);
            ll_ping = itemView.findViewById(R.id.ll_ping);
            ll_comment = itemView.findViewById(R.id.ll_comment);
            ll_unread = itemView.findViewById(R.id.ll_unread);
            ll_unread_blue = itemView.findViewById(R.id.ll_unread_blue);
            ll_topic = itemView.findViewById(R.id.ll_topic);


            ll_ping.setOnClickListener(this);
            ll_comment.setOnClickListener(this);
            ll_unread.setOnClickListener(this);
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


