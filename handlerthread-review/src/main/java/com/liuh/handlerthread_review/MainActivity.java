package com.liuh.handlerthread_review;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

/**
 * 实现效果：每隔5秒请求一次服务器数据，然后更新UI。
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView tv_content;

    private HandlerThread mCheckMsgThread;

    private Handler mCheckMsgHandler;//使用HandlerThread的Looper构建一个消息循环系统

    private boolean isUpdateInfo;//是否开始请求后台，进行数据更新

    private static final int MSG_UPDATE_INFO = 0x110;

    //与UI线程相关的handler
    private Handler mHandler = new Handler();//使用主线程的Looper的构建一个消息循环系统

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_content = findViewById(R.id.tv_content);

        //创建后台线程
        initBackThread();
    }

    private void initBackThread() {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();

        //使用HandlerThread的Looper构建一个消息循环系统
        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                checkForUpdate();

                if (isUpdateInfo) {
                    //隔一秒发送一个空消息，促使这个消息循环系统一直循环下去，只要控制值 isUpdateInfo 不为 false .
                    mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 5000);
                }

            }
        };
    }

    //模拟从服务器请求数据，解析数据
    private void checkForUpdate() {

        try {
            //模拟耗时
            Thread.sleep(1000);

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.e("-------", "实时更新数据...");
                    String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
                    result = String.format(result, (int) (Math.random() * 3000 + 1000));
                    tv_content.setText(Html.fromHtml(result));
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止查询
        isUpdateInfo = false;
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mCheckMsgThread.quit();
    }

}
