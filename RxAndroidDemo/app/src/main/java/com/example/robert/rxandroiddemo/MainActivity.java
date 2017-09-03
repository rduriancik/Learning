package com.example.robert.rxandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Observable<String> myObservable = Observable.just("Hello");

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


        Observable<Integer> myIntArrayObservable =
                Observable.from(new Integer[]{1, 2, 3, 4, 5, 6});

        myIntArrayObservable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 0;
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return String.valueOf(integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("RXResult", "Map function result: " + s);
            }
        });*/

        /*Observable<String[]> fetchFromGoogle = Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                try {
                    String data = fetchData("http://www.google.com");
                    subscriber.onNext(new String[]{"from Google", data});
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        fetchFromGoogle.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String[]>() {
                    @Override
                    public void call(String[] s) {
                        Log.d("RXResult", s[0] + ", the result was: " + s[0]);
                    }
                });*/


        /*Observable.from(new String[]{"https://www.google.com", "https://www.yahoo.com"})
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            Log.d("RXResult", "Resulting number is: " + fetchData(s));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });*/

        Observable<String[]> fetchFromGoogle = Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                try {
                    String data = fetchData("http://www.google.com");
                    subscriber.onNext(new String[]{"from Google", data});
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        Observable<String[]> fetchFromYahoo = Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                try {
                    String data = fetchData("http://www.yahoo.com");
                    subscriber.onNext(new String[]{"from Yahoo", data});
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        Observable.zip(fetchFromGoogle, fetchFromYahoo, new Func2<String[], String[], String>() {
            @Override
            public String call(String[] strings, String[] strings2) {
                return strings[0] + " and " + strings2[0] + " the result is "
                        + (Integer.parseInt(strings[1]) + Integer.parseInt(strings2[1]));
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("RXResult", s);
                    }
                });

    }


    public String fetchData(String url) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        String inputLine;
        String result = "";

        while ((inputLine = in.readLine()) != null) {
            result += inputLine;
        }

        return result.length() + "";
    }
}
