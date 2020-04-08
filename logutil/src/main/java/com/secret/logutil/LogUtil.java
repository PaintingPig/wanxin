package com.secret.logutil;

import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.secret.logutil.Logger.WXFormatStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by dq
 * on 2020/4/8
 */
public class LogUtil {

    public static void init(){
        Logger.addLogAdapter(new DiskLogAdapter(WXFormatStrategy.newBuilder().build()));
    }

    public static void upLoadLogFile(String url, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        getRequestBody();
        Request.Builder builder = new Request.Builder();
        builder.url(url).post(getRequestBody());
        okHttpClient.newCall(builder.build()).enqueue(callback);
    }

    private static RequestBody getRequestBody(){
        MultipartBody.Builder builder = new MultipartBody.Builder();
        try {
            File file = new File(Constant.filePath);
            if(file.exists() && file.isDirectory()){
                File []files = file.listFiles();
                for(File tmp : files){
                    builder.addFormDataPart(tmp.getName(),file.getName(),RequestBody.create(MediaType.parse(getFileType(tmp.getName())),tmp));
                }
            }
        }catch (Exception e){
            return null;
        }
        return builder.build();
    }

    private static String getFileType(String fileName){
        if(fileName == null || !fileName.contains(".")){
            return "unKnow";
        }
        String [] tmp = fileName.split("\\.");
       return tmp[tmp.length-1];
    }
}
