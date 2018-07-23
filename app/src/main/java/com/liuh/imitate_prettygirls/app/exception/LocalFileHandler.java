package com.liuh.imitate_prettygirls.app.exception;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.liuh.imitate_prettygirls.util.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * Date: 2018/7/23 19:15
 * Description:本地异常处理类
 */

public class LocalFileHandler extends BaseExceptionHandler {

    private Context context;

    public LocalFileHandler(Context context) {
        this.context = context;
    }

    @Override
    public boolean handleException(Throwable e) {
        if (e == null) {
            return false;
        }

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉，程序出现异常，正在从异常中恢复", Toast.LENGTH_SHORT).show();
                Looper.loop();
                super.run();
            }
        }.start();

        //保存错误日志
        saveLog(e);

        return true;
    }

    /**
     * 保存错误日志到本地
     *
     * @param e
     */
    private void saveLog(Throwable e) {

        try {
            File path = new File(FileUtil.getDiskCacheDir(context) + "/log");

            if (!path.exists()) {
                path.mkdirs();
            }

            File errorFile = new File(path + "/crash.txt");
            if (!errorFile.exists()) {
                errorFile.createNewFile();
            }

            OutputStream out = new FileOutputStream(errorFile, true);
            out.write(("\n\n-------错误分割线" + dateFormat.format(new Date()) + "-------\n\n")
                    .getBytes(Charset.forName("UTF-8")));

            PrintStream stream = new PrintStream(out, false, "UTF-8");
            e.printStackTrace(stream);
            stream.flush();
            out.flush();
            stream.close();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
