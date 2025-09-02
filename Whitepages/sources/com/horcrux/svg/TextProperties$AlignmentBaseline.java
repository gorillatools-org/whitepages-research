package com.horcrux.svg;

import com.facebook.react.uimanager.ViewProps;
import java.util.HashMap;
import java.util.Map;

enum TextProperties$AlignmentBaseline {
    baseline("baseline"),
    textBottom("text-bottom"),
    alphabetic("alphabetic"),
    ideographic("ideographic"),
    middle("middle"),
    central("central"),
    mathematical("mathematical"),
    textTop("text-top"),
    bottom(ViewProps.BOTTOM),
    center("center"),
    top(ViewProps.TOP),
    textBeforeEdge("text-before-edge"),
    textAfterEdge("text-after-edge"),
    beforeEdge("before-edge"),
    afterEdge("after-edge"),
    hanging("hanging");
    
    private static final Map alignmentToEnum = null;
    private final String alignment;

    static {
        int i;
        alignmentToEnum = new HashMap();
        for (TextProperties$AlignmentBaseline textProperties$AlignmentBaseline : values()) {
            alignmentToEnum.put(textProperties$AlignmentBaseline.alignment, textProperties$AlignmentBaseline);
        }
    }

    private TextProperties$AlignmentBaseline(String str) {
        this.alignment = str;
    }

    static TextProperties$AlignmentBaseline getEnum(String str) {
        Map map = alignmentToEnum;
        if (map.containsKey(str)) {
            return (TextProperties$AlignmentBaseline) map.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public String toString() {
        return this.alignment;
    }
}
