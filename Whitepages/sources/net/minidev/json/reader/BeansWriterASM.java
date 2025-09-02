package net.minidev.json.reader;

import net.minidev.asm.BeansAccess;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONUtil;

public class BeansWriterASM implements JsonWriterI {
    public void writeJSONString(Object obj, Appendable appendable, JSONStyle jSONStyle) {
        BeansAccess.get(obj.getClass(), JSONUtil.JSON_SMART_FIELD_FILTER);
        appendable.append('{');
        throw null;
    }
}
