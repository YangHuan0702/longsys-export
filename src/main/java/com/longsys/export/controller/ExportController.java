package com.longsys.export.controller;


import com.longsys.export.constant.LogInfoConstant;
import com.longsys.export.domain.bo.ExportBo;
import com.longsys.export.domain.request.ExportRequest;
import com.longsys.export.domain.response.ExportLogResp;
import com.longsys.export.domain.response.Resp;
import com.longsys.export.service.ExportLogService;
import com.longsys.export.service.ExportService;
import com.longsys.export.util.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 导出接口
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@Api(value = "导出接口")
@Slf4j
@RestController
@RequestMapping("/export")
public class ExportController {


    private final ExportService exportService;

    private final ExportLogService exportLogService;

    @Autowired
    public ExportController(ExportService exportService, ExportLogService exportLogService) {
        this.exportService = exportService;
        this.exportLogService = exportLogService;
    }


    @PostMapping("/ddd")
    @ApiOperation(value = "导出信息（统一）")
    public void export(@RequestBody ExportRequest exportRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            ExportBo export = exportService.export(exportRequest);
            ExcelUtil.downLoad(request, response, exportRequest.getFileName(), export);
        } catch (Throwable e) {
            log.error(LogInfoConstant.ERROR_INFO(e, this.getClass().getName()));
        }
    }

    @GetMapping("/exportLogs")
    @ApiOperation(value = "导出日志")
    public Resp<List<ExportLogResp>> exportLogs() {
        List<ExportLogResp> exportLogResp = exportLogService.fullLog();
        return Resp.success(exportLogResp);
    }

}
