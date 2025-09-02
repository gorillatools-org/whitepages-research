package com.horcrux.svg;

import com.salesforce.marketingcloud.messages.iam.j;
import java.util.HashMap;
import java.util.Map;

enum FilterProperties$FeBlendMode {
    UNKNOWN(j.h),
    NORMAL("normal"),
    MULTIPLY("multiply"),
    SCREEN("screen"),
    DARKEN("darken"),
    LIGHTEN("lighten");
    
    private static final Map typeToEnum = null;
    private final String mode;

    static {
        int i;
        typeToEnum = new HashMap();
        for (FilterProperties$FeBlendMode filterProperties$FeBlendMode : values()) {
            typeToEnum.put(filterProperties$FeBlendMode.mode, filterProperties$FeBlendMode);
        }
    }

    private FilterProperties$FeBlendMode(String str) {
        this.mode = str;
    }

    static FilterProperties$FeBlendMode getEnum(String str) {
        Map map = typeToEnum;
        if (map.containsKey(str)) {
            return (FilterProperties$FeBlendMode) map.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public String toString() {
        return this.mode;
    }
}
