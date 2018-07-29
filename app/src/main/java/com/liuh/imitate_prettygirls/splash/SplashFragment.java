package com.liuh.imitate_prettygirls.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.liuh.imitate_prettygirls.R;
import com.liuh.imitate_prettygirls.base.MyActivityManager;
import com.liuh.imitate_prettygirls.base.PgBaseFragment;
import com.liuh.imitate_prettygirls.home.HomeActivity;

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

    private GlideDrawableImageViewTarget mImageViewTarget;

    public static SplashFragment getInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        mPresenter = new SplashPresenter(SplashFragment.this);

        initAnim();

        mImageViewTarget = new GlideDrawableImageViewTarget(mSplashImg) {

            @Override
            public void onResourceReady(GlideDrawable resource,
                                        GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                mSplashImg.startAnimation(scaleAnimation);
            }
        };

    }

    private void initAnim() {
        scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2500);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                MyActivityManager.getInstance().finishActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void showGirl(String girlUrl) {
        Glide.with(getActivity())
                .load(girlUrl)
                .into(mImageViewTarget);
    }

    @Override
    public void showGirl() {
        Glide.with(getActivity())
                .load(R.drawable.welcome)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageViewTarget);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
