package com.yh.admin.common.utils;

import com.yh.admin.common.exceptions.CheckException;

/**
 * 校验工具类
 */
public class CheckUtil {

    public static void check(boolean condition, String msg){
        if (!condition){
            fail(msg);
        }
    }

    public static void notNull(Object obj, String msg){
        if(obj == null){
            fail(msg);
        }
    }

    private static void fail(String msg){
        throw new CheckException(msg);
    }
}
