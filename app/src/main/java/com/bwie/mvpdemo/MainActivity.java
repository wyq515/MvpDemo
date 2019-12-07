package com.bwie.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.mvpdemo.base.BaseActivity;
import com.bwie.mvpdemo.contract.IHomeContract;
import com.bwie.mvpdemo.model.adapter.MlssAdapter;
import com.bwie.mvpdemo.model.adapter.PzshAdapter;
import com.bwie.mvpdemo.model.adapter.RxxpAdapter;
import com.bwie.mvpdemo.model.entity.BannerEntity;
import com.bwie.mvpdemo.model.entity.HomeEntity;
import com.bwie.mvpdemo.presenter.HomePresenter;
import com.stx.xhb.androidx.XBanner;

import java.util.List;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {


    private RecyclerView rxxp;
    private RecyclerView mlsh;
    private RecyclerView pzsh;
    private XBanner xBanner;

    @Override
    protected void initData() {
        presenter.getHome(); //获取收入数据
        presenter.getBanner(); // 获取轮播数据
    }

    @Override
    protected void initView() {
        rxxp = findViewById(R.id.rv_rxxp);
        mlsh = findViewById(R.id.rv_mlsh);
        pzsh = findViewById(R.id.rv_pzsh);
        xBanner = findViewById(R.id.xbanner);

        //布局管理器
        rxxp.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        //垂直列表
        mlsh.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        //Grid  网格布局
        pzsh.setLayoutManager(new GridLayoutManager(this,2));

    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(Object data) {
        if(data!= null){
            if(data instanceof HomeEntity){ //首页;列表
                Toast.makeText(this, "xxx"+((HomeEntity) data).getResult().getRxxp().getName(), Toast.LENGTH_SHORT).show();
                //热销新品
                RxxpAdapter rxxpAdapter = new RxxpAdapter(this,((HomeEntity) data).getResult().getRxxp().getCommodityList());
                rxxp.setAdapter(rxxpAdapter);
                //魔力时尚
                MlssAdapter mlssAdapter = new MlssAdapter(this,((HomeEntity) data).getResult().getMlss().getCommodityList());
                mlsh.setAdapter(mlssAdapter);

                //品质生活
                PzshAdapter pzshAdapter = new PzshAdapter(this,((HomeEntity) data).getResult().getPzsh().getCommodityList());
                pzsh.setAdapter(pzshAdapter);


            }else if (data instanceof BannerEntity){
                //轮播图
                final List<BannerEntity.Banner> result = ((BannerEntity) data).result;
                xBanner.setBannerData(result);
                xBanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(MainActivity.this).load(result.get(position).imageUrl).into((ImageView) view);
                    }
                });
            }
        }
    }

    @Override
    public void error(Throwable throwable) {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
