package com.abizo.dynamictabtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    int tabNum;
    List<String> tabNames;

    public FragmentAdapter(@NonNull FragmentManager fm, int numTabs, List<String> names) {
        super(fm);
        tabNum = numTabs;
        tabNames = names;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        String name = tabNames.get(position);
        return TabFragment.newInstance(position, name);
    }

    @Override
    public int getCount() {
        return tabNum;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }
}
