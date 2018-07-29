package com.liuh.imitate_prettygirls.home;

import android.util.Log;

import com.liuh.imitate_prettygirls.data.GirlsDataSource;
import com.liuh.imitate_prettygirls.data.GirlsResponsitory;
import com.liuh.imitate_prettygirls.data.bean.GirlsBean;

/**
 * Created by huan on 2018/7/29.
 */

public class GirlsPresenter implements GirlsContract.Presenter {

    private GirlsContract.View mView;
    private GirlsResponsitory mGirlsResponsitory;

    public GirlsPresenter(GirlsContract.View view) {
        mView = view;
        mGirlsResponsitory = new GirlsResponsitory();
    }

    @Override
    public void start() {
        getGirls(1, 20, true);
    }

    @Override
    public void getGirls(int page, int size, final boolean isRefresh) {
        Log.e("-------", "getGirls");
        mGirlsResponsitory.getGirls(page, size, new GirlsDataSource.LoadGirlsCallback() {
            @Override
            public void onGirlsLoaded(GirlsBean girlsBean) {
                if (isRefresh) {
                    mView.refresh(girlsBean.getResults());
                } else {
                    mView.load(girlsBean.getResults());
                }
                mView.showNormal();
            }

            @Override
            public void onDataNotAvailable() {
                if (isRefresh) {
                    mView.showError();
                }
            }
        });
    }
}
