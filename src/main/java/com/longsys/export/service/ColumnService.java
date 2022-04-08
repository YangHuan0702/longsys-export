package com.longsys.export.service;

import com.longsys.export.domain.response.TableInfoResponse;

import java.util.List;

/**
 * 表-字段服务层接口
 *
 * @author huan.yang
 * @date 2022-03-31
 */
public interface ColumnService {

    /**
     * 获取所有的表信息
     *
     * @return 表信息
     */
    List<TableInfoResponse> tableList();

}
