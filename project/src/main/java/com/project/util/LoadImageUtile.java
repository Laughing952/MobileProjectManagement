package com.project.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.project.R;
import com.waterbase.utile.GlideUtile;

/**
 * Created by Water on 2018/4/9.
 */

public class LoadImageUtile {

    @BindingAdapter({"res"})
    public static void LocalImageLoad(ImageView imageView, int res) {
        imageView.setImageResource(res);
    }

    /**
     * 加载网络图片(圆形)
     *
     * @param imageView
     * @param url
     */

    public static void loadImage(ImageView imageView, String url) {
        GlideUtile.loadCircleImage(imageView, url, R.mipmap.ic_head_normal, R.mipmap.ic_head_normal);
    }

    /**
     * 加载网络图片（方形）
     *
     * @param imageView
     * @param url
     */
    @BindingAdapter({"projecturl"})
    public static void loadSquareImage(ImageView imageView, String url) {
        GlideUtile.loadImage(imageView, url, R.mipmap.ic_camera, R.mipmap.ic_camera);
    }
}
