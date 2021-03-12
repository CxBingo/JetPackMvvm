package com.bingo.basemodule.network.utils;

import android.os.Build;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by francisbingo on 2020/10/26 14:34
 */

public class RetrofitUtil {

    /**
     * 获取OkHttpClient
     * 用于打印请求参数
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getOkHttpClient() {
        // 日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        // 新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("http请求参数：", message);
            }
        });
        loggingInterceptor.setLevel(level);
        // 定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder newBuilder = chain.request().newBuilder();
                newBuilder.removeHeader("User-Agent");//移除旧的
                // 给http设置UA需涵盖 系统版本、软件名称和版本、硬件版本 -----客户端可用可不用
                newBuilder.addHeader("User-Agent", " os:android" + " model:" + Build.MANUFACTURER + " os_version:" + Build.VERSION.RELEASE + " app_verison:");//添加真正的头部

                newBuilder.addHeader("token", "xxx");
                Request request = newBuilder.build();
                return chain.proceed(request);
            }
        });
        // OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
