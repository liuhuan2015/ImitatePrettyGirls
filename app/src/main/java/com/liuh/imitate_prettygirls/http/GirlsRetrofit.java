package com.liuh.imitate_prettygirls.http;

import com.liuh.imitate_prettygirls.BuildConfig;
import com.liuh.imitate_prettygirls.app.MyApplication;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date: 2018/7/27 10:46
 * Description:
 */

public class GirlsRetrofit {

    private volatile static Retrofit retrofit;

    public GirlsRetrofit() {
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (GirlsRetrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BuildConfig.API_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(MyApplication.defaultOkHttpClient())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
