package com.jiakaiyang.eraseview.lib.utils;

import java.lang.reflect.Field;

/**
 * Created by jia on 2017/12/8.
 */

public class ClassUtils {

    public static Object getTargetField(Object object
            , String name) {
        Class clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;

        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {

            }
        }

        return null;
    }
}
