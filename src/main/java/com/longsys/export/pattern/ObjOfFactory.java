package com.longsys.export.pattern;


import com.longsys.export.constant.SpecialInfoConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huan.yang
 * @date 2022-03-30
 */
public class ObjOfFactory {

    private static final ObjOfFactory OBJ_OF_FACTORY = new ObjOfFactory();

    public static ObjOfFactory getInstance() {
        return OBJ_OF_FACTORY;
    }


    public Map<String, Object> buildMap(List<Object> itemId1) {
        Map<String, Object> params = new HashMap<>(2);
        params.put(SpecialInfoConstant.DEL, SpecialInfoConstant.BOOL_INT_FALSE);
        params.put(SpecialInfoConstant.ITEMID_IN_1, itemId1);
        return params;
    }


}
