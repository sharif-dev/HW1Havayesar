package com.example.weatherforecast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentPageAdapter extends FragmentPagerAdapter {
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public FragmentPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
}
