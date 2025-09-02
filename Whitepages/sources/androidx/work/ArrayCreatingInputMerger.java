package androidx.work;

import androidx.work.Data;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class ArrayCreatingInputMerger extends InputMerger {
    public Data merge(List list) {
        Intrinsics.checkNotNullParameter(list, "inputs");
        Data.Builder builder = new Data.Builder();
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map keyValueMap = ((Data) it.next()).getKeyValueMap();
            Intrinsics.checkNotNullExpressionValue(keyValueMap, "input.keyValueMap");
            Iterator it2 = keyValueMap.entrySet().iterator();
            while (true) {
                if (it2.hasNext()) {
                    Map.Entry entry = (Map.Entry) it2.next();
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    Class cls = value != null ? value.getClass() : String.class;
                    Object obj = hashMap.get(str);
                    Intrinsics.checkNotNullExpressionValue(str, "key");
                    if (obj != null) {
                        Class<?> cls2 = obj.getClass();
                        if (Intrinsics.areEqual((Object) cls2, (Object) cls)) {
                            Intrinsics.checkNotNullExpressionValue(value, "value");
                            value = concatenateArrays(obj, value);
                        } else if (Intrinsics.areEqual((Object) cls2.getComponentType(), (Object) cls)) {
                            value = concatenateArrayAndNonArray(obj, value, cls);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } else if (!cls.isArray()) {
                        value = createArrayFor(value, cls);
                    }
                    Intrinsics.checkNotNullExpressionValue(value, "if (existingValue == nul…      }\n                }");
                    hashMap.put(str, value);
                }
            }
        }
        builder.putAll(hashMap);
        Data build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "output.build()");
        return build;
    }

    private final Object concatenateArrays(Object obj, Object obj2) {
        int length = Array.getLength(obj);
        int length2 = Array.getLength(obj2);
        Class<?> componentType = obj.getClass().getComponentType();
        Intrinsics.checkNotNull(componentType);
        Object newInstance = Array.newInstance(componentType, length + length2);
        System.arraycopy(obj, 0, newInstance, 0, length);
        System.arraycopy(obj2, 0, newInstance, length, length2);
        Intrinsics.checkNotNullExpressionValue(newInstance, "newArray");
        return newInstance;
    }

    private final Object concatenateArrayAndNonArray(Object obj, Object obj2, Class cls) {
        int length = Array.getLength(obj);
        Object newInstance = Array.newInstance(cls, length + 1);
        System.arraycopy(obj, 0, newInstance, 0, length);
        Array.set(newInstance, length, obj2);
        Intrinsics.checkNotNullExpressionValue(newInstance, "newArray");
        return newInstance;
    }

    private final Object createArrayFor(Object obj, Class cls) {
        Object newInstance = Array.newInstance(cls, 1);
        Array.set(newInstance, 0, obj);
        Intrinsics.checkNotNullExpressionValue(newInstance, "newArray");
        return newInstance;
    }
}
