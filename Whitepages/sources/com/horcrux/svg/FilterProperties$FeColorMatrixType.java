package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;

enum FilterProperties$FeColorMatrixType {
    MATRIX("matrix"),
    SATURATE("saturate"),
    HUE_ROTATE("hueRotate"),
    LUMINANCE_TO_ALPHA("luminanceToAlpha");
    
    private static final Map typeToEnum = null;
    private final String type;

    static {
        int i;
        typeToEnum = new HashMap();
        for (FilterProperties$FeColorMatrixType filterProperties$FeColorMatrixType : values()) {
            typeToEnum.put(filterProperties$FeColorMatrixType.type, filterProperties$FeColorMatrixType);
        }
    }

    private FilterProperties$FeColorMatrixType(String str) {
        this.type = str;
    }

    static FilterProperties$FeColorMatrixType getEnum(String str) {
        Map map = typeToEnum;
        if (map.containsKey(str)) {
            return (FilterProperties$FeColorMatrixType) map.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public String toString() {
        return this.type;
    }
}
