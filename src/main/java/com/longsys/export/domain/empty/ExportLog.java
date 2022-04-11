package com.longsys.export.domain.empty;

import lombok.Data;

import java.util.Date;

/**
 * @author huan.yang
 * @date 2022-04-01
 */
@Data
public class ExportLog {
    /**
     * id
     */
    private long id;

    /**
     * 文件名称
     */
    private String fileName;


    /**
     * 导出说明
     */
    private String exportDesc;

    /**
     * 请求参数
     */
    private String request;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 导出时间
     */
    private Date eTime;

}
