package com.bw.forwardweek3.util;


import com.bw.forwardweek3.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtil {

    // TODO: 2020/1/2 这个baseurl必须修改
    private static final String BASE_URL = "http://172.17.8.100/";

    //成员变量
    private final Api api;


    //单例第二步 geti
    public static NetUtil getInstance() {
        return SingleHolder.NET_UTIL;
    }

    //单例第三步  私有静态内部类
    private static final class SingleHolder {
        private static final NetUtil NET_UTIL = new NetUtil();
    }

    //单例第一步 私有构造器
    private NetUtil() {
        //拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //构造一个  OkHttpClient 对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                //添加拦截器
                .addInterceptor(httpLoggingInterceptor)
                .build();

        //构造 Retrofit 的 Builder 对象
        Retrofit retrofit = new Retrofit.Builder()
                //自定义 OkhttpClient
                .client(okHttpClient)
                //公共的url部分
                .baseUrl(BASE_URL)
                //支持Gson    json -> bean
                .addConverterFactory(GsonConverterFactory.create())
                //支持Rxjava   Bean -> 被观察者
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        //通过 create 方法，构造 api 对象
        api = retrofit.create(Api.class);
    }


    //暴露Api给外界使用
    public Api getApi() {
        return api;
    }
}
