package com.longsys.export.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 表信息响应模型
 *
 * @author huan.yang
 * @date 2022-03-31
 */
@ApiModel(value = "表信息响应模型")
@Data
@AllArgsConstructor
public class TableInfoResponse {

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "表名")
    private String tableDesc;

    @ApiModelProperty(value = "字段信息")
    private List<ColumnInfoResponse> cs;

    public static TableInfoResponse build(String tableName, String tableDesc, List<ColumnInfoResponse> cs) {
        return new TableInfoResponse(tableName, tableDesc, cs);
    }

}
