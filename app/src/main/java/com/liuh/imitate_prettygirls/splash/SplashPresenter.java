package com.liuh.imitate_prettygirls.splash;

import com.liuh.imitate_prettygirls.data.GirlsResponsitory;

/**
 * Date: 2018/7/24 19:19
 * Description:
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;

    private GirlsResponsitory mResponsitory;

    public SplashPresenter(SplashContract.View mView) {
        this.mView = mView;
        mResponsitory = new GirlsResponsitory();
    }

    @Override
    public void start() {




    }
}
