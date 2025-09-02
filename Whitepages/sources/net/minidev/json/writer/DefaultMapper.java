package net.minidev.json.writer;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class DefaultMapper extends JsonReaderI {
    protected DefaultMapper(JsonReader jsonReader) {
        super(jsonReader);
    }

    public JsonReaderI startObject(String str) {
        return this.base.DEFAULT;
    }

    public JsonReaderI startArray(String str) {
        return this.base.DEFAULT;
    }

    public Object createObject() {
        return new JSONObject();
    }

    public Object createArray() {
        return new JSONArray();
    }

    public void setValue(Object obj, String str, Object obj2) {
        ((JSONObject) obj).put(str, obj2);
    }

    public void addValue(Object obj, Object obj2) {
        ((JSONArray) obj).add(obj2);
    }
}
