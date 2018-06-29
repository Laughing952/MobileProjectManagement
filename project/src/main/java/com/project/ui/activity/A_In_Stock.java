package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.even.RefreshEven;
import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AOutboundBinding;
import com.project.response.MaterialsRep;
import com.project.ui.adapter.Adapter_Classify;
import com.project.ui.adapter.Adapter_Materials;
import com.project.ui.mvpview.InStockView;
import com.project.ui.presenter.InStockPresenter;
import com.project.ui.viewmodel.ClassifyeVM;
import com.project.widget.dialog.Dialog_OutStock;
import com.waterbase.utile.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * 库存
 * <p>
 * 作者：Laughing on 2018/5/18 13:37
 * 邮箱：719240226@qq.com
 */

public class A_In_Stock extends TitleActivity implements InStockView {

    public static final int SAVE_STOCK = 163; // 库存
    public static final int OUT_STOCK = 164; // 出库

    private AOutboundBinding mBinding;
    private InStockPresenter mPresenter;
    private Adapter_Classify adapterClassify;
    private Adapter_Materials adapterMaterials;

    private int flag;

    /**
     * @param context
     * @param p_id    项目ID1
     * @param p_id    SAVE_STOCK 库存  OUT_STOCK 出库
     */
    public static void startActivity(Context context, String p_id, int flag) {
        Intent intent = new Intent(context, A_In_Stock.class);
        intent.putExtra("p_id", p_id);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mBinding = setView(R.layout.a_outbound);
        flag = getIntent().getIntExtra("flag", SAVE_STOCK);
        if (flag == SAVE_STOCK)
            setTitleText("物资库存");
        else if (flag == OUT_STOCK)
            setTitleText("出库：选择物资");
        initView();
        initData();
        initListener();
    }

    private void initView() {
        if (flag == SAVE_STOCK) {
            mBinding.llBottom.setVisibility(View.GONE);
        } else if (flag == OUT_STOCK) {
            mBinding.llBottom.setVisibility(View.VISIBLE);
        }
        mBinding.rvClassify.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvData.setLayoutManager(new LinearLayoutManager(this));
        adapterClassify = new Adapter_Classify();
        mBinding.rvClassify.setAdapter(adapterClassify);
        adapterMaterials = new Adapter_Materials(flag);
        mBinding.rvData.setAdapter(adapterMaterials);
    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));
        adapterClassify.setItemClickListener(new ItemClickListener<ClassifyeVM>() {
            @Override
            public void itemClick(View v, ClassifyeVM classifyeVM, int index) {
                mPresenter.classifyeClick(classifyeVM);
            }
        });
        adapterMaterials.setItemClickListener(new ItemClickListener<MaterialsRep>() {
            @Override
            public void itemClick(View v, MaterialsRep materialsRep, int index) {
                mPresenter.materialsClick(materialsRep);
            }
        });
        adapterMaterials.setOutStockListener(new Adapter_Materials.OutStockListener<MaterialsRep>() {
            @Override
            public void outStock(View v, MaterialsRep materialsRep, int index) {
                new Dialog_OutStock.Builder(A_In_Stock.this)
                        .setMaterialsRep(materialsRep)
                        .setConfirmListener(num -> {
                            LogUtil.d(TAG, "NUM: " + num);
                            materialsRep.setAskOutNum(num);
                            mPresenter.addOutStock(materialsRep);
                        })
                        .create()
                        .show();
            }
        });
    }

    private void initData() {
        mPresenter = new InStockPresenter(this, this, this);
        mPresenter.initData(getIntent().getStringExtra("p_id"));
    }


    @Override
    public void initClassifyList(List<ClassifyeVM> classifyeVMList) {
        adapterClassify.setData(classifyeVMList);
    }

    @Override
    public void initMaterialsList(List<MaterialsRep> materialsRepList) {
        adapterMaterials.setData(materialsRepList);
    }

    @Override
    public String getSearchContent() {
        return mBinding.etSearch.getText().toString();
    }

    @Override
    public void showInStock(boolean isShowInStock) {
        if (isShowInStock)
            mBinding.tvInStock.setTextColor(getResources().getColor(R.color.main_color));
        else
            mBinding.tvInStock.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void searchCancel() {
        mBinding.etSearch.setText(null);
    }

    @Override
    public void initCarNum(String size) {
        mBinding.tvClassificationNum.setText(size);
    }

    @Subscribe
    public void evenMessage(RefreshEven refreshEven) {
        mPresenter.initData(getIntent().getStringExtra("p_id"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
