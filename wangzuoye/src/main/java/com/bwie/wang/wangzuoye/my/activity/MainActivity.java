package com.bwie.wang.wangzuoye.my.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.wang.wangzuoye.R;
import com.bwie.wang.wangzuoye.mvp.presenter.SouPresenter;
import com.bwie.wang.wangzuoye.mvp.view.SouView;
import com.bwie.wang.wangzuoye.my.adapter.SousouAdapter;
import com.bwie.wang.wangzuoye.my.bean.SouBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements SouView {
    @BindView(R.id.rec_sou)
    RecyclerView recSou;
    SouPresenter souPresenter;
    SousouAdapter sousouAdapter;
    boolean b=false;//实现通过判断切换

    Unbinder unbinder;

    @BindView(R.id.tab_lay)
    TabLayout mTabLayout;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.qiehuan)
    ImageView qiehuan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        souPresenter = new SouPresenter();
        souPresenter.attachView(this);
        souPresenter.load();

        mTabLayout.addTab(mTabLayout.newTab().setText("综合"));
        mTabLayout.addTab(mTabLayout.newTab().setText("销量"));
        mTabLayout.addTab(mTabLayout.newTab().setText("价格"));
        mTabLayout.addTab(mTabLayout.newTab().setText("筛选"));
        mTabLayout.getTabAt(1).select();


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        souPresenter.detachView();
    }


    @Override
    public void onSuccess(SouBean souBean) {
        final List<SouBean.DataBean> list = souBean.getData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recSou.setLayoutManager(layoutManager);
        sousouAdapter = new SousouAdapter(this, list);
        recSou.setAdapter(sousouAdapter);
    }

    @Override
    public void onFailed(String mag) {

    }

    @OnClick({R.id.back, R.id.qiehuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Toast.makeText(MainActivity.this,"你点我会返回的",Toast.LENGTH_SHORT).show();

                break;
            case R.id.qiehuan:

                if (b==false) {
                    //点击后想要变成什么要的布局样式就搞一个你的需求
                    recSou.setLayoutManager(new GridLayoutManager(this,2));
                    //给布尔值重新赋值
                    b = true;
                    //给点击按钮的图片重新赋值
//                    cIv.setImageResource(R.mipmap.ic_linear);
                }else if (b==true) {
                    recSou.setLayoutManager(new LinearLayoutManager(this));
                    //给布尔值重新赋值
                    b = false;
                    //给点击按钮的图片重新赋值
//                    cIv.setImageResource(R.mipmap.ic_grid);
                }
                break;
            default:
                    break;
        }
    }
}
