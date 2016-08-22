package com.repository.task.data.leancloud;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 */
public class TodoInterceptor implements Interceptor {
    public TodoInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数
//        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
//                .newBuilder()
//                .scheme(oldRequest.url().scheme())
//                .host(oldRequest.url().host());
//                .addQueryParameter("X-LC-Id", "lMICj44Ei43EU6iwlP8AhmsM-MdYXbMMI") // app id
//                .addQueryParameter("X-LC-Key", "zWtOdE7BMo1FlprP3fc3pT2d")  // app key
//                .addQueryParameter("Content-Type", "application/json");
//                .addQueryParameter(RestConstants.OS_VERSION, RestConstants.getOsVersion())
//                .addQueryParameter(RestConstants.NETWORKING, "")
//                .addQueryParameter(RestConstants.RESOLUTION, "")
//                .addQueryParameter(RestConstants.APP_IDENTIFIER, "4")
//                .addQueryParameter(RestConstants.MODEL, "");

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
//                .url(authorizedUrlBuilder.build())
                .url(oldRequest.url())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("X-LC-Id", "lMICj44Ei43EU6iwlP8AhmsM-MdYXbMMI") // app id
                .header("X-LC-Key", "zWtOdE7BMo1FlprP3fc3pT2d")  // app key
//                .header("Content-Type", "application/json")
                .build();

        return chain.proceed(newRequest);
    }
}
