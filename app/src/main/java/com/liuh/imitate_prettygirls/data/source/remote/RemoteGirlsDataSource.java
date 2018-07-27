package com.liuh.imitate_prettygirls.data.source.remote;

import com.liuh.imitate_prettygirls.data.GirlsDataSource;
import com.liuh.imitate_prettygirls.data.bean.GirlsBean;
import com.liuh.imitate_prettygirls.http.GirlsRetrofit;
import com.liuh.imitate_prettygirls.http.GirlsService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Date: 2018/7/24 19:54
 * Description:
 */

public class RemoteGirlsDataSource implements GirlsDataSource {
    @Override
    public void getGirls(int page, int size, final LoadGirlsCallback callback) {
        GirlsRetrofit.getRetrofit()
                .create(GirlsService.class)
                .getGirls("福利", size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(GirlsBean girlsBean) {
                        callback.onGirlsLoaded(girlsBean);
                    }
                })
        ;

    }

    @Override
    public void getGirl(LoadGirlsCallback callback) {
        getGirls(1, 1, callback);
    }
}
