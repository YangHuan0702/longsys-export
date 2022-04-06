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
public enum ExceptionEnum {

    /**
     * XSS为NULL
     */
    XSS_NULL("XSS对象为NULL", NullPointerException.class),
    /**
     * 导入失败
     */
    INSERT_ERROR("导入失败", RuntimeException.class),

    /**
     * 导出失败,缺少必要数据
     */
    EXPORT_ERROR("导出失败,缺少必要数据",NullPointerException.class),

    /**
     * 数据节点为空
     */
    DATA_NODE_NULL("数据节点为空!", NullPointerException.class),

    /**
     * 字段配置信息为空
     */
    COLUMN_EMPTY("查询字段配置信息为空", NullPointerException.class),

    /**
     * 构建Excel文件异常,基础数据为空
     */
    BUILD_EXCEL_ERROR("构建Excel文件异常,基础数据为空",NullPointerException.class)
    ;


    /**
     * 异常信息
     */
    private final String msg;

    /**
     * 异常
     */
    private final Class<? extends Exception> exception;

    ExceptionEnum(String msg, Class<? extends Exception> e) {
        this.msg = msg;
        this.exception = e;
    }


    public static void asser(boolean r, ExceptionEnum importExceptionEnum) throws Exception {
        if (r) {
            Constructor<? extends Exception> constructor = importExceptionEnum.getException().getConstructor(String.class);
            throw constructor.newInstance(importExceptionEnum.getMsg());
        }
    }
}
