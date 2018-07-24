package com.liuh.imitate_prettygirls.data;

import com.liuh.imitate_prettygirls.data.bean.GirlsBean;

/**
 * Date: 2018/7/24 19:32
 * Description:
 */

public interface GirlsDataSource {

    interface LoadGirlsCallback {

        void onGirlsLoaded(GirlsBean girlsBean);

        void onDataNotAvailable();
    }

    void getGirls(int page, int size, LoadGirlsCallback callback);

    void getGirl(LoadGirlsCallback callback);

}
