package com.liuh.imitate_prettygirls.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by huan on 2018/7/29.
 */

public abstract class PgBaseFragment extends BaseFragment {

    private CompositeSubscription mCompositeSubscription = null;

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
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
