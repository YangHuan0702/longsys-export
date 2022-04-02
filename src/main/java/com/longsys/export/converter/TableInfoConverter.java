package com.longsys.export.converter;


import com.longsys.export.constant.ExcelConstant;
import com.longsys.export.constant.LogInfoConstant;
import com.longsys.export.constant.SpecialInfoConstant;
import com.longsys.export.domain.empty.Column;
import com.longsys.export.util.ParamsLogicUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表信息转换器
 *
 * @author huan.yang
 * @date 2022-03-29
 */
public class TableInfoConverter implements Converter<Column> {

    private final static TableInfoConverter INSTANCE = new TableInfoConverter();

    public static TableInfoConverter getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Column> convertByXss(XSSFWorkbook xssfWorkbook) {

        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(SpecialInfoConstant.ORDINARY_ZERO);
        XSSFRow fields = xssfSheet.getRow(SpecialInfoConstant.ORDINARY_ZERO);

        int maxCell = fields.getLastCellNum();

        List<Map<String, String>> list = new ArrayList<>();

        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            Map<String, String> map = new HashMap<>(maxCell);
            XSSFRow xssfRow = xssfSheet.getRow(i);
            int minCell = xssfRow.getFirstCellNum();
            maxCell = xssfRow.getLastCellNum();
            for (int j = minCell; j < maxCell; j++) {
                map.put(fields.getCell(j).toString(), xssfRow.getCell(j).toString());
            }
            list.add(map);
        }

        List<Column> columns = new ArrayList<>(list.size());
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, String> row : list) {
                String column = row.get(ExcelConstant.TABLE_INFO_COLUMN);
                String name = row.get(ExcelConstant.TBALE_INFO_NAME);
                String tableName = row.get(ExcelConstant.TABLE_INFO_TABLE_NAME);
                if (ParamsLogicUtil.validaStrArrayExistEmpty(column, name, tableName)) {
                    throw new NullPointerException(LogInfoConstant.EXCEL_NOT_ALLOWED_EMPTY_ROW);
                }
                columns.add(Column.build(column, name, tableName));
            }
        }
        return columns;
    }


}
