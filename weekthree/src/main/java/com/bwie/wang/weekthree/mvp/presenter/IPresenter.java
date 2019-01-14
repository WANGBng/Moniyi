package com.bwie.wang.weekthree.mvp.presenter;

import java.util.Map;

/**
 * date:2019/1/13.
 *
 * @author 王丙均
 */

public interface IPresenter {
    void startRequest(String url, Map<String, String> params, Class clazz);

}
