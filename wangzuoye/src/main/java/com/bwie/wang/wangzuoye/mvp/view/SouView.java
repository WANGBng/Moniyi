package com.bwie.wang.wangzuoye.mvp.view;

import com.bwie.wang.wangzuoye.base.BaseView;
import com.bwie.wang.wangzuoye.my.bean.SouBean;

/**
 * date:2018/12/28.
 *
 * @author 王丙均
 */

public interface SouView extends BaseView {
    void onSuccess(SouBean souBean);
    void onFailed(String mag);
}
