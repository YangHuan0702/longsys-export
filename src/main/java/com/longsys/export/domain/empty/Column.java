package com.longsys.export.domain.empty;

import lombok.Data;

/**
 * @author huan.yang
 * @date 2022-03-29
 */
@Data
public class Column {
    /**
     * 字段名
     */
    private String column;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 所属表名
     */
    private String tableName;

}
