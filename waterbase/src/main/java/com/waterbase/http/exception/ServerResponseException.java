package com.waterbase.http.exception;

import android.content.Intent;

import com.waterbase.http.common.ErrorCode;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.LogUtil;

/**
 * 服务器返回的异常
 */
public class ServerResponseException extends RuntimeException {
    public ServerResponseException(int errorCode, String cause) {
//        super(cause);
        super(cause, new Throwable(errorCode + ""));
        LogUtil.e("Retrofit", "服务器响应失败，错误码：" + errorCode + "，错误原因：" + cause);


    }
}
