package com.longsys.export.mapper;

import com.longsys.export.domain.empty.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表信息Mapper接口
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@Mapper
public interface ColumnMapper {


    /**
     * 批量插入
     *
     * @param columns columns
     * @return 影响行数
     */
    int batchInsert(List<Column> columns);


    /**
     * 根据字段名与表名进行查询
     *
     * @param tables  表名
     * @param columns 字段名
     * @return DB数据
     */
    List<Column> listByColumnsAndTables(@Param("tables") List<String> tables, @Param("columns") List<String> columns);

}
