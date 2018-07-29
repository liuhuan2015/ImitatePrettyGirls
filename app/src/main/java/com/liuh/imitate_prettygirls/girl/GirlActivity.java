package com.liuh.imitate_prettygirls.girl;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.liuh.imitate_prettygirls.R;
import com.liuh.imitate_prettygirls.base.AppActivity;
import com.liuh.imitate_prettygirls.base.BaseFragment;
import com.liuh.imitate_prettygirls.home.GirlsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GirlActivity extends AppActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    GirlFragment mGirlFragment;

    @Override
    protected BaseFragment getFirstFragment() {

        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_girl;
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
        mToolbar.setTitle(R.string.meizhi);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_girl, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
            mGirlFragment.shareGirl();
            return true;
        } else if (id == R.id.action_save) {
            mGirlFragment.saveGirl();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishActivity();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void finishActivity() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

}
