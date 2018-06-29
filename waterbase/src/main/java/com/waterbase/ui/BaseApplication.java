package com.waterbase.ui;

import android.app.Application;
import android.content.Context;

import com.waterbase.utile.Utils;

/**
 * Created by Water on 2018/3/30.
 */

public abstract class BaseApplication extends Application {
    public static int DEFAULT_TIMEOUT = 30000;
    public static String IP = "47.98.62.218:8080";
    public static String HOST = "http://10.66.5.91:8080/";
    public static String API_SERVER_URL = HOST + "police-check-service/";

    private static BaseApplication application;

    public static Context getAppContext() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initRxRetrofitApp();
        application = this;
    }

    /**
     * 重写此方法以完成http请求的初始化
     */
    protected abstract void initRxRetrofitApp();

}
