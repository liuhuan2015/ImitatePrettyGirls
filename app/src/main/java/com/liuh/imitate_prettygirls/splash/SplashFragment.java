package com.liuh.imitate_prettygirls.splash;

import android.os.Bundle;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.liuh.imitate_prettygirls.R;
import com.liuh.imitate_prettygirls.app.PgBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Date: 2018/7/24 18:38
 * Description:
 */

public class SplashFragment extends PgBaseFragment implements SplashContract.View {

    @BindView(R.id.splash)
    ImageView mSplashImg;

    private ScaleAnimation scaleAnimation;

    private SplashPresenter mPresenter;

    private Unbinder unbinder;


    public static SplashFragment getInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);




    }

    @Override
    public void showGirl(String girlUrl) {

    }

    @Override
    public void showGirl() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }


}
