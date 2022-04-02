package com.longsys.export.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 导出接口
 * @date 2022-03-29
 * @author huan.yang
 */
@Api(value = "导出接口")
@Slf4j
@RestController
@RequestMapping("/export")
public class ExportController {


    @PostMapping("/ddd")
    @ApiOperation(value = "导出信息（统一）")
    public void export(){

    }

}
