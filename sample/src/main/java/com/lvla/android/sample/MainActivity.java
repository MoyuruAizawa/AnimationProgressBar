package com.lvla.android.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.lvla.android.animation_progress_bar.AnimationProgressBar;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    public static final int MAX = 10000;

    CompositeSubscription subscriptions = new CompositeSubscription();

    private AnimationProgressBar progressBar1;
    private AnimationProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar1 = (AnimationProgressBar)findViewById(R.id.progressBar1);
        progressBar2 = (AnimationProgressBar)findViewById(R.id.progressBar2);
        progressBar1.setMax(MAX);
        progressBar2.setMax(MAX);

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar1.setProgress(MAX, 10000);
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    for (int i = 1; i <= 10; i++) {
                        Thread.sleep(900);
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer i) {
                        progressBar2.setProgress( (MAX/10) * i, 1000);
                    }
                });
    }

    @Override
    protected void onStop() {
        subscriptions.unsubscribe();
        super.onStop();
    }
}
