package com.longsys.export.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 参数逻辑工具类
 *
 * @author huan.yang
 * @date 2022-03-29
 */
public class ParamsLogicUtil {

    /**
     * 校验String数组中是否有存在为空的元素
     * @param args String数组
     * @return true:包含，false:不包含
     */
    public static boolean validaStrArrayExistEmpty(String... args) {
        boolean r = false;
        for (String arg : args) {
            if (StringUtils.isEmpty(arg)) {
                r = true;
                break;
            }
        }
        return r;
    }


}
