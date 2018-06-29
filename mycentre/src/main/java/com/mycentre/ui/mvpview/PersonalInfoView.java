package com.mycentre.ui.mvpview;

import com.global.viewmodel.MyCenterSexViewModel;
import com.mycentre.response.ImageRep;
import com.mycentre.ui.viewmodel.PersonInfoVM;

/**
 * 个人资料
 * 作者：Laughing on 2018/5/2 14:29
 * 邮箱：719240226@qq.com
 */

public interface PersonalInfoView<T> {
    /**
     * 设置 性别
     *
     * @param model
     */
    void setSex2View(MyCenterSexViewModel model);

    /**
     * 保存个人资料成功
     */
    void savePersonalInfoSuccess();

    /**
     * 获取PersonInfoVM 对象
     *
     * @return PersonInfoVM 对象
     */
    PersonInfoVM getPersonInfoVM();
    /**
     * 选择头像
     */
    void chooseHeadImage();

    /**
     * 返回个人资料信息
     *
     * @param data
     */
    void showPersonInfo(T data);

    /**
     * 图像选择后返回结果
     */
    void resultImageUrl(ImageRep imageRep);
}
