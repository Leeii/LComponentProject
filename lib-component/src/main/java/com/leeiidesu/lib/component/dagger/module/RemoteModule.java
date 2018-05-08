package com.leeiidesu.lib.component.dagger.module;

import android.app.Application;

import com.leeiidesu.converter.fastjson.FastJsonConverterFactory;
import com.leeiidesu.lib.base.tools.LoggerInterceptor;


import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 远端module
 * Created by Leeii on 2017/11/3.
 */

@Module
public class RemoteModule {

    @Provides
    @Singleton
    FastJsonConverterFactory providerConverterFactory() {
        return FastJsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory providerAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    OkHttpClient providerOkHttpClient(Interceptor intercept, Cache cache
                                      /*, TokenInterceptor tokenInterceptor,
                                      CookieInterceptor cookieInterceptor*/) {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
//        okHttpClient.addInterceptor(tokenInterceptor);
//        okHttpClient.addInterceptor(cookieInterceptor);
        return configureClient(okHttpClient, intercept, cache);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client,
                             String httpUrl,
                             FastJsonConverterFactory converterFactory,
                             RxJava2CallAdapterFactory adapterFactory) {
        final Retrofit.Builder builder = new Retrofit.Builder();
        return configureRetrofit(builder, client, httpUrl, converterFactory, adapterFactory);
    }

    @Singleton
    @Provides
    Interceptor provideIntercept() {
        return new LoggerInterceptor();//打印请求信息的拦截器
    }
//
//    @Singleton
//    @Provides
//    CookieInterceptor provideCookieInterceptor() {
//        return new CookieInterceptor();//打印请求信息的拦截器
//    }
//
//    @Singleton
//    @Provides
//    TokenInterceptor providerTokenInterceptor() {
//        return new TokenInterceptor();
//    }

    @Singleton
    @Provides
    Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024);//设置缓存路径和大小 10M
    }

    @Singleton
    @Provides
    File provideCacheFile(Application application) {
        return application.getCacheDir();
    }

    /**
     * 配置okHttpclient
     */
    private OkHttpClient configureClient(OkHttpClient.Builder okHttpClient,
                                         Interceptor intercept,
                                         Cache cache) {
        OkHttpClient.Builder builder = okHttpClient
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .addNetworkInterceptor(intercept);
        return builder.build();
    }

    /**
     * 配置Retrofit
     */
    private Retrofit configureRetrofit(Retrofit.Builder builder,
                                       OkHttpClient client,
                                       String httpUrl,
                                       FastJsonConverterFactory converterFactory,
                                       RxJava2CallAdapterFactory adapterFactory) {
        return builder
                .baseUrl(httpUrl)
                .client(client)
                .addCallAdapterFactory(adapterFactory)
                .addConverterFactory(converterFactory)
                .build();
    }

    public interface Exposes {
        Retrofit getRetrofit();

        OkHttpClient getOkHttpClient();
    }
}
