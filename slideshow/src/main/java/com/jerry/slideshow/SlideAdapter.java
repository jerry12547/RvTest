package com.jerry.slideshow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author jerry create on 2018/6/26
 * E-Mail Address：jerry12547@163.com
 * @function
 */
public class SlideAdapter extends PagerAdapter {

    private List<SlideBean> data;
    public SlideAdapter(List<SlideBean> data ){
        this.data =data;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        container.addView();
        return super.instantiateItem(container, position);
    }
}
