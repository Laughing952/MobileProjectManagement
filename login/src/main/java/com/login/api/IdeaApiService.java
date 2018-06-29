package com.login.api;

import com.login.request.PasswordResetReq;
import com.login.request.UerLoginReq;
import com.login.request.UserRegisterReq;
import com.login.response.UserLoginRep;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by dell on 2017/4/1.
 */

public interface IdeaApiService {

    /**
     * 获取验证码
     *
     * @return
     */
    @GET("register/sms/{phoneNum}")
    Observable<String> sms(@Path("phoneNum") String phoneNum);


    /**
     * 登录
     *
     * @param user
     * @return
     */
    @POST("/sys/login/mobile")
    Observable<UserLoginRep> login(@Body UerLoginReq user);

    /**
     * 用户注册
     *
     * @param registerReq
     * @return
     */
    @POST("sys/register/mobile")
    Observable<UserLoginRep> register(@Body UserRegisterReq registerReq);

    /**
     * 忘记密码
     *
     * @param user
     * @return
     */
    @POST("sys/forgetPassword")
    Observable<UserLoginRep> forgetPassword(@Body PasswordResetReq user);



















    /**
     * 修改密码
     *
     * @param
     * @return
     */
    @POST("sys/updateByPassword")
    Observable<UserLoginRep> updatePassword(@Body UserRegisterReq user);



}
