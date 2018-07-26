package com.liuh.intentservice_review;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Date: 2018/7/26 09:03
 * Description:
 */

public class MyIntentService extends IntentService {

    public static final String DOWNLOAD_URL = "download_url";
    public static final String INDEX_FLAG = "index_flag";
    public static UpdateUI updateUI;

    public static void setUpdateUI(UpdateUI updateUI) {
        MyIntentService.updateUI = updateUI;
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * 实现异步任务的方法
     *
     * @param intent Activity传递过来的Intent，数据封装在intent中
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("-------", "Thread.currentThread().getName(): " + Thread.currentThread().getName());

        //在子线程中进行网络请求
        Bitmap bitmap = downloadUrlBitmap(intent.getStringExtra(DOWNLOAD_URL));
        Message msg1 = new Message();
        msg1.what = intent.getIntExtra(INDEX_FLAG, 0);
        msg1.obj = bitmap;

        //通知主线程去更新UI
        if (updateUI != null) {
            updateUI.updateUI(msg1);
        }

        Log.e("-------", "onHandleIntent");
    }

    //------------------------------重写以下方法仅为测试--------------------------------------------

    @Override
    public void onCreate() {
        Log.e("-------", "onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        Log.e("-------", "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e("-------", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("-------", "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("-------", "onBind");
        return super.onBind(intent);
    }

    public interface UpdateUI {
        void updateUI(Message message);
    }


    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


}
