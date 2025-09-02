package net.minidev.json;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minidev.asm.FieldFilter;
import net.minidev.json.annotate.JsonIgnore;

public abstract class JSONUtil {
    public static final JsonSmartFieldFilter JSON_SMART_FIELD_FILTER = new JsonSmartFieldFilter();

    public static class JsonSmartFieldFilter implements FieldFilter {
        public boolean canUse(Field field, Method method) {
            JsonIgnore jsonIgnore = (JsonIgnore) method.getAnnotation(JsonIgnore.class);
            return jsonIgnore == null || !jsonIgnore.value();
        }
    }

    public static String getGetterName(String str) {
        int length = str.length();
        char[] cArr = new char[(length + 3)];
        cArr[0] = 'g';
        cArr[1] = 'e';
        cArr[2] = 't';
        char charAt = str.charAt(0);
        if (charAt >= 'a' && charAt <= 'z') {
            charAt = (char) (charAt - ' ');
        }
        cArr[3] = charAt;
        for (int i = 1; i < length; i++) {
            cArr[i + 3] = str.charAt(i);
        }
        return new String(cArr);
    }

    public static String getIsName(String str) {
        int length = str.length();
        char[] cArr = new char[(length + 2)];
        cArr[0] = 'i';
        cArr[1] = 's';
        char charAt = str.charAt(0);
        if (charAt >= 'a' && charAt <= 'z') {
            charAt = (char) (charAt - ' ');
        }
        cArr[2] = charAt;
        for (int i = 1; i < length; i++) {
            cArr[i + 2] = str.charAt(i);
        }
        return new String(cArr);
    }
}
