package com.longsys.export.service.impl;

import com.longsys.export.constant.SpecialInfoConstant;
import com.longsys.export.domain.empty.ExportLog;
import com.longsys.export.domain.response.ExportLogResp;
import com.longsys.export.mapper.ExportLogMapper;
import com.longsys.export.service.ExportLogService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 导出日志服务层接口实现
 *
 * @author huan.yang
 * @date 2022-04-01
 */
@Service
public class ExportLogServiceImpl implements ExportLogService {


    private final ExportLogMapper exportLogMapper;

    @Autowired
    public ExportLogServiceImpl(ExportLogMapper exportLogMapper) {
        this.exportLogMapper = exportLogMapper;
    }


    @Override
    public void addLog(ExportLog exportLog) throws Exception {
        this.exportLogMapper.insert(exportLog);
    }

    @Override
    public List<ExportLogResp> fullLog() {
        List<ExportLog> exportLogs = exportLogMapper.selectByParams(new HashMap<>(SpecialInfoConstant.SIZE_0));
        if (CollectionUtils.isEmpty(exportLogs)) {
            return null;
        }
        List<ExportLogResp> r = new ArrayList<>(exportLogs.size());
        for (ExportLog exportLog : exportLogs) {
            ExportLogResp exportLogResp = ExportLogResp.build(exportLog);
            r.add(exportLogResp);
        }
        return r;
    }
}
