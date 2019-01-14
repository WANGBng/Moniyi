package com.bwie.wang.weekthree.mvp.model;

import com.bwie.wang.weekthree.callback.MyCallBack;
import com.bwie.wang.weekthree.my.ok.ICallBack;
import com.bwie.wang.weekthree.my.ok.OkHttpUtils;

import java.util.Map;

/**
 * date:2019/1/13.
 *
 * @author 王丙均
 */

public class IModelImpl implements IModel {
    @Override
    public void requestData(String url, Map<String, String> params, Class clazz, final MyCallBack callBack) {

        OkHttpUtils.getInstance().postEnqueue(url, params, clazz, new ICallBack() {
            @Override
            public void success(Object obj) {
                callBack.success(obj);
            }

            @Override
            public void failed(Exception e) {
                callBack.failed(e);
            }
        });
    }
}
