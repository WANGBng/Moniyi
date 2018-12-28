package com.bwie.wang.wangzuoye.mvp.presenter;

import com.bwie.wang.wangzuoye.base.BasePresenter;
import com.bwie.wang.wangzuoye.mvp.view.InfoView;
import com.bwie.wang.wangzuoye.my.bean.XiangQingBean;
import com.bwie.wang.wangzuoye.my.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/28.
 *
 * @author 王丙均
 */

public class InfoPresenter extends BasePresenter<InfoView> {

    public void loadData(int pid) {
        HttpUtils.getInsane().api.xq(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiangQingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XiangQingBean xiangQingBean) {
                        getView().onSuccess(xiangQingBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
