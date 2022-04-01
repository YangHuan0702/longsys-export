package com.longsys.export.domain.response;

import com.longsys.export.constant.RespCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一响应模型
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@ApiModel(value = "响应")
@Data
public class Resp<T> {

    @ApiModelProperty(value = "附加数据")
    private Object obj;

    @ApiModelProperty(value = "输出信息")
    private String msg;

    @ApiModelProperty(value = "响应码")
    private int code;

    @ApiModelProperty(value = "响应数据")
    private T data;


    public Resp(T data) {
        this.data = data;
    }

    public Resp(T data, RespCodeEnum respCodeEnum) {
        this.data = data;
        this.code = respCodeEnum.getCode();
        this.msg = respCodeEnum.getMsg();
    }

    /**
     * 成功信息
     *
     * @param data 响应数据
     * @param <T>  数据泛型
     * @return 统一响应
     */
    public static <T> Resp success(T data) {
        return new Resp(data, RespCodeEnum.SUCC);
    }


}
