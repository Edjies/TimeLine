package com.edjies.timeline.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author  hubble
 */

public class UFile {
    public final static String EXTERNAL_DIR = "kyxedu";
    // 创建内部文件
    public static File createInternalFile(Context context, String name) {
            return new File(context.getFilesDir(), name);
    }

    // 创建外部文件
    public static File createExternalFile(String name) {
        if(isSDcardAvailable()) {
            return new File(getExternalDir(), name);
        }
        return null;
    }

    // 获取外部文件目录
    public static File getExternalDir() {
        if(isSDcardAvailable()) {
            File dir = new File(Environment.getExternalStorageDirectory(), EXTERNAL_DIR);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            return dir;
        }

        return null;
    }

    // SD卡是否可用
    public static boolean isSDcardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 读取Assets目录下面指定文件并返回String数据
     * @param context
     * @param fileName
     * @return
     */
    public static String getJsonDataFromAssets(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
           inputStream  = context.getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            String json = new String(buffer, "utf-8");
            stringBuilder = stringBuilder.append(json);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(inputStream != null) {
                    inputStream.close();
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
