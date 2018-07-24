package com.liuh.imitate_prettygirls.app;

import com.liuh.imitate_prettygirls.base.BaseFragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Date: 2018/7/24 18:40
 * Description:
 */

public abstract class PgBaseFragment extends BaseFragment {

    private CompositeSubscription mCompositeSubscription = null;

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
            //recreate mCompositeSubscription
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void unSubscribeAll() {
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
        mCompositeSubscription = null;
    }

    protected void unSubscribe(Subscription subscription) {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.remove(subscription);
        }
    }

    @Override
    public void onDetach() {
        unSubscribeAll();
        super.onDetach();
    }
}
