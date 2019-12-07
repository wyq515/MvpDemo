package com.bwie.mvpdemo.base.mvp;

import java.lang.ref.WeakReference;

/**
 * @auther: 王亚奇
 * @Date: 2019/12/06
 * @Time:下午 03:26
 * @Description:${DESCRIPTION}
 */
public abstract class BasePresenter <M extends IBaseModel,V extends IBaseView>{

    public M model;
    public WeakReference<V> weakReference;
    
    public BasePresenter(){
        model = initModel();
    }


    protected abstract M initModel();

    /**
     * 不通过构造传参  通过单独的绑定的方法进行传递
     * @param v
     */
    public void attachView(V v){
        weakReference = new WeakReference<>(v);
    }

    /**
     * 销毁 view 释放资源 解绑
     */

    public void detach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    public  V getView(){
        return weakReference.get();  //得到包装的对象
    }

}
