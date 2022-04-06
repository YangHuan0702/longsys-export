package com.longsys.export.service.impl;

import com.longsys.export.constant.benum.ExceptionEnum;
import com.longsys.export.converter.TableInfoConverter;
import com.longsys.export.domain.empty.Column;
import com.longsys.export.mapper.ColumnMapper;
import com.longsys.export.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 导入服务接口实现类
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@Service
@Slf4j
public class ImportServiceImpl implements ImportService {

    private final TableInfoConverter tableInfoConverter;

    private final ColumnMapper columnMapper;

    @Autowired
    public ImportServiceImpl(ColumnMapper columnMapper) {
        this.columnMapper = columnMapper;
        this.tableInfoConverter = TableInfoConverter.getInstance();
    }

    @Override
    public void insertTableInfoByXss(XSSFWorkbook xssfWorkbook) throws Exception {
        ExceptionEnum.asser(xssfWorkbook == null, ExceptionEnum.XSS_NULL);
        List<Column> columns = tableInfoConverter.convertByXss(xssfWorkbook);
        int r = columnMapper.batchInsert(columns);
        ExceptionEnum.asser(r <= 0, ExceptionEnum.INSERT_ERROR);
    }
}
