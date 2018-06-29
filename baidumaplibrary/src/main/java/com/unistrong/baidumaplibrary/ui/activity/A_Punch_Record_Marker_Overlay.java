package com.unistrong.baidumaplibrary.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.unistrong.baidumaplibrary.R;
import com.unistrong.baidumaplibrary.util.DesencityUtil;
import com.waterbase.utile.LogUtil;


/**
 * 标志覆盖物显示标题，并且可以拖动（重点）
 * 作者：Laughing on 2016/7/27 19:36
 * 邮箱：719240226@qq.com
 */
public class A_Punch_Record_Marker_Overlay extends BaseMapActivity {


    private View pop;
    private TextView tv_title;
    private static final String PUNCH_RECORD_NAME = "PunchRecordName";//考勤点名称
    private static final String PUNCH_RECORD_LAT = "PunchRecordLat";//考勤点纬度
    private static final String PUNCH_RECORD_LONG = "PunchRecordLong";//考勤点经度
    private String mSigninPointName;
    private double mPersonSigninLaTitudeDouble;
    private double mPersonSigninLongTitudeDouble;

    @Override
    public void init() {
        initMarker();
        initListener();

    }

    /**
     * 初始化标志物移动监听并实现
     */
    private void initListener() {
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

    private void initPop(Marker marker) {
        // 显示一个泡泡
        if (pop == null) {
            pop = View.inflate(A_Punch_Record_Marker_Overlay.this, R.layout.pop, new FrameLayout(mContext));

            tv_title = (TextView) pop.findViewById(R.id.tv_title);
            mMapView.addView(pop, createLayoutParams(marker.getPosition()));
        } else {
            mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));
        }
        tv_title.setText(marker.getTitle());//设置初始化布局时定义的标题
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
        builder.yOffset(-(DesencityUtil.dp2pix(A_Punch_Record_Marker_Overlay.this, 30)));// 设置View往上偏移
        MapViewLayoutParams layoutParams = builder.build();
        return layoutParams;
    }


    private void initMarkerData() {
        Intent intent = getIntent();
        mSigninPointName = intent.getStringExtra(PUNCH_RECORD_NAME);
        String personSigninLaTitude = intent.getStringExtra(PUNCH_RECORD_LAT);
        String personSigninLongTitude = intent.getStringExtra(PUNCH_RECORD_LONG);
        mPersonSigninLaTitudeDouble = Double.parseDouble(personSigninLaTitude);
        mPersonSigninLongTitudeDouble = Double.parseDouble(personSigninLongTitude);
        LogUtil.e("TAG", "laughing----------mPersonSigninLaTitudeDouble------------>   " + mPersonSigninLaTitudeDouble);
        LogUtil.e("TAG", "laughing----------mPersonSigninLongTitudeDouble------------>   " + mPersonSigninLongTitudeDouble);
        beidajiePos = new LatLng(mPersonSigninLaTitudeDouble, mPersonSigninLongTitudeDouble);//重新设置中心点为打卡点的经纬度


    }

    /**
     * 初始化标志覆盖物
     */
    private void initMarker() {
        initMarkerData();
        mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(beidajiePos);
        baiduMap.setMapStatus(mMapStatusUpdate);
        MarkerOptions options = new MarkerOptions();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker);
        //位置
        options.position(new LatLng(mPersonSigninLaTitudeDouble + 0.0001, mPersonSigninLongTitudeDouble))    //添加一个向北的标志
                .title(mSigninPointName)        // title
                .icon(icon)        // 图标
                .draggable(true);    // 设置图标可以拖动
        baiduMap.addOverlay(options);
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


}
