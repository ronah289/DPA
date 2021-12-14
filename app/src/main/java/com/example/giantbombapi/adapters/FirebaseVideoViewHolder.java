package com.example.giantbombapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giantbombapi.Constants;
import com.example.giantbombapi.R;
import com.example.giantbombapi.models.Result;
import com.example.giantbombapi.ui.MoviesDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseVideoViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindVideo(Result videos) {
        TextView videoTitle = mView.findViewById(R.id.videoTitleTextView);
        TextView date = mView.findViewById(R.id.dateTextView);
        /*
        hold image..
        ImageView image = mView.findViewById(R.id.videoImageView);
        Picasso.get().load(videos.getImage().getIconUrl()).into(image);
*/
        date.setText(videos.getPublishDate());
        videoTitle.setText(videos.getName());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Result> videos = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_VIDEOS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    videos.add(snapshot.getValue(Result.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MoviesDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("results", Parcels.wrap(videos));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}