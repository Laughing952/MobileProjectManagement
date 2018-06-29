package com.unistrong.working.api;


import com.waterbase.http.common.IdeaApi;
import com.waterbase.http.common.RxRetrofitApp;

public class RetrofitHelper {
    private static WorkingApiService mWorkingApiService;

    public static WorkingApiService getApiService(){
        return mWorkingApiService;
    }

    static {
        mWorkingApiService= IdeaApi.getApiService(WorkingApiService.class, RxRetrofitApp.getApiServerUrl());
    }
}
