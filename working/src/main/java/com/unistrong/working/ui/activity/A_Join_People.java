package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.global.ui.activity.TitleActivity;
import com.unistrong.working.R;
import com.unistrong.working.databinding.AJoinPeopleBinding;
import com.unistrong.working.response.JoinPeopleRep;
import com.unistrong.working.ui.adapter.Adapter_Join_People;
import com.unistrong.working.ui.mvpview.JoinPeopleView;
import com.unistrong.working.ui.presenter.JoinPeoplePresenter;
import com.unistrong.working.ui.viewmodel.JoinPeopleVM;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 参与人
 * 作者：Laughing on 2018/5/3 21:44
 * 邮箱：719240226@qq.com
 */

public class A_Join_People extends TitleActivity implements JoinPeopleView {

    private AJoinPeopleBinding mBinding;
    private JoinPeoplePresenter mPresenter;
    private Adapter_Join_People mAdapte;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_join_people);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        //获取上一个页面上传递来的数据
        mIntent = getIntent();
        ArrayList<JoinPeopleRep> mJoinPeopleRep = (ArrayList<JoinPeopleRep>) mIntent.getSerializableExtra("projectPersonBeans");
        mPresenter = new JoinPeoplePresenter(this, this, this);
//        mPresenter.initData(mJoinPeopleRep);
        mAdapte = new Adapter_Join_People();
        mBinding.rvCreateProjectChooseMember.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvCreateProjectChooseMember.setAdapter(mAdapte);

    }

    private void initListener() {

        mBinding.setClick(v -> mPresenter.click(v));
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Join_People.class));
    }

    public void initTitle() {
        setTitleText("项目成员");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("确定");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        ToastUtil.showToast(this, "确定");
        finish();
    }

    /**
     * 获取回传的数据
     * <p>
     * int requestCode 请求码 int resultCode 结果码 Intent data 意图(带着返回参数)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10002 && resultCode == RESULT_OK) {
            // 为图片控件设置内容

//            int imageId = data.getIntExtra("imageId", R.drawable.ic_launcher);
//
//            iv.setImageResource(imageId);
        }
    }

    @Override
    public void showData(List<JoinPeopleVM> vmList) {
        mAdapte.setData(vmList);
    }
}
