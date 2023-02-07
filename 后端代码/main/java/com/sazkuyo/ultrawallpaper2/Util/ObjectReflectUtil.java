package com.sazkuyo.ultrawallpaper2.Util;

import com.sazkuyo.ultrawallpaper2.Entity.Category;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;

public class ObjectReflectUtil {

    public static void setObjectProperty(Object obj, String property_name, Object value) {
        Class<?> objClass = null;
        try {
            objClass = obj.getClass();
            Field field = objClass.getDeclaredField(property_name);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.out.println("没有该参数");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("外部操作权限被禁止");
        }
    }

    public static Object getObjProperty(Object obj, String property_name) {
        Class<?> objClass = null;
        try {
            objClass = obj.getClass();
            Field field = objClass.getDeclaredField(property_name);
            field.setAccessible(true);
            return field.get(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.out.println("没有该参数");
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("外部操作权限被禁止");
            return null;
        }
    }
}
