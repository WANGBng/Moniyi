package com.bwie.wang.weekthree.mvp.presenter;

import com.bwie.wang.weekthree.callback.MyCallBack;
import com.bwie.wang.weekthree.mvp.model.IModelImpl;
import com.bwie.wang.weekthree.my.activity.IView;

import java.util.Map;

/**
 * date:2019/1/13.
 *
 * @author 王丙均
 */

public class IPresenterImpl implements IPresenter {
    private IModelImpl model;
    private IView iView;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        model = new IModelImpl();
    }

    @Override
    public void startRequest(String url, Map<String, String> params, Class clazz) {
        model.requestData(url, params, clazz, new MyCallBack() {
            @Override
            public void success(Object data) {
                iView.showResponseData(data);
            }

            @Override
            public void failed(Exception e) {
                iView.showResponseFail(e);
            }
        });
    }

    public void onDetach() {
        if (model != null) {
            model = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
