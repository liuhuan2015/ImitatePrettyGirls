package com.liuh.imitate_prettygirls.girl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liuh.imitate_prettygirls.R;
import com.liuh.imitate_prettygirls.app.Constants;
import com.liuh.imitate_prettygirls.base.PgBaseFragment;
import com.liuh.imitate_prettygirls.data.bean.GirlsBean;
import com.liuh.imitate_prettygirls.util.BitmapUtil;
import com.liuh.imitate_prettygirls.widget.PinchImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huan on 2018/7/29.
 */
public class GirlFragment extends PgBaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.rootView)
    LinearLayout mRootView;

    private GirlAdapter mAdapter;

    private ArrayList<GirlsBean.ResultsEntity> datas;

    private int current;

    private Unbinder unbinder;

    private OnGirlChange mListener;

    public static GirlFragment newInstance(ArrayList<Parcelable> datas, int current) {
        Bundle bundle = new Bundle();
        GirlFragment fragment = new GirlFragment();
        bundle.putParcelableArrayList("girls", datas);
        bundle.putInt("current", current);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girl;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnGirlChange) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    " must implement OnHeadlineSelectedListener");
        }

    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        unbinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            datas = bundle.getParcelableArrayList("girls");
            current = bundle.getInt("current");
        }

        mAdapter = new GirlAdapter(mActivity, datas);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(current);
        mViewPager.addOnPageChangeListener(this);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getColor();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 根据图片获得主题色
     */
    private void getColor() {

        PinchImageView imageView = getCurrentImageView();
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());
        Palette.Builder builder = Palette.from(bitmap);

        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {
                Palette.Swatch vir = palette.getVibrantSwatch();
                if (vir == null) {
                    return;
                }
                mListener.change(vir.getRgb());
            }
        });
    }

    private PinchImageView getCurrentImageView() {
        View currentItem = mAdapter.getPrimaryItem();
        if (currentItem == null) {
            return null;
        }

        PinchImageView imageView = currentItem.findViewById(R.id.img);
        if (imageView == null) {
            return null;
        }

        return imageView;
    }

    public void saveGirl() {
        String imgUrl = datas.get(mViewPager.getCurrentItem()).getUrl();
        PinchImageView imageView = getCurrentImageView();
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());

        addSubscribe(Observable.just(BitmapUtil.saveBitmap(bitmap, Constants.dir,
                imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length()), true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isSuccess -> {
                    if (isSuccess) {
                        Snackbar.make(mRootView, "下载好了呦", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(mRootView, "下载出错了呦", Snackbar.LENGTH_LONG).show();
                    }
                }, RxUtils.IgnoreErrorProcessor));


    }

    public void shareGirl() {
        PinchImageView imageView = getCurrentImageView();


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnGirlChange {

        void change(int color);
    }

}
