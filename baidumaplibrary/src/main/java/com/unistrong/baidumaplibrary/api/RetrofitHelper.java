package com.unistrong.baidumaplibrary.api;


import com.waterbase.http.common.IdeaApi;
import com.waterbase.http.common.RxRetrofitApp;

public class RetrofitHelper {
    private static MapApiService mWorkingApiService;

    public static MapApiService getApiService(){
        return mWorkingApiService;
    }

    static {
        mWorkingApiService= IdeaApi.getApiService(MapApiService.class, RxRetrofitApp.getApiServerUrl());
    }
}
