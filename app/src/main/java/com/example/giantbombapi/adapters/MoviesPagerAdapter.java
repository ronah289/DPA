package com.example.giantbombapi.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.giantbombapi.models.Result;
import com.example.giantbombapi.ui.MoviesDetailFragment;

import java.util.List;

@SuppressWarnings("ALL")
public class MoviesPagerAdapter extends FragmentPagerAdapter {

    private final List<Result> mResults;

    public MoviesPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Result> results) {
        super(fm, behavior);
        mResults = results;
    }

    @Override
    public Fragment getItem(int position) {
        return MoviesDetailFragment.newInstance(mResults.get(position));
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mResults.get(position).getName();
    }

}
