package com.longsys.export.mapper;

import com.longsys.export.domain.empty.Column;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 表信息Mapper接口
 * @author huan.yang
 * @date 2022-03-29
 */
@Mapper
public interface ColumnMapper {


    /**
     * 批量插入
     * @param columns columns
     * @return 影响行数
     */
    int batchInsert(List<Column> columns);

}
