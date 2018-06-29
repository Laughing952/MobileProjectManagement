package com.mycentre.bean;

/**
 * 版本信息
 * Created by Laughing on 2018/4/10.
 */

public class VersionInfo {

    /**
     * 版本编号
     */
    private String versionCode;
    /**
     * 版本名称
     */
    private String strVersionName;
    /**
     * 下载路径
     */
    private String appPath;


    public int getVersionCode() {
        return Integer.parseInt(versionCode);
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getStrVersionName() {
        return strVersionName;
    }

    public void setStrVersionName(String strVersionName) {
        this.strVersionName = strVersionName;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }
}
