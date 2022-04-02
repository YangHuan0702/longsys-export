package com.longsys.export.controller;

import com.longsys.export.constant.LogInfoConstant;
import com.longsys.export.constant.benum.RespCodeEnum;
import com.longsys.export.domain.response.Resp;
import com.longsys.export.service.ImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 导入接口
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@Api(value = "导入接口")
@RestController
@RequestMapping("/import")
@Slf4j
public class ImportController {


    private final ImportService importService;

    @Autowired
    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/importTableInfo")
    @ApiOperation(value = "导入表信息接口")
    public Resp<Void> importTableInfo(@RequestParam MultipartFile file) {
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
            importService.insertTableInfoByXss(xssfWorkbook);
        } catch (Throwable e) {
            RespCodeEnum respCodeEnum = e instanceof Error ? RespCodeEnum.ERROR : RespCodeEnum.IMPORT_FAILURE;
            log.error(String.format(LogInfoConstant.API_ERROR_LOG, this.getClass().getName(), e.getMessage()));
            return Resp.error(respCodeEnum);
        }
        return Resp.success();
    }
}
