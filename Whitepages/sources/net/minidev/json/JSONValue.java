package net.minidev.json;

import net.minidev.json.reader.JsonWriter;
import net.minidev.json.reader.JsonWriterI;
import net.minidev.json.writer.JsonReader;

public abstract class JSONValue {
    public static JSONStyle COMPRESSION = JSONStyle.NO_COMPRESS;
    public static final JsonReader defaultReader = new JsonReader();
    public static final JsonWriter defaultWriter = new JsonWriter();

    public static void writeJSONString(Object obj, Appendable appendable, JSONStyle jSONStyle) {
        if (obj == null) {
            appendable.append("null");
            return;
        }
        Class<?> cls = obj.getClass();
        JsonWriter jsonWriter = defaultWriter;
        JsonWriterI write = jsonWriter.getWrite(cls);
        if (write == null) {
            if (cls.isArray()) {
                write = JsonWriter.arrayWriter;
            } else {
                write = jsonWriter.getWriterByInterface(obj.getClass());
                if (write == null) {
                    write = JsonWriter.beansWriterASM;
                }
            }
            jsonWriter.registerWriter(write, cls);
        }
        write.writeJSONString(obj, appendable, jSONStyle);
    }

    public static String escape(String str) {
        return escape(str, COMPRESSION);
    }

    public static String escape(String str, JSONStyle jSONStyle) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        jSONStyle.escape(str, sb);
        return sb.toString();
    }

    public static void escape(String str, Appendable appendable, JSONStyle jSONStyle) {
        if (str != null) {
            jSONStyle.escape(str, appendable);
        }
    }
}
