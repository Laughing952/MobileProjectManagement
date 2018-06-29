package com.unistrong.mobileprojectmanagement;

import com.baidu.mapapi.SDKInitializer;
import com.waterbase.http.common.RxRetrofitApp;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.PreferencesManager;

//import com.baidu.mapapi.SDKInitializer;


/**
 * 作者：Laughing on 2018/5/5 17:20
 * 邮箱：719240226@qq.com
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //在使用百度地图SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());


        PreferencesManager preferencesManager = PreferencesManager.getInstance(BaseApplication.getAppContext(), "move");
    }

    @Override
    protected void initRxRetrofitApp() {
//        RxRetrofitApp.init(HOST, IP, API_SERVER_URL, DEFAULT_TIMEOUT);

        // 张鑫
        RxRetrofitApp.init("http://10.66.5.252:8080/");
        // 张升

//        RxRetrofitApp.init("http://10.66.5.229:8080/");
        //张斌
//        RxRetrofitApp.init("http://10.66.5.231:8780/");

        //服务器ProjectManagement/
//        RxRetrofitApp.init("http://10.66.2.82:8081/");

    }

}
