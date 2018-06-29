package com.waterbase.global;

import static android.R.attr.key;

/**
 * © 2012 amsoft.cn
 * 名称：AbAppConfig.java 
 * 描述：初始设置类.
 *
 * @author LiuQi
 */
public class AppConfig {
	/**  UI设计的基准宽度. */
	public static int UI_WIDTH = 1242;
	
	/**  UI设计的基准高度. */
	public static int UI_HEIGHT = 2208;
	
	/**  UI设计的密度. */
	public static int UI_DENSITY = 1;
	
	/** 默认 SharePreferences文件名. */
	public static String SHARED_PATH = "app_share";
	
	/** 默认下载文件地址. */
	public static  String DOWNLOAD_ROOT_DIR = "download";
	
    /** 默认下载图片文件地址. */
	public static  String DOWNLOAD_IMAGE_DIR = "images";
	
    /** 默认下载文件地址. */
	public static  String DOWNLOAD_FILE_DIR = "files";
	
	/** APP缓存目录. */
	public static  String CACHE_DIR = "cache";
	
	/** DB目录. */
	public static  String DB_DIR = "nofubao_db";
	
	/** 默认磁盘缓存超时时间设置，毫秒. */
	public static long DISK_CACHE_EXPIRES_TIME = 60*1000*1000;
	
	/** 内存缓存大小  单位10M. */
	public static int MAX_CACHE_SIZE_INBYTES = 10*1024*1024;
	
	/** 磁盘缓存大小  单位10M. */
	public static int MAX_DISK_USAGE_INBYTES = 10*1024*1024;
	
	public static final String KEY = "ha6appl9h0o82hnh";

	public static final String PHONE = "4000616899"; // 客服电话
}
