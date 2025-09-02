package net.minidev.json.writer;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minidev.json.JSONArray;

public class DefaultMapperOrdered extends JsonReaderI {
    protected DefaultMapperOrdered(JsonReader jsonReader) {
        super(jsonReader);
    }

    public JsonReaderI startObject(String str) {
        return this.base.DEFAULT_ORDERED;
    }

    public JsonReaderI startArray(String str) {
        return this.base.DEFAULT_ORDERED;
    }

    public void setValue(Object obj, String str, Object obj2) {
        ((Map) obj).put(str, obj2);
    }

    public Object createObject() {
        return new LinkedHashMap();
    }

    public void addValue(Object obj, Object obj2) {
        ((JSONArray) obj).add(obj2);
    }

    public Object createArray() {
        return new JSONArray();
    }
}
