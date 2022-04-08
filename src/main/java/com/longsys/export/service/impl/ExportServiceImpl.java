package com.longsys.export.service.impl;

import com.longsys.export.constant.SpecialInfoConstant;
import com.longsys.export.constant.benum.ExceptionEnum;
import com.longsys.export.domain.bo.ColumnExportBo;
import com.longsys.export.domain.bo.ExportBo;
import com.longsys.export.domain.empty.Column;
import com.longsys.export.domain.request.ColumnRequest;
import com.longsys.export.domain.request.ExportRequest;
import com.longsys.export.mapper.BusinessMapper;
import com.longsys.export.mapper.ColumnMapper;
import com.longsys.export.pattern.ObjOfFactory;
import com.longsys.export.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 导出服务层实现
 *
 * @author huan.yang
 * @date 2022-03-30
 */
@Slf4j
@Service
public class ExportServiceImpl implements ExportService {


    private final BusinessMapper businessMapper;

    private final ColumnMapper columnMapper;

    private final ObjOfFactory objOfFactory;

    @Autowired
    public ExportServiceImpl(BusinessMapper businessMapper, ColumnMapper columnMapper) {
        this.businessMapper = businessMapper;
        this.columnMapper = columnMapper;
        this.objOfFactory = ObjOfFactory.getInstance();
    }

    @Override
    public ExportBo export(ExportRequest request)throws Exception {
        ExceptionEnum.asser(null == request || CollectionUtils.isEmpty(request.getDataModel()),ExceptionEnum.EXPORT_ERROR);
        return processor(request);
    }


    public ExportBo processor(ExportRequest request) throws Exception {

        Map<String, ColumnExportBo> groupByNodeKey = request.getDataModel().stream().collect(Collectors.toMap(ColumnRequest::getKey, columnRequest -> {
            ColumnExportBo columnExportBo = new ColumnExportBo();
            BeanUtils.copyProperties(columnRequest, columnExportBo);
            return columnExportBo;
        }));

        List<ColumnExportBo> bos = new ArrayList<>();
        for (ColumnExportBo columnExportBo : groupByNodeKey.values()) {
            List<Map<String, Object>> maps = getRows(columnExportBo, groupByNodeKey);
            columnExportBo.setRows(maps);
            bos.add(columnExportBo);
        }

        Map<String, LinkedHashMap<String, String>> excelTitle = getExcelTitle(bos);
        return new ExportBo(excelTitle, bos);
    }

    private Map<String, LinkedHashMap<String, String>> getExcelTitle(List<ColumnExportBo> p) throws Exception {

        Map<String, Map<String, String>> tableInfo = getTableInfo(p);

        Map<String, LinkedHashMap<String, String>> r = new HashMap<>(tableInfo.keySet().size());

        for (ColumnExportBo columnExportBo : p) {
            Map<String, String> columnNames = tableInfo.get(columnExportBo.getTableName());
            List<String> orderColumns = columnExportBo.getColumns();

            LinkedHashMap<String, String> title = new LinkedHashMap<>();
            for (String orderColumn : orderColumns) {
                String s = columnNames.get(orderColumn);
                if (!StringUtils.isEmpty(s)) {
                    title.put(orderColumn, s);
                }
            }
            if (!title.isEmpty()) {
                r.put(columnExportBo.getKey(), title);
            }
        }
        return r;
    }


    private Map<String, Map<String, String>> getTableInfo(List<ColumnExportBo> p) throws Exception {
        List<String> allColumns = new ArrayList<>();
        List<String> tables = new ArrayList<>();
        for (ColumnExportBo columnExportBo : p) {
            allColumns.addAll(columnExportBo.getColumns());
            tables.add(columnExportBo.getTableName());
        }
        List<Column> columns = columnMapper.listByColumnsAndTables(tables, allColumns);
        ExceptionEnum.asser(CollectionUtils.isEmpty(columns), ExceptionEnum.COLUMN_EMPTY);

        // Group table-columns
        Map<String, Map<String, String>> groupTableNameMap = new HashMap<>(columns.size() >> SpecialInfoConstant.ORDINARY_ONE);
        for (Column column : columns) {
            Map<String, String> cs = groupTableNameMap.computeIfAbsent(column.getTableName(), k -> new HashMap<>(SpecialInfoConstant.SIZE_16));
            cs.put(column.getCname(), column.getName());
        }
        return groupTableNameMap;
    }

    private List<Map<String, Object>> getRows(ColumnExportBo columnExportBo, Map<String, ColumnExportBo> groupByNodeKey) throws Exception {

        // columns
        List<String> targetColumns = columnExportBo.getColumns();
        if (!targetColumns.contains(SpecialInfoConstant.ID)) {
            targetColumns.add(SpecialInfoConstant.ID);
        }
        String columns = String.join(SpecialInfoConstant.COMMA, targetColumns);

        // query params
        Map<String, Object> params = columnExportBo.getParams();
        if(null == params){
            params = new HashMap<>(SpecialInfoConstant.SIZE_1);
        }
        params.put(SpecialInfoConstant.DEL, SpecialInfoConstant.STR_N_FALSE);

        if (null == columnExportBo.getObjOfRequest()) {
            return businessMapper.commonSelect(columns, columnExportBo.getTableName(), params);
        }

        ColumnExportBo leftDataNode = groupByNodeKey.get(columnExportBo.getObjOfRequest().getLeftKey());
        ExceptionEnum.asser(null == leftDataNode, ExceptionEnum.DATA_NODE_NULL);

        if (CollectionUtils.isEmpty(leftDataNode.getRows())) {
            List<Map<String, Object>> leftRows = getRows(leftDataNode, groupByNodeKey);
            leftDataNode.setRows(leftRows);
        }
        return getRows(columnExportBo, leftDataNode);
    }


    private List<Map<String, Object>> getRows(ColumnExportBo columnExportBo, ColumnExportBo leftDataNode) {

        // 关联表
        String objOfTableName = columnExportBo.getObjOfRequest().getObjOfTableName();

        Map<String, Object> params = columnExportBo.getParams();

        // 左边节点关联表中所用字段
        String leftColumn = columnExportBo.getObjOfRequest().getLeftColumn();

        // 当前数据节点的目标字段
        String groupColumn = columnExportBo.getObjOfRequest().getGroupColumn();

        List<Object> inSqlColumn = new ArrayList<>();
        List<Map<String, Object>> rows = leftDataNode.getRows();
        for (Map<String, Object> row : rows) {
            Object o = row.get(leftColumn);
            inSqlColumn.add(o);
        }

        // 不存在关联表
        if (StringUtils.isEmpty(objOfTableName)) {
            params.put(String.format(SpecialInfoConstant.IN_FORMAT, groupColumn), inSqlColumn);
        } else {
            // 存在关联表
            List<Map<String, String>> objOfInfos = businessMapper.commonSelectObjOf(columnExportBo.getObjOfRequest().getObjOfTableName(), objOfFactory.buildMap(inSqlColumn));
            if (CollectionUtils.isNotEmpty(objOfInfos)) {
                List<String> itemId2 = objOfInfos.stream().map(map -> map.get(SpecialInfoConstant.ITEMID_2)).collect(Collectors.toList());
                params.put(String.format(SpecialInfoConstant.IN_FORMAT, groupColumn), itemId2);
            }
        }
        return businessMapper.commonSelect(String.join(SpecialInfoConstant.COMMA, columnExportBo.getColumns()), columnExportBo.getTableName(), params);
    }

}
