package com.unistrong.baidumaplibrary.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.global.util.UserIdUtil;
import com.unistrong.baidumaplibrary.R;
import com.unistrong.baidumaplibrary.rep.AttendanceSettingRep;
import com.unistrong.baidumaplibrary.ui.mvpview.ProjectDistributionView;
import com.unistrong.baidumaplibrary.ui.presenter.ProjectDistributionPresenter;
import com.unistrong.baidumaplibrary.util.DesencityUtil;

import java.util.List;

/**
 * 项目地图
 * 作者：Laughing on 2018/5/7 07:23
 * 邮箱：719240226@qq.com
 */

public class A_Project_Distribution extends BaseMapActivity implements ProjectDistributionView {

    private ProjectDistributionPresenter mPresenter;
    private MapStatusUpdate mapStatusUpdate = null;
    private View pop;
    private TextView tv_title;

    private String mSigninPointName;
    private double mPersonSigninLaTitudeDouble;
    private double mPersonSigninLongTitudeDouble;

    @Override
    public void init() {
        setTitleText("项目地图");//标题

//        LinearLayout ll_hello = (LinearLayout) findViewById(R.id.ll_hello);
//        ll_hello.setVisibility(View.VISIBLE);
        LinearLayout ll_mode = (LinearLayout) findViewById(R.id.ll_mode);
        ll_mode.setVisibility(View.VISIBLE);

        initData();

        initListener();

    }

    private void initData() {

        // 4.	设置地图缩放为12
        mMapStatusUpdate = MapStatusUpdateFactory.zoomTo(5);
        baiduMap.setMapStatus(mMapStatusUpdate);
        mPresenter = new ProjectDistributionPresenter(this, this, this);
        mPresenter.downLoadData(UserIdUtil.getUserIdLong());
    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));

        /**
         * 标志物点击监听（点击显示pop）
         */
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 显示一个泡泡
                initPop(marker);
                return true;    //自己处理事件return true
            }
        });
        /**
         * 标志物移动监听（让pop跟随移动）
         */
        baiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {
                initPop(marker);

                mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));

            }

            @Override
            public void onMarkerDrag(Marker marker) {
                //标志正在拖动
                mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                //标志拖动结束
                mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));

            }
        });

    }


    @Override
    public void scaleMin() {
        // 		1)	缩小
        mapStatusUpdate = MapStatusUpdateFactory.zoomOut();
        baiduMap.setMapStatus(mapStatusUpdate);

    }

    @Override
    public void scaleMax() {
        // 		2)	放大
        mapStatusUpdate = MapStatusUpdateFactory.zoomIn();
        baiduMap.setMapStatus(mapStatusUpdate);

    }

    @Override
    public void getMapState() {
        // 		3)	旋转（0 ~ 360），每次在原来的基础上再旋转30度
        MapStatus currentMapStatus = baiduMap.getMapStatus();    // 获取地图当前的状态
        float rotate = currentMapStatus.rotate + 30;
        Log.i(TAG, "rotate = " + rotate);
        MapStatus mapStatus = new MapStatus.Builder().rotate(rotate).build();
        mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        baiduMap.setMapStatus(mapStatusUpdate);

    }

    @Override
    public void upDown() {
        MapStatus currentMapStatus;
        MapStatus mapStatus;// 		俯仰（0 ~ -45），每次在原来的基础上再俯仰-5
        currentMapStatus = baiduMap.getMapStatus();    // 获取地图当前的状态
        float overlook = currentMapStatus.overlook - 5;
        Log.i(TAG, "overlook = " + overlook);
        mapStatus = new MapStatus.Builder().overlook(overlook).build();
        mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        baiduMap.setMapStatus(mapStatusUpdate);

    }

    @Override
    public void move() {
        // 		5)	移动
        mapStatusUpdate = MapStatusUpdateFactory.newLatLng(qianYuYangGuangPos);
        baiduMap.animateMapStatus(mapStatusUpdate, 2000);
        baiduMap.setMapStatus(mapStatusUpdate);

    }

    @Override
    public void normalMode() {
        //普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        baiduMap.setTrafficEnabled(false);
    }

    @Override
    public void trafficMode() {
        //交通地图
        baiduMap.setTrafficEnabled(true);
    }

    @Override
    public void satelliteMode() {
        //卫星地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        baiduMap.setTrafficEnabled(false);
    }

    @Override
    public void backData(List<AttendanceSettingRep> response) {
        initMarkerData(response);
    }


    /**
     * 初始化标志覆盖物
     */
    private void initMarker(List<AttendanceSettingRep> response) {
        mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(beidajiePos);
        baiduMap.setMapStatus(mMapStatusUpdate);
        MarkerOptions options = new MarkerOptions();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker);

        for (AttendanceSettingRep res : response) {
            beidajiePos = new LatLng(mPersonSigninLaTitudeDouble, mPersonSigninLongTitudeDouble);//重新设置中心点为打卡点的经纬度
            //位置
            options.position(new LatLng(Double.parseDouble(res.getSigninPointLaTitude()) + 0.0001, Double.parseDouble(res.getSigninPointLongTitude())))    //添加一个向北的标志
                    .title(res.getSigninPointName())        // title
                    .icon(icon)        // 图标
                    .draggable(true);    // 设置图标可以拖动
            baiduMap.addOverlay(options);
        }


//        // 添加一个向北的标志
//        options = new MarkerOptions().icon(icon)
//                .title(mSigninPointName)
//                .position(new LatLng(mPersonSigninLaTitudeDouble + 0.0001, mPersonSigninLongTitudeDouble))
//                .draggable(true);
//        baiduMap.addOverlay(options);
//
//        // 添加一个向东的标志
//        options = new MarkerOptions().icon(icon)
//                .title("向东")
//                .position(new LatLng(mPersonSigninLaTitudeDouble, mPersonSigninLongTitudeDouble + 0.0001))
//                .draggable(true);
//        baiduMap.addOverlay(options);
//
//        // 添加一个向西南的标志
//        options = new MarkerOptions().icon(icon)
//                .title("向西南")
//                .position(new LatLng(mPersonSigninLaTitudeDouble - 0.0001, mPersonSigninLongTitudeDouble - 0.0001))
//                .draggable(true);
//        baiduMap.addOverlay(options);
    }

    /**
     * 初始化marker的数据
     *
     * @param response
     */
    private void initMarkerData(List<AttendanceSettingRep> response) {
        initMarker(response);

    }


    /**
     * 创建一个布局参数
     *
     * @param position
     * @return 一个布局参数
     */
    private MapViewLayoutParams createLayoutParams(LatLng position) {
        MapViewLayoutParams.Builder builder = new MapViewLayoutParams.Builder();
        builder.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode);// 指定坐标类型为经纬度
        builder.position(position);// 设置标志的位置
        builder.yOffset(-(DesencityUtil.dp2pix(A_Project_Distribution.this, 30)));// 设置View往上偏移
        MapViewLayoutParams layoutParams = builder.build();
        return layoutParams;
    }

    private void initPop(Marker marker) {
        // 显示一个泡泡
        if (pop == null) {
            pop = View.inflate(A_Project_Distribution.this, R.layout.pop, new FrameLayout(mContext));

            tv_title = (TextView) pop.findViewById(R.id.tv_title);
            mMapView.addView(pop, createLayoutParams(marker.getPosition()));
        } else {
            mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));
        }
        tv_title.setText(marker.getTitle());//设置初始化布局时定义的标题
    }


}
