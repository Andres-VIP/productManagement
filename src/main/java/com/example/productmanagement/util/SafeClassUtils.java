package com.example.productmanagement.util;

import org.apache.commons.lang3.ClassUtils;

public class SafeClassUtils {

    private static final int MAX_CLASS_NAME_LENGTH = 200;

    public static Class<?> safeGetClass(String className) throws ClassNotFoundException {
        if (className == null || className.length() > MAX_CLASS_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid or too long class name");
        }
        return ClassUtils.getClass(className);
    }
}
