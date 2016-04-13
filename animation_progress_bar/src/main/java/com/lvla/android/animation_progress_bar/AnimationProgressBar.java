package com.lvla.android.animation_progress_bar;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by lvla on 2016/04/13.
 */
public class AnimationProgressBar extends ProgressBar {
    private ObjectAnimator animator = null;
    private TimeInterpolator interpolator = null;

    public AnimationProgressBar(Context context) {
        super(context);
    }

    public AnimationProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    public void setProgress(int progress, int animationDurationMilliSec) {
        animator = ObjectAnimator.ofInt(this, "progress", progress);
        animator.setDuration(animationDurationMilliSec);
        if(interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animator.start();
    }

    public void end() {
        if(animator != null) {
            animator.end();
        }
    }
}

