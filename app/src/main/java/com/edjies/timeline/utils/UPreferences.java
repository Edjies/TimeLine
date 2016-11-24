package com.edjies.timeline.utils;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**偏好工具
 * @author hubble
 */
public class UPreferences {

	public final static String PREFER_FIRST_USE = "prefer_first_use";


	
	private SharedPreferences sp;
	
	private static UPreferences helper;
	
	
	private UPreferences(Context context) {
		sp = context.getSharedPreferences("kyx_user_prefer", Context.MODE_PRIVATE);
	}
	
	public static UPreferences getInstance(Context context) {
		if(helper == null) {
			synchronized (UPreferences.class) {
				if(helper == null) {
					helper = new UPreferences(context);
				}
			}
		}
		return helper;
	}

	public SharedPreferences.Editor getEditor() {
		return sp.edit();
	}

	
	
	public String getString(String flag, String defaultValue) {
		return sp.getString(flag, defaultValue);
	}
	
	public String getString(String flag, String uid, String defaultValue) {
		return sp.getString(flag + uid, defaultValue);
	}
	
	public boolean getBoolean(String flag, boolean defValue) {
		return sp.getBoolean(flag, defValue);
	}
	
	public boolean getBoolean(String flag, String uid, boolean defValue) {
		return sp.getBoolean(flag + uid, defValue);
	}
	
	public int getInt(String flag, int defValue) {
		return sp.getInt(flag, defValue);
	}
	
	public int getInt(String flag, String uid, int defValue) {
		return sp.getInt(flag + uid, defValue);
	}
	
	public long getLong(String flag, long defValue) {
		return sp.getLong(flag, defValue);
	}
	
	public long getLong(String flag, String uid, long defValue) {
		return sp.getLong(flag + uid, defValue);
	}
	
	public void setString(String flag, String value) {
		sp.edit().putString(flag, value).commit();
	}
	
	public void setString(String flag, String uid, String value) {
		sp.edit().putString(flag + uid, value).commit();
	}
	
	public void setBoolean(String flag, boolean value) {
		sp.edit().putBoolean(flag, value).commit();
	}
	
	public void setBoolean(String flag, String uid, boolean value) {
		sp.edit().putBoolean(flag + uid, value).commit();
	}
	
	public void setInt(String flag, int value) {
		sp.edit().putInt(flag, value).commit();
	}
	
	public void setInt(String flag, String uid, int value) {
		sp.edit().putInt(flag + uid, value).commit();
	}
	
	public void setLong(String flag, long value) {
		sp.edit().putLong(flag, value).commit();
	}
	
	public void setLong(String flag, String uid, long value) {
		sp.edit().putLong(flag + uid, value).commit();
	}
	
	public void setStringSet(String flag, HashSet<String> value) {
		sp.edit().putStringSet(flag, new HashSet<String>()).commit();
		sp.edit().putStringSet(flag, value).commit();
	}
	
	public Set<String> getStringSet(String flag, HashSet<String> defValue) {
		return sp.getStringSet(flag, defValue);
	}
	
	public void setStringSet(String flag, String uid, HashSet<String> value) {
		sp.edit().putStringSet(flag + uid, new HashSet<String>()).commit();
		sp.edit().putStringSet(flag + uid, value).commit();
	}
	
	public Set<String> getStringSet(String flag, String uid, HashSet<String> defValue) {
		return sp.getStringSet(flag + uid, defValue);
	}
	
}
