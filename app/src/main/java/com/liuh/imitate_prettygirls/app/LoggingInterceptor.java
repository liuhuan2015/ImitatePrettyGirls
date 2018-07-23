package com.liuh.imitate_prettygirls.app;

import com.liuh.imitate_prettygirls.util.LogUtil;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Date: 2018/7/23 20:10
 * Description:
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        LogUtil.d(String.format(Locale.ENGLISH, "Sending request %s on %s%n%s", request.url(),
                chain.connection(), request.headers()));
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        LogUtil.e(String.format(Locale.ENGLISH, "Received response from %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
