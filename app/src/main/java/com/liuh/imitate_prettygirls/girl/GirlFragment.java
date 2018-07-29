package com.liuh.imitate_prettygirls.girl;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liuh.imitate_prettygirls.R;
import com.liuh.imitate_prettygirls.base.PgBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huan on 2018/7/29.
 */

public class GirlFragment extends PgBaseFragment {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.rootView)
    LinearLayout mRootView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girl;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }


}
