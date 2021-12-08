package com.example.giantbombapi.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giantbombapi.Constants;
import com.example.giantbombapi.R;
import com.example.giantbombapi.adapters.AllVideosAdapter;
import com.example.giantbombapi.models.AllVideos;
import com.example.giantbombapi.models.Result;
import com.example.giantbombapi.network.GiantBombApi;
import com.example.giantbombapi.network.GiantBombClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListActivity extends AppCompatActivity{
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textView) TextView errors;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.welcomeUser) TextView userWelcome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.available_list) TextView availableVideos;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progressbar)
    ProgressBar progress;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private AllVideosAdapter videos;
    private List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        getVideos();
        TextView userWelcome = findViewById(R.id.welcomeUser);

        Intent usernameIntent = getIntent();
        String username = usernameIntent.getStringExtra("username");
        userWelcome.setText(String.format("Welcome %s", username));
    }

    public void getVideos(){
        GiantBombApi gbClient = GiantBombClient.getClient();
        Call<AllVideos> call = gbClient.getAllVideos(Constants.GIANT_BOMB_API_KEY);

        call.enqueue(new Callback<AllVideos>() {
            @Override
            public void onResponse(@NonNull Call<AllVideos> call, @NonNull Response<AllVideos> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    userWelcome.setVisibility(View.GONE);
                    availableVideos.setVisibility(View.GONE);
                    assert response.body() != null;
                    results = response.body().getResults();
                    videos = new AllVideosAdapter(results);
                    recyclerView.setAdapter(videos);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(MoviesListActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);

                    showresult();
                }  //                     showUnsuccessfulMessage();


            }

            @Override
            public void onFailure(@NonNull Call<AllVideos> call, @NonNull Throwable t) {
                System.out.println("error"+call);

            }
        });
    }
    private void showresult() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progress.setVisibility(View.GONE);
    }
}