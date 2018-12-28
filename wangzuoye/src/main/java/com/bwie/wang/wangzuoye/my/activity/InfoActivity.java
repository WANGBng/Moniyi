package com.bwie.wang.wangzuoye.my.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bwie.wang.wangzuoye.R;
import com.bwie.wang.wangzuoye.mvp.presenter.InfoPresenter;
import com.bwie.wang.wangzuoye.mvp.view.InfoView;
import com.bwie.wang.wangzuoye.my.adapter.DetailsAdapter;
import com.bwie.wang.wangzuoye.my.bean.XiangQingBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InfoActivity extends AppCompatActivity implements InfoView{

    @BindView(R.id.flyBanner)
    ViewPager flyBanner;
    @BindView(R.id.txt_subhead)
    TextView txtSubhead;
    @BindView(R.id.txt_price)
    TextView txtPrice;

    Unbinder unbinder;
    InfoPresenter infoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        unbinder = ButterKnife.bind(this);
        infoPresenter=new InfoPresenter();
        infoPresenter.attachView(this);
        infoPresenter.loadData(1);
    }
    @Override
    public void onSuccess(XiangQingBean xiangQingBean) {
        txtSubhead.setText(xiangQingBean.getData().getTitle());
        txtPrice.setText("￥"+xiangQingBean.getData().getPrice());

        String images = xiangQingBean.getData().getImages();
        String[] split = images.split("\\|");
        DetailsAdapter adapter = new DetailsAdapter(InfoActivity.this, split);
        flyBanner.setAdapter(adapter);
    }

    @Override
    public void onError(String msg) {
        Log.d("TAG", "onError: " + msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        infoPresenter.detachView();
    }
}
