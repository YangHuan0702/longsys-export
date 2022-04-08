package com.longsys.export.controller;


import com.longsys.export.constant.LogInfoConstant;
import com.longsys.export.constant.benum.RespCodeEnum;
import com.longsys.export.domain.bo.ExportBo;
import com.longsys.export.domain.request.ExportRequest;
import com.longsys.export.domain.response.Resp;
import com.longsys.export.service.ExportService;
import com.longsys.export.util.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    public ExportController(ExportService exportService){
        this.exportService = exportService;
    }


    @PostMapping("/ddd")
    @ApiOperation(value = "导出信息（统一）")
    public void export(@RequestBody ExportRequest exportRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            ExportBo export = exportService.export(exportRequest);
            ExcelUtil.downLoad(request,response,exportRequest.getFileName(),export);
        } catch (Throwable e) {
            RespCodeEnum respCodeEnum = e instanceof Error ? RespCodeEnum.ERROR : RespCodeEnum.IMPORT_FAILURE;
            log.error(String.format(LogInfoConstant.API_ERROR_LOG, this.getClass().getName(), e.getMessage()));
        }
    }

}
