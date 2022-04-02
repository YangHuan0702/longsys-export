package com.longsys.export.constant.benum;


import lombok.Getter;

import java.lang.reflect.Constructor;

/**
 * 导入业务线中的异常枚举
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@Getter
public enum ImportExceptionEnum {

    /**
     * XSS为NULL
     */
    XSS_NULL("XSS对象为NULL", NullPointerException.class),
    /**
     * 导入失败
     */
    INSERT_ERROR("导入失败", RuntimeException.class);


    /**
     * 异常信息
     */
    private final String msg;

    /**
     * 异常
     */
    private final Class<? extends Exception> exception;

    ImportExceptionEnum(String msg, Class<? extends Exception> e) {
        this.msg = msg;
        this.exception = e;
    }


    public static void asser(boolean r, ImportExceptionEnum importExceptionEnum) throws Exception {
        if (r) {
            Constructor<? extends Exception> constructor = importExceptionEnum.getException().getConstructor(String.class);
            throw constructor.newInstance(importExceptionEnum.getMsg());
        }
    }
}
