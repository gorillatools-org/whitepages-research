package com.horcrux.svg;

import com.salesforce.marketingcloud.messages.iam.j;
import java.util.HashMap;
import java.util.Map;

enum FilterProperties$EdgeMode {
    UNKNOWN(j.h),
    DUPLICATE("duplicate"),
    WRAP("wrap"),
    NONE("none");
    
    private static final Map edgeModeToEnum = null;
    private final String edgeMode;

    static {
        int i;
        edgeModeToEnum = new HashMap();
        for (FilterProperties$EdgeMode filterProperties$EdgeMode : values()) {
            edgeModeToEnum.put(filterProperties$EdgeMode.edgeMode, filterProperties$EdgeMode);
        }
    }

    private FilterProperties$EdgeMode(String str) {
        this.edgeMode = str;
    }

    static FilterProperties$EdgeMode getEnum(String str) {
        Map map = edgeModeToEnum;
        if (map.containsKey(str)) {
            return (FilterProperties$EdgeMode) map.get(str);
        }
        throw new IllegalArgumentException("Unknown 'edgeMode' Value: " + str);
    }

    public String toString() {
        return this.edgeMode;
    }
}
