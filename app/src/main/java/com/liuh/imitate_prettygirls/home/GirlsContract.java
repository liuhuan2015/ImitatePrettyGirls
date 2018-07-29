package com.liuh.imitate_prettygirls.home;

import com.liuh.imitate_prettygirls.base.BasePresenter;
import com.liuh.imitate_prettygirls.base.BaseView;
import com.liuh.imitate_prettygirls.data.bean.GirlsBean;

import java.util.List;

/**
 * Created by huan on 2018/7/29.
 */

public interface GirlsContract {

    interface View extends BaseView {

        void refresh(List<GirlsBean.ResultsEntity> datas);

        void load(List<GirlsBean.ResultsEntity> datas);

        void showError();

        void showNormal();
    }

    interface Presenter extends BasePresenter {
        void getGirls(int page, int size, boolean isRefresh);
    }

}
