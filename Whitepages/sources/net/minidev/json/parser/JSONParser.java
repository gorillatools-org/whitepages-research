package net.minidev.json.parser;

public class JSONParser {
    public static int DEFAULT_PERMISSIVE_MODE = (System.getProperty("JSON_SMART_SIMPLE") != null ? 1984 : -1);
    private int mode;
    private JSONParserString pString;

    private JSONParserString getPString() {
        if (this.pString == null) {
            this.pString = new JSONParserString(this.mode);
        }
        return this.pString;
    }

    public JSONParser(int i) {
        this.mode = i;
    }

    public Object parse(String str) {
        return getPString().parse(str);
    }
}
