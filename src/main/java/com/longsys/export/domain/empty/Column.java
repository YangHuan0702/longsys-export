package com.longsys.export.domain.empty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huan.yang
 * @date 2022-03-29
 */
@Data
@AllArgsConstructor
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


    public static Column build(String column,String name,String tableName){
        return new Column(column,name,tableName);
    }

}
