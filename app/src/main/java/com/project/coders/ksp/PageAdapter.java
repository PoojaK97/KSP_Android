package com.project.coders.ksp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    int countTab;
    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.countTab = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabPast tpast = new TabPast();
                return tpast;
            case 1:
                TabPresent tpres = new TabPresent();
                return tpres;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return countTab;
    }
}
