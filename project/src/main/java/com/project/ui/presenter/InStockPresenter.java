package com.project.ui.presenter;

import android.nfc.Tag;
import android.view.View;

import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.request.MaterialsReq;
import com.project.response.MaterialsRep;
import com.project.response.QualityListRep;
import com.project.ui.activity.A_In_Stock;
import com.project.ui.activity.A_Material_Record;
import com.project.ui.activity.A_Out_Stock_Verify;
import com.project.ui.mvpview.InStockView;
import com.project.ui.viewmodel.ClassifyeVM;
import com.project.ui.viewmodel.MaterialsVM;
import com.project.widget.dialog.Dialog_Car;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 库存
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class InStockPresenter {

    private static final String TAG = "InStockPresenter";

    private InStockView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    private List<MaterialsRep> materialsRepList; // 服务器返回的数据
    private List<ClassifyeVM> allList; // 全部数据分组
    private List<ClassifyeVM> inStockList; // 库存数据分组

    private String searchConent; // 搜索框的内容
    private boolean isShowInStock; // 是否正在展示库存
    private boolean isShowSearch; // 是否正在展示搜索 false库存数据 true展示全部数据
    private String pId;// 项目ID

    private List<MaterialsReq> carList;

    public InStockPresenter(InStockView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    /**
     * 获取物资数据 todo
     *
     * @param pId 项目ID
     */
    public void initData(String pId) {
        this.pId = pId;
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().saveStockList(pId)
                , new DefaultObserver<List<MaterialsRep>>() {
                    @Override
                    public void onSuccess(List<MaterialsRep> qualityListRep) {
                        if (materialsRepList == null)
                            materialsRepList = qualityListRep;
                        else {
                            materialsRepList.clear();
                            materialsRepList.addAll(qualityListRep);
                        }

                        allList = null; // 全部数据分组
                        inStockList = null; // 库存数据分组
                        showData();
                    }
                });
    }

    /**
     * 数据分组全部数据
     */
    private void classifyeAll() {
        if (allList == null) {
            allList = Classifye(materialsRepList);
        }
        mView.initClassifyList(allList);
        mView.initMaterialsList(allList.get(0).getMaterialsRepList());
    }


    public void click(View view) {
        if (view.getId() == R.id.tv_in_stock) {
            // 查看有库存
            if (isShowInStock) {
                // 展示全部
                isShowInStock = false;
            } else {
                isShowInStock = true;
            }
            mView.showInStock(isShowInStock);
            showData();
        } else if (view.getId() == R.id.iv_search) {
            // 搜索
            isShowSearch = true;
            searchConent = mView.getSearchContent();
            showData();
        } else if (view.getId() == R.id.tv_cancel_search) {
            // 取消搜索
            isShowSearch = false;
            searchConent = null;
            mView.searchCancel();
            showData();
        } else if (view.getId() == R.id.tv_ok) {
            // 确认出库
            if (carList == null || carList.isEmpty()) {
                ToastUtil.showToast(activity, "请至少选择一项物资");
                return;
            }
            A_Out_Stock_Verify.startActivity(activity, pId, carList);
        } else if (view.getId() == R.id.ll_car) {
            // 购物车
            if (carList == null || carList.isEmpty()) {
                ToastUtil.showToast(activity, "请至少选择一项物资");
                return;
            }
            new Dialog_Car.Builder(activity)
                    .setDelListener(materialsReq -> carList.remove(materialsReq))
                    .setConfirmListener(materialsRepList -> {
                        A_Out_Stock_Verify.startActivity(activity, pId, materialsRepList);
                    })
                    .setMaterialsRepList(carList)
                    .create()
                    .show();
        }
    }


    /**
     * 数据展示
     */
    private void showData() {
        if (!isShowInStock && !isShowSearch) {
            classifyeAll();
        } else if (isShowInStock && !isShowSearch) {
            inStockClassifye();
        } else if (!isShowInStock && isShowSearch) {
            searchClassifye();
        } else if (isShowInStock && isShowSearch) {
            searchAndinStockClassifye();
        }
    }

    private void searchAndinStockClassifye() {
        List<MaterialsRep> searchRepList = new ArrayList<>();
        for (MaterialsRep rep : materialsRepList) {
            if (rep.getClassifyeName().indexOf(searchConent) != -1
                    || rep.getMaterialsName().indexOf(searchConent) != -1
                    || rep.getStandardName().indexOf(searchConent) != -1
                    || rep.getCompanyName().indexOf(searchConent) != -1
                    && !StrUtil.isEmpty(rep.getSaveNum())
                    && !"0".equals(rep.getSaveNum())) {
                searchRepList.add(rep);
            }
        }
        List<ClassifyeVM> resultList = Classifye(searchRepList);
        mView.initClassifyList(resultList);
        mView.initMaterialsList(resultList.get(0).getMaterialsRepList());

    }

    /**
     * 搜索数据
     */
    private void searchClassifye() {
        List<MaterialsRep> searchRepList = new ArrayList<>();
        for (MaterialsRep rep : materialsRepList) {
            if (rep.getClassifyeName().indexOf(searchConent) != -1
                    || rep.getMaterialsName().indexOf(searchConent) != -1
                    || rep.getStandardName().indexOf(searchConent) != -1
                    || rep.getCompanyName().indexOf(searchConent) != -1) {
                searchRepList.add(rep);
            }
        }
        List<ClassifyeVM> searchList = Classifye(searchRepList);
        mView.initClassifyList(searchList);
        mView.initMaterialsList(searchList.get(0).getMaterialsRepList());
    }

    /**
     * 数据分组
     *
     * @param repList
     * @return
     */
    private List<ClassifyeVM> Classifye(List<MaterialsRep> repList) {
        List<ClassifyeVM> vmList = new ArrayList<>();
        vmList.add(new ClassifyeVM("-1", "全部", repList, true));
        Iterator<MaterialsRep> iter = repList.iterator();
        while (iter.hasNext()) {
            MaterialsRep rep = iter.next();
            boolean isAdd = true;
            for (int i = 0; i < vmList.size(); i++) {
                if (rep.getClassifyeID().equals(vmList.get(i).getClassifyeID())) {
                    vmList.get(i).getMaterialsRepList().add(rep);
                    isAdd = false;
                    break;
                }
            }
            if (isAdd) {
                ClassifyeVM vm = new ClassifyeVM(rep.getClassifyeID(), rep.getClassifyeName());
                vm.getMaterialsRepList().add(rep);
                vmList.add(vm);
            }
        }
        return vmList;
    }


    /**
     * 库存数据
     */
    private void inStockClassifye() {
        if (inStockList == null) {
            List<MaterialsRep> inStockAll = new ArrayList<>();
            for (MaterialsRep rep : materialsRepList) {
                if (!StrUtil.isEmpty(rep.getSaveNum()) && !"0".equals(rep.getSaveNum())) {
                    inStockAll.add(rep);
                }
            }
            inStockList = Classifye(inStockAll);
        }
        mView.initClassifyList(inStockList);
        mView.initMaterialsList(inStockList.get(0).getMaterialsRepList());

    }

    /**
     * 分类的item点击事件 todo
     *
     * @param classifyeVM
     */
    public void classifyeClick(ClassifyeVM classifyeVM) {
        mView.initMaterialsList(classifyeVM.getMaterialsRepList());
    }

    /**
     * 物资的item点击事件 todo
     *
     * @param materialsRep
     */
    public void materialsClick(MaterialsRep materialsRep) {
        A_Material_Record.startActivity(activity, materialsRep.getEncoding());
    }

    /**
     * 添加物资
     *
     * @param materialsRep
     */
    public void addOutStock(MaterialsRep materialsRep) {
        if (carList == null)
            carList = new ArrayList<>();
        boolean isAdd = true;
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getMaterialsID().equals(materialsRep.getMaterialsID())) {
                isAdd = false;
                int oldNum = 0;
                try {
                    oldNum = Integer.parseInt(carList.get(i).getAskOutNum());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                int addNum = 0;
                try {
                    addNum = Integer.parseInt(materialsRep.getAskOutNum());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                LogUtil.d(TAG, "oldNum: " + oldNum + "  ;addNum: " + addNum);
                int newNum = oldNum + addNum;
                carList.get(i).setAskOutNum(String.valueOf(newNum));
            }
        }
        if (isAdd) {
            MaterialsReq req = new MaterialsReq(materialsRep);
            carList.add(req);
        }
        mView.initCarNum(String.valueOf(carList.size()));
    }
}
