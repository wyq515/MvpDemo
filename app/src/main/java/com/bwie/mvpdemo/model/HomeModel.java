package com.bwie.mvpdemo.model;

import com.bwie.mvpdemo.api.Api;
import com.bwie.mvpdemo.contract.IHomeContract;
import com.bwie.mvpdemo.model.entity.BannerEntity;
import com.bwie.mvpdemo.model.entity.HomeEntity;
import com.bwie.mvpdemo.utils.VolleyUtil;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * @auther: 王亚奇
 * @Date: 2019/12/06
 * @Time:下午 04:04
 * @Description:${DESCRIPTION}
 */
public class HomeModel  implements IHomeContract.IMdel {
    @Override
    public void getHome(final ModelCallback modelCallback) {
        VolleyUtil.getInstance().doGet(new HashMap<String, String>(), Api.HOME_URL, new VolleyUtil.VolleyCallback() {
            @Override
            public void success(String respose) {
                HomeEntity homeEntity = new  Gson().fromJson(respose,HomeEntity.class);
                modelCallback.success(homeEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                modelCallback.error(throwable);
            }
        });
    }

    //获取轮播图
    @Override
    public void getBanner(final ModelCallback modelCallback) {
       VolleyUtil.getInstance().doGet(new HashMap<String, String>(), Api.BANNER_URL, new VolleyUtil.VolleyCallback() {
           @Override
           public void success(String respose) {
               BannerEntity  bannerEntity = new Gson().fromJson(respose,BannerEntity.class);
               modelCallback.success(bannerEntity);
           }

           @Override
           public void failure(Throwable throwable) {
                modelCallback.error(throwable);
           }
       });
    }
}
