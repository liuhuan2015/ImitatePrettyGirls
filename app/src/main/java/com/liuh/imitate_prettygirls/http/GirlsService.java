package com.liuh.imitate_prettygirls.http;

import com.liuh.imitate_prettygirls.data.bean.GirlsBean;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Date: 2018/7/27 10:43
 * Description:接口定义
 */

public interface GirlsService {

    @GET("api/data/{type}/{count}/{page}")
    Observable<GirlsBean> getGirls(@Path("type") String type, @Path("count") int count,
                                   @Path("page") int page);
}
