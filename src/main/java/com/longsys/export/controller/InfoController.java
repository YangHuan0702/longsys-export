package com.longsys.export.controller;

import com.longsys.export.domain.response.Resp;
import com.longsys.export.domain.response.TableInfoResponse;
import com.longsys.export.service.ColumnService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huan.yang
 * @date 2022-03-31
 */
@RestController
@RequestMapping("/info")
@Slf4j
public class InfoController {


    private final ColumnService columnService;

    @Autowired
    public InfoController(ColumnService columnService){
        this.columnService = columnService;
    }


    @GetMapping("/tables")
    @ApiOperation(value = "获取表信息接口")
    public Resp<List<TableInfoResponse>> tables() {
        return Resp.success(columnService.tableList());
    }

}
