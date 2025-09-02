package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;

enum TextProperties$FontWeight {
    Normal("normal"),
    Bold("bold"),
    w100("100"),
    w200("200"),
    w300("300"),
    w400("400"),
    w500("500"),
    w600("600"),
    w700("700"),
    w800("800"),
    w900("900"),
    Bolder("bolder"),
    Lighter("lighter");
    
    private static final Map weightToEnum = null;
    private final String weight;

    static {
        int i;
        weightToEnum = new HashMap();
        for (TextProperties$FontWeight textProperties$FontWeight : values()) {
            weightToEnum.put(textProperties$FontWeight.weight, textProperties$FontWeight);
        }
    }

    private TextProperties$FontWeight(String str) {
        this.weight = str;
    }

    static boolean hasEnum(String str) {
        return weightToEnum.containsKey(str);
    }

    static TextProperties$FontWeight get(String str) {
        return (TextProperties$FontWeight) weightToEnum.get(str);
    }

    public String toString() {
        return this.weight;
    }
}
