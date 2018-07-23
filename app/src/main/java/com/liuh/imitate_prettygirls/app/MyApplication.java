package com.liuh.imitate_prettygirls.app;

import android.app.Application;

import com.liuh.imitate_prettygirls.app.exception.LocalFileHandler;
import com.liuh.imitate_prettygirls.util.LogUtil;
import com.liuh.imitate_prettygirls.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Date: 2018/7/23 19:00
 * Description:
 */

public class MyApplication extends Application {

    private static MyApplication mApplication;

    public static String currentGirl = "http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg";

    @Override
    public void onCreate() {
        super.onCreate();

        InitializeService.start(this);

        mApplication = this;

        //配置是否显示Log
        LogUtil.isDebug = true;

        //配置是否显示Toast
        ToastUtil.isShow = true;

        //配置程序异常退出处理
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));
    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
        return client;
    }

    public static MyApplication getInstance() {
        return mApplication;
    }

}
