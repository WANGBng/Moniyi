package com.bwie.wang.weekthree.callback;

/**
 * date:2019/1/13.
 *
 * @author 王丙均
 */

public interface MyCallBack<T> {
    void success(T data);
    void failed(Exception e);
}
