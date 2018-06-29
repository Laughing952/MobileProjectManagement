package com.login.api;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.widget.RemoteViews;

import com.login.R;
import com.login.help.FileUtils;
import com.waterbase.http.download.DownloadListener;
import com.waterbase.http.download.DownloadUtils;
import com.waterbase.utile.LogUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * 下载APK
 * Created by kb.zhang on 2018/4/10.
 */

public class S_DownLoadApp extends Service {
    private String TAG = "S_DownLoadApp";
    private NotificationManager notificationManager;
    private Notification myNotify;
    private DownloadUtils downloadUtils;
    private RemoteViews rv;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        downloadUtils = new DownloadUtils();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        myNotify = new Notification();
        myNotify.icon = R.mipmap.ic_management;
//        myNotify.tickerText = "准备下载...";
//        myNotify.when = System.currentTimeMillis();

        myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除

        rv = new RemoteViews(getPackageName(),
                R.layout.s_down_load_apk);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //下载路径
        String strUrl = intent.getStringExtra("url");

        //APK保存在DownLoad文件夹中
        File outputFile = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS), "ztzs.apk");
        //开始下载
        downloadUtils.download(strUrl, new DownloadListener() {
            @Override
            public void onProgress(int progress) {
                rv.setProgressBar(R.id.progress, 100, progress, false);
                rv.setTextViewText(R.id.text_content, progress + "%");
                myNotify.contentView = rv;
                notificationManager.notify(0, myNotify);
            }

            @Override
            public void onSuccess(ResponseBody responseBody) {
                notificationManager.cancel(0);
                try {
                    FileUtils.writeFile(responseBody.byteStream(), outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /* 安装APK */
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                //版本在7.0以上是不能直接通过uri访问的
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //添加这一句表示对目标应用临时授权该Uri所代表的文件
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                    intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.unistrong.mobileprojectmanagement.fileProvider", outputFile),
                            "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } else {
                    intent.setDataAndType(Uri.fromFile(outputFile),
                            "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                startActivity(intent);
                LogUtil.d(TAG, "下载完成！");
            }

            @Override
            public void onFail(String message) {
                rv.setTextViewText(R.id.text_title, "下载失败");
                myNotify.contentView = rv;
                notificationManager.notify(0, myNotify);
                LogUtil.e(TAG, message);
            }

            @Override
            public void onComplete() {

            }
        });

        return super.onStartCommand(intent, flags, startId);
    }
}
