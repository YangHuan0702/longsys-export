package com.longsys.export.mapper;

import org.apache.ibatis.annotations.Mapper;

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
     * @param params 参数
     * @return 对应的返回行
     */
    List<Map> commonSelect(Map<String,String> params);

}
