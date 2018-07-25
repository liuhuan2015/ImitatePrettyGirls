package com.liuh.imitate_prettygirls.home;

import android.os.Bundle;
import android.view.View;

import com.liuh.imitate_prettygirls.base.BaseFragment;

/**
 * Date: 2018/7/25 10:08
 * Description:
 */

public class GirlsFragment extends BaseFragment {
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    public static GirlsFragment getInstance() {
        GirlsFragment girlsFragment = new GirlsFragment();
        return girlsFragment;
    }
}
