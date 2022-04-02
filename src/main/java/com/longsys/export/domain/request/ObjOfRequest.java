package com.longsys.export.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 关联关系请求模型
 * 注意：如果不存在关联表，则直接按照数据模型中的目标字段进行关联
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@ApiModel(value = "关联对象请求模型")
@Data
public class ObjOfRequest {

    @ApiModelProperty(value = "关联表表名")
    private String objOfTableName;

    @ApiModelProperty(value = "左边字段")
    private String leftColumn;

    @ApiModelProperty(value = "左边节点")
    private String leftKey;

    @ApiModelProperty(value = "右边字段")
    private String rightColumn;

    @ApiModelProperty(value = "右边节点")
    private String rightKey;

    @ApiModelProperty(value = "0:一对一/1：一对多")
    private int multi;
}
