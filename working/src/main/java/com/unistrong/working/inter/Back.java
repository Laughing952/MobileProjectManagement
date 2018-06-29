package com.unistrong.working.inter;

/**
 * 跳转后接收新页面返回的字符串数据
 * 作者：Laughing on 2018/5/13 17:21
 * 邮箱：719240226@qq.com
 */

public interface Back {
    /**
     * 跳转后返回的数据
     *
     * @param data
     */
    void backData(String data);
}
