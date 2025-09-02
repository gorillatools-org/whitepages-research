package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;

enum FilterProperties$FeCompositeOperator {
    OVER("over"),
    IN("in"),
    OUT("out"),
    ATOP("atop"),
    XOR("xor"),
    ARITHMETIC("arithmetic");
    
    private static final Map typeToEnum = null;
    private final String type;

    static {
        int i;
        typeToEnum = new HashMap();
        for (FilterProperties$FeCompositeOperator filterProperties$FeCompositeOperator : values()) {
            typeToEnum.put(filterProperties$FeCompositeOperator.type, filterProperties$FeCompositeOperator);
        }
    }

    private FilterProperties$FeCompositeOperator(String str) {
        this.type = str;
    }

    static FilterProperties$FeCompositeOperator getEnum(String str) {
        Map map = typeToEnum;
        if (map.containsKey(str)) {
            return (FilterProperties$FeCompositeOperator) map.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public String toString() {
        return this.type;
    }
}
