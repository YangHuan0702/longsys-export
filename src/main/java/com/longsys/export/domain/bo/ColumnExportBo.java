package com.longsys.export.domain.bo;

import com.longsys.export.domain.request.ColumnRequest;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author huan.yang
 * @date 2022-03-30
 */
@Data
public class ColumnExportBo extends ColumnRequest {

    /**
     * 行
     */
    private List<Map<String, Object>> rows;

}

