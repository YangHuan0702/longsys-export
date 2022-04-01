package com.longsys.export.domain.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字段导入业务模型
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImportColumnBo extends BaseRowModel {

    @ExcelProperty(value = "字段名", index = 0)
    private String column;

    @ExcelProperty(value = "字段名", index = 1)
    private String name;
}
