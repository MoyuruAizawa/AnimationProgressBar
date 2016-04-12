package com.lvla.android.animation_progress_bar;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by lvla on 2016/04/13.
 */
public class AnimationProgressBar extends ProgressBar {
    public AnimationProgressBar(Context context) {
        super(context);
    }

    public AnimationProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setProgress(int progress, int animationDurationMilliSec) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.animation.ObjectAnimator animator
                    = android.animation.ObjectAnimator.ofInt(this, "progress", progress);
            animator.setDuration(animationDurationMilliSec);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.start();
        } else {
            ObjectAnimator animator = ObjectAnimator.ofInt(this, "progress", progress);
            animator.setDuration(animationDurationMilliSec);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.start();
        }
    }
}

