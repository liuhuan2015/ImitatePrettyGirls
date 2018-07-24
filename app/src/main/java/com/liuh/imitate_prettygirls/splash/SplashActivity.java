package com.liuh.imitate_prettygirls.splash;

import com.liuh.imitate_prettygirls.base.AppActivity;
import com.liuh.imitate_prettygirls.base.BaseFragment;

public class SplashActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return SplashFragment.getInstance();
    }
}
