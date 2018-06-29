package com.mycentre.api;


import com.mycentre.bean.VersionInfo;
import com.mycentre.request.FeedbackReq;
import com.mycentre.request.UserChangePhoneReq;
import com.mycentre.response.ImageRep;
import com.mycentre.ui.viewmodel.PersonInfoVM;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by dell on 2017/4/1.
 */

public interface IdeaApiService {

    /**
     * 退出登录
     *
     * @return
     */
    @PUT("user/logout")
    Observable<Object> loginOut();


    /**
     * 获取版本信息进行apk下载
     *
     * @return
     */
    @GET("appVersion/getLatestVersion")
    Observable<VersionInfo> getVersion();

    /**
     * 上传图片
     *
     * @return
     */
    @Multipart
    @POST("img/uploadImage")
    Observable<ImageRep> uploadImage(@Part MultipartBody.Part part);

    /**
     * 上传反馈信息
     *
     * @return
     */
    @POST("opinion/save")
    Observable<Object> uploadFeedbackInfo(@Body FeedbackReq feedbackReq);

    /**
     * 上传个人资料信息
     *
     * @return
     */
    @POST("sys/user/updateUser")
    Observable<Object> uploadPersonInfo(@Body PersonInfoVM personInfoVM);

    /**
     * 下载个人资料信息
     *
     * @return
     */
    @GET("sys/user/userById")
    Observable<PersonInfoVM> getPersonInfo(@Query("userId") long userId);


    /**
     * 修改手机号
     *
     * @return sys/user/updateByUser
     */
    @POST("sys/user/updateByUser")
    Observable<Object> changePhoneNum(@Body UserChangePhoneReq registerReq);
}
