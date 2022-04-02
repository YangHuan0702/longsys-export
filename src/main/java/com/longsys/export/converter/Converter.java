package com.longsys.export.converter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * 转换器统一接口
 * @author huan.yang
 * @date 2022-03-29
 */
public interface Converter<T> {
    /**
     * 通过xss文件转换为目标对象
     * @param xssfWorkbook Xss文档对象
     * @return 转换后的类型
     */
    List<T> convertByXss(XSSFWorkbook xssfWorkbook);

}
