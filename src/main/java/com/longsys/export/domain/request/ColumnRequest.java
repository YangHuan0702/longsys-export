package com.longsys.export.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 导出字段请求模型
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@ApiModel(value = "导出字段请求模型")
@Data
public class ColumnRequest {

    @ApiModelProperty(value = "node key，注意该值需要保证当前请求模型中的唯一性")
    private String key;

    @ApiModelProperty(value = "所需字段")
    private List<String> columns;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "查询条件")
    private Map<String, Object> params;

    @ApiModelProperty(value = "关联关系")
    private ObjOfRequest objOfRequest;
}
