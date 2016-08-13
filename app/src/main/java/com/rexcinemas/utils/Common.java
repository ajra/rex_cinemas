package com.rexcinemas.utils;

public class Common {
    public static <T> String getTAG(Class<T> clazz) {
        String className = clazz.getName();
        className = getClassNameFromPackageName(className);
        return className;
    }
    private static String getClassNameFromPackageName(String className) {
        if (className.contains(".")) {
            String[] classNameArr = className.split("\\.");
            className = classNameArr[classNameArr.length - 1];
        }
        return className;
    }
}
