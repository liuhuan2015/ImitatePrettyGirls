package com.liuh.imitate_prettygirls.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Date: 2018/7/24 09:30
 * Description:
 */

public abstract class AppActivity extends BaseActivity {

    //获取第一个Fragment
    protected abstract BaseFragment getFirstFragment();

    //获取Intent
    protected void handleIntent(Intent intent) {
    }

    protected void setTheme() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        if (getIntent() != null) {
            handleIntent(getIntent());
        }

        //避免重复添加Fragment
        if (getSupportFragmentManager().getFragments() == null
                || getSupportFragmentManager().getFragments().size() == 0) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }

        MyActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyActivityManager.getInstance().finishActivity(this);
    }
}
