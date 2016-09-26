package com.lvla.android.animation_progress_bar;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class AnimationProgressBar extends ProgressBar {
    private ObjectAnimator animator = null;

    public AnimationProgressBar(Context context) {
        super(context);
    }

    public AnimationProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setProgress(int progress, int animationDurationMilliSec, @Nullable TimeInterpolator interpolator) {
        animator = ObjectAnimator.ofInt(this, "progress", progress);
        animator.setDuration(animationDurationMilliSec);
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animator.start();
    }

    public void setProgress(int progress, int animationDurationMilliSec) {
        setProgress(progress, animationDurationMilliSec, null);
    }

    public void end() {
        if(animator != null) {
            animator.end();
        }
    }
}

