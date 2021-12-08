package com.example.giantbombapi.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giantbombapi.ui.MoviesDetailActivity;
import com.example.giantbombapi.R;
import com.example.giantbombapi.models.Result;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllVideosAdapter extends RecyclerView.Adapter<AllVideosAdapter.allVideosViewHolder> {

    private static List<Result> mAllVideos;

    public AllVideosAdapter(List<Result> allVideos) {
        mAllVideos = allVideos;
    }


    @NonNull
    public allVideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videos_layout,parent,false);
        return new allVideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull allVideosViewHolder holder, int position) {
        holder.bindVideosToView(mAllVideos.get(position));

    }

    @Override
    public int getItemCount() {
        return mAllVideos.size();
    }


    public static class allVideosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.videoTitleTextView) TextView videoTitle;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.dateTextView) TextView date;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.videoImageview) ImageView image;
        private final Context mContext;


        public allVideosViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void bindVideosToView(Result result){
            Picasso.get().load(result.getImage().getIconUrl()).into(image);
            date.setText(result.getPublishDate());
            videoTitle.setText(result.getName());
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, MoviesDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("results", Parcels.wrap(mAllVideos));
            mContext.startActivity(intent);

        }
    }
}
