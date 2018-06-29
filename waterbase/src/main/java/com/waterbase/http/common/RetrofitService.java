package com.waterbase.http.common;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.waterbase.http.converter.GsonConverterFactory;
import com.waterbase.http.interceptor.HttpCacheInterceptor;
import com.waterbase.http.interceptor.HttpHeaderInterceptor;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.Utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Retrofit实例化类
 * Created by zhpan on 2018/3/21.
 */

public class RetrofitService {
    public static OkHttpClient.Builder getOkHttpClientBuilder() {

//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Response response = chain.proceed(chain.request());
//
//                Request request = chain.request();
//                String url = request.url().toString();
//                LogUtil.d("RetrofitService", "url: " + url);
//                //存入Session
//                if (response.header("Set-Cookie") != null) {
//                    // JSESSIONID=EEF29A1F6D276E60751850ACCCD25D88; Path=/police-check-service; HttpOnly
//                    String SetCookie = response.header("Set-Cookie");
//                    String Cookie = SetCookie.substring(0, SetCookie.indexOf(";"));
//                    PreferencesManager.getInstance(Utils.getContext()).put("cookie", Cookie);
//                }
//                return response;
//            }
//
//        };

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor((String message) -> {
            try {
                if (TextUtils.isEmpty(message)) return;
                String s = message.substring(0, 1);
                //如果收到响应是json才打印
                if ("{".equals(s) || "[".equals(s)) {
                    LogUtil.i("OKHttp--1---RetrofitService", URLDecoder.decode(message, "utf-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                LogUtil.e("OKHttp-----RetrofitService", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(Utils.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        return new OkHttpClient.Builder()
                .readTimeout(RxRetrofitApp.getDefaultTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(RxRetrofitApp.getDefaultTimeout(), TimeUnit.MILLISECONDS)
//                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HttpHeaderInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())  // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .hostnameVerifier(new SafeHostnameVerifier())
                .cache(cache);
    }

    public static Retrofit.Builder getRetrofitBuilder(String baseUrl) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        OkHttpClient okHttpClient = RetrofitService.getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))     //json 自动解析最外层
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
    }
}
