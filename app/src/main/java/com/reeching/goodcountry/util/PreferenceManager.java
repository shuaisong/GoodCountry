package com.reeching.goodcountry.util;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    /**
     * name of preference
     */
    private static final String PREFERENCE_NAME = "saveInfo";
    private static PreferenceManager mPreferencemManager;
    private static SharedPreferences.Editor editor;
    private SharedPreferences mMSharedPreferences;

    private PreferenceManager(Context cxt) {
        mMSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = mMSharedPreferences.edit();
    }

    public static synchronized void init(Context cxt) {
        if (mPreferencemManager == null) {
            mPreferencemManager = new PreferenceManager(cxt);
        }
    }

    /**
     * get instance of PreferenceManager
     *
     * @param
     * @return
     */
    public synchronized static PreferenceManager getInstance() {
        if (mPreferencemManager == null) {
            throw new RuntimeException("please init first!");
        }

        return mPreferencemManager;
    }

    public void putString(String key, String value) {
        editor.putString(key, value).apply();
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public String getString(String key) {
        return mMSharedPreferences.getString(key, "");
    }

    public int getInt(String key) {
        return mMSharedPreferences.getInt(key, -1);
    }

    public float getFloat(String key) {
        return mMSharedPreferences.getFloat(key, -1);
    }

    public long getLong(String key) {
        return mMSharedPreferences.getLong(key, -1L);
    }

    public boolean getBoolean(String key) {
        return mMSharedPreferences.getBoolean(key, false);
    }

    public void setIsFirst(boolean isFirst) {
        editor.putBoolean("isFirst", isFirst);
        editor.apply();
    }

    public boolean getIsFirst() {
        return mMSharedPreferences.getBoolean("isFirst", true);
    }
}
