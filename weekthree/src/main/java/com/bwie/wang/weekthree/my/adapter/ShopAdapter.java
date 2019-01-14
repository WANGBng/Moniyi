package com.bwie.wang.weekthree.my.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.wang.weekthree.R;
import com.bwie.wang.weekthree.my.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/13.
 *
 * @author 王丙均
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder>{
    private List<ShopBean.DataBean> mList = new ArrayList<>();
    private Context mContext;

    public ShopAdapter(Context context) {
        this.mContext = context;
    }
    public void setList(List<ShopBean.DataBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //适配器的布局--复选框、商家名、RecyclerView(展示商家下的商品)
        View view = View.inflate(mContext, R.layout.shop_seller_car_adapter, null);
        MyViewHolder myViewHoler = new MyViewHolder(view);
        return myViewHoler;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {

        //设置商家的名字
        myViewHolder.mSellerName.setText(mList.get(position).getSellerName());
        //展示商品的RecyclerView的适配器
        final ProductsAdapter productsAdapter = new ProductsAdapter(mContext, mList.get(position).getList());
        //展示商品的RecyclerView的布局格式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        myViewHolder.mRecyclerView.setLayoutManager(linearLayoutManager);
        myViewHolder.mRecyclerView.setAdapter(productsAdapter);

        myViewHolder.mCheck.setChecked(mList.get(position).isCheck());

        //**************回调商品的接口************************
        //*************商品全部选中，商家选中******************
        productsAdapter.setListener(new ProductsAdapter.ShopCallBackListener() {
            @Override
            public void callBack() {
                //从商品适配里回调回来，回给activity，activity计算价格和数量
                if(mShopCallBackListener != null) {
                    mShopCallBackListener.callBack(mList);
                }
                //*****1.商品集合
                List<ShopBean.DataBean.ListBean> listBeans = mList.get(position).getList();
                //*****2.创建一个临时的标志位，用来记录现在点击的状态
                boolean isAllChecked = true;
                //*****3.遍历所有的商品
                for (ShopBean.DataBean.ListBean bean : listBeans) {
                    if (!bean.isCheck()) {
                        //只要有一个商品未选中，标志位设置成false，并且跳出循环
                        isAllChecked = false;
                        break;
                    }
                }

                //*****4.刷新商家的状态
                myViewHolder.mCheck.setChecked(isAllChecked);
                //商品的选中状态
                mList.get(position).setCheck(isAllChecked);
            }
        });
        //*************商家选中，商家下的商品全部选中****************

        //监听checkBox的点击事件
        //目的是改变旗下所有商品的选中状态
        myViewHolder.mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先改变自己的标志位
                mList.get(position).setCheck(myViewHolder.mCheck.isChecked());
                //调用产品adapter的方法，用来全选和反选
                productsAdapter.selectOrRemoveAll(myViewHolder.mCheck.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        RecyclerView mRecyclerView;
        TextView mSellerName;
        CheckBox mCheck;

        public MyViewHolder(View itemView) {
            super(itemView);
            mSellerName = (TextView) itemView.findViewById(R.id.tv_shop);
            mCheck = itemView.findViewById(R.id.check_shop);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_shop);

        }
    }
    /**
     * *********定义接口，在Activity****************
     */
    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack(List<ShopBean.DataBean> list);
    }
}
