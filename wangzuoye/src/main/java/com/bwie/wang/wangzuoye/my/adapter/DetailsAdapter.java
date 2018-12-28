package com.bwie.wang.wangzuoye.my.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.wang.wangzuoye.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * date:2018/12/28.
 *
 * @author 王丙均
 */

public class DetailsAdapter extends PagerAdapter {
    private Context context;
    private String[] images;

    public DetailsAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = View.inflate(context, R.layout.detailsadapter_item, null);
        SimpleDraweeView simpleDraweeView = view.findViewById(R.id.img_glide);

        Glide.with(context).load(images[position % images.length]).into(simpleDraweeView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
