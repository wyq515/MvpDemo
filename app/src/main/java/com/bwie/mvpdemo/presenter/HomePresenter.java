package com.bwie.mvpdemo.presenter;

import com.bwie.mvpdemo.base.mvp.BasePresenter;
import com.bwie.mvpdemo.contract.IHomeContract;
import com.bwie.mvpdemo.model.HomeModel;

/**
 * @auther: 王亚奇
 * @Date: 2019/12/06
 * @Time:下午 04:56
 * @Description:${DESCRIPTION}
 */
public class HomePresenter extends BasePresenter<HomeModel, IHomeContract.IView> implements IHomeContract.IPresenter{
    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }

    @Override
    public void getHome() {
        model.getHome(new IHomeContract.IMdel.ModelCallback() {
            @Override
            public void success(Object data) {
                getView().success(data);
            }

            @Override
            public void error(Throwable throwable) {
                getView().error(throwable);
            }
        });
    }

    @Override
    public void getBanner() {
        model.getBanner(new IHomeContract.IMdel.ModelCallback() {
            @Override
            public void success(Object data) {
                getView().success(data);
            }

            @Override
            public void error(Throwable throwable) {
                getView().error(throwable);
            }
        });
    }
}
