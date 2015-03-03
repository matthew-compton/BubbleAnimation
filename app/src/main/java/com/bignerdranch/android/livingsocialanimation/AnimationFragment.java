package com.bignerdranch.android.livingsocialanimation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.InjectView;

public class AnimationFragment extends Fragment {

    @InjectView(R.id.fragment_animation_circle_blue) ImageView mCircleBlue;
    @InjectView(R.id.fragment_animation_circle_orange) ImageView mCircleOrange;
    @InjectView(R.id.fragment_animation_circle_pink) ImageView mCirclePink;
    @InjectView(R.id.fragment_animation_circle_yellow) ImageView mCircleYellow;

    public static AnimationFragment newInstance() {
        return new AnimationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animation, container, false);
        return view;
    }

}