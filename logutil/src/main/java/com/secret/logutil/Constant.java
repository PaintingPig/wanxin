package com.secret.logutil;

import android.os.Environment;

import java.io.File;

/**
 * Created by dq
 * on 2020/4/8
 */
public class Constant {
    public static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "wanxin" + File.separatorChar + "log";

}
