package com.liuh.imitate_prettygirls.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.liuh.imitate_prettygirls.R;
import com.liuh.imitate_prettygirls.base.AppActivity;
import com.liuh.imitate_prettygirls.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private long exitTime = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.girls_fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
    }

    @OnClick({R.id.fab})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                //必须明确使用mailto前缀来修饰邮件地址
                Uri uri = Uri.parse("mailto:liuh_wk@163.com");
                Intent intent = new Intent(Intent.ACTION_SEND, uri);
                startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
                break;
        }
    }


    @Override
    protected BaseFragment getFirstFragment() {
        return GirlsFragment.getInstance();
    }


}
