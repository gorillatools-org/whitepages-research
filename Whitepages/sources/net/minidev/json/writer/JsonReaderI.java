package net.minidev.json.writer;

public abstract class JsonReaderI {
    private static String ERR_MSG = "Invalid or non Implemented status";
    public final JsonReader base;

    public abstract void addValue(Object obj, Object obj2);

    public Object convert(Object obj) {
        return obj;
    }

    public abstract Object createArray();

    public JsonReaderI(JsonReader jsonReader) {
        this.base = jsonReader;
    }

    public JsonReaderI startObject(String str) {
        throw new RuntimeException(String.valueOf(ERR_MSG) + " startObject(String key) in " + getClass() + " key=" + str);
    }

    public JsonReaderI startArray(String str) {
        throw new RuntimeException(String.valueOf(ERR_MSG) + " startArray in " + getClass() + " key=" + str);
    }

    public void setValue(Object obj, String str, Object obj2) {
        throw new RuntimeException(String.valueOf(ERR_MSG) + " setValue in " + getClass() + " key=" + str);
    }

    public Object createObject() {
        throw new RuntimeException(String.valueOf(ERR_MSG) + " createObject() in " + getClass());
    }
}
