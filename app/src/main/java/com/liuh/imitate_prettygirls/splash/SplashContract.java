package com.liuh.imitate_prettygirls.splash;

import com.liuh.imitate_prettygirls.base.BasePresenter;
import com.liuh.imitate_prettygirls.base.BaseView;

/**
 * Date: 2018/7/24 18:59
 * Description:
 */

public interface SplashContract {

    interface View extends BaseView<Presenter> {
        void showGirl(String girlUrl);

        void showGirl();
    }

    interface Presenter extends BasePresenter {

    }

}
