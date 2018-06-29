package com.login.api;


import com.waterbase.http.common.IdeaApi;
import com.waterbase.http.common.RxRetrofitApp;

public class RetrofitHelper {
    private static IdeaApiService mIdeaApiService;

    public static IdeaApiService getApiService(){
        return mIdeaApiService;
    }

    static {
       mIdeaApiService= IdeaApi.getApiService(IdeaApiService.class, RxRetrofitApp.getApiServerUrl());
    }
}
