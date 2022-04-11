package com.longsys.export.service;

import com.longsys.export.domain.empty.ExportLog;
import com.longsys.export.domain.response.ExportLogResp;

import java.util.List;

/**
 * 导出日志服务层接口
 *
 * @author huan.yang
 * @date 2022-04-01
 */
public interface ExportLogService {

    /**
     * 添加日志
     *
     * @param exportLog 日志
     * @return 日志ID
     * @throws Exception 异常
     */
    void addLog(ExportLog exportLog) throws Exception;


    /**
     * 获取所有的导出日志
     *
     * @return 导出日志响应模型列表
     */
    List<ExportLogResp> fullLog();


}
