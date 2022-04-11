package com.longsys.export.mapper;

import com.longsys.export.domain.empty.ExportLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author huan.yang
 * @date 2022-04-01
 */
@Mapper
public interface ExportLogMapper {


    /**
     * 插入
     *
     * @param exportLog 实体对象
     * @return 结果
     */
    int insert(@Param("exportLog") ExportLog exportLog);


    /**
     * 全量查询
     * @param params 参数
     * @return 结果
     */
    List<ExportLog> selectByParams(@Param("params") Map<String, Object> params);

}
