package com.unistrong.baidumaplibrary.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.global.util.DateUtils;
import com.global.util.UserIdUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.unistrong.baidumaplibrary.R;
import com.unistrong.baidumaplibrary.rep.AttendanceSettingRep;
import com.unistrong.baidumaplibrary.req.AddCustomAttendanceReq;
import com.unistrong.baidumaplibrary.req.LocationPunchReq;
import com.unistrong.baidumaplibrary.ui.mvpview.LocationView;
import com.unistrong.baidumaplibrary.ui.presenter.LocationPresenter;
import com.unistrong.baidumaplibrary.util.DistanceUtil;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ToastUtil;

import java.util.List;

/**
 * 定位打卡
 * 作者：Laughing on 2017/7/30 09:52
 * 邮箱：719240226@qq.com
 */

public class A_Punch extends BaseMapActivity implements LocationView {
    public LocationClient mLocationClient = null;
    public LocationPresenter mPresenter;
    public BDLocationListener myListener = new MyLocationListener();
    protected double mLatitude;
    protected double mLongitude;
    protected LinearLayout mLl_content;
    private StringBuffer mSb;
    private Dialog dialog;
    private List<AttendanceSettingRep> mAttendanceSettingRepList;

    private String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, //定位权限
            Manifest.permission.ACCESS_FINE_LOCATION, //访问GPS定位
            Manifest.permission.ACCESS_WIFI_STATE, //用于访问wifi网络信息
            Manifest.permission.ACCESS_NETWORK_STATE,//获取运营商信息，用于支持提供运营商信息相关的接口
            Manifest.permission.CHANGE_WIFI_STATE};// 这个权限用于获取wifi的获取权限，wifi信息会用来进行网络定位
    private String mAddress = "无法获取您当前位置，请检查网络是否可用";
    private double mDiatance;
    private String mSigninPointId;

    @Override
    public void init() {
        initTitleInner();//子类初始化标题
        initUI();
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        //初始化定位信息
        initLocation();
        baiduMap.setMyLocationEnabled(true);    // 开启定位图层
        setMyLocationConfigeration(MyLocationConfiguration.LocationMode.COMPASS);
//        mLocationClient.start();    // 开始定位
        initData();
    }

    private void initData() {
        mPresenter = new LocationPresenter(this, this, this);
        mPresenter.downLoadData(UserIdUtil.getUserIdLong());//获取已添加的考勤列表

    }

    private void initTitleInner() {
        setTitleText("定位打卡");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("打卡");

    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        mPresenter.selectPerformer(mAttendanceSettingRepList);//请选考勤点
    }

    /**
     * 显示是否打卡对话框
     *
     * @param msg  显示信息
     * @param flag 标记打卡类型0：上班打卡  1：下班打卡  2：外勤打卡
     */
    public void showNoticeDialog(String msg, int flag, AttendanceSettingRep rep) {
        // 构造对话框

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("确定要打卡吗？");
        builder.setMessage("当前位置：" + msg);
        // 更新
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mPresenter.getPointInfo(flag, rep.getSigninPointId());//获取打卡点的位置信息（注意：不是定位出来的信息）
                dialog.dismiss();
            }
        });
        // 稍后更新
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }


    /**
     * 提交打卡数据  0：上班打卡  1：下班打卡  2：外勤打卡
     *
     * @param flag
     */
    private void uploadData(int flag, AddCustomAttendanceReq response) {
        //拿到考勤点经纬度与定位到的经纬度进行计算
        mDiatance = calculation(response.getSigninPointLaTitude(), response.getSigninPointLongTitude());


        String signinType = "";
        switch (flag) {
            case 0:
                signinType = "0";
                break;
            case 1:
                signinType = "1";
                break;
            case 2:
                signinType = "2";
                break;

            default:

                break;
        }
        // TODO: 2018/5/18 // 提交打卡数据

        String mLongitudeStr = String.valueOf(mLongitude);
        String mLatitudeStr = String.valueOf(mLatitude);
        String mDiatanceStr = String.valueOf(mDiatance);
        LogUtil.e("TAG", "laughing------------------mDiatanceStr---->   " + mDiatanceStr);
        LocationPunchReq locationPunchReq = new LocationPunchReq(response.getItemId(), mSigninPointId,
                UserIdUtil.getUserIdLong(), mAddress, mDiatanceStr, "", signinType,
                UserIdUtil.getUserIdLong(), response.getSigninPointName(), mLongitudeStr, mLatitudeStr);
        mPresenter.commitPunchData(locationPunchReq);

    }


    /**
     * 用来显示按钮控制模式:罗盘，普通，跟随（这里复用地图显示模式的三个按钮）
     */
    protected void initUI() {
        mLl_content = (LinearLayout) findViewById(R.id.ll_mode);
        mLl_content.setVisibility(View.GONE);//隐藏布局三个按钮
        Button bt_normal = (Button) findViewById(R.id.bt_normal);
        bt_normal.setText("普通");
        Button bt_traffic = (Button) findViewById(R.id.bt_traffic);
        bt_traffic.setText("罗盘");
        Button bt_satellite = (Button) findViewById(R.id.bt_satellite);
        bt_satellite.setText("跟随");
    }

    public void click(View v) {
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);//每次先把地图状态清空，在设置
        int i = v.getId();
        if (i == R.id.bt_normal) {// 普通态： 更新定位数据时不对地图做任何操作
            setMyLocationConfigeration(MyLocationConfiguration.LocationMode.NORMAL);

        } else if (i == R.id.bt_traffic) {// 罗盘态，显示定位方向圈，保持定位图标在地图中心
            setMyLocationConfigeration(MyLocationConfiguration.LocationMode.COMPASS);


        } else if (i == R.id.bt_satellite) {// 跟随态，保持定位图标在地图中心
            setMyLocationConfigeration(MyLocationConfiguration.LocationMode.FOLLOWING);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        RxPermissions rxPermissions = new RxPermissions(A_Punch.this);
        rxPermissions
                .request(perms)
                .subscribe(granted -> {
                    if (granted) {
                        mLocationClient.start();    // 开始定位
                        ToastUtil.showToast(mContext, "开始定位");
                    } else {
                        ToastUtil.showToast(mContext, "拒绝权限");

                    }
                });
    }


    /**
     * 设置定位配置信息
     *
     * @param mode 传入的模式： NORMAL,普通
     *             FOLLOWING,跟随
     *             COMPASS;罗盘
     */
    private void setMyLocationConfigeration(MyLocationConfiguration.LocationMode mode) {
//        MyLocationConfiguration.LocationMode var1;//以作为方法参数传进来了
        boolean enableDirection = true;    // 设置允许显示方向
        BitmapDescriptor customMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);    // 自定义定位的图标
        MyLocationConfiguration configuration = new MyLocationConfiguration(mode, enableDirection, customMarker);
