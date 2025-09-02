package net.minidev.json.reader;

import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;

public class ArrayWriter implements JsonWriterI {
    public void writeJSONString(Object obj, Appendable appendable, JSONStyle jSONStyle) {
        jSONStyle.arrayStart(appendable);
        boolean z = false;
        for (Object obj2 : (Object[]) obj) {
            if (z) {
                jSONStyle.objectNext(appendable);
            } else {
                z = true;
            }
            JSONValue.writeJSONString(obj2, appendable, jSONStyle);
        }
        jSONStyle.arrayStop(appendable);
    }
}
