package com.longsys.export.controller;


import com.longsys.export.constant.LogInfoConstant;
import com.longsys.export.constant.benum.RespCodeEnum;
import com.longsys.export.domain.request.ExportRequest;
import com.longsys.export.domain.response.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/ddd")
    @ApiOperation(value = "导出信息（统一）")
    public Resp<Void> export(@RequestBody ExportRequest exportRequest) {
        try {

        }catch (Throwable e){
            RespCodeEnum respCodeEnum = e instanceof Error ? RespCodeEnum.ERROR : RespCodeEnum.IMPORT_FAILURE;
            log.error(String.format(LogInfoConstant.API_ERROR_LOG, this.getClass().getName(), e.getMessage()));
            return Resp.error(respCodeEnum);
        }
        return Resp.success();
    }

}
