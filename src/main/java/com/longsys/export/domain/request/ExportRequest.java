package com.longsys.export.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 导出模型
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@Data
@ApiModel(value = "导出模型")
public class ExportRequest {

    @ApiModelProperty(value = "导出文件名称")
    private String fileName;

    @ApiModelProperty(value = "导出备注")
    private String desc;

    @ApiModelProperty(value = "导出人名称")
    private String username;

    @ApiModelProperty(value = "导出数据模型")
    private List<ColumnRequest> dataModel;

    @ApiModelProperty(value = "是否保存日志:0:否/1:s是")
    private String isSave;
}
