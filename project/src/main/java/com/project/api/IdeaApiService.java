package com.project.api;

import com.project.request.CreateProjectReq;
import com.project.request.NewQualityReq;
import com.project.request.OutStockVerifyReq;
import com.project.request.WorkerReq;
import com.project.response.ExamineTypeRep;
import com.project.response.ImageRep;
import com.project.response.MaterialsRep;
import com.project.response.ProjectDoingRep;
import com.project.response.ProjectHomeRep;
import com.project.response.QualityListRep;
import com.project.response.UserInfoRep;
import com.project.response.WorkerRep;
import com.project.response.WorkerTypeRep;
import com.project.ui.viewmodel.CreateProjectVM;
import com.project.ui.viewmodel.ProjectMemberCheckedVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by laughing 2018/5/15.
 */

public interface IdeaApiService {

    /**
     * 上传图片
     *
     * @param part
     */
    @Multipart
    @POST("img/uploadImage")
    Observable<ImageRep> uploadImage(@Part MultipartBody.Part part);

    /**
     * 退出登录
     *
     * @return
     */
    @PUT("user/logout")
    Observable<Object> loginOut();


    @GET("project/manage/item")
    Observable<ProjectHomeRep> projectHome(@Query("itemId") String pId, @Query("userId") String userId);

    /**
     * 质量检查列表
     *
     * @param pId         项目id
     * @param staterIndex 质量状态
     * @param date        date 2018-05-08
     * @return
     */
    @GET("qualityCheck/query")
    Observable<List<QualityListRep>> qualityList(@Query("pId") String pId
            , @Query("qcIspass") int staterIndex, @Query("qcDate") String date
            , @Query("currentPage") int currentPage, @Query("pageSize") int pageSize);

    /**
     * 未完成项目-列表
     */
    @GET("project/manage/list?status=0")
    Observable<ArrayList<ProjectDoingRep>> getProjectDoingData(@QueryMap() Map<String, Object> map);

    /**
     * 已归档（完成）项目-列表
     */
    @GET("project/manage/list?status=1")
    Observable<ArrayList<ProjectDoingRep>> getProjectOverData(@QueryMap() Map<String, Object> map);

    /**
     * 创建项目（接口不对）
     */
    @POST("project/manage/add")
    Observable<Object> createProject(@Body CreateProjectReq addReq);

    /**
     * 编辑项目
     */
    @POST("project/manage/update")
    Observable<Object> uploadEditProject(@Body CreateProjectVM mCreateProjectVM);


    /**
     * 获取人员列表（cuihao）
     */
    @GET("project/manage/queryPerson")
    Observable<ArrayList<ProjectMemberCheckedVM>> getPersonInfoList(@Query("userId") String userId);

    /**
     * 删除项目
     */
    @POST("project/manage/deleteItem")
    Observable<Object> deleteProject(@Query("itemId") String itemId);

    /**
     * 归档项目
     */
    @POST("project/manage/updateStatus")
    Observable<Object> archiveProject(@Query("itemId") String itemId);

    /**
     * 归档项目
     */
    @POST("project/manage/cancelStatus")
    Observable<Object> cancelArchiveProject(@Query("itemId") String itemId);

    /**
     * 获取编辑项目信息（cuihao）
     */
    @GET("project/manage/item")
    Observable<CreateProjectVM> getEditProjectInfo(@Query("itemId") String projectId);

    /**
     * 搜索项目-返回个或列表
     */
    @GET("project/manage/queryItem")
    Observable<ArrayList<ProjectDoingRep>> getProjectSearchData(@QueryMap() Map<String, Object> map);


    /************************************************************************************************/
    /**
     * 查询工人信息列表
     *
     * @param pId
     * @return
     */
    @GET("projectWorker/query")
    Observable<List<WorkerRep>> workersList(@Query("itemId") String pId);

    /**
     * 新建工人信息
     * @param workerReq
     * @return
     */
    @POST("projectWorker/save")
    Observable<Object> newWorker(@Body() WorkerReq workerReq);

    /**
     * 修改工人信息
     * @param workerReq
     * @return
     */
    @POST("projectWorker/update")
    Observable<Object> updateWorker(@Body() WorkerReq workerReq);

    /**
     * 新建工人信息
     * @param workerId
     * @return
     */
    @GET("projectWorker/queryById")
    Observable<WorkerReq> queryWorker(@Query("workerId") String workerId);

    /**
     * 删除工人信息
     * @param workerId
     * @return
     */
    @POST("projectWorker/delete")
    Observable<Object> delWorker(@Query("workerId") String workerId);

    /**
     * 查询工种信息
     *
     * @return
     */
    @GET("projectWorker/queryWorkType")
    Observable<List<WorkerTypeRep>> queryWorkType();

    /**
     * 新建质量检查
     *
     * @param qualityReq
     * @return
     */
    @POST("qualityCheck/save")
    Observable<Object> newQuality(@Body() NewQualityReq qualityReq);

    /**
     * 修改质量检查
     *
     * @param qualityReq
     * @return
     */
    @POST("qualityCheck/update")
    Observable<Object> updateQuality(@Body() NewQualityReq qualityReq);

    /**
     * 删除质量检查
     *
     * @param e_id
     * @return
     */
    @POST("qualityCheck/delete")
    Observable<Object> delQuality(@Query("qcId") String e_id);

    /**
     * 查询查询检查类型
     *
     * @return
     */
    @GET("qualityCheck/queryType")
    Observable<List<ExamineTypeRep>> queryType();

    /**
     * 根据id查询信息
     *
     * @return
     */
    @GET("qualityCheck/queryById")
    Observable<QualityListRep> queryById(@Query("qcId") String qcId, @Query("itemId") String pId);

    /**
     * 查询物资库存
     *
     * @return
     */
    @GET("MaterialStock/query")
    Observable<List<MaterialsRep>> saveStockList(@Query("itemId") String pId);

    /**
     * 查询物资库存
     *
     * @return
     */
    @GET("materialType/findMaterialCount")
    Observable<MaterialsRep> materialRecord(@Query("mateCode") String materialsID);


    /**
     * 出库
     *
     * @param stockVerifyReq
     * @return
     */
    @POST("MaterialManage/saveOutInvo")
    Observable<Object> outStock(@Body OutStockVerifyReq stockVerifyReq);





    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @GET("sys/user/userById")
    Observable<UserInfoRep> getUserInfo(@Query("userId") long userId);


}
