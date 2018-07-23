package com.liuh.imitate_prettygirls.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Date: 2018/7/23 19:03
 * Description:这个服务用来做一些异步的初始化，以缩短app冷启动时间
 */

public class InitializeService extends IntentService {

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        context.startActivity(intent);
    }

    public InitializeService() {
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            //todo delay init

        }
    }
}
