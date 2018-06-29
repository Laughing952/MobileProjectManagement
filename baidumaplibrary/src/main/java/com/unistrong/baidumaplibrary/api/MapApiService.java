package com.unistrong.baidumaplibrary.api;

import com.unistrong.baidumaplibrary.rep.AttendanceSettingRep;
import com.unistrong.baidumaplibrary.req.AddCustomAttendanceReq;
import com.unistrong.baidumaplibrary.req.LocationPunchReq;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Laughing on 2018/5/26.
 */

public interface MapApiService {

    /**
     * 获取已添加的考勤列表（考勤地点选择/项目分布）
     *
     * @param map
     * @return
     */
    @GET("signin/findSigninSetting")
    Observable<List<AttendanceSettingRep>> uploadAttendanceListData(@QueryMap() Map<String, Object> map);

    /**
     * 获取考勤信息
     *
     * @param signinPointId
     * @return
     */
    @GET("signin/findSigninSetDetail")
    Observable<AddCustomAttendanceReq> downloadAttendanceData(@Query("signinPointId") String signinPointId);


    /**
     * 新增上下班、外出打卡数据
     *
     * @param locationPunchReq
     * @return
     */
    @POST("signin/saveSigninUser")
    Observable<Object> downloadPunchData(@Body LocationPunchReq locationPunchReq);


}
