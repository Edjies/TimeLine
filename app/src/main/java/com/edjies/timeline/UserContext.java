package com.edjies.timeline;

import android.content.Context;
import android.content.SharedPreferences;

import com.edjies.timeline.utils.UFile;
import com.edjies.timeline.utils.UPreferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 线程安全的用户环境单例
 * Created by hubble on 2016/10/19 0019.
 */

public class UserContext {

    public final static String FIELD_CONTRIBUTION = "user_contribution";
    public final static String SERIALIZE_FIELD_BANK_CARD = "user_bank_card";
    /**ArrayList<ClassInfo>数据*/
    public final static String SERIALIZE_FIELD_CLASS = "user_class";
    /**ArrayList<ContactGroup> 用户联系人数据*/
    public final static String SERIALIZE_FIELD_CONTACT = "user_contact";
    // 单例设置
    private static UserContext mUserContext;

    private UserContext() {

    }

    public static UserContext getInstance() {
        if(mUserContext == null) {
            synchronized (UserContext.class) {
                if(mUserContext == null) {
                    mUserContext = new UserContext();
                }
            }
        }
        return mUserContext;
    }

    public String getField(Context context, String field, String defaultValue) {
        return UPreferences.getInstance(context).getString(field, defaultValue);
    }

    public String getField(Context context, String field) {
        return UPreferences.getInstance(context).getString(field, "");
    }

    public Serializable getSerializableField(Context context, String field, Serializable defaultValue) {
        ObjectInputStream inStream = null;
        try {
            File file = UFile.createInternalFile(context, field);
            if(!file.exists() || !file.isFile()) {
                return defaultValue;
            }

            inStream = new ObjectInputStream(new FileInputStream(file));
            return (Serializable) inStream.readObject();
        }catch (Exception e) {

        }finally {
            if(inStream != null) {
                try{
                    inStream.close();
                }catch (Exception e) {

                }
            }
        }
        return defaultValue;
    }

    public void setSerializableField(Context context, String field, Serializable object) {
        ObjectOutputStream outStream = null;
        try {
            File file = UFile.createInternalFile(context, field);
            if(!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            outStream = new ObjectOutputStream(new FileOutputStream(file));
            outStream.writeObject(object);
        }catch (Exception e) {

        }finally {
            if(outStream != null) {
                try{
                    outStream.close();
                }catch (Exception e) {

                }
            }
        }
    }

    public void setField(Context context, String field, String value) {
        UPreferences.getInstance(context).setString(field, value);
    }



}
