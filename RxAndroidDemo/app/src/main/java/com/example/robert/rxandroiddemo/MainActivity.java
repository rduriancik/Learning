package com.example.robert.rxandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> myObservable = Observable.just("Hello");

        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("RXResult", "No more data to emit");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("RXResult", s);
            }
        };

        Subscription subscription1 = myObservable.subscribe(myObserver);

        Action1<String> myAction = new Action1<String>() {
            @Override
            public void call(String o) {
                Log.d("RXResult", "The result from this inner class Action is: " + o);
            }
        };

        Subscription subscription2 = myObservable.subscribe(myAction);

        Observable<String> myArrayObservable =
                Observable.from(new String[]{"Hello", "Gracias", "Bonjurno", "Goodbye", "Salam", "Shalom"});

        myArrayObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("RXResult", s);
            }
        });

    }
}
