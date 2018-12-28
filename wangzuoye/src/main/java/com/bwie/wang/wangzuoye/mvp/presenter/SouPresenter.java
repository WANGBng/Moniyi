package com.bwie.wang.wangzuoye.mvp.presenter;

import com.bwie.wang.wangzuoye.base.BasePresenter;
import com.bwie.wang.wangzuoye.mvp.view.SouView;
import com.bwie.wang.wangzuoye.my.bean.SouBean;
import com.bwie.wang.wangzuoye.my.utils.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/28.
 *
 * @author 王丙均
 */

public class SouPresenter extends BasePresenter<SouView> {

    public void load(){
        Observable<SouBean> sou = HttpUtils.getInsane().api.sou();

        sou.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SouBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(SouBean souBean) {
                        getView().onSuccess(souBean);
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
