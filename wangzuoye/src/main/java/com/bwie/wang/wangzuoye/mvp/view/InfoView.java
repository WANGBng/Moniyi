package com.bwie.wang.wangzuoye.mvp.view;

import com.bwie.wang.wangzuoye.base.BaseView;
import com.bwie.wang.wangzuoye.my.bean.XiangQingBean;

/**
 * date:2018/12/28.
 * @author 王丙均
 */

public interface InfoView extends BaseView {
    void onSuccess(XiangQingBean xiangQingBean);
    void onError(String msg);
}
