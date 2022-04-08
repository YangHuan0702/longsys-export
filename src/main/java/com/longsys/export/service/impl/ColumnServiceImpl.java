package com.longsys.export.service.impl;

import com.longsys.export.constant.SpecialInfoConstant;
import com.longsys.export.domain.empty.Column;
import com.longsys.export.domain.response.ColumnInfoResponse;
import com.longsys.export.domain.response.TableInfoResponse;
import com.longsys.export.mapper.ColumnMapper;
import com.longsys.export.service.ColumnService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表-字段服务层接口实现
 *
 * @author huan.yang
 * @date 2022-03-31
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    private final ColumnMapper columnMapper;

    @Autowired
    public ColumnServiceImpl(ColumnMapper columnMapper) {
        this.columnMapper = columnMapper;
    }


    @Override
    public List<TableInfoResponse> tableList() {
        List<Column> cs = columnMapper.list();
        if (CollectionUtils.isEmpty(cs)) {
            return null;
        }
        return conversion(cs);
    }

    private List<TableInfoResponse> conversion(List<Column> cs) {

        List<TableInfoResponse> r = new ArrayList<>();

        Map<String, List<Column>> groupByTableName = new HashMap<>(cs.size() >> SpecialInfoConstant.ORDINARY_ONE);

        for (Column c : cs) {
            List<Column> columns = groupByTableName.computeIfAbsent(c.getTableName(), k -> new ArrayList<>());
            columns.add(c);
        }

        for (Map.Entry<String, List<Column>> entry : groupByTableName.entrySet()) {
            if (StringUtils.isNotEmpty(entry.getKey()) && CollectionUtils.isNotEmpty(entry.getValue())) {
                List<ColumnInfoResponse> columns = ColumnInfoResponse.buils(entry.getValue());
                TableInfoResponse tableResp = TableInfoResponse.build(entry.getKey(), entry.getValue().get(SpecialInfoConstant.SIZE_0).getTableDesc(), columns);
                r.add(tableResp);
            }
        }

        return r;
    }


}
