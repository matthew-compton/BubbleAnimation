package com.bignerdranch.android.livingsocialanimation.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bignerdranch.android.livingsocialanimation.R;
import com.bignerdranch.android.livingsocialanimation.model.AnimationState;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import hugo.weaving.DebugLog;
import timber.log.Timber;

public class AnimationFragment extends Fragment {

    @InjectView(R.id.fragment_animation_circle_blue) ImageView mCircleBlue;
    @InjectView(R.id.fragment_animation_circle_orange) ImageView mCircleOrange;
    @InjectView(R.id.fragment_animation_circle_pink) ImageView mCirclePink;
    @InjectView(R.id.fragment_animation_circle_yellow) ImageView mCircleYellow;

    private AnimationState mState;

    public static AnimationFragment newInstance() {
        return new AnimationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mState = AnimationState.HIDING;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animation, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.fragment_animation_layout)
    @DebugLog
    public void onClickLayout() {
        switch (mState) {
            case HIDING:
                Timber.i("HIDING -> ENTERING -> SHOWING");
                startEnterAnimation();
                break;
            case SHOWING:
                Timber.i("SHOWING -> ENTERING -> HIDING");
                startExitAnimation();
                break;
            case ENTERING:
            case EXITING:
            default:
                break;
        }
    }

    private void startEnterAnimation() {
        mState = AnimationState.ENTERING;
        mCircleBlue.setVisibility(View.VISIBLE);
        mCircleOrange.setVisibility(View.VISIBLE);
        mCirclePink.setVisibility(View.VISIBLE);
        mCircleYellow.setVisibility(View.VISIBLE);
        mState = AnimationState.SHOWING;
    }

    private void startExitAnimation() {
        mState = AnimationState.EXITING;
        mCircleBlue.setVisibility(View.INVISIBLE);
        mCircleOrange.setVisibility(View.INVISIBLE);
        mCirclePink.setVisibility(View.INVISIBLE);
        mCircleYellow.setVisibility(View.INVISIBLE);
        mState = AnimationState.HIDING;
    }

}