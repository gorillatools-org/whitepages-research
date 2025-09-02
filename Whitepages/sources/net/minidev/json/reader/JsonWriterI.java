package net.minidev.json.reader;

import net.minidev.json.JSONStyle;

public interface JsonWriterI {
    void writeJSONString(Object obj, Appendable appendable, JSONStyle jSONStyle);
}
