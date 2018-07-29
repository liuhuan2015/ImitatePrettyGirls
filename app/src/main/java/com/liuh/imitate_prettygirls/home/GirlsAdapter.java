package com.liuh.imitate_prettygirls.home;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.liuh.imitate_prettygirls.data.bean.GirlsBean;

/**
 * Created by huan on 2018/7/29.
 */

public class GirlsAdapter extends RecyclerArrayAdapter<GirlsBean.ResultsEntity> {

    public OnMyItemClickListener mOnMyItemClickListener;

    public GirlsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlsViewHolder(parent);
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMyItemClickListener != null) {
                    mOnMyItemClickListener.onItemClick(position, holder);
                }
            }
        });
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, BaseViewHolder holder);
    }

    public void setmOnMyItemClickListener(OnMyItemClickListener mOnMyItemClickListener) {
        this.mOnMyItemClickListener = mOnMyItemClickListener;
    }
}
