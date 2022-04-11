package com.longsys.export.domain.response;

import com.longsys.export.domain.empty.ExportLog;
import com.longsys.export.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huan.yang
 * @date 2022-04-01
 */
@ApiModel("导出日志响应模型")
@Data
@AllArgsConstructor
public class ExportLogResp {

    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "请求参数")
    private String request;

    @ApiModelProperty(value = "导出人")
    private String userName;

    @ApiModelProperty(value = "导出时间")
    private String exportDateTime;

    @ApiModelProperty(value = "导出说明")
    private String exportDesc;


    public static ExportLogResp build(ExportLog exportLog) {
        return new ExportLogResp(exportLog.getId(),exportLog.getFileName(), exportLog.getRequest(), exportLog.getUserName(), DateUtil.date2Str(exportLog.getETime()), exportLog.getExportDesc());
    }
}
