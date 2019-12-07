package com.bwie.mvpdemo.contract;

import com.bwie.mvpdemo.base.mvp.IBaseModel;
import com.bwie.mvpdemo.base.mvp.IBaseView;

/**
 * @auther: 王亚奇
 * @Date: 2019/12/06
 * @Time:下午 03:37
 * @Description:${DESCRIPTION}   契约类
 */
public interface IHomeContract {

    interface  IMdel extends IBaseModel{
        void getHome(ModelCallback modelCallback);
        void getBanner(ModelCallback modelCallback);

        interface ModelCallback{
            void success(Object data);
            void error(Throwable throwable);
        }


    }

    interface IView extends IBaseView{
        void success (Object data);
        void error(Throwable throwable);
    }

    interface  IPresenter{
        void getHome(); //获取首页数据
        void getBanner(); //获取轮播
    }


}
