package com.longsys.export.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导入服务接口
 * @author huan.yang
 * @date 2022-03-29
 */
public interface ImportService {

    /**
     * 从XSS中导入表信息
     * @param xssfWorkbook excel表信息
     * @throws Exception 业务处理异常
     */
    void insertTableInfoByXss(XSSFWorkbook xssfWorkbook) throws Exception;

}
