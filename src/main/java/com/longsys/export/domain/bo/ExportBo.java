package com.longsys.export.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huan.yang
 * @date 2022-03-30
 */
@Data
@AllArgsConstructor
public class ExportBo {

    /**
     * Excel头
     */
    private Map<String, LinkedHashMap<String, String>> excelTitles;

    /**
     * 行信息
     */
    private List<ColumnExportBo> nodes;


}
