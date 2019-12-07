package com.bwie.mvpdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bwie.mvpdemo.base.mvp.BasePresenter;
import com.bwie.mvpdemo.base.mvp.IBaseView;

/**
 * @auther: 王亚奇
 * @Date: 2019/12/06
 * @Time:下午 03:52
 * @Description:${DESCRIPTION}
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        presenter = initPresenter();
        if(presenter!= null){
            //绑定view
            presenter.attachView(this);
        }

        initView();

        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    //初始化p  让子类实现
    protected abstract P initPresenter();

    //绑定布局
    protected abstract int bindLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (presenter!= null){
            presenter.detach();
        }
    }
}
