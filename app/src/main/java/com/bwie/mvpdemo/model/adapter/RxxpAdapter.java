package com.bwie.mvpdemo.model.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.mvpdemo.MainActivity;
import com.bwie.mvpdemo.R;
import com.bwie.mvpdemo.model.entity.HomeEntity;

import java.util.List;

/**
 * @auther: 王亚奇
 * @Date: 2019/12/07
 * @Time:上午 08:44
 * @Description:${DESCRIPTION}
 */
public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.MyViewHolder> {
    private Context context;
    private List<HomeEntity.ResultBean.RxxpBean.CommodityListBean> list;

    public RxxpAdapter(Context context, List<HomeEntity.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;

    }


    //初始化 viewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rxxp_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    //绑定
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(list.get(position).getMasterPic())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .circleCrop()
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))//应哟圆角配置
                .into(holder.imageView);
        holder.textViewname.setText(list.get(position).getCommodityName());
        holder.textViewprice.setText(list.get(position).getPrice()+"");

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, list.get(position).getCommodityName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textViewname,textViewprice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_iamge);
             textViewname = itemView.findViewById(R.id.tv_name);
            textViewprice = itemView.findViewById(R.id.tv_price);
        }
    }
}
