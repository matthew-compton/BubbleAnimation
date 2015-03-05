package com.bignerdranch.android.livingsocialanimation.controller;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bignerdranch.android.livingsocialanimation.R;
import com.bignerdranch.android.livingsocialanimation.anim.ArcAnimator;
import com.bignerdranch.android.livingsocialanimation.anim.Side;
import com.bignerdranch.android.livingsocialanimation.model.AnimationState;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import hugo.weaving.DebugLog;
import timber.log.Timber;

public class AnimationFragment extends Fragment {

    private static final int DURATION_ANIMATION_MS = 1000;

    @InjectView(R.id.fragment_animation_layout) FrameLayout mLayout;
    @InjectView(R.id.fragment_animation_circle_blue) ImageView mCircleBlue;
    @InjectView(R.id.fragment_animation_circle_orange) ImageView mCircleOrange;
    @InjectView(R.id.fragment_animation_circle_pink) ImageView mCirclePink;
    @InjectView(R.id.fragment_animation_circle_yellow) ImageView mCircleYellow;
    @InjectView(R.id.fragment_animation_image_view_text) ImageView mTextImageView;

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
        int width = mLayout.getWidth();
        int height = mLayout.getHeight();

        ArcAnimator arcAnimatorBlue = createEnterAnimationOnView(
                mCircleBlue,
                (int) (width * .4),
                (int) (height * .4),
                90,
                Side.LEFT
        );
        ArcAnimator arcAnimatorOrange = createEnterAnimationOnView(
                mCircleOrange,
                (int) (width * .35),
                (int) (height * .55),
                90,
                Side.LEFT
        );
        ArcAnimator arcAnimatorPink = createEnterAnimationOnView(
                mCirclePink,
                (int) (width * .4),
                (int) (height * .5),
                90,
                Side.LEFT
        );
        ArcAnimator arcAnimatorYellow = createEnterAnimationOnView(
                mCircleYellow,
                (int) (width * .65),
                (int) (height * .4),
                90,
                Side.RIGHT
        );

        arcAnimatorBlue.start();
        arcAnimatorOrange.start();
        arcAnimatorPink.start();
        arcAnimatorYellow.start();

        new Handler().postDelayed(() -> {
            mTextImageView.setVisibility(View.VISIBLE);
            mTextImageView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.text_enter));
            new Handler().postDelayed(() -> mState = AnimationState.SHOWING, DURATION_ANIMATION_MS);
        }, DURATION_ANIMATION_MS);
    }

    private ArcAnimator createEnterAnimationOnView(View view, int endX, int endY, int degrees, Side side) {
        view.setVisibility(View.VISIBLE);
        ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(view, endX, endY, degrees, side);
        arcAnimator.setInterpolator(new DecelerateInterpolator());
        arcAnimator.setDuration(DURATION_ANIMATION_MS);
        return arcAnimator;
    }

    private void startExitAnimation() {
        mState = AnimationState.EXITING;
        int width = mLayout.getWidth();
        int height = mLayout.getHeight();
        ArcAnimator arcAnimatorBlue = createExitAnimationOnView(
                mCircleBlue,
                0,
                0,
                90,
                Side.LEFT
        );
        ArcAnimator arcAnimatorOrange = createExitAnimationOnView(
                mCircleOrange,
                0,
                0,
                90,
                Side.LEFT
        );
        ArcAnimator arcAnimatorPink = createExitAnimationOnView(
                mCirclePink,
                0,
                0,
                90,
                Side.LEFT
        );
        ArcAnimator arcAnimatorYellow = createExitAnimationOnView(
                mCircleYellow,
                width,
                height,
                90,
                Side.RIGHT
        );

        arcAnimatorBlue.start();
        arcAnimatorOrange.start();
        arcAnimatorPink.start();
        arcAnimatorYellow.start();

        mTextImageView.setVisibility(View.INVISIBLE);
        mTextImageView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.text_exit));

        new Handler().postDelayed(() -> {
            mState = AnimationState.HIDING;
        }, DURATION_ANIMATION_MS);
    }

    private ArcAnimator createExitAnimationOnView(View view, int endX, int endY, int degrees, Side side) {
        ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(view, endX, endY, degrees, side);
        arcAnimator.setInterpolator(new AccelerateInterpolator());
        arcAnimator.setDuration(DURATION_ANIMATION_MS);
        arcAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
        return arcAnimator;
    }

}