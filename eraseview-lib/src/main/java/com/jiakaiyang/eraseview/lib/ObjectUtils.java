package com.jiakaiyang.eraseview.lib;

import java.lang.reflect.Field;

/**
 * Created by jia on 2017/10/3.
 */

public class ObjectUtils {

    /**
     *
     * @param object
     * @param fieldName
     * @return
     */
    public static Field getDeclaredMethod(Object object, String fieldName) {
        Field field = null;
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {

            }
        }
        return null;
    }
}
