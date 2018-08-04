package com.liuh.imitate_prettygirls.app;

import com.liuh.imitate_prettygirls.util.FileUtil;

/**
 * Created by huan on 2018/8/4.
 * 保存项目中用到的常量
 */
public final class Constants {

    private Constants() {
    }

    public static final String dir = FileUtil.getDiskCacheDir(MyApplication.getInstance())
            + "/girls";
}
