package com.example.giantbombapi.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giantbombapi.Constants;
import com.example.giantbombapi.R;
import com.example.giantbombapi.adapters.FirebaseVideoViewHolder;
import com.example.giantbombapi.models.Result;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedVideoListActivity extends AppCompatActivity {
    private DatabaseReference mVideoReference;
    private FirebaseRecyclerAdapter<Result, FirebaseVideoViewHolder> mFirebaseAdapter;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textView) TextView mErrorTextView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progressbar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        mVideoReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_VIDEOS);
        setUpFirebaseAdapter();
        hideProgressBar();
        showRestaurants();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Result> options =
                new FirebaseRecyclerOptions.Builder<Result>()
                        .setQuery(mVideoReference, Result.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Result, FirebaseVideoViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseVideoViewHolder firebaseVideoViewHolder, int position, @NonNull Result video) {
                firebaseVideoViewHolder.bindVideo(video);
            }

            @NonNull
            @Override
            public FirebaseVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videos_layout, parent, false);
                return new FirebaseVideoViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}