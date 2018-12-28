package com.bwie.wang.wangzuoye.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.wang.wangzuoye.R;
import com.bwie.wang.wangzuoye.my.activity.InfoActivity;
import com.bwie.wang.wangzuoye.my.bean.SouBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * date:2018/12/27.
 *
 * @author 王丙均
 */

public class SousouAdapter extends RecyclerView.Adapter<SousouAdapter.ViewHolder> {
    private Context context;
    private List<SouBean.DataBean> souList;

    public SousouAdapter(Context context, List<SouBean.DataBean> souList) {
        this.context = context;
        this.souList = souList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.sou_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Uri uri = Uri.parse(souList.get(position).getImages().replace("https", "http").split("\\|")[0]);
        AbstractDraweeController fresco = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .build();
        holder.item_simple.setController(fresco);

        holder.title.setText(souList.get(position).getTitle());
        holder.price.setText("￥" + souList.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击时将pid传到商品的详情去
                Intent intent = new Intent(context, InfoActivity.class);
                int pid = souList.get(position).getPid();
                intent.putExtra("pid", pid);
                context.startActivity(intent);
                Toast.makeText(context, "sssss", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return souList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView item_simple;
        private TextView title;
        private TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            item_simple = (SimpleDraweeView) itemView.findViewById(R.id.item_simple);
            title = (TextView) itemView.findViewById(R.id.title);
             price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
