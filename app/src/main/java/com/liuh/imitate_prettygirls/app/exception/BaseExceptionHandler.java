package com.liuh.imitate_prettygirls.app.exception;

import java.text.DateFormat;

/**
 * Date: 2018/7/23 19:15
 * Description:系统异常处理类
 */

public abstract class BaseExceptionHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";

    protected static final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);

    /**
     * 未捕获异常跳转
     *
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        //如果正确处理了未捕获异常
        if (handleException(e)) {

            try {
                //如果处理了，让程序继续运行3秒后退出，以保证错误日志的保存
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成，开发者可以根据自己的情况来自定义异常处理逻辑
     * @param e
     * @return
     */
    public abstract boolean handleException(Throwable e);
}
