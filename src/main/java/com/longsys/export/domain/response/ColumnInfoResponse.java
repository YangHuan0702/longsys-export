package com.longsys.export.domain.response;

import com.longsys.export.domain.empty.Column;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字段信息响应模型
 * @author huan.yang
 * @date 2022-03-31
 */
@Data
@ApiModel(value = "字段信息响应模型")
@AllArgsConstructor
public class ColumnInfoResponse {

    @ApiModelProperty(value = "字段名")
    private String cname;

    @ApiModelProperty(value = "字段名")
    private String name;

    public static ColumnInfoResponse build(String cname,String name){
        return new ColumnInfoResponse(cname,name);
    }

    public static List<ColumnInfoResponse> buils(List<Column> cs){
        if(CollectionUtils.isEmpty(cs)){
            return null;
        }
        List<ColumnInfoResponse> r = new ArrayList<>();
        for (Column c : cs) {
            r.add(build(c.getCname(),c.getName()));
        }
        return r;
    }

}
