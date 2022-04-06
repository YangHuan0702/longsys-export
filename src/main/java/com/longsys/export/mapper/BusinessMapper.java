package com.longsys.export.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author huan.yang
 * @date 2022-03-29
 */
@Mapper
public interface BusinessMapper {


    /**
     * 通用查询
     *
     * @param columns   所需字段
     * @param tableName 目标表
     * @param params    参数
     * @return 对应的返回行
     */
    List<Map<String, Object>> commonSelect(@Param("columns") String columns, @Param("tableName") String tableName, @Param("condition") Map<String, Object> params);

    /**
     * 通用关系关联查询
     *
     * @param objofTableName 关系关联表名
     * @param params         参数
     * @return 对应的返回行
     */
    List<Map<String, String>> commonSelectObjOf(@Param("objofTableName") String objofTableName, @Param("condition") Map<String, Object> params);

}
