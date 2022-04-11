package com.longsys.export.constant;

/**
 * @author huan.yang
 * @date 2022-03-29
 */
public class LogInfoConstant {

    public static final String API_ERROR_LOG = "%s API Error : %s,%s";

    public static final String EXCEL_NOT_ALLOWED_EMPTY_ROW = "Excel中不能存在为空的列";



    public static String ERROR_INFO(Throwable e,String className){
        StackTraceElement stackTraceElement = e.getStackTrace()[SpecialInfoConstant.SIZE_0];
        return String.format(LogInfoConstant.API_ERROR_LOG, className, e.getMessage(),stackTraceElement.toString());
    }

}
