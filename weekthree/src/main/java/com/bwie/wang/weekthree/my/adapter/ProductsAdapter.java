package com.bwie.wang.weekthree.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.wang.weekthree.R;
import com.bwie.wang.weekthree.my.activity.CustomCounterView;
import com.bwie.wang.weekthree.my.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/13.
 *
 * @author 王丙均
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>{
    private Context mContext;
    private List<ShopBean.DataBean.ListBean> mList = new ArrayList<>();

    public ProductsAdapter(Context mContext, List<ShopBean.DataBean.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //              商品的展示视图：复选框、标题、价格、自定义View(加减号、数量)
        View view = View.inflate(mContext, R.layout.shop_car_adapter, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        //商品的图片
        String url = mList.get(position).getImages().split("\\|")[0].replace("https", "http");
        Glide.with(mContext).load(url).into(myViewHolder.mImage);
        //商品的标题
        myViewHolder.mTitle.setText(mList.get(position).getTitle());
        //商品的价钱
        myViewHolder.mPrice.setText(mList.get(position).getPrice() + "");
        //商品的复选框
        //根据我记录的状态，改变勾选
        myViewHolder.mCheckBox.setChecked(mList.get(position).isCheck());
        /**
         * ***************商品的复选框状态监听****************
         *
         */
        //商品的跟商家的有所不同，商品添加了选中改变的监听
        myViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //****1.优先改变自己的状态 (运行一)
                mList.get(position).setCheck(isChecked);
                //回调，目的是告诉activity，有人选中状态被改变
                if (mShopCallBackListener != null) {
                    mShopCallBackListener.callBack();
                }
            }
        });

        //------------------------------------------------------------------
        //设置自定义View里的Edit
        myViewHolder.mCustomShopCarPrice.setData(this, mList, position);
        myViewHolder.mCustomShopCarPrice.setOnCallBack(new CustomCounterView.CallBackListener() {
            @Override
            public void callBack() {
                if (mShopCallBackListener != null) {
                    mShopCallBackListener.callBack();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CustomCounterView mCustomShopCarPrice;
        TextView mTitle, mPrice;
        ImageView mImage;
        CheckBox mCheckBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.iv_product);
            mTitle = (TextView) itemView.findViewById(R.id.tv_product_title);
            mPrice = (TextView) itemView.findViewById(R.id.tv_product_price);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.check_product);
            mCustomShopCarPrice = (CustomCounterView) itemView.findViewById(R.id.custom_product_counter);
        }
    }

    /**
     * ****************商品里定义个方法：**************************
     * *****************商家的所有商品状态改变**********************
     * 在我们子商品的adapter中，修改子商品的全选和反选
     * @param isSelectAll
     */
    public void selectOrRemoveAll(boolean isSelectAll) {
        //循环商品
        for (ShopBean.DataBean.ListBean listBean : mList) {
            listBean.setCheck(isSelectAll);
        }
        //刷新
        notifyDataSetChanged();
    }

    /**
     * ***************定义接口**************************
     * ***************起过渡作用*************************
     */
    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack();
    }
}
