package com.qrcode.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.qrcode.qrcodecore.BGAQRCodeUtil;
import com.qrcode.zixing.QRCodeEncoder;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.common.ProgressUtils;
import com.waterbase.ui.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Water on 2018/5/2.
 */

public class QRCode {


    public interface CreateQRCodeResultListener {
        void result(Bitmap bitmap);
    }

    /**
     * 创建黑色前景色、白色背景色的二维码图片
     *
     * @param context
     * @param lifecycleProvider 生命周期管理
     * @param content           内容
     * @param resultListener
     */
    public static void createQRCode(Context context, LifecycleProvider lifecycleProvider
            , String content, CreateQRCodeResultListener resultListener) {

        Observable.just(content)
                .map(s -> QRCodeEncoder.syncEncodeQRCode(s, BGAQRCodeUtil.dp2px(context, 150)))
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> {
                    if (resultListener != null)
                        resultListener.result((Bitmap) bitmap);
                });
    }

    /**
     * 创建指定前景色、白色背景色的二维码图片
     *
     * @param context
     * @param lifecycleProvider 生命周期管理
     * @param content           内容
     * @param color             颜色 R.color.red
     * @param resultListener
     */
    public static void createQRCode(Context context, LifecycleProvider lifecycleProvider
            , String content, int color, CreateQRCodeResultListener resultListener) {

        Observable.just(content)
                .map(s -> QRCodeEncoder.syncEncodeQRCode(s, BGAQRCodeUtil.dp2px(context, 150)
                        , context.getResources().getColor(color)))
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> {
                    if (resultListener != null)
                        resultListener.result((Bitmap) bitmap);
                });
    }




    /**
     * 创建指定前景色、白色背景色、带logo的二维码图片。
     *
     * @param context
     * @param lifecycleProvider 生命周期管理
     * @param content           内容
     * @param color             颜色 R.color.red
     * @param logoBitmap logo图片
     * @param resultListener
     */
    public static void createQRCode(Context context, LifecycleProvider lifecycleProvider
            , String content, int color, Bitmap logoBitmap, CreateQRCodeResultListener resultListener) {

        Observable.just(content)
                .map(s -> QRCodeEncoder.syncEncodeQRCode(s, BGAQRCodeUtil.dp2px(context, 150)
                        , context.getResources().getColor(color), logoBitmap))
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> {
                    if (resultListener != null)
                        resultListener.result((Bitmap) bitmap);
                });
    }

}
