package com.liuh.imitate_prettygirls.data;

import com.liuh.imitate_prettygirls.data.source.local.LocalGirlsDataSource;
import com.liuh.imitate_prettygirls.data.source.remote.RemoteGirlsDataSource;

/**
 * Date: 2018/7/24 19:32
 * Description:
 */

public class GirlsResponsitory implements GirlsDataSource {

    private LocalGirlsDataSource mLocalGirlsDataSource;
    private RemoteGirlsDataSource mRemoteGirlsDataSource;

    public GirlsResponsitory() {
        this.mLocalGirlsDataSource = new LocalGirlsDataSource();
        this.mRemoteGirlsDataSource = new RemoteGirlsDataSource();
    }

    @Override
    public void getGirls(int page, int size, LoadGirlsCallback callback) {
        mRemoteGirlsDataSource.getGirls(page, size, callback);
    }

    @Override
    public void getGirl(LoadGirlsCallback callback) {
        mRemoteGirlsDataSource.getGirl(callback);
    }
}
