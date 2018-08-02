package com.liuh.imitate_prettygirls.girl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.liuh.imitate_prettygirls.R;
import com.liuh.imitate_prettygirls.data.bean.GirlsBean;
import com.liuh.imitate_prettygirls.widget.PinchImageView;

import java.util.ArrayList;

/**
 * Date: 2018/8/2 09:29
 * Description:
 */

public class GirlAdapter extends PagerAdapter {

    private Context mContext;

    private ArrayList<GirlsBean.ResultsEntity> mDatas;

    private LayoutInflater layoutInflater;

    private View mCurrentView;

    public GirlAdapter(Context mContext, ArrayList<GirlsBean.ResultsEntity> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView = (View) object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        final String imageUrl = mDatas.get(position).getUrl();
        View view = layoutInflater.inflate(R.layout.item_girl_detail, container, false);
        PinchImageView imageView = view.findViewById(R.id.img);
        Glide.with(mContext)
                .load(imageUrl)
                .thumbnail(0.2f)
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

}
