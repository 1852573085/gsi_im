package com.aqiang.storage.sp;

import android.content.Context;
import android.content.SharedPreferences;

import com.aqiang.common.app.BaseApplication;

public class SPUtils {

    private SharedPreferences sp;

    private SPUtils(){
        sp = BaseApplication.getContext().getSharedPreferences("gis_im", Context.MODE_PRIVATE);
    }

    private static SPUtils spUtils = new SPUtils();

    public static SPUtils getInstance(){
        return spUtils;
    }

    private SharedPreferences.Editor getEditor(){
        return sp.edit();
    }

    public <T> void put(String key,T value){
        SharedPreferences.Editor editor = getEditor();
        if(value instanceof String){
            editor.putString(key, (String) value);
        }else if(value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if(value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if(value instanceof Float){
            editor.putFloat(key, (Float) value);
        }else if(value instanceof Long){
            editor.putBoolean(key, (Boolean) value);
        }
        editor.commit();
    }

    public Object getObject(String key,Object defaultValue){
        if(defaultValue instanceof String){
            return sp.getString(key, (String) defaultValue);
        }else if(defaultValue instanceof Boolean){
            return sp.getBoolean(key, (Boolean) defaultValue);
        }else if(defaultValue instanceof Integer){
            return sp.getInt(key, (Integer) defaultValue);
        }else if(defaultValue instanceof Float){
            return sp.getFloat(key, (Float) defaultValue);
        }else if(defaultValue instanceof Long){
            return sp.getLong(key, (Long) defaultValue);
        }
        return null;
    }

    public void remove(String key){
        getEditor().remove(key).commit();
    }
    public boolean contains(String key){
        return sp.contains(key);
    }

    public void clear(){
        getEditor().clear();
    }
}
