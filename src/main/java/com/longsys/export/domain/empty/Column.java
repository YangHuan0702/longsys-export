package com.longsys.export.domain.empty;

import com.longsys.export.util.primaryKey.IDUtil;
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
     * id
     */
    private Long id;

    /**
     * 字段名
     */
    private String cname;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 所属表名
     */
    private String tableName;

    /**
     * 表名
     */
    private String tableDesc;

    public static Column build(String cname,String name,String tableName,String tableDesc){
        return new Column(IDUtil.getUniqueId(),cname,name,tableName,tableDesc);
    }
}
