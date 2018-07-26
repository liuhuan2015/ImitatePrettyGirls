package com.liuh.imitate_prettygirls.splash;

import com.liuh.imitate_prettygirls.app.MyApplication;
import com.liuh.imitate_prettygirls.data.GirlsDataSource;
import com.liuh.imitate_prettygirls.data.GirlsResponsitory;
import com.liuh.imitate_prettygirls.data.bean.GirlsBean;

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
        mResponsitory.getGirl(new GirlsDataSource.LoadGirlsCallback() {
            @Override
            public void onGirlsLoaded(GirlsBean girlsBean) {
                mView.showGirl(girlsBean.getResults().get(0).getUrl());
                MyApplication.currentGirl = girlsBean.getResults().get(0).getUrl();
            }

            @Override
            public void onDataNotAvailable() {
                mView.showGirl();
            }
        });
    }
}
