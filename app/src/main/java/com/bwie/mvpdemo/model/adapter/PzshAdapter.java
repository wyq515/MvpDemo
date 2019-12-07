package com.bwie.mvpdemo.model.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bwie.mvpdemo.MainActivity;
import com.bwie.mvpdemo.R;
import com.bwie.mvpdemo.model.entity.HomeEntity;

import java.util.List;

/**
 * @auther: 王亚奇
 * @Date: 2019/12/07
 * @Time:上午 08:46
 * @Description:${DESCRIPTION}
 */
public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.ViewHolder> {

    private Context context;
    private List<HomeEntity.ResultBean.PzshBean.CommodityListBeanX> list;

    public PzshAdapter(Context context, List<HomeEntity.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rxxp_item,null);
        ViewHolder  viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getMasterPic())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .circleCrop()
                .centerCrop()
                .into(holder.imageView);

        holder.textViewname.setText(list.get(position).getCommodityName());
        holder.textViewprice.setText(list.get(position).getPrice()+"");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textViewname,textViewprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_iamge);
            textViewname = itemView.findViewById(R.id.tv_name);
            textViewprice = itemView.findViewById(R.id.tv_price);


        }
    }
}
