package com.bwie.wang.weekthree.mvp.view;

/**
 * date:2019/1/13.
 *
 * @author 王丙均
 */

public interface IView<T> {
    void showResponseData(T data);
    void showResponseFail(T data);
}