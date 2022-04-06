package com.longsys.export.service;

import com.longsys.export.domain.bo.ExportBo;
import com.longsys.export.domain.request.ExportRequest;

/**
 * 导出服务接口
 * @author huan.yang
 * @date 2022-03-29
 */
public interface ExportService {

    /**
     * 导出
     * @param request 导出请求
     * @return Excel业务模型
     * @throws Exception 导出异常
     */
    ExportBo export(ExportRequest request) throws Exception;

}
