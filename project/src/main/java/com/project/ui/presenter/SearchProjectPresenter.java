package com.project.ui.presenter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.response.ProjectDoingRep;
import com.project.ui.mvpview.SearchProjectView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class SearchProjectPresenter {
    private SearchProjectView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private Map<String, Object> map;
    private int currentPage = 1;
    private int pageSize = 10;
    private String mStringData;
    private HttpManager httpManager;

    public SearchProjectPresenter(SearchProjectView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        map = new HashMap<>();
        httpManager = new HttpManager(activity, lifecycleProvider);
    }

    public void click(View view) {
        if (view.getId() == R.id.tv_search_project_search) {
            //搜索项目
            mView.getBoxText();
            search();
        }
    }

    public void search() {
        if (!StrUtil.isEmpty(mStringData)) {
            //发送网络请求，进行项目搜索
            doHttpRequest();
        } else {
            ToastUtil.showToast(activity, "请输入项目名");
        }

    }

    //获取搜索框文本内容
    public void setStringData(String stringData) {
        mStringData = stringData;
    }

    /**
     * 网络请求--用项目名称来模糊查询
     */
    public void doHttpRequest() {
        currentPage = 1;
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        // TODO: 2018/5/22  添加搜索的名字参数
        map.put("text", mStringData);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getProjectSearchData(map)
                , new DefaultObserver<List<ProjectDoingRep>>() {
                    @Override
                    public void onSuccess(List<ProjectDoingRep> involvedInfoRep) {
                        mView.initDataResult(involvedInfoRep);
                    }
                });

    }

    /**
     * 加载更多
     */
    public void loadMoreData() {
        currentPage++;
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getProjectOverData(map)
                , new DefaultObserver<List<ProjectDoingRep>>() {
                    @Override
                    public void onSuccess(List<ProjectDoingRep> involvedInfoRep) {
                        mView.loadMoreResult(involvedInfoRep);
                    }
                });
    }

    /**
     * 删除项目
     */
    public void deleteProject(ProjectDoingRep data) {
        AlertDialog dialog1 = new AlertDialog.Builder(activity)
                .setCancelable(false)
                .setMessage("删除项目会同步删除与项目有关的任务、报销、申请、合同、记账、发票、记工、" +
                        "物资等数据，且数据不可恢复，请慎重选择。确定删除项目"+data.getItemname())
                .setPositiveButton("确定", (dialog, which) -> {
                    dialog.dismiss();
                    uploadDeleteProject(data.getItemid());
                }).setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .create();
        dialog1.show();
        dialog1.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        dialog1.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.color_cancel));
    }


    public void uploadDeleteProject(String itemId){
        httpManager.doHttpDeal(RetrofitHelper.getApiService().deleteProject(itemId)
                , new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.refreshResult();
                    }
                });
    }
    public void uploadArchiveProject(String itemId){
        httpManager.doHttpDeal(RetrofitHelper.getApiService().archiveProject(itemId)
                , new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.refreshResult();
                    }
                });
    }

    /**
     * 归档项目
     */
    public void archiveProject(String projectId){
        AlertDialog dialog1 = new AlertDialog.Builder(activity)
                .setCancelable(false)

                .setMessage("是否归档")
                .setPositiveButton("确定", (dialog, which) -> {
                    dialog.dismiss();
                    uploadArchiveProject(projectId);
                }).setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .create();
        dialog1.show();
        dialog1.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        dialog1.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.color_cancel));
    }

}
