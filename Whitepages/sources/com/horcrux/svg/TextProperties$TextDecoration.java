package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;

enum TextProperties$TextDecoration {
    None("none"),
    Underline("underline"),
    Overline("overline"),
    LineThrough("line-through"),
    Blink("blink");
    
    private static final Map decorationToEnum = null;
    private final String decoration;

    static {
        int i;
        decorationToEnum = new HashMap();
        for (TextProperties$TextDecoration textProperties$TextDecoration : values()) {
            decorationToEnum.put(textProperties$TextDecoration.decoration, textProperties$TextDecoration);
        }
    }

    private TextProperties$TextDecoration(String str) {
        this.decoration = str;
    }

    static TextProperties$TextDecoration getEnum(String str) {
        Map map = decorationToEnum;
        if (map.containsKey(str)) {
            return (TextProperties$TextDecoration) map.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public String toString() {
        return this.decoration;
    }
}
