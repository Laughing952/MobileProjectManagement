package com.unistrong.working.api;

import com.unistrong.working.bean.IArrangedBean;
import com.unistrong.working.bean.IParticipatedBean;
import com.unistrong.working.bean.SentToMeBean;
import com.unistrong.working.request.AddCustomAttendanceReq;
import com.unistrong.working.request.AlterTaskScheduleReq;
import com.unistrong.working.request.CreateGeneralRequestReq;
import com.unistrong.working.request.DisposeGeneralRequestReq;
import com.unistrong.working.response.AttendanceSettingRep;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.response.ImageRep;
import com.unistrong.working.response.PunchRecordRep;
import com.unistrong.working.ui.viewmodel.CreateNewTaskVM;
import com.unistrong.working.ui.viewmodel.SentToMeDetailsVM;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by dell on 2017/4/1.
 */

public interface WorkingApiService {

    /**
     * 上传图片
     *
     * @param part
     */
    @Multipart
    @POST("img/uploadImage")
    Observable<ImageRep> uploadImage(@Part MultipartBody.Part part);

    /**
     * 上传创建通用需求申请
     *
     * @param body
     */
    @POST("approve/saveApprove")
    Observable<Object> uploadCreateRequestDetails(@Body CreateGeneralRequestReq body);

    /**
     * 获取通用申请数据(我审批的)
     *
     * @param id
     */
    @GET("approve/findMyApprove")
    Observable<List<GeneralRequestItemDetailRep>> getRequestApprovalDetails(@Query("userId") long id);

    /**
     * 获取通用申请数据(我提交的)
     *
     * @param id
     */
    @GET("approve/findMySubmit")
    Observable<List<GeneralRequestItemDetailRep>> getRequestCommitDetails(@Query("userId") long id);

    /**
     * 获取通用申请数据(抄送我的)
     *
     * @param id
     */
    @GET("approve/findMySend")
    Observable<List<GeneralRequestItemDetailRep>> getRequestCcMeDetails(@Query("userId") long id);

    /**
     * 处理通用申请
     *
     * @param disposeReq
     */
    @POST("approve/updateApprove")
    Observable<Object> disposeGeneralRequest(@Body DisposeGeneralRequestReq disposeReq);

    /**
     * 获取通用申请详情
     *
     * @param approveId
     */
    @GET("approve/findDetail")
    Observable<GeneralRequestItemDetailRep> getGeneralRequestInfo(@Query("approveId") Long approveId);

    /**
     * 获取人员信息
     *
     * @return
     */
    @GET("approve/findDeptUser")
    Observable<List<CreateNewTask_PerformerRep>> getPersonInfo(@Query("itemId") String projectId);

    /**
     * 获取项目信息
     *
     * @return
     */
    @GET("approve/findItem")
    Observable<List<CreateNewTask_ProjectNameRep>> getProjectInfo(@Query("userId") long id);


    /**
     * 上传自定义考勤规则数据
     *
     * @param addCustomAttendanceReq
     */
    @POST("signin/saveSigninSetting")
    Observable<Object> uploadCustomAttendanceData(@Body AddCustomAttendanceReq addCustomAttendanceReq);


    /**
     * 获取已添加的考勤列表
     *
     * @param map
     * @return
     */
    @GET("signin/findSigninSetting")
    Observable<List<AttendanceSettingRep>> uploadAttendanceListData(@QueryMap() Map<String, Object> map);

    /**
     * 加载点击的考勤item的详情信息
     *
     * @param signinPointId
     * @return
     */
    @GET("signin/findSigninSetDetail")
    Observable<AddCustomAttendanceReq> downloadAttendanceData(@Query("signinPointId") String signinPointId);


    /**
     * 删除考勤点
     *
     * @param attendanceSettingRep
     * @return
     */
    @POST("signin/updateSigninSetting")
    Observable<Object> deleteAttendancePoint(@Body AttendanceSettingRep attendanceSettingRep);


    /**
     * 编辑考勤点
     *
     * @param addCustomAttendanceReq
     */
    @POST("signin/updateSigninSetting")
    Observable<Object> uploadEditAttendanceDetails(@Body AddCustomAttendanceReq addCustomAttendanceReq);


    /**
     * 获取打卡记录列表信息（分页加载）
     *
     * @param map
     * @return
     */
    @GET("signin/findSigninUser")
    Observable<List<PunchRecordRep>> downloadPunchRecordData(@QueryMap() Map<String, Object> map);

    /**
     * 获取工作任务数据(派给我的)
     *
     * @param map
     */
    @GET("task/findMyTask")
    Observable<List<SentToMeBean>> getSendToMeList(@QueryMap Map<String, Object> map);

    /**
     * 获取工作任务数据(我参与的)
     *
     * @param map
     */
    @GET("task/findMyPartakeTask")
    Observable<List<IParticipatedBean>> getIParticpatedList(@QueryMap Map<String, Object> map);

    /**
     * 获取工作任务数据(我安排的)
     *
     * @param map
     */
    @GET("task/findMyPlanTask")
    Observable<List<IArrangedBean>> getIArrangedList(@QueryMap Map<String, Object> map);

    /**
     * 上传新建工作任务信息(cuihao)
     *
     * @param mCreateNewTaskVM
     */
    @POST("task/saveTask")
    Observable<Object> uploadTaskInfo(@Body CreateNewTaskVM mCreateNewTaskVM);

    /**
     * 获取工作任务详情
     *
     * @param taskId
     */
    @GET("task/findTaskDetail")
    Observable<SentToMeDetailsVM> getWorkTaskInfo(@Query("taskId") String taskId);

    /**
     * 修改任务进度
     *
     * @param alterTaskScheduleReq
     */
    @POST("task/updateTaskFeedback")
    Observable<Object> alterTaskSchedule(@Body AlterTaskScheduleReq alterTaskScheduleReq);






























    /**
     * 我安排的-取消任务
     *
     * @param taskId
     */
    @POST("task/deleteTask")
    Observable<Object> uploadCancleTask(@Query("taskId") String taskId);


    /**
     * 获取工作任务详情
     *
     * @param message
     */
    @POST("task/saveTaskMessage ")
    Observable<Object> sendMessageInfo(@Body SentToMeDetailsVM message);
}
