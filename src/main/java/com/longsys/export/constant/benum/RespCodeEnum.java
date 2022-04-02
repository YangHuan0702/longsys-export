package com.longsys.export.constant.benum;

import lombok.Getter;

/**
 * @author huan.yang
 * @date 2022-03-29
 */
@Getter
public enum RespCodeEnum {
    /**
     * 成功
     */
    SUCC(200,"访问成功"),

    /**
     * 500
     */
    ERROR(500,"系统错误"),

    /**
     * 403
     */
    PERMISSION_E(403,"无权访问"),

    /**
     * 导入失败
     */
    IMPORT_FAILURE(1000,"导入失败")
    ;

    /**
     * 响应码
     */
    private final int code;

    /**
     * 输出信息
     */
    private final String msg;

    RespCodeEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }


}
