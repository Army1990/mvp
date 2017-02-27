package com.myselfapp.myselfapp.network;

import com.myselfapp.myselfapp.config.MySelfConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Army on 2016/12/27
 * 初始化Retrofit
 */

public class ApiService {
    private static ApiService sApiService;
    private static final int REQUEST_TIMEOUT=10000;
    public static ApiService getDefault(){
        if (sApiService==null){
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            okHttpClient.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

            okHttpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Response req = chain.proceed(request);
                    return req.newBuilder()
                            .header("key1","value1")
                            .addHeader("key2","value2")
                            .build();
                }
            });

            sApiService=new Retrofit.Builder()
                    .client(okHttpClient.build())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(MySelfConfig.PRODUCT_HOST)
                    .build().create(ApiService.class);
        }
        return sApiService;

    }
}
