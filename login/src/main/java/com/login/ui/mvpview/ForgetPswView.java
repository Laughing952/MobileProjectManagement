package com.login.ui.mvpview;

import com.login.response.UserLoginRep;

/**
 * Created by edz on 2018/3/27.
 */

public interface ForgetPswView {
    /**
     * 显示正在加载进度框
     */
    void showLoading();

    /**
     * 隐藏正在加载进度框
     */
    void hideLoading();

    /**
     * 获取验证码成功
     */
    void getCodeSucceed();

    /**
     * 修改密码成功
     *
     * @param data 数据源
     */
    void setPswSucceed(UserLoginRep data);

    /**
     * 当数据请求失败后，调用此接口提示
     *
     * @param msg 失败原因
     */
    void showFailureMessage(String msg);

    /**
     * 当数据请求异常，调用此接口提示
     */
    void showErrorMessage(String msg);

    /**
     * 校验手机号码成功
     */
    void checkPhoneNumSucceed();

    /**
     * 校验手机号码失败
     *
     * @param msg 失败原因
     */
    void checkPhoneNumFailure(String msg);

    /**
     * 校验密码成功
     */
    void checkPsw1Succeed();

    /**
     * 校验密码失败
     *
     * @param msg 失败原因
     */
    void checkPsw1Failure(String msg);

    /**
     * 校验密码成功
     */
    void checkPsw2Succeed();

    /**
     * 校验密码失败
     *
     * @param msg 失败原因
     */
    void checkPsw2Failure(String msg);

    /**
     * 开始倒计时
     */
    void countdown();
}
