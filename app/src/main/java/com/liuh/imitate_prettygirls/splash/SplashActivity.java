package com.liuh.imitate_prettygirls.splash;

import com.liuh.imitate_prettygirls.R;
import com.liuh.imitate_prettygirls.base.AppActivity;
import com.liuh.imitate_prettygirls.base.BaseFragment;

public class SplashActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return SplashFragment.getInstance();
    }

    @Override
    protected void setTheme() {
        setTheme(R.style.AppTheme_Splash);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.splash_fragment;
    }
}
