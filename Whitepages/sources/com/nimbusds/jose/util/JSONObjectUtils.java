package com.nimbusds.jose.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public abstract class JSONObjectUtils {
    public static JSONObject parse(String str) {
        try {
            Object parse = new JSONParser(640).parse(str);
            if (parse instanceof JSONObject) {
                return (JSONObject) parse;
            }
            throw new ParseException("JSON entity is not an object", 0);
        } catch (net.minidev.json.parser.ParseException e) {
            throw new ParseException("Invalid JSON: " + e.getMessage(), 0);
        }
    }

    private static Object getGeneric(JSONObject jSONObject, String str, Class cls) {
        if (!jSONObject.containsKey(str)) {
            throw new ParseException("Missing JSON object member with key \"" + str + "\"", 0);
        } else if (jSONObject.get(str) != null) {
            Object obj = jSONObject.get(str);
            if (cls.isAssignableFrom(obj.getClass())) {
                return obj;
            }
            throw new ParseException("Unexpected type of JSON object member with key \"" + str + "\"", 0);
        } else {
            throw new ParseException("JSON object member with key \"" + str + "\" has null value", 0);
        }
    }

    public static long getLong(JSONObject jSONObject, String str) {
        return ((Number) getGeneric(jSONObject, str, Number.class)).longValue();
    }

    public static String getString(JSONObject jSONObject, String str) {
        return (String) getGeneric(jSONObject, str, String.class);
    }

    public static URI getURI(JSONObject jSONObject, String str) {
        try {
            return new URI((String) getGeneric(jSONObject, str, String.class));
        } catch (URISyntaxException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public static JSONArray getJSONArray(JSONObject jSONObject, String str) {
        return (JSONArray) getGeneric(jSONObject, str, JSONArray.class);
    }

    public static String[] getStringArray(JSONObject jSONObject, String str) {
        try {
            return (String[]) getJSONArray(jSONObject, str).toArray(new String[0]);
        } catch (ArrayStoreException unused) {
            throw new ParseException("JSON object member with key \"" + str + "\" is not an array of strings", 0);
        }
    }

    public static List getStringList(JSONObject jSONObject, String str) {
        return Arrays.asList(getStringArray(jSONObject, str));
    }

    public static JSONObject getJSONObject(JSONObject jSONObject, String str) {
        return (JSONObject) getGeneric(jSONObject, str, JSONObject.class);
    }
}