//        baiduMap.setMyLocationConfigeration(configuration);
        baiduMap.setMyLocationConfiguration(configuration);
    }

    /**
     * 初始化
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 3000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    /**
     * 获取考勤列表信息
     *
     * @param response
     */
    @Override
    public void backData(List<AttendanceSettingRep> response) {

        mAttendanceSettingRepList = response;
    }

    /**
     * 请求到的
     *
     * @param rep
     */
    @Override
    public void setPerformerResult(AttendanceSettingRep rep) {
        showDialog2(rep);
    }

    /**
     * 考勤点信息拿到经纬度与定位的经纬度用来计算考勤距离
     *
     * @param response
     */
    @Override
    public void backPointInfo(int flag, AddCustomAttendanceReq response) {
        mSigninPointId = response.getSigninPointId();
        uploadData(flag, response);//提交打卡数据
    }

    /**
     * 打卡成功关闭页面
     */
    @Override
    public void finishiPage() {
        finish();
    }

    /**
     * 拿到考勤点经纬度与定位到的经纬度进行计算
     *
     * @param signinPointLaTitude
     * @param signinPointLongTitude
     */
    private double calculation(String signinPointLaTitude, String signinPointLongTitude) {
        return DistanceUtil.GetShortDistance(getDouble(signinPointLongTitude), getDouble(signinPointLaTitude), mLongitude, mLatitude);
    }

    /**
     * 把String 类型的经纬度转换为double类型
     *
     * @param str
     * @return
     */
    private double getDouble(String str) {

        return Double.parseDouble(str);
    }

    public class MyLocationListener implements BDLocationListener {


        @Override
        public void onReceiveLocation(BDLocation location) {
            /**
             * 处理定位结果显示到地图上
             */
            if (location != null) {
                MyLocationData.Builder builder = new MyLocationData.Builder();
                builder.accuracy(location.getRadius());// 设置方向
                builder.direction(location.getDirection());// 设置精度
                builder.latitude(location.getLatitude());// 设置纬度
                builder.longitude(location.getLongitude());// 设置经度

                MyLocationData locationData = builder.build();
                baiduMap.setMyLocationData(locationData);// 把定位数据显示到地图上

                getLocationData(location);//获取定位数据的方法，最后集中上传
            }

            //获取定位结果
            mSb = new StringBuffer(256);

            mSb.append("time : ");
            mSb.append(location.getTime());    //获取定位时间

            mSb.append("\nerror code : ");
            mSb.append(location.getLocType());    //获取类型类型

            mSb.append("\nlatitude : ");
            mSb.append(location.getLatitude());    //获取纬度信息

            mSb.append("\nlontitude : ");
            mSb.append(location.getLongitude());    //获取经度信息

            mSb.append("\nradius : ");
            mSb.append(location.getRadius());    //获取定位精准度

            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                // GPS定位结果
                mSb.append("\nspeed : ");
                mSb.append(location.getSpeed());    // 单位：公里每小时

                mSb.append("\nsatellite : ");
                mSb.append(location.getSatelliteNumber());    //获取卫星数

                mSb.append("\nheight : ");
                mSb.append(location.getAltitude());    //获取海拔高度信息，单位米

                mSb.append("\ndirection : ");
                mSb.append(location.getDirection());    //获取方向信息，单位度

                mSb.append("\naddr : ");
                mSb.append(location.getAddrStr());    //获取地址信息

                mSb.append("\ndescribe : ");
                mSb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                // 网络定位结果
                mSb.append("\naddr : ");
                mSb.append(location.getAddrStr());    //获取地址信息

                mSb.append("\noperationers : ");
                mSb.append(location.getOperators());    //获取运营商信息

                mSb.append("\ndescribe : ");
                mSb.append("网络定位成功");

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果
                mSb.append("\ndescribe : ");
                mSb.append("离线定位成功，离线定位结果也是有效的");

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                mSb.append("\ndescribe : ");
                mSb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                mSb.append("\ndescribe : ");
                mSb.append("网络不同导致定位失败，请检查网络是否通畅");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                mSb.append("\ndescribe : ");
                mSb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

            }

            mSb.append("\nlocationdescribe : ");
            mSb.append(location.getLocationDescribe());    //位置语义化信息

            List<Poi> list = location.getPoiList();    // POI数据
            if (list != null) {
                mSb.append("\npoilist size = : ");
                mSb.append(list.size());
                for (Poi p : list) {
                    mSb.append("\npoi= : ");
                    mSb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
//            LogUtil.e("TAG", "laughing---------------------->   " + mSb.toString());

        }

    }

    /**
     * 获取定位数据的方法，最后集中上传
     *
     * @param location 地位后获取的位置对象，从中取出想要的数据即可
     */
    private void getLocationData(BDLocation location) {
        // 获得纬度
        mLatitude = location.getLatitude();
        // 获得经度
        mLongitude = location.getLongitude();
        String str1 = location.getAddrStr();
        String str2 = str1.substring(2, str1.length());
        //获得位置
        mAddress = str2;//截取掉中国二字
//        mAddress = location.getAddrStr();

    }


    @Override
    protected void onDestroy() {
        mLocationClient.stop();        // 停止定位
        super.onDestroy();
    }


    /**
     * 选择打卡类型对话框2
     */
    public void showDialog2(AttendanceSettingRep rep) {
        View localView = LayoutInflater.from(this).inflate(
                R.layout.dialog_bottom_i_arranged, null);
        TextView tv_task_participant = (TextView) localView.findViewById(R.id.tv_task_participant);//上班打卡
        tv_task_participant.setText("上班打卡");
        TextView tv_urge_task = (TextView) localView.findViewById(R.id.tv_urge_task);//下班打卡
        tv_urge_task.setText("下班打卡");
        TextView tv_edit_task = (TextView) localView.findViewById(R.id.tv_edit_task);//
        tv_edit_task.setVisibility(View.GONE);
        ImageView line_below_edit_task = (ImageView) localView.findViewById(R.id.line_below_edit_task);//
        line_below_edit_task.setVisibility(View.GONE);//隐藏线
        TextView tv_cancel_task = (TextView) localView.findViewById(R.id.tv_cancel_task);//外勤打卡
        tv_cancel_task.setText("外勤打卡");
        TextView tv_cancel = (TextView) localView.findViewById(R.id.tv_cancel);
        dialog = new Dialog(mContext, com.global.R.style.custom_dialog);
        DateUtils.initDialog(dialog, localView, getWindowManager());
        dialog.show();
        tv_cancel.setOnClickListener(arg0 -> {
            //取消按钮
            dialog.dismiss();
        });

        tv_task_participant.setOnClickListener(v -> {
            // 上班打卡
            showNoticeDialog(mAddress, 0, rep);
            dialog.dismiss();

        });
        tv_urge_task.setOnClickListener(v -> {
            // 下班打卡
            showNoticeDialog(mAddress, 1, rep);
            dialog.dismiss();

        });
//        tv_edit_task.setOnClickListener(v -> {
//            dialog.dismiss();
//            // 编辑任务
//  showNoticeDialog(mAddress);
//
//        });

        tv_cancel_task.setOnClickListener(v -> {
            // 外勤打卡
            showNoticeDialog(mAddress, 2, rep);
            dialog.dismiss();
        });
    }
}
