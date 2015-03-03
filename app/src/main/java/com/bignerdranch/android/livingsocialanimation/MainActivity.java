package com.bignerdranch.android.livingsocialanimation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.bignerdranch.android.livingsocialanimation.tabs.SlidingTabLayout;

public class MainActivity extends ActionBarActivity {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new CustomOnPageChangeListener());
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    private class CustomOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            if (position == CustomPagerAdapter.POSITION_FRAGMENT_ANIMATION) {
                AnimationFragment fragment = (AnimationFragment) mViewPager.getAdapter().instantiateItem(mViewPager, 1);
                fragment.startAnimation();
            }
            super.onPageSelected(position);
        }
    }

}