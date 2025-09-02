package net.minidev.json.reader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONUtil;

public class BeansWriter implements JsonWriterI {
    public void writeJSONString(Object obj, Appendable appendable, JSONStyle jSONStyle) {
        Object obj2;
        Method method;
        try {
            jSONStyle.objectStart(appendable);
            boolean z = false;
            for (Class cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
                for (Field field : cls.getDeclaredFields()) {
                    int modifiers = field.getModifiers();
                    if ((modifiers & 152) <= 0) {
                        if ((modifiers & 1) > 0) {
                            obj2 = field.get(obj);
                        } else {
                            try {
                                method = cls.getDeclaredMethod(JSONUtil.getGetterName(field.getName()), (Class[]) null);
                            } catch (Exception unused) {
                                method = null;
                            }
                            if (method == null) {
                                Class<?> type = field.getType();
                                if (type == Boolean.TYPE || type == Boolean.class) {
                                    method = cls.getDeclaredMethod(JSONUtil.getIsName(field.getName()), (Class[]) null);
                                }
                            }
                            if (method != null) {
                                obj2 = method.invoke(obj, (Object[]) null);
                            }
                        }
                        if (obj2 != null || !jSONStyle.ignoreNull()) {
                            if (z) {
                                jSONStyle.objectNext(appendable);
                            } else {
                                z = true;
                            }
                            JsonWriter.writeJSONKV(field.getName(), obj2, appendable, jSONStyle);
                        }
                    }
                }
            }
            jSONStyle.objectStop(appendable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
