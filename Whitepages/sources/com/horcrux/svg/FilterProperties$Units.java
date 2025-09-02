package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;

enum FilterProperties$Units {
    OBJECT_BOUNDING_BOX("objectBoundingBox"),
    USER_SPACE_ON_USE("userSpaceOnUse");
    
    private static final Map unitsToEnum = null;
    private final String units;

    static {
        int i;
        unitsToEnum = new HashMap();
        for (FilterProperties$Units filterProperties$Units : values()) {
            unitsToEnum.put(filterProperties$Units.units, filterProperties$Units);
        }
    }

    private FilterProperties$Units(String str) {
        this.units = str;
    }

    static FilterProperties$Units getEnum(String str) {
        Map map = unitsToEnum;
        if (map.containsKey(str)) {
            return (FilterProperties$Units) map.get(str);
        }
        throw new IllegalArgumentException("Unknown 'Unit' Value: " + str);
    }

    public String toString() {
        return this.units;
    }
}
