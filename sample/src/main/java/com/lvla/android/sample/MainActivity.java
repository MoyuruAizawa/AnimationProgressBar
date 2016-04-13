package com.lvla.android.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lvla.android.animation_progress_bar.AnimationProgressBar;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final int MAX = 10000;
    private Subscription subscription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AnimationProgressBar animationProgress
                = (AnimationProgressBar)findViewById(R.id.animationProgressBar);
        final AnimationProgressBar animationProgress2
                = (AnimationProgressBar)findViewById(R.id.animationProgressBar2);
        final ProgressBar progress= (ProgressBar) findViewById(R.id.progressBar);

        animationProgress.setMax(MAX);
        animationProgress2.setMax(MAX);
        progress.setMax(MAX);

        animationProgress2.setInterpolatorForHorizontal(new DecelerateInterpolator());

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationProgress.end();
                animationProgress2.end();

                animationProgress.setProgress(0);
                animationProgress2.setProgress(0);
                progress.setProgress(0);

                if(subscription != null) {
                    subscription.unsubscribe();
                }

                subscription = startProgress().subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer i) {
                                animationProgress.setProgress( (MAX/4) * i, 1000 );
                                animationProgress2.setProgress((MAX/4) * i, 1000 );
                                progress.setProgress( (MAX/4) * i );
                            }
                        });
            }
        });

    }

    private Observable startProgress() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    for (int i = 1; i <= 4; i++) {
                        if(subscriber.isUnsubscribed()) {
                            break;
                        }
                        Thread.sleep(900);
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
