package net.minidev.asm;

import java.util.HashMap;
import java.util.LinkedHashSet;

public abstract class BeansAccessConfig {
    protected static HashMap classFiledNameMapper = new HashMap();
    protected static HashMap classMapper = new HashMap();

    static {
        Class<Object> cls = Object.class;
        addTypeMapper(cls, DefaultConverter.class);
        addTypeMapper(cls, ConvertDate.class);
    }

    public static void addTypeMapper(Class cls, Class cls2) {
        synchronized (classMapper) {
            try {
                LinkedHashSet linkedHashSet = (LinkedHashSet) classMapper.get(cls);
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                    classMapper.put(cls, linkedHashSet);
                }
                linkedHashSet.add(cls2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
