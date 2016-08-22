package com.clean.common.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangfeng on 16-8-19.
 */
public class CommonApiManager<S> {
    public static <T extends Object> Observable<T> fork(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private S service;

    protected CommonApiManager(String baseUrl, Class<S> cls) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        attachClient(builder);

        service = builder.build().create(cls);
    }

    private void attachClient(Retrofit.Builder builder) {
        OkHttpClient client = getHttpClient();
        if (null != client) {
            builder.client(client);
        }
    }

    protected OkHttpClient getHttpClient() {
        return null;
    }

    protected S getService() {
        return service;
    }

    public static void unsubscribe(Subscription subscription) {
        if (null != subscription && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
