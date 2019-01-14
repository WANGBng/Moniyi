package com.bwie.wang.weekthree.mvp.model;

import com.bwie.wang.weekthree.callback.MyCallBack;

import java.util.Map;

/**
 * date:2019/1/13.
 *
 * @author 王丙均
 */

public interface IModel {
    void requestData(String url, Map<String, String> params, Class clazz, MyCallBack callBack);

}
