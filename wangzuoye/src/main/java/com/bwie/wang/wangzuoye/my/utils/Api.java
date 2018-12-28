package com.bwie.wang.wangzuoye.my.utils;



import com.bwie.wang.wangzuoye.my.bean.SouBean;
import com.bwie.wang.wangzuoye.my.bean.XiangQingBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/26.
 * @author 王丙均
 */
public interface Api {
    @GET("searchProducts?keywords=手机&page=1")
    Observable<SouBean> sou();

    @GET("getProductDetail")
    Observable<XiangQingBean> xq(@Query("pid") int pid);
}
