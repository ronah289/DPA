package com.example.giantbombapi.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.giantbombapi.Constants;
import com.example.giantbombapi.R;
import com.example.giantbombapi.models.Result;
import com.example.giantbombapi.models.VideoCategory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviesDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesDetailFragment extends Fragment implements View.OnClickListener{
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.videoImageView) ImageView mImageLabel;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.categoryTextView) TextView mCategoriesLabel;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.mWebLinkLabel) TextView mWebLinkLabel;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.websiteTextView) Button mWebsiteLabel;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.DateTextView) TextView mDateLabel;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.videoUrlTextView) TextView mVideoUrlLabel;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.AddToFavoritesButton) Button mAddToFavorites;

    private Result mResult;


    public MoviesDetailFragment() {
        // Required empty public constructor
    }

    public static MoviesDetailFragment newInstance(Result result) {
        MoviesDetailFragment fragment = new MoviesDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("result", Parcels.wrap(result));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mResult = Parcels.unwrap(getArguments().getParcelable("result"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movies_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mResult.getImage().getIconUrl()).into(mImageLabel);

        List<String> categories = new ArrayList<>();

        for (VideoCategory category: mResult.getVideoCategories()) {
            categories.add(category.getName());
        }

        mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
        mWebLinkLabel.setText(mResult.getUrl());
        mDateLabel.setText(mResult.getPublishDate());
        mVideoUrlLabel.setText(mResult.getSiteDetailUrl());
        mWebsiteLabel.setOnClickListener(this);
        mAddToFavorites.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mWebsiteLabel){
            String videoLink = mResult.getImage().getScreenLargeUrl();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(String.valueOf(videoLink)));
            startActivity(browserIntent);
        }
        else if (view == mAddToFavorites){
            DatabaseReference oneVideo = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_VIDEOS);
            oneVideo.push().setValue(mResult);
            Toast.makeText(getContext(), "Added To Favorites", Toast.LENGTH_SHORT).show();
        }

    }
}