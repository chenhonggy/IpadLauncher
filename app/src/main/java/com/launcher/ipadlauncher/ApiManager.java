package com.launcher.ipadlauncher;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.concurrency.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by chen on 14-7-19.
 */
public class ApiManager {
    private interface ApiManagerService {
        @GET("/informs/1")
        TestData getWeather();
    }

    private static final RestAdapter restAdapter = new RestAdapter.Builder()
            .setServer("http://10.0.0.8:8888/api")
            .build();
    private static final ApiManagerService apiManager = restAdapter.create(ApiManagerService.class);
    public static Observable<TestData> getWeatherData() {
        return Observable.create(new Observable.OnSubscribeFunc<TestData>() {
            @Override
            public Subscription onSubscribe(Observer<? super TestData> observer) {
                try {
                    observer.onNext(apiManager.getWeather());
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }

                return Subscriptions.empty();
            }
        }).subscribeOn(Schedulers.threadPoolForIO());
    }
}
