package com.bignerdranch.android.livingsocialanimation.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    public static final int COUNT = 2;
    public static final int POSITION_FRAGMENT_IMAGE = 0;
    public static final int POSITION_FRAGMENT_ANIMATION = 1;

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ImageFragment.newInstance();
            case 1:
                return AnimationFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Image";
            case 1:
                return "Animation";
            default:
                return null;
        }
    }

}