package com.example.giantbombapi.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
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

@SuppressWarnings("ALL")
public class MoviesListFragment extends Fragment {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textView)
    TextView errors;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.welcomeUser) TextView userWelcome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.available_list) TextView availableVideos;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progressbar)
    ProgressBar progress;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private AllVideosAdapter videos;
    private List<Result> results;



    public MoviesListFragment() {
        // Required empty public constructor
    }


    public static MoviesListFragment newInstance(String param1, String param2) {
        MoviesListFragment fragment = new MoviesListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        ButterKnife.bind(this,view);
        getVideos();
        return view;
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
                    getActivity().runOnUiThread(() -> {
                        videos = new AllVideosAdapter(results);
                        recyclerView.setAdapter(videos);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);

                        showresult();
                    });
                }
                else {
                    showUnsuccessfulMessage();
                }

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
    @SuppressLint("SetTextI18n")
    private void showUnsuccessfulMessage() {
        errors.setText("Error.Try Later");
        errors.setVisibility(View.VISIBLE);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem search = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) search.getActionView();


        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                videos.getFilter().filter(s);
                return false;
            }
        });

    }
}